/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import Commands.Tipos;
import static Strategies.BaseStrategyReceive.refPantalla;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThundersStrategyR extends BaseStrategyReceive {       
    public static final String STRATEGY_NAME = "thunders";       
    
    @Override       public String getStrategyName() {           
        return STRATEGY_NAME;   
    }       
    
    @Override       
    public void execute1(String[] args) { 
//        (refPantalla.indexFi+"/"+ThundersStrategy.STRATEGY_NAME+"/"+"1", String Warrior)
        int muertes = 0;
        if(args != null && refPantalla.iniciada && args.length == 5){
            Tipos.Tipo tipoEnviar = null;
            for (int i = 0; i < 100; i++) {
                int randomX = (new Random()).nextInt(20);
                int randomY = (new Random()).nextInt(30);
                int daño = (new Random()).nextInt(2);
                if(daño == 0){
                    refPantalla.juego.poblacion[randomX][randomY].hurt(10,Integer.valueOf(args[4]));
                }
                else{
                    refPantalla.juego.poblacion[randomX][randomY].hurt(20,Integer.valueOf(args[4]));
                }
                refPantalla.juego.poblacion[randomX][randomY].addMensaje("Fue atacada por un rayo de "+args[3]);
                if(refPantalla.juego.poblacion[randomX][randomY].vida <= 0){
                    muertes++;
                    tipoEnviar=refPantalla.juego.poblacion[randomX][randomY].morir(refPantalla.juego.unidades);
                    if(tipoEnviar!=null){
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[3]);
                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                        } catch (IOException ex) {
                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con "+100+" rayos\nY destruyo "+muertes+" casillas");
                refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con exito");
            } catch (IOException ex) {
                Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
            }
            refPantalla.updatePorcentajes();
        }
        else{
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" sin exito");
            } catch (IOException ex) {
                Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   

    @Override
    public void execute2(String[] args) {
        if(args != null && refPantalla.iniciada && args.length == 4){ 
            Tipos.Tipo tipoEnviar = null;
            int Truenos = (new Random()).nextInt(6)+5;
            int rango = (new Random()).nextInt(9)+2;
            for (int i = 0; i < Truenos; i++) {
                int randomX = (new Random()).nextInt(20);
                int randomY = (new Random()).nextInt(30);
                for (int j = 0; j < rango; j++){
                    for (int k = 0; k < rango; k++){
                        if(randomX+j<20){
                            if(randomY+k < 30){
                                refPantalla.juego.poblacion[randomX+j][randomY+k].addMensaje("Fue atacada por un rayo de Poseidon de "+args[3]);
                                tipoEnviar=refPantalla.juego.poblacion[randomX+j][randomY+k].morir(refPantalla.juego.unidades);
                                if(tipoEnviar!=null){
                                    try {
                                        refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                        refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[3]);
                                        refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            if(randomY-k >= 0){
                                refPantalla.juego.poblacion[randomX+j][randomY-k].addMensaje("Fue atacada por un rayo de Poseidon de "+args[3]);
                                tipoEnviar=refPantalla.juego.poblacion[randomX+j][randomY-k].morir(refPantalla.juego.unidades);
                                if(tipoEnviar!=null){
                                    try {
                                        refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                        refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[3]);
                                        refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                        if(randomX-j >= 0){
                            if(randomY+k < 30){
                                refPantalla.juego.poblacion[randomX-j][randomY+k].addMensaje("Fue atacada por un rayo de Poseidon de "+args[3]);
                                tipoEnviar=refPantalla.juego.poblacion[randomX-j][randomY+k].morir(refPantalla.juego.unidades);
                                if(tipoEnviar!=null){
                                    try {
                                        refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                        refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[3]);
                                        refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            if(randomY-k >= 0){
                               refPantalla.juego.poblacion[randomX-j][randomY-k].addMensaje("Fue atacada por un rayo de Poseidon de "+args[3]);
                               tipoEnviar=refPantalla.juego.poblacion[randomX-j][randomY-k].morir(refPantalla.juego.unidades);
                                if(tipoEnviar!=null){
                                    try {
                                        refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                        refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[3]);
                                        refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        } 
                    }
                }
            }
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con "+Truenos+" rayos de Poseidon\nEn un rango de "+rango+" casillas");
                refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con exito");
            } catch (IOException ex) {
                Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
            }
            refPantalla.updatePorcentajes();
        }
        else{
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" sin exito");
            } catch (IOException ex) {
                Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void execute3(String[] args) {
        if(args != null && refPantalla.iniciada && args.length == 5){ 
            int muertes = 0;
            int Anguilas = (new Random()).nextInt(76)+25;
            Tipos.Tipo tipoEnviar;
            for (int i = 0; i < Anguilas; i++) {
                int randomX = (new Random()).nextInt(20);
                int randomY = (new Random()).nextInt(30);
                int descargas = (new Random()).nextInt(10)+1;
                refPantalla.juego.poblacion[randomX][randomY].addMensaje("Fue atacada por una anguila de "+args[3]);
                for (int j = 0; j < descargas; j++){
                    refPantalla.juego.poblacion[randomX][randomY].hurt(10, Integer.valueOf(args[4]));
                }
                if(refPantalla.juego.poblacion[randomX][randomY].vida <= 0){
                    muertes++;
                    tipoEnviar=refPantalla.juego.poblacion[randomX][randomY].morir(refPantalla.juego.unidades);
                    if(tipoEnviar!=null){
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[3]);
                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                        } catch (IOException ex) {
                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
            }
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con "+Anguilas+" anguilas\nY destruyo "+muertes+" casillas");
                refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con exito");
            } catch (IOException ex) {
                Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            refPantalla.updatePorcentajes();
        }
        else{
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" sin exito");
            } catch (IOException ex) {
                Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    
}

