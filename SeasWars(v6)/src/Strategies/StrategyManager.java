/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import Commands.Tipos.Tipo;
import java.util.HashMap;

/**
 *
 * @author kevin
 */
public class StrategyManager {   
    private final HashMap<String, Class<? extends IStrategy>>    STRATEGIES =          
            new HashMap<String, Class<? extends IStrategy>>();       
    
    public StrategyManager(Tipo tipo) {
        registStrategy(tipo);
    }          
    
    public IStrategy getStrategy(String commandName) {           
        if (STRATEGIES.containsKey(commandName.toUpperCase())) {               
            try {                   
                return STRATEGIES.get(commandName.toUpperCase()).newInstance();
            } catch (Exception e) {   e.printStackTrace();                   
            return null;   
            }           
        } 
        else {
            return null;   
        }   
    }       
    
    public void registStrategy(Tipo tipo) {   
        switch (tipo) {
            case THUNDERS:
                STRATEGIES.put(ThundersStrategy.STRATEGY_NAME.toUpperCase(), ThundersStrategy.class); 
                break;
            case FISH: 
                STRATEGIES.put(FishStrategy.STRATEGY_NAME.toUpperCase(), FishStrategy.class); 
                break;
            case KRAKEN:
                STRATEGIES.put(KrakenStrategy.STRATEGY_NAME.toUpperCase(), KrakenStrategy.class);  
                break;
            case WAVES:
                STRATEGIES.put(WavesStrategy.STRATEGY_NAME.toUpperCase(), WavesStrategy.class); 
                break;
            case TRIDENT:
                STRATEGIES.put(TridentStrategy.STRATEGY_NAME.toUpperCase(), TridentStrategy.class);  
                break;
            case VOLCANOES:
                STRATEGIES.put(VolcanoesStrategy.STRATEGY_NAME.toUpperCase(), VolcanoesStrategy.class); 
                break;
            default:
                break;
        }  
    }
}
