package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.beans.PropertyVetoException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pedidos extends JInternalFrame {
	private JTextField textFieldCpf;
	private JTextField textFieldNomeCliente;
	private JTextField textFieldData;
	private JTextField textFieldProdutoCod;
	private JTable table;
	private JTextField textFieldQuantidade;
	private JTextField textFieldCampoDescricaoCod;
	private JTextField textFieldQtdItens;
	private JTextField textFieldValorTotal;

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
		textFieldNomeCliente.setBounds(229, 41, 348, 20);
		panel.add(textFieldNomeCliente);

		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblData.setBounds(587, 42, 39, 18);
		panel.add(lblData);

		textFieldData = new JTextField();
		textFieldData.setColumns(10);
		textFieldData.setBackground(new Color(225, 225, 225));
		textFieldData.setBounds(627, 39, 89, 20);
		panel.add(textFieldData);

		textFieldProdutoCod = new JTextField();
		textFieldProdutoCod.setEditable(false);
		textFieldProdutoCod.setColumns(10);
		textFieldProdutoCod.setBackground(new Color(225, 225, 225));
		textFieldProdutoCod.setBounds(109, 72, 46, 20);
		panel.add(textFieldProdutoCod);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidade.setBounds(546, 72, 80, 20);
		panel.add(lblQuantidade);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 101, 693, 142);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(UIManager.getColor("Button.light"));
		table.setForeground(SystemColor.activeCaption);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBackground(new Color(225, 225, 225));
		textFieldQuantidade.setBounds(627, 73, 89, 20);
		panel.add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);

		JButton btnNewButton = new JButton("Gravar");
		btnNewButton.setBounds(259, 287, 89, 23);
		panel.add(btnNewButton);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldCpf.setText("");
				textFieldNomeCliente.setText("");
				textFieldData.setText("");
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
		textFieldCampoDescricaoCod.setBounds(165, 72, 371, 20);
		panel.add(textFieldCampoDescricaoCod);

		JLabel lblQtdItens = new JLabel("Qtd Itens:");
		lblQtdItens.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQtdItens.setBounds(23, 254, 94, 20);
		panel.add(lblQtdItens);

		textFieldQtdItens = new JTextField();
		textFieldQtdItens.setEditable(false);
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

	}
}
