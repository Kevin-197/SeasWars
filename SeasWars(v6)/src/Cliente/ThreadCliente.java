/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */

public class ThreadCliente extends Thread{
    
    private Socket socketRef;
    public DataInputStream reader;
    public DataOutputStream writer;
    private String nombre;
    private boolean running = true;
    private pantallaPrincipal refPantalla;

    public ThreadCliente(Socket socketRef, pantallaPrincipal refPantalla) throws IOException {
        this.socketRef = socketRef;
        this.reader = new DataInputStream(socketRef.getInputStream());
        this.writer = new DataOutputStream(socketRef.getOutputStream());
        this.refPantalla = refPantalla;
    }
    
    public void run (){
        int instruccionId = 1;
        int jugadorDa;
        String Ataque;
        while (running){
            try {
                String usuario = "";
                instruccionId = reader.readInt(); // esperar hasta que reciba un entero
                
                switch (instruccionId){
                    case 1: // recibe el turno del jufador 1
                        refPantalla.setNombreTurno(reader.readUTF());
                    break;
                    case 2: // pasan un mensaje por el chat
                          usuario = reader.readUTF();
                          String mensaje = reader.readUTF();
                          refPantalla.addMensaje(usuario+">   " + mensaje);
                    break;                   
                    case 3: // pinta que inicio partida
                        refPantalla.setInicioPartida(reader.readInt());
                    break;
                    case 4: // pinta titulo
                        refPantalla.setTitle(reader.readUTF());
                    break;
                    case 5: // setIndex
                        refPantalla.setIndexFi(reader.readInt());
                    break;
                    case 6: // pasan un mensaje por la bitacora
                          String mensajeB = reader.readUTF();
                          refPantalla.addMensajeBitacora(mensajeB);
                    break; 
                    case 7: // jugadores conectados
                        int idx = reader.readInt();
                        String owner = reader.readUTF();
                        refPantalla.setJugadores(idx, owner);
                        
                    break;
                    case 8: // manda info de este jugador
                        int idxR = reader.readInt();
                        String respuesta = refPantalla.consultPlayer();
                        this.writer.writeInt(10);
                        this.writer.writeInt(idxR);
                        this.writer.writeUTF(respuesta);
                        
                    break;
                    case 9: // recibe info de otro jugador
                        String res = reader.readUTF();
                        refPantalla.addMensajeBitacora(res);
                        
                    break;
                    case 10: // recibe el ganador
                        refPantalla.setNombreGanador(reader.readUTF());
                    break;
                    case 11:
                        Ataque = reader.readUTF();
                        refPantalla.juego.ReceivetAttack(Ataque);
                    break;
                    case 12:
                        Ataque = reader.readUTF();
                        refPantalla.juego.receiveStrategy(Ataque);
                    break;
                    case 13: // pasan un mensaje por result
                          String mensajeR = reader.readUTF();
                          refPantalla.addMensajeResult(mensajeR);
                    break;
                }
            } catch (IOException ex) {
                
            }
        }
    }

}
