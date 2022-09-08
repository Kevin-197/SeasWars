/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import Commands.CommandUtil;
import static Strategies.BaseStrategy.refPantalla;
import java.io.IOException;

public class WavesStrategy extends BaseStrategy {       
    public static final String STRATEGY_NAME = "waves";       
    
    @Override       public String getStrategyName() {           
        return STRATEGY_NAME;   
    }       
    
    @Override
    public void execute1(String[] args, String Warrior, boolean damageBuff, int power) {           
        if(args != null && refPantalla.iniciada && args.length == 2){
            int index = refPantalla.juego.getIndex(args[1]); 
            if(index != -1){
                try {
                    refPantalla.refCliente.hiloCliente.writer.writeInt(11);
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.indexFi+"/"+WavesStrategy.STRATEGY_NAME+"/"+"1"+"/"+ Warrior);
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

    @Override
    public void execute2(String[] args, String Warrior, boolean damageBuff, int power) {
        if(args != null && refPantalla.iniciada && args.length <= 8){
            int index = refPantalla.juego.getIndex(args[1]); 
            if(index != -1){
                
                if(args.length == 4 && CommandUtil.isInteger(args[2]) && CommandUtil.isInteger(args[3])){
                    try {
                        refPantalla.refCliente.hiloCliente.writer.writeInt(11);
                        
                        if(damageBuff){
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.indexFi+"/"+WavesStrategy.STRATEGY_NAME+"/"+"2"+"/"+args[2]+"/"+args[3]+"/"+ Warrior+"/"+power);
                        }else{
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.indexFi+"/"+WavesStrategy.STRATEGY_NAME+"/"+"2"+"/"+args[2]+"/"+args[3]+"/"+ Warrior+"/"+0);
                    }
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
                try {
                    refPantalla.refCliente.hiloCliente.writer.writeInt(11);
                    if(damageBuff){
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.indexFi+"/"+WavesStrategy.STRATEGY_NAME+"/"+"3"+"/"+ Warrior+"/"+power);
                    }else{
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.indexFi+"/"+WavesStrategy.STRATEGY_NAME+"/"+"3"+"/"+ Warrior+"/"+0);
                    }
                    
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
}

