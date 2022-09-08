/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Commands.CommandUtil;
import Commands.Tipos;
import static Strategies.BaseStrategy.refPantalla;
import Strategies.RStrategy;
import Strategies.StrategyManagerReceive;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author eidur
 */
public class Juego {
    public ArrayList<Jugador> jugadores;
    private static Juego juego;
    public boolean KrakenProtec;
    int indexJuego;
    String nombre;
    StrategyManagerReceive strategiesR;
    public ArrayList<Luchador> unidades = new ArrayList<Luchador>();
    public Casilla[][] poblacion = new Casilla[20][30]; 
    private Juego() {
        this.jugadores = new ArrayList<Jugador>();
        this.KrakenProtec = false;
        this.strategiesR = new StrategyManagerReceive();
    }

    public static synchronized Juego getIntance() {           
        if (juego == null) {               
            juego = new Juego();   
        }
        return juego;   
    } 

    void addPlayer(String nombre, int idx) {
        for (int i = 0; i < jugadores.size(); i++) {
            if(jugadores.get(i).nombre.equals(nombre)){
                return;
            }
        }
        jugadores.add(new Jugador(nombre,idx));
    }
    
    public void addPoblacion(JLabel refLabel, int vida, int fila, int columna, int resistance) {
        this.poblacion[fila][columna] = new Casilla(refLabel,vida, resistance);
    }
    public void addPoblacion(Casilla casilla, int fila, int columna) {
        this.poblacion[fila][columna] = casilla;
    }
    
    void setIndex(int index) {
        this.indexJuego = index;
    }
    
    public void vidaCasillas(){
        for(int i =0; i<poblacion.length;i++){
            for(int j =0; j<poblacion[i].length;j++){
                poblacion[i][j].refLabel.setText(""+poblacion[i][j].vida);
            }
        }
    }

    void limpiarVidaCasillas() {
        for(int i =0; i<20;i++){
            for(int j =0; j<30;j++){
                if(poblacion[i][j].desastre == null){
                    if(poblacion[i][j].vivo){
                        poblacion[i][j].refLabel.setText("");
                    }
                    else{
                        poblacion[i][j].refLabel.setText("X");
                    }
                    
                }
                else{
                    poblacion[i][j].refLabel.setText(poblacion[i][j].desastre.getNom());
                }
            }
        }
    }
    
    public String consultCasilla(Integer x, Integer y) {
        String casillaStr = "Casilla ["+x+"] ["+y+"]\n";
        if(poblacion.length > x){
            if(poblacion[x].length > y){
                casillaStr += poblacion[x][y].toString();
            }
            else{
                casillaStr += "No existe";
            }
        }
        else{
            casillaStr += "No existe";
        }
        return casillaStr;
    }
    
    public String getInfo(){
        int casillasM = 0;
        int vida = 0;
        for(int i =0; i<20;i++){
            for(int j =0; j<30;j++){
                if(poblacion[i][j].vida <= 0){
                    casillasM++;
                }
                else{
                    vida += poblacion[i][j].vida;
                }
            }
        }
        vida = vida*100/60000;
        return "Vida: "+vida+"%\nCasillas Muertas: "+casillasM;
    }

    public int getIndex(String nombre) {
        int index = -1;
        for (int i = 0; i < jugadores.size(); i++) {
            if(jugadores.get(i).nombre.equals(nombre)){
                index = jugadores.get(i).index;
            }
        }
        return index;
    }


    public Luchador getWarrior(String nombre) {
        Luchador w = null;
        for (int i = 0; i < unidades.size(); i++) {
            if(unidades.get(i).getName().equals(nombre)){
                return unidades.get(i);
            }
        }
        return null;
    }

    public void activarKrakenProtect() {
        this.KrakenProtec = true;
    }
    
    
    public void sanar(String nombre) {
        for (int i = 0; i < unidades.size(); i++) {
            this.unidades.get(i).sanar();
        }
    }
    
    
    public void receiveStrategy(String parametro) {  
        if (parametro.trim().length() != 0) {
            String[] parametros = CommandUtil.tokenizerArgs(parametro);               
            String commandName = ""; 
            if (parametros.length > 1) {        
                commandName = parametros[0];
            }
            for(int i=0; i<this.unidades.size(); i++){
                if(this.unidades.get(i).getName().equals(parametros[1])){
                    this.unidades.get(i).addStrategy(Tipos.getTipo(commandName));
                    break;
                }
            }
            
        }    
    }
    public void ReceivetAttack(String parametro) {  
        if (parametro.trim().length() != 0) {
            String[] parametros = CommandUtil.tokenizerArgs(parametro);               
            String commandName = ""; 
            if (parametros.length > 2) {        
                commandName = parametros[1];
            }
            RStrategy StrategyR;   
            StrategyR = strategiesR.getStrategy(commandName); 
            if(StrategyR != null){
                if(null != parametros[2])switch (parametros[2]) {
                    case "1":
                        StrategyR.execute1(parametros);
                        break;
                    case "2":
                        StrategyR.execute2(parametros);
                        break;
                    case "3":
                        StrategyR.execute3(parametros);
                        break;
                    default:
                        pantallaPrincipal.getIntance().addMensajeResult("Datos de ataque invalidos1");
                        break;
                }
                else{
                    pantallaPrincipal.getIntance().addMensajeResult("Datos de ataque invalidos2");
                }
            }
            else{
                pantallaPrincipal.getIntance().addMensajeResult("Datos de ataque invalidos3");
            }
        }    
    }

    void checkVivos() {
        Luchador w = null;
        for (int i = 0; i < unidades.size(); i++) {
            if(unidades.get(i).vivo){
                return;
            }
        }
        try {
            pantallaPrincipal.getIntance().refCliente.hiloCliente.writer.writeInt(8);
        } catch (IOException ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
