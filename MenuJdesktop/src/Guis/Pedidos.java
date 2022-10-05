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

public class Pedidos extends JInternalFrame {
	private JTextField textFieldCpf;
	private JTextField textFieldNomeCliente;
	private JTextField textFieldData;
	private JTextField textFieldCompra;
	private JTable table;
	private JTextField textFieldViewMarca;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		setFrameIcon(new ImageIcon(Pedidos.class.getResource("/Icones/produtos.png")));
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

		textFieldCompra = new JTextField();
		textFieldCompra.setEditable(false);
		textFieldCompra.setColumns(10);
		textFieldCompra.setBackground(new Color(225, 225, 225));
		textFieldCompra.setBounds(109, 72, 46, 20);
		panel.add(textFieldCompra);

		JLabel lblCodMarca = new JLabel("Quantidade:");
		lblCodMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodMarca.setBounds(546, 72, 80, 20);
		panel.add(lblCodMarca);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 101, 693, 142);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(UIManager.getColor("Button.light"));
		table.setForeground(SystemColor.activeCaption);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		textFieldViewMarca = new JTextField();
		textFieldViewMarca.setBackground(new Color(225, 225, 225));
		textFieldViewMarca.setBounds(627, 73, 89, 20);
		panel.add(textFieldViewMarca);
		textFieldViewMarca.setColumns(10);

		JButton btnNewButton = new JButton("Gravar");
		btnNewButton.setBounds(259, 287, 89, 23);
		panel.add(btnNewButton);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(382, 287, 89, 23);
		panel.add(btnLimpar);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Pedido");
		rdbtnNewRadioButton.setBounds(290, 7, 90, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnOramento = new JRadioButton("Orçamento");
		rdbtnOramento.setBounds(382, 7, 109, 23);
		panel.add(rdbtnOramento);
		
		JLabel lblPeoduto = new JLabel("Produto Cod:");
		lblPeoduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPeoduto.setBounds(23, 72, 94, 20);
		panel.add(lblPeoduto);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(new Color(225, 225, 225));
		textField.setBounds(165, 72, 371, 20);
		panel.add(textField);
		
		JLabel lblQtdItens = new JLabel("Qtd Itens:");
		lblQtdItens.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQtdItens.setBounds(23, 254, 94, 20);
		panel.add(lblQtdItens);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(225, 225, 225));
		textField_1.setBounds(99, 254, 46, 20);
		panel.add(textField_1);
		
		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorTotal.setBounds(546, 254, 80, 20);
		panel.add(lblValorTotal);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(225, 225, 225));
		textField_2.setBounds(627, 255, 89, 20);
		panel.add(textField_2);

	}
}
