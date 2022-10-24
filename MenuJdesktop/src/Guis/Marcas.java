package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Conexao.Dao.MarcaDao;
import Models.Cliente;
import Models.Marca;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Marcas extends JInternalFrame {
	private JTextField textCodigoMarca;
	private JTextField textDescricaoMarca;
	private JTable tblMarcas;;

	DefaultTableModel mdlMarcas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Marcas frame = new Marcas();
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
	public Marcas() {
		setClosable(true);
		setFrameIcon(new ImageIcon(Marcas.class.getResource("/Icones/marca.png")));
		setTitle("Gestão de Marcas");
		setBounds(100, 100, 770, 538);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Marcas");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(302, 0, 154, 48);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 46, 739, 451);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCodMarca = new JLabel("Código");
		lblCodMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodMarca.setBounds(23, 36, 70, 20);
		panel.add(lblCodMarca);

		textCodigoMarca = new JTextField();
		textCodigoMarca.setBackground(new Color(225, 225, 225));
		textCodigoMarca.setBounds(116, 37, 64, 20);
		panel.add(textCodigoMarca);
		textCodigoMarca.setColumns(10);

		JLabel lblDescricaoMarca = new JLabel("Descrição");
		lblDescricaoMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescricaoMarca.setBounds(23, 67, 83, 20);
		panel.add(lblDescricaoMarca);

		textDescricaoMarca = new JTextField();
		textDescricaoMarca.setColumns(10);
		textDescricaoMarca.setBackground(new Color(225, 225, 225));
		textDescricaoMarca.setBounds(116, 67, 294, 20);
		panel.add(textDescricaoMarca);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 145, 693, 261);
		panel.add(scrollPane);

		tblMarcas = new JTable();
		scrollPane.setViewportView(tblMarcas);

		tblMarcas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int contador = tblMarcas.getSelectedRow();
				textCodigoMarca.setText(mdlMarcas.getValueAt(contador, 0).toString());
				textDescricaoMarca.setText(mdlMarcas.getValueAt(contador, 1).toString());
				textCodigoMarca.setEditable(false);
			}
		});

		mdlMarcas = new DefaultTableModel();

		Object[] colunn = { "Código", "Descrição" };
		Object[] row = new Object[2];
		mdlMarcas.setColumnIdentifiers(colunn);
		tblMarcas.setModel(mdlMarcas);
		((DefaultTableModel) mdlMarcas).setRowCount(0);
		tblMarcas.getColumnModel().getColumn(0).setMaxWidth(100);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (validaCampos() == false) {
						return;
					}

					Marca marca = new Marca();

					marca.setCodigoMarca(textCodigoMarca.getText());
					marca.setDescricaoMarca(textDescricaoMarca.getText());

					MarcaDao marcadao = new MarcaDao();
					marcadao.inserirMarca(marca);
					textCodigoMarca.setText("");
					textDescricaoMarca.setText("");

				} catch (Exception erroCadastroMarca) {
					JOptionPane.showMessageDialog(null, erroCadastroMarca);
					JOptionPane.showInternalMessageDialog(null, "Erro no cadastro:" + erroCadastroMarca);
				}

			}
		});
		btnGravar.setBounds(23, 417, 89, 23);
		panel.add(btnGravar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textCodigoMarca.setText("");
				textDescricaoMarca.setText("");
				textCodigoMarca.setEditable(true);

				((DefaultTableModel) mdlMarcas).setRowCount(0);

			}
		});
		btnLimpar.setBounds(487, 417, 89, 23);
		panel.add(btnLimpar);

		JButton btnListarTudo = new JButton("Listar");
		btnListarTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Marca marca = new Marca();
				MarcaDao marcadao = new MarcaDao();

				marcadao.listarTodasMarcas();
				((DefaultTableModel) mdlMarcas).setRowCount(0);

				ArrayList<Marca> listaDeMarcas = new ArrayList<>();
				listaDeMarcas = marcadao.listarTodasMarcas();

				for (Marca contador : listaDeMarcas) {
					row[0] = contador.getCodigoMarca();
					row[1] = contador.getDescricaoMarca();

					mdlMarcas.addRow(row);
				}
			}
		});
		btnListarTudo.setBounds(348, 417, 119, 23);
		panel.add(btnListarTudo);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Marca marca = new Marca();
				MarcaDao marcadao = new MarcaDao();

				marca.setCodigoMarca(textCodigoMarca.getText());
				marca.setDescricaoMarca(textDescricaoMarca.getText());

				String codmarca = textCodigoMarca.getText();

				if (codmarca.equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Nenhuma Marca selecionada.");
					return;
				}

				if (JOptionPane.showConfirmDialog(null, "Confirma alteração da Marca selecionada?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					marcadao.alterarMarcaPorCodigo(marca);
				}
				textCodigoMarca.setEditable(true);
				textCodigoMarca.setText("");
				textDescricaoMarca.setText("");
			}
		});
		btnAlterar.setBounds(130, 417, 89, 23);
		panel.add(btnAlterar);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MarcaDao marcadao = new MarcaDao();
				Marca marca = new Marca();

				marca.setCodigoMarca(textCodigoMarca.getText());
				marca.setDescricaoMarca(textDescricaoMarca.getText());

				String codmarca = textCodigoMarca.getText();
				if (codmarca.equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Nenhuma Marca selecionada.");
					return;
				}

				if (JOptionPane.showConfirmDialog(null, "Confirma exclusão da Marca selecionada?", "SIM",
						JOptionPane.YES_NO_OPTION) == 0) {
					marcadao.deletarMarca(marca);
				}
				textCodigoMarca.setEditable(true);
				textCodigoMarca.setText("");
				textDescricaoMarca.setText("");
			}
		});
		btnDeletar.setBounds(238, 417, 89, 23);
		panel.add(btnDeletar);

	}

	public boolean validaCampos() {
		String codigo = textCodigoMarca.getText();
		String descricao = textDescricaoMarca.getText();

		// Valida código marca
		if (codigo.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Código é preenchimento obrigatório.");
			textCodigoMarca.requestFocus();
			return (false);
		}

		if (!ValidaEntrada.isInt(codigo)) {
			JOptionPane.showInternalMessageDialog(null, "Código somente números.");
			textCodigoMarca.setText("");
			textCodigoMarca.requestFocus();
			return (false);
		}

		if (Integer.parseInt(codigo) < 0) {
			JOptionPane.showInternalMessageDialog(null, "Código não pode ser negativo.");
			textCodigoMarca.setText("");
			textCodigoMarca.requestFocus();
			return (false);
		}

		// Validação nome marca
		if (descricao.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Campo Descrição preenchimento obrigatório.");
			textDescricaoMarca.requestFocus();
			return (false);
		}

		if ((descricao.length() < 2) || (descricao.length() > 100)) {
			JOptionPane.showInternalMessageDialog(null, "Campo Desrição mínimo 2 e máximo 100 caracteres.");
			textDescricaoMarca.requestFocus();
			return (false);
		}

		return (true);

	}

}
