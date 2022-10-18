package Models;

public class Marca {

	String codigoMarca;
	String descricaoMarca;

	public Marca(String codigoMarca, String descricaoMarca) {
		super();
		this.codigoMarca = codigoMarca;
		this.descricaoMarca = descricaoMarca;
	}

	public Marca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getDescricaoMarca() {
		return descricaoMarca;
	}

	public void setDescricaoMarca(String descricaoMarca) {
		this.descricaoMarca = descricaoMarca;
	}

}
