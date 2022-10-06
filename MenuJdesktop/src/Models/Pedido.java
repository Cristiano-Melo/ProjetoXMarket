package Models;

public class Pedido {
	String pedido;
	String orcamento;
	String cpf_cliente;
	String valorTotal;
	String qtdItens;
	String quantidade_produto;
	String data;
	String descricao_produto;
	
	public Pedido(String pedido, String orcamento, String cpf_cliente, String valorTotal, String qtdItens,
			String quantidade_produto, String data, String descricao_produto) {
		super();
		
		this.cpf_cliente = cpf_cliente;
		this.valorTotal = valorTotal;
		this.qtdItens = qtdItens;
		this.quantidade_produto = quantidade_produto;
		this.data = data;
		this.descricao_produto = descricao_produto;
	}

	
	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(String qtdItens) {
		this.qtdItens = qtdItens;
	}

	public String getQuantidade_produto() {
		return quantidade_produto;
	}

	public void setQuantidade_produto(String quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao_produto() {
		return descricao_produto;
	}

	public void setDescricao_produto(String descricao_produto) {
		this.descricao_produto = descricao_produto;
	}
	
	
	
}
