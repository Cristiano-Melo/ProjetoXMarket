package Guis;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FormataDecimal {

	public static String duasCasas(String numero) {

		DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
		formatoDecimal.setRoundingMode(RoundingMode.UP);

		double valorRetorno = Double.parseDouble(numero);

		return (formatoDecimal.format(valorRetorno));
	}

}
