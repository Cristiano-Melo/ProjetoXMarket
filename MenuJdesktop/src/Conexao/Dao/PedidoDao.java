package Conexao.Dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Models.ItemPedido;
import Models.ListaPedido;
import Models.Marca;
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
						+ ",(select max(cod_pedido) from pedidos)," + listaItensPedido.get(cont).getQuantidade_item()
						+ ", (select valor_venda_produto * " + listaItensPedido.get(cont).getQuantidade_item()
						+ " from produtos p where p.cod_produto = "
						+ listaItensPedido.get(cont).getProdutos_cod_produto() + "));";

				conectabancodao.getStatement().execute(query2);

				// Se pedido 'P' altera saldo estoque
				if (pedido.getTipo_pedido().equals("P")) {

					String query3 = "update produtos set quantidade_produto = (quantidade_produto - "
							+ listaItensPedido.get(cont).getQuantidade_item() + ") where cod_produto = "
							+ listaItensPedido.get(cont).getProdutos_cod_produto() + ";";

					conectabancodao.getStatement().execute(query3);
				}

			}

			if (pedido.getTipo_pedido().equals("P")) {
				JOptionPane.showInternalMessageDialog(null, "Pedido cadastrado com sucesso!");
			} else {
				JOptionPane.showInternalMessageDialog(null, "Orçamento cadastrado com sucesso!");
			}

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

	}

	public void excluirPedido(Pedido pedido) {

		ArrayList<ListaPedido> listaItensPedido = new ArrayList<>();
		int cod_pedido = Integer.parseInt(pedido.getCod_pedido());
		listaItensPedido = listarPedidoPorCodigoParaDeletar(cod_pedido);

		try {

			int contador = listaItensPedido.size();
			for (int cont = 0; cont < contador; cont++) {

				ListaPedido listaPedido = new ListaPedido();

				listaPedido.setCod_produto(listaItensPedido.get(cont).getCod_produto());
				listaPedido.setNome_produto(listaItensPedido.get(cont).getNome_produto());
				listaPedido.setQuantidade_produto(listaItensPedido.get(cont).getQuantidade_produto());
				listaPedido.setQuantidade_item(listaItensPedido.get(cont).getQuantidade_item());

				String query2 = "update produtos set quantidade_produto = " + listaPedido.getQuantidade_item() + "+"
						+ listaPedido.getQuantidade_produto() + " where cod_produto = " + listaPedido.getCod_produto()
						+ ";";

				System.out.println(query2);

				conectabancodao.getStatement().execute(query2);

			}

			String query3 = "delete from pedidos where cod_pedido=" + pedido.getCod_pedido() + "";
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
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(
						Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
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
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(
						Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
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
					+ data + "' and' " + data2
					+ " 'and p.cod_pedido = i.pedidos_cod_pedido and p.clientes_cod_cliente = c.cod_cliente and i.produtos_cod_produto = pr.cod_produto order by p.cod_pedido;";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {
					ListaPedido pedidos = new ListaPedido();

				pedidos.setCod_pedido(conectabancodao.getResultSet().getString("cod_pedido"));
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(
						Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
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
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(
						Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
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
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setCod_produto(conectabancodao.getResultSet().getString("cod_produto"));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(
						Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
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
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setData_pedido(new SimpleDateFormat("yyyy-MM-dd")
						.parse(conectabancodao.getResultSet().getString("data_pedido")));
				pedidos.setClientes_cod_cliente(conectabancodao.getResultSet().getString("clientes_cod_cliente"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setTipo_pedido(conectabancodao.getResultSet().getString("tipo_pedido"));
				pedidos.setPreco_total_item(
						Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
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

	public ArrayList<ListaPedido> listarPedidoPorCodigo(Integer cod_pedido) {

		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();

		try {
			String query = "select * from pedidos as p join clientes as c join itens_pedido as i join produtos as pr join marcas mar where p.cod_pedido =  "
					+ cod_pedido
					+ " and p.cod_pedido = i.pedidos_cod_pedido and p.clientes_cod_cliente = c.cod_cliente and i.produtos_cod_produto = pr.cod_produto and pr.cod_marca_produto = mar.cod_marca order by p.cod_pedido;";

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {
				ListaPedido pedidos = new ListaPedido();
				pedidos.setCod_pedido(conectabancodao.getResultSet().getString("cod_pedido"));
				pedidos.setCod_cliente(conectabancodao.getResultSet().getString("cod_cliente"));
				;
				pedidos.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				pedidos.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				pedidos.setEndereco_cliente(conectabancodao.getResultSet().getString("endereco_cliente"));
				pedidos.setBairro_cliente(conectabancodao.getResultSet().getString("bairro_cliente"));
				pedidos.setCidade_cliente(conectabancodao.getResultSet().getString("cidade_cliente"));
				pedidos.setUf_cliente(conectabancodao.getResultSet().getString("uf_cliente"));
				pedidos.setCep_cliente(conectabancodao.getResultSet().getString("cep_cliente"));
				pedidos.setCod_itens_pedido(conectabancodao.getResultSet().getString("cod_itens_pedido"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				pedidos.setValor_venda_produto(conectabancodao.getResultSet().getString("valor_venda_produto"));
				pedidos.setCondicao_pagamento_pedido(
						conectabancodao.getResultSet().getString("condicao_pagamento_pedido"));
				pedidos.setPreco_total_item(
						Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item")));
				pedidos.setCod_produto(conectabancodao.getResultSet().getString("cod_produto"));
				pedidos.setCod_marca(conectabancodao.getResultSet().getString("cod_marca"));
				pedidos.setDescricao_marca(conectabancodao.getResultSet().getString("nome_marca"));

				listaDePedidos.add(pedidos);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return listaDePedidos;
	}

	public ArrayList<ListaPedido> listarPedidoPorCodigoParaDeletar(Integer cod_pedido) {

		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();

		try {
			String query = "select * from pedidos as p join itens_pedido as i join produtos as pr where p.cod_pedido =  "
					+ cod_pedido
					+ " and p.cod_pedido = i.pedidos_cod_pedido and i.produtos_cod_produto = pr.cod_produto;";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {
				ListaPedido pedidos = new ListaPedido();

				pedidos.setCod_pedido(conectabancodao.getResultSet().getString("cod_pedido"));
				pedidos.setCod_itens_pedido(conectabancodao.getResultSet().getString("cod_itens_pedido"));
				pedidos.setQuantidade_item(conectabancodao.getResultSet().getString("quantidade_item"));
				pedidos.setQuantidade_produto(conectabancodao.getResultSet().getString("quantidade_produto"));
				pedidos.setCod_produto(conectabancodao.getResultSet().getString("cod_produto"));

				listaDePedidos.add(pedidos);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return listaDePedidos;
	}

	public Integer listarUltimoPedido() {

		Integer ultimoPedido = 0;

		try {
			String query = "select * from pedidos order by cod_pedido desc limit 1";

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {

				ultimoPedido = Integer.parseInt(conectabancodao.getResultSet().getString("cod_pedido"));

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return ultimoPedido;
	}

	public boolean verificaSaldoEstoque(String codigoItem, String qtdeVenda) {
		try {
			int quantidadeEstoque = 0;
			int quantidadeVenda = Integer.parseInt(qtdeVenda);
			String qtdeEstoque = "";
			String query = "select quantidade_produto from produtos where cod_produto ='" + codigoItem + "';";

			System.out.println("query seleção descrição código da marca: [" + query + "]\n");

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			if (conectabancodao.getResultSet().next()) {

				qtdeEstoque = conectabancodao.getResultSet().getString("quantidade_produto");
				quantidadeEstoque = Integer.parseInt(qtdeEstoque);
				if ((quantidadeEstoque - quantidadeVenda) >= 0) {
					return (true);
				}
				return (false);
			}

		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Erro na verificação da quantidade do item: [" + codigoItem
					+ "] " + e.getMessage() + ". Verifique!");
			return (false);
		}
		return (false);

	}

	public ArrayList listarTodosOrcamentos() {
		int cod_orcamento = 0;
		ArrayList<Integer> listaOrcamentos = new ArrayList();

		try {

			String query = "select cod_pedido from pedidos where tipo_pedido='O' order by cod_pedido;";

			System.out.println("Query lista de orçamentos: [" + query + "]\n");

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			cod_orcamento = 0;

			while (conectabancodao.getResultSet().next()) {
				cod_orcamento = Integer.parseInt(conectabancodao.getResultSet().getString("cod_pedido"));
				listaOrcamentos.add(cod_orcamento);
				System.out.println("Orcamento: " + cod_orcamento);
			}

			return (listaOrcamentos);
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null,
					"Erro na seleção dos Orcamentos: [" + e.getMessage() + "]. Verifique!");
			cod_orcamento = -1;
			listaOrcamentos.add(cod_orcamento);
			return (listaOrcamentos);
		}

	}

	public boolean alteraOrcamentoItens(String codigoOrcamentoBase, String codigoOrcamentoNovo) {

		try {

			String query = "delete from itens_pedido where pedidos_cod_pedido=" + codigoOrcamentoBase + ";";
			System.out.println("Query deleta orçamentos(itens): [" + query + "]\n");
			conectabancodao.getStatement().execute(query);

			String query1 = "update itens_pedido set pedidos_cod_pedido = '" + codigoOrcamentoBase
					+ "' where pedidos_cod_pedido='" + codigoOrcamentoNovo + "';";
			System.out.println("Query altera orçamentos(itens): [" + query1 + "]\n");
			conectabancodao.getStatement().execute(query1);

			return (true);
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Erro Alteração ItensPedido [" + codigoOrcamentoBase
					+ "] Erro: [" + e.getMessage() + "]. Verifique!");

			return (false);
		}

	}
	
	public boolean alteraOrcamentoPedido(Pedido pedido, String codigoOrcamentoBase) {

		try {

			String codPedido = pedido.getCod_pedido();
			LocalDate dataPedido = pedido.getData_pedido();
			String clientesCodCliente = pedido.getClientes_cod_cliente();
			String condicaoPagamentoPedido = pedido.getCondicao_pagamento_pedido();
			String tipoPedido = pedido.getTipo_pedido();

			System.out.println("At.Pedido codPedido: " + codPedido);
			System.out.println("At.Pedido dataPedido: " + dataPedido);
			System.out.println("At.Pedido clientesCodCliente: " + clientesCodCliente);
			System.out.println("At.Pedido condicaoPagamentoPedido: " + condicaoPagamentoPedido);
			System.out.println("At.Pedido tipoPedido: " + tipoPedido);

			String query = "update pedidos set data_pedido='" + dataPedido + "', clientes_cod_cliente='"
					+ clientesCodCliente + "',condicao_pagamento_pedido='" + condicaoPagamentoPedido + "',tipo_pedido='"
					+ tipoPedido + "' where cod_pedido='" + codigoOrcamentoBase + "';";
			System.out.println("Query update orçamentos(pedido): [" + query + "]\n");

			conectabancodao.getStatement().execute(query);

			String query1 = "delete from pedidos where cod_pedido='" + codPedido + "';";
			conectabancodao.getStatement().execute(query1);

			return (true);
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null,
					"Erro atualização Pedido [" + codigoOrcamentoBase + "] Erro: [" + e.getMessage() + "]. Verifique!");

			return (false);
		}

	}

	// *************************************

	public String buscaCodigoUltimoPedido() {

		try {
			String codigoPedido = "";
			String query = "select max(cod_pedido) as codPedido from pedidos;";
			System.out.println("Query buscaCodigoUltimoPedido: [" + query + "]\n");

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			if (conectabancodao.getResultSet().next()) {
				codigoPedido = (conectabancodao.getResultSet().getString("codPedido"));
			}
			return (codigoPedido);
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Erro na pesquisa: [" + e.getMessage() + "]. Verifique!");

			return ("");
		}
	}

	public double buscaPrecoVendaItens(String codpedido, String itemPedido) {

		try {
			double precoVenda = 0.00;
			String query = "select preco_total_item from itens_pedido where produtos_cod_produto='" + itemPedido
					+ "' and pedidos_cod_pedido='" + codpedido + "';";

			System.out.println("Query buscaPrecoVendaItens: [" + query + "]\n");

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			if (conectabancodao.getResultSet().next()) {
				precoVenda = Double.parseDouble(conectabancodao.getResultSet().getString("preco_total_item"));
			}

			System.out.println("Dao-Código Pedido: " + codpedido);
			System.out.println("Dao-Item Pedido: " + itemPedido);
			System.out.println("Dao-precoVenda: " + precoVenda);

			return (precoVenda);
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null,
					"Erro na buscaPrecoVendaItens: [" + e.getMessage() + "]. Verifique!");

			return (0.0);
		}

	}

	public boolean excluirOrcamento(String codigoOrcamento) {

		try {

			String query = "delete from itens_pedido where pedidos_cod_pedido=" + codigoOrcamento + ";";
			System.out.println("Query itens orçamento(itens): [" + query + "]\n");
			conectabancodao.getStatement().execute(query);

			String query1 = "delete from pedidos where cod_pedido=" + codigoOrcamento + ";";
			System.out.println("Query deleta orçamento: [" + query1 + "]\n");
			conectabancodao.getStatement().execute(query1);

			return (true);
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Erro na exclusão do Orcamento [" + codigoOrcamento
					+ "] Erro: [" + e.getMessage() + "]. Verifique!");

			return (false);
		}

	}

}
