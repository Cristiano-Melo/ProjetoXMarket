package Conexao.Dao;

import Models.Produto;

public class ProdutoDao {

	private ConectaBancoDao conectabancodao = new ConectaBancoDao();

	public void inserirProduto(Produto produto) {
		try {

			String query = "insert into produtos values(null, '" + produto.getNome_produto() + "','"
					+ produto.getQuantidade_produto() + "','" + produto.getValor_compra_produto() + "','"
					+ produto.getValor_venda_produto() + "','" + produto.getDescricao_produto() + "','" + produto.getCod_marca_pedidoString() + "');";
			System.out.println(query);
			conectabancodao.getStatement().execute(query);

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

	}

}
