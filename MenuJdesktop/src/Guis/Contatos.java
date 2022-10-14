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
		panel.setBounds(10, 11, 714, 418);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Telefones de Contatos");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(270, 11, 200, 17);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblNewLabel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(UIManager.getColor("Button.light"));
		panel_1_1.setBounds(198, 57, 160, 350);
		panel.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Filiais");
		lblNewLabel_1_1.setBounds(61, 11, 42, 14);
		panel_1_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBackground(UIManager.getColor("Button.light"));
		panel_1_1_1.setBounds(368, 57, 160, 350);
		panel.add(panel_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Suporte");
		lblNewLabel_1_1_1.setBounds(60, 11, 57, 14);
		panel_1_1_1.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBackground(UIManager.getColor("Button.light"));
		panel_1_1_2.setBounds(538, 57, 160, 350);
		panel.add(panel_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Sac");
		lblNewLabel_1_1_1_1.setBounds(63, 11, 37, 14);
		panel_1_1_2.add(lblNewLabel_1_1_1_1);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_1_1_3 = new JPanel();
		panel_1_1_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1_3.setLayout(null);
		panel_1_1_3.setBackground(UIManager.getColor("Button.light"));
		panel_1_1_3.setBounds(28, 57, 160, 350);
		panel.add(panel_1_1_3);
		
		JLabel lblNewLabel_1 = new JLabel("Administração");
		lblNewLabel_1.setBounds(29, 11, 96, 14);
		panel_1_1_3.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));

	}
	
}
