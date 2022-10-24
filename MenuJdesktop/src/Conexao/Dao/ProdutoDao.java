package Conexao.Dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Guis.FormataDecimal;
import Models.Produto;

public class ProdutoDao {

	private ConectaBancoDao conectabancodao = new ConectaBancoDao();

	public void inserirProduto(Produto produto) {
		try {

			String query = "insert into produtos values(null, '" + produto.getNome_produto() + "','"
					+ produto.getQuantidade_produto() + "','" + produto.getValor_compra_produto() + "','"
					+ produto.getValor_venda_produto() + "','" + produto.getDescricao_produto() + "','"
					+ produto.getCod_marca_produto() + "');";
			System.out.println(query);
			conectabancodao.getStatement().execute(query);
			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Erro no cadastro do produto [" + e.getMessage() + "]. Verifique!");
		}

	}

	public ArrayList<Produto> listarTodosProdutos() {

		ArrayList<Produto> listaDeProdutos = new ArrayList<>();

		try {
			String query = "select * from produtos AS p join marcas AS m ON p.cod_marca_produto = m.cod_marca;";

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {

				Produto produto = new Produto();

				produto.setCod_produto(conectabancodao.getResultSet().getString("cod_produto"));
				produto.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				produto.setQuantidade_produto(conectabancodao.getResultSet().getString("quantidade_produto"));
				produto.setValor_compra_produto(conectabancodao.getResultSet().getString("valor_compra_produto"));
//				String valorCompra= FormataDecimal.duasCasas(conectabancodao.getResultSet().getString("valor_compra_produto"));
//				produto.setValor_compra_produto(valorCompra);
//				String valorVenda = FormataDecimal.duasCasas(conectabancodao.getResultSet().getString("valor_venda_produto"));
//				produto.setValor_venda_produto(valorVenda);
				produto.setValor_venda_produto(conectabancodao.getResultSet().getString("valor_venda_produto"));
				produto.setDescricao_produto(conectabancodao.getResultSet().getString("descricao_produto"));
				produto.setCod_marca_produto(conectabancodao.getResultSet().getString("cod_marca_produto"));
				produto.setNome_marca_produto(conectabancodao.getResultSet().getString("nome_marca"));

				listaDeProdutos.add(produto);
			}

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return listaDeProdutos;
	}

	public ArrayList<Produto> listarProdutoPorNome(String nome) {

		ArrayList<Produto> listaDeProdutos = new ArrayList<>(); // criação de um Array para armazenar os objetos de
																// produto que vamos buscar no banco de dados

		try {
			String query = "select * from produtos AS p join marcas AS m ON p.cod_marca_produto = m.cod_marca where nome_produto like '%"
					+ nome + "%'";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query)); // executamos a query no
																								// banco de dados para
																								// realizar a consulta e
																								// armazenamos o
																								// resultado no método
																								// Set do resultset
																								// alocado no Dao
																								// ConectaBancoDao
			while (conectabancodao.getResultSet().next()) { // aqui criamos uma repetição para armazenar no array cada
															// resultado em um objeto produto diferente para listar
															// todos de uma vez no Scroll pane do Windows Builder

				Produto produto = new Produto(); // criamos um novo objeto Produto para cada repetição para armazenarmos
													// no Array

				produto.setCod_produto(conectabancodao.getResultSet().getString("cod_produto")); // adicionamos ao
																									// objeto criado
																									// acima cada campo
																									// do resultado do
																									// banco de dados
				produto.setNome_produto(conectabancodao.getResultSet().getString("nome_produto"));
				produto.setQuantidade_produto(conectabancodao.getResultSet().getString("quantidade_produto"));
				produto.setValor_compra_produto(conectabancodao.getResultSet().getString("valor_compra_produto"));
//				String valorCompra= FormataDecimal.duasCasas(conectabancodao.getResultSet().getString("valor_compra_produto"));
//				produto.setValor_compra_produto(valorCompra);
//				String valorVenda = FormataDecimal.duasCasas(conectabancodao.getResultSet().getString("valor_venda_produto"));
//				produto.setValor_venda_produto(valorVenda);
				produto.setValor_venda_produto(conectabancodao.getResultSet().getString("valor_venda_produto"));
				produto.setDescricao_produto(conectabancodao.getResultSet().getString("descricao_produto"));
				produto.setCod_marca_produto(conectabancodao.getResultSet().getString("cod_marca_produto"));
				produto.setNome_marca_produto(conectabancodao.getResultSet().getString("nome_marca"));

				listaDeProdutos.add(produto); // por fim pegamos o objeto produto criado acima e alocamos ele dentro de
												// um Array para listar futuramente no Scroll Pane
			}
			// System.out.println(listaDeProdutos);
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

		return listaDeProdutos; // Retornamos esse Array do Tipo Produto para listarmos no Scroll pane
	}

