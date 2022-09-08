/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import Cliente.Disaster;
import Commands.CommandUtil;
import Commands.Tipos;
import static Strategies.BaseStrategy.refPantalla;
import static Strategies.BaseStrategyReceive.refPantalla;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
class WavesStrategyR extends BaseStrategyReceive {       
    public static final String STRATEGY_NAME = "waves";       
    
    @Override       public String getStrategyName() {           
        return STRATEGY_NAME;   
    }       
    
    @Override       
    public void execute1(String[] args) {           
        if(args != null && refPantalla.iniciada && args.length == 4){
          
            int randomX = (new Random()).nextInt(20);
            int randomY = (new Random()).nextInt(30);
            int radio = (new Random()).nextInt(9)+2;
            System.out.println(randomX+"  "+randomY);
            Tipos.Tipo tipoEnviar= null;
            for (int j = 0; j <= radio; j++){
                for (int k = 0; k <= radio; k++){
                    if(j == 0 && k == 0){
                        refPantalla.juego.poblacion[randomX][randomY].desastre = new Disaster(radio,"T",true);
                        refPantalla.juego.poblacion[randomX][randomY].addMensaje("Se genero un tornado de "+args[3]);
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
                        refPantalla.juego.poblacion[randomX][randomY].refLabel.setText("T");
                    }
                    else{
                        if(randomX+j<20){
                            if(randomY+k < 30){
                                refPantalla.juego.poblacion[randomX+j][randomY+k].desastre = new Disaster(radio,"T",false);
                                
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
                                refPantalla.juego.poblacion[randomX+j][randomY+k].refLabel.setText("T");
                            }
                            if(randomY-k >= 0){
                                refPantalla.juego.poblacion[randomX+j][randomY-k].desastre = new Disaster(radio,"T",false);
                                
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
                                refPantalla.juego.poblacion[randomX+j][randomY-k].refLabel.setText("T");
                            }
                        }
                        if(randomX-j >= 0){
                            if(randomY+k < 30){
                                refPantalla.juego.poblacion[randomX-j][randomY+k].desastre = new Disaster(radio,"T",false);
                                
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
                                refPantalla.juego.poblacion[randomX-j][randomY+k].refLabel.setText("T");
                            }
                            if(randomY-k >= 0){
                               refPantalla.juego.poblacion[randomX-j][randomY-k].desastre = new Disaster(radio,"T",false);
                               
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
                               refPantalla.juego.poblacion[randomX-j][randomY-k].refLabel.setText("T");
                            }
                        }
                    }
                }
            }
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con un tornado\nGenerado en "+randomX+","+randomY);
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
        if(args != null && refPantalla.iniciada && args.length == 7){
            if(args.length == 7 && CommandUtil.isInteger(args[3]) && CommandUtil.isInteger(args[4])){               
                int X = Integer.valueOf(args[3]);
                int Y = Integer.valueOf(args[4]);
                if(X<20 && X>=0 && Y>=0 && Y<30){
                    if(refPantalla.juego.poblacion[X][Y].desastre != null && refPantalla.juego.poblacion[X][Y].desastre.isOrigen() && "T".equals(refPantalla.juego.poblacion[X][Y].desastre.getNom())){
                        int casillas = refPantalla.juego.poblacion[X][Y].desastre.getRadio()*10;
                        Tipos.Tipo tipoEnviar=null;
                        for (int i = 0; i < casillas; i++) {
                            int randomX = (new Random()).nextInt(20);
                            int randomY = (new Random()).nextInt(30);
                            //refPantalla.juego.poblacion[randomX][randomY].vida -= 25;
                            refPantalla.juego.poblacion[randomX][randomY].addMensaje("Fue atacada por la basura de "+args[5]);
                            refPantalla.juego.poblacion[randomX][randomY].hurt(25,Integer.valueOf(args[6]));
                            int basuraR = (new Random()).nextInt(2);
                            if(basuraR == 1){
                                refPantalla.juego.poblacion[randomX][randomY].setRadioactivo();
                            }
                            if(refPantalla.juego.poblacion[randomX][randomY].vida <= 0){
                                
                                tipoEnviar=refPantalla.juego.poblacion[randomX][randomY].morir(refPantalla.juego.unidades);
                                if(tipoEnviar!=null){
                                    try {
                                        refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                        refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[5]);
                                        refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" con basura del tornado\nAfecto en "+casillas+" casillas");
                            refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" con exito");
                        } catch (IOException ex) {
                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" sin exito");
                        } catch (IOException ex) {
                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else{
                    try {
                        refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" sin exito");
                    } catch (IOException ex) {
                        Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else{
                try {
                    refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" sin exito");
                } catch (IOException ex) {
                    Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            refPantalla.updatePorcentajes();
        }
        else{
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" sin exito");
            } catch (IOException ex) {
                Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void execute3(String[] args) {
        if(args != null && refPantalla.iniciada && args.length == 5){
            int daño = 10*((new Random()).nextInt(10)+1);
            Tipos.Tipo tipoEnviar=null;
            for(int i =0; i<20;i++){
                for(int j =0; j<30;j++){
                    if(refPantalla.juego.poblacion[i][j].radiactivo){
                        refPantalla.juego.poblacion[i][j].addMensaje("Fue atacada por la radiacion de "+args[3]);
                        refPantalla.juego.poblacion[i][j].hurt(daño,Integer.valueOf(args[4]));
                        if(refPantalla.juego.poblacion[i][j].vida <= 0){
                            
                            tipoEnviar=refPantalla.juego.poblacion[i][j].morir(refPantalla.juego.unidades);
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
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con radiacion");
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
