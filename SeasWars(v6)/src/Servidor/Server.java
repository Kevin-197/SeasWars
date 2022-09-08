/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.Juego;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/**
 *
 * @author kevin
 */

public class Server{
    
    PantallaServidor refPantalla;
    public ArrayList<ThreadServidor> conexiones;
    public ArrayList<ThreadServidor> derrotados;
    private boolean running = true;
    private ServerSocket srv;
    public int turno = 0;
    private boolean partidaIniciada = false;

    public Server(PantallaServidor refPantalla) {
        this.refPantalla = refPantalla;
        conexiones = new ArrayList<ThreadServidor>();
        derrotados = new ArrayList<ThreadServidor>();
        this.refPantalla.setSrv(this);
    }

    
    
    public boolean iniciarPartida() {
        if(conexiones.size() < 2){
            return false;
        }
        else{
            for (int i = 0; i < conexiones.size(); i++) {
                ThreadServidor current = conexiones.get(i);
                if(current.getOrden() == 0){
                    return false;
                }
            }
            this.OrdenarTurnos();
            this.turno = -1;
            this.partidaIniciada = true;
            return true;
        }
    }
    
    private void OrdenarTurnos(){
        boolean insert = false;
        ArrayList<ThreadServidor> ordenados = new ArrayList<ThreadServidor>();
        for (int i = 0; i < conexiones.size(); i++) {
            ThreadServidor current = conexiones.get(i);
            if(ordenados.isEmpty()){
                ordenados.add(current);
            }
            else{
                for(int j = 0; j < ordenados.size(); j++) {
                    ThreadServidor currentor = ordenados.get(j);
                    if(currentor.getOrden() < current.getOrden()){
                        ordenados.add(j, current);
                        insert = true;
                        break;
                    }
                }
                if(!insert){
                    ordenados.add(current);
                }
                insert = false;
            }
        }
        //System.out.println("ordenados; "+ordenados.size());
        conexiones = ordenados;
    }
    
    public void stopserver(){
        running = false;
    }
    
    public String getNextTurno(){
        //System.out.println(conexiones.size());
        if ( ++turno >= conexiones.size())
            turno = 0;
        
        for (int i = 0; i < derrotados.size(); i++){
            if(conexiones.get(turno) == derrotados.get(i)){
                return this.getNextTurno();
            }
        }
        return conexiones.get(turno).nombre;
    }
    
    public String getTurno(){
        return conexiones.get(turno).nombre;
    }
    
    public String verificarNombre(String name,String n, int cont){
        for (int i = 0; i < conexiones.size(); i++) {
            ThreadServidor current = conexiones.get(i);
            if(current.nombre.equals(name)){
                return verificarNombre(n+"("+cont+")",n ,++cont);
            }
        }
        return name;
    }
    
    public void runServer(){
        int contadorDeConexiones = 0;
        try{
            srv = new ServerSocket(35567);
            while (running){
                refPantalla.addMessage("::Esperando conexión ...");
                Socket nuevaConexion = srv.accept();
                if (!partidaIniciada){ 
                    if (contadorDeConexiones < 6){
                        contadorDeConexiones++;
                        refPantalla.addMessage(":Conexión de jugador " + contadorDeConexiones + " aceptada");

                        // nuevo thread
                        ThreadServidor newThread = new ThreadServidor(nuevaConexion, this);
                        conexiones.add(newThread);
                        newThread.start();
                    }
                    else{
                        refPantalla.addMessage(":Conexión denegada: sala llena");
                    }
                }
                else{
                    // OutputStream socket para poder hacer un writer
                    refPantalla.addMessage(":Conexión denegada: partida iniciada");
                }
                
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
}
