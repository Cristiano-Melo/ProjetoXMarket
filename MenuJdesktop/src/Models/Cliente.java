 package Models;

public class Cliente {
	private String cod_cliente;

	private String nome_cliente;
	private String cpf_cliente;
	private String rg_cliente;
	private String email_cliente;
	private String telefone_cliente;
	private String endereco_cliente;
	private String bairro_cliente;
	private String cidade_cliente;
	private String uf_cliente;
	private String cep_cliente;
	
	public Cliente(String nome_cliente, String cpf_cliente, String rg_cliente, String email_cliente,
			String telefone_cliente, String endereco_cliente, String bairro_cliente, String cidade_cliente,
			String uf_cliente, String cep_cliente) {
		super();
		this.nome_cliente = nome_cliente;
		this.cpf_cliente = cpf_cliente;
		this.rg_cliente = rg_cliente;
		this.email_cliente = email_cliente;
		this.telefone_cliente = telefone_cliente;
		this.endereco_cliente = endereco_cliente;
		this.bairro_cliente = bairro_cliente;
		this.cidade_cliente = cidade_cliente;
		this.uf_cliente = uf_cliente;
		this.cep_cliente = cep_cliente;
	}
	public Cliente() {
		
	}

	public String getCod_cliente() {
		return cod_cliente;
	}
	
	public void setCod_cliente(String cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	
	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

	public String getRg_cliente() {
		return rg_cliente;
	}

	public void setRg_cliente(String rg_cliente) {
		this.rg_cliente = rg_cliente;
	}

	public String getEmail_cliente() {
		return email_cliente;
	}

	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}

	public String getTelefone_cliente() {
		return telefone_cliente;
	}

	public void setTelefone_cliente(String telefone_cliente) {
		this.telefone_cliente = telefone_cliente;
	}

	public String getEndereco_cliente() {
		return endereco_cliente;
	}

	public void setEndereco_cliente(String endereco_cliente) {
		this.endereco_cliente = endereco_cliente;
	}

	public String getBairro_cliente() {
		return bairro_cliente;
	}

	public void setBairro_cliente(String bairro_cliente) {
		this.bairro_cliente = bairro_cliente;
	}

	public String getCidade_cliente() {
		return cidade_cliente;
	}

	public void setCidade_cliente(String cidade_cliente) {
		this.cidade_cliente = cidade_cliente;
	}

	public String getUf_cliente() {
		return uf_cliente;
	}

	public void setUf_cliente(String uf_cliente) {
		this.uf_cliente = uf_cliente;
	}

	public String getCep_cliente() {
		return cep_cliente;
	}

	public void setCep_cliente(String cep_cliente) {
		this.cep_cliente = cep_cliente;
	}

}
