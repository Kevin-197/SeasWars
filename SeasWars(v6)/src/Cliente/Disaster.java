/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

/**
 *
 * @author kevin
 */
public class Disaster {
    public int radio;
    public boolean origen;
    public String nom;
    public Disaster(int radio , String nom, boolean origen) {
        this.radio = radio;
        this.nom = nom;
        this.origen = origen;
    }

    public boolean isOrigen() {
        return origen;
    }
    
    public int getRadio() {
        return radio;
    }

    public String getNom() {
        return nom;
    }
    
}
