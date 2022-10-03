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

}
