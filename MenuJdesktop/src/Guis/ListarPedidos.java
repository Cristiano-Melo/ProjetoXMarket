package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.beans.PropertyVetoException;
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
import Conexao.Dao.ProdutoDao;
import Models.Cliente;
import Models.ItemPedido;
import Models.ListaPedido;
import Models.Pedido;
import Models.Produto;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ListarPedidos extends JInternalFrame {
	private JTextField textFieldCodPedidos;
	Pedido pedido = new Pedido();
	public Produto teste;
	double calculaValorTotal = 0;

	private JTable table;
	private String tipoPedido = "";
	
	DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarPedidos frame = new ListarPedidos();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ListarPedidos() {
		try {
			setMaximum(false);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		setClosable(true);
		setFrameIcon(new ImageIcon(ListarPedidos.class.getResource("/Icones/relatorio.png")));
		setTitle("Gestão de Pedidos");
		setBounds(100, 100, 770, 538);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Pedidos/Orçamentos");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(250, 0, 259, 48);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 46, 739, 455);
		getContentPane().add(panel);
		panel.setLayout(null);

		textFieldCodPedidos = new JTextField();
		textFieldCodPedidos.setBackground(new Color(225, 225, 225));
		textFieldCodPedidos.setBounds(71, 10, 67, 20);
		panel.add(textFieldCodPedidos);
		textFieldCodPedidos.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 41, 693, 331);
		panel.add(scrollPane);
		
		table = new JTable();
		
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int contador1 = table.getSelectedRow();
				textFieldCodPedidos.setText(model.getValueAt(contador1, 0).toString());

			}
		});		
		
		model = new DefaultTableModel();
		Object[] colunn = {"Codigo","Data","Condição","Cod. Cliente","Cliente","Cod. Produto", "Produto", "Qtde.","Valor Item"};
		Object[] row = new Object[9];
		model.setColumnIdentifiers(colunn);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(2).setMaxWidth(80);
		table.getColumnModel().getColumn(3).setMaxWidth(50);
		table.getColumnModel().getColumn(5).setMaxWidth(50);
		table.getColumnModel().getColumn(7).setMaxWidth(50);
		table.getColumnModel().getColumn(8).setMaxWidth(80);
		
//		table.setBackground(UIManager.getColor("Button.light"));
//		table.setForeground(SystemColor.activeCaption);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setBounds(23, 10, 54, 20);
		panel.add(lblCodigo);
		
		JButton btnListar = new JButton("Listar por Data");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PedidoDao pedidoDao = new PedidoDao();
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}	
				
				String data = JOptionPane.showInputDialog("Informe a Data: ");
				
				ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();
				listaDePedidos = pedidoDao.listarPedidoPorData(data);

				for (ListaPedido contador : listaDePedidos) {
					row[0] = contador.getCod_pedido();
					row[1] = contador.getData_pedido();
					row[2] = contador.getCondicao_pagamento_pedido();
					row[3] = contador.getClientes_cod_cliente();
					row[4] = contador.getNome_cliente();
					row[5] = contador.getCod_produto();
					row[6] = contador.getNome_produto();
					row[7] = contador.getQuantidade_item();
					row[8] = contador.getPreco_total_item();
					model.addRow(row);
				}
				
			}
		});
		btnListar.setBounds(19, 421, 139, 23);
		panel.add(btnListar);
		
		JButton btnListarPorCliente = new JButton("Listar por Cliente");
		btnListarPorCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoDao pedidoDao = new PedidoDao();
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}	
				
				String nome = JOptionPane.showInputDialog("Informe o Nome do Cliente: ");
				
				ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();
				listaDePedidos = pedidoDao.listarPedidoPorNomeCliente(nome);

				for (ListaPedido contador : listaDePedidos) {
					row[0] = contador.getCod_pedido();
					row[1] = contador.getData_pedido();
					row[2] = contador.getCondicao_pagamento_pedido();
					row[3] = contador.getClientes_cod_cliente();
					row[4] = contador.getNome_cliente();
					row[5] = contador.getCod_produto();
					row[6] = contador.getNome_produto();
					row[7] = contador.getQuantidade_item();
					row[8] = contador.getPreco_total_item();
					model.addRow(row);
				}
				
			}
		});
		
		btnListarPorCliente.setBounds(177, 421, 167, 23);
		panel.add(btnListarPorCliente);
		
		JButton btnListarPorPagamento = new JButton("Listar por Pagamento");
		btnListarPorPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}	
				ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();
				
				listaDePedidos = InputDialog();
				
				for (ListaPedido contador : listaDePedidos) {
					row[0] = contador.getCod_pedido();
					row[1] = contador.getData_pedido();
					row[2] = contador.getCondicao_pagamento_pedido();
					row[3] = contador.getClientes_cod_cliente();
					row[4] = contador.getNome_cliente();
					row[5] = contador.getCod_produto();
					row[6] = contador.getNome_produto();
					row[7] = contador.getQuantidade_item();
					row[8] = contador.getPreco_total_item();
					model.addRow(row);
				}
				
			}
		});
		btnListarPorPagamento.setBounds(363, 421, 167, 23);
		panel.add(btnListarPorPagamento);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoDao pedidodao = new PedidoDao();
				Pedido pedido = new Pedido();
				pedido.setCod_pedido(textFieldCodPedidos.getText());

				if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Cliente?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					pedidodao.excluirPedido(pedido);
				}
				
			}
		});
		btnDeletar.setBounds(549, 421, 167, 23);
		panel.add(btnDeletar);
	}
	
	protected ArrayList<ListaPedido> InputDialog() {
		
		PedidoDao pedidoDao = new PedidoDao();
		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();
		
		
		String[] options = { null, "Dinheiro", "Pix", "Débito", "Credito" };
		ImageIcon icon = new ImageIcon("src/icones/lupa.png");
		String n = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada", "Condição de Pagamento",
				JOptionPane.QUESTION_MESSAGE, icon, options, options[4]);
		System.out.println(n);

		String opcao = n;
		switch (opcao) {

		case "Dinheiro":
			
			listaDePedidos = pedidoDao.listarPedidoPorCondicaoPagamento(opcao);
			break;
			
		case "Pix":

			listaDePedidos = pedidoDao.listarPedidoPorCondicaoPagamento(opcao);
			break;

		case "Débito":

			listaDePedidos = pedidoDao.listarPedidoPorCondicaoPagamento(opcao);
			break;

		case "Credito":

			listaDePedidos = pedidoDao.listarPedidoPorCondicaoPagamento(opcao);
			break;

		}
		
		return listaDePedidos;
	}
}
