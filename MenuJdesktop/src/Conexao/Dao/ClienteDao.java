package Conexao.Dao;

import java.security.PublicKey;
import java.util.ArrayList;

import Models.Cliente;

public class ClienteDao {

	private ConectaBancoDao conectabancodao = new ConectaBancoDao();

	public void inserirCliente(Cliente cliente) {
		try {

			String query = "insert into clientes values(null, '" + cliente.getNome_cliente() + "','"
					+ cliente.getCpf_cliente() + "','" + cliente.getRg_cliente() + "','" + cliente.getEmail_cliente()
					+ "','" + cliente.getTelefone_cliente() + "','" + cliente.getEndereco_cliente() + "','"
					+ cliente.getBairro_cliente() + "','" + cliente.getCidade_cliente() + "','"
					+ cliente.getUf_cliente() + "','" + cliente.getCep_cliente() + "');";
			System.out.println(query);
			conectabancodao.getStatement().execute(query);
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

		ArrayList<Cliente> listaDeClientes = new ArrayList<>();
		
		try {
			String query = "select * from clientes where nome_cliente like '%" + nome + "%'";
			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));
			System.out.println(conectabancodao.getResultSet());
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
			System.out.println(listaDeClientes);
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		
		return listaDeClientes;
	}

	public void deletarClientePorId(Cliente cliente) {
		try {
			String query = "delete from clientes where cod_cliente = '" + cliente.getCod_cliente() + "';";
			System.out.println(query);
			conectabancodao.getStatement().execute(query);

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

}
