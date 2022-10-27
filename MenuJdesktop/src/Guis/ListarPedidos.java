package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class ListarPedidos extends JInternalFrame {
	private JTextField textFieldCodPedidos;
	Pedido pedido = new Pedido();
	public Produto teste;
	double calculaValorTotal = 0;

	private JTable table;

	DefaultTableModel model;
	Object[] row = new Object[11];
	
	DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
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
		setTitle("Consulta de Pedidos");
		setBounds(100, 100, 770, 538);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Pedido");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(299, 0, 128, 48);
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
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
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
		btnDeletar.setBounds(518, 397, 160, 34);
		panel.add(btnDeletar);

		JLabel lblNewLabel_1 = new JLabel("* Preenchimento obrigatório para excluir o pedido");
		lblNewLabel_1.setBounds(154, 13, 320, 14);
		panel.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Relatórios");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputDialog2();
			}
		});
		btnNewButton.setIcon(
				new ImageIcon("C:\\Users\\00787663\\Desktop\\ProjetoXMarket\\MenuJdesktop\\src\\Icones\\icons8-verificar-100.png"));
		btnNewButton.setBounds(299, 397, 160, 34);
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
		btnConsultaPedido.setBounds(80, 397, 160, 34);
		panel.add(btnConsultaPedido);
	}

	protected ArrayList<ListaPedido> InputDialog() {
		PedidoDao pedidoDao = new PedidoDao();
		ArrayList<ListaPedido> listaDePedidos = new ArrayList<>();
		String[] options = { "Selecione a forma de pagamento", "Dinheiro", "Pix", "Débito", "Credito" };
		ImageIcon icon = new ImageIcon("src/icones/lupa.png");
		String n = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada", "Condição de Pagamento",
				JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
		System.out.println(n);
		String opcao = n;
		switch (opcao) {
		case "Selecione a forma de pagamento":
			JOptionPane.showMessageDialog(null, "Selecione uma opção válida!");
			break;
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
			String[] options = { "Selecione uma opção", "Relatório Todos os Pedidos","Relatório por Código do Pedido", "Relatório Pedidos por CPF",
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
				
			case "Relatório por Código do Pedido":
				Integer codigo = Integer.valueOf(JOptionPane.showInputDialog("Insira o código do pedido: "));
				frame.relatorioPedidoPorCodigo(codigo);
				break;

			case "Relatório Pedidos por CPF":
				System.out.println();
				String cpf = JOptionPane.showInputDialog("Insira o cpf do cliente: ");
				frame.relatorioPedidoPorCpf(cpf);
				break;

			case "Relatório Pedidos por Data":
				
				System.out.println("Relatório Pedidos por Data");
				
				System.out.println("Relatório Pedidos por Data");String data = JOptionPane.showInputDialog(null, "Insira a data no seguinte formato 'DD/MM/AAAA':");
				
				LocalDate dataFormatada = LocalDate.parse(data,formatoData);
				
				String dataBanco = String.valueOf(dataFormatada);
				frame.relatorioPedidoPorData(dataBanco);
				break;

			case "Relatório Pedidos Entre Datas":
				System.out.println("Relatório Pedidos Entre Datas");
				
				String data1 = JOptionPane.showInputDialog("Insira a primeira data no seguinte formato 'DD/MM/AAAA':");
				LocalDate dataFormatada1 = LocalDate.parse(data1,formatoData);
				String dataBanco1 = String.valueOf(dataFormatada1);
				
				String data2 = JOptionPane.showInputDialog("Insira a segunda data no seguinte formato 'DD/MM/AAAA':");
				LocalDate dataFormatada2 = LocalDate.parse(data2,formatoData);
				String dataBanco2 = String.valueOf(dataFormatada2);
				frame.relatorioPedidoEntreDatas(dataBanco1, dataBanco2);
				break;

			case "Relatório Pedidos por Nome":
				System.out.println("Relatório Pedidos por Nome");
				String nome = JOptionPane.showInputDialog("Insira o nome do cliente: ");
				frame.relatorioPedidoPorNome(nome);
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
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		frmPrincipal frame1 = new frmPrincipal();
		
		try {
			String[] options = { "Selecione uma opção", "Listar Todos os Pedidos","Listar Pedidos por Código", "Listar Pedidos por CPF",
					"Listar Pedidos por Nome", "Listar Pedidos por Data", "Listar Pedidos Entre Datas",
					"Listar Pedidos Opção de Pagamento" };
			ImageIcon icon = new ImageIcon("src/icones/lupa.png");
			String n = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada", "Relatórios",
					JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
			System.out.println(n);
			String opcao = n;
			switch (opcao) {

			case "Selecione uma opção":
				JOptionPane.showMessageDialog(null, "Selecione uma opção válida!");
				break;

			case "Listar Todos os Pedidos":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				listaPedido = pedidoDao.listarTodosPedidos();
				
				if (JOptionPane.showConfirmDialog(null, "Deseja emitir um relatório desta consulta?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					frame1.relatorioPedido();
				}
				
				break;

			case "Listar Pedidos por Código":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				Integer cod_pedido = Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o Código: "));
				listaPedido = pedidoDao.listarPedidoPorCodigo(cod_pedido);
				
				if (JOptionPane.showConfirmDialog(null, "Deseja emitir um relatório desta consulta?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					frame1.relatorioPedidoPorCodigo(cod_pedido);
				}
				
				
				break;	
				
			case "Listar Pedidos por CPF":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				
				String cpf = JOptionPane.showInputDialog(null, "Digite o CPF");
				listaPedido = pedidoDao.listarPedidoPorCpfCliente(cpf);
	
				
				if (JOptionPane.showConfirmDialog(null, "Deseja emitir um relatório desta consulta?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					frame1.relatorioPedidoPorCpf(cpf);
				}
				
				
				break;
				
			case "Listar Pedidos por Data":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
								
				String data = JOptionPane.showInputDialog(null, "Insira a data no seguinte formato 'DD/MM/AAAA':");
				
				LocalDate dataFormatada = LocalDate.parse(data,formatoData);
				
				String dataBanco = String.valueOf(dataFormatada);
				
				listaPedido = pedidoDao.listarPedidoPorData(dataBanco);
				
				if (JOptionPane.showConfirmDialog(null, "Deseja emitir um relatório desta consulta?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					frame1.relatorioPedidoPorData(dataBanco);
				}
				
				
				break;

			case "Listar Pedidos Entre Datas":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				String data1 = JOptionPane.showInputDialog("Insira a primeira data no seguinte formato 'DD/MM/AAAA':");
				LocalDate dataFormatada1 = LocalDate.parse(data1,formatoData);
				String dataBanco1 = String.valueOf(dataFormatada1);
				
				String data2 = JOptionPane.showInputDialog("Insira a segunda data no seguinte formato 'DD/MM/AAAA':");
				LocalDate dataFormatada2 = LocalDate.parse(data2,formatoData);
				String dataBanco2 = String.valueOf(dataFormatada2);
				
				listaPedido = pedidoDao.listarPedidoEntreDatas(dataBanco1, dataBanco2);
				
				if (JOptionPane.showConfirmDialog(null, "Deseja emitir um relatório desta consulta?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					frame1.relatorioPedidoEntreDatas(dataBanco1, dataBanco2);
				}
				
				
				break;

			case "Listar Pedidos por Nome":
				
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				
				String nome = JOptionPane.showInputDialog("Insira o nome do cliente: ");
				listaPedido = pedidoDao.listarPedidoPorNomeCliente(nome);
				
				if (JOptionPane.showConfirmDialog(null, "Deseja emitir um relatório desta consulta?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					
					
					frame1.relatorioPedidoPorNome(nome);
				}
				
				
				break;

			case "Listar Pedidos Opção de Pagamento":
				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				
				
				
				if (JOptionPane.showConfirmDialog(null, "Deseja emitir um relatório desta consulta?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					frame1.relatorioPedidoOpcaoPagamento();
					
				}else {
					Boolean trata = false;
					do {
						String[] options1 = { "Selecione a opção", "Dinheiro", "Pix", "Débito", "Credito" };
						ImageIcon icon1 = new ImageIcon("src/icones/lupa.png");
						String n1 = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada",
								"Condição de Pagamento", JOptionPane.QUESTION_MESSAGE, icon1, options1, options1[0]);
						System.out.println(n1);
						String opcao1 = n1;

						
						switch (opcao1) {
						case "Selecione a opção":
							JOptionPane.showMessageDialog(null, "Selecione uma opção válida!");
							trata = false;
							break;

						case "Dinheiro":
							listaPedido = pedidoDao.listarPedidoPorCondicaoPagamento(opcao1);
							trata = true;
							break;

						case "Pix":
							listaPedido = pedidoDao.listarPedidoPorCondicaoPagamento(opcao1);
							trata = true;
							break;
							
						case "Débito":
							listaPedido = pedidoDao.listarPedidoPorCondicaoPagamento(opcao1);
							trata = true;
							break;
							
						case "Credito":
							listaPedido = pedidoDao.listarPedidoPorCondicaoPagamento(opcao1);
							trata = true;
							break;
						}
					} while (trata == false);
				}
				
				
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
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada;

		for (ListaPedido contador : listaDePedidos) {
			row[0] = contador.getCod_pedido();
			dataFormatada = formatador.format(contador.getData_pedido());
			row[1] = dataFormatada;
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