package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Conexao.Dao.ClienteDao;
import Conexao.Dao.MarcaDao;
import Conexao.Dao.ProdutoDao;
import Conexao.Dao.MarcaDao;
import Models.Cliente;
import Models.Marca;
import Models.Produto;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Insets;

public class Produtos extends JInternalFrame {
	private JTextField textFieldCod;
	private JTextField textFieldProduto;
	private JTextField textFieldQuantidade;
	private JTextField textFieldCompra;
	private JTextField textFieldVenda;
	private JTable table;
	private static JTextField textFieldViewMarca;
	private JTextField textFieldDescricao;
	JComboBox comboBox_CodMarca = new JComboBox();
	

	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produtos frame = new Produtos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Produtos() {
		setClosable(true);
		setFrameIcon(new ImageIcon(Produtos.class.getResource("/Icones/produtos.png")));
		setTitle("Gestão de Produtos");
		setBounds(100, 100, 770, 538);
		getContentPane().setLayout(null);
		textFieldViewMarca = new JTextField();
		
		JLabel lblNewLabel = new JLabel("Produtos");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(302, 0, 154, 48);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 46, 739, 451);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCodProduto = new JLabel("Cod:");
		lblCodProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodProduto.setBounds(23, 23, 46, 20);
		panel.add(lblCodProduto);

		textFieldCod = new JTextField();
		textFieldCod.setEditable(false);
		textFieldCod.setBackground(new Color(225, 225, 225));
		textFieldCod.setBounds(61, 23, 86, 20);
		panel.add(textFieldCod);
		textFieldCod.setColumns(10);

		textFieldProduto = new JTextField();
		textFieldProduto.setColumns(10);
		textFieldProduto.setBackground(new Color(225, 225, 225));
		textFieldProduto.setBounds(240, 23, 288, 20);
		panel.add(textFieldProduto);

		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setBounds(179, 23, 63, 20);
		panel.add(lblProduto);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidade.setBounds(538, 25, 89, 18);
		panel.add(lblQuantidade);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setColumns(10);
		textFieldQuantidade.setBackground(new Color(225, 225, 225));
		textFieldQuantidade.setBounds(627, 23, 89, 20);
		panel.add(textFieldQuantidade);

		JLabel lblPrecoC = new JLabel("Preço Compra:");
		lblPrecoC.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecoC.setBounds(23, 54, 104, 20);
		panel.add(lblPrecoC);

		textFieldCompra = new JTextField();
		textFieldCompra.setColumns(10);
		textFieldCompra.setBackground(new Color(225, 225, 225));
		textFieldCompra.setBounds(116, 54, 77, 20);
		panel.add(textFieldCompra);

		JLabel lblPrecoV = new JLabel("Preço Venda:");
		lblPrecoV.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecoV.setBounds(203, 54, 89, 20);
		panel.add(lblPrecoV);

		textFieldVenda = new JTextField();
		textFieldVenda.setColumns(10);
		textFieldVenda.setBackground(new Color(225, 225, 225));
		textFieldVenda.setBounds(295, 54, 77, 20);
		panel.add(textFieldVenda);

