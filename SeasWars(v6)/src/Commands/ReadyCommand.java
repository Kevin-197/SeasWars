/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Cliente.pantallaPrincipal;
import java.io.IOException;

public class ReadyCommand extends BaseCommand {       
    public static final String COMMAN_NAME = "ready";       
    
    @Override       public String getCommandName() {           
        return COMMAN_NAME;   
    }       
    
    @Override       
    public void execute(String[] args) {           
        if(args == null && refPantalla.juego.unidades.size() == 3){
            try { 
            
                refPantalla.refCliente.hiloCliente.writer.writeInt(6);

            } catch (IOException ex) {

            }
        }
        else{
            new ErrorCommand().execute(null);
        }
    }   
}

