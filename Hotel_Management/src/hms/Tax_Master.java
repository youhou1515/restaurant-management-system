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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;

public class Tax_Master extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	Databaseconnection dbcon = new Databaseconnection();
	JComboBox comboBox;
	private JButton btnAddalta;
	private JButton btnDeletealtd;
	private JLabel label_1;
	

	public Tax_Master() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width*16/20, dim.height * 17 / 20);
		setUndecorated(true);
		setType(javax.swing.JFrame.Type.UTILITY);
		
//		setBounds(x, y, dim.width *8/ 20, dim.height * 8 / 20);
//		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_decimalonly(textField.getText());
				boolean result1 = Regex_integeronly(textField.getText());
				if (result != true && result1 != true) {
					JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
					textField.setText(null);

				}
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnAddalta.requestFocus();
				}
			}
		});
		textField.setBounds(379, 237, 412, 34);
		textField.setBackground(Color.WHITE);
		textField.setColumns(10);
		contentPanel.add(textField);
		try {
			ShowTax();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblAdddeleteTaxRate = new JLabel("Add/Delete Tax Rate");
		lblAdddeleteTaxRate.setBounds(0, 11, 1092, 23);
		lblAdddeleteTaxRate.setOpaque(true);
		lblAdddeleteTaxRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdddeleteTaxRate.setForeground(Color.BLACK);
		lblAdddeleteTaxRate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdddeleteTaxRate.setBackground(Color.WHITE);
		contentPanel.add(lblAdddeleteTaxRate);
		
		JPanel panel = new JPanel();
		panel.setBounds(264, 123, 544, 35);
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBackground(Color.WHITE);
		contentPanel.add(panel);
		
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
					ShowTax();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				comboBox.setBounds(10, 8, 524, 21);
				panel.add(comboBox);
				comboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						String data1=(String) comboBox.getSelectedItem();
						textField.setText(data1);
						
					}
				});
		
		JLabel label = new JLabel("Name");
		label.setBounds(293, 245, 46, 14);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(label);
		
		btnAddalta = new JButton("ADD [Alt+A]");
		btnAddalta.setMnemonic('A');
		btnAddalta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnDeletealtd.requestFocus();
				}
			}
		});
		btnAddalta.setBounds(349, 337, 172, 35);
		btnAddalta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddalta.addActionListener(new ActionListener() {
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

					dbcon.taxentry(data);
				
					comboBox.removeAllItems();
					comboBox.setModel(new DefaultComboBoxModel(new String[] {"SELECT"}));
					
					ShowTax();
					comboBox.setSelectedItem(null);
					
					textField.setText(null);
//					DefaultTableModel model=(DefaultTableModel) table.getModel();
//					model.setRowCount(0);
//					ShowTaxTable();
					
						}}	} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnAddalta.setForeground(Color.WHITE);
		btnAddalta.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnAddalta.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnAddalta.setBackground(Color.GRAY);
		contentPanel.add(btnAddalta);
		
		btnDeletealtd = new JButton("DELETE [Alt+D]");
		btnDeletealtd.setMnemonic('D');
		btnDeletealtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					comboBox.requestFocus();
				}
			}
		});
		btnDeletealtd.setBounds(619, 337, 172, 35);
		btnDeletealtd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletealtd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
						JOptionPane.showMessageDialog(null, "Please Select Category from List to Delete");
					} else

					{
						String message = "Do you want to Delete ? ";
						String title = "CONFIRM";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							
							
						delete_tax(textField.getText());
						
						comboBox.removeAllItems();
						
						textField.setText(null);
						
						comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
						ShowTax();
						comboBox.setSelectedIndex(0);
						
//						DefaultTableModel model=(DefaultTableModel) table.getModel();
//						model.setRowCount(0);
//						ShowTaxTable();
				
					
						}}} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDeletealtd.setForeground(Color.WHITE);
		btnDeletealtd.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnDeletealtd.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnDeletealtd.setBackground(Color.GRAY);
		contentPanel.add(btnDeletealtd);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Tax_Master.class.getResource("/hms/images/taxlabel.png")));
		label_1.setBounds(454, 438, 215, 177);
		contentPanel.add(label_1);
		try {
			ShowTaxTable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void ShowTax() throws SQLException {
		try {
			dbcon.connect();
			Statement st = dbcon.conn.createStatement();

			ResultSet rs = st.executeQuery("Select gst from gstlist");
			while (rs.next()) {
				String data = rs.getString(1);
				comboBox.addItem(data);

			}
		} catch (Exception e1) {
		} finally {
			dbcon.conn.close();
		}
	}
		public void ShowTaxTable() throws SQLException {
			try {
				
				dbcon.connect();
				String sql = "Select gst from gstlist";
				dbcon.pst = dbcon.conn.prepareStatement(sql);
				dbcon.rs = dbcon.pst.executeQuery();
				
				}
			 catch (Exception e1) {
			} finally {
				dbcon.conn.close();
			}
	}
		
		
	public void delete_tax(String categoryxyz) throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			String sql = "Delete from gstlist where gst='" + categoryxyz + "'";
			int x = dbcon.st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Succesfully Deleted");

		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			dbcon.conn.close();
		}
	}
	
	public boolean Regex_decimalonly(String abc) {
		boolean res = true;
		if (abc.length() != 0) {
			String regex = "^[\\+\\-]{0,1}[0-9]+[\\.\\,]{1}[0-9]+$";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(abc);

			if (matcher.matches() != true) {
				res = false;
			}
		}
		return res;
	}
	public boolean Regex_integeronly(String abc) {
		boolean res = true;
		if (abc.length() != 0) {
			String regex = "[0-9]*";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(abc);

			if (matcher.matches() != true) {
				res = false;
			}
		}
		return res;
	}
}
