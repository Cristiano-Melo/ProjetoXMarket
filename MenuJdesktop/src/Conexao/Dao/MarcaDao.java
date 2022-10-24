package Conexao.Dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Models.Marca;

public class MarcaDao {

	private ConectaBancoDao conectabancodao = new ConectaBancoDao();

	// Inclui nova marca no BD
	public void inserirMarca(Marca marca) {
		try {
			String query = "insert into marcas values (" + marca.getCodigoMarca() + ",'" + marca.getDescricaoMarca()
					+ "');";
			System.out.println("query inserção marca: [" + query + "]\n");
			conectabancodao.getStatement().execute(query);

			JOptionPane.showInternalMessageDialog(null, "Marca [" + marca.getCodigoMarca() + "] inserida com sucesso!");

		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null,
					"Erro na inclusão da marca: [" + marca.getCodigoMarca() + "] " + e.getMessage() + ". Verifique!");
		}
	}

	// Lista as marcas cadastradas no BD
	public ArrayList<Marca> listarTodasMarcas() {

		ArrayList<Marca> listaDeMarcas = new ArrayList<>();

		try {
			String query = "select * from marcas order by cod_marca;";
			System.out.println("query seleção marca: [" + query + "]\n");

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {
				Marca marca = new Marca();

				marca.setCodigoMarca(conectabancodao.getResultSet().getString("cod_marca"));
				marca.setDescricaoMarca(conectabancodao.getResultSet().getString("nome_marca"));

				listaDeMarcas.add(marca);
			}

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return listaDeMarcas;
	}

	// Deleta uma marca cadastrada no BD
	public void deletarMarca(Marca marca) { // Optamos por receber o objeto marca inteiro mas vamos usar somente o ID
											// dele para realizar o delete deste cadastro no banco de dados
		try {
			String query = "delete from marcas where cod_marca = " + marca.getCodigoMarca() + ";";
			System.out.println("query deleção marca: [" + query + "]\n");
			conectabancodao.getStatement().execute(query); // chamamos o Dao responsável em conectar ao banco de dados e
															// executar a query
			JOptionPane.showInternalMessageDialog(null, "Marca [" + marca.getCodigoMarca() + "] excluída com sucesso!");

		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null,
					"Erro na exclusão da marca: [" + marca.getCodigoMarca() + "] " + e.getMessage() + ". Verifique!");

		}
	}

	public void alterarMarcaPorCodigo(Marca marca) { // Mesmo processo do Delete, porém aqui realizamos o update do cadastro
													// de marca usando o ID e buscamos todos os dados digitados no
													// Windows Builder
		try {
			String query = "update marcas set nome_marca='" + marca.getDescricaoMarca() + "' where cod_marca = "
					+ marca.getCodigoMarca() + ";";
			System.out.println("query alteração marca: [" + query + "]\n");
			conectabancodao.getStatement().execute(query);
			JOptionPane.showInternalMessageDialog(null, "Marca [" + marca.getCodigoMarca() + "] alterada com sucesso!");
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null,
					"Erro na alteração da marca: [" + marca.getCodigoMarca() + "] " + e.getMessage() + ". Verifique!");
		}
	}
	
	public String buscaDescricaoCodMarca(String codmarca) {
		String  descricao="";
		try {
			
			
			String query = "select nome_marca from marcas where cod_marca='"+codmarca+"';";
			
			System.out.println("query seleção descrição código da marca: [" + query + "]\n");
			
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));
			
			if(conectabancodao.getResultSet().next()){
				descricao=conectabancodao.getResultSet().getString("nome_marca");
				return(descricao);
			}
			return("Descrição não encontrada");
		
			
	
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null,
					"Erro na seleção da descrição da marca: [" + codmarca + "] " + e.getMessage() + ". Verifique!");
			return(descricao);
		}
		
	}

}
