package musi.al;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
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
    
    public WebsiteParser(String url, String searchWordOrRegex) {
        this.url = url;
        this.searchWordOrRegex = searchWordOrRegex;
        BufferedReader in = new BufferedReader(new InputStreamReader(
        HttpRequest.get(url).buffer()));
        StringBuffer buffer = new StringBuffer();
        String inputLine;
        
        try {
            while ((inputLine = in.readLine()) != null) {
                buffer.append(inputLine);
            }
        } catch (IOException ex) {
            Logger.getLogger(WebsiteParser.class.getName()).log(Level.SEVERE, null, ex.toString());
        }
        
        System.out.println(buffer.toString());
    }
    
    @Override
    public void run() {
        
    }
}