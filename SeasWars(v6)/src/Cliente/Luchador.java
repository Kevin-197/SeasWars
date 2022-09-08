/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Commands.Tipos.Tipo;
import Strategies.IStrategy;
import Strategies.StrategyManager;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;

/**
 *
 * @author eidur
 */
public class Luchador{
    private String name;
    private Tipo tipo;
    private int percentage;
    private int sanity;
    private int power;
    private int resistance;
    private StrategyManager strategies;
    public ArrayList<Casilla> poblacionLuchador = new ArrayList<Casilla>(); 
    public boolean damageBuff = false;
    public boolean vivo;
            
    public Luchador(String name, int percentage,Tipo tipo, int sanity, int power, int stamina) {
        this.percentage = percentage;
        this.sanity = sanity;
        this.power = power;
        this.resistance = stamina;
        this.name = name;
        this.tipo = tipo;
        this.vivo = true;
        this.strategies = new StrategyManager(this.tipo);
    }

    public int getPercentage() {
        return percentage;
    }

    public int getSanity() {
        return sanity;
    }

    public int getPower() {
        return power;
    }

    public int getResistance() {
        return resistance;
    }

    String getName() {
        return name;
    }

    public Tipo getTipo() {
        return tipo;
    }

    
    
    public void startAttack(String[] parametros) {                
        if (parametros.length != 0) {        
            String commandName = ""; 
            if (parametros.length > 1) {        
                commandName = parametros[0];  
                parametros = Arrays.copyOfRange(parametros, 1, parametros.length); 
            } 
            IStrategy Strategy;   
            Strategy = strategies.getStrategy(commandName);
            if(Strategy != null){
                if(null != parametros[0])switch (parametros[0]) {
                    case "1":
                        Strategy.execute1(parametros, this.name, this.damageBuff, this.power);
                        if(this.damageBuff){
                            this.damageBuff=false;
                        }
                        break;
                    case "2":
                        Strategy.execute2(parametros, this.name, this.damageBuff, this.power);
                        if(this.damageBuff){
                            this.damageBuff=false;
                        }
                        break;
                    case "3":
                        Strategy.execute3(parametros, this.name, this.damageBuff, this.power);
                        if(this.damageBuff){
                            this.damageBuff=false;
                        }
                        break;
                    default:
                        pantallaPrincipal.getIntance().addMensajeResult("Datos de ataque invalidos");
                        break;
                }
                else{
                    pantallaPrincipal.getIntance().addMensajeResult("Datos de ataque invalidos");
                }
               
            }
            else{
                pantallaPrincipal.getIntance().addMensajeResult("Datos de ataque invalidos");
            }
        }     
         
    }
    
    public void addPoblacion(Casilla newCasilla) {
        this.poblacionLuchador.add(newCasilla);
    }
    
    public void addStrategy(Tipo tipo) {
        this.strategies.registStrategy(tipo);
    }
    
    public void sanar() {
        for(int i = 0; i<this.poblacionLuchador.size();i++){
            if(this.poblacionLuchador.get(i).vivo){
                if(this.poblacionLuchador.get(i).vida+this.sanity<=100 && this.poblacionLuchador.get(i).vivo){
                    this.poblacionLuchador.get(i).vida+=this.sanity;
                }else{
                    this.poblacionLuchador.get(i).vida =100;
                }
            }
        }
    }
    public void buff() {
        this.damageBuff=true;
    }
    
    public void protect() {
        for(int i = 0; i<poblacionLuchador.size();i++){
            this.poblacionLuchador.get(i).setResistanceBuff(true);
        }
    }

    void morir() {
       this.vivo = false;
       Juego.getIntance().checkVivos();
    }
    
}
