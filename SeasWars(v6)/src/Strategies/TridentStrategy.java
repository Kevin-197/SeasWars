/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import Commands.CommandUtil;
import static Strategies.BaseStrategy.refPantalla;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;

public class TridentStrategy extends BaseStrategy {       
    public static final String STRATEGY_NAME = "trident";       
    
    @Override       public String getStrategyName() {           
        return STRATEGY_NAME;   
    }       
    
    @Override       
    public void execute1(String[] args, String Warrior, boolean damageBuff, int power) {           
        if(args != null && refPantalla.iniciada && args.length <= 8){
            int index = refPantalla.juego.getIndex(args[1]); 
            if(index != -1){
                
                if(args.length == 8 && CommandUtil.isInteger(args[2]) && CommandUtil.isInteger(args[3]) && CommandUtil.isInteger(args[4]) && CommandUtil.isInteger(args[5]) && CommandUtil.isInteger(args[6]) && CommandUtil.isInteger(args[7])){
                    try {
                        refPantalla.refCliente.hiloCliente.writer.writeInt(11);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.indexFi+"/"+TridentStrategy.STRATEGY_NAME+"/"+"1"+"/"+args[2]+"/"+args[3]+"/"+args[4]+"/"+args[5]+"/"+args[6]+"/"+args[7]+"/"+ Warrior);
                        refPantalla.refCliente.hiloCliente.writer.writeInt(index);
                    } catch (IOException ex) {

                    }
                }
                else{
                    refPantalla.addMensajeResult("Datos de ataque invalidos");
                }
                        
            }
            else{
                refPantalla.addMensajeResult("Datos de ataque invalidos");
            }
        }
        else{
            refPantalla.addMensajeResult("Datos de ataque invalidos");
        }
    }   

    @Override
    public void execute2(String[] args, String Warrior, boolean damageBuff, int power) {
        if(args != null && refPantalla.iniciada && args.length <= 8){
            int index = refPantalla.juego.getIndex(args[1]); 
            if(index != -1){
                
                if(args.length == 5 && CommandUtil.isInteger(args[2]) && CommandUtil.isInteger(args[3]) && CommandUtil.isInteger(args[4])){
                    try {
                        refPantalla.refCliente.hiloCliente.writer.writeInt(11);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.indexFi+"/"+TridentStrategy.STRATEGY_NAME+"/"+"2"+"/"+args[2]+"/"+args[3]+"/"+args[4]+"/"+ Warrior);
                        refPantalla.refCliente.hiloCliente.writer.writeInt(index);
                    } catch (IOException ex) {

                    }
                }
                else{
                    refPantalla.addMensajeResult("Datos de ataque invalidos");
                }
                        
            }
            else{
                refPantalla.addMensajeResult("Datos de ataque invalidos");
            }
        }
        else{
            refPantalla.addMensajeResult("Datos de ataque invalidos");
        }
    }

    @Override
    public void execute3(String[] args, String Warrior, boolean damageBuff, int power) {
        if(args != null && refPantalla.iniciada && args.length == 2){
            int index = refPantalla.juego.getIndex(args[1]); 
            if(index != -1){
                
                refPantalla.juego.activarKrakenProtect();
                        
            }
            else{
                refPantalla.addMensajeResult("Datos de ataque invalidos");
            }
        }
        else{
            refPantalla.addMensajeResult("Datos de ataque invalidos");
        }
    }
}

