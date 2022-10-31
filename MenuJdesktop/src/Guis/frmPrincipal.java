package Guis;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Conexao.Dao.ClienteDao;
import Conexao.Dao.PedidoDao;
import Conexao.Dao.ProdutoDao;
import Models.Cliente;
import Models.ListaPedido;
import Models.Login;
import Models.Produto;
import Relatorios.ComprovanteFiscal;
import Relatorios.RelatorioCliente;
import Relatorios.RelatorioPedidos;
import Relatorios.RelatorioProdutos;
import net.sf.jasperreports.engine.JRException;

public class frmPrincipal extends JFrame {

	private JPanel contentPane;
	private Clientes c;
	private Produtos p;
	private Pedidos pedidos;
	private ListarPedidos lp;
	private Marcas m;
	private Login login;
	private Contatos con;
	private JDesktopPane desktopPanePrincipal; // classe do painel deskttoppanel
	protected AbstractButton textFieldCodCliente;
	protected AbstractButton textFieldNomeCliente;
	protected AbstractButton textFieldCpf;

	ArrayList<Cliente> listacliente = new ArrayList<Cliente>();
	DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipal frame = new frmPrincipal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void projetoGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmPrincipal.class.getResource("/Imagens/botao.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1043, 904);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivos = new JMenu("Sistema");
		mnArquivos.setForeground(new Color(0, 0, 0));
		mnArquivos.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnArquivos);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setBackground(new Color(255, 255, 255));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Deseja sair do programa", "SAIR",
						JOptionPane.YES_NO_OPTION) == 0) // metodo para sair do sistema
					System.exit(0);
			}
		});
		mntmSair.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/sair.PNG")));
		mntmSair.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnArquivos.add(mntmSair);

		JMenu mnProcessos = new JMenu("Processos");
		mnProcessos.setForeground(new Color(0, 0, 0));
		mnProcessos.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnProcessos);

		JMenuItem mntmCadastro = new JMenuItem("Marcas");
		mntmCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarMarcas();

			}
		});
		mntmCadastro.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/relatorio.png")));
		mntmCadastro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnProcessos.add(mntmCadastro);

		JMenu mnConsultas = new JMenu("Consultas");
		mnConsultas.setForeground(new Color(0, 0, 0));
		mnConsultas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnConsultas);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Clientes");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarClientes();
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/cliente.png")));
		mnConsultas.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Produtos");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarProdutos();
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/produtos.png")));
		mnConsultas.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Pedidos");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarPedidos();

			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/icons8-verificar-100.png")));
		mnConsultas.add(mntmNewMenuItem_3);

		JMenu mnRelatorio = new JMenu("Relatorios");
		mnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnRelatorio.setForeground(new Color(0, 0, 0));
		mnRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnRelatorio);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("2ª Via Cupom Fiscal");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer cod_pedido = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do pedido: "));
				relatorioComprovanteFiscal(cod_pedido);				
			}
		});
		mntmNewMenuItem_4.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/relatorio.png")));
		mnRelatorio.add(mntmNewMenuItem_4);

		JMenu mnNewMenu_4 = new JMenu("Ajuda");
		mnNewMenu_4.setForeground(new Color(0, 0, 0));
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu_4);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		toolBar.setForeground(Color.WHITE);
		toolBar.setBackground(UIManager.getColor("Button.background"));
		toolBar.setFloatable(false);

		desktopPanePrincipal = new JDesktopPane();
		desktopPanePrincipal.setBackground(new Color(204, 204, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(desktopPanePrincipal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
						.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(desktopPanePrincipal, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
					.addContainerGap())
		);

		JLabel lblNewLabel = new JLabel("New label");
		//lblNewLabel.setIcon(new ImageIcon("C:\\Users\\00787671\\Documents\\GitHub\\ProjetoXMarket-main\\MenuJdesktop\\src\\Imagens\\Teste.jpg"));
		lblNewLabel.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Imagens/Teste.jpg")));
		GroupLayout gl_desktopPanePrincipal = new GroupLayout(desktopPanePrincipal);
		gl_desktopPanePrincipal.setHorizontalGroup(
			gl_desktopPanePrincipal.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 1039, Short.MAX_VALUE)
		);
		gl_desktopPanePrincipal.setVerticalGroup(
			gl_desktopPanePrincipal.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
		);
		desktopPanePrincipal.setLayout(gl_desktopPanePrincipal);

		JButton btnClientes = new JButton("Clientes     ");
		btnClientes.setBackground(new Color(255, 255, 255));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarClientes();// chamando o metodo void carregar clientes
			}
		});
		btnClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClientes.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/cliente.png")));
		toolBar.add(btnClientes);

		JButton btnProdutos = new JButton("Produtos     ");
		btnProdutos.setBackground(new Color(255, 255, 255));
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarProdutos();// chamando o metodo void carregar produtos
			}
		});
		btnProdutos.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/produtos.png")));
		btnProdutos.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnProdutos);

		JButton btnNewButton = new JButton("Pedidos     ");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarPedidos();
			}
		});
		btnNewButton.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/icons8-verificar-100.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnNewButton);

		JButton btnConsultas = new JButton("Consultas     ");
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputDialog();

			}
		});
		btnConsultas.setBackground(new Color(255, 255, 255));
		btnConsultas.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/lupa.png")));
		btnConsultas.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnConsultas);

		JButton btnRelatorios = new JButton("Relatorios     ");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputDialog2();
			}
		});
		btnRelatorios.setBackground(new Color(255, 255, 255));
		btnRelatorios.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/relatorio.png")));
		btnRelatorios.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnRelatorios);

		JButton btnContatos = new JButton("Contatos     ");
		btnContatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarContatos();

			}
		});
		btnContatos.setBackground(new Color(255, 255, 255));
		btnContatos.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/contato.png")));
		btnContatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnContatos);

		JButton btnAjuda = new JButton("Ajuda");
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File pdf = new File("C:\\Users\\00787663\\Desktop\\ProjetoXMarket\\MenuJdesktop\\src\\Docs\\ManualDeApoio.pdf");
				try {
				  Desktop.getDesktop().open(pdf);
				} catch(Exception ex) {
				  ex.printStackTrace();
				  JOptionPane.showMessageDialog(null, "Erro no Desktop: " + ex);
				}
				
			}
		});
		btnAjuda.setBackground(new Color(255, 255, 255));
		btnAjuda.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/chamada-de-ajuda.png")));
		btnAjuda.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnAjuda);
		contentPane.setLayout(gl_contentPane);

	}

	protected void InputDialog() {
		String[] options = { null, "Consulta Clientes", "Consulta Produtos", "Consulta Pedidos" };
		ImageIcon icon = new ImageIcon("src/icones/lupa.png");
		String n = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada", "Consultas",
				JOptionPane.QUESTION_MESSAGE, icon, options, options[3]);
		if(n==null) {
			return;
		}

		String opcao = n;
		switch (opcao) {

		case "Consulta Clientes":
			System.out.println("Consulta Clientes");
			carregarClientes();

			break;
		case "Consulta Produtos":
			System.out.println("Consulta Produtos");
			carregarProdutos();
			break;

		case "Consulta Pedidos":
			System.out.println("Consulta Pedidos");
			listarPedidos();
			break;

		}

	}

	protected ArrayList<ListaPedido> InputDialog2() {

		try {
			String[] options = { "Selecione uma opção", "Relatório Clientes", "Relatório Clientes por Nome",
					"Relatório Clientes por CPF", "Relatório Produtos", "Relatório Pedidos",
					"Relatório Pedidos por CPF", "Relatório Pedidos por Nome", "Relatório Pedidos por Data",
					"Relatório Pedidos Entre Datas", "Relatório Pedidos Opção de Pagamento", "Relatório Pedido por Código" };
			ImageIcon icon = new ImageIcon("src/icones/lupa.png");
			String n = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada", "Relatórios",
					JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
			System.out.println(n);
			String opcao = n;
			switch (opcao) {

			case "Selecione uma opção":
				JOptionPane.showMessageDialog(null, "Selecione uma opção válida!");
				break;
			case "Relatório Clientes":
				System.out.println("Relatório Clientes");
				relatorioCliente();
				break;

			case "Relatório Clientes por Nome":
				System.out.println("Relatório Clientes por Nome");
				relatorioClientePorNome();
				break;

			case "Relatório Clientes por CPF":
				System.out.println("Relatório Clientes por CPF");
				relatorioClientePorCPF();
				break;

			case "Relatório Produtos":
				System.out.println("Relatório Produtos");
				relatorioProduto();
				break;
			case "Relatório Pedidos":
				System.out.println("Relatório Pedidos");
				relatorioPedido();
				break;
				
			case "Relatório Pedido por Código":
				Integer codigo = Integer.valueOf(JOptionPane.showInputDialog("Insira o código do pedido: "));
				relatorioPedidoPorCodigo(codigo);
				break;

			case "Relatório Pedidos por CPF":
				System.out.println();
				String cpf = JOptionPane.showInputDialog("Insira o cpf do cliente: ");
				relatorioPedidoPorCpf(cpf);
				break;

			case "Relatório Pedidos por Data":
				System.out.println("Relatório Pedidos por Data");String data = JOptionPane.showInputDialog(null, "Insira a data no seguinte formato 'DD/MM/AAAA':");
				
				LocalDate dataFormatada = LocalDate.parse(data,formatoData);
				
				String dataBanco = String.valueOf(dataFormatada);
				
				relatorioPedidoPorData(dataBanco);
				break;

			case "Relatório Pedidos Entre Datas":
				System.out.println("Relatório Pedidos Entre Datas");
				
				String data1 = JOptionPane.showInputDialog("Insira a primeira data no seguinte formato 'DD/MM/AAAA':");
				LocalDate dataFormatada1 = LocalDate.parse(data1,formatoData);
				String dataBanco1 = String.valueOf(dataFormatada1);
				
				String data2 = JOptionPane.showInputDialog("Insira a segunda data no seguinte formato 'DD/MM/AAAA':");
				LocalDate dataFormatada2 = LocalDate.parse(data2,formatoData);
				String dataBanco2 = String.valueOf(dataFormatada2);
				relatorioPedidoEntreDatas(dataBanco1, dataBanco2);
				break;

			case "Relatório Pedidos por Nome":
				System.out.println("Relatório Pedidos por Nome");
				String nome = JOptionPane.showInputDialog("Insira o nome do cliente: ");
				relatorioPedidoPorNome(nome);
				break;

			case "Relatório Pedidos Opção de Pagamento":
				System.out.println("Relatório Pedidos Opção de Pagamento");
				
				relatorioPedidoOpcaoPagamento();
				break;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Operação cancelada!");
		}
		return null;
	}

	public frmPrincipal() {
		setTitle("PDV - XMarket");
		setResizable(false);
		projetoGui();
	}

	void relatorioPedido() {

		RelatorioPedidos relatorio = new RelatorioPedidos();

		try {
			PedidoDao pedidodao = new PedidoDao();
			List<ListaPedido> listaDePedidos = new ArrayList<>();

			ArrayList<ListaPedido> arraypedido = new ArrayList<>();

			arraypedido = pedidodao.listarTodosPedidos();

			for (ListaPedido contador : arraypedido) {

				ListaPedido pedido = new ListaPedido();

				pedido.setCod_pedido(contador.getCod_pedido());
				pedido.setData_pedido(contador.getData_pedido());
				pedido.setCondicao_pagamento_pedido(contador.getCondicao_pagamento_pedido());
				pedido.setTipo_pedido(contador.getTipo_pedido());
				pedido.setNome_cliente(contador.getNome_cliente());
				pedido.setCpf_cliente(contador.getCpf_cliente());
				pedido.setNome_produto(contador.getNome_produto());
				pedido.setQuantidade_item(contador.getQuantidade_item());
				pedido.setPreco_total_item(contador.getPreco_total_item());
				pedido.setValor_venda_produto(contador.getValor_venda_produto());

				listaDePedidos.add(pedido);
			}

			relatorio.gerarRelatorio(listaDePedidos);

		} catch (JRException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	void relatorioPedidoOpcaoPagamento() {
		RelatorioPedidos relatorio = new RelatorioPedidos();

		try {
			PedidoDao pedidodao = new PedidoDao();
			List<ListaPedido> listaDePedidos = new ArrayList<>();

			ArrayList<ListaPedido> arraypedido = new ArrayList<>();
			Boolean trata = true;

			do {
				String[] options = { "Selecione a opção", "Dinheiro", "Pix", "Débito", "Credito" };
				ImageIcon icon = new ImageIcon("src/icones/lupa.png");
				String n = (String) JOptionPane.showInputDialog(null, "Selecione Opção Desejada",
						"Condição de Pagamento", JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
				System.out.println(n);
				String opcao = n;

				switch (opcao) {
				case "Selecione a opção":
					JOptionPane.showMessageDialog(null, "Selecione uma opção válida!");
					trata = false;
					break;

				case "Dinheiro":
					listaDePedidos = pedidodao.listarPedidoPorCondicaoPagamento(opcao);
					trata = true;
					break;

				case "Pix":
					listaDePedidos = pedidodao.listarPedidoPorCondicaoPagamento(opcao);
					trata = true;
					break;
					
				case "Débito":
					listaDePedidos = pedidodao.listarPedidoPorCondicaoPagamento(opcao);
					trata = true;
					break;
					
				case "Credito":
					listaDePedidos = pedidodao.listarPedidoPorCondicaoPagamento(opcao);
					trata = true;
					break;
				}
			} while (trata == false);

			for (ListaPedido contador : arraypedido) {
				ListaPedido pedido = new ListaPedido();

				pedido.setCod_pedido(contador.getCod_pedido());
				pedido.setData_pedido(contador.getData_pedido());
				pedido.setCondicao_pagamento_pedido(contador.getCondicao_pagamento_pedido());
				pedido.setTipo_pedido(contador.getTipo_pedido());
				pedido.setNome_cliente(contador.getNome_cliente());
				pedido.setCpf_cliente(contador.getCpf_cliente());
				pedido.setNome_produto(contador.getNome_produto());
				pedido.setQuantidade_item(contador.getQuantidade_item());
				pedido.setPreco_total_item(contador.getPreco_total_item());
				pedido.setValor_venda_produto(contador.getValor_venda_produto());

				listaDePedidos.add(pedido);
			}

			relatorio.gerarRelatorio(listaDePedidos);

		} catch (JRException e1) {
			JOptionPane.showMessageDialog(null, "Tempo entre essas datas não possui nada!");
		}
	}

	void relatorioPedidoPorNome(String nome) {
		RelatorioPedidos relatorio = new RelatorioPedidos();

		try {
			PedidoDao pedidodao = new PedidoDao();
			List<ListaPedido> listaDePedidos = new ArrayList<>();

			ArrayList<ListaPedido> arraypedido = new ArrayList<>();

			arraypedido = pedidodao.listarPedidoPorNomeCliente(nome);

			for (ListaPedido contador : arraypedido) {
				ListaPedido pedido = new ListaPedido();

				pedido.setCod_pedido(contador.getCod_pedido());
				pedido.setData_pedido(contador.getData_pedido());
				pedido.setCondicao_pagamento_pedido(contador.getCondicao_pagamento_pedido());
				pedido.setTipo_pedido(contador.getTipo_pedido());
				pedido.setNome_cliente(contador.getNome_cliente());
				pedido.setCpf_cliente(contador.getCpf_cliente());
				pedido.setNome_produto(contador.getNome_produto());
				pedido.setQuantidade_item(contador.getQuantidade_item());
				pedido.setPreco_total_item(contador.getPreco_total_item());
				pedido.setValor_venda_produto(contador.getValor_venda_produto());

				listaDePedidos.add(pedido);
			}

			relatorio.gerarRelatorio(listaDePedidos);

		} catch (JRException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}
	
	void relatorioPedidoPorCodigo(Integer codigo) {
		RelatorioPedidos relatorio = new RelatorioPedidos();

		try {
			PedidoDao pedidodao = new PedidoDao();
			List<ListaPedido> listaDePedidos = new ArrayList<>();

			ArrayList<ListaPedido> arraypedido = new ArrayList<>();

			

			arraypedido = pedidodao.listarPedidoPorCodigo(codigo);

			for (ListaPedido contador : arraypedido) {
				ListaPedido pedido = new ListaPedido();

				pedido.setCod_pedido(contador.getCod_pedido());
				pedido.setData_pedido(contador.getData_pedido());
				pedido.setCondicao_pagamento_pedido(contador.getCondicao_pagamento_pedido());
				pedido.setTipo_pedido(contador.getTipo_pedido());
				pedido.setNome_cliente(contador.getNome_cliente());
				pedido.setCpf_cliente(contador.getCpf_cliente());
				pedido.setNome_produto(contador.getNome_produto());
				pedido.setQuantidade_item(contador.getQuantidade_item());
				pedido.setPreco_total_item(contador.getPreco_total_item());
				pedido.setValor_venda_produto(contador.getValor_venda_produto());

				listaDePedidos.add(pedido);
			}

			relatorio.gerarRelatorio(listaDePedidos);

		} catch (JRException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}


	void relatorioPedidoPorCpf(String cpf) {

		RelatorioPedidos relatorio = new RelatorioPedidos();

		try {
			PedidoDao pedidodao = new PedidoDao();
			List<ListaPedido> listaDePedidos = new ArrayList<>();

			ArrayList<ListaPedido> arraypedido = new ArrayList<>();

			arraypedido = pedidodao.listarPedidoPorCpfCliente(cpf);

			for (ListaPedido contador : arraypedido) {
				ListaPedido pedido = new ListaPedido();

				pedido.setCod_pedido(contador.getCod_pedido());
				pedido.setData_pedido(contador.getData_pedido());
				pedido.setCondicao_pagamento_pedido(contador.getCondicao_pagamento_pedido());
				pedido.setTipo_pedido(contador.getTipo_pedido());
				pedido.setNome_cliente(contador.getNome_cliente());
				pedido.setCpf_cliente(contador.getCpf_cliente());
				pedido.setNome_produto(contador.getNome_produto());
				pedido.setQuantidade_item(contador.getQuantidade_item());
				pedido.setPreco_total_item(contador.getPreco_total_item());
				pedido.setValor_venda_produto(contador.getValor_venda_produto());

				listaDePedidos.add(pedido);
			}

			relatorio.gerarRelatorio(listaDePedidos);

		} catch (JRException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	void relatorioPedidoEntreDatas(String data1, String data2) {
		RelatorioPedidos relatorio = new RelatorioPedidos();

		try {
			PedidoDao pedidodao = new PedidoDao();
			List<ListaPedido> listaDePedidos = new ArrayList<>();

			ArrayList<ListaPedido> arraypedido = new ArrayList<>();

			arraypedido = pedidodao.listarPedidoEntreDatas(data1, data2);

			for (ListaPedido contador : arraypedido) {
				ListaPedido pedido = new ListaPedido();

				pedido.setCod_pedido(contador.getCod_pedido());
				pedido.setData_pedido(contador.getData_pedido());
				pedido.setCondicao_pagamento_pedido(contador.getCondicao_pagamento_pedido());
				pedido.setTipo_pedido(contador.getTipo_pedido());
				pedido.setNome_cliente(contador.getNome_cliente());
				pedido.setCpf_cliente(contador.getCpf_cliente());
				pedido.setNome_produto(contador.getNome_produto());
				pedido.setQuantidade_item(contador.getQuantidade_item());
				pedido.setPreco_total_item(contador.getPreco_total_item());
				pedido.setValor_venda_produto(contador.getValor_venda_produto());

				listaDePedidos.add(pedido);
			}

			relatorio.gerarRelatorio(listaDePedidos);

		} catch (JRException e1) {
			JOptionPane.showMessageDialog(null, "Tempo entre essas datas não possui nada!");
		}
	}

	void relatorioPedidoPorData(String data) {
		RelatorioPedidos relatorio = new RelatorioPedidos();

		try {
			PedidoDao pedidodao = new PedidoDao();
			List<ListaPedido> listaDePedidos = new ArrayList<>();

			ArrayList<ListaPedido> arraypedido = new ArrayList<>();

			arraypedido = pedidodao.listarPedidoPorData(data);

			for (ListaPedido contador : arraypedido) {
				ListaPedido pedido = new ListaPedido();

				pedido.setCod_pedido(contador.getCod_pedido());
				pedido.setData_pedido(contador.getData_pedido());
				pedido.setCondicao_pagamento_pedido(contador.getCondicao_pagamento_pedido());
				pedido.setTipo_pedido(contador.getTipo_pedido());
				pedido.setNome_cliente(contador.getNome_cliente());
				pedido.setCpf_cliente(contador.getCpf_cliente());
				pedido.setNome_produto(contador.getNome_produto());
				pedido.setQuantidade_item(contador.getQuantidade_item());
				pedido.setPreco_total_item(contador.getPreco_total_item());
				pedido.setValor_venda_produto(contador.getValor_venda_produto());

				listaDePedidos.add(pedido);
			}

			relatorio.gerarRelatorio(listaDePedidos);

		} catch (JRException e1) {
			JOptionPane.showMessageDialog(null, "Tempo entre essas datas não possui nada!");
		}
	}

	void relatorioProduto() {
		RelatorioProdutos relatorio = new RelatorioProdutos();

		try {
			ProdutoDao produtodao = new ProdutoDao();
			List<Produto> listaDeProdutos = new ArrayList<>();

			ArrayList<Produto> arrayproduto = new ArrayList<>();

			arrayproduto = produtodao.listarTodosProdutos();

			for (Produto contador : arrayproduto) {
				Produto produto = new Produto();

				produto.setCod_produto(contador.getCod_produto());
				produto.setNome_produto(contador.getNome_produto());
				produto.setQuantidade_produto(contador.getQuantidade_produto());
				produto.setValor_compra_produto(contador.getValor_compra_produto());
				produto.setValor_venda_produto(contador.getValor_venda_produto());
				produto.setDescricao_produto(contador.getDescricao_produto());
				produto.setCod_marca_produto(contador.getCod_marca_produto());

				System.out.println(produto.getCod_produto());
				System.out.println(produto.getNome_produto());
				System.out.println(produto.getQuantidade_produto());
				System.out.println(produto.getValor_compra_produto());
				System.out.println(produto.getValor_venda_produto());
				System.out.println(produto.getDescricao_produto());
				System.out.println(produto.getCod_marca_produto());

				listaDeProdutos.add(produto);
			}

			relatorio.gerarRelatorio(listaDeProdutos);

		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}

	void relatorioCliente() {
		RelatorioCliente relatorio = new RelatorioCliente();

		try {
			ClienteDao clientedao = new ClienteDao();
			List<Cliente> listaDeClientes = new ArrayList<>();

			ArrayList<Cliente> arraycliente = new ArrayList<>();

			arraycliente = clientedao.listarTodosClientes();

			for (Cliente contador : arraycliente) {
				Cliente clientes = new Cliente();

				clientes.setCod_cliente(contador.getCod_cliente());
				clientes.setNome_cliente(contador.getNome_cliente());
				clientes.setCpf_cliente(contador.getCpf_cliente());
				clientes.setRg_cliente(contador.getRg_cliente());
				clientes.setEmail_cliente(contador.getEmail_cliente());
				clientes.setTelefone_cliente(contador.getTelefone_cliente());
				clientes.setEndereco_cliente(contador.getEndereco_cliente());
				clientes.setBairro_cliente(contador.getBairro_cliente());
				clientes.setCidade_cliente(contador.getCidade_cliente());
				clientes.setUf_cliente(contador.getUf_cliente());
				clientes.setCep_cliente(contador.getCep_cliente());

				listaDeClientes.add(clientes);
			}

			relatorio.gerarRelatorio(listaDeClientes);

		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}

	void relatorioClientePorNome() {
		RelatorioCliente relatorio = new RelatorioCliente();

		try {
			ClienteDao clientedao = new ClienteDao();
			List<Cliente> listaDeClientes = new ArrayList<>();

			ArrayList<Cliente> arraycliente = new ArrayList<>();

			String nome = JOptionPane.showInputDialog("Informe o Nome: ");

			arraycliente = clientedao.listarClientePorNome(nome);

			for (Cliente contador : arraycliente) {
				Cliente clientes = new Cliente();

				clientes.setCod_cliente(contador.getCod_cliente());
				clientes.setNome_cliente(contador.getNome_cliente());
				clientes.setCpf_cliente(contador.getCpf_cliente());
				clientes.setRg_cliente(contador.getRg_cliente());
				clientes.setEmail_cliente(contador.getEmail_cliente());
				clientes.setTelefone_cliente(contador.getTelefone_cliente());
				clientes.setEndereco_cliente(contador.getEndereco_cliente());
				clientes.setBairro_cliente(contador.getBairro_cliente());
				clientes.setCidade_cliente(contador.getCidade_cliente());
				clientes.setUf_cliente(contador.getUf_cliente());
				clientes.setCep_cliente(contador.getCep_cliente());

				listaDeClientes.add(clientes);
			}

			relatorio.gerarRelatorio(listaDeClientes);

		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}

	void relatorioClientePorCPF() {
		RelatorioCliente relatorio = new RelatorioCliente();

		try {
			ClienteDao clientedao = new ClienteDao();
			List<Cliente> listaDeClientes = new ArrayList<>();

			ArrayList<Cliente> arraycliente = new ArrayList<>();

			String cpf = JOptionPane.showInputDialog("Informe o Cpf: ");

			arraycliente = clientedao.listarClientePorCpf(cpf);

			for (Cliente contador : arraycliente) {
				Cliente clientes = new Cliente();

				clientes.setCod_cliente(contador.getCod_cliente());
				clientes.setNome_cliente(contador.getNome_cliente());
				clientes.setCpf_cliente(contador.getCpf_cliente());
				clientes.setRg_cliente(contador.getRg_cliente());
				clientes.setEmail_cliente(contador.getEmail_cliente());
				clientes.setTelefone_cliente(contador.getTelefone_cliente());
				clientes.setEndereco_cliente(contador.getEndereco_cliente());
				clientes.setBairro_cliente(contador.getBairro_cliente());
				clientes.setCidade_cliente(contador.getCidade_cliente());
				clientes.setUf_cliente(contador.getUf_cliente());
				clientes.setCep_cliente(contador.getCep_cliente());

				listaDeClientes.add(clientes);
			}

			relatorio.gerarRelatorio(listaDeClientes);

		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}
	
	void relatorioComprovanteFiscal(Integer cod_pedido) {
		ComprovanteFiscal relatorio = new ComprovanteFiscal();

		try {
			PedidoDao pedidoDao = new PedidoDao();
			List<ListaPedido> listaDePedidos = new ArrayList<>();

			ArrayList<ListaPedido> arrayPedido = new ArrayList<>();

			arrayPedido = pedidoDao.listarPedidoPorCodigo(cod_pedido);

			for (ListaPedido contador : arrayPedido) {
				ListaPedido itemPedido = new ListaPedido();
				
				itemPedido.setCod_pedido(contador.getCod_pedido());
				itemPedido.setCpf_cliente(contador.getCpf_cliente());
				itemPedido.setNome_cliente(contador.getNome_cliente());
				itemPedido.setEndereco_cliente(contador.getEndereco_cliente());
				itemPedido.setBairro_cliente(contador.getBairro_cliente());
				itemPedido.setCidade_cliente(contador.getCidade_cliente());
				itemPedido.setUf_cliente(contador.getUf_cliente());
				itemPedido.setCep_cliente(contador.getCep_cliente());
				itemPedido.setCod_itens_pedido(contador.getCod_itens_pedido());
				itemPedido.setQuantidade_item(contador.getQuantidade_item());
				itemPedido.setNome_produto(contador.getNome_produto());
				itemPedido.setValor_venda_produto(contador.getValor_venda_produto());
				itemPedido.setPreco_total_item(contador.getPreco_total_item());
				itemPedido.setCondicao_pagamento_pedido(contador.getCondicao_pagamento_pedido());

				listaDePedidos.add(itemPedido);
			}

			relatorio.gerarRelatorio(listaDePedidos);

		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}

	void carregarProdutos() {
		if (p == null || p.isClosed()) { // metodo que verifica se a janela esta aberta, barrando nova abertura
			p = new Produtos();
			desktopPanePrincipal.add(p);

			Dimension tf = p.getSize();// Metodo que centraliza no meio da tela a janela produtos
			p.setLocation((desktopPanePrincipal.getWidth() - tf.width) / 3,
					(desktopPanePrincipal.getHeight() - tf.height) / 3);
			p.show();

		}
	}

	void carregarClientes() {
		if (c == null || c.isClosed()) { // metodo que verifica se a janela esta aberta, barrando nova abertura
			c = new Clientes();
			desktopPanePrincipal.add(c);

			Dimension tf = c.getSize();// Metodo que centraliza no meio da tela a janela cliente
			c.setLocation((desktopPanePrincipal.getWidth() - tf.width) / 2,
					(desktopPanePrincipal.getHeight() - tf.height) / 2);
			c.show();

		}
	}

	void carregarPedidos() {
		if (pedidos == null || pedidos.isClosed()) { // metodo que verifica se a janela esta aberta, barrando nova
														// abertura
			pedidos = new Pedidos();
			desktopPanePrincipal.add(pedidos);

			Dimension tf = pedidos.getSize();// Metodo que centraliza no meio da tela a janela cliente
			pedidos.setLocation((desktopPanePrincipal.getWidth() - tf.width) / 2,
					(desktopPanePrincipal.getHeight() - tf.height) / 2);
			pedidos.show();

		}
	}

	void carregarContatos() {
		if (con == null || con.isClosed()) {
			con = new Contatos();
			desktopPanePrincipal.add(con);
			Dimension tf = con.getSize();// Metodo que centraliza no meio da tela a janela produtos
			con.setLocation((desktopPanePrincipal.getWidth() - tf.width) / 2,
					(desktopPanePrincipal.getHeight() - tf.height) / 2);
			con.show();

		}
	}

	void carregarMarcas() {
		if (m == null || m.isClosed()) {
			m = new Marcas();
			desktopPanePrincipal.add(m);
			Dimension tf = m.getSize();// Metodo que centraliza no meio da tela a janela produtos
			m.setLocation((desktopPanePrincipal.getWidth() - tf.width) / 2,
					(desktopPanePrincipal.getHeight() - tf.height) / 2);
			m.show();

		}
	}

	void listarPedidos() {
		if (lp == null || lp.isClosed()) {
			lp = new ListarPedidos();
			desktopPanePrincipal.add(lp);
			Dimension tf = lp.getSize();// Metodo que centraliza no meio da tela a janela produtos
			lp.setLocation((desktopPanePrincipal.getWidth() - tf.width) / 2,
					(desktopPanePrincipal.getHeight() - tf.height) / 2);
			lp.show();

		}
	}
}