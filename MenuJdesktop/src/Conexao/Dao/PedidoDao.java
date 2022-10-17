package Conexao.Dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Models.ItemPedido;
import Models.Pedido;
import Models.Produto;

public class PedidoDao {

	private ConectaBancoDao conectabancodao = new ConectaBancoDao();

	public void inserirPedido(Pedido pedido, ArrayList<ItemPedido> itempedido) {

		ArrayList<ItemPedido> listaItensPedido = new ArrayList<>();
		listaItensPedido = itempedido;

		try {

			String query = "insert into pedidos values(null,'" + pedido.getData_pedido() + "',"
					+ pedido.getClientes_cod_cliente() + ",'" + pedido.getCondicao_pagamento_pedido() + "','"
					+ pedido.getTipo_pedido() + "');";

			conectabancodao.getStatement().execute(query);

			int contador = listaItensPedido.size();
			for (int cont = 0; cont < contador; cont++) {
				String query2 = "insert into itens_pedido values(null,"
						+ listaItensPedido.get(cont).getProdutos_cod_produto()
						+ ",(select max(cod_pedido) from pedidos)," + listaItensPedido.get(cont).getQuantidade_item()
						+ ", (select valor_venda_produto * " + listaItensPedido.get(cont).getQuantidade_item()
						+ " from produtos p where p.cod_produto = "
						+ listaItensPedido.get(cont).getProdutos_cod_produto() + "));";

				conectabancodao.getStatement().execute(query2);

				String query3 = "update produtos set quantidade_produto = (quantidade_produto - "
						+ listaItensPedido.get(cont).getQuantidade_item() + ") where cod_produto = "
						+ listaItensPedido.get(cont).getProdutos_cod_produto() + ";";

				conectabancodao.getStatement().execute(query3);

			}

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

	}
	
	public void excluirPedido(Pedido pedido) {
//		ArrayList<Produto> listaItensPedido = new ArrayList<>();
		try {
			String query = "select * from itens_pedido where pedidos_cod_pedido = "+pedido.getCod_pedido()+"";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));
			while (conectabancodao.getResultSet().next()) {
				
				Produto produto = new Produto();
				
				produto.setCod_produto(conectabancodao.getResultSet().getString("cod_produto"));
				produto.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				produto.setQuantidade_produto(conectabancodao.getResultSet().getString("quantidade_produto"));
				produto.setValor_compra_produto(conectabancodao.getResultSet().getString("valor_compra_produto"));
				produto.setValor_venda_produto(conectabancodao.getResultSet().getString("valor_venda_produto"));
				produto.setDescricao_produto(conectabancodao.getResultSet().getString("descricao_produto"));
				produto.setCod_marca_produto(conectabancodao.getResultSet().getString("cod_marca_produto"));
				produto.setNome_marca_produto(conectabancodao.getResultSet().getString("nome_marca"));
				
				String query2 = "update produtos set quantidade_produto = (quantidade_produto + "
						+ produto.getQuantidade_produto() + ") where cod_produto = "
						+ produto.getCod_produto() + ";";

				conectabancodao.getStatement().execute(query2);
				
			}
			
			String query3 = "select * from pedidos where cod_pedido="+pedido.getCod_pedido()+"";
			conectabancodao.getStatement().execute(query3);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

}
