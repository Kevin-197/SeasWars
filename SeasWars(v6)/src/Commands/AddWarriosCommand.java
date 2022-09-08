/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Cliente.pantallaPrincipal;
import Commands.Tipos.Tipo;
import java.io.IOException;

public class AddWarriosCommand extends BaseCommand {       
    public static final String COMMAN_NAME = "addWarrior";       
    
    @Override       public String getCommandName() {           
        return COMMAN_NAME;   
    }       
    
    @Override       
    public void execute(String[] args) {    

        if(args != null && args.length == 7){
            Tipo tipo = Tipos.getTipo(args[2]);
            if(CommandUtil.isInteger(args[1]) && CommandUtil.isInteger(args[3]) && CommandUtil.isInteger(args[4]) && CommandUtil.isInteger(args[5]) && tipo != null){
                
                if(refPantalla.addFighter(args[0], Integer.valueOf(args[1]), tipo, Integer.valueOf(args[3]), Integer.valueOf(args[4]), Integer.valueOf(args[5]), args[6])){
                    refPantalla.addMensajeBitacora(args[0]+" va a luchar por tu pueblo!");
                }
                else{
                    new ErrorCommand().execute(null);
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

