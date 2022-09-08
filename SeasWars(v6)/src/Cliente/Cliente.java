/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.net.Socket;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author kevin
 */


public class Cliente {
    
    Socket socketRef;
    pantallaPrincipal refPantalla;
    public ThreadCliente hiloCliente;

    public Cliente(pantallaPrincipal refPantalla) {
        this.refPantalla = refPantalla;
        refPantalla.setRefCliente(this);
    }
    
    public void conectar(){
 
        try{
        
            socketRef = new Socket("localhost", 35567);
            try{
                hiloCliente = new ThreadCliente(socketRef, refPantalla);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            
            hiloCliente.start();
            String nombre = JOptionPane.showInputDialog("Introduzca un Nick:");
            if(nombre == null || nombre == ""){
                nombre = "guest"+(new Random()).nextInt(1000000);
            }
            refPantalla.setTitle(nombre);
            hiloCliente.writer.writeInt(1); //instruccion para el switch del thraed servidor
            hiloCliente.writer.writeUTF(nombre); //instruccion para el switch del thraed servidor
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        
    }

    
}
