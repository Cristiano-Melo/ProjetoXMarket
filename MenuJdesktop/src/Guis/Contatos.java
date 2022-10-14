package Guis;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Contatos extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contatos frame = new Contatos();
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
	public Contatos() {
		setClosable(true);
		setBorder(new TitledBorder(null, "", TitledBorder.TRAILING, TitledBorder.TOP, null, null));
		setTitle("Contatos");
		setFrameIcon(new ImageIcon(Contatos.class.getResource("/Icones/icons8-suporte-on-line-100.png")));
		setBounds(100, 100, 738, 469);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 714, 403);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(Color.LIGHT_GRAY);
		panel_1_1.setBounds(198, 57, 160, 39);
		panel.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Filiais");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setBounds(61, 11, 42, 14);
		panel_1_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBackground(Color.LIGHT_GRAY);
		panel_1_1_1.setBounds(368, 57, 160, 39);
		panel.add(panel_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Suporte");
		lblNewLabel_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1.setBounds(60, 11, 57, 14);
		panel_1_1_1.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBackground(Color.LIGHT_GRAY);
		panel_1_1_2.setBounds(538, 57, 160, 39);
		panel.add(panel_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Sac");
		lblNewLabel_1_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1_1.setBounds(63, 11, 37, 14);
		panel_1_1_2.add(lblNewLabel_1_1_1_1);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_1_1_3 = new JPanel();
		panel_1_1_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1_3.setLayout(null);
		panel_1_1_3.setBackground(Color.LIGHT_GRAY);
		panel_1_1_3.setBounds(28, 57, 160, 39);
		panel.add(panel_1_1_3);
		
		JLabel lblNewLabel_1 = new JLabel("Administração");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(29, 11, 96, 14);
		panel_1_1_3.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(UIManager.getColor("Button.darkShadow"));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(28, 11, 670, 35);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Telefones de Contatos");
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		
		JPanel panel_1_1_3_1 = new JPanel();
		panel_1_1_3_1.setLayout(null);
		panel_1_1_3_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1_3_1.setBackground(UIManager.getColor("Button.shadow"));
		panel_1_1_3_1.setBounds(28, 103, 160, 263);
		panel.add(panel_1_1_3_1);
		
		JLabel lblNewLabel_2 = new JLabel("Juridico ramal 3030-0303");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 11, 149, 24);
		panel_1_1_3_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Comercial ramal 3030-0301");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(10, 43, 149, 24);
		panel_1_1_3_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Dpt Pessoal  3030-0302");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_2.setBounds(10, 78, 149, 24);
		panel_1_1_3_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("RH  ramal 3030-0304");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_3.setBounds(10, 113, 149, 24);
		panel_1_1_3_1.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Marketing  ramal 3030-0305");
		lblNewLabel_2_4.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_4.setBounds(10, 148, 149, 24);
		panel_1_1_3_1.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Finanças ramal 3030-0306");
		lblNewLabel_2_5.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_5.setBounds(10, 183, 149, 24);
		panel_1_1_3_1.add(lblNewLabel_2_5);
		
		JPanel panel_1_1_3_1_1 = new JPanel();
		panel_1_1_3_1_1.setLayout(null);
		panel_1_1_3_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1_3_1_1.setBackground(UIManager.getColor("Button.shadow"));
		panel_1_1_3_1_1.setBounds(198, 103, 160, 263);
		panel.add(panel_1_1_3_1_1);
		
		JLabel lblNewLabel_2_6 = new JLabel("Juridico ramal 3030-0303");
		lblNewLabel_2_6.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_6.setBounds(10, 11, 149, 24);
		panel_1_1_3_1_1.add(lblNewLabel_2_6);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Comercial ramal 3030-0301");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_1_1.setBounds(10, 43, 149, 24);
		panel_1_1_3_1_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Dpt Pessoal  3030-0302");
		lblNewLabel_2_2_1.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_2_1.setBounds(10, 78, 149, 24);
		panel_1_1_3_1_1.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("RH  ramal 3030-0304");
		lblNewLabel_2_3_1.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_3_1.setBounds(10, 113, 149, 24);
		panel_1_1_3_1_1.add(lblNewLabel_2_3_1);
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Marketing  ramal 3030-0305");
		lblNewLabel_2_4_1.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_4_1.setBounds(10, 148, 149, 24);
		panel_1_1_3_1_1.add(lblNewLabel_2_4_1);
		
		JLabel lblNewLabel_2_5_1 = new JLabel("Finanças ramal 3030-0306");
		lblNewLabel_2_5_1.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_5_1.setBounds(10, 183, 149, 24);
		panel_1_1_3_1_1.add(lblNewLabel_2_5_1);
		
		JPanel panel_1_1_3_1_2 = new JPanel();
		panel_1_1_3_1_2.setLayout(null);
		panel_1_1_3_1_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1_3_1_2.setBackground(UIManager.getColor("Button.shadow"));
		panel_1_1_3_1_2.setBounds(368, 103, 160, 263);
		panel.add(panel_1_1_3_1_2);
		
		JLabel lblNewLabel_2_7 = new JLabel("Juridico ramal 3030-0303");
		lblNewLabel_2_7.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_7.setBounds(10, 11, 149, 24);
		panel_1_1_3_1_2.add(lblNewLabel_2_7);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Comercial ramal 3030-0301");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_1_2.setBounds(10, 43, 149, 24);
		panel_1_1_3_1_2.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("Dpt Pessoal  3030-0302");
		lblNewLabel_2_2_2.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_2_2.setBounds(10, 78, 149, 24);
		panel_1_1_3_1_2.add(lblNewLabel_2_2_2);
		
		JLabel lblNewLabel_2_3_2 = new JLabel("RH  ramal 3030-0304");
		lblNewLabel_2_3_2.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_3_2.setBounds(10, 113, 149, 24);
		panel_1_1_3_1_2.add(lblNewLabel_2_3_2);
		
		JLabel lblNewLabel_2_4_2 = new JLabel("Marketing  ramal 3030-0305");
		lblNewLabel_2_4_2.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_4_2.setBounds(10, 148, 149, 24);
		panel_1_1_3_1_2.add(lblNewLabel_2_4_2);
		
		JLabel lblNewLabel_2_5_2 = new JLabel("Finanças ramal 3030-0306");
		lblNewLabel_2_5_2.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_5_2.setBounds(10, 183, 149, 24);
		panel_1_1_3_1_2.add(lblNewLabel_2_5_2);
		
		JPanel panel_1_1_3_1_3 = new JPanel();
		panel_1_1_3_1_3.setLayout(null);
		panel_1_1_3_1_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1_3_1_3.setBackground(UIManager.getColor("Button.shadow"));
		panel_1_1_3_1_3.setBounds(538, 103, 160, 263);
		panel.add(panel_1_1_3_1_3);
		
		JLabel lblNewLabel_2_8 = new JLabel("Juridico ramal 3030-0303");
		lblNewLabel_2_8.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_8.setBounds(10, 11, 149, 24);
		panel_1_1_3_1_3.add(lblNewLabel_2_8);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Comercial ramal 3030-0301");
		lblNewLabel_2_1_3.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_1_3.setBounds(10, 43, 149, 24);
		panel_1_1_3_1_3.add(lblNewLabel_2_1_3);
		
		JLabel lblNewLabel_2_2_3 = new JLabel("Dpt Pessoal  3030-0302");
		lblNewLabel_2_2_3.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_2_3.setBounds(10, 78, 149, 24);
		panel_1_1_3_1_3.add(lblNewLabel_2_2_3);
		
		JLabel lblNewLabel_2_3_3 = new JLabel("RH  ramal 3030-0304");
		lblNewLabel_2_3_3.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_3_3.setBounds(10, 113, 149, 24);
		panel_1_1_3_1_3.add(lblNewLabel_2_3_3);
		
		JLabel lblNewLabel_2_4_3 = new JLabel("Marketing  ramal 3030-0305");
		lblNewLabel_2_4_3.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_4_3.setBounds(10, 148, 149, 24);
		panel_1_1_3_1_3.add(lblNewLabel_2_4_3);
		
		JLabel lblNewLabel_2_5_3 = new JLabel("Finanças ramal 3030-0306");
		lblNewLabel_2_5_3.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2_5_3.setBounds(10, 183, 149, 24);
		panel_1_1_3_1_3.add(lblNewLabel_2_5_3);

	}
}
