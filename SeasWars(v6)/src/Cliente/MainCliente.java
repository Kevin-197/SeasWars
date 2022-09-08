/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

/**
 *
 * @author eidur
 */
public class MainCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try{
        pantallaPrincipal pantalla = pantallaPrincipal.getIntance();
        Cliente c = new Cliente(pantalla);
        pantalla.setVisible(true); 
        c.conectar();
               
        }
        catch(Exception e){
            
        }
    }
    
}
