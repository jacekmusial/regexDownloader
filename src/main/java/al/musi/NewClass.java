/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.musi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author re
 */
public class NewClass {
    public static final String EXAMPLE_TEST = "This is my small example string which I'm going to use for pattern matching.";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\w+");
        // in case you would like to ignore case sensitivity,
        // you could use this statement:
        // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(EXAMPLE_TEST);
        // check all occurance
        while (matcher.find()) {
          System.out.print("Start index: " + matcher.start());
          System.out.print(" End index: " + matcher.end() + " ");
          System.out.println(matcher.group());
        }
        // now create a new pattern and matcher to replace whitespace with tabs
        Pattern replace = Pattern.compile("\\s+");
        Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
        System.out.println(matcher2.replaceAll("\t"));
        /*Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("aaaaab");
        boolean b = m.matches();
        
        if (b) {
        System.out.println(m.toMatchResult());
        }*/
    }
}
