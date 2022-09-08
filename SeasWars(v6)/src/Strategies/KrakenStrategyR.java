/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import Commands.CommandUtil;
import Commands.Tipos;
import static Strategies.BaseStrategy.refPantalla;
import static Strategies.BaseStrategyReceive.refPantalla;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
class KrakenStrategyR extends BaseStrategyReceive{
    public static final String STRATEGY_NAME = "kraken";       
    
    @Override       public String getStrategyName() {           
        return STRATEGY_NAME;   
    }       
    
    @Override       
    public void execute1(String[] args) {           
//        (refPantalla.indexFi+"/"+KrakenStrategy.STRATEGY_NAME+"/"+"1"+"/"+args[2]+"/"+args[3]+"/"+args[4]+"/"+args[5]+"/"+args[6]+"/"+args[7])
        if(args != null && refPantalla.iniciada && args.length == 10){
            int muertes = 0;
            if(args.length == 10 && CommandUtil.isInteger(args[3]) && CommandUtil.isInteger(args[4]) && CommandUtil.isInteger(args[5]) && CommandUtil.isInteger(args[6]) && CommandUtil.isInteger(args[7]) && CommandUtil.isInteger(args[8])){
                if(refPantalla.juego.KrakenProtec){
                    try {
                        refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(args[9]+" ataco a "+refPantalla.getTitle()+" con El kraken y tenia proteccion\nEl ataque fue retornado");
                        refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(args[9]+" ataco a "+refPantalla.getTitle()+" sin exito");
                    } catch (IOException ex) {
                        Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    refPantalla.juego.KrakenProtec = false;
                    try {
                        refPantalla.refCliente.hiloCliente.writer.writeInt(11);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.indexFi+"/"+KrakenStrategy.STRATEGY_NAME+"/"+"1"+"/"+args[3]+"/"+args[4]+"/"+args[5]+"/"+args[6]+"/"+args[7]+"/"+args[8]+"/"+args[9]);
                        refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                    } catch (IOException ex) {

                    }
                }
                else{
                    List<Integer> puntosX = new ArrayList<Integer>();
                    List<Integer> puntosY = new ArrayList<Integer>();
                    Integer.valueOf(args[3]);
                    puntosX.add(Integer.valueOf(args[3]));
                    puntosX.add(Integer.valueOf(args[5]));
                    puntosX.add(Integer.valueOf(args[7]));
                    puntosY.add(Integer.valueOf(args[4]));
                    puntosY.add(Integer.valueOf(args[6]));
                    puntosY.add(Integer.valueOf(args[8]));
                    Tipos.Tipo tipoEnviar=null;
                    for(int i = 0; i < puntosX.size(); i++) {
                        int cX = puntosX.get(i);
                        int cY = puntosY.get(i);
                        if(cX < 20 && cY < 30 && cX >= 0 && cY >= 0){
                            muertes += 9;
                            refPantalla.juego.poblacion[cX][cY].addMensaje("Fue atacada por un tentaculo del kraken de "+args[9]);
                            tipoEnviar=refPantalla.juego.poblacion[cX][cY].morir(refPantalla.juego.unidades);
                            if(tipoEnviar!=null){
                            try {
                                refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[9]);
                                refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                            } catch (IOException ex) {
                                Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            }
                            
                            
                            
                            if(cX+1<20){
                                refPantalla.juego.poblacion[cX+1][cY].addMensaje("Fue atacada por un tentaculo del kraken de "+args[9]);
                                tipoEnviar=refPantalla.juego.poblacion[cX+1][cY].morir(refPantalla.juego.unidades);
                                if(tipoEnviar!=null){
                                try {
                                    refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                    refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[9]);
                                    refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                } catch (IOException ex) {
                                    Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                }
                                if(cY+1 < 30){
                                    refPantalla.juego.poblacion[cX][cY+1].addMensaje("Fue atacada por un tentaculo del kraken de "+args[9]);
                                    tipoEnviar=refPantalla.juego.poblacion[cX][cY+1].morir(refPantalla.juego.unidades);
                                    if(tipoEnviar!=null){
                                        try {
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[9]);
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                        } catch (IOException ex) {
                                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    refPantalla.juego.poblacion[cX+1][cY+1].addMensaje("Fue atacada por un tentaculo del kraken de "+args[9]);
                                    tipoEnviar=refPantalla.juego.poblacion[cX+1][cY+1].morir(refPantalla.juego.unidades);
                                    if(tipoEnviar!=null){
                                        try {
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[9]);
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                        } catch (IOException ex) {
                                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                                if(cY-1 >= 0){
                                    refPantalla.juego.poblacion[cX][cY-1].addMensaje("Fue atacada por un tentaculo del kraken de "+args[9]);
                                    tipoEnviar=refPantalla.juego.poblacion[cX][cY-1].morir(refPantalla.juego.unidades);
                                    if(tipoEnviar!=null){
                                        try {
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[9]);
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                        } catch (IOException ex) {
                                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    refPantalla.juego.poblacion[cX+1][cY-1].addMensaje("Fue atacada por un tentaculo del kraken de "+args[9]);
                                    tipoEnviar=refPantalla.juego.poblacion[cX+1][cY-1].morir(refPantalla.juego.unidades);
                                    if(tipoEnviar!=null){
                                        try {
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[9]);
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                        } catch (IOException ex) {
                                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            }
                            if(cX-1 >= 0){
                                refPantalla.juego.poblacion[cX-1][cY].addMensaje("Fue atacada por un tentaculo del kraken de "+args[9]);
                                tipoEnviar=refPantalla.juego.poblacion[cX-1][cY].morir(refPantalla.juego.unidades);
                                    if(tipoEnviar!=null){
                                        try {
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[9]);
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                        } catch (IOException ex) {
                                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                if(cY+1 < 30){
                                    refPantalla.juego.poblacion[cX-1][cY+1].addMensaje("Fue atacada por un tentaculo del kraken de "+args[9]);
                                    tipoEnviar=refPantalla.juego.poblacion[cX-1][cY+1].morir(refPantalla.juego.unidades);
                                    if(tipoEnviar!=null){
                                        try {
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[9]);
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                        } catch (IOException ex) {
                                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                                if(cY-1 >= 0){
                                    refPantalla.juego.poblacion[cX-1][cY-1].addMensaje("Fue atacada por un tentaculo del kraken de "+args[9]);
                                    tipoEnviar=refPantalla.juego.poblacion[cX-1][cY-1].morir(refPantalla.juego.unidades);
                                    if(tipoEnviar!=null){
                                        try {
                                            refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                            refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[9]);
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
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(args[9]+" ataco a "+refPantalla.getTitle()+" con 3 tentaculos\nY ataco "+muertes+" casillas");
                        refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(args[9]+" ataco a "+refPantalla.getTitle()+" con exito");
                    } catch (IOException ex) {
                        Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else{
                try {
                    refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(args[9]+" ataco a "+refPantalla.getTitle()+" sin exito");
                } catch (IOException ex) {
                    Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            refPantalla.updatePorcentajes();
        }
        else{
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[9]+" ataco a "+refPantalla.getTitle()+" sin exito");
            } catch (IOException ex) {
                Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   

    @Override
    public void execute2(String[] args) {
//        (refPantalla.indexFi+"/"+KrakenStrategy.STRATEGY_NAME+"/"+"2"+"/"+args[2]+"/"+args[3]);
        if(args != null && refPantalla.iniciada && args.length == 6){
            if(args.length == 6 && CommandUtil.isInteger(args[3]) && CommandUtil.isInteger(args[4])){
                int rango = (new Random()).nextInt(8)+1;
                if(refPantalla.juego.KrakenProtec){
                    try {
                        refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" con El kraken y tenia proteccion\nEl ataque fue retornado");
                        refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" sin exito");
                    } catch (IOException ex) {
                        Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    refPantalla.juego.KrakenProtec = false;
                    try {
                        refPantalla.refCliente.hiloCliente.writer.writeInt(11);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.indexFi+"/"+KrakenStrategy.STRATEGY_NAME+"/"+"2"+"/"+args[3]+"/"+args[4]+"/"+args[5]);
                        refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                    } catch (IOException ex) {

                    }
                }
                else{
                    int X = Integer.valueOf(args[3]);
                    int Y = Integer.valueOf(args[4]);
                    Tipos.Tipo tipoEnviar =null;
                    for (int j = 0; j <=rango; j++){
                        
                        if(X+j<20){
                            refPantalla.juego.poblacion[X+j][Y].addMensaje("Fue atacada por un respiro del kraken de "+args[5]);
                            tipoEnviar=refPantalla.juego.poblacion[X+j][Y].morir(refPantalla.juego.unidades);
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
                        if(X-j>=0){
                            refPantalla.juego.poblacion[X-j][Y].addMensaje("Fue atacada por un respiro del kraken de "+args[5]);
                            tipoEnviar=refPantalla.juego.poblacion[X-j][Y].morir(refPantalla.juego.unidades);
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
                        if(Y-j>=0){
                            refPantalla.juego.poblacion[X][Y-j].addMensaje("Fue atacada por un respiro del kraken de "+args[5]);
                            tipoEnviar=refPantalla.juego.poblacion[X][Y-j].morir(refPantalla.juego.unidades);
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
                        if(Y+j<30){
                            refPantalla.juego.poblacion[X][Y+j].addMensaje("Fue atacada por un respiro del kraken de "+args[5]);
                            tipoEnviar=refPantalla.juego.poblacion[X][Y+j].morir(refPantalla.juego.unidades);
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
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" con el aliento del Kraken\nY ataco en un rango de "+rango+" casillas");
                        refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" con exito");
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
//        (refPantalla.indexFi+"/"+KrakenStrategy.STRATEGY_NAME+"/"+"3");
        if(args != null && refPantalla.iniciada && args.length == 4){
            if(refPantalla.juego.KrakenProtec){
                try {
                    refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con El kraken y tenia proteccion\nEl ataque fue retornado");
                    refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" sin exito");
                } catch (IOException ex) {
                    Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                }
                refPantalla.juego.KrakenProtec = false;
                try {
                    refPantalla.refCliente.hiloCliente.writer.writeInt(11);
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(refPantalla.indexFi+"/"+KrakenStrategy.STRATEGY_NAME+"/"+"3"+"/"+args[3]);
                    refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                } catch (IOException ex) {

                }
            }
            else{
                Tipos.Tipo tipoEnviar =null;
                int randomX = (new Random()).nextInt(20);
                int randomY = (new Random()).nextInt(30);
                int rango = (new Random()).nextInt(9)+1;
                for (int i = 0; i <= rango; i++){
                    for (int j = 0; j <= rango; j++){
                        if(randomX+i<20){
                            if(randomY+j < 30){
                                refPantalla.juego.poblacion[randomX+i][randomY+j].addMensaje("Fue atacada por el kraken de "+args[3]);
                                tipoEnviar=refPantalla.juego.poblacion[randomX+i][randomY+j].morir(refPantalla.juego.unidades);
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
                            if(randomY-j >= 0){
                                refPantalla.juego.poblacion[randomX+i][randomY-j].addMensaje("Fue atacada por el kraken de "+args[3]);
                                tipoEnviar=refPantalla.juego.poblacion[randomX+i][randomY-j].morir(refPantalla.juego.unidades);
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
                        if(randomX-i >= 0){
                            if(randomY+j < 30){
                                refPantalla.juego.poblacion[randomX-i][randomY+j].addMensaje("Fue atacada por el kraken de "+args[3]);
                                tipoEnviar=refPantalla.juego.poblacion[randomX-i][randomY+j].morir(refPantalla.juego.unidades);
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
                            if(randomY-j >= 0){
                                refPantalla.juego.poblacion[randomX-i][randomY-j].addMensaje("Fue atacada por el kraken de "+args[3]);
                                tipoEnviar=refPantalla.juego.poblacion[randomX-i][randomY-j].morir(refPantalla.juego.unidades);
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
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con el Kraken\nY ataco en un rango de "+rango+" casillas");
                    refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con exito");
                } catch (IOException ex) {
                    Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                }
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
