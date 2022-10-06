package Conexao.Dao;

import Models.Pedido;

public class PedidoDao {

	private ConectaBancoDao conectabancodao = new ConectaBancoDao();

	public void inserirPedido(Pedido pedido) {
		try {

			String query = "insert into produtos values(null, '" + pedido.getCpf_cliente() + "','"
					+ pedido.getQuantidade_produto() + "','" + pedido.getData() + "','"
					+ pedido.getValorTotal() + "','" + pedido.getQtdItens() + "','" + pedido.getDescricao_produto() + "');";
			System.out.println(query);
			conectabancodao.getStatement().execute(query);

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

	}

}

