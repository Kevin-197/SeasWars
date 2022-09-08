/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;


       
public interface IStrategy {       
    public String getStrategyName();       
    public void execute1(String[] args, String Warrior, boolean damageBuff, int power);
    public void execute2(String[] args, String Warrior, boolean damageBuff, int power);
    public void execute3(String[] args, String Warrior, boolean damageBuff, int power); 
}
