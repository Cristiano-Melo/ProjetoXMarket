package Models;

import java.time.LocalDate;
import java.util.Date;

public class ListaPedido {
	String cod_pedido;
	Date data_pedido;
	String condicao_pagamento_pedido;
	String clientes_cod_cliente;
	String tipo_pedido;
	
	String cod_cliente;
	String nome_cliente;
	String cpf_cliente;
	String rg_cliente;
	String email_cliente;
	String endereco_cliente;
	String bairro_cliente;
	String cidade_cliente;
	String uf_cliente;
	String cep_cliente;
	
	String cod_produto;
	String nome_produto;
	String valor_venda_produto;
	String quantidade_produto;
	
	public String getQuantidade_produto() {
		return quantidade_produto;
	}
	public void setQuantidade_produto(String quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}
	String quantidade_item;
	Double preco_total_item;
	String cod_itens_pedido;
	
	
	public String getCod_itens_pedido() {
		return cod_itens_pedido;
	}
	public void setCod_itens_pedido(String cod_itens_pedido) {
		this.cod_itens_pedido = cod_itens_pedido;
	}
	public String getCep_cliente() {
		return cep_cliente;
	}
	public void setCep_cliente(String cep_cliente) {
		this.cep_cliente = cep_cliente;
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
	public void setUf_cliente(String estado_cliente) {
		this.uf_cliente = estado_cliente;
	}
	public String getEndereco_cliente() {
		return endereco_cliente;
	}
	public void setEndereco_cliente(String endereco_cliente) {
		this.endereco_cliente = endereco_cliente;
	}
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
	public Date getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(Date data_pedido) {
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
