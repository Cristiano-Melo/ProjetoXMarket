package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.beans.PropertyVetoException;
import java.sql.Date;
import java.time.LocalDate;
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
import Conexao.Dao.PedidoDao;
import Models.Cliente;
import Models.ItemPedido;
import Models.Pedido;

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
	private JTextField textFieldCampoDescricaoCod;
	private JTextField textFieldQtdItens;
	private JTextField textFieldValorTotal;

	DefaultTableModel model;
	private JTextField textFieldCodCliente;
	
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
		textFieldCpf.setBackground(new Color(225, 225, 225));
		textFieldCpf.setBounds(99, 41, 120, 20);
		panel.add(textFieldCpf);
		textFieldCpf.setColumns(10);

		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setColumns(10);
		textFieldNomeCliente.setBackground(new Color(225, 225, 225));
		textFieldNomeCliente.setBounds(229, 41, 318, 20);
		panel.add(textFieldNomeCliente);

		textFieldProdutoCod = new JTextField();
		textFieldProdutoCod.setColumns(10);
		textFieldProdutoCod.setBackground(new Color(225, 225, 225));
		textFieldProdutoCod.setBounds(109, 72, 46, 20);
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
		lblQuantidade.setBounds(479, 71, 80, 20);
		panel.add(lblQuantidade);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 101, 693, 142);
		panel.add(scrollPane);

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
		Object[] colunn = {"Codigo","Produto", "Quantidade"};
		Object[] row = new Object[3];
		model.setColumnIdentifiers(colunn);
		table.setModel(model);
		
		table.setBackground(UIManager.getColor("Button.light"));
		table.setForeground(SystemColor.activeCaption);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBackground(new Color(225, 225, 225));
		textFieldQuantidade.setBounds(559, 72, 54, 20);
		panel.add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pedido pedido = new Pedido();
				ArrayList<ItemPedido> listaItensPedido = new ArrayList<>();
				PedidoDao pedidodao = new PedidoDao();				
				
				pedido.setData_pedido(LocalDate.now().toString());
				pedido.setClientes_cod_cliente(textFieldCpf.getText());
				pedido.setCondicao_pagamento_pedido(comboBoxCondicaoPagamento.getSelectedItem().toString());
				pedido.setTipo_pedido("P");
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
				textFieldCampoDescricaoCod.setText("");
				textFieldQtdItens.setText("");
				textFieldValorTotal.setText("");

			}
		});
		btnLimpar.setBounds(382, 287, 89, 23);
		panel.add(btnLimpar);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Pedido");
		rdbtnNewRadioButton.setBounds(290, 7, 90, 23);
		panel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnOramento = new JRadioButton("Orçamento");
		rdbtnOramento.setBounds(382, 7, 109, 23);
		panel.add(rdbtnOramento);

		JLabel lblProduto = new JLabel("Produto Cod:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setBounds(23, 72, 94, 20);
		panel.add(lblProduto);

		textFieldCampoDescricaoCod = new JTextField();
		textFieldCampoDescricaoCod.setColumns(10);
		textFieldCampoDescricaoCod.setBackground(new Color(225, 225, 225));
		textFieldCampoDescricaoCod.setBounds(165, 72, 312, 20);
		panel.add(textFieldCampoDescricaoCod);

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
		
		JButton btnInserir_1 = new JButton("+");
		btnInserir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInserir_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInserir_1.setBounds(672, 72, 44, 23);
		panel.add(btnInserir_1);
		
		JButton btnInserir_1_1 = new JButton("-");
		btnInserir_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInserir_1_1.setBounds(618, 72, 44, 23);
		panel.add(btnInserir_1_1);

	}
}
