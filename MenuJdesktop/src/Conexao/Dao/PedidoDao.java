package Conexao.Dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Models.ItemPedido;
import Models.ListaPedido;
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
			String query = "select * from itens_pedido as i join produtos as p where i.pedidos_cod_pedido = " + pedido.getCod_pedido() + "";
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
						+ produto.getQuantidade_produto() + ") where cod_produto = " + produto.getCod_produto() + ";";

				conectabancodao.getStatement().execute(query2);

			}

			String query3 = "select * from pedidos where cod_pedido=" + pedido.getCod_pedido() + "";
			conectabancodao.getStatement().execute(query3);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public ArrayList<ListaPedido> listarTodosPedidos() {

		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();

		try {
			String query = "select * from pedidos as p join clientes as c join itens_pedido as i join produtos as pr "
					+ "where p.cod_pedido = i.pedidos_cod_pedido and p.clientes_cod_cliente = c.cod_cliente and "
					+ "i.produtos_cod_produto = pr.cod_produto order by p.cod_pedido;";			

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {

				ListaPedido pedidos = new ListaPedido();

				pedidos.setCod_pedido(conectabancodao.getResultSet().getString("cod_pedido"));
				pedidos.setData_pedido(conectabancodao.getResultSet().getString("data_pedido"));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
				pedidos.setValor_venda_produto(conectabancodao.getResultSet().getString("valor_venda_produto"));

				listaDePedidos.add(pedidos);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return listaDePedidos;
	}
	
	public ArrayList<ListaPedido> listarPedidoPorData(String data) {

		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();

		try {
			String query = "select * from pedidos as p join clientes as c join itens_pedido as i join produtos as pr where p.data_pedido = '"
					+ data
					+ "' and p.cod_pedido = i.pedidos_cod_pedido and p.clientes_cod_cliente = c.cod_cliente and i.produtos_cod_produto = pr.cod_produto order by p.cod_pedido;";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {
				ListaPedido pedidos = new ListaPedido();

				pedidos.setCod_pedido(conectabancodao.getResultSet().getString("cod_pedido"));
				pedidos.setData_pedido(conectabancodao.getResultSet().getString("data_pedido"));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
				pedidos.setValor_venda_produto(conectabancodao.getResultSet().getString("valor_venda_produto"));
				pedidos.setCod_produto(conectabancodao.getResultSet().getString("cod_produto"));

				listaDePedidos.add(pedidos);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return listaDePedidos;
	}

	public ArrayList<ListaPedido> listarPedidoEntreDatas(String data, String data2) {

		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();

		try {
			String query = "select * from pedidos as p join clientes as c join itens_pedido as i join produtos as pr where p.data_pedido between '" 
					+ data
					+ "' and' "+data2+" 'and p.cod_pedido = i.pedidos_cod_pedido and p.clientes_cod_cliente = c.cod_cliente and i.produtos_cod_produto = pr.cod_produto order by p.cod_pedido;";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {
				ListaPedido pedidos = new ListaPedido();

				pedidos.setCod_pedido(conectabancodao.getResultSet().getString("cod_pedido"));
				pedidos.setData_pedido(conectabancodao.getResultSet().getString("data_pedido"));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
				System.out.println(pedidos.getPreco_total_item());
				
				pedidos.setValor_venda_produto(conectabancodao.getResultSet().getString("valor_venda_produto"));
				System.out.println(pedidos.getValor_venda_produto());
				
				
				listaDePedidos.add(pedidos);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return listaDePedidos;
	}

	public ArrayList<ListaPedido> listarPedidoPorNomeCliente(String nome) {

		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();
		
		try {
			String query = "select * from pedidos as p join clientes as c join itens_pedido as i join produtos as pr where c.nome_cliente like '%"
					+ nome
					+ "%' and p.cod_pedido = i.pedidos_cod_pedido and p.clientes_cod_cliente = c.cod_cliente and i.produtos_cod_produto = pr.cod_produto order by p.cod_pedido;";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {
				ListaPedido pedidos = new ListaPedido();
				pedidos.setCod_pedido(conectabancodao.getResultSet().getString("cod_pedido"));
				pedidos.setData_pedido(conectabancodao.getResultSet().getString("data_pedido"));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
				pedidos.setValor_venda_produto(conectabancodao.getResultSet().getString("valor_venda_produto"));
				pedidos.setCod_produto(conectabancodao.getResultSet().getString("cod_produto"));

				listaDePedidos.add(pedidos);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return listaDePedidos;
	}
	
	public ArrayList<ListaPedido> listarPedidoPorCpfCliente(String cpf) {

		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();

		try {
			String query = "select * from pedidos as p join clientes as c join itens_pedido as i join produtos as pr where c.cpf_cliente = '"
					+ cpf
					+ "' and p.cod_pedido = i.pedidos_cod_pedido and p.clientes_cod_cliente = c.cod_cliente and i.produtos_cod_produto = pr.cod_produto order by p.cod_pedido;";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {
				
				ListaPedido pedidos = new ListaPedido();

				pedidos.setCod_pedido(conectabancodao.getResultSet().getString("cod_pedido"));
				pedidos.setData_pedido(conectabancodao.getResultSet().getString("data_pedido"));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
				pedidos.setValor_venda_produto(conectabancodao.getResultSet().getString("valor_venda_produto"));

				listaDePedidos.add(pedidos);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return listaDePedidos;
	}

	public ArrayList<ListaPedido> listarPedidoPorCondicaoPagamento(String condicao) {

		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();

		try {
			String query = "select * from pedidos as p join clientes as c join itens_pedido as i join produtos as pr where p.condicao_pagamento_pedido like '%"
					+ condicao
					+ "%' and p.cod_pedido = i.pedidos_cod_pedido and p.clientes_cod_cliente = c.cod_cliente and i.produtos_cod_produto = pr.cod_produto order by p.cod_pedido;";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {
				ListaPedido pedidos = new ListaPedido();
				pedidos.setCod_pedido(conectabancodao.getResultSet().getString("cod_pedido"));
				pedidos.setData_pedido(conectabancodao.getResultSet().getString("data_pedido"));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
				System.out.println(pedidos.getPreco_total_item());
				pedidos.setValor_venda_produto(conectabancodao.getResultSet().getString("valor_venda_produto"));
				System.out.println(pedidos.getValor_venda_produto());
				pedidos.setCod_produto(conectabancodao.getResultSet().getString("cod_produto"));

				listaDePedidos.add(pedidos);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return listaDePedidos;
	}

}
