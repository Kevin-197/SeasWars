/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import Cliente.pantallaPrincipal;



public abstract class BaseStrategy implements IStrategy {       
    public static pantallaPrincipal refPantalla = pantallaPrincipal.getIntance();
    @Override       
    public abstract String getStrategyName();       
    
    @Override       
    public abstract void execute1(String[] args, String Warrior, boolean damageBuff, int power);   
    
    @Override
    public abstract void execute2(String[] args, String Warrior, boolean damageBuff, int power);  
    
    @Override
    public abstract void execute3(String[] args, String Warrior, boolean damageBuff, int power);  
}

