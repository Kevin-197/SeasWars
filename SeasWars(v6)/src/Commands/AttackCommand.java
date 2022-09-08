/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Cliente.Luchador;
import Cliente.pantallaPrincipal;
import java.io.IOException;
import java.util.Arrays;

public class AttackCommand extends BaseCommand {       
    public static final String COMMAN_NAME = "attack";       
    
    @Override       public String getCommandName() {           
        return COMMAN_NAME;   
    }       
    
    @Override       
    public void execute(String[] args) {    
         
        if(args != null && args.length >=4  && refPantalla.iniciada){
            if (refPantalla.nombreTurno.equals(refPantalla.getTitle())){
                
                Luchador warrior = refPantalla.juego.getWarrior(args[0]);
                if(warrior != null && warrior.vivo){
                    args = Arrays.copyOfRange(args, 1, args.length);
                    warrior.startAttack(args);
                    new SkipCommand().execute(null);
                }
                else{
                    new ErrorCommand().execute(null);
                }
            }
            else
                refPantalla.addMensaje("No es tu turno");
            
        }
        else{
            new ErrorCommand().execute(null);
        }
        
    }   
}