	public void deletarProdutoPorId(Produto produto) { // Optamos por receber o objeto produto inteiro mas vamos usar
														// somente o ID dele para realizar o delete deste cadastro no
														// banco de dados
		try {
			String query = "delete from produtos where cod_produto = '" + produto.getCod_produto() + "';";
			System.out.println("Query exclusãoProduto: " + query);
			conectabancodao.getStatement().execute(query); // chamamos o Dao responsável em conectar ao banco de dados e
															// executar a query
			JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Erro na exclusão do produto [" + e.getMessage() + "]. Verifique!");
		}
	}

	public void alterarProdutoPorId(Produto produto) { // Mesmo processo do Delete, porém aqui realizamos o update do
														// cadastro de produto usando o ID e buscamos todos os dados
														// digitados no Windows Builder
		try {

			String query = "update produtos set nome_produto='" + produto.getNome_produto() + "', quantidade_produto='"
					+ produto.getQuantidade_produto() + "',valor_compra_produto='" + produto.getValor_compra_produto()
					+ "',valor_venda_produto='" + produto.getValor_venda_produto() + "',descricao_produto='"
					+ produto.getDescricao_produto() + "',cod_marca_produto='" + produto.getCod_marca_produto()
					+ "'  where cod_produto= '" + produto.getCod_produto() + "';";

			System.out.println(query);
			conectabancodao.getStatement().execute(query);
			JOptionPane.showMessageDialog(null, "Cadastro alterado sucesso!");
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			JOptionPane.showMessageDialog(null,
					"Erro na alteração do cadastro do produto [" + e.getMessage() + "]. Verifique!");
		}
	}

	// Método que retorna o valor de compra do produto
	public String buscaPrecoCompra(String codProduto) {

		try {
			String query = "select valor_compra_produto from produtos where cod_produto = '" + codProduto + "';";
			System.out.println("Query buscaPrecoCompra: " + query);
			conectabancodao.getStatement().execute(query);

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			if (conectabancodao.getResultSet().next()) {
				String valorCompra = conectabancodao.getResultSet().getString("valor_compra_produto");
				return (valorCompra);
			}
			return ("-1.00");

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Erro na buscaPrecoCompra [" + e.getMessage() + "]. Verifique!");
			return (e.getMessage());
		}

	}

	public String buscaPrecoVenda(String codProduto) {

		try {
			String query = "select valor_venda_produto from produtos where cod_produto = '" + codProduto + "';";
			System.out.println("Query buscaPrecoVenda: " + query);
			conectabancodao.getStatement().execute(query);

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			if (conectabancodao.getResultSet().next()) {
				String valorVenda = conectabancodao.getResultSet().getString("valor_venda_produto");
				return (valorVenda);
			}
			return ("-1.00");

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Erro na buscaPrecoVenda [" + e.getMessage() + "]. Verifique!");
			return (e.getMessage());
		}

	}

	public String buscaCodigoUltimoProdutoCadastrado() {

		try {
			String query = "select max(cod_produto) as cod_produto from produtos;";
			System.out.println("Query buscaCodigoProduto: " + query);
			conectabancodao.getStatement().execute(query);

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			if (conectabancodao.getResultSet().next()) {
				String codigoProduto = conectabancodao.getResultSet().getString("cod_produto");
				return (codigoProduto);
			}
			return ("-1.00");

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Erro na buscaCodigoProduto [" + e.getMessage() + "]. Verifique!");
			return (e.getMessage());
		}

	}

}
