import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import java.awt.SystemColor;

public class AboutWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutWindow frame = new AboutWindow();
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
	public AboutWindow() {
		setTitle("About");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 315, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnCreatedByCurtis = new JTextPane();
		txtpnCreatedByCurtis.setBackground(SystemColor.control);
		txtpnCreatedByCurtis.setEditable(false);
		txtpnCreatedByCurtis.setText("Created by: Curtis Haynes\r\n\r\nCreated on: 11-1-2016\r\n\r\nVersion: 1.0");
		txtpnCreatedByCurtis.setBounds(41, 30, 337, 192);
		contentPane.add(txtpnCreatedByCurtis);
	}

}
