package Guis;

public class ValidaEntrada {

	public static boolean temLetra(String frase) {

		for (int i = 0; i < frase.length(); i++) {
			char c = frase.charAt(i);
			if (Character.isLetter(c)) {
				return (true);
			}
		}
		return (false);
	}
	
	public static boolean isFloat(String valor) {
		try {
			Float.parseFloat(valor);
			return(true);
		}catch(Exception e){
			return(false);
		}
	}
	
	public static boolean isInt(String valor) {
		try {
			Integer.parseInt(valor);
			return(true);
		}catch(Exception e){
			return(false);
		}
	}

}
