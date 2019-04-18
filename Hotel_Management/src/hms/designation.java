package hms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;

public class designation extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JComboBox comboBox;
	String tablexyz;
	LocalDate today = LocalDate.now();
	LocalTime time = LocalTime.now();
	Databaseconnection dbcon = new Databaseconnection();
	private JButton button_1;
	private JButton button;
	private JLabel label;

	public designation() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setType(javax.swing.JFrame.Type.UTILITY);
		setUndecorated(true);
	

		// setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(264, 123, 544, 35);
		contentPanel.add(panel);
		panel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField.requestFocus();
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"SELECT"}));
		try {
			showdesignation();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				try {
					dbcon.connect();
					String abc = (String) comboBox.getSelectedItem();
					textField.setText(abc);
					tablexyz = textField.getText();
					dbcon.conn.close();
				} catch (Exception fe) {
				}
			}
		});

		comboBox.setBounds(10, 8, 524, 20);
		panel.add(comboBox);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(293, 245, 46, 14);
		contentPanel.add(lblName);

		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField.setText(null);

				}
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					button_1.requestFocus();
				}
			}
		});
		textField.setBounds(379, 237, 412, 34);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblAdddeleteTable = new JLabel("Add/Delete Designation");
		lblAdddeleteTable.setOpaque(true);
		lblAdddeleteTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdddeleteTable.setForeground(Color.BLACK);
		lblAdddeleteTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdddeleteTable.setBackground(Color.WHITE);
		lblAdddeleteTable.setBounds(0, 11, 1092, 23);
		contentPanel.add(lblAdddeleteTable);

		button = new JButton("DELETE [Alt+D]");
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setToolTipText("Click to Delete");
		button.setMnemonic('D');
		button.setMnemonic(KeyEvent.VK_D);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
						JOptionPane.showMessageDialog(null, "Please Select Designation from List to Delete");
					} else

					{
						String message = "Do you want to Delete ? ";
						String title = "CONFIRM";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							String desig = textField.getText().toUpperCase();
							dbcon.deletedesignation(desig);
							
							comboBox.removeAllItems();
							comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

							showdesignation();
							comboBox.setSelectedIndex(0);

							textField.setText(null);
							
//							DefaultTableModel model = (DefaultTableModel) table.getModel();
//							model.setRowCount(0);
//							ShowdesignationTable();

						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		button.setBackground(Color.GRAY);
		button.setBounds(619, 337, 172, 35);
		contentPanel.add(button);

		button_1 = new JButton("ADD [Alt+A]");
		button_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					button.requestFocus();
				}
			}
		});
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setToolTipText("Click to Add");
		button_1.setMnemonic('A');
		button_1.setMnemonic(KeyEvent.VK_A);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String data = textField.getText().toUpperCase();
					if (data.length() == 0) {

					} else

					{
						String message = "Do you want to add? ";
						String title = "CONFIRM";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							dbcon.insertdesignation(data);
							comboBox.removeAllItems();
							comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
							showdesignation();
							comboBox.setSelectedIndex(0);

							textField.setText(null);
							
//							DefaultTableModel model = (DefaultTableModel) table.getModel();
//							model.setRowCount(0);
//							ShowdesignationTable();
						}
					}
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, "Already exists!");
				} finally {
					try {
						dbcon.conn.close();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_1.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		button_1.setBackground(Color.GRAY);
		button_1.setBounds(349, 337, 172, 35);
		contentPanel.add(button_1);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(designation.class.getResource("/hms/images/maxresdefault.jpg")));
		label.setBounds(483, 440, 215, 177);
		contentPanel.add(label);
		try {
			ShowdesignationTable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
				   comboBox.requestFocus();
			     }
			   } ); 
	}

	private void ShowdesignationTable() throws SQLException {
try {
			
			dbcon.connect();
			String sql = "Select designation from Designationlist order by designation asc";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			
			}
		 catch (Exception e1) {
		} finally {
			dbcon.conn.close();
		}
		
	}

//	public void ShowTable() throws SQLException {
//		try {
//			dbcon.connect();
//			Statement st = dbcon.conn.createStatement();
//			ResultSet rs = st.executeQuery("Select tablename from tablelist");
//			while (rs.next()) {
//				String data = rs.getString(1);
//				comboBox.addItem(data);
//			}
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e);
//		} finally {
//			dbcon.conn.close();
//		}
//
//	}

	public void showdesignation() throws SQLException {

		try {

			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st.executeQuery("Select designation from Designationlist order by designation asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				comboBox.addItem(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "CONTACT SUPPORT TEAM!");
		} finally {
			dbcon.conn.close();
		}

	}
	public boolean Regex_alphanumeric(String abc) {
		boolean res = true;
		if (abc.length() != 0) {
			String regex = "^[a-zA-Z0-9 ]+$";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(abc);

			if (matcher.matches() != true) {
				res = false;
			}
		}
		return res;
	}
}
