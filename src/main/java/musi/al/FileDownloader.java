/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musi.al;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author re
 */
public class FileDownloader {
    private URL url;
    private String source;
    
    public FileDownloader(String url) {
        try {
            this.url = new URL(url);
            
            BufferedInputStream in =
                new BufferedInputStream(this.url.openStream());
            FileOutputStream out = 
                new FileOutputStream("360g-32.mp3");
            BufferedOutputStream bos = 
                new BufferedOutputStream(out);
            
            byte[] data = new byte[1024];
            int read;
            
            while ((read = in.read(data, 0, 1024)) >= 0) {
                bos.write(data, 0, read);
            }
            bos.close();
            in.close();
        } catch (MalformedURLException ex) { } catch (IOException ex) {
            Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*private static class ThreadLoop implements Runnable {
    @Override
    public void run() {
    
    }
    }*/
}