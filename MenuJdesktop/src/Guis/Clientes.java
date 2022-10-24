package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Conexao.Dao.ClienteDao;
import Models.Cliente;

public class Clientes extends JInternalFrame {
	private JTextField textFieldCodCliente;
	private static JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldEndereco;
	private JTextField textFieldRg;
	private JTextField textFieldBairro;
	private JTextField textFieldTelefone;
	private JTextField textFieldCep;
	private JTable table;
	private JTextField textFieldEmail;
	private JTextField textFieldCidade;
	private JComboBox comboBox_Uf = new JComboBox();
	

	DefaultTableModel model;
	Object[] row = new Object[11];
	ClienteDao clientedao = new ClienteDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes frame = new Clientes();
					frame.setVisible(true);
					textFieldNome.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Clientes() {
		setClosable(true);
		setFrameIcon(new ImageIcon(Clientes.class.getResource("/Icones/cliente.png")));
		setTitle("Gestão de Clientes");
		setBounds(100, 100, 770, 538);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(318, 0, 132, 51);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 40, 749, 457);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCodCliente = new JLabel("Cod_Cliente:");
		lblCodCliente.setBounds(10, 25, 81, 20);
		lblCodCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblCodCliente);

		textFieldCodCliente = new JTextField();
		textFieldCodCliente.setEditable(false);
		textFieldCodCliente.setBounds(101, 25, 55, 20);
		textFieldCodCliente.setColumns(10);
		textFieldCodCliente.setBackground(new Color(225, 225, 225));
		panel.add(textFieldCodCliente);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(205, 25, 46, 20);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(277, 23, 238, 20);
		textFieldNome.setColumns(10);
		textFieldNome.setBackground(new Color(225, 225, 225));
		panel.add(textFieldNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(525, 25, 89, 18);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblCpf);

		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(557, 26, 168, 20);
		textFieldCpf.setColumns(10);
		textFieldCpf.setBackground(new Color(225, 225, 225));
		panel.add(textFieldCpf);

		textFieldEndereco = new JTextField();
		textFieldEndereco.setBounds(277, 61, 448, 20);
		textFieldEndereco.setColumns(10);
		textFieldEndereco.setBackground(new Color(225, 225, 225));
		panel.add(textFieldEndereco);

		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(205, 60, 89, 20);
		lblEndereco.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblEndereco);

		textFieldRg = new JTextField();
		textFieldRg.setBounds(54, 125, 126, 20);
		textFieldRg.setColumns(10);
		textFieldRg.setBackground(new Color(225, 225, 225));
		panel.add(textFieldRg);

		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(10, 125, 104, 20);
		lblRg.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblRg);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 91, 46, 20);
		lblBairro.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblBairro);

		textFieldBairro = new JTextField();
		textFieldBairro.setBounds(54, 91, 272, 20);
		textFieldBairro.setColumns(10);
		textFieldBairro.setBackground(new Color(225, 225, 225));
		panel.add(textFieldBairro);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(336, 92, 55, 18);
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblCidade);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(277, 125, 148, 20);
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBackground(new Color(225, 225, 225));
		panel.add(textFieldTelefone);

		textFieldCep = new JTextField();
		textFieldCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();

			}
		});
		textFieldCep.setBounds(54, 61, 126, 20);
		textFieldCep.setColumns(10);
		textFieldCep.setBackground(new Color(225, 225, 225));
		panel.add(textFieldCep);

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(10, 60, 104, 20);
		lblCep.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblCep);

		comboBox_Uf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		comboBox_Uf.setBounds(599, 92, 126, 22);
		panel.add(comboBox_Uf);

		JLabel lblQuantidade_1_1 = new JLabel("UF:");
		lblQuantidade_1_1.setBounds(574, 92, 55, 18);
		lblQuantidade_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblQuantidade_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 187, 716, 218);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int contador = table.getSelectedRow();
				textFieldCodCliente.setText(model.getValueAt(contador, 0).toString());
				textFieldNome.setText(model.getValueAt(contador, 1).toString());
				textFieldCpf.setText(model.getValueAt(contador, 2).toString());
				textFieldRg.setText(model.getValueAt(contador, 3).toString());
				textFieldEmail.setText(model.getValueAt(contador, 4).toString());
				textFieldTelefone.setText(model.getValueAt(contador, 5).toString());
				textFieldEndereco.setText(model.getValueAt(contador, 6).toString());
				textFieldBairro.setText(model.getValueAt(contador, 7).toString());
				textFieldCidade.setText(model.getValueAt(contador, 8).toString());
				comboBox_Uf.setSelectedItem(model.getValueAt(contador, 9));
				textFieldCep.setText(model.getValueAt(contador, 10).toString());
			}

		});

		model = new DefaultTableModel();
		Object[] colunn = { "Codigo", "Nome", "CPF", "RG", "Email", "Telefone", "Endereço", "Bairro", "Cidade", "UF",
				"CEP" };
	//	Object[] row = new Object[11];
		model.setColumnIdentifiers(colunn);
		table.setModel(model);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (validaCampos() == false) {
						return;
					}
					String valida = textFieldCodCliente.getText();
					if (!valida.equals("")) {
						JOptionPane.showInternalMessageDialog(null, "Cliente já cadastrado. Operação inválida");
						return;
					}

					Cliente cliente = new Cliente();
					ClienteDao clienteDao = new ClienteDao();

					cliente.setNome_cliente(textFieldNome.getText());
					cliente.setCpf_cliente(textFieldCpf.getText());
					cliente.setRg_cliente(textFieldRg.getText());
					cliente.setEmail_cliente(textFieldEmail.getText());
					cliente.setTelefone_cliente(textFieldTelefone.getText());
					cliente.setEndereco_cliente(textFieldEndereco.getText());
					cliente.setBairro_cliente(textFieldBairro.getText());
					cliente.setCidade_cliente(textFieldCidade.getText());
					cliente.setUf_cliente(comboBox_Uf.getSelectedItem().toString());
					cliente.setCep_cliente(textFieldCep.getText());

					clienteDao.inserirCliente(cliente);
					
					ArrayList<Cliente> listaDeClientes = new ArrayList<>();
					listaDeClientes = clientedao.listarTodosClientes();
					limpaCamposGrid();
					montaGrid(listaDeClientes);

				} catch (Exception erroCadastroCliente) {
					JOptionPane.showMessageDialog(null, erroCadastroCliente);
					limpaCampos();
				}

			}
		});
		btnGravar.setBounds(78, 416, 89, 23);
		panel.add(btnGravar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCamposGrid();

			}
		});
		btnLimpar.setBounds(579, 416, 89, 23);
		panel.add(btnLimpar);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefone.setBounds(205, 125, 71, 18);
		panel.add(lblTelefone);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(462, 125, 55, 18);
		panel.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBackground(new Color(225, 225, 225));
		textFieldEmail.setBounds(520, 125, 205, 20);
		panel.add(textFieldEmail);

		textFieldCidade = new JTextField();
		textFieldCidade.setColumns(10);
		textFieldCidade.setBackground(new Color(225, 225, 225));
		textFieldCidade.setBounds(394, 92, 170, 20);
		panel.add(textFieldCidade);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ClienteDao clientedao = new ClienteDao();
				Cliente cliente = new Cliente();
				cliente.setCod_cliente(textFieldCodCliente.getText());

				if (textFieldNome.getText().equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Nenhum cliente selecionado.");
					return;
				}

				if (JOptionPane.showConfirmDialog(null, "Confirma exclusão do Cliente?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					clientedao.deletarClientePorId(cliente);
				}
				
				ArrayList<Cliente> listaDeClientes = new ArrayList<>();
				listaDeClientes = clientedao.listarTodosClientes();
				limpaCamposGrid();
				montaGrid(listaDeClientes);

			}
		});
		btnDeletar.setBounds(412, 416, 89, 23);
		panel.add(btnDeletar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textFieldNome.getText().equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Nenhum cliente selecionado.");
					return;
				}
				
				if (validaCampos() == false) {
                    return;
                }

				ClienteDao clientedao = new ClienteDao();
				Cliente cliente = new Cliente();
				cliente.setCod_cliente(textFieldCodCliente.getText());
				cliente.setNome_cliente(textFieldNome.getText());
				cliente.setCpf_cliente(textFieldCpf.getText());
				cliente.setRg_cliente(textFieldRg.getText());
				cliente.setEmail_cliente(textFieldEmail.getText());
				cliente.setTelefone_cliente(textFieldTelefone.getText());
				cliente.setEndereco_cliente(textFieldEndereco.getText());
				cliente.setBairro_cliente(textFieldBairro.getText());
				cliente.setCidade_cliente(textFieldCidade.getText());
				cliente.setUf_cliente(comboBox_Uf.getSelectedItem().toString());
				cliente.setCep_cliente(textFieldCep.getText());

				if (JOptionPane.showConfirmDialog(null, "Confirma atualização do cadastro?",
						"SIM", JOptionPane.YES_NO_OPTION) == 0) {
					clientedao.alterarClientePorId(cliente);
					ArrayList<Cliente> listaDeClientes = new ArrayList<>();
					listaDeClientes = clientedao.listarTodosClientes();
					limpaCamposGrid();
					montaGrid(listaDeClientes);
				}
				limpaCampos();
			
			}
			
		});
		btnAlterar.setBounds(245, 416, 89, 23);
		panel.add(btnAlterar);

		JButton btnPesquisa_1 = new JButton("");
		btnPesquisa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}

				ArrayList<Cliente> listaDeClientes = new ArrayList<>();
				listaDeClientes = InputDialog();
				montaGrid(listaDeClientes);

			}
		});
		btnPesquisa_1.setIcon(new ImageIcon(Clientes.class.getResource("/Icones/lupa.png")));
		btnPesquisa_1.setBounds(10, 155, 35, 30);
		panel.add(btnPesquisa_1);

	}

	public boolean validaCampos() {
		String nome = textFieldNome.getText();
		String cpf = textFieldCpf.getText();
		String rg = textFieldRg.getText();
		String email = textFieldEmail.getText();
		String endereco = textFieldEndereco.getText();
		String bairro = textFieldBairro.getText();
		String cidade = textFieldCidade.getText();
		String uf = comboBox_Uf.getSelectedItem().toString();
		String cep = textFieldCep.getText();
		String telefone = textFieldTelefone.getText();

		// Validação nome cliente
		if (nome.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Nome preenchimento obrigatório.");
			textFieldNome.requestFocus();
			return (false);
		}

		if ((nome.length() < 2) || (nome.length() > 100)) {
			JOptionPane.showInternalMessageDialog(null, "Nome do Cliente mínimo 2 máximo 100 posições.");
			textFieldNome.requestFocus();
			return (false);
		}

		// Validação cpf
		if (cpf.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo CPF preenchimento obrigatório.");
			textFieldCpf.requestFocus();
			return (false);
		}

		if (cpf.length() != 11) {
			JOptionPane.showInternalMessageDialog(null, "Campo CPF 11 dígitos.");
			textFieldCpf.setText("");
			textFieldCpf.requestFocus();
			return (false);
		}

		if (!ValidaEntrada.isNumero(cpf)) {
			JOptionPane.showInternalMessageDialog(null, "Campo CPF somente números.");
			textFieldCpf.setText("");
			textFieldCpf.requestFocus();
			return (false);
		}

		if (!ValidaEntrada.CPFValido(cpf)) {
			JOptionPane.showInternalMessageDialog(null, "CPF inválido!");
			textFieldCpf.setText("");
			textFieldCpf.requestFocus();
			return (false);
		}

		// Valida RG
		if (rg.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo RG preenchimento obrigatório.");
			textFieldRg.requestFocus();
			return (false);
		}

		if (!ValidaEntrada.isNumero(rg)) {
			JOptionPane.showInternalMessageDialog(null, "Campo RG somente números.");
			textFieldRg.setText("");
			textFieldRg.requestFocus();
			return (false);
		}

		if (rg.length() > 8) {
			JOptionPane.showInternalMessageDialog(null, "Campo RG máximo 8 dígitos.");
			textFieldRg.requestFocus();
			return (false);
		}

		// Valida endereço
		if (endereco.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Endereço preenchimento obrigatório.");
			textFieldEndereco.requestFocus();
			return (false);
		}

		if (endereco.length() > 255) {
			JOptionPane.showInternalMessageDialog(null, "Campo Endereço máximo 255 caracteres.");
			textFieldEndereco.setText("");
			textFieldEndereco.requestFocus();
			return (false);
		}

		// Valida bairro
		if (bairro.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Bairro preenchimento obrigatório.");
			textFieldBairro.requestFocus();
			return (false);
		}

		if (bairro.length() > 50) {
			JOptionPane.showInternalMessageDialog(null, "Campo Bairro máximo 50 caracteres.");
			textFieldBairro.setText("");
			textFieldBairro.requestFocus();
			return (false);
		}

		// Valida cidade
		if (cidade.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Cidade preenchimento obrigatório.");
			textFieldCidade.requestFocus();
			return (false);
		}

		if (cidade.length() > 50) {
			JOptionPane.showInternalMessageDialog(null, "Campo Cidade máximo 50 caracteres.");
			textFieldCidade.setText("");
			textFieldCidade.requestFocus();
			return (false);
		}

		// Valida UF
		if (uf.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo UF preenchimento obrigatório.");
			comboBox_Uf.requestFocus();
			return (false);
		}

		// Valida cep
		if (cep.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo CEP preenchimento obrigatório.");
			textFieldCep.requestFocus();
			return (false);
		}

		if (!ValidaEntrada.isNumero(cep)) {
			JOptionPane.showInternalMessageDialog(null, "Campo CEP só números.");
			textFieldCep.setText("");
			textFieldCep.requestFocus();
			return (false);
		}

		if (cep.length() != 8) {
			JOptionPane.showInternalMessageDialog(null, "Campo CEP deve ter 8 números.");
			textFieldCep.requestFocus();
			return (false);
		}

		// Valida telefone
		if (telefone.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Telefone preenchimento obrigatório.");
			textFieldTelefone.requestFocus();
			return (false);
		}

		if ((telefone.length() < 10) || (telefone.length() > 11)) {
			JOptionPane.showInternalMessageDialog(null, "Campo Telefone mínimo 10 e máximo 11 números.");
			textFieldTelefone.requestFocus();
			return (false);
		}

		if (!ValidaEntrada.isNumero(telefone)) {
			JOptionPane.showInternalMessageDialog(null, "Campo Telefone só números.");
			textFieldTelefone.setText("");
			textFieldTelefone.requestFocus();
			return (false);
		}

		// Valida email
		if (email.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Email preenchimento obrigatório.");
			textFieldEmail.requestFocus();
			return (false);
		}

		if ((email.length() < 5) || (email.length() > 100)) {
			JOptionPane.showInternalMessageDialog(null, "Campo Email mínimo 10 e máximo 100 caracteres.");
			textFieldEmail.setText("");
			textFieldEmail.requestFocus();
			return (false);
		}

		if (!email.matches(
				"^[A-za-z0-9_]+@+[A-za-z0-9]+\\.[A-za-z]{3}$|^[A-za-z0-9_]+@+[A-za-z0-9]+\\.[A-za-z]{3}\\.[A-Za-z]{2}$")) {
			JOptionPane.showInternalMessageDialog(null,
					"Email inválido! Formatos: [fulano@empresa.com] ou [fulano@empresa.com.br]");
			textFieldEmail.requestFocus();
			return (false);
		}

		return (true);

	}

	protected ArrayList<Cliente> InputDialog() {
		String[] options = { "Selecione uma opção", "Listar por Nome", "Listar Tudo", "Listar por CPF"};
		ImageIcon icon = new ImageIcon("src/icones/lupa.png");
		String n = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada", "Pesquisa",
				JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
		System.out.println(n);
		ArrayList<Cliente> pesquisar = new ArrayList<>();
		ClienteDao clientedao = new ClienteDao();
		String opcao = n;
		switch (opcao) {
		
		case "Selecione uma opção":
			JOptionPane.showMessageDialog(null, "Selecione uma opção válida!");
			break;
			
		case "Listar por CPF":
			try {
				String cpf = JOptionPane.showInputDialog("Informe o CPF: ");
				if (!cpf.equals("")) {
					pesquisar = clientedao.listarClientePorCpf(cpf);
				}else {
					JOptionPane.showMessageDialog(null, "CPF não pode estar vazio!");
				}
				
			} catch (Exception e) {
				JOptionPane.showInputDialog(e);
			}
			
			break;
		case "Listar por Nome":
			String nome = JOptionPane.showInputDialog("Informe o Nome: ");
			if (!nome.equals("")) {
				pesquisar = clientedao.listarClientePorNome(nome);
			}else {
				JOptionPane.showMessageDialog(null, "NOME não pode estar vazio!");
			}
			break;
		case "Listar Tudo":
			pesquisar = clientedao.listarTodosClientes();
			break;
		}
		return pesquisar;
	}

	private ArrayList<Cliente> listarTudo() {
		ClienteDao clientedao = new ClienteDao();

		ArrayList<Cliente> listaDeClientes = new ArrayList<>();
		listaDeClientes = clientedao.listarTodosClientes();

		return listaDeClientes;
	}

	private ArrayList<Cliente> listarPorNome(String nome) {
		ClienteDao clientedao = new ClienteDao();

		ArrayList<Cliente> listaDeClientes = new ArrayList<>();
		listaDeClientes = clientedao.listarClientePorNome(nome);

		return listaDeClientes;
	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = textFieldCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					textFieldCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					textFieldBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("endereço")) {
					textFieldEndereco.setText(element.getText());

				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("uf")) {
					comboBox_Uf.setSelectedItem(element.getText());
					System.out.println(element.getText());
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {

					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}

				textFieldEndereco.setText(tipoLogradouro + " " + logradouro);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	void limpaCampos() {
		textFieldCodCliente.setText("");
		textFieldNome.setText("");
		textFieldCpf.setText("");
		textFieldRg.setText("");
		textFieldEmail.setText("");
		textFieldEndereco.setText("");
		textFieldBairro.setText("");
		textFieldCidade.setText("");
		comboBox_Uf.setSelectedItem("");
		textFieldCep.setText("");
		textFieldTelefone.setText("");
	}
	
	void limpaCamposGrid() {
		textFieldCodCliente.setText("");
		textFieldNome.setText("");
		textFieldCpf.setText("");
		textFieldRg.setText("");
		textFieldEmail.setText("");
		textFieldEndereco.setText("");
		textFieldBairro.setText("");
		textFieldCidade.setText("");
		comboBox_Uf.setSelectedItem("");
		textFieldCep.setText("");
		textFieldTelefone.setText("");
		if (model.getRowCount() != 0) {
			model.setRowCount(0);
		}
	}
	
	void montaGrid( ArrayList<Cliente> listaCliente) {

		ArrayList<Cliente> listaDeClientes = new ArrayList<>();
		listaDeClientes = listaCliente;

		for (Cliente contador : listaDeClientes) {
			row[0] = contador.getCod_cliente();
			row[1] = contador.getNome_cliente();
			row[2] = contador.getCpf_cliente();
			row[3] = contador.getRg_cliente();
			row[4] = contador.getEmail_cliente();
			row[5] = contador.getTelefone_cliente();
			row[6] = contador.getEndereco_cliente();
			row[7] = contador.getBairro_cliente();
			row[8] = contador.getCidade_cliente();
			row[9] = contador.getUf_cliente();
			row[10] = contador.getCep_cliente();
			model.addRow(row);
		}
	}

}
