/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Commands.Tipos.Tipo;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author eidur
 */
public class Casilla {
    public int vida=100;
    int defensa=100;
    public boolean radiactivo; 
    String bitacora ="Bitacora:\n";
    public Disaster desastre;
    public javax.swing.JLabel  refLabel;
    private int resistance;
    public boolean resistanceBuff = false;
    public boolean vivo = true;

    public Casilla(JLabel refLabel, int vida, int resistance) {
        this.refLabel = refLabel;
        this.vida = vida;
        this.desastre = null;
        this.radiactivo = false;
        this.resistance = resistance;
    }
    
    public String toString(){
        return "Vida: "+this.vida+"\n"+this.bitacora;
    }

    public JLabel getRefLabel() {
        return refLabel;
    }
    
    public Tipo morir(ArrayList<Luchador> Unidades){
        this.vivo = false;
        this.vida = 0;
        this.refLabel.setText("X");
        Luchador tipoLuchador =null;
        boolean tipoEncontrado=false; 
        for(int i=0; i<Unidades.size(); i++){
            
            for(int j=0; j<Unidades.get(i).poblacionLuchador.size(); j++){
                if(Unidades.get(i).poblacionLuchador.get(j) == this){
                    tipoEncontrado = true;
                    break;
                }
            }
            if(tipoEncontrado){
                    tipoLuchador = Unidades.get(i);
                    break;
            }
        }
        tipoEncontrado  = false;
        for(int i=0; i<tipoLuchador.poblacionLuchador.size(); i++){
            if(tipoLuchador.poblacionLuchador.get(i).vivo){
                tipoEncontrado = true;
                break;
            }
        }
        if(tipoEncontrado){
            return null;
        }else{
            tipoLuchador.morir();
            return tipoLuchador.getTipo();
        }
    }

    public void setRadioactivo() {
        this.radiactivo = true;
        this.refLabel.setBackground(Color.GREEN);
    }

    public void setResistanceBuff(boolean resistanceBuff) {
        this.resistanceBuff = resistanceBuff;
    }

    public int getResistance() {
        return resistance;
    }

    public boolean isResistanceBuff() {
        return resistanceBuff;
    }
    public void hurt(int danio, int potenciador) {
        int damage = (int) (danio+(danio*(potenciador*0.01)));
        if(isResistanceBuff() && vida !=100){
            this.vida-=damage-(damage*(this.getResistance()*0.01));
            this.resistanceBuff=false;
        }else{
            this.vida-=damage;
        }
    }

    public void addMensaje(String mensaje) {
        this.bitacora += mensaje + "\n";
    }
    
    
    
    
     
}
