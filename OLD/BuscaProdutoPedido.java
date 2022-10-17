package Guis;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Conexao.Dao.ProdutoDao;
import Models.Produto;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class BuscaProdutoPedido extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscaProdutoPedido frame = new BuscaProdutoPedido();
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
	public BuscaProdutoPedido() {
		
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 185);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel();
		Object[] colunn = {"Codigo","Produto","Descrição", "Quantidade", "Valor"};
		Object[] row = new Object[5];
		model.setColumnIdentifiers(colunn);
		table.setModel(model);
		
		JButton btnPesquisa = new JButton("Pesquisa");
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto produto = new Produto();
				ProdutoDao produtodao = new ProdutoDao();

				if (model.getRowCount() != 0) {
					model.setRowCount(0);
				}
				String nome = JOptionPane.showInputDialog("Informe o Nome: ");

				ArrayList<Produto> listaDeProdutos = new ArrayList<>();
				listaDeProdutos = produtodao.listarProdutoPorNome(nome);

				for (Produto contador : listaDeProdutos) {
					row[0] = contador.getCod_produto();
					row[1] = contador.getNome_produto();
					row[2] = contador.getDescricao_produto();
					row[3] = contador.getQuantidade_produto();
					row[4] = contador.getValor_venda_produto();
					model.addRow(row);
				}
				
			}
		});
		btnPesquisa.setBounds(10, 206, 118, 44);
		contentPane.add(btnPesquisa);
		
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(139, 206, 139, 44);
		contentPane.add(btnOK);
		
	}
	public Produto retornaProduto() {
		
		Produto produto = new Produto();
		int contador = table.getSelectedRow();
		System.out.println(contador);
		produto.setCod_produto(model.getValueAt(contador, 0).toString());
		produto.setNome_produto(model.getValueAt(contador, 1).toString());
		produto.setDescricao_produto(model.getValueAt(contador, 2).toString());
		produto.setQuantidade_produto(model.getValueAt(contador, 3).toString());
		produto.setValor_venda_produto(model.getValueAt(contador, 4).toString());
		
		
		return produto;
	}
}
