package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import Conexao.Dao.ProdutoDao;
import Models.Produto;

public class Produtos extends JInternalFrame {
	private JTextField textFieldCod;
	private JTextField textFieldProduto;
	private JTextField textFieldQuantidade;
	private JTextField textFieldCompra;
	private JTextField textFieldVenda;
	private JTable table;
	private JTextField textFieldViewMarca;
	private JTextField textFieldDescricao;
	JComboBox comboBox_CodMarca = new JComboBox();;

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
		setBounds(100, 100, 769, 417);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Produtos");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(302, 0, 154, 48);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 46, 739, 321);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCodProduto = new JLabel("Cod:");
		lblCodProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodProduto.setBounds(23, 15, 46, 20);
		panel.add(lblCodProduto);

		textFieldCod = new JTextField();
		textFieldCod.setEditable(false);
		textFieldCod.setBackground(new Color(225, 225, 225));
		textFieldCod.setBounds(61, 15, 86, 20);
		panel.add(textFieldCod);
		textFieldCod.setColumns(10);

		textFieldProduto = new JTextField();
		textFieldProduto.setColumns(10);
		textFieldProduto.setBackground(new Color(225, 225, 225));
		textFieldProduto.setBounds(240, 13, 288, 20);
		panel.add(textFieldProduto);

		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setBounds(179, 15, 63, 20);
		panel.add(lblProduto);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidade.setBounds(538, 15, 89, 18);
		panel.add(lblQuantidade);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setColumns(10);
		textFieldQuantidade.setBackground(new Color(225, 225, 225));
		textFieldQuantidade.setBounds(627, 13, 89, 20);
		panel.add(textFieldQuantidade);

		JLabel lblPrecoC = new JLabel("Preço Compra:");
		lblPrecoC.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecoC.setBounds(23, 60, 104, 20);
		panel.add(lblPrecoC);

		textFieldCompra = new JTextField();
		textFieldCompra.setColumns(10);
		textFieldCompra.setBackground(new Color(225, 225, 225));
		textFieldCompra.setBounds(116, 60, 77, 20);
		panel.add(textFieldCompra);

		JLabel lblPrecoV = new JLabel("Preço Venda:");
		lblPrecoV.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecoV.setBounds(203, 60, 89, 20);
		panel.add(lblPrecoV);

		textFieldVenda = new JTextField();
		textFieldVenda.setColumns(10);
		textFieldVenda.setBackground(new Color(225, 225, 225));
		textFieldVenda.setBounds(290, 60, 77, 20);
		panel.add(textFieldVenda);

		JLabel lblCodMarca = new JLabel("Cod_Marca:");
		lblCodMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodMarca.setBounds(420, 60, 89, 20);
		panel.add(lblCodMarca);

		
		comboBox_CodMarca.setModel(new DefaultComboBoxModel(new String[] {"", "001", "002", "003", "004", "005", "006"}));
		comboBox_CodMarca.setBounds(506, 60, 46, 22);
		panel.add(comboBox_CodMarca);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 124, 693, 147);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(UIManager.getColor("Button.light"));
		table.setForeground(SystemColor.activeCaption);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		textFieldViewMarca = new JTextField();
		textFieldViewMarca.setEditable(false);
		textFieldViewMarca.setBackground(new Color(225, 225, 225));
		textFieldViewMarca.setBounds(587, 61, 129, 20);
		panel.add(textFieldViewMarca);
		textFieldViewMarca.setColumns(10);

		JButton btnNewButton = new JButton("Gravar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xmarca=comboBox_CodMarca.getSelectedItem().toString();
				
				System.out.println("Conteudo do campo: ["+xmarca+"]\n");
				
				if(validaCampos() == false) {
					return;
				}
				
				Produto produto = new Produto();
				ProdutoDao produtoDao = new ProdutoDao();

				produto.setNome_produto(textFieldProduto.getText());
				produto.setQuantidade_produto(textFieldQuantidade.getText());
				produto.setValor_compra_produto(textFieldCompra.getText());
				produto.setValor_venda_produto(textFieldVenda.getText());
				produto.setDescricao_produto(textFieldDescricao.getText());
				produto.setCod_marca_pedidoString(comboBox_CodMarca.getSelectedItem().toString());
				
				produtoDao.inserirProduto(produto);		
				
			}
		});
		btnNewButton.setBounds(222, 287, 89, 23);
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
			}
		});
		btnLimpar.setBounds(402, 287, 89, 23);
		panel.add(btnLimpar);

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescricao.setBounds(23, 93, 104, 20);
		panel.add(lblDescricao);

		textFieldDescricao = new JTextField();
		textFieldDescricao.setColumns(10);
		textFieldDescricao.setBackground(new Color(225, 225, 225));
		textFieldDescricao.setBounds(92, 93, 624, 20);
		panel.add(textFieldDescricao);

	}

	public boolean validaCampos() {
		String codigo = textFieldCod.getText();
		String produto = textFieldProduto.getText();
		String quantidade = textFieldQuantidade.getText();
		String precoCompra = textFieldCompra.getText();
		precoCompra = precoCompra.replace(",", ".");
		String precoVenda = textFieldVenda.getText();
		precoVenda = precoVenda.replace(",", ".");
		String descricao=textFieldDescricao.getText();
		
		String marca = comboBox_CodMarca.getSelectedItem().toString();

//		// Valida código produto
//		if (codigo.equals("")) {
//			JOptionPane.showInternalMessageDialog(null, "Campo Codigo é preenchimento obrigatório.");
//			textFieldCod.requestFocus();
//			return (false);
//		}

		if (ValidaEntrada.temLetra(codigo)) {
			JOptionPane.showInternalMessageDialog(null, "Campo Codigo somente números.");
			textFieldCod.setText("");
			textFieldCod.requestFocus();
			return (false);
		}

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
		
		//Valida campo descrição
		if (descricao.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Descrição é preenchimento obrigatório.");
			textFieldDescricao.requestFocus();
			return (false);
		}
		

		return (true);

	}

}
