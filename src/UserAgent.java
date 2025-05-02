import java.util.Locale;

public class UserAgent {
    private final String os;
    private final String browser;


    public UserAgent(String userAgentStr) {
        this.os = parseOS(userAgentStr);
        this.browser = parseBrowser(userAgentStr);
    }

    private String parseOS(String userAgent){
        String lowOs= userAgent.toLowerCase();
        if (lowOs.contains("windows")){ return "Windows";}
        else if (lowOs.contains("macintosh")||lowOs.contains("mac os x")) {return "macOS";}
        else if (lowOs.contains("linux")) { return "Linux";}
        else {return "Other";}
    }
    private String parseBrowser(String userAgent){
        String lowBrowser= userAgent.toLowerCase();
        if (lowBrowser.contains("edg/")||lowBrowser.contains("edge")){ return "Edge";}
        else if (lowBrowser.contains("firefox/")) {return "Firefox";}
        else if (lowBrowser.contains("chrome/")) {return "Chrome";}
        else if (lowBrowser.contains("opera/") || lowBrowser.contains("opr/")) { return "Opera";}
        else {return "Other";}
    }

    public String getOs() {
        return os;
    }

    public String getBrowser() {
        return browser;
    }

}
