/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Cliente.Luchador;
import Cliente.pantallaPrincipal;
import static Strategies.BaseStrategyReceive.refPantalla;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpecialAttackCommand extends BaseCommand {       
    public static final String COMMAN_NAME = "Sattack";       
    
    @Override       public String getCommandName() {           
        return COMMAN_NAME;   
    }       
    
    @Override       
    public void execute(String[] args) {    
         
        if(args != null && args.length ==2  && refPantalla.iniciada){
            if (refPantalla.nombreTurno.equals(refPantalla.getTitle())){
                
                Luchador warrior = refPantalla.juego.getWarrior(args[0]);
                if(warrior != null && warrior.vivo){
                    if("life".equals(args[1])){
                        warrior.sanar();
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.getTitle()+" uso una bonificaion especial");
                        } catch (IOException ex) {
                        }
                        new SkipCommand().execute(null);
                    }
                    else if("damage".equals(args[1])){
                        warrior.buff();
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.getTitle()+" uso una bonificaion especial");
                        } catch (IOException ex) {
                        }
                        new SkipCommand().execute(null);
                    }
                    else if("defense".equals(args[1])){
                        warrior.protect();
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.getTitle()+" uso una bonificaion especial");
                        } catch (IOException ex) {
                        }
                        new SkipCommand().execute(null);
                    }
                    else{
                        new ErrorCommand().execute(null);
                    }
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

