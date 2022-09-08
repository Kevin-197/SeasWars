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
class VolcanoesStrategyR extends BaseStrategyReceive {       
    public static final String STRATEGY_NAME = "volcanoes";       
    
    @Override       public String getStrategyName() {           
        return STRATEGY_NAME;   
    }       
    
    @Override       
    public void execute1(String[] args) {           
        if(args != null && refPantalla.iniciada && args.length == 4){
            Tipos.Tipo tipoEnviar;
            int randomX = (new Random()).nextInt(20);
            int randomY = (new Random()).nextInt(30);
            int radio = (new Random()).nextInt(10)+1;
            System.out.println(randomX+"  "+randomY);
            for (int j = 0; j <= radio; j++){
                for (int k = 0; k <= radio; k++){
                    if(j == 0 && k == 0){
                        refPantalla.juego.poblacion[randomX][randomY].desastre = new Disaster(radio,"V",true);
                        refPantalla.juego.poblacion[randomX][randomY].addMensaje("Se genero un volcan de "+args[3]);
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
                        refPantalla.juego.poblacion[randomX][randomY].refLabel.setText("V");
                    }
                    else{
                        if(randomX+j<20){
                            if(randomY+k < 30){
                                refPantalla.juego.poblacion[randomX+j][randomY+k].desastre = new Disaster(radio,"V",false);
                                
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
                                refPantalla.juego.poblacion[randomX+j][randomY+k].refLabel.setText("V");
                            }
                            if(randomY-k >= 0){
                                refPantalla.juego.poblacion[randomX+j][randomY-k].desastre = new Disaster(radio,"V",false);
                                
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
                                refPantalla.juego.poblacion[randomX+j][randomY-k].refLabel.setText("V");
                            }
                        }
                        if(randomX-j >= 0){
                            if(randomY+k < 30){
                                refPantalla.juego.poblacion[randomX-j][randomY+k].desastre = new Disaster(radio,"V",false);
                                
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
                                refPantalla.juego.poblacion[randomX-j][randomY+k].refLabel.setText("V");
                            }
                            if(randomY-k >= 0){
                               refPantalla.juego.poblacion[randomX-j][randomY-k].desastre = new Disaster(radio,"V",false);
                               
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
                               refPantalla.juego.poblacion[randomX-j][randomY-k].refLabel.setText("V");
                            }
                        }
                    }
                }
            }
            try {
                refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                refPantalla.refCliente.hiloCliente.writer.writeUTF(args[3]+" ataco a "+refPantalla.getTitle()+" con un volcan\nGenerado en "+randomX+","+randomY);
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
//    attack/Nemo2/waves/2/Kevin/19/12
//addwarrior/Nemo/50/VOLCANOES/50/50/50/Aquaman.png
    @Override
    public void execute2(String[] args) {
        if(args != null && refPantalla.iniciada && args.length == 7){
            if(args.length == 7 && CommandUtil.isInteger(args[3]) && CommandUtil.isInteger(args[4])){               
                int X = Integer.valueOf(args[3]);
                int Y = Integer.valueOf(args[4]);
                if(X<20 && X>=0 && Y>=0 && Y<30){
                    if(refPantalla.juego.poblacion[X][Y].desastre != null && refPantalla.juego.poblacion[X][Y].desastre.isOrigen() && "V".equals(refPantalla.juego.poblacion[X][Y].desastre.getNom())){
                        int lado = 1+(refPantalla.juego.poblacion[X][Y].desastre.getRadio()*2);
                        int casillas = lado*lado*10;
                        Tipos.Tipo tipoEnviar = null;
                        for (int i = 0; i < casillas; i++) {
                            int randomX = (new Random()).nextInt(20);
                            int randomY = (new Random()).nextInt(30);
                            refPantalla.juego.poblacion[randomX][randomY].addMensaje("Fue atacada por la explosion volcan de "+args[5]);
                            refPantalla.juego.poblacion[randomX][randomY].hurt(20, Integer.valueOf(args[6]));
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
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" con una explosion volcan\nAfecto en "+casillas+" casillas");
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
        if(args != null && refPantalla.iniciada && args.length == 7){
            if(args.length == 7 && CommandUtil.isInteger(args[3]) && CommandUtil.isInteger(args[4])){
                int X = Integer.valueOf(args[3]);
                int Y = Integer.valueOf(args[4]);
                if(X<20 && X>=0 && Y>=0 && Y<30){
                    if(refPantalla.juego.poblacion[X][Y].desastre != null && refPantalla.juego.poblacion[X][Y].desastre.isOrigen() && "V".equals(refPantalla.juego.poblacion[X][Y].desastre.getNom())){
                        int radio = refPantalla.juego.poblacion[X][Y].desastre.getRadio();
                        int radioC = radio+5;
                        int daño = radio*((new Random()).nextInt(2)+5);
                        Tipos.Tipo tipoEnviar = null;
                        for (int j = 0; j <= radioC; j++){
                            for (int k = 0; k <= radioC; k++){
                                if(X+j<20){
                                    if(Y+k < 30){
                                        refPantalla.juego.poblacion[X+j][Y+k].hurt(daño, Integer.valueOf(args[6]));
                                        //refPantalla.juego.poblacion[X+j][Y+k].vida -= daño;
                                        if(refPantalla.juego.poblacion[X+j][Y+k].vida <= 0){
                                            
                                            tipoEnviar=refPantalla.juego.poblacion[X+j][Y+k].morir(refPantalla.juego.unidades);
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
                                    if(Y-k >= 0){
                                        refPantalla.juego.poblacion[X+j][Y-k].hurt(daño, Integer.valueOf(args[6]));
                                        //refPantalla.juego.poblacion[X+j][Y-k].vida -= daño;
                                        if(refPantalla.juego.poblacion[X+j][Y-k].vida <= 0){
                                            
                                            tipoEnviar=refPantalla.juego.poblacion[X+j][Y-k].morir(refPantalla.juego.unidades);
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
                                }
                                if(X-j >= 0){
                                    if(Y+k < 30){
                                        refPantalla.juego.poblacion[X-j][Y+k].hurt(daño, Integer.valueOf(args[6]));
//                                        refPantalla.juego.poblacion[X-j][Y+k].vida -= daño;
                                        if(refPantalla.juego.poblacion[X-j][Y+k].vida <= 0){
                                            
                                            tipoEnviar=refPantalla.juego.poblacion[X-j][Y+k].morir(refPantalla.juego.unidades);
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
                                    if(Y-k >= 0){
                                        refPantalla.juego.poblacion[X-j][Y-k].hurt(daño, Integer.valueOf(args[6]));
                                        //refPantalla.juego.poblacion[X-j][Y-k].vida -= daño;
                                        if(refPantalla.juego.poblacion[X-j][Y-k].vida <= 0){
                                            
                                            tipoEnviar=refPantalla.juego.poblacion[X-j][Y-k].morir(refPantalla.juego.unidades);
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
                                }
                            }
                        }
                        try {
                            refPantalla.refCliente.hiloCliente.writer.writeInt(13);
                            refPantalla.refCliente.hiloCliente.writer.writeUTF(args[5]+" ataco a "+refPantalla.getTitle()+" con un calentamiento del volcan");
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
}

//addwarrior/Nemo/50/VOLCANOES/50/50/50/Aquaman.png
//addwarrior/Nemo2/20/FISH/75/75/75/Aquaman.png
//addwarrior/Nemo3/30/VOLCANOES/100/100/100/Aquaman.png
