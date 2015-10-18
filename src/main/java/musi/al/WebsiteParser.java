package musi.al;

import java.io.IOException;
import musi.al.HttpRequest.HttpRequestException;

/**
 *
 * @author re
 */
final class WebsiteParser implements Runnable {
    private String url;
    private String source;
    private String searchWordOrRegex;
    private Boolean everythingOkay;
    
    public WebsiteParser(String url, String searchWordOrRegex) {
        this.url = url;
        this.searchWordOrRegex = searchWordOrRegex;
        this.everythingOkay = true;
        /*BufferedReader in = new BufferedReader(new InputStreamReader(
        HttpRequest.get(url).buffer()));
        
        if (httpRequest.ok() || httpRequest.isBodyEmpty()) {
        //this.isEverythingOkay = httpRequest.
        }
        
        StringBuffer buffer = new StringBuffer();
        String inputLine;
        
        try {
        while ((inputLine = in.readLine()) != null) {
        buffer.append(inputLine);
        }
        } catch (IOException ex) {
        Logger.getLogger(WebsiteParser.class.getName()).log(Level.SEVERE, null, ex.toString());
        this.whatIsTheProblem = ex.toString();
        }
        System.out.println(buffer.toString());
        */
    }
    
    @Override
    public void run() {
        HttpRequest httpRequest = null;
        
        try {
            String str = HttpRequest.get(url).body();

            if (httpRequest.ok() && !httpRequest.isBodyEmpty()) {
                this.source = str;
            }
        } catch (HttpRequestException ex) {
            IOException cause = ex.getCause();
            System.out.println(cause.getMessage());
        }
    }
}