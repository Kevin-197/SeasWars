/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;
import Commands.Tipos;
import Commands.Tipos.Tipo;
import static Strategies.BaseStrategyReceive.refPantalla;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
class FishStrategyR extends BaseStrategyReceive {       
    public static final String STRATEGY_NAME = "fish";       
    
    @Override       public String getStrategyName() {           
        return STRATEGY_NAME;   
    }       
    
    @Override       
    public void execute1(String[] args) { 
        int muertes = 0;
        if(args != null && refPantalla.iniciada && args.length == 5){
            int Pescados = (new Random()).nextInt(201)+100;
            for (int i = 0; i < Pescados; i++) {
                int randomX = (new Random()).nextInt(20);
                int randomY = (new Random()).nextInt(30);
                refPantalla.juego.poblacion[randomX][randomY].hurt(33,Integer.valueOf(args[4]));
                refPantalla.juego.poblacion[randomX][randomY].addMensaje("Fue atacada por un pez de "+args[3]);
                if(refPantalla.juego.poblacion[randomX][randomY].vida <= 0){
                    muertes ++;
                    Tipo tipoEnviar=refPantalla.juego.poblacion[randomX][randomY].morir(refPantalla.juego.unidades);
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
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con "+Pescados+" peces\nY destruyo "+muertes+" casillas");
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
        int muertes = 0;
        if(args != null && refPantalla.iniciada && args.length == 4){
            
            int X1 = 0;
            int Y1 = 0;
            int X2 = 0;
            int Y2 = 29;
            int X3 = 19;
            int Y3 = 0;
            int X4 = 19;
            int Y4 = 29;
            int rango = (new Random()).nextInt(10)+1;
            for (int i = 0; i < rango; i++){
                for (int j = 0; j < rango; j++){
                    muertes += 4;
                    refPantalla.juego.poblacion[X1+i][Y1+j].addMensaje("Fue atacada por un tiburon de "+args[3]);
                    Tipo tipoEnviar=refPantalla.juego.poblacion[X1+i][Y1+j].morir(refPantalla.juego.unidades);
                    if(tipoEnviar!=null){
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[3]);
                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                        } catch (IOException ex) {
                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    refPantalla.juego.poblacion[X2+i][Y2-j].addMensaje("Fue atacada por un tiburon de "+args[3]);
                    tipoEnviar=refPantalla.juego.poblacion[X2+i][Y2-j].morir(refPantalla.juego.unidades);
                    if(tipoEnviar!=null){
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[3]);
                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                        } catch (IOException ex) {
                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    refPantalla.juego.poblacion[X3-i][Y3+j].addMensaje("Fue atacada por un tiburon de "+args[3]);
                    tipoEnviar=refPantalla.juego.poblacion[X3-i][Y3+j].morir(refPantalla.juego.unidades);
                    if(tipoEnviar!=null){
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[3]);
                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                        } catch (IOException ex) {
                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    refPantalla.juego.poblacion[X4-i][Y4-j].addMensaje("Fue atacada por un tiburon de "+args[3]);
                    tipoEnviar=refPantalla.juego.poblacion[X4-i][Y4-j].morir(refPantalla.juego.unidades);
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
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con tiburones en un rango de "+rango);
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
        int muertes = 0;
        if(args != null && refPantalla.iniciada && args.length == 5){ 
            
            int Pulpos = (new Random()).nextInt(31)+20;
            for (int i = 0; i < Pulpos; i++) {
                for (int j = 0; j < 8; j++){
                    int randomX = (new Random()).nextInt(20);
                    int randomY = (new Random()).nextInt(30);
                    refPantalla.juego.poblacion[randomX][randomY].hurt(25,Integer.valueOf(args[4]));
                    refPantalla.juego.poblacion[randomX][randomY].addMensaje("Fue atacada por un pulpo de "+args[3]);
                    if(refPantalla.juego.poblacion[randomX][randomY].vida <= 0){
                        muertes ++;
                        Tipo tipoEnviar=refPantalla.juego.poblacion[randomX][randomY].morir(refPantalla.juego.unidades);
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
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con "+Pulpos+" pulpos\nY murieron "+muertes+" casillas");
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
