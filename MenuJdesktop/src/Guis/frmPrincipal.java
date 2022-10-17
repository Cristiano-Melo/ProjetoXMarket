package Guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
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
import Models.Cliente;
import Models.Login;

public class frmPrincipal extends JFrame {

	private JPanel contentPane;
	private Clientes c;
	private Produtos p;
	private Pedidos pedidos;
	private Login login;
	private Contatos con;
	private JDesktopPane desktopPanePrincipal; // classe do painel deskttoppanel
	protected AbstractButton textFieldCodCliente;
	protected AbstractButton textFieldNomeCliente;
	protected AbstractButton textFieldCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipal frame = new frmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void projetoGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmPrincipal.class.getResource("/Imagens/botao.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1335, 561);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivos = new JMenu("Arquivos");
		mnArquivos.setForeground(new Color(0, 0, 0));
		mnArquivos.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnArquivos);

		JMenuItem mntmClientes = new JMenuItem("Cliente");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarClientes();

			}
		});

		JMenuItem mntmNewMenuItem = new JMenuItem("Login");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//carregarLogin();
			}
		});
		mntmNewMenuItem.setBackground(Color.WHITE);
		mntmNewMenuItem.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/controle-de-acesso.png")));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnArquivos.add(mntmNewMenuItem);
		mntmClientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmClientes.setBackground(new Color(255, 255, 255));
		mntmClientes.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/avatar-de-perfil.png")));
		mnArquivos.add(mntmClientes);

		JMenuItem mntmProdutos = new JMenuItem("Produtos");
		mntmProdutos.setBackground(new Color(255, 255, 255));
		mntmProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarProdutos();
			}
		});
		mntmProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmProdutos.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/produtos-de-higiene.png")));
		mnArquivos.add(mntmProdutos);

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

		JMenuItem mntmFaturamento = new JMenuItem("Faturamento");
		mntmFaturamento.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/relatorio.png")));
		mntmFaturamento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnProcessos.add(mntmFaturamento);

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

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Vendas");
		mntmNewMenuItem_4.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/relatorio.png")));
		mnRelatorio.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Gerencial");
		mntmNewMenuItem_5.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/icons8-verificar-100.png")));
		mnRelatorio.add(mntmNewMenuItem_5);

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
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1309, Short.MAX_VALUE).addGroup(Alignment.LEADING,
						gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(desktopPanePrincipal, GroupLayout.DEFAULT_SIZE, 1289, Short.MAX_VALUE)
								.addGap(55)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(desktopPanePrincipal, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
						.addContainerGap()));

		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBackground(new Color(255, 255, 255));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarClientes();// chamando o metodo void carregar clientes
			}
		});
		btnClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClientes.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/cliente.png")));
		toolBar.add(btnClientes);

		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.setBackground(new Color(255, 255, 255));
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarProdutos();// chamando o metodo void carregar produtos
			}
		});
		btnProdutos.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/produtos.png")));
		btnProdutos.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnProdutos);

		JButton btnNewButton = new JButton("Pedidos");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarPedidos();
			}
		});
		btnNewButton.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/icons8-verificar-100.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnNewButton);

		JButton btnConsultas = new JButton("Consultas");
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputDialog();

			}
		});
		btnConsultas.setBackground(new Color(255, 255, 255));
		btnConsultas.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/lupa.png")));
		btnConsultas.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnConsultas);

		JButton btnRelatorios = new JButton("Relatorios");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnRelatorios.setBackground(new Color(255, 255, 255));
		btnRelatorios.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Icones/relatorio.png")));
		btnRelatorios.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnRelatorios);

		JButton btnContatos = new JButton("Contatos");
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
		System.out.println(n);

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
			carregarPedidos();
			break;

		}

	}

	public frmPrincipal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		projetoGui();
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
}
