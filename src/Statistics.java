import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private HashSet<String> pages;
    private HashMap<String,Integer> osCount;
    private HashSet<String> notFoundPages;
    private HashMap<String,Integer> browserCount;

    public Statistics() {
        totalTraffic = 0;
        minTime = LocalDateTime.MAX;
        maxTime = LocalDateTime.MIN;
        pages = new HashSet<>();
        osCount = new HashMap<>();
        notFoundPages = new HashSet<>();
        browserCount = new HashMap<>();
    }
    public void addEntry(LogEntry log){
        totalTraffic+=log.getResponceSize();

        LocalDateTime logTime = log.getDateTime();
        if (logTime.isBefore(minTime)){
            minTime = logTime;
        }
        if (logTime.isAfter(maxTime)){
            maxTime=logTime;
        }
        if (log.getResponceCode()==200){
            pages.add(log.getPath());
        }
        if (log.getResponceCode()==404){
            notFoundPages.add(log.getPath());
        }
        String os = log.getUserAgent().getOs();
        osCount.put(os,osCount.getOrDefault(os,0)+1);
        String browser = log.getUserAgent().getBrowser();
        browserCount.put(browser,browserCount.getOrDefault(browser,0)+1);
    }

    public HashSet<String> getPages() {
        return pages;
    }
    public HashSet<String> getNotFoundPages(){
        return notFoundPages;
    }

    public HashMap<String,Double> getOsStatistic(){
        HashMap<String,Double> osStats =new HashMap<>();
        int total = 0;

        for (int count: osCount.values()){
            total+=count;
        }
        if (total==0) return osStats;

        for (Map.Entry<String,Integer>entry: osCount.entrySet()){
        double ratio = (double) entry.getValue()/total;
        osStats.put(entry.getKey(), ratio);
        }
        return osStats;
    }
    public HashMap<String,Double> getBrowserStatistic(){
        HashMap<String,Double> browserStats = new HashMap<>();
        int total = 0;
        for (int count:browserCount.values()){
            total+=count;
        }
        if (total==0) return browserStats;
        for (Map.Entry<String,Integer>entry: browserCount.entrySet()){
            double ratio = (double) entry.getValue()/total;
            browserStats.put(entry.getKey(),ratio);
        }
        return browserStats;
    }


    public double getTrafficRate (){
        if (minTime.equals(LocalDateTime.MAX) || maxTime.equals(LocalDateTime.MIN)) {
            return 0.0;
        }
        long hours = Duration.between(minTime, maxTime).toHours();
        return hours>0 ? (double) totalTraffic/hours : totalTraffic;
    }
}
