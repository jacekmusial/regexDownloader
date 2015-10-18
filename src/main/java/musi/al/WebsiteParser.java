package musi.al;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String str = HttpRequest.get(url).body();
        
        if (httpRequest.ok() && !httpRequest.isBodyEmpty()) {
            this.source = str;
        }
    }
}