package Models;

public class ListaPedido {
	String cod_pedido;
	String data_pedido;
	String condicao_pagamento_pedido;
	String cod_cliente;
	String nome_cliente;
	String cpf_cliente;
	String rg_cliente;
	String email_cliente;
	String clientes_cod_cliente;
	String cod_produto;
	String nome_produto;
	String quantidade_item;
	String valor_venda_produto;
	Double preco_total_item;
	String tipo_pedido;
	
	
	public String getValor_venda_produto() {
		return valor_venda_produto;
	}
	public void setValor_venda_produto(String valor_venda_produto) {
		this.valor_venda_produto = valor_venda_produto;
	}
	public String getEmail_cliente() {
		return email_cliente;
	}
	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}
	public String getRg_cliente() {
		return rg_cliente;
	}
	public void setRg_cliente(String rg_cliente) {
		this.rg_cliente = rg_cliente;
	}
	public String getClientes_cod_cliente() {
		return clientes_cod_cliente;
	}
	public void setClientes_cod_cliente(String clientes_cod_cliente) {
		this.clientes_cod_cliente = clientes_cod_cliente;
	}
	public String getCpf_cliente() {
		return cpf_cliente;
	}
	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}
	public String getTipo_pedido() {
		return tipo_pedido;
	}
	public void setTipo_pedido(String tipo_pedido) {
		this.tipo_pedido = tipo_pedido;
	}
	public String getCod_pedido() {
		return cod_pedido;
	}
	public void setCod_pedido(String cod_pedido) {
		this.cod_pedido = cod_pedido;
	}
	public String getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(String data_pedido) {
		this.data_pedido = data_pedido;
	}
	public String getCondicao_pagamento_pedido() {
		return condicao_pagamento_pedido;
	}
	public void setCondicao_pagamento_pedido(String condicao_pagamento_pedido) {
		this.condicao_pagamento_pedido = condicao_pagamento_pedido;
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
	public String getQuantidade_item() {
		return quantidade_item;
	}
	public void setQuantidade_item(String quantidade_item) {
		this.quantidade_item = quantidade_item;
	}
	public Double getPreco_total_item() {
		return preco_total_item;
	}
	public void setPreco_total_item(Double preco_total_item) {
		this.preco_total_item = preco_total_item;
	}
	

}
