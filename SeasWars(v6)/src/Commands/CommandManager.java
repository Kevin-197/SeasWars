/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.HashMap;

public class CommandManager {       
    private static CommandManager commandManager;       
    private static final HashMap<String, Class<? extends ICommand>> COMMANDS =          
            new HashMap<String, Class<? extends ICommand>>();       
    
    private CommandManager() {                     
        registCommand(SkipCommand.COMMAN_NAME, SkipCommand.class);   
        registCommand(ReadyCommand.COMMAN_NAME, ReadyCommand.class); 
        registCommand(StartCommand.COMMAN_NAME, StartCommand.class);
        registCommand(ChatCommand.COMMAN_NAME, ChatCommand.class);
        registCommand(PrivateChatCommand.COMMAN_NAME, PrivateChatCommand.class);
        registCommand(LifeCommand.COMMAN_NAME, LifeCommand.class);
        registCommand(ClearLifeCommand.COMMAN_NAME, ClearLifeCommand.class);
        registCommand(GiveUpCommand.COMMAN_NAME, GiveUpCommand.class);
        registCommand(ConsultCellCommand.COMMAN_NAME, ConsultCellCommand.class);
        registCommand(ConsultPlayerCommand.COMMAN_NAME, ConsultPlayerCommand.class);
        registCommand(AddWarriosCommand.COMMAN_NAME, AddWarriosCommand.class);
        registCommand(AttackCommand.COMMAN_NAME, AttackCommand.class);
        registCommand(SpecialAttackCommand.COMMAN_NAME, SpecialAttackCommand.class);
    }       
    
    public static synchronized CommandManager getIntance() {           
        if (commandManager == null) {               
            commandManager = new CommandManager();   
        }
        return commandManager;   
    }       
    
    public ICommand getCommand(String commandName) {           
        if (COMMANDS.containsKey(commandName.toUpperCase())) {               
            try {                   
                return COMMANDS.get(commandName.toUpperCase()).newInstance();
            } catch (Exception e) {   e.printStackTrace();                   
            return new ErrorCommand();   
            }           
        } 
        else {
            return new NotFoundCommand();   
        }   
    }       
    
    public void registCommand(String commandName, Class<? extends ICommand> command) {   
        COMMANDS.put(commandName.toUpperCase(), command);   
    }   
}

