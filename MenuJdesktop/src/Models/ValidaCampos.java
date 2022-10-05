package Models;

import javax.swing.JOptionPane;

import Guis.ValidaEntrada;

public class ValidaCampos {
	String nome_cliente;
	String cpf_cliente;
	String rg_cliente;
	String email_cliente;
	String telefone_cliente;
	String endereco_cliente;
	String bairro_cliente;
	String cidade_cliente;
	String uf_cliente;
	String cep_cliente;
	
	public String getNome_cliente() {
		return nome_cliente;
	}
	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
	public String getCpf_cliente() {
		return cpf_cliente;
	}
	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}
	public String getRg_cliente() {
		return rg_cliente;
	}
	public void setRg_cliente(String rg_cliente) {
		this.rg_cliente = rg_cliente;
	}
	public String getEmail_cliente() {
		return email_cliente;
	}
	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}
	public String getTelefone_cliente() {
		return telefone_cliente;
	}
	public void setTelefone_cliente(String telefone_cliente) {
		this.telefone_cliente = telefone_cliente;
	}
	public String getEndereco_cliente() {
		return endereco_cliente;
	}
	public void setEndereco_cliente(String endereco_cliente) {
		this.endereco_cliente = endereco_cliente;
	}
	public String getBairro_cliente() {
		return bairro_cliente;
	}
	public void setBairro_cliente(String bairro_cliente) {
		this.bairro_cliente = bairro_cliente;
	}
	public String getCidade_cliente() {
		return cidade_cliente;
	}
	public void setCidade_cliente(String cidade_cliente) {
		this.cidade_cliente = cidade_cliente;
	}
	public String getUf_cliente() {
		return uf_cliente;
	}
	public void setUf_cliente(String uf_cliente) {
		this.uf_cliente = uf_cliente;
	}
	public String getCep_cliente() {
		return cep_cliente;
	}
	public void setCep_cliente(String cep_cliente) {
		this.cep_cliente = cep_cliente;
	}
	
	public boolean validaNome_cliente() {
		
		//Validação nome cliente
				if(this.nome_cliente.equals("")) {
					JOptionPane.showInternalMessageDialog(null,	"Campo Nome preenchimento obrigatório.");
//					this.nome_cliente.requestFocus();
					return(false);
				}
				
				if(this.nome_cliente.length()>100) {
					JOptionPane.showInternalMessageDialog(null,	"Nome do Cliente máximo de 100 posições.");
//					textFieldNome.requestFocus();
					return(false);
				}
			return (true);
	}
	
	public boolean validaCpf_cliente() {
		this.cpf_cliente=getCpf_cliente().replace(".", "");
		this.cpf_cliente=getCpf_cliente().replace("-", "");
				
		//Validação cpf
				if(this.cpf_cliente.equals("")) {
					JOptionPane.showInternalMessageDialog(null,	"Campo CPF preenchimento obrigatório.");
//					textFieldCpf.requestFocus();
					return(false);
				}
				
				if(ValidaEntrada.temLetra(cpf)) {
					JOptionPane.showInternalMessageDialog(null, "Campo CPF somente números.");
//					setCpf_cliente(this.cpf_cliente);
//					textFieldCpf.requestFocus();
					return(false);
				}
				
				if(this.cpf_cliente.length()>11) {
					JOptionPane.showInternalMessageDialog(null, "Campo CPF máximo 11 dígitos.");
//					textFieldCpf.setText("");
//					textFieldCpf.requestFocus();
					return(false);
				}
		
	}
		public boolean validaCep_cliente() {
			this.cep_cliente=getCep_cliente().replace("-", "");
			this.cep_cliente=this.cep_cliente.replace(".", "");
			
		}
	
		
		telefone=textFieldTelefone.getText().replace("-", "");
		telefone=telefone.replace(".", "");
		telefone=telefone.replace("(", "");
		telefone=telefone.replace(")", "");
		
			
		
		
		
		
		
		//Valida RG
		if(rg.equals("")) {
			JOptionPane.showInternalMessageDialog(null,	"Campo RG preenchimento obrigatório.");
			textFieldRg.requestFocus();
			return(false);
		}
		
		if(ValidaEntrada.temLetra(rg)) {
			JOptionPane.showInternalMessageDialog(null, "Campo RG somente números.");
			textFieldRg.setText("");
			textFieldRg.requestFocus();
			return(false);
		}
		
		if(rg.length()>8) {
			JOptionPane.showInternalMessageDialog(null, "Campo RG máximo 8 dígitos.");
			textFieldRg.setText("");
			textFieldRg.requestFocus();
			return(false);
		}
		
		
		//Valida endereço
		if(endereco.equals("")) {
			JOptionPane.showInternalMessageDialog(null,	"Campo Endereço preenchimento obrigatório.");
			textFieldEndereco.requestFocus();
			//Valida cep
			if(cep.equals("")) {
				JOptionPane.showInternalMessageDialog(null,	"Campo CEP preenchimento obrigatório.");
				textFieldCep.requestFocus();
				return(false);
			}
			
			if(ValidaEntrada.temLetra(cep)) {
				JOptionPane.showInternalMessageDialog(null,	"Campo Telefone só números.");
				textFieldCep.setText("");
				textFieldCep.requestFocus();
				return(false);
			}
			
			if(cep.length()!=8) {
				JOptionPane.showInternalMessageDialog(null,	"Campo CEP deve ter 8 posições.");
				textFieldCep.requestFocus();
				return(false);
			}
			
			return(false);
		}
		
		if(endereco.length()>255) {
			JOptionPane.showInternalMessageDialog(null, "Campo Endereço máximo 255 caracteres.");
			textFieldEndereco.setText("");
			textFieldEndereco.requestFocus();
			return(false);
		}
		
		//Valida bairro
		if(bairro.equals("")) {
			JOptionPane.showInternalMessageDialog(null,	"Campo Bairro preenchimento obrigatório.");
			textFieldBairro.requestFocus();
			return(false);
		}
		
		if(bairro.length()>50) {
			JOptionPane.showInternalMessageDialog(null, "Campo Bairro máximo 50 caracteres.");
			textFieldBairro.setText("");
			textFieldBairro.requestFocus();
			return(false);
		}
		
		//Valida cidade
		if(cidade.equals("")) {
			JOptionPane.showInternalMessageDialog(null,	"Campo Cidade preenchimento obrigatório.");
			textFieldCidade.requestFocus();
			return(false);
		}
		
		if(cidade.length()>50) {
			JOptionPane.showInternalMessageDialog(null, "Campo Cidade máximo 50 caracteres.");
			textFieldCidade.setText("");
			textFieldCidade.requestFocus();
			return(false);
		}
		
		//Valida UF
		if(uf.equals("")) {
			JOptionPane.showInternalMessageDialog(null,	"Campo UF preenchimento obrigatório.");
			comboBox_Uf.requestFocus();
			return(false);
		}
		
		
			
		//Valida telefone
		if(telefone.equals("")) {
			JOptionPane.showInternalMessageDialog(null,	"Campo Telefone preenchimento obrigatório.");
			textFieldTelefone.requestFocus();
			return(false);
		}
		
		if(ValidaEntrada.temLetra(telefone)) {
			JOptionPane.showInternalMessageDialog(null,	"Campo Telefone só números.");
			textFieldTelefone.requestFocus();
			return(false);
		}
					
		if((telefone.length()<10)||(telefone.length()>11)) {
			JOptionPane.showInternalMessageDialog(null,	"Campo Telefone mínimo 10 e máximo 11 números.");
			textFieldTelefone.requestFocus();
			return(false);
		}
		
	
		
		//Valida email
		if(email.equals("")) {
			JOptionPane.showInternalMessageDialog(null,	"Campo Email preenchimento obrigatório.");
			textFieldEmail.requestFocus();
			return(false);
		}
		
		if((email.length()<5)||(email.length()>100)) {
			JOptionPane.showInternalMessageDialog(null, "Campo Email mínimo 10 e máximo 100 caracteres.");
			textFieldEmail.setText("");
			textFieldEmail.requestFocus();
			return(false);
		}
		
	
		
		//Validar email válido		
		
		//Teste de atualização GIT
		
		
		return(true);
		
	}

}
