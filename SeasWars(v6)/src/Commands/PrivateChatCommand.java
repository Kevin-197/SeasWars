/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Cliente.pantallaPrincipal;
import java.io.IOException;

public class PrivateChatCommand extends BaseCommand {       
    public static final String COMMAN_NAME = "Pchat";       
    
    @Override       public String getCommandName() {           
        return COMMAN_NAME;   
    }       
    
    @Override       
    public void execute(String[] args) {      
        if(args != null && args.length == 2){
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(7);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[0]);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[1]);
            } catch (IOException ex) {

            }
        }
        else{
            new ErrorCommand().execute(null);
        }
        
    }   
}

