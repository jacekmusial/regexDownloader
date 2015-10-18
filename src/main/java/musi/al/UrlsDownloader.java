/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musi.al;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author re
 */
public class UrlsDownloader {
    private String url;
    private String source;
    private String searchWordOrRegex;
    private Pattern pattern;
    
    public UrlsDownloader(String url, String searchWordOrRegex) {
        this.url = url;
        this.searchWordOrRegex = searchWordOrRegex;
        HttpRequest httpRequest = new HttpRequest(url, "");
        if (httpRequest.ok() && !httpRequest.isBodyEmpty()) {
            try {
                Path path = Paths.get("asd.mp3");
                InputStream inputStream = httpRequest.buffer();
                Files.copy(inputStream, path);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UrlsDownloader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UrlsDownloader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /*private static class ThreadLoop implements Runnable {
    @Override
    public void run() {
    
    }
    }*/
}