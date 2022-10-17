package Guis;

public class ValidaEntrada {

	public static boolean isNumero(String frase) {

		for (int i = 0; i < frase.length(); i++) {
			char c = frase.charAt(i);
			if (!Character.isDigit(c)) {
				return (false);
			}
		}
		return (true);
	}

	public static boolean CPFValido(String frase) {
		int soma=0;
		int dv1=0;
		int dv2=0;
		int resto=0;
		int contador=0;
		
		String base = frase.substring(0, 9);
		contador=10;
		String[] cpf=base.split("");
		for (int i=0; i<9;i++) {
			int digito=Integer.parseInt(cpf[i]);
			soma +=  (digito * contador);
			contador--;
		}
		
		resto = (soma%11);
		dv1=(11-resto);
		if(dv1>9) {
			dv1=0;
		}
		base += String.valueOf(dv1);
		soma=0;
		contador=11;
		cpf=base.split("");
		for (int i=0; i<10;i++) {
			int digito=Integer.parseInt(cpf[i]);
			soma +=  (digito * contador);
			contador--;
		}
		
		resto = (soma%11);
		dv2=(11-resto);
		if(dv2>9) {
			dv2=0;
		}
		base += String.valueOf(dv2);

		if(base.equals(frase)) {
			return(true);
		}
		return(false);
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
