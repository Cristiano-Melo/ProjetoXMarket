package Conexao.Dao;

import javax.swing.JOptionPane;

import Guis.frmPrincipal;
import Models.Login;

public class LoginDao {

	private ConectaBancoDao conectabancodao = new ConectaBancoDao();

	public void logar(Login login) {

		String query = "select * from login where usuario = '" + login.getUsuario() + "' and senha = '"
				+ login.getSenha() + "'"; // Query para conectar no banco de dados e trazer o usuário e senha caso encontre no banco

		try {

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query)); // Retorna verdadeiro ou falso de acordo com o select acimoa
			boolean result = conectabancodao.getResultSet().next(); // seta uma variável com o true or false do resultado da query

			if (result) { // caso verdadeiro loga no sistema
				JOptionPane.showMessageDialog(null,"Seja Bem vindo");
				frmPrincipal principal = new frmPrincipal();
				principal.setVisible(true);

			} else { // caso falso retorna erro de usuário ou senha inválido

				JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
			}

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			JOptionPane.showMessageDialog(null, e);

		}

	}

}