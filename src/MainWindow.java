import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.jgoodies.*;
import java.beans.*;
import net.miginfocom.*;
import java.util.*;
import java.sql.*;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {

	static private JFrame frmEmployeeEditor;
	private JTextField textField_id;
	private JTextField textField_fName;
	private JTextField textField_lName;
	private JTextField textField_hDate;
	private JTextField textField_jTitle;
	
	public static EditEmployee editEmp;
	public static int numInput;
	private int add_id = 0;
	private String fName = "",lName = "", hDate = "", jTitle = "";
	private JTextField textField_removeid;
	private JTable employeeList;
	
	Vector columnNamesVector = new Vector();
    Vector dataVector = new Vector();
    
    DefaultTableModel dTableModel;
    private JTextField textField_editId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Connection to the mySQL database
		MySQL.connectMySQL();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					MainWindow window = new MainWindow();
					window.frmEmployeeEditor.setVisible(true);
					frmEmployeeEditor.setResizable(false);
				} 
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});	
	}

	/**
	 * Create the application.
	 */
	public MainWindow() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
    
	private void initialize() {
		frmEmployeeEditor = new JFrame();
		frmEmployeeEditor.setTitle("Employee Editor");
		frmEmployeeEditor.setBounds(800, 600, 810, 584);
		frmEmployeeEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployeeEditor.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmEmployeeEditor.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmRefreshList = new JMenuItem("Refresh List");
		mntmRefreshList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dTableModel.fireTableDataChanged();
				dTableModel.setRowCount(0);
				refresh();
			}
		});
		mnView.add(mntmRefreshList);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutWindow aboutWin = new AboutWindow();
				aboutWin.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		frmEmployeeEditor.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmEmployeeEditor.getContentPane().add(tabbedPane);
		
		JPanel panel_aerEmp = new JPanel();
		tabbedPane.addTab("Add/Edit/Remove", null, panel_aerEmp, null);
		panel_aerEmp.setLayout(null);
		
		JSeparator separator01 = new JSeparator();
		separator01.setOrientation(SwingConstants.VERTICAL);
		separator01.setBounds(255, 11, 20, 484);
		panel_aerEmp.add(separator01);
		
		JSeparator separator02 = new JSeparator();
		separator02.setOrientation(SwingConstants.VERTICAL);
		separator02.setBounds(535, 11, 20, 484);
		panel_aerEmp.add(separator02);
		
		JLabel lblAddEmployee = new JLabel("Add Employee");
		lblAddEmployee.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddEmployee.setBounds(80, 11, 100, 14);
		panel_aerEmp.add(lblAddEmployee);
		
		JLabel lblEditEmployee = new JLabel("Edit Employee");
		lblEditEmployee.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEditEmployee.setBounds(349, 11, 100, 14);
		panel_aerEmp.add(lblEditEmployee);
		
		JLabel lblRemoveEmployee = new JLabel("Remove Employee");
		lblRemoveEmployee.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRemoveEmployee.setBounds(607, 11, 115, 14);
		panel_aerEmp.add(lblRemoveEmployee);
		
		textField_id = new JTextField();
		textField_id.setBounds(10, 68, 235, 20);
		panel_aerEmp.add(textField_id);
		textField_id.setColumns(10);
		
		textField_fName = new JTextField();
		textField_fName.setBounds(10, 124, 235, 20);
		panel_aerEmp.add(textField_fName);
		textField_fName.setColumns(10);
		
		JLabel lbl_addIdNum = new JLabel("ID Number:");
		lbl_addIdNum.setBounds(10, 43, 80, 14);
		panel_aerEmp.add(lbl_addIdNum);
		
		JLabel lbl_addFirstName = new JLabel("First Name:");
		lbl_addFirstName.setBounds(10, 99, 80, 14);
		panel_aerEmp.add(lbl_addFirstName);
		
		textField_lName = new JTextField();
		textField_lName.setBounds(10, 180, 235, 20);
		panel_aerEmp.add(textField_lName);
		textField_lName.setColumns(10);
		
		JLabel lbl_addLastName = new JLabel("Last Name:");
		lbl_addLastName.setBounds(10, 155, 80, 14);
		panel_aerEmp.add(lbl_addLastName);
		
		textField_hDate = new JTextField();
		textField_hDate.setBounds(10, 236, 235, 20);
		panel_aerEmp.add(textField_hDate);
		textField_hDate.setColumns(10);
		
		JLabel lbl_addHireDate = new JLabel("Hire Date:");
		lbl_addHireDate.setBounds(10, 211, 80, 14);
		panel_aerEmp.add(lbl_addHireDate);
		
		textField_jTitle = new JTextField();
		textField_jTitle.setBounds(10, 292, 235, 20);
		panel_aerEmp.add(textField_jTitle);
		textField_jTitle.setColumns(10);
		
		JLabel lbl_addJobTitle = new JLabel("Job Title:");
		lbl_addJobTitle.setBounds(10, 267, 80, 14);
		panel_aerEmp.add(lbl_addJobTitle);
		
		JButton btn_addSubmit = new JButton("Submit");
		btn_addSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int add_id;
				String add_fName, add_lName, add_hDate, add_jTitle;
				try
				{
					add_id = Integer.parseInt(textField_id.getText());
					add_fName = textField_fName.getText();
					add_lName = textField_lName.getText();
					add_hDate = textField_hDate.getText();
					add_jTitle = textField_jTitle.getText();
					
					String add_emp = "INSERT employees (emp_id, emp_firstName, emp_lastName, emp_hireDate, emp_title)" +
					        "VALUE (" + add_id + ", '" + add_fName + "', '" + add_lName + "', '" + add_hDate + "', '" + add_jTitle + "');";
					try 
					{
						MySQL.stmt.executeUpdate(add_emp);
						yesPop();
					}
					
					catch (SQLException e)
					{
						noPop();
					}
				}
				
				catch(java.lang.NumberFormatException e)
				{
					noPop();
				}
				
			}
		});
		btn_addSubmit.setBounds(80, 387, 100, 28);
		panel_aerEmp.add(btn_addSubmit);
		
		JLabel lbl_removeIdNumber = new JLabel("ID Number:");
		lbl_removeIdNumber.setBounds(547, 43, 80, 14);
		panel_aerEmp.add(lbl_removeIdNumber);
		
		textField_removeid = new JTextField();
		textField_removeid.setBounds(547, 68, 242, 20);
		panel_aerEmp.add(textField_removeid);
		textField_removeid.setColumns(10);
		
		JButton btn_removeSubmit = new JButton("Submit");
		btn_removeSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				try
				{
					int remove_id = Integer.parseInt(textField_removeid.getText());
					String remove_emp = "DELETE FROM employees WHERE emp_id =" + String.valueOf(remove_id) + ";";
					
					try
					{
						MySQL.stmt.executeUpdate(remove_emp);
						yesPop();
					}
					
					catch(SQLException | java.lang.NumberFormatException e)
					{
						noPop();
					}
				}
				
				catch(java.lang.NumberFormatException e)
				{
					noPop();
				}
			}
		});
		btn_removeSubmit.setBounds(622, 387, 100, 28);
		panel_aerEmp.add(btn_removeSubmit);
		
		textField_editId = new JTextField();
		textField_editId.setBounds(267, 68, 258, 20);
		panel_aerEmp.add(textField_editId);
		textField_editId.setColumns(10);
		
		JLabel lbl_editIdNum = new JLabel("ID Number:");
		lbl_editIdNum.setBounds(268, 43, 80, 14);
		panel_aerEmp.add(lbl_editIdNum);
		
		JButton btn_editSubmit = new JButton("Submit");
		btn_editSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try 
				{
					numInput = Integer.valueOf(textField_editId.getText().toString());
					String queryVar = "SELECT emp_firstName FROM employees WHERE emp_id=" + numInput + ";";
					ResultSet rs = MySQL.stmt.executeQuery(queryVar);
					
					if(rs.next())
					{
						MySQL.stmt.executeQuery(queryVar);
						
						editEmp = new EditEmployee();

						editEmp.setVisible( true );
						editEmp.setSize( 450,400 );
					    editEmp.setLocationRelativeTo( null );
					    editEmp.setResizable(false); 
					}
					
					else
					{
						noPop();
						System.out.println("emp_id isn't in the schema.");
					}
									
				} 
				
				catch (java.lang.NumberFormatException | SQLException e) 
				{
					noPop();
				}
			}
		});
		btn_editSubmit.setBounds(349, 387, 100, 28);
		panel_aerEmp.add(btn_editSubmit);
		
		
		refresh();
		
		JScrollPane panel_viewEmp = new JScrollPane(employeeList);
		tabbedPane.addTab("View Employees", null, panel_viewEmp, null);
		
		
		dTableModel = new DefaultTableModel(dataVector, columnNamesVector) 
		{
	        public Class getColumnClass(int column) 
	        {
	            Class returnValue; 

	            if ((column >= 0) && (column < getColumnCount())) 
	            {
	                returnValue = getValueAt(0, column).getClass();
	            } else 
	            { 
	                returnValue = Object.class;
	            }
	            return returnValue;
	        }
	    };
	    
		
		JTable employeeList = new JTable(dTableModel)
		{
			public boolean isCellEditable(int row, int column) {return false;}
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
		};
		panel_viewEmp.setViewportView(employeeList);
		
		//----------------------------------------------------------------------------------------------------------------------------------------------------

	}
	
	public void refresh()
	{
		ArrayList columnNames = new ArrayList();
		ArrayList data = new ArrayList();
		String slctFrmBase = "SELECT * FROM employees.employees;";
		
		try 
		{
			MySQL.stmt = MySQL.conn.createStatement();
		}
		
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			ResultSet rs = MySQL.stmt.executeQuery(slctFrmBase);
			ResultSetMetaData md = rs.getMetaData();
			
			int columns = md.getColumnCount();
			
		//  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }

                data.add( row );
            }
		}
		
		catch (SQLException e)
        {
        	System.out.println( e.getMessage() );
        }

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));
	}
	
	public void noPop()
	{
		JOptionPane.showMessageDialog(null, "Could not submit", "Error" , JOptionPane.WARNING_MESSAGE);
		System.out.println("Error");
	}
	
	public void yesPop()
	{
		JOptionPane.showMessageDialog(null, "Submission Successful", "Submitted", JOptionPane.PLAIN_MESSAGE);
	}
}
