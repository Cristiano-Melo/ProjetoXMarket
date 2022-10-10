package Conexao.Dao;

import java.util.ArrayList;

import Models.ItemPedido;
import Models.Pedido;

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
						+ ",(select max(cod_pedido) from pedidos),"
						+ listaItensPedido.get(cont).getQuantidade_item() + ", (select valor_venda_produto * "
						+ listaItensPedido.get(cont).getQuantidade_item()
						+ " from produtos p where p.cod_produto = "
						+ listaItensPedido.get(cont).getProdutos_cod_produto() + "));";

				conectabancodao.getStatement().execute(query2);

			}

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

	}

}
