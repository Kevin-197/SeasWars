/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Cliente.pantallaPrincipal;



public abstract class BaseCommand implements ICommand {       
    public static pantallaPrincipal refPantalla = pantallaPrincipal.getIntance();
    @Override       
    public abstract String getCommandName();       
    
    @Override       
    public abstract void execute(String[] args);   
}

