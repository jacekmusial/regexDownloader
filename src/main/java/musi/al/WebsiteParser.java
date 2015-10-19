package musi.al;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import musi.al.HttpRequest.HttpRequestException;
import static musi.al.NewClass.EXAMPLE_TEST;

/**
 *
 * @author re
 */
final class WebsiteParser implements Runnable {
    //TODO maybe add some final regex? Or detect 
    /**
     * Pattern to compile regex
     */
    private final Pattern pattern;
    private final String url;
    /**
     * Source of website
     */
    private String source;
    private Boolean everythingOkay;
    
    /***
     * HttpRequest object. See {@link musi.al.HttpRequest}
     */
    private HttpRequest httpRequest;

    /**
     * @return 
     */
    public int getResponse() {
        return this.httpRequest.code();
    }
    
    /**
     * @return <var>source</var> code of website
     */
    public String getSource() {
        return this.source;
    }
    
    public WebsiteParser(String url, String pattern) {
        this.url = url;
        this.pattern = Pattern.compile(pattern);
        this.everythingOkay = true;
        httpRequest = null;
    }
    
    @Override
    public void run() {
        try {
            String str = HttpRequest.get(url).body();

            if (httpRequest.ok() && !httpRequest.isBodyEmpty()) {
                this.source = str;
            }
        } catch (HttpRequestException ex) {
            IOException cause = ex.getCause();
            System.out.println(cause.getMessage());
        }
        
        Matcher matcher = pattern.matcher(getSource());
        
        while (matcher.find()) {// check all occurance
          System.out.print("Start index: " + matcher.start());
          System.out.print(" End index: " + matcher.end() + " ");
          System.out.println(matcher.group());
        }
    }
}