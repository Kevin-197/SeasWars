/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.ArrayList;
import java.util.List;

public class CommandUtil {       
    public static String[] tokenizerArgs(String args) {           
        List<String> tokens = new ArrayList<String>();           
        char[] charArray = args.toCharArray();           
        String contact = "";           
        boolean inText = false;           
        
        for (char c : charArray) {               
            if (c == '/' && !inText) {                   
                if (contact.length() != 0) {   
                    tokens.add(contact);                       
                    contact = "";   
                }               
            } else if (c == '"') {
                if (inText) {   tokens.add(contact);                       
                contact = "";                       
                inText = false;                   
                } else {                       
                    inText = true;   
                }               
            } else {   
                contact += c;   
            }   
        }           
        if (contact.trim().length() != 0) {   
            tokens.add(contact.trim());   
        }           
        
        String[] argsArray = new String[tokens.size()];   
        argsArray = tokens.toArray(argsArray);           
        return argsArray;   
    }
    
    public static boolean isInteger(String numero){
        try{
            Integer.parseInt(numero);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}

