package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;
import java.beans.PropertyVetoException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
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
import Conexao.Dao.PedidoDao;
import Models.Cliente;
import Models.ItemPedido;
import Models.Pedido;
import Models.Produto;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;

public class Pedidos extends JInternalFrame {
	private JTextField textFieldCpf;
	private JTextField textFieldNomeCliente;
	private JTextField textFieldProdutoCod;
	private JTable table;
	private JTextField textFieldQuantidade;
	public JTextField textFieldNomeProduto;
	private JTextField textFieldQtdItens;
	private JTextField textFieldValorTotal;
	Pedido pedido = new Pedido();
	private JDesktopPane desktopPane; 
	public Produto teste;

	DefaultTableModel model;
	private JTextField textFieldCodCliente;
	private JTextField textFieldValor;
	private JTextField textFieldMarca;
	
	PesquisaProdutoPedido pesquisaProduto = new PesquisaProdutoPedido();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pedidos frame = new Pedidos();
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
	public Pedidos() {
		try {
			setMaximum(false);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setClosable(true);
		setFrameIcon(new ImageIcon(Pedidos.class.getResource("/Icones/relatorio.png")));
		setTitle("Gestão de Pedidos");
		setBounds(100, 100, 769, 417);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Pedidos/Orçamentos");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(250, 0, 259, 48);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 46, 739, 321);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblClienteCpf = new JLabel("Cliente CPF:");
		lblClienteCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClienteCpf.setBounds(23, 41, 94, 20);
		panel.add(lblClienteCpf);

		textFieldCpf = new JTextField();
		textFieldCpf.setEditable(false);
		textFieldCpf.setBackground(new Color(225, 225, 225));
		textFieldCpf.setBounds(99, 41, 120, 20);
		panel.add(textFieldCpf);
		textFieldCpf.setColumns(10);

		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setEditable(false);
		textFieldNomeCliente.setColumns(10);
		textFieldNomeCliente.setBackground(new Color(225, 225, 225));
		textFieldNomeCliente.setBounds(229, 41, 318, 20);
		panel.add(textFieldNomeCliente);

		textFieldProdutoCod = new JTextField();
		textFieldProdutoCod.setEditable(false);
		textFieldProdutoCod.setColumns(10);
		textFieldProdutoCod.setBackground(new Color(225, 225, 225));
		textFieldProdutoCod.setBounds(112, 102, 67, 20);
		panel.add(textFieldProdutoCod);
		
		JComboBox comboBoxCondicaoPagamento = new JComboBox();
		comboBoxCondicaoPagamento.setModel(new DefaultComboBoxModel(new String[] {"", "Dinheiro", "Pix", "Débito", "Credito"}));
		comboBoxCondicaoPagamento.setBounds(174, 7, 94, 22);
		panel.add(comboBoxCondicaoPagamento);
		
		JLabel lblNewLabel_1 = new JLabel("Condição de Pagamento");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 11, 173, 14);
		panel.add(lblNewLabel_1);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidade.setBounds(467, 72, 80, 20);
		panel.add(lblQuantidade);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 133, 693, 110);
		panel.add(scrollPane);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Pedido");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pedido.setTipo_pedido("P");
			}
		});
		
		JRadioButton rdbtnOramento = new JRadioButton("Orçamento");
		rdbtnOramento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					pedido.setTipo_pedido("O");
			}
		});
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int contador = table.getSelectedRow();
				textFieldProdutoCod.setText(model.getValueAt(contador, 0).toString());
				textFieldQuantidade.setText(model.getValueAt(contador, 1).toString());
			
			}
		});
		
		model = new DefaultTableModel();
		Object[] colunn = {"Codigo","Produto", "Quantidade", "Valor"};
		Object[] row = new Object[4];
		model.setColumnIdentifiers(colunn);
		table.setModel(model);
		
		table.setBackground(UIManager.getColor("Button.light"));
		table.setForeground(SystemColor.activeCaption);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBackground(new Color(225, 225, 225));
		textFieldQuantidade.setBounds(557, 73, 54, 20);
		panel.add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<ItemPedido> listaItensPedido = new ArrayList<>();
				PedidoDao pedidodao = new PedidoDao();				
				
				pedido.setData_pedido(LocalDate.now().toString());
				pedido.setClientes_cod_cliente(textFieldCodCliente.getText());
				pedido.setCondicao_pagamento_pedido(comboBoxCondicaoPagamento.getSelectedItem().toString());
				pedido.setTipo_pedido(pedido.getTipo_pedido());
				int contador = model.getRowCount();
				
				for(int teste=0; teste < contador; teste++) {
					
					ItemPedido itemPedido = new ItemPedido();
					
					itemPedido.setProdutos_cod_produto(model.getValueAt(teste, 0).toString());
					itemPedido.setQuantidade_item(model.getValueAt(teste, 2).toString());
					
					listaItensPedido.add(itemPedido);
				}
				
				pedidodao.inserirPedido(pedido, listaItensPedido);
				
				JOptionPane.showMessageDialog(btnGravar, "Cliente cadastrado com sucesso!");
			}
		});
		btnGravar.setBounds(259, 287, 89, 23);
		panel.add(btnGravar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldCpf.setText("");
				textFieldNomeCliente.setText("");
				textFieldProdutoCod.setText("");
				textFieldQuantidade.setText("");
				textFieldNomeProduto.setText("");
				textFieldQtdItens.setText("");
				textFieldValorTotal.setText("");

			}
		});
		btnLimpar.setBounds(382, 287, 89, 23);
		panel.add(btnLimpar);

		rdbtnNewRadioButton.setBounds(290, 7, 90, 23);
		panel.add(rdbtnNewRadioButton);

		rdbtnOramento.setBounds(382, 7, 109, 23);
		panel.add(rdbtnOramento);

		JLabel lblProduto = new JLabel("Nome Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setBounds(23, 72, 94, 20);
		panel.add(lblProduto);

		textFieldNomeProduto = new JTextField();
		textFieldNomeProduto.setEditable(false);
		textFieldNomeProduto.setColumns(10);
		textFieldNomeProduto.setBackground(new Color(225, 225, 225));
		textFieldNomeProduto.setBounds(127, 72, 330, 20);
		panel.add(textFieldNomeProduto);

		JLabel lblQtdItens = new JLabel("Qtd Itens:");
		lblQtdItens.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQtdItens.setBounds(23, 254, 94, 20);
		panel.add(lblQtdItens);

		textFieldQtdItens = new JTextField();
		textFieldQtdItens.setColumns(10);
		textFieldQtdItens.setBackground(new Color(225, 225, 225));
		textFieldQtdItens.setBounds(99, 254, 46, 20);
		panel.add(textFieldQtdItens);

		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorTotal.setBounds(546, 254, 80, 20);
		panel.add(lblValorTotal);

		textFieldValorTotal = new JTextField();
		textFieldValorTotal.setColumns(10);
		textFieldValorTotal.setBackground(new Color(225, 225, 225));
		textFieldValorTotal.setBounds(627, 255, 89, 20);
		panel.add(textFieldValorTotal);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setBounds(557, 41, 54, 20);
		panel.add(lblCodigo);
		
		textFieldCodCliente = new JTextField();
		textFieldCodCliente.setEditable(false);
		textFieldCodCliente.setColumns(10);
		textFieldCodCliente.setBackground(new Color(225, 225, 225));
		textFieldCodCliente.setBounds(608, 41, 46, 20);
		panel.add(textFieldCodCliente);
		
		JButton btnPesquisaCliente = new JButton("Pesquisar Cliente");
		btnPesquisaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente cliente = new Cliente();
				ClienteDao clienteDao = new ClienteDao();
				
				String cpf = JOptionPane.showInputDialog("Informe o CPF do Cliente: ");
				cliente = clienteDao.listarClientePorCpf(cpf);
				
				textFieldCodCliente.setText(cliente.getCod_cliente());
				textFieldNomeCliente.setText(cliente.getNome_cliente());
				textFieldCpf.setText(cliente.getCpf_cliente());				
				
			}
		});
		btnPesquisaCliente.setBounds(672, 41, 46, 23);
		panel.add(btnPesquisaCliente);
		
		JButton btnInserir = new JButton("+");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			

					int contador = model.getRowCount()+1;
					textFieldQtdItens.setText(String.valueOf(contador));

					row[0] = textFieldProdutoCod.getText();
					row[1] = textFieldNomeProduto.getText();
					row[2] = textFieldQuantidade.getText();
					row[3] = textFieldMarca.getText();
					row[4] = textFieldValor.getText();

					model.addRow(row);
			}
		});
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInserir.setBounds(672, 72, 44, 23);
		panel.add(btnInserir);
		
		JButton btnRemover = new JButton("-");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int contador = model.getRowCount()-1;
				textFieldQtdItens.setText(String.valueOf(contador));

				row[0] = textFieldProdutoCod.getText();
				row[1] = textFieldNomeProduto.getText();
				row[2] = textFieldQuantidade.getText();
				row[3] = textFieldMarca.getText();
				row[4] = textFieldValor.getText();

				model.removeRow(contador);;
				
			}
		});
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemover.setBounds(618, 72, 44, 23);
		panel.add(btnRemover);
		
		JButton btnBuscaProdutos = new JButton("Pesquisar Produtos");
		btnBuscaProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				pesquisaProduto.main(null);
							
			}

		});
		btnBuscaProdutos.setBounds(586, 101, 80, 23);
		panel.add(btnBuscaProdutos);
		
		JLabel lblCodProduto = new JLabel("Cod Produto:");
		lblCodProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodProduto.setBounds(23, 102, 94, 20);
		panel.add(lblCodProduto);
		
		JLabel lblValorUnitrio = new JLabel("Valor Unitário:");
		lblValorUnitrio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorUnitrio.setBounds(189, 102, 94, 20);
		panel.add(lblValorUnitrio);
		
		textFieldValor = new JTextField();
		textFieldValor.setEditable(false);
		textFieldValor.setColumns(10);
		textFieldValor.setBackground(new Color(225, 225, 225));
		textFieldValor.setBounds(278, 102, 94, 20);
		panel.add(textFieldValor);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMarca.setBounds(382, 102, 94, 20);
		panel.add(lblMarca);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setEditable(false);
		textFieldMarca.setColumns(10);
		textFieldMarca.setBackground(new Color(225, 225, 225));
		textFieldMarca.setBounds(425, 102, 151, 20);
		panel.add(textFieldMarca);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto p = pesquisaProduto.getBuscaProduto();
				p = pesquisaProduto.getBuscaProduto();
				System.out.println(p);
			}
		});
		btnOk.setBounds(672, 101, 44, 23);
		panel.add(btnOk);

	}
	
	
}
