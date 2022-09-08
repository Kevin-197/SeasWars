/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import javax.swing.JLabel;

/**
 *
 * @author kevin
 */
public class Jugador {
    int vida = 0;
    int index;
    String nombre;

    Jugador(String nombre, int idx) {
        this.nombre = nombre;
        this.index = idx;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
