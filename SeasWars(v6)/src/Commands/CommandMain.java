/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.Arrays;


public class CommandMain {       
    public static void startCommand(String cm) {  
        CommandManager manager = CommandManager.getIntance();                
        if (cm.trim().length() != 0) {
            String[] commands = CommandUtil.tokenizerArgs(cm);               
            String commandName = commands[0];
            String[] commandArgs = null; 
            if (commands.length > 1) {        
                commandArgs = Arrays.copyOfRange(commands, 1, commands.length);   
            } 
            ICommand command = manager.getCommand(commandName);   
            command.execute(commandArgs);
        }     
         
    }   
}

