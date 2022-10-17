package Guis;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ListaPedidos extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaPedidos frame = new ListaPedidos();
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
	public ListaPedidos() {
		setBounds(100, 100, 450, 300);

	}

}
