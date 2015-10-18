/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musi.al;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author re
 */
public class FileDownloader implements Runnable{
    /**
     * URL UUUUUUUUUUUUUUUURLGH
     */
    private URL url;
    
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
     * Variable contains throwed toString exceptions
    */
    private String errors;

    /**
     * @return the <var>everythingOkay</var> 
    */
    public Boolean everythingOkay() {
        return this.everythingOkay;
    }
    
    /**
     * @return the <var>errors</var>  or nothing, if empty
    */
    public String getErrors() {
        return this.errors != null ? this.errors : "";
    }
    
    /**
     * Return response code from server
     * 
     * @return {@link #response}
     */
    public int getResponse() {
        return this.response;
    }
    
    public FileDownloader(String url, String filename) {
        try {
            out = new FileOutputStream(filename);
            this.url = new URL(url);
        } catch (MalformedURLException | FileNotFoundException ex) { } 
        
        this.everythingOkay = true;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(this.url.openStream())) {
            BufferedOutputStream bos = new BufferedOutputStream(out);
            
            byte[] data = new byte[1024];
            int read;
            while ((read = in.read(data, 0, 1024)) >= 0) {
                bos.write(data, 0, read);
            }   
            
            bos.close();
        }
        catch (FileNotFoundException ex) { }
        catch (IOException ex) {} 
        finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}