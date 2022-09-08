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
public class StrategyManagerReceive {   
    private final HashMap<String, Class<? extends RStrategy>>    STRATEGIES =          
            new HashMap<String, Class<? extends RStrategy>>();       
    
    public StrategyManagerReceive() {
        
        STRATEGIES.put(ThundersStrategyR.STRATEGY_NAME.toUpperCase(), ThundersStrategyR.class); 
        STRATEGIES.put(FishStrategyR.STRATEGY_NAME.toUpperCase(), FishStrategyR.class); 
        STRATEGIES.put(KrakenStrategyR.STRATEGY_NAME.toUpperCase(), KrakenStrategyR.class);  
        STRATEGIES.put(WavesStrategyR.STRATEGY_NAME.toUpperCase(), WavesStrategyR.class); 
        STRATEGIES.put(TridentStrategyR.STRATEGY_NAME.toUpperCase(), TridentStrategyR.class);  
        STRATEGIES.put(VolcanoesStrategyR.STRATEGY_NAME.toUpperCase(), VolcanoesStrategyR.class); 
    }          
    
    public RStrategy getStrategy(String commandName) {           
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
}