		JLabel lblCodMarca = new JLabel("Cod_Marca:");
		lblCodMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodMarca.setBounds(377, 54, 89, 20);
		panel.add(lblCodMarca);
		comboBox_CodMarca.addItemListener(new ItemListener() {
			//@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {

					String descricao_marca = "";
					String codigo_marca = "";

					MarcaDao marcadao = new MarcaDao();
					codigo_marca = comboBox_CodMarca.getSelectedItem().toString();

					if (codigo_marca.equals("")) {
						textFieldViewMarca.setText("");
					}
					else {
						
						descricao_marca = marcadao.buscaDescricaoCodMarca(codigo_marca);
						textFieldViewMarca.setText(descricao_marca);
					}

				}

			}

		});
		comboBox_CodMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		comboBox_CodMarca.setBounds(463, 52, 46, 22);
		panel.add(comboBox_CodMarca);

		// Carrega itens no combo referente Marca
		//Marca marca = new Marca();
		MarcaDao marcadao = new MarcaDao();

		marcadao.listarTodasMarcas();

		ArrayList<Marca> listaDeMarcas = new ArrayList<>();
		listaDeMarcas = marcadao.listarTodasMarcas();
		comboBox_CodMarca.addItem("");

		for (Marca contador : listaDeMarcas) {
			comboBox_CodMarca.addItem(contador.getCodigoMarca());
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 158, 693, 248);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MarcaDao marcadao = new MarcaDao();
				int contador = table.getSelectedRow();
				String descricao_marca="";
				descricao_marca = marcadao.buscaDescricaoCodMarca(model.getValueAt(contador,5).toString());
				

				
				textFieldCod.setText(model.getValueAt(contador, 0).toString());
				textFieldProduto.setText(model.getValueAt(contador, 1).toString());
				textFieldQuantidade.setText(model.getValueAt(contador,2).toString());
				textFieldCompra.setText(model.getValueAt(contador,3).toString());
				textFieldVenda.setText(model.getValueAt(contador,4).toString());
				comboBox_CodMarca.setSelectedItem(model.getValueAt(contador,5).toString());
				textFieldDescricao.setText(model.getValueAt(contador,6).toString());
				textFieldViewMarca.setText(descricao_marca);
				
				
				
				
				
			}
		});

		model = new DefaultTableModel();
		Object[] colunn = { "Codigo", "Produto", "Quantidade", "Preço compra", "Preço venda", "Código marca",
				"Descrição" };
		Object[] row = new Object[7];
		model.setColumnIdentifiers(colunn);
		table.setModel(model);

		// Retirada da cor da fonte apresentada no grid da tela estava muito clara.
		// Retirando as linhas ficou fonte black
		// table.setBackground(UIManager.getColor("Button.light"));
		// table.setForeground(SystemColor.activeCaption);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		textFieldViewMarca = new JTextField();
		textFieldViewMarca.setEditable(false);
		textFieldViewMarca.setBackground(new Color(225, 225, 225));
		textFieldViewMarca.setBounds(519, 54, 197, 20);
		panel.add(textFieldViewMarca);
		textFieldViewMarca.setColumns(10);

		JButton btnNewButton = new JButton("Gravar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xmarca = comboBox_CodMarca.getSelectedItem().toString();

				System.out.println("Conteudo do campo: [" + xmarca + "]\n");

				if (validaCampos() == false) {
					return;
				}

				Produto produto = new Produto();
				ProdutoDao produtoDao = new ProdutoDao();

				produto.setNome_produto(textFieldProduto.getText());
				produto.setQuantidade_produto(textFieldQuantidade.getText());
				produto.setValor_compra_produto(textFieldCompra.getText());
				produto.setValor_venda_produto(textFieldVenda.getText());
				produto.setDescricao_produto(textFieldDescricao.getText());
				produto.setCod_marca_produto(comboBox_CodMarca.getSelectedItem().toString());

				produtoDao.inserirProduto(produto);
				
				//Limpa os campos após gravação do produto
				textFieldCod.setText("");
				textFieldProduto.setText("");
				textFieldQuantidade.setText("");
				textFieldCompra.setText("");
				textFieldVenda.setText("");
				comboBox_CodMarca.setSelectedIndex(-1);
				textFieldDescricao.setText("");
				textFieldViewMarca.setText("");

			}
		});
		btnNewButton.setBounds(76, 417, 89, 23);
		panel.add(btnNewButton);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textFieldCod.setText("");
				textFieldProduto.setText("");
				textFieldQuantidade.setText("");
				textFieldCompra.setText("");
				textFieldVenda.setText("");
				textFieldDescricao.setText("");
				comboBox_CodMarca.setSelectedItem("");
				((DefaultTableModel) model).setRowCount(0);

			}
		});
		btnLimpar.setBounds(571, 417, 89, 23);
		panel.add(btnLimpar);

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescricao.setBounds(23, 89, 104, 20);
		panel.add(lblDescricao);

		textFieldDescricao = new JTextField();
		textFieldDescricao.setColumns(10);
		textFieldDescricao.setBackground(new Color(225, 225, 225));
		textFieldDescricao.setBounds(92, 89, 624, 20);
		panel.add(textFieldDescricao);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBackground(new Color(192, 192, 192));
		btnPesquisar.setIcon(new ImageIcon("C:\\Users\\weslley.leles\\Pictures\\icones\\lupinha.PNG"));
		btnPesquisar.setMargin(new Insets(2, 3, 2, 3));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}

				ArrayList<Produto> listaDeProdutos = new ArrayList<>();
				listaDeProdutos = InputDialog();

				for (Produto contador : listaDeProdutos) {

					row[0] = contador.getCod_produto();
					row[1] = contador.getNome_produto();
					row[2] = contador.getQuantidade_produto();
					row[3] = contador.getValor_compra_produto();
					row[4] = contador.getValor_venda_produto();
					row[5] = contador.getCod_marca_produto();
					row[6] = contador.getDescricao_produto();

					model.addRow(row);
				}

			}
		});
		btnPesquisar.setIcon(new ImageIcon(Produtos.class.getResource("/Icones/lupa.png")));
		btnPesquisar.setBounds(23, 120, 35, 30);
		panel.add(btnPesquisar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldCod.getText().equals("")){
					JOptionPane.showInternalMessageDialog(null, "Nenhum produto selecionado.");
					return;
				}
				
				if (validaCampos() == false) {
					return;
				}
				ProdutoDao produtodao = new ProdutoDao();
				Produto produto = new Produto();

				produto.setCod_produto(textFieldCod.getText());
				produto.setNome_produto(textFieldProduto.getText());
				produto.setQuantidade_produto(textFieldQuantidade.getText());
				produto.setValor_compra_produto(textFieldCompra.getText());
				produto.setValor_venda_produto(textFieldVenda.getText());
				produto.setDescricao_produto(textFieldDescricao.getText());
				produto.setCod_marca_produto(comboBox_CodMarca.getSelectedItem().toString());

				if (JOptionPane.showConfirmDialog(null, "Confirma alteração no cadastro do produto?",
						"SIM", JOptionPane.YES_NO_OPTION) == 0) {
					produtodao.alterarProdutoPorId(produto);
				}
			}
		});
		btnAlterar.setBounds(241, 417, 89, 23);
		panel.add(btnAlterar);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldCod.getText().equals("")){
					JOptionPane.showInternalMessageDialog(null, "Nenhum produto selecionado.");
					return;
				}
				ProdutoDao produtodao = new ProdutoDao();
				Produto produto = new Produto();
				produto.setCod_produto(textFieldCod.getText());

				if (JOptionPane.showConfirmDialog(null, "Confirma esclusão do Produto?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					produtodao.deletarProdutoPorId(produto);
				}
			}
		});
		btnDeletar.setBounds(406, 417, 89, 23);
		panel.add(btnDeletar);

	}

	public boolean validaCampos() {
		String codigo = textFieldCod.getText();
		String produto = textFieldProduto.getText();
		String quantidade = textFieldQuantidade.getText();
		String precoCompra = textFieldCompra.getText();
		precoCompra = precoCompra.replace(",", ".");
		String precoVenda = textFieldVenda.getText();
		precoVenda = precoVenda.replace(",", ".");
		String descricao = textFieldDescricao.getText();

		String marca = comboBox_CodMarca.getSelectedItem().toString();

//		// Valida código produto
//		if (codigo.equals("")) {
//			JOptionPane.showInternalMessageDialog(null, "Campo Codigo é preenchimento obrigatório.");
//			textFieldCod.requestFocus();
//			return (false);
//		}

//		if (ValidaEntrada.temLetra(codigo)) {
//			JOptionPane.showInternalMessageDialog(null, "Campo Codigo somente números.");
//			textFieldCod.setText("");
//			textFieldCod.requestFocus();
//			return (false);
//		}

		// Validação nome produto
		if (produto.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Produto preenchimento obrigatório.");
			textFieldProduto.requestFocus();
			return (false);
		}

		if ((produto.length() < 2) || (produto.length() > 100)) {
			JOptionPane.showInternalMessageDialog(null, "Campo Produto máximo mínimo 2 e máximo 100 caracteres.");
			textFieldProduto.requestFocus();
			return (false);
		}

		// Valida quantidade
		if (quantidade.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Quantidade é preenchimento obrigatório.");
			textFieldQuantidade.requestFocus();
			return (false);
		}

		if (!ValidaEntrada.isInt(quantidade)) {
			JOptionPane.showInternalMessageDialog(null, "Quantidade somente números.");
			textFieldQuantidade.setText("");
			textFieldQuantidade.requestFocus();
			return (false);
		}

		if (Integer.parseInt(quantidade) < 0) {
			JOptionPane.showInternalMessageDialog(null, "Quantidade não pode ser negativa.");
			textFieldQuantidade.setText("");
			textFieldQuantidade.requestFocus();
			return (false);
		}

		// Valida preço de compra
		if (precoCompra.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Preço Compra é preenchimento obrigatório.");
			textFieldCompra.requestFocus();
			return (false);
		}

		if (!ValidaEntrada.isFloat(precoCompra)) {
			JOptionPane.showInternalMessageDialog(null, "Preço Compra somente número.");
			textFieldCompra.setText("");
			textFieldCompra.requestFocus();
			return (false);
		}

		// Valida preço de venda
		if (precoVenda.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Preço Venda é preenchimento obrigatório.");
			textFieldVenda.requestFocus();
			return (false);
		}

		if (!ValidaEntrada.isFloat(precoVenda)) {
			JOptionPane.showInternalMessageDialog(null, "Preço Compra somente número.");
			textFieldVenda.setText("");
			textFieldVenda.requestFocus();
			return (false);
		}

		// Valida marca
		if (marca.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Marca preenchimento obrigatório.");
			comboBox_CodMarca.requestFocus();
			return (false);
		}

		// Valida campo descrição
		if (descricao.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Descrição é preenchimento obrigatório.");
			textFieldDescricao.requestFocus();
			return (false);
		}

		return (true);

	}
	protected ArrayList<Produto> InputDialog() {
		String[] options = { null, "Listar por Nome", "Listar Tudo" };
		ImageIcon icon = new ImageIcon("src/icones/lupa.png");
		String n = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada", "Pesquisa",
				JOptionPane.QUESTION_MESSAGE, icon, options, options[2]);
		System.out.println(n);
		// frmPrincipal principal = new frmPrincipal();
		ArrayList<Produto> pesquisar = new ArrayList<>();
		ProdutoDao produtodao = new ProdutoDao();
		String opcao = n;
		switch (opcao) {

		case "Listar por Nome":
			String nome = JOptionPane.showInputDialog("Informe o Nome: ");

			pesquisar = produtodao.listarProdutoPorNome(nome);

			break;
		case "Listar Tudo":

			pesquisar = produtodao.listarTodosProdutos();

			break;

		}
		return pesquisar;
	}
}

