/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */


class ThreadServidor extends Thread{
    
    private Socket socketRef;
    private DataInputStream reader;
    private DataOutputStream writer;
    public String nombre;
    private int orden = 0;
    private boolean derrotado;
    private boolean running = true;
    Server server;

    public ThreadServidor(Socket socketRef, Server server) throws IOException {
        this.socketRef = socketRef;
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        this.server = server;
    }
    
    public void enviarTurnoInicial() throws IOException{
        writer.writeInt(1);
        writer.writeUTF(server.getTurno());
    }
    
    private void enviarNombre() throws IOException {
        writer.writeInt(4);
        writer.writeUTF(this.nombre);
    }

    public int getOrden() {
        return orden;
    }
    
    public void enviarJugadoresConectados() throws IOException{
        
        for (int i = 0; i < server.conexiones.size(); i++) {
            String currentStr = server.conexiones.get(i).nombre;

            writer.writeInt(7);
            writer.writeInt(i);
            writer.writeUTF(currentStr);
            
        }
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
  
    
    public void run (){
        
        int instruccionId = 1;
        int jugadorDa;
        int jugadorRecibe;
        ThreadServidor conexion;
        String Ataque;
        while (running){
            try {
                instruccionId = reader.readInt(); // esperar hasta que reciba un entero
                
                switch (instruccionId){
                    case 1: // pasan el nombre del usuario
                        nombre = " ";
                        String n = reader.readUTF();
                        nombre = server.verificarNombre(n,n,1);
                        enviarNombre();   
                        
                        
                    break;
                    case 2: // pasan un mensaje por el chat
                        String mensaje = reader.readUTF();
                        
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(2);
                            current.writer.writeUTF(nombre);
                            current.writer.writeUTF(mensaje);
                            
                        }
                    break;
                    
                    case 3: // iniciar partida
                        if(server.iniciarPartida()){
                            String nextor = server.getNextTurno();
                            for (int i = 0; i < server.conexiones.size(); i++) {
                                ThreadServidor current = server.conexiones.get(i);
                                current.writer.writeInt(3);
                                current.writer.writeInt(1);
                                current.writer.writeInt(1);
                                current.writer.writeUTF(nextor);
                                current.enviarJugadoresConectados();
                            }
                        }
                        else{
                            for (int i = 0; i < server.conexiones.size(); i++) {
                                ThreadServidor current = server.conexiones.get(i);
                                current.writer.writeInt(3);
                                current.writer.writeInt(2);
                            }
                        }
                        
                        
                    break;
                    case 4: //cambia turno
                        String next = server.getNextTurno();
                        if(server.derrotados.size() == server.conexiones.size()-1){
                            for (int i = 0; i < server.conexiones.size(); i++) {
                                ThreadServidor current = server.conexiones.get(i);
                                current.writer.writeInt(10);
                                current.writer.writeUTF(next);
                            }
                        }
                        else{
                            for (int i = 0; i < server.conexiones.size(); i++) {
                                ThreadServidor current = server.conexiones.get(i);
                                current.writer.writeInt(1);
                                current.writer.writeUTF(next);
                            }
                        }
                        
                    break; 
                    case 5: // mensajes server
                        String mensajeServer = reader.readUTF();
                        
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(2);
                            current.writer.writeUTF("Server");
                            current.writer.writeUTF(mensajeServer);
                        }
                    break;
                    case 6: // listo para 
                        if(this.getOrden() == 0){
                            this.setOrden((new Random()).nextInt(6)+1);
                            for (int i = 0; i < server.conexiones.size(); i++) {
                                if(server.conexiones.get(i) == this){
                                    this.writer.writeInt(5);
                                    this.writer.writeInt(i);
                                }
                            }
                            
                            for (int i = 0; i < server.conexiones.size(); i++) {
                                ThreadServidor current = server.conexiones.get(i);
                                current.writer.writeInt(2);
                                current.writer.writeUTF("Server");
                                current.writer.writeUTF(this.nombre+" ya esta listo!");
                            }
                            
                        }
                        else{
                            this.writer.writeInt(2);
                            this.writer.writeUTF("Server");
                            this.writer.writeUTF("Ya estas listo");
                        }
                    break;
                    case 7: // mensaje privado
                        String destinatario = reader.readUTF();
                        String mensajePrivado = reader.readUTF();
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            if(current.nombre.equals(destinatario)){
                                current.writer.writeInt(2);
                                current.writer.writeUTF(this.nombre);
                                current.writer.writeUTF(mensajePrivado);
                            }
                        }
                    break;
                    case 8: // mensaje privado
                        if(derrotado == false){
                            server.derrotados.add(this);
                            derrotado = true;
                            this.setOrden(-1);
                            for (int i = 0; i < server.conexiones.size(); i++) {
                                ThreadServidor current = server.conexiones.get(i);
                                current.writer.writeInt(6);
                                current.writer.writeUTF(this.nombre+ " ha sido derrotado");
                                
                            }
                        }
                    break;
                    case 9: // info de otro jugador
                        jugadorRecibe = reader.readInt();
                        jugadorDa = reader.readInt();
                        conexion = server.conexiones.get(jugadorRecibe);
                        conexion.writer.writeInt(8);
                        conexion.writer.writeInt(jugadorDa);
                    break;
                    case 10: // respuesta info de otro jugador
                        jugadorDa = reader.readInt();
                        String respuesta = reader.readUTF();
                        conexion = server.conexiones.get(jugadorDa);
                        conexion.writer.writeInt(9);
                        conexion.writer.writeUTF(respuesta);
                    break;
                    case 11: // ataque de otro jugador
                        Ataque = reader.readUTF();
                        jugadorRecibe = reader.readInt();
                        conexion = server.conexiones.get(jugadorRecibe);
                        conexion.writer.writeInt(11);
                        conexion.writer.writeUTF(Ataque);
                    break;
                    case 12: // transferencia de poder
                        Ataque = reader.readUTF();
                        jugadorRecibe = reader.readInt();
                        conexion = server.conexiones.get(jugadorRecibe);
                        conexion.writer.writeInt(12);
                        conexion.writer.writeUTF(Ataque);
                    break;
                    case 13: 
                        String mensajeBitacora = reader.readUTF();
                        
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(6);
                            current.writer.writeUTF(mensajeBitacora);

                        }
                    break;
                    case 14: 
                        String mensajeResult = reader.readUTF();
                        
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(13);
                            current.writer.writeUTF(mensajeResult);

                        }
                    break;
                   
                }
            } catch (IOException ex) {
                
            }
        }
    }

    
}