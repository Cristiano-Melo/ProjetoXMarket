package Conexao.Dao;

import Models.Produto;

public class ProdutoDao {

	private ConectaBancoDao conectabancodao = new ConectaBancoDao();

	public void inserirProduto(Produto produto) {
		try {

			String query = "insert into produtos values(null, '" + produto.getNome_produto() + "','"
					+ produto.getQuantidade_produto() + "','" + produto.getValor_produto() + "','"
					+ produto.getDescricao_produto() + "');";
			System.out.println(query);
			conectabancodao.getStatement().execute(query);

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

	}

}
