/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musi.al;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author re
 */
public class Main {
    public static void main (String[] args) {
        FileDownloader urlsDownloader = new FileDownloader(
            "http://georgegordon.org/audio/radio/mp3/360g-32.mp3");
    }
}