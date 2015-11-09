package al.musi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 *
 * Return ArrayLists TODO from given <var>URL</var>
 * 
 * @author re
 */
public class Download implements Runnable {
    
    /**
     * URL UUUUUUUUUUUUUUUURLGH
     */
    private ArrayList<Integer> as;
    
    /**
     * URL UUUUUUUUUUUUUUUURLGH
     */
    private URL url;
    
    /**
     * int size of file being CURRENTLY downloading
     */
    private int size;
    
    /**
     * String name of file being CURRENTLY downloading
     */
    private String filename;    
    
    /**
     * int return number of file
     */
    private int numberOfFileDownloading;    

    /**
     * Response code from server
    */
    private int response;
    
    /**
     * True if no-errors ({@link #errors} empty String), false otherwise
    */
    private FileOutputStream out;
    
    /**
     * True if no-errors ({@link #errors} empty String), false otherwise
    */
    private Boolean everythingOkay = false;
    
    /**
     * Variable contains throwed exceptions (e.toString)
    */
    private String errors;

    /**
     * @return the <var>everythingOkay</var> 
    */
    public Boolean everythingOkay() {
        return this.everythingOkay;
    }
    
    /**
     * Retrieve throwed errors
     * 
     * @return the String<var>errors</var>  or nothing, if empty
    */
    public String getErrors() {
        return this.errors != null ? this.errors : "";
    }
    
    /**
     * For current file (get
     * 
     * @return the int<var>errors</var>  or nothing, if empty
    */
    public int getNumberOfCurrentFileLenght() {
        return this.size;
    }
    
    
    /**
     * Return response code from server
     * 
     * @return {@link #response}
     */
    public int getResponse() {
        return this.response;
    }
    
    /** 
    * Constructor.  
    * @param url URL to files
    * @param filename Filename along with extension
    * @param path Path where save file
    */
    public Download(String url, String filename, String path) {
        this.errors = new String();
        
        try {
            out = new FileOutputStream(filename);
            this.url = new URL(url);
        } catch (MalformedURLException | FileNotFoundException ex) { } 
        
        this.everythingOkay = true;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(this.url.toString());
            URLConnection urlc = url.openConnection();
            int size = urlc.getContentLength();
            
            BufferedInputStream in = new BufferedInputStream
                (this.url.openStream());
                
            BufferedOutputStream bos = new BufferedOutputStream(out);
            
            byte[] data = new byte[1024];
            int read;
            while ((read = in.read(data, 0, 1024)) >= 0) {
                bos.write(data, 0, read);
            }   
            
            bos.close();
        }
        catch (FileNotFoundException ex) { 
            this.errors += ex.getMessage();
            this.everythingOkay = false;
        }
        catch (IOException ex) {
            this.errors += ex.getMessage();
            this.everythingOkay = false;
        } 
        finally {
            try {
                out.close();
            } catch (IOException ex) { }
        }
    }
}