package setup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.filechooser.FileFilter;

import com.toedter.calendar.JDateChooser;

import hms.Databaseconnection;
import hms.Tax_Master;
import hms.homepage;
import net.proteanit.sql.DbUtils;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import javax.swing.JPasswordField;

public class Validation extends JFrame {

	private final JPanel contentPanel = new JPanel();
	Databaseconnection dbcon = new Databaseconnection();
	JFileChooser fc = new JFileChooser();
	FileInputStream fis;
	ImageIcon format = null;

	String pathName;
	private JTable table;
	DefaultTableModel model;
	JDateChooser dateChooser_1;
	private JButton btnNewButton;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblNewPassword;
	private JLabel lblNewLabel;
	private JButton btnNewButton_2;

	public Validation() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 10 / 20, dim.height * 11 / 20);

		setType(javax.swing.JFrame.Type.UTILITY);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblShowAllVehicle = new JLabel("Configuration Setup");
		lblShowAllVehicle.setOpaque(true);
		lblShowAllVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowAllVehicle.setForeground(Color.WHITE);
		lblShowAllVehicle.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblShowAllVehicle.setBackground(Color.BLACK);
		lblShowAllVehicle.setBounds(0, 0, 704, 31);
		contentPanel.add(lblShowAllVehicle);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 240));
		panel.setBounds(0, 26, 704, 430);
		contentPanel.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(27, 11, 621, 152);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 621, 152);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {

					DefaultTableModel model1 = (DefaultTableModel) table.getModel();
//				int SelectedRowIndex = table.getSelectedRow();
					if (model1.getRowCount() > 0) {

						int x = table.getSelectedRow();
						String item = model1.getValueAt(x, 0).toString();

						if (e.getKeyCode() == KeyEvent.VK_DELETE) {
							String message = "Do you want to Delete ? ";
							String title = "CONFIRM";

							int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {

//					JOptionPane.showMessageDialog(null, item);
								model1.removeRow(x);
								dbcon.companydelete(item);
								btnNewButton.setEnabled(true);

							}
						}
					}
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f);
				}

			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "S No", "Company Name", "Address", "Phone No." }));
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		table.getColumnModel().getColumn(1).setMinWidth(175);
		table.getColumnModel().getColumn(2).setPreferredWidth(175);
		table.getColumnModel().getColumn(2).setMinWidth(175);
		table.getColumnModel().getColumn(3).setPreferredWidth(175);
		table.getColumnModel().getColumn(3).setMinWidth(175);
		scrollPane.setViewportView(table);

		JLabel lblValidTo = new JLabel("Valid upto:");
		lblValidTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValidTo.setBounds(27, 198, 94, 35);
		panel.add(lblValidTo);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy/MM/dd");

		dateChooser_1.setDate(new Date());
		dateChooser_1.setBounds(105, 198, 393, 35);
		panel.add(dateChooser_1);

		btnNewButton = new JButton("Validate!");
		btnNewButton.setEnabled(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String data13 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				
						try {
							dbcon.validate(data13, "");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						
					
				}
			}
		});
		btnNewButton.setBounds(533, 199, 115, 35);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("::Click to Change Password::");
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwordField_1.setVisible(true);
				passwordField.setVisible(true);
				lblNewLabel.setVisible(true);
				lblNewPassword.setVisible(true);
				btnNewButton_2.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(27, 248, 181, 70);
		panel.add(btnNewButton_1);

		passwordField = new JPasswordField();
		passwordField.setVisible(false);
		passwordField.setBounds(334, 244, 165, 35);
		panel.add(passwordField);

		lblNewLabel = new JLabel("Enter Password:");
		lblNewLabel.setVisible(false);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(228, 248, 105, 24);
		panel.add(lblNewLabel);

		lblNewPassword = new JLabel("Confirm Password:");
		lblNewPassword.setVisible(false);
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewPassword.setBounds(218, 294, 115, 24);
		panel.add(lblNewPassword);

		passwordField_1 = new JPasswordField();
		passwordField_1.setVisible(false);
		passwordField_1.setBounds(333, 290, 165, 35);
		panel.add(passwordField_1);
		
		btnNewButton_2 = new JButton("Submit");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.setVisible(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String password="",password1="";
				
				try {
				password=new String(passwordField.getPassword());
				}
				catch(Exception f)
				{
					password="";
					
				}
				try {
					 password1 = new String(passwordField_1.getPassword());
					}
					catch(Exception f)
					{
						password1="";
						
					}
				
			
				if(password.equals(password1))
				{
					try {
						
						if(password1.length()>0 || password.length()>0)
						{
					
						dbcon.updateprofilepassword(password, password1);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "please enter password first!!");
							
						}
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
				}
				
			}
				else {
					
					JOptionPane.showMessageDialog(null, "Enter Password & Confirm Password doesn't match");
				}	
			}
		});
		btnNewButton_2.setBounds(534, 260, 114, 39);
		panel.add(btnNewButton_2);
		
		JLabel lblNoteSelectCompany = new JLabel("Note: Select company and press Delete to Delete Company Profile");
		lblNoteSelectCompany.setBounds(27, 162, 472, 14);
		panel.add(lblNoteSelectCompany);

		try {
			Table2();
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}

	}

	public void Table2() throws Exception {
		try {
			model = (DefaultTableModel) table.getModel();
			dbcon.connect();
			String sql = "Select sno,companyname,address,city,phoneno from companyprofile where sno is not null";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				String companyname = dbcon.rs.getString("companyname");
				String address = dbcon.rs.getString("address");
				int i = dbcon.rs.getInt("sno");
				String city = dbcon.rs.getString("city");

				String phoneno = dbcon.rs.getString("phoneno");

				model.addRow(new Object[] { i, companyname, address, city, phoneno });
				btnNewButton.setEnabled(true);

			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			try {

				dbcon.conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
}
