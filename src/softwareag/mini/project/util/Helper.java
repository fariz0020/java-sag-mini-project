/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareag.mini.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fariz.nurzam
 */
public class Helper {
    
    private Scanner scanner = null;
    
    public Helper() {
        scanner = new Scanner(System.in);
    }
    
    public String textH1(String text) {
        return "== " + text + " ==";
    }
    
    public String textH2(String text) {
        return "==== " + text + " ====";
    }
    
    public String textH3(String text) {
        return "====== " + text + " ======";
    }
    
    public void endSection() {
        System.out.println("----- (press enter to continue) ------");
        scanner.nextLine();
    }
    
    public String capitailizeWord(String str) { 
        StringBuffer s = new StringBuffer(); 
  
        // Declare a character of space 
        // To identify that the next character is the starting 
        // of a new word 
        char ch = ' '; 
        for (int i = 0; i < str.length(); i++) { 
              
            // If previous character is space and current 
            // character is not space then it shows that 
            // current letter is the starting of the word 
            if (ch == ' ' && str.charAt(i) != ' ') 
                s.append(Character.toUpperCase(str.charAt(i))); 
            else
                s.append(str.charAt(i)); 
            ch = str.charAt(i); 
        } 
  
        // Return the string with trimming 
        return s.toString().trim(); 
    } 
    
}
