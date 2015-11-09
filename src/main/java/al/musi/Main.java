/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.musi;

/**
 *
 * @author re
 */
public class Main {
    public static void main (String[] args) {
        Download urlsDownloader = new Download(
                "http://georgegordon.org/audio/radio/mp3/360g-32.mp3", 
                "360g-32.mp3",
                ".");
        
        urlsDownloader.run();
        /*WebsiteParser websiteParser = new WebsiteParser(
        "http://georgegordon.org/audio/radio/bigpage.html", null);*/
        
        //websiteParser.run();
    }
}