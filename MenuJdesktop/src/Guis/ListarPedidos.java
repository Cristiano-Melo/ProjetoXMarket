package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import Conexao.Dao.PedidoDao;
import Models.ListaPedido;
import Models.Pedido;
import Models.Produto;

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

	DefaultTableModel model;
	Object[] row = new Object[11];

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
		textFieldCodPedidos.setBounds(82, 10, 67, 20);
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
		Object[] colunn = { "Codigo", "Data", "Condição", "Cod. Cliente", "Cliente", "Cod. Produto", "Produto", "Qtde.",
				"Valor Item" };
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

		JLabel lblCodigo = new JLabel("Codigo*:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setBounds(23, 10, 67, 20);
		panel.add(lblCodigo);

		JButton btnDeletar = new JButton("Excluir Pedido");
		btnDeletar.setIcon(new ImageIcon(
				"C:\\Users\\00787663\\Desktop\\ProjetoXMarket\\MenuJdesktop\\src\\Icones\\icons8-remover-100.png"));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoDao pedidodao = new PedidoDao();
				Pedido pedido = new Pedido();
				pedido.setCod_pedido(textFieldCodPedidos.getText());

				if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Pedido?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					pedidodao.excluirPedido(pedido);
				}

			}
		});
		btnDeletar.setBounds(518, 397, 139, 34);
		panel.add(btnDeletar);

		JLabel lblNewLabel_1 = new JLabel("* Preenchimento obrigatório para excluir o pedido");
		lblNewLabel_1.setBounds(154, 13, 251, 14);
		panel.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Relatórios");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputDialog2();
			}
		});
		btnNewButton.setIcon(
				new ImageIcon("C:\\Users\\00787663\\Desktop\\ProjetoXMarket\\MenuJdesktop\\src\\Icones\\icons8-verificar-100.png"));
		btnNewButton.setBounds(299, 397, 139, 34);
		panel.add(btnNewButton);
		
		JButton btnConsultaPedido = new JButton("Consulta Pedido");
		btnConsultaPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}

				ArrayList<ListaPedido> listaPedido = new ArrayList<>();
				
				listaPedido = InputDialog3();
				
				montaGrid(listaPedido);

			}
		});
		btnConsultaPedido.setIcon(new ImageIcon("C:\\Users\\00787663\\Desktop\\ProjetoXMarket\\MenuJdesktop\\src\\Icones\\lupa.png"));
		btnConsultaPedido.setBounds(80, 397, 139, 34);
		panel.add(btnConsultaPedido);
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

	protected void InputDialog2() {
		frmPrincipal frame = new frmPrincipal();
		try {
			String[] options = { "Selecione uma opção", "Relatório Todos os Pedidos", "Relatório Pedidos por CPF",
					"Relatório Pedidos por Nome", "Relatório Pedidos por Data", "Relatório Pedidos Entre Datas",
					"Relatório Pedidos Opção de Pagamento" };
			ImageIcon icon = new ImageIcon("src/icones/lupa.png");
			String n = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada", "Relatórios",
					JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
			System.out.println(n);
			String opcao = n;
			switch (opcao) {

			case "Selecione uma opção":
				JOptionPane.showMessageDialog(null, "Selecione uma opção válida!");
				break;

			case "Relatório Todos os Pedidos":
				System.out.println("Relatório Pedidos");
				frame.relatorioPedido();
				break;

			case "Relatório Pedidos por CPF":
				System.out.println();
				frame.relatorioPedidoPorCpf();
				break;

			case "Relatório Pedidos por Data":
				
				System.out.println("Relatório Pedidos por Data");
				frame.relatorioPedidoPorData();
				break;

			case "Relatório Pedidos Entre Datas":
				System.out.println("Relatório Pedidos Entre Datas");
				frame.relatorioPedidoEntreDatas();
				break;

			case "Relatório Pedidos por Nome":
				System.out.println("Relatório Pedidos por Nome");
				frame.relatorioPedidoPorNome();
				break;

			case "Relatório Pedidos Opção de Pagamento":
				System.out.println("Relatório Pedidos Opção de Pagamento");
				frame.relatorioPedidoOpcaoPagamento();
				break;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Opção não pode estar vazia!");
		}
	}
	
	protected ArrayList<ListaPedido> InputDialog3() {
		PedidoDao pedidoDao = new PedidoDao();
		ArrayList<ListaPedido> listaPedido = new ArrayList<>();
		
		try {
			String[] options = { "Selecione uma opção", "Relatório Todos os Pedidos", "Relatório Pedidos por CPF",
					"Relatório Pedidos por Nome", "Relatório Pedidos por Data", "Relatório Pedidos Entre Datas",
					"Relatório Pedidos Opção de Pagamento" };
			ImageIcon icon = new ImageIcon("src/icones/lupa.png");
			String n = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada", "Relatórios",
					JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
			System.out.println(n);
			String opcao = n;
			switch (opcao) {

			case "Selecione uma opção":
				JOptionPane.showMessageDialog(null, "Selecione uma opção válida!");
				break;

			case "Relatório Todos os Pedidos":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				listaPedido = pedidoDao.listarTodosPedidos();
				break;

			case "Relatório Pedidos por CPF":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				String cpf = JOptionPane.showInputDialog(null, "Digite o CPF");
				listaPedido = pedidoDao.listarPedidoPorCpfCliente(cpf);
				break;
				
			case "Relatório Pedidos por Data":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				String data = JOptionPane.showInputDialog(null, "Insira a data no seguinte formato 'AAAA-MM-DD':");
				listaPedido = pedidoDao.listarPedidoPorData(data);
				break;

			case "Relatório Pedidos Entre Datas":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				data = JOptionPane.showInputDialog("Insira a primeira data no seguinte formato 'AAAA-MM-DD':");
				String data2 = JOptionPane.showInputDialog("Insira a segunda data no seguinte formato 'AAAA-MM-DD':");
				listaPedido = pedidoDao.listarPedidoEntreDatas(data, data2);
				break;

			case "Relatório Pedidos por Nome":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				String nome = JOptionPane.showInputDialog("Insira o nome do cliente: ");
				listaPedido = pedidoDao.listarPedidoPorNomeCliente(nome);				
				break;

			case "Relatório Pedidos Opção de Pagamento":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				listaPedido = InputDialog();
				
				break;
			}
			

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Opção não pode estar vazia!");
		}
		
		return listaPedido;
		
	}
	
	void montaGrid( ArrayList<ListaPedido> listaPedido) {

		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();
		listaDePedidos = listaPedido;

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
}
