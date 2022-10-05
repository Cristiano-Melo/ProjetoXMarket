package Conexao.Dao;

import java.security.PublicKey;

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

	public void listarTodosClientes() {
		try {
			String query = "select * from clientes;";
			System.out.println(query);
			conectabancodao.getStatement().execute(query);
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public void listarCliente() {
		try {
			String query = "select * from clientes where='" + cliente.get"' "
			}
		}
}
