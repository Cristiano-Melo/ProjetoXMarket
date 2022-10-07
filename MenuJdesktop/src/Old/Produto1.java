package Models;

public class Produto {
	String nome_produto;
	String quantidade_produto;
	String valor_compra_produto;
	String valor_venda_produto;
	String descricao_produto;
	String cod_marca_pedido;

	public Produto(String nome_produto, String quantidade_produto, String valor_compra_produto,
			String valor_venda_produto, String descricao_produto, String cod_marca_pedido) {
		super();
		this.nome_produto = nome_produto;
		this.quantidade_produto = quantidade_produto;
		this.valor_compra_produto = valor_compra_produto;
		this.valor_venda_produto = valor_venda_produto;
		this.descricao_produto = descricao_produto;
		this.cod_marca_pedido = cod_marca_pedido;
	}

	public Produto() {

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

	public String getCod_marca_pedidoString() {
		return cod_marca_pedido;
	}

	public void setCod_marca_pedidoString(String cod_marca_pedido) {
		this.cod_marca_pedido = cod_marca_pedido;
	}

	

}