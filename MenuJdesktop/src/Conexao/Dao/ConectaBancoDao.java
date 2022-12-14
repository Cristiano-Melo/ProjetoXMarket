package Conexao.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConectaBancoDao {
	private Connection connection = null;
	private java.sql.Statement statement = null;
	private ResultSet resultset = null;
	
	public ConectaBancoDao() {
		 conectar();
	}
	
	private void conectar() {
		
		String servidor = "jdbc:mysql://localhost/xmarket_api";
		String usuario = "root";
		String senha = "mysql";
		String driver = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.connection.createStatement();
		} catch (Exception e) {
			System.out.println("ERRO" + e.getMessage());
		}

	}

	public boolean estaConectado() {
		if (this.connection != null) {
			return true;
		} else {
			return false;
		}

	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public Statement getStatement() {
		return this.statement;
	}
	
	public ResultSet getResultSet() {
		return this.resultset;
	}

	public void setResultset(ResultSet resultset) {
		this.resultset = resultset;
	}

	
	}
