/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

/**
 *
 * @author kevin
 */
public class Tipos {
    public enum Tipo{
        THUNDERS("Thunders under the sea"), FISH("Fish telepathy"), KRAKEN("Release the Kraken"), WAVES("Waves control"), TRIDENT("The Trident"), VOLCANOES("Undersea volcanoes");
        
        private String nombreTipo;
	
	private Tipo (String nombreTipo){
		this.nombreTipo = nombreTipo;
	}

	public String getNombre() {
		return nombreTipo;
	}
    }
    
    public static Tipo getTipo(String tipo){
        if(null == tipo){
            return null;
        }
        else switch (tipo) {
            case "THUNDERS":
                return Tipo.THUNDERS;
            case "FISH":
                return Tipo.FISH;
            case "KRAKEN":
                return Tipo.KRAKEN;
            case "WAVES":
                return Tipo.WAVES;
            case "TRIDENT":
                return Tipo.TRIDENT;
            case "VOLCANOES":
                return Tipo.VOLCANOES;
            default:
                return null;
        }
    }
    public static String getString(Tipo tipo){
        if(null == tipo){
            return null;
        }
        else switch (tipo) {
            case THUNDERS:
                return "THUNDERS";
            case FISH:
                return "FISH";
            case KRAKEN:
                return "KRAKEN";
            case WAVES:
                return "WAVES";
            case TRIDENT:
                return "TRIDENT";
            case VOLCANOES:
                return "VOLCANOES";
            default:
                return null;
        }
    }
}
