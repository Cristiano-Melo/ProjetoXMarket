package Conexao.Dao;

import java.util.ArrayList;

import Models.Cliente;

public class ClienteDao {

	private ConectaBancoDao conectabancodao = new ConectaBancoDao();

	// função para inserir um novo cliente
	public void inserirCliente(Cliente cliente) {
		try {
			// query para inserir no banco de dados usando o objeto cliente
			String query = "insert into clientes values(null, '" + cliente.getNome_cliente() + "','"
					+ cliente.getCpf_cliente() + "','" + cliente.getRg_cliente() + "','" + cliente.getEmail_cliente()
					+ "','" + cliente.getTelefone_cliente() + "','" + cliente.getEndereco_cliente() + "','"
					+ cliente.getBairro_cliente() + "','" + cliente.getCidade_cliente() + "','"
					+ cliente.getUf_cliente() + "','" + cliente.getCep_cliente() + "');";
			System.out.println(query); // somente um print no terminal para validação se a query foi executada
										// corretamente
			conectabancodao.getStatement().execute(query); // chamamos o Dao responsável em conectar ao banco de dados
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

	public ArrayList<Cliente> listarTodosClientes() {

		ArrayList<Cliente> listaDeClientes = new ArrayList<>();

		try {
			String query = "select * from clientes;";

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));

			while (conectabancodao.getResultSet().next()) {

				Cliente clientes = new Cliente();

				clientes.setCod_cliente(conectabancodao.getResultSet().getString("cod_cliente"));
				clientes.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				clientes.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				clientes.setRg_cliente(conectabancodao.getResultSet().getString("rg_cliente"));
				clientes.setEmail_cliente(conectabancodao.getResultSet().getString("email_cliente"));
				clientes.setTelefone_cliente(conectabancodao.getResultSet().getString("telefone_cliente"));
				clientes.setEndereco_cliente(conectabancodao.getResultSet().getString("endereco_cliente"));
				clientes.setBairro_cliente(conectabancodao.getResultSet().getString("bairro_cliente"));
				clientes.setCidade_cliente(conectabancodao.getResultSet().getString("cidade_cliente"));
				clientes.setUf_cliente(conectabancodao.getResultSet().getString("uf_cliente"));
				clientes.setCep_cliente(conectabancodao.getResultSet().getString("cep_cliente"));

				listaDeClientes.add(clientes);
			}

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return listaDeClientes;
	}

	public ArrayList<Cliente> listarClientePorNome(String nome) {

		ArrayList<Cliente> listaDeClientes = new ArrayList<>(); // criação de um Array para armazenar os objetos de
																// clientes que vamos buscar no banco de dados

		try {
			String query = "select * from clientes where nome_cliente like '%" + nome + "%'";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query)); // executamos a query no
																								// banco de dados para
																								// realizar a consulta e
																								// armazenamos o
																								// resultado no método
																								// Set do resultset
																								// alocado no Dao
																								// ConectaBancoDao
			while (conectabancodao.getResultSet().next()) { // aqui criamos uma repetição para armazenar no array cada
															// resultado em um objeto cliente diferente para listar
															// todos de uma vez no Scroll pane do Windows Builder

				Cliente clientes = new Cliente(); // criamos um novo objeto Cliente para cada repetição para
													// armazenarmos no Array

				clientes.setCod_cliente(conectabancodao.getResultSet().getString("cod_cliente")); // adicionamos ao
																									// objeto criado
																									// acima cada campo
																									// do resultado do
																									// banco de dados
				clientes.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				clientes.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				clientes.setRg_cliente(conectabancodao.getResultSet().getString("rg_cliente"));
				clientes.setEmail_cliente(conectabancodao.getResultSet().getString("email_cliente"));
				clientes.setTelefone_cliente(conectabancodao.getResultSet().getString("telefone_cliente"));
				clientes.setEndereco_cliente(conectabancodao.getResultSet().getString("endereco_cliente"));
				clientes.setBairro_cliente(conectabancodao.getResultSet().getString("bairro_cliente"));
				clientes.setCidade_cliente(conectabancodao.getResultSet().getString("cidade_cliente"));
				clientes.setUf_cliente(conectabancodao.getResultSet().getString("uf_cliente"));
				clientes.setCep_cliente(conectabancodao.getResultSet().getString("cep_cliente"));

				listaDeClientes.add(clientes); // por fim pegamos o objeto cliente criado acima e alocamos ele dentro de
												// um Array para listar futuramente no Scroll Pane
			}
			System.out.println(listaDeClientes);
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

		return listaDeClientes; // Retornamos esse Array do Tipo Cliente para listarmos no Scroll pane
	}

	public void deletarClientePorId(Cliente cliente) { // Optmos por receber o objeto cliente inteiro mas vamos usar
														// somente o ID dele para realizar o delete deste cadastro no
														// banco de dados
		try {
			String query = "delete from clientes where cod_cliente = '" + cliente.getCod_cliente() + "';";
			System.out.println(query);
			conectabancodao.getStatement().execute(query); // chamamos o Dao responsável em conectar ao banco de dados e
															// executar a query

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

	public void alterarClientePorId(Cliente cliente) { // Mesmo processo do Delete, porém aqui realizamos o update do
														// cadastro de cliente usando o ID e buscamos todos os dados
														// digitados no Windows Builder
		try {
			String query = "update clientes set nome_cliente='" + cliente.getNome_cliente() + "', cpf_cliente='"
					+ cliente.getCpf_cliente() + "',rg_cliente='" + cliente.getRg_cliente() + "',email_cliente='"
					+ cliente.getEmail_cliente() + "',telefone_cliente='" + cliente.getTelefone_cliente()
					+ "',endereco_cliente='" + cliente.getEndereco_cliente() + "',bairro_cliente='"
					+ cliente.getBairro_cliente() + "',cidade_cliente='" + cliente.getCidade_cliente()
					+ "',uf_cliente='" + cliente.getUf_cliente() + "',cep_cliente='" + cliente.getCep_cliente()
					+ "'  where cod_cliente= '" + cliente.getCod_cliente() + "';";
			System.out.println(query);
			conectabancodao.getStatement().execute(query);
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

	public Cliente listarClientePorCpf(String cpf) {
		Cliente clientes = new Cliente();  
		try {
			String query = "select * from clientes where cpf_cliente = '" + cpf + "';";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));
			
				while(conectabancodao.getResultSet().next()) {
				clientes.setCod_cliente(conectabancodao.getResultSet().getString("cod_cliente"));
				clientes.setNome_cliente(conectabancodao.getResultSet().getString("nome_cliente"));
				clientes.setCpf_cliente(conectabancodao.getResultSet().getString("cpf_cliente"));
				clientes.setRg_cliente(conectabancodao.getResultSet().getString("rg_cliente"));
				clientes.setEmail_cliente(conectabancodao.getResultSet().getString("email_cliente"));
				clientes.setTelefone_cliente(conectabancodao.getResultSet().getString("telefone_cliente"));
				clientes.setEndereco_cliente(conectabancodao.getResultSet().getString("endereco_cliente"));
				clientes.setBairro_cliente(conectabancodao.getResultSet().getString("bairro_cliente"));
				clientes.setCidade_cliente(conectabancodao.getResultSet().getString("cidade_cliente"));
				clientes.setUf_cliente(conectabancodao.getResultSet().getString("uf_cliente"));
				clientes.setCep_cliente(conectabancodao.getResultSet().getString("cep_cliente"));
				}
		
		}catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		
		return clientes;

	}
}
