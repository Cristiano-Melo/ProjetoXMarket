package Guis;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

import Conexao.Dao.ProdutoDao;
import Models.Produto;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PesquisaProdutoPedido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	DefaultTableModel model;
	private JTable table;
	private Produto buscaProduto;
	
	
	
	public JTable getTable() {
		return table;
	}

	public Produto getBuscaProduto() {
		return buscaProduto;
	}

	public void setBuscaProduto(Produto buscaProduto) {
		this.buscaProduto = buscaProduto;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * Launch the application.
	 * @return 
	 */
	public static void main(String[] args) {
		
		try {
			PesquisaProdutoPedido dialog = new PesquisaProdutoPedido();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PesquisaProdutoPedido() {
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 414, 175);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		
		model = new DefaultTableModel();
		Object[] colunn = {"Codigo","Produto","Descrição", "Quantidade", "Valor"};
		Object[] row = new Object[5];
		model.setColumnIdentifiers(colunn);
		table.setModel(model);
		
		JButton btnPesquisaNomeProduto = new JButton("Pesquisar");
		btnPesquisaNomeProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buscaProduto = new Produto();
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
		btnPesquisaNomeProduto.setBounds(168, 194, 89, 23);
		contentPanel.add(btnPesquisaNomeProduto);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Produto teste;
						teste = retornaProduto();
//						return teste;
						setVisible(false);
						
					}
				});

				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
	}
	
		public Produto retornaProduto() {
					
					Produto produto = new Produto();
					int contador = table.getSelectedRow();
					System.out.println(contador);
					this.buscaProduto.setCod_produto(model.getValueAt(contador, 0).toString());
					this.buscaProduto.setNome_produto(model.getValueAt(contador, 1).toString());
					this.buscaProduto.setDescricao_produto(model.getValueAt(contador, 2).toString());
					this.buscaProduto.setQuantidade_produto(model.getValueAt(contador, 3).toString());
					this.buscaProduto.setValor_venda_produto(model.getValueAt(contador, 4).toString());
					
			
			return produto;
		}
}
