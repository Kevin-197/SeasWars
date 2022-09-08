/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Cliente.pantallaPrincipal;
import java.io.IOException;

public class ConsultCellCommand extends BaseCommand {       
    public static final String COMMAN_NAME = "cell";       
    
    @Override       public String getCommandName() {           
        return COMMAN_NAME;   
    }       
    
    @Override       
    public void execute(String[] args) {      
        if(args != null && args.length == 2 && refPantalla.iniciada){
            if(CommandUtil.isInteger(args[0]) && CommandUtil.isInteger(args[1])){
               
                refPantalla.consultCasilla(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
            }
        }
        else{
            new ErrorCommand().execute(null);
        }
        
    }   
}

