package Models;

public class ItemPedido {
	
	String cod_itens_pedido;
	String produtos_cod_produto;
	String pedidos_cod_pedido;
	String quantidade_item;
	String preco_total_item;
	public String getCod_itens_pedido() {
		return cod_itens_pedido;
	}
	public void setCod_itens_pedido(String cod_itens_pedido) {
		this.cod_itens_pedido = cod_itens_pedido;
	}
	public String getProdutos_cod_produto() {
		return produtos_cod_produto;
	}
	public void setProdutos_cod_produto(String produtos_cod_produto) {
		this.produtos_cod_produto = produtos_cod_produto;
	}
	public String getPedidos_cod_pedido() {
		return pedidos_cod_pedido;
	}
	public void setPedidos_cod_pedido(String pedidos_cod_pedido) {
		this.pedidos_cod_pedido = pedidos_cod_pedido;
	}
	public String getQuantidade_item() {
		return quantidade_item;
	}
	public void setQuantidade_item(String quantidade_item) {
		this.quantidade_item = quantidade_item;
	}
	public String getPreco_total_item() {
		return preco_total_item;
	}
	public void setPreco_total_item(String preco_total_item) {
		this.preco_total_item = preco_total_item;
	}

	
	
}
