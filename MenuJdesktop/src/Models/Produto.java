package Models;

public class Produto {
	String cod_produto;
	String nome_produto;
	String quantidade_produto;
	String valor_compra_produto;
	String valor_venda_produto;
	String descricao_produto;
	String cod_marca_produto;
	String nome_marca_produto;
	
	public Produto(String cod_produto, String nome_produto, String quantidade_produto, String valor_compra_produto,
			String valor_venda_produto, String descricao_produto, String cod_marca_pedido, String nome_marca_produto) {
		super();
		this.cod_produto = cod_produto;
		this.nome_produto = nome_produto;
		this.quantidade_produto = quantidade_produto;
		this.valor_compra_produto = valor_compra_produto;
		this.valor_venda_produto = valor_venda_produto;
		this.descricao_produto = descricao_produto;
		this.cod_marca_produto = cod_marca_pedido;
		this.nome_marca_produto = nome_marca_produto;
	}

	public Produto() {
	}

	public String getCod_produto() {
		return cod_produto;
	}

	public void setCod_produto(String cod_produto) {
		this.cod_produto = cod_produto;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public String getQuantidade_produto() {
		return quantidade_produto;
	}

	public void setQuantidade_produto(String quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}

	public String getValor_compra_produto() {
		return valor_compra_produto;
	}

	public void setValor_compra_produto(String valor_compra_produto) {
		this.valor_compra_produto = valor_compra_produto;
	}

	public String getValor_venda_produto() {
		return valor_venda_produto;
	}

	public void setValor_venda_produto(String valor_venda_produto) {
		this.valor_venda_produto = valor_venda_produto;
	}

	public String getDescricao_produto() {
		return descricao_produto;
	}

	public void setDescricao_produto(String descricao_produto) {
		this.descricao_produto = descricao_produto;
	}

	public String getCod_marca_produto() {
		return cod_marca_produto;
	}

	public void setCod_marca_produto(String cod_marca_pedido) {
		this.cod_marca_produto = cod_marca_pedido;
	}

	public String getNome_marca_produto() {
		return nome_marca_produto;
	}

	public void setNome_marca_produto(String nome_marca_produto) {
		this.nome_marca_produto = nome_marca_produto;
	}
	

	

	
}