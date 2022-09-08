/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import Cliente.pantallaPrincipal;

/**
 *
 * @author kevin
 */
public abstract class BaseStrategyReceive implements RStrategy {       
    public static pantallaPrincipal refPantalla = pantallaPrincipal.getIntance();
    @Override       
    public abstract String getStrategyName();       
    
    @Override       
    public abstract void execute1(String[] args);  
    public abstract void execute2(String[] args);
    public abstract void execute3(String[] args);
}