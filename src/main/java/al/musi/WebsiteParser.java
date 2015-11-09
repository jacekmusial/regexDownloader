package al.musi;

import java.io.IOException;
import java.util.regex.Pattern;
import al.musi.HttpRequest.HttpRequestException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 *
 * @author re
 */
final class WebsiteParser implements Runnable {
    /**
     * Pattern to compile regex
     */
    private /*final*/ Pattern pattern;
    
    /**
     * Given url to website
     */
    private final String url;
    
    /**
     * Source of website
     */
    private String source;
    
    /**
     * Set true if there are no errors, false otherwise
     */
    private Boolean everythingOkay;
    
    /***
     * HttpRequest object. See {@link al.musi.HttpRequest}
     */
    HttpRequest httpRequest;

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
    
    /**
     * @param s Base String
     * @param width 
     * @return substring of <var>s</var>
     */
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    
    /**
     * Parse all links from <var>url</var>.
     * @return String contains all links
     */
    private String getAllLinks() {
        Document doc = Parser.parse(getSource(), url);
        Elements links = doc.select("a[href]");
        
        StringBuilder ret = new StringBuilder();
        
        System.out.printf("\nLinks: (%d)", links.size());
        for( Element link : links) {
            System.out.printf(" * a: <%s>  (%s)",
                link.attr("abs:href"), trim(link.text(), 35));
            ret.append(link.attr("abs:href")).append(trim(link.text(), 35));
        }
        return ret.length() > 0 ? ret.toString() : null;
    }
    
    public WebsiteParser(String url, String pattern) {
        this.url = url;
        //this.pattern = Pattern.compile(pattern);
        httpRequest = new HttpRequest(url, "GET");
    }
    
    @Override
    public void run() {
        try {//get(url).
            String str = httpRequest.body();

            if (httpRequest.ok() && !httpRequest.isBodyEmpty()) {
                this.source = str;
            }
        } catch (HttpRequestException ex) {
            IOException cause = ex.getCause();
            System.out.println(cause.getMessage());
            this.everythingOkay = false;
        }
        
        /*
        Document doc = Parser.parse(getSource(), url);
        Elements media = doc.select("[src]");
        media.stream().forEach((src) -> {
            if (src.tagName().equals("img"))
                System.out.printf(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                System.out.printf(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        });*/
        
        /*Matcher matcher = pattern.matcher(getSource());
        while (matcher.find()) {// check all occurance
        System.out.print("Start index: " + matcher.start());
        System.out.print(" End index: " + matcher.end() + " ");
        System.out.println(matcher.group());
        }*/
    }
}