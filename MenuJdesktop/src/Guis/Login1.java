package Guis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import Conexao.Dao.LoginDao;
import Models.Login;
import javax.swing.JPasswordField;



public class Login1 extends JFrame {
	
	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login1 frame = new Login1();
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
	public Login1() {
		setTitle("PDV - XMarket");
		setFocusable(true);
		setBounds(100, 100, 499, 365);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, 483, 334);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(153, 204, 204));
		passwordField.setBounds(219, 245, 133, 20);
		panel.add(passwordField);
		
		JTextField textFieldCampoUsuario = new JTextField();
		textFieldCampoUsuario.setBackground(new Color(153, 204, 204));
		textFieldCampoUsuario.setBounds(219, 214, 133, 20);
		panel.add(textFieldCampoUsuario);
		textFieldCampoUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
		lblUsuario.setBounds(157, 208, 77, 31);
		panel.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.BLACK);
		lblSenha.setFont(new Font("Arial", Font.BOLD, 14));
		lblSenha.setBounds(157, 240, 46, 28);
		panel.add(lblSenha);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				LoginDao loginDao = new LoginDao();
				Login login = new Login();
				login.setUsuario(textFieldCampoUsuario.getText());
				login.setSenha(passwordField.getText());
				
				Boolean fechaTela = loginDao.logar(login);
				
				if(!fechaTela) {
				 dispose();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Arial", btnNewButton.getFont().getStyle(), btnNewButton.getFont().getSize() + 2));
		btnNewButton.setBounds(219, 286, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(SystemColor.desktop);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Imagens/CapturarTela.PNG")));
		lblNewLabel.setBounds(10, 11, 463, 312);
		panel.add(lblNewLabel);
}
}
