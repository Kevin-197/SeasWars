/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Cliente.pantallaPrincipal;
import java.io.IOException;

public class ConsultPlayerCommand extends BaseCommand {       
    public static final String COMMAN_NAME = "player";       
    
    @Override       public String getCommandName() {           
        return COMMAN_NAME;   
    }       
    
    @Override       
    public void execute(String[] args) {    
         
        if(args != null && args.length == 1 && refPantalla.iniciada){
            
            int index = refPantalla.juego.getIndex(args[0]); 
            if(index != -1){
                try {
                    refPantalla.refCliente.hiloCliente.writer.writeInt(9);
                    refPantalla.refCliente.hiloCliente.writer.writeInt(index);
                    refPantalla.refCliente.hiloCliente.writer.writeInt(refPantalla.indexFi);
                } catch (IOException ex) {

                }
            } 
            else{
                new ErrorCommand().execute(null);
            }
        }
        else{
            new ErrorCommand().execute(null);
        }
        
    }   
}

