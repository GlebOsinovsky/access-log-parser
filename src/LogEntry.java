import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    private final String ip;
    private final LocalDateTime dateTime;
    private final HttpMethod method;
    private final String path;
    private final int responceCode;
    private final int responceSize;
    private final String referer;
    private final UserAgent userAgent;

    public LogEntry(String line) {
        int firstSpace = line.indexOf(' ');
        this.ip = line.substring(0, firstSpace);
        String rest = line.substring(firstSpace + 1);

        // извлечение двух пропущенных свойств
        int nextSpace1 = rest.indexOf(' ');
        int nextSpace2 = rest.indexOf(' ', nextSpace1 + 1);
        rest = rest.substring(nextSpace2 + 1);

        // извлечение даты и времени
        int bracketStart = rest.indexOf('[');
        int bracketEnd = rest.indexOf(']');
        String dateStr = rest.substring(bracketStart + 1, bracketEnd);
        this.dateTime = parseDateTime(dateStr);
        rest = rest.substring(bracketEnd + 2);

        // извлечение метода и пути
        int quoteStart = rest.indexOf('"');
        int quoteEnd = rest.indexOf('"', quoteStart + 1);
        String request = rest.substring(quoteStart + 1, quoteEnd);
        String[] requestParts = request.split(" ");
        this.method = parseMethod(requestParts.length > 0 ? requestParts[0] : "");
        this.path = requestParts.length > 1 ? requestParts[1] : "";
        rest = rest.substring(quoteEnd + 2);

        // извлечение HTTP-кода и размера данных
        int space1 = rest.indexOf(' ');
        int space2 = rest.indexOf(' ', space1 + 1);
        this.responceCode = Integer.parseInt(rest.substring(0, space1));
        this.responceSize = Integer.parseInt(rest.substring(space1 + 1, space2));;
        rest = rest.substring(space2 + 1);

        // извлечение referer
        quoteStart = rest.indexOf('"');
        quoteEnd = rest.indexOf('"', quoteStart + 1);
        this.referer = rest.substring(quoteStart + 1, quoteEnd);
        rest = rest.substring(quoteEnd + 2);

        // извлечение User-Agent
        quoteStart = rest.indexOf('"');
        quoteEnd = rest.indexOf('"', quoteStart + 1);
        this.userAgent = new UserAgent(rest.substring(quoteStart + 1, quoteEnd));
    }

    private LocalDateTime parseDateTime(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH
        );
        return LocalDateTime.parse(dateStr, formatter);
    }

    private HttpMethod parseMethod(String method) {
        try {
            return HttpMethod.valueOf(method);
        } catch (IllegalArgumentException e) {
            return HttpMethod.UNKNOWN;
        }
    }

    public String getIPadress() {
        return ip;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponceCode() {
        return responceCode;
    }

    public int getResponceSize() {
        return responceSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}
