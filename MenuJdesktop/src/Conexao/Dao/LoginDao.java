package Conexao.Dao;

import javax.swing.JOptionPane;

import Guis.frmPrincipal;
import Models.Login;

public class LoginDao {

	private ConectaBancoDao conectabancodao = new ConectaBancoDao();

	public void logar(Login login) {

		String query = "select * from login where usuario = '" + login.getUsuario() + "' and senha = '"
				+ login.getSenha() + "'";

		try {

			conectabancodao.setResultset(conectabancodao.getStatement().executeQuery(query));
			boolean result = conectabancodao.getResultSet().next();

			if (result) {

				JOptionPane.showMessageDialog(null,"Seja Bem vindo");
				frmPrincipal principal = new frmPrincipal();
				principal.setVisible(true);

			} else {

				JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
			}

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			JOptionPane.showMessageDialog(null, e);

		}

	}

}