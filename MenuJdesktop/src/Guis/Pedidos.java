package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Conexao.Dao.ClienteDao;
import Conexao.Dao.PedidoDao;
import Conexao.Dao.ProdutoDao;
import Models.Cliente;
import Models.ItemPedido;
import Models.Pedido;
import Models.Produto;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Component;

@SuppressWarnings("serial")
public class Pedidos extends JInternalFrame {
	private JTextField textCPFCliente;
	private JTextField textFieldNomeCliente;
	private JTextField textSelCodProduto;
	private JTable tblProdutosVenda;
	private JTable tblProdutosSelecao;
	private JTextField textSelQtdItem;
	public JTextField textSelNomeProduto;
	private JTextField textQtdItens;
	private JTextField textValorTotal;
	Pedido pedido = new Pedido();
	public Produto teste;
	Double valorTotalPedido = 0.00;
	Produto produto = new Produto();
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
	DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();

	DefaultTableModel mdlProdutosVda, mdlProdutosSel;
	private JTextField textCodCliente;
	private JTextField textSelValorVenda;
	private JTextField textSelDesMarca;
	private JTextField textSelCodMarca;
	private String tipoPedido = "";
	private JComboBox comboBoxCondicaoPagamento = new JComboBox();
	JRadioButton rdbtnOrcamento = new JRadioButton("Orçamento");
	JRadioButton rdbtnPedido = new JRadioButton("Pedido");

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pedidos() {
		try {
			setMaximum(false);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		esquerda.setHorizontalAlignment(SwingConstants.LEFT);
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		setClosable(true);
		setFrameIcon(new ImageIcon(Pedidos.class.getResource("/Icones/relatorio.png")));
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

		JLabel lblClienteCpf = new JLabel("Cliente CPF:");
		lblClienteCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClienteCpf.setBounds(23, 41, 94, 20);
		panel.add(lblClienteCpf);

		textCPFCliente = new JTextField();
		textCPFCliente.setEditable(false);
		textCPFCliente.setBackground(new Color(225, 225, 225));
		textCPFCliente.setBounds(99, 41, 120, 20);
		panel.add(textCPFCliente);
		textCPFCliente.setColumns(10);

		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setEditable(false);
		textFieldNomeCliente.setColumns(10);
		textFieldNomeCliente.setBackground(new Color(225, 225, 225));
		textFieldNomeCliente.setBounds(229, 41, 318, 20);
		panel.add(textFieldNomeCliente);

		textSelCodProduto = new JTextField();
		textSelCodProduto.setEditable(false);
		textSelCodProduto.setColumns(10);
		textSelCodProduto.setBackground(new Color(225, 225, 225));
		textSelCodProduto.setBounds(107, 236, 67, 20);
		panel.add(textSelCodProduto);

		comboBoxCondicaoPagamento
				.setModel(new DefaultComboBoxModel(new String[] { "", "Dinheiro", "Pix", "Débito", "Credito" }));
		comboBoxCondicaoPagamento.setBounds(179, 10, 94, 18);
		panel.add(comboBoxCondicaoPagamento);

		JLabel lblNewLabel_1 = new JLabel("Condição de Pagamento:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(23, 11, 173, 14);
		panel.add(lblNewLabel_1);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidade.setBounds(467, 206, 80, 20);
		panel.add(lblQuantidade);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 267, 693, 110);
		panel.add(scrollPane);

		

		rdbtnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnPedido.isSelected()) {
					rdbtnOrcamento.setSelected(false);
				}
				tipoPedido = "P";
				pedido.setTipo_pedido(tipoPedido);
				tipoPedido = "";
			}
		});

		rdbtnOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnOrcamento.isSelected()) {
					rdbtnPedido.setSelected(false);
				}
				tipoPedido = "0";
				pedido.setTipo_pedido(tipoPedido);
				tipoPedido = "";
			}
		});

		tblProdutosVenda = new JTable();
		scrollPane.setViewportView(tblProdutosVenda);
		tblProdutosVenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textSelQtdItem.setEditable(false);

				int contador = tblProdutosVenda.getSelectedRow();
				textSelCodProduto.setText(mdlProdutosVda.getValueAt(contador, 0).toString());
				textSelNomeProduto.setText(mdlProdutosVda.getValueAt(contador, 1).toString());
				textSelQtdItem.setText(mdlProdutosVda.getValueAt(contador, 2).toString());
				textSelCodMarca.setText(mdlProdutosVda.getValueAt(contador, 3).toString());
				textSelDesMarca.setText(mdlProdutosVda.getValueAt(contador, 4).toString());
				textSelValorVenda.setText(mdlProdutosVda.getValueAt(contador, 5).toString());

			}
		});

		mdlProdutosVda = new DefaultTableModel();
		Object[] colunn = { "Cod.Produto", "Des.Produto", "Quantidade", "Marca", "Des.Marca", "Vlr.Venda" };
		Object[] row = new Object[6];
		mdlProdutosVda.setColumnIdentifiers(colunn);
		tblProdutosVenda.setModel(mdlProdutosVda);
		tblProdutosVenda.getColumnModel().getColumn(0).setMaxWidth(100);
		tblProdutosVenda.getColumnModel().getColumn(1).setMaxWidth(600);
		tblProdutosVenda.getColumnModel().getColumn(2).setMaxWidth(80);
		tblProdutosVenda.getColumnModel().getColumn(3).setMaxWidth(50);
		tblProdutosVenda.getColumnModel().getColumn(4).setMaxWidth(400);
		tblProdutosVenda.getColumnModel().getColumn(5).setMaxWidth(150);
		tblProdutosVenda.getColumnModel().getColumn(5).setCellRenderer(direita);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		textSelQtdItem = new JTextField();
		textSelQtdItem.setBackground(new Color(225, 225, 225));
		textSelQtdItem.setBounds(557, 207, 54, 20);
		panel.add(textSelQtdItem);
		textSelQtdItem.setColumns(10);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(validaDados()==false) {
					return;
				}

				ArrayList<ItemPedido> listaItensPedido = new ArrayList<>();
				PedidoDao pedidodao = new PedidoDao();

				pedido.setData_pedido(LocalDate.now().toString());
				pedido.setClientes_cod_cliente(textCodCliente.getText());
				pedido.setCondicao_pagamento_pedido(comboBoxCondicaoPagamento.getSelectedItem().toString());
				pedido.setTipo_pedido(pedido.getTipo_pedido());
				int contador = mdlProdutosVda.getRowCount();

				for (int teste = 0; teste < contador; teste++) {

					ItemPedido itemPedido = new ItemPedido();

					itemPedido.setProdutos_cod_produto(mdlProdutosVda.getValueAt(teste, 0).toString());
					itemPedido.setQuantidade_item(mdlProdutosVda.getValueAt(teste, 2).toString());

					listaItensPedido.add(itemPedido);
				}

				pedidodao.inserirPedido(pedido, listaItensPedido);
				
				//Chamada de Cupom de Venda
				frmPrincipal frame = new frmPrincipal();
				Integer cod_pedido = pedidodao.listarUltimoPedido();
				frame.relatorioComprovanteFiscal(cod_pedido);
				
				//Limpeza de todos os dados e variáveis após gravar o pedido
				valorTotalPedido = 0.00;
				textCPFCliente.setText("");
				textFieldNomeCliente.setText("");
				textQtdItens.setText("");
				textValorTotal.setText("");
				textSelCodProduto.setText("");
				textSelQtdItem.setText("");
				textSelNomeProduto.setText("");
				textSelCodMarca.setText("");
				textSelValorVenda.setText("");
				textSelDesMarca.setText("");
				comboBoxCondicaoPagamento.setSelectedIndex(-1);
				rdbtnOrcamento.setSelected(false);
				rdbtnPedido.setSelected(false);
				textCodCliente.setText("");

				if (mdlProdutosSel.getRowCount() != 0) {
					mdlProdutosSel.setRowCount(0);
				}

				if (mdlProdutosVda.getRowCount() != 0) {
					mdlProdutosVda.setRowCount(0);
				}
			}
		});
		btnGravar.setBounds(258, 421, 89, 23);
		panel.add(btnGravar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textCPFCliente.setText("");
				textFieldNomeCliente.setText("");
				textQtdItens.setText("");
				textValorTotal.setText("");
				textSelCodProduto.setText("");
				textSelQtdItem.setText("");
				textSelNomeProduto.setText("");
				textSelValorVenda.setText("");
				textSelCodMarca.setText("");
				textSelDesMarca.setText("");
				comboBoxCondicaoPagamento.setSelectedIndex(-1);
				rdbtnOrcamento.setSelected(false);
				rdbtnPedido.setSelected(false);
				textCodCliente.setText("");
				
				comboBoxCondicaoPagamento.setModel(new DefaultComboBoxModel(new String[] { "", "Dinheiro", "Pix", "Débito", "Credito" }));

				if (mdlProdutosSel.getRowCount() != 0) {
					mdlProdutosSel.setRowCount(0);
				}

				if (mdlProdutosVda.getRowCount() != 0) {
					mdlProdutosVda.setRowCount(0);
				}

			}
		});
		btnLimpar.setBounds(381, 421, 89, 23);
		panel.add(btnLimpar);

		rdbtnPedido.setBounds(293, 7, 90, 23);
		panel.add(rdbtnPedido);

		rdbtnOrcamento.setBounds(385, 7, 109, 23);
		panel.add(rdbtnOrcamento);

		JLabel lblProduto = new JLabel("Nome Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setBounds(23, 206, 94, 20);
		panel.add(lblProduto);

		textSelNomeProduto = new JTextField();
		textSelNomeProduto.setEditable(false);
		textSelNomeProduto.setColumns(10);
		textSelNomeProduto.setBackground(new Color(225, 225, 225));
		textSelNomeProduto.setBounds(127, 206, 330, 20);
		panel.add(textSelNomeProduto);

		JLabel lblQtdItens = new JLabel("Qtd. Itens:");
		lblQtdItens.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQtdItens.setBounds(23, 388, 94, 20);
		panel.add(lblQtdItens);

		textQtdItens = new JTextField();
		textQtdItens.setColumns(10);
		textQtdItens.setBackground(new Color(225, 225, 225));
		textQtdItens.setBounds(99, 388, 46, 20);
		panel.add(textQtdItens);

		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorTotal.setBounds(546, 388, 80, 20);
		panel.add(lblValorTotal);

		textValorTotal = new JTextField();
		textValorTotal.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textValorTotal.setColumns(10);
		textValorTotal.setBackground(new Color(225, 225, 225));
		textValorTotal.setBounds(627, 389, 89, 20);
		panel.add(textValorTotal);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setBounds(557, 41, 54, 20);
		panel.add(lblCodigo);

		textCodCliente = new JTextField();
		textCodCliente.setEditable(false);
		textCodCliente.setColumns(10);
		textCodCliente.setBackground(new Color(225, 225, 225));
		textCodCliente.setBounds(608, 41, 46, 20);
		panel.add(textCodCliente);

		JButton btnPesquisaCliente = new JButton("Pesquisar Cliente");
		btnPesquisaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Cliente> cliente = new ArrayList<>();
				ClienteDao clienteDao = new ClienteDao();

				String cpf = JOptionPane.showInputDialog("Informe o CPF do Cliente: ");
				cliente = clienteDao.listarClientePorCpf(cpf);

				for (Cliente contador : cliente) {
					textCodCliente.setText(contador.getCod_cliente());
					textFieldNomeCliente.setText(contador.getNome_cliente());
					textCPFCliente.setText(contador.getCpf_cliente());
				}
			}
		});
		btnPesquisaCliente.setBounds(672, 41, 46, 23);
		panel.add(btnPesquisaCliente);

		JButton btnInserir = new JButton("+");
		btnInserir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {// Início botão +
														// ========================================================================AQUI

				PedidoDao pedidodao = new PedidoDao();

				String itemSelecionado = textSelCodProduto.getText();
				String qtdeSelecionada = textSelQtdItem.getText();

				if (itemSelecionado.equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Nenhum produto selecionado. Verifique!");
					textSelQtdItem.setText("");
					return;
				}

				if (qtdeSelecionada.equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Quantidade  é obrigatória.");
					textSelQtdItem.setText("");
					textSelQtdItem.requestFocus();
					return;
				}

				if (!ValidaEntrada.isNumero(qtdeSelecionada)) {
					JOptionPane.showInternalMessageDialog(null, "Quantidade, somente números (inteiro positivo).");
					textSelQtdItem.setText("");
					textSelQtdItem.requestFocus();
					return;
				}

				if (!pedidodao.verificaSaldoEstoque(itemSelecionado, qtdeSelecionada)) {
					JOptionPane.showInternalMessageDialog(null, "Quantidade informada indisponível no estoque!");
					textSelQtdItem.setText("");
					textSelQtdItem.requestFocus();
					return;
				}

				int nrolinhas = mdlProdutosVda.getRowCount();

				for (int i = 0; i < nrolinhas; i++) {
					System.out.println("Passou 'i' vale:[" + i + "]");
					System.out.println("Passou 'nrolinhas' vale:[" + nrolinhas + "]");

					if ((mdlProdutosVda.getValueAt(i, 0).toString()).equals(itemSelecionado)) {
						JOptionPane.showInternalMessageDialog(null, "Item já selecionado para o orçamento/pedido.");
						textSelQtdItem.setText("");
						textSelQtdItem.requestFocus();
						textSelCodProduto.setText("");
						textSelNomeProduto.setText("");
						textSelQtdItem.setText("");
						textSelValorVenda.setText("");
						textSelCodMarca.setText("");
						textSelDesMarca.setText("");
						System.out.println("Item da lista [" + mdlProdutosVda.getValueAt(i, 0).toString()
								+ "] Item Selecionado [" + itemSelecionado + "]");
						return;
					}
				}

				row[0] = textSelCodProduto.getText();
				row[1] = textSelNomeProduto.getText();
				row[2] = textSelQtdItem.getText();
				row[3] = textSelCodMarca.getText();
				row[4] = textSelDesMarca.getText();
				row[5] = textSelValorVenda.getText();

				mdlProdutosVda.addRow(row);

				Double qtdItem = Double.parseDouble(textSelQtdItem.getText());
				Double valorVenda = Double.parseDouble(textSelValorVenda.getText());
				valorTotalPedido += (qtdItem * valorVenda);
				textValorTotal.setText(String.valueOf(valorTotalPedido));

				textQtdItens.setText(String.valueOf(nrolinhas + 1));

				textSelCodProduto.setText("");
				textSelNomeProduto.setText("");
				textSelQtdItem.setText("");
				textSelValorVenda.setText("");
				textSelCodMarca.setText("");
				textSelDesMarca.setText("");
			}
		});// Fim botão +
			// ============================================================================================================AQUI
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInserir.setBounds(672, 206, 44, 23);
		panel.add(btnInserir);

		JButton btnRemover = new JButton("-");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String itemSelecionado = textSelCodProduto.getText();
				String qtdeSelecionada = textSelQtdItem.getText();

				if (itemSelecionado.equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Nenhum produto selecionado. Verifique!");
					textSelQtdItem.setText("");
					return;
				}

				int nrolinhas = mdlProdutosVda.getRowCount();

				for (int i = 0; i < nrolinhas; i++) {
					if ((mdlProdutosVda.getValueAt(i, 0).toString()).equals(itemSelecionado)) {
						Double qtdItem = Double.parseDouble(textSelQtdItem.getText());
						Double valorVenda = Double.parseDouble(textSelValorVenda.getText());
						mdlProdutosVda.removeRow(i);
						valorTotalPedido -= (qtdItem * valorVenda);
						break;
					}
				}

				textValorTotal.setText(String.valueOf(valorTotalPedido));

				textQtdItens.setText(String.valueOf(nrolinhas - 1));
				textSelCodProduto.setText("");
				textSelNomeProduto.setText("");
				textSelQtdItem.setText("");
				textSelValorVenda.setText("");
				textSelCodMarca.setText("");
				textSelDesMarca.setText("");
				textSelQtdItem.setEditable(true);
			}
		});
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemover.setBounds(618, 206, 44, 23);
		panel.add(btnRemover);

		JLabel lblCodProduto = new JLabel("Cod.Produto:");
		lblCodProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodProduto.setBounds(23, 236, 94, 20);
		panel.add(lblCodProduto);

		JLabel lblValorUnitrio = new JLabel("Valor Venda:");
		lblValorUnitrio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorUnitrio.setBounds(185, 236, 94, 20);
		panel.add(lblValorUnitrio);

		textSelValorVenda = new JTextField();
		textSelValorVenda.setEditable(false);
		textSelValorVenda.setColumns(10);
		textSelValorVenda.setBackground(new Color(225, 225, 225));
		textSelValorVenda.setBounds(267, 236, 94, 20);
		panel.add(textSelValorVenda);

		JLabel lblMarca = new JLabel("Des.Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMarca.setBounds(498, 236, 67, 20);
		panel.add(lblMarca);

		textSelDesMarca = new JTextField();
		textSelDesMarca.setEditable(false);
		textSelDesMarca.setColumns(10);
		textSelDesMarca.setBackground(new Color(225, 225, 225));
		textSelDesMarca.setBounds(567, 236, 149, 20);
		panel.add(textSelDesMarca);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 106, 693, 96);
		panel.add(scrollPane_1);

		tblProdutosSelecao = new JTable();
		scrollPane_1.setViewportView(tblProdutosSelecao);
		tblProdutosSelecao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textSelQtdItem.setEditable(true);
				int contador1 = tblProdutosSelecao.getSelectedRow();
				textSelCodProduto.setText(mdlProdutosSel.getValueAt(contador1, 0).toString());
				textSelNomeProduto.setText(mdlProdutosSel.getValueAt(contador1, 1).toString());
				textSelCodMarca.setText(mdlProdutosSel.getValueAt(contador1, 3).toString());
				textSelQtdItem.setText("");
				textSelDesMarca.setText(mdlProdutosSel.getValueAt(contador1, 4).toString());
				textSelValorVenda.setText(mdlProdutosSel.getValueAt(contador1, 5).toString());
				textSelQtdItem.requestFocus();

			}
		});

		mdlProdutosSel = new DefaultTableModel();
		Object[] colunn1 = { "Cod.Produto", "Des.Produto", "Quantidade", "Marca", "Des.Marca", "Vlr.Venda" };
		Object[] row1 = new Object[6];
		mdlProdutosSel.setColumnIdentifiers(colunn1);
		tblProdutosSelecao.setModel(mdlProdutosSel);
		tblProdutosSelecao.getColumnModel().getColumn(0).setMaxWidth(100);
		tblProdutosSelecao.getColumnModel().getColumn(1).setMaxWidth(600);
		tblProdutosSelecao.getColumnModel().getColumn(2).setMaxWidth(80);
		tblProdutosSelecao.getColumnModel().getColumn(3).setMaxWidth(50);
		tblProdutosSelecao.getColumnModel().getColumn(4).setMaxWidth(400);
		tblProdutosSelecao.getColumnModel().getColumn(5).setMaxWidth(150);

		tblProdutosSelecao.getColumnModel().getColumn(5).setCellRenderer(direita);

		JScrollBar scrollBar_1 = new JScrollBar();
		scrollPane_1.setRowHeaderView(scrollBar_1);

		JLabel lblCodMarca = new JLabel("Cod.Marca:");
		lblCodMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodMarca.setBounds(369, 236, 82, 20);
		panel.add(lblCodMarca);

		textSelCodMarca = new JTextField();
		textSelCodMarca.setEditable(false);
		textSelCodMarca.setColumns(10);
		textSelCodMarca.setBackground(new Color(225, 225, 225));
		textSelCodMarca.setBounds(440, 236, 54, 20);
		panel.add(textSelCodMarca);

		JButton btnBuscaProdutos = new JButton("Pesquisar Produtos");
		btnBuscaProdutos.setMargin(new Insets(2, 3, 2, 3));
		btnBuscaProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Produto();
				ProdutoDao produtodao = new ProdutoDao();

				if (mdlProdutosSel.getRowCount() != 0) {
					mdlProdutosSel.setRowCount(0);
				}
				String nome = JOptionPane.showInputDialog("Informe o nome do Produto: ");

				ArrayList<Produto> listaDeProdutos = new ArrayList<>();
				listaDeProdutos = produtodao.listarProdutoPorNome(nome);

				for (Produto contador : listaDeProdutos) {
					row1[0] = contador.getCod_produto();
					row1[1] = contador.getNome_produto();
					row1[3] = contador.getCod_marca_produto();
					row1[2] = contador.getQuantidade_produto();
					row1[4] = contador.getNome_marca_produto();
					row1[5] = contador.getValor_venda_produto();
					mdlProdutosSel.addRow(row1);
				}
			}

		});
		btnBuscaProdutos.setBounds(23, 72, 136, 23);
		panel.add(btnBuscaProdutos);

	}

	public boolean validaDados() {

		String condicaoPagamento = comboBoxCondicaoPagamento.getSelectedItem().toString();

		if (condicaoPagamento.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Condição de Pagamento é obrigatória.");
			comboBoxCondicaoPagamento.requestFocus();
			return (false);
		}
		
		
		if(!rdbtnPedido.isSelected()){
			if(!rdbtnOrcamento.isSelected()) {
				JOptionPane.showInternalMessageDialog(null, "Selecione 'Orçamento' ou 'Pedido'");
				return (false);
			}
		}
		
		
		
		if(textCPFCliente.getText().equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Nenhum cliente informado.");
			return (false);
		}
		
		if ((textQtdItens.getText().equals(""))||(textValorTotal.getText().equals(""))) {
			JOptionPane.showInternalMessageDialog(null, "Pedido não contém Itens ou Valor Total válidos");
			return (false);
		}

		return (true);

	}
}
