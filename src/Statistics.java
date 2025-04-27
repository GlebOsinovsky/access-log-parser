import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        totalTraffic = 0;
        minTime = LocalDateTime.MAX;
        maxTime = LocalDateTime.MIN;
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
    }
    public double getTrafficRate (){
        if (minTime.equals(LocalDateTime.MAX) || maxTime.equals(LocalDateTime.MIN)) {
            return 0.0;
        }
        long hours = Duration.between(minTime, maxTime).toHours();
        return hours>0 ? (double) totalTraffic/hours : totalTraffic;
    }
}
