import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    private List<LogEntry> logEntries = new ArrayList<>();
    private long totalTraffic;
    private LocalDateTime minTime = LocalDateTime.MAX;;
    private LocalDateTime maxTime = LocalDateTime.MIN;
    private HashSet<String> pages;
    private HashMap<String,Integer> osCount;
    private HashSet<String> notFoundPages;
    private HashMap<String,Integer> browserCount;
    private HashMap<Long,Integer> peakVisits;
    private HashSet<String> refererDomains;

    public Statistics() {
        totalTraffic = 0;
        minTime = LocalDateTime.MAX;
        maxTime = LocalDateTime.MIN;
        pages = new HashSet<>();
        osCount = new HashMap<>();
        notFoundPages = new HashSet<>();
        browserCount = new HashMap<>();
        peakVisits = new HashMap<>();
        refererDomains = new HashSet<>();
    }
    //получаем данные из логов
    public void addEntry(LogEntry log){
        totalTraffic+=log.getResponceSize();

        logEntries.add(log);
        if (log.getDateTime().isBefore(minTime)) minTime = log.getDateTime();
        if (log.getDateTime().isAfter(maxTime)) maxTime = log.getDateTime();
        if (log.getResponceCode()==200)pages.add(log.getPath());
        if (log.getResponceCode()==404) notFoundPages.add(log.getPath());
        if (!log.getUserAgent().isBot()){
            long second = log.getDateTime().toEpochSecond(ZoneOffset.UTC);
            peakVisits.put(second,peakVisits.getOrDefault(second,0)+1);
        }
        String os = log.getUserAgent().getOs();
        osCount.put(os,osCount.getOrDefault(os,0)+1);
        String browser = log.getUserAgent().getBrowser();
        browserCount.put(browser,browserCount.getOrDefault(browser,0)+1);

        String referer = log.getReferer();
        if  (referer!=null && !referer.isEmpty()) {
            try {
                if (!referer.startsWith("http://") && !referer.startsWith("https://")) referer = "http://" + referer;

                URI uri = new URI(referer);
                String domain = uri.getHost();
                if (domain != null) {
                    if (domain.startsWith("www.")) domain = domain.substring(4);
                    refererDomains.add(domain);
                }
            } catch (URISyntaxException e) {
                System.err.println("Некорректный referer: " + referer);
            }
    }}
    public HashSet<String> getRefererDomains(){
        return refererDomains;
    }
 //максимальная посещаемость одним юзером
    public int getMaxVisitsByUser(){
        return logEntries.stream()
                .filter(logEntry -> !logEntry.getUserAgent().isBot())
                .collect(Collectors.groupingBy(LogEntry::getIPadress,Collectors.counting()))
                .values().stream().max(Long::compare).map(Long::intValue).orElse(0);
    }
    //пиковая посещаемость за секунду
    public int getPeakVisitPerSecond(){
        return peakVisits.values().stream().max(Integer::compare).orElse(0);
    }

    //среднее колв-во посещений за час (без ботов)
    public double getAverageVisitPerHour(){
        long hours = Duration.between(minTime,maxTime).toHours();
        if (hours==0) return 0.0;
        return logEntries.stream().filter(logEntry -> !logEntry.getUserAgent().isBot()).count()/(double) hours;
    }
//    среднее кол-во  ошибок в час
    public double getAverageErrorsPerHour(){
        long hours = Duration.between(minTime,maxTime).toHours();
        if (hours==0) return 0.0;
        return logEntries.stream().filter(logEntry -> logEntry.getResponceCode()>=300 && logEntry.getResponceCode()<600)
                .count()/(double) hours;
    }
//    средняя посещаемость одним юзером
    public double getAverageVisitPerUser(){
        long uniqueUser = logEntries.stream().filter(logEntry -> logEntry.getUserAgent().isBot())
                .map(LogEntry::getIPadress).distinct().count();
        if (uniqueUser == 0 ) return 0.0;
        return logEntries.stream().filter(logEntry -> !logEntry.getUserAgent().isBot()).count()/(double) uniqueUser;
    }

    public HashSet<String> getPages() {
        return pages;
    }
    //получаем страницы с 404
    public HashSet<String> getNotFoundPages(){
        return notFoundPages;
    }
    //статистика ОС
    public Map<String,Double> getOsStatistic(){
        long total = logEntries.size();
        return logEntries.stream().collect(Collectors.groupingBy(
                logEntry -> logEntry.getUserAgent().getOs(),
                Collectors.counting() ))
                .entrySet().stream().collect(Collectors.toMap(HashMap.Entry::getKey,entry->entry.getValue()/(double)total));
    }
    //статистика браущеров
    public Map<String,Double> getBrowserStatistic(){
        long total = logEntries.stream().filter(logEntry -> !logEntry.getUserAgent().isBot()).count();
        return logEntries.stream().filter(logEntry -> !logEntry.getUserAgent().isBot())
                .collect(Collectors.groupingBy(logEntry -> logEntry.getUserAgent().getBrowser(),Collectors.counting()))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,entry->entry.getValue()/(double) total));

    }
    //средний трафик в час
    public double getTrafficRate (){
        if (minTime.equals(LocalDateTime.MAX) || maxTime.equals(LocalDateTime.MIN)) {
            return 0.0;
        }
        long hours = Duration.between(minTime, maxTime).toHours();
        return hours>0 ? (double) totalTraffic/hours : totalTraffic;
    }
}
