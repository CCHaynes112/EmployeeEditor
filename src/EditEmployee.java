import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class EditEmployee extends JFrame {

	private JPanel contentPane;
	static JTextField textField;
	static JTextField textField_1;
	static JTextField textField_2;
	static JTextField textField_3;
	static JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditEmployee frame = new EditEmployee();
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
	public EditEmployee() {
		setTitle("Edit Employee Info");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 414, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 31, 204, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setLabelFor(textField);
		lblNewLabel.setBounds(10, 11, 117, 14);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 82, 204, 20);
		contentPane.add(textField_1);
		
		JLabel lblEmployeeFirstName = new JLabel("Employee First Name");
		lblEmployeeFirstName.setBounds(10, 62, 117, 14);
		contentPane.add(lblEmployeeFirstName);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 133, 204, 20);
		contentPane.add(textField_2);
		
		JLabel lblEmployeeLastName = new JLabel("Employee Last Name");
		lblEmployeeLastName.setBounds(10, 113, 117, 14);
		contentPane.add(lblEmployeeLastName);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 184, 204, 20);
		contentPane.add(textField_3);
		
		JLabel lblEmployeeHireDate = new JLabel("Employee Hire Date");
		lblEmployeeHireDate.setBounds(10, 164, 117, 14);
		contentPane.add(lblEmployeeHireDate);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 235, 204, 20);
		contentPane.add(textField_4);
		
		JLabel lblEmployeeJobTitle = new JLabel("Employee Job Title");
		lblEmployeeJobTitle.setBounds(10, 215, 117, 14);
		contentPane.add(lblEmployeeJobTitle);
		
		JTextPane lbl_descrip = new JTextPane();
		lbl_descrip.setBackground(SystemColor.control);
		lbl_descrip.setEditable(false);
		lbl_descrip.setText("Please enter the information you would like to be updated for the selected employee and hit submit to finalize.");
		lbl_descrip.setBounds(239, 31, 149, 99);
		contentPane.add(lbl_descrip);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				editEmp();
			}
		});
		btnSubmit.setBounds(272, 234, 89, 23);
		contentPane.add(btnSubmit);
	}
	
	public static void editEmp()
	{
		String sqlQuery;
		
		
		if(!textField_1.getText().isEmpty())
		{
			try
			{
				sqlQuery = "UPDATE employees SET emp_firstName='" + textField_1.getText() + "' WHERE emp_id=" + MainWindow.numInput + ";";
				MySQL.stmt.executeUpdate(sqlQuery);
			}
			
			catch(java.lang.NumberFormatException | SQLException e)
			{
				e.getStackTrace();
			}
		}
		
		if(!textField_2.getText().isEmpty())
		{
			try
			{
				sqlQuery = "UPDATE employees SET emp_lastName='" + textField_2.getText() + "' WHERE emp_id=" + MainWindow.numInput + ";";
				MySQL.stmt.executeUpdate(sqlQuery);
			}
			
			catch(java.lang.NumberFormatException | SQLException e)
			{
				e.getStackTrace();
			}
		}
		
		if(!textField_3.getText().isEmpty())
		{
			try
			{
				sqlQuery = "UPDATE employees SET emp_hireDate='" + textField_3.getText() + "' WHERE emp_id=" + MainWindow.numInput + ";";
				MySQL.stmt.executeUpdate(sqlQuery);
			}
			
			catch(java.lang.NumberFormatException | SQLException e)
			{
				e.getStackTrace();
			}
		}
		
		if(!textField_4.getText().isEmpty())
		{
			try
			{
				sqlQuery = "UPDATE employees SET emp_title='" + textField_4.getText() + "' WHERE emp_id=" + MainWindow.numInput + ";";
				MySQL.stmt.executeUpdate(sqlQuery);
			}
			
			catch(java.lang.NumberFormatException | SQLException e)
			{
				e.getStackTrace();
			}
		}
		
		if(!textField.getText().isEmpty())
		{
			try
			{
				
				sqlQuery = "UPDATE employees SET emp_id=" + textField.getText() + " WHERE emp_id=" + MainWindow.numInput + ";";
				System.out.println(textField.getText());
				MySQL.stmt.executeUpdate(sqlQuery);
				
			}
			
			catch(java.lang.NumberFormatException | SQLException e)
			{
				e.getStackTrace();
			}
			
		}
		
		else
		{
			System.out.println("No changes were made.");
		}
		
		MainWindow.editEmp.dispose();
	}
}
