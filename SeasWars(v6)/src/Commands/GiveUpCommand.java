/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Cliente.pantallaPrincipal;
import java.io.IOException;

public class GiveUpCommand extends BaseCommand {       
    public static final String COMMAN_NAME = "giveUp";       
    
    @Override       public String getCommandName() {           
        return COMMAN_NAME;   
    }       
    
    @Override       
    public void execute(String[] args) {           
        if(args == null){
            try { 
            
                if (refPantalla.nombreTurno.equals(refPantalla.getTitle())){
                    refPantalla.refCliente.hiloCliente.writer.writeInt(8);
                    new SkipCommand().execute(null);
                }
                else
                    refPantalla.addMensaje("No es tu turno");
            } catch (IOException ex) {

            }
        }
        else{
            new ErrorCommand().execute(null);
        }
    }   
}

