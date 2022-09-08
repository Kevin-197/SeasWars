/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Cliente.pantallaPrincipal;
import java.io.IOException;

public class StartCommand extends BaseCommand {       
    public static final String COMMAN_NAME = "start";       
    
    @Override       public String getCommandName() {           
        return COMMAN_NAME;   
    }       
    
    @Override       
    public void execute(String[] args) {      
        if(args == null){
            try { 
                if(refPantalla.iniciada){
                    refPantalla.addMensaje("Ya inicio");
                }
                else
                    refPantalla.refCliente.hiloCliente.writer.writeInt(3);

            } catch (IOException ex) {

            }
        }
        else{
            new ErrorCommand().execute(null);
        }
        
    }   
}

