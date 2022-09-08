/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import Commands.CommandUtil;
import Commands.Tipos;
import static Strategies.BaseStrategyReceive.refPantalla;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
class TridentStrategyR extends BaseStrategyReceive {       
    public static final String STRATEGY_NAME = "trident";       
    
    @Override       public String getStrategyName() {           
        return STRATEGY_NAME;   
    }       
    
    @Override       
    public void execute1(String[] args) {  
//        (refPantalla.indexFi+"/"+TridentStrategy.STRATEGY_NAME+"/"+"1"+"/"+args[2]+"/"+args[3]+"/"+args[4]+"/"+args[5]+"/"+args[6]+"/"+args[7]);
        if(args != null && refPantalla.iniciada && args.length == 10){
            
            if(args.length == 10 && CommandUtil.isInteger(args[3]) && CommandUtil.isInteger(args[4]) && CommandUtil.isInteger(args[5]) && CommandUtil.isInteger(args[6]) && CommandUtil.isInteger(args[7]) && CommandUtil.isInteger(args[8])){
                List<Integer> puntosX = new ArrayList<Integer>();
                List<Integer> puntosY = new ArrayList<Integer>();
                Integer.valueOf(args[3]);
                puntosX.add(Integer.valueOf(args[3]));
                puntosX.add(Integer.valueOf(args[5]));
                puntosX.add(Integer.valueOf(args[7]));
                puntosY.add(Integer.valueOf(args[4]));
                puntosY.add(Integer.valueOf(args[6]));
                puntosY.add(Integer.valueOf(args[8]));
                int rango = (new Random()).nextInt(4)+1;
                Tipos.Tipo tipoEnviar;
                for(int i = 0; i < puntosX.size(); i++) {
                    int cX = puntosX.get(i);
                    int cY = puntosY.get(i);
                    for (int j = 0; j <= rango; j++){
                        if(cX+j<20){
                            refPantalla.juego.poblacion[cX+j][cY].addMensaje("Fue atacada por un tridente de "+args[9]);
                            tipoEnviar=refPantalla.juego.poblacion[cX+j][cY].morir(refPantalla.juego.unidades);
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
                        if(cX-j>=0){
                            refPantalla.juego.poblacion[cX-j][cY].addMensaje("Fue atacada por un tridente de "+args[9]);
                            tipoEnviar=refPantalla.juego.poblacion[cX-j][cY].morir(refPantalla.juego.unidades);
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
                        if(cY-j>=0){
                            refPantalla.juego.poblacion[cX][cY-j].addMensaje("Fue atacada por un tridente de "+args[9]);
                            tipoEnviar=refPantalla.juego.poblacion[cX][cY-j].morir(refPantalla.juego.unidades);
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
                        if(cY+j<30){
                            refPantalla.juego.poblacion[cX][cY+j].addMensaje("Fue atacada por un tridente de "+args[9]);
                            tipoEnviar=refPantalla.juego.poblacion[cX][cY+j].morir(refPantalla.juego.unidades);
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
                try {
                    refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(args[9]+" ataco a "+refPantalla.getTitle()+" con su tridente\nEn un rango de "+rango+" casillas");
                    refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(args[9]+" ataco a "+refPantalla.getTitle()+" con exito");
                } catch (IOException ex) {
                    Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
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
//        (refPantalla.indexFi+"/"+TridentStrategy.STRATEGY_NAME+"/"+"2"+"/"+args[2]+"/"+args[3]+"/"+args[4]);
        if(args != null && refPantalla.iniciada && args.length == 7){ 
              
            if(args.length == 7 && CommandUtil.isInteger(args[3]) && CommandUtil.isInteger(args[4]) && CommandUtil.isInteger(args[5])){
                int N = -1;
                int N1 = Integer.valueOf(args[3]);
                int N2 = Integer.valueOf(args[4]);
                int N3 = Integer.valueOf(args[5]);
                if(N1 <= 9  && N2 <= 9 && N3 <= 9 && N1 >= 0 && N2 >= 0 && N3 >= 0){
                    while(true){
                        String Num = JOptionPane.showInputDialog("Introduzca un Numero del 0 al 9:");
                        if(Num != null && CommandUtil.isInteger(Num)){
                            N = Integer.valueOf(Num);
                            if(N >= 0 && N <= 9){
                                break;
                            }
                        }
                    }
                    if(N == N1 || N == N2 || N == N3){
                        int rango = N1*N2*N3;
                        Tipos.Tipo tipoEnviar= null;
                        for (int i = 0; i < rango; i++) {
                            int randomX = (new Random()).nextInt(20);
                            int randomY = (new Random()).nextInt(30);
                            refPantalla.juego.poblacion[randomX][randomY].addMensaje("Fue atacada por "+args[6]);
                            tipoEnviar=refPantalla.juego.poblacion[randomX][randomY].morir(refPantalla.juego.unidades);
                            if(tipoEnviar!=null){
                                try {
                                    refPantalla.refCliente.hiloCliente.writer.writeInt(12);
                                    refPantalla.refCliente.hiloCliente.writer.writeUTF(Tipos.getString(tipoEnviar)+"/"+args[6]);
                                    refPantalla.refCliente.hiloCliente.writer.writeInt(Integer.valueOf(args[0]));
                                } catch (IOException ex) {
                                    Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(args[6]+" ataco a "+refPantalla.getTitle()+"\nEn un rango de "+rango+" casillas");
                            refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(args[6]+" ataco a "+refPantalla.getTitle()+" con exito");
                        } catch (IOException ex) {
                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(args[6]+" ataco a "+refPantalla.getTitle()+" sin exito");
                        } catch (IOException ex) {
                            Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else{
                    try {
                        refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                        refPantalla.refCliente.hiloCliente.writer.writeUTF(args[6]+" ataco a "+refPantalla.getTitle()+" sin exito");
                    } catch (IOException ex) {
                        Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else{
                try {
                    refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                    refPantalla.refCliente.hiloCliente.writer.writeUTF(args[6]+" ataco a "+refPantalla.getTitle()+" sin exito");
                } catch (IOException ex) {
                    Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            refPantalla.updatePorcentajes();
        }
        else{
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(14);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[6]+" ataco a "+refPantalla.getTitle()+" sin exito");
            } catch (IOException ex) {
                Logger.getLogger(FishStrategyR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void execute3(String[] args) {
        
        refPantalla.addMensajeResult("Datos de ataque invalidos");
            
    }
    
}