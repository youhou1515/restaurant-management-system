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
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;
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
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;

public class purchase_wages extends JFrame {

	private final JPanel contentPanel = new JPanel();
	String unitxyz;
	Databaseconnection dbcon = new Databaseconnection();
	private JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JTextField textField_2;
	private JTextField textField_3;;

	public purchase_wages(String currentuser) {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setUndecorated(true);
		setType(javax.swing.JFrame.Type.UTILITY);

		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(454, 404, 225, 234);
		contentPanel.add(label_1);
		label_1.setIcon(new ImageIcon(purchase_wages.class.getResource("/hms/images/images45.jpg")));

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(71, 91, 499, 630);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblEmployeeName = new JLabel("Employee Name:");
		lblEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmployeeName.setBounds(10, 46, 109, 14);
		panel.add(lblEmployeeName);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		comboBox.setBounds(116, 39, 336, 33);
		panel.add(comboBox);
		try {
			Showstafflist();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		textField = new JTextField();
		textField.setEditable(false);
		Calendar mCalendar = Calendar.getInstance();
		String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

		textField.setText(month);
		textField.setBounds(116, 87, 373, 33);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_decimalonly(textField_1.getText());
				boolean result1 = Regex_integeronly(textField_1.getText());
				if (result != true && result1 != true) {
					JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
					textField_1.setText(null);

				}
//				try {
//					double salary = Double.parseDouble(textField_1.getText());
//
//				} catch (Exception f) {
//					JOptionPane.showMessageDialog(null, "Please enter valid amount");
//				}
			}
		});
		textField_1.setBounds(116, 139, 373, 33);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblMonth = new JLabel("Month:");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMonth.setBounds(10, 96, 46, 14);
		panel.add(lblMonth);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAmount.setBounds(10, 148, 74, 14);
		panel.add(lblAmount);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
						comboBox.requestFocusInWindow();

					} else {

						try {
							double salary = Double.parseDouble(textField_1.getText());

							String Staffname = comboBox.getSelectedItem().toString();
							String staffid = staffname_selection(Staffname);
							String month = textField.getText().trim();

							dbcon.addstaffdailysalary(staffid, Staffname, salary, month, currentuser);

							clearsalary();

						} catch (Exception f) {
							JOptionPane.showMessageDialog(null, "Please enter valid amount");
						}

					}

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, "Please enter valid amount");
				}

			}
		});
		btnNewButton.setBounds(156, 195, 109, 33);
		panel.add(btnNewButton);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearsalary();
			}
		});
		btnClear.setBounds(275, 195, 109, 33);
		panel.add(btnClear);

		JLabel lblDailySalaryExpenses = new JLabel("Daily Salary Expenses");
		lblDailySalaryExpenses.setBounds(0, 0, 499, 19);
		panel.add(lblDailySalaryExpenses);
		lblDailySalaryExpenses.setOpaque(true);
		lblDailySalaryExpenses.setHorizontalAlignment(SwingConstants.CENTER);
		lblDailySalaryExpenses.setForeground(Color.BLACK);
		lblDailySalaryExpenses.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblDailySalaryExpenses.setBackground(Color.WHITE);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comboBox.removeAllItems();
					comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
					Showstafflist();
				} catch (SQLException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}

			}
		});
		button_2.setIcon(new ImageIcon(purchase_wages.class.getResource("/hms/images/refresh.png")));
		button_2.setBounds(462, 39, 29, 30);
		panel.add(button_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(590, 91, 499, 630);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(94, 45, 373, 33);
		panel_1.add(textField_2);

		JLabel lblPurpose = new JLabel("Purpose");
		lblPurpose.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPurpose.setBounds(10, 54, 74, 14);
		panel_1.add(lblPurpose);

		textField_3 = new JTextField();
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					double salary = Double.parseDouble(textField_3.getText().trim());

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, "Please enter valid amount");
				}
			}
		});
		textField_3.setColumns(10);
		textField_3.setBounds(94, 106, 373, 33);
		panel_1.add(textField_3);

		JLabel label = new JLabel("Amount:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 114, 74, 14);
		panel_1.add(label);

		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String purpose = textField_2.getText();
					double amount = Double.parseDouble(textField_3.getText().trim());

					dbcon.insertdailyexpenses(purpose, amount, currentuser);
					clearExpenses();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(150, 176, 109, 33);
		panel_1.add(button);

		JButton button_1 = new JButton("Clear");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearExpenses();
			}
		});
		button_1.setBounds(307, 176, 109, 33);
		panel_1.add(button_1);

		JLabel lblOtherExpenses = new JLabel("Other Expenses");
		lblOtherExpenses.setBounds(0, 0, 499, 19);
		panel_1.add(lblOtherExpenses);
		lblOtherExpenses.setOpaque(true);
		lblOtherExpenses.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtherExpenses.setForeground(Color.BLACK);
		lblOtherExpenses.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblOtherExpenses.setBackground(Color.WHITE);

	}

	public void Showstafflist() throws SQLException {
		try {

			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st.executeQuery("Select staffname from staffdetails  order by staffname asc");

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

	public String staffname_selection(String staffname) throws SQLException {
		String a = "";
		try {
			dbcon.connect();

			String sql = "Select staff_id from staffdetails where staffname='" + staffname + "'";
			// JOptionPane.showMessageDialog(null, "asdasd");

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {

				a = dbcon.rs.getString("staff_id");

			}
		}

		catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

		return a;

	}

	public void clearsalary() {

		Calendar mCalendar = Calendar.getInstance();
		String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

		textField.setText(month);

		textField_1.setText(null);
		comboBox.setSelectedIndex(0);
	}

	public void setuser(String current_user) {
		// TODO Auto-generated method stub
		String cuser = current_user;

	}

	public void clearExpenses() {
		textField_2.setText(null);
		textField_3.setText(null);

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
