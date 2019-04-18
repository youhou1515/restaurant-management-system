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
import java.util.Date;
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
import javax.swing.SpinnerDateModel;
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
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JLocaleChooser;

import java.awt.Component;

import java.awt.Panel;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.ImageIcon;

public class BirthdayAdvanceBooking extends JFrame {

	private final JPanel contentPanel = new JPanel();
	String unitxyz;
	Databaseconnection dbcon = new Databaseconnection();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_6;
	private JComboBox comboBox;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JTextField textField_7;

	public BirthdayAdvanceBooking() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setUndecorated(true);
		setType(javax.swing.JFrame.Type.UTILITY);

		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBackground(new Color(211, 211, 211));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Type of Booking Occasion*:");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(32, 88, 184, 34);
		contentPanel.add(lblNewLabel_1);

		JLabel lblAdvanceBookingPanel = new JLabel("Advance Booking Panel");
		lblAdvanceBookingPanel.setOpaque(true);
		lblAdvanceBookingPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdvanceBookingPanel.setForeground(Color.BLACK);
		lblAdvanceBookingPanel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdvanceBookingPanel.setBackground(Color.WHITE);
		lblAdvanceBookingPanel.setBounds(0, 11, 1142, 23);
		contentPanel.add(lblAdvanceBookingPanel);

		JLabel lblCustomersName = new JLabel("Customer's Name*:");
		lblCustomersName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCustomersName.setBackground(Color.WHITE);
		lblCustomersName.setBounds(32, 142, 184, 34);
		contentPanel.add(lblCustomersName);

		JLabel lblAddress_1 = new JLabel("Address:");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress_1.setBackground(Color.WHITE);
		lblAddress_1.setBounds(32, 198, 184, 34);
		contentPanel.add(lblAddress_1);

		JLabel lblDateFrom_1 = new JLabel("Date from:");
		lblDateFrom_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateFrom_1.setBackground(Color.WHITE);
		lblDateFrom_1.setBounds(32, 362, 184, 34);
		contentPanel.add(lblDateFrom_1);

		JLabel lblMobileNumber = new JLabel("Mobile Number*:");
		lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMobileNumber.setBackground(Color.WHITE);
		lblMobileNumber.setBounds(32, 308, 184, 34);
		contentPanel.add(lblMobileNumber);

		JLabel lblTiming_1 = new JLabel("Timing:");
		lblTiming_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTiming_1.setBackground(Color.WHITE);
		lblTiming_1.setBounds(32, 407, 184, 34);
		contentPanel.add(lblTiming_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(219, 66, 799, 384);
		contentPanel.add(panel);
		panel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String abc = (String) comboBox.getSelectedItem();

					partyname_selection(abc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		try {
			ShowUnit();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e2);
		}
		comboBox.setBounds(10, 24, 722, 31);
		comboBox.setBackground(new Color(255, 255, 255));
		panel.add(comboBox);

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
		textField.setBounds(10, 77, 771, 31);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_1.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_1.setText(null);

				}
			}
		});
		textField_1.setBounds(10, 131, 771, 31);
		textField_1.setColumns(10);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_2.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_2.setText(null);

				}
			}
		});
		textField_2.setBounds(10, 187, 771, 31);
		textField_2.setColumns(10);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_3.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_3.setText(null);

				}
			}
		});
		textField_3.setBounds(10, 245, 344, 31);
		textField_3.setColumns(10);
		panel.add(textField_3);

		JLabel label = new JLabel("-TO-");
		label.setBounds(410, 297, 35, 20);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(label);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		dateChooser_1.setDate(new Date());
		dateChooser_1.setBounds(510, 287, 271, 41);
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		panel.add(dateChooser_1);

		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setDate(new Date());
		dateChooser.setBounds(10, 287, 344, 41);
		dateChooser.setDateFormatString("yyyy/MM/dd");
		panel.add(dateChooser);

		JLabel label_1 = new JLabel("-TO-");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(410, 338, 35, 20);
		panel.add(label_1);

		Date date = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		spinner = new JSpinner(sm);
		spinner.setForeground(Color.RED);
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JSpinner.DateEditor de_spinner = new JSpinner.DateEditor(spinner, "hh:mm");
		spinner.setEditor(de_spinner);
		spinner.setBounds(10, 341, 344, 32);
		panel.add(spinner);

		Date date1 = new Date();
		SpinnerDateModel sm1 = new SpinnerDateModel(date1, null, null, Calendar.HOUR_OF_DAY);
		spinner_1 = new JSpinner(sm1);
		spinner_1.setForeground(Color.RED);
		spinner_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JSpinner.DateEditor de_spinner_1 = new JSpinner.DateEditor(spinner_1, "hh:mm");
		spinner_1.setEditor(de_spinner_1);
		spinner_1.setBounds(510, 340, 271, 33);
		panel.add(spinner_1);

		JLabel lblAadharNumber_1 = new JLabel("Aadhar Number:");
		lblAadharNumber_1.setBounds(381, 241, 106, 34);
		panel.add(lblAadharNumber_1);
		lblAadharNumber_1.setOpaque(true);
		lblAadharNumber_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAadharNumber_1.setBackground(Color.WHITE);

		textField_7 = new JTextField();
		textField_7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_7.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_7.setText(null);

				}
			}
		});
		textField_7.setBounds(510, 245, 271, 31);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
				try {
					ShowUnit();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BirthdayAdvanceBooking.class.getResource("/hms/images/refresh.png")));
		btnNewButton_1.setBounds(739, 24, 29, 31);
		panel.add(btnNewButton_1);

		JLabel lblTotalCost = new JLabel("Total Cost:");
		lblTotalCost.setForeground(Color.RED);
		lblTotalCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalCost.setBackground(Color.WHITE);
		lblTotalCost.setBounds(32, 474, 184, 34);
		contentPanel.add(lblTotalCost);

		JLabel lblAdvanceDeposit = new JLabel("Advance Deposit:");
		lblAdvanceDeposit.setForeground(Color.RED);
		lblAdvanceDeposit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdvanceDeposit.setBackground(Color.WHITE);
		lblAdvanceDeposit.setBounds(32, 538, 184, 34);
		contentPanel.add(lblAdvanceDeposit);

		JLabel lblRemainingAmount = new JLabel("Remaining Amount:");
		lblRemainingAmount.setForeground(Color.RED);
		lblRemainingAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRemainingAmount.setBackground(Color.WHITE);
		lblRemainingAmount.setBounds(32, 602, 184, 34);
		contentPanel.add(lblRemainingAmount);

		JButton btnNewButton = new JButton("Book Now! [Alt+B]");
		btnNewButton.setMnemonic('B');
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
					comboBox.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Select Booking Occasion");

				}

				else if (textField.getText().length() == 0 || textField.getText() == null) {
					JOptionPane.showMessageDialog(null, "Please Enter Customer Name");
				}

				else if (textField_3.getText().length() == 0 || textField_3.getText() == null) {
					JOptionPane.showMessageDialog(null, "Please Enter Mobile Number");
				}

				else {
					String Ocassion = comboBox.getSelectedItem().toString();
					String Customer = textField.getText().toUpperCase();
					String Address = textField_1.getText().toUpperCase();
					String aadhar = textField_7.getText().toUpperCase();

					String mobile = textField_3.getText().toUpperCase();
					String datefrom = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String dateto = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					String timeto = spinner.getValue().toString();
					String timefrom = spinner_1.getValue().toString();
					double totalcost = Double.parseDouble(textField_4.getText());
					double advanceamt = Double.parseDouble(textField_5.getText());
					double remainingamt = Double.parseDouble(textField_6.getText());

					String remarks = textField_2.getText().toUpperCase();

					String Slipno = lblNewLabel_2.getText().toString();

					try {
						dbcon.insertbookingdetails(Ocassion, Customer, Address, aadhar, mobile, datefrom, dateto,
								timeto, timefrom, totalcost, advanceamt, remainingamt, Slipno, remarks);
						clearall();
						set_max_function_4_slip();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
//						JOptionPane.showMessageDialog(null, e1);
					}

				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setActionCommand("Book Now!");
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(702, 491, 233, 47);
		contentPanel.add(btnNewButton);

		JButton btnClearAll = new JButton("Clear [Alt+C]");
		btnClearAll.setMnemonic('C');
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearall();

			}
		});
		btnClearAll.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClearAll.setForeground(Color.WHITE);
		btnClearAll.setBackground(Color.BLACK);
		btnClearAll.setActionCommand("Book Now!");
		btnClearAll.setBounds(702, 562, 233, 47);
		contentPanel.add(btnClearAll);

		lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setBounds(32, 64, 156, 23);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Slip No./");
		lblNewLabel_3.setBounds(31, 49, 46, 14);
		contentPanel.add(lblNewLabel_3);

		textField_4 = new JTextField("0");
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				boolean result = Regex_decimalonly(textField_4.getText());
				boolean result1 = Regex_integeronly(textField_4.getText());
				if (result != true && result1 != true) {
					JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
					textField_4.setText(null);

				}
				
			}
		});
		textField_4.setBounds(240, 474, 415, 32);
		contentPanel.add(textField_4);
		textField_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		textField_4.setColumns(10);

		textField_5 = new JTextField("0");
		textField_5.setBounds(240, 538, 415, 32);
		contentPanel.add(textField_5);
		textField_5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					double totalcost = Double.parseDouble(textField_4.getText());
					double advanceamt = Double.parseDouble(textField_5.getText());
					textField_6.setText(String.valueOf(totalcost - advanceamt));
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, "Please enter valid amount");
					textField_5.setText(null);
				}
			}
		});

		textField_5.setColumns(10);
		textField_5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));

		textField_6 = new JTextField("0");
		textField_6.setEditable(false);
		textField_6.setBounds(240, 602, 415, 32);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);
		textField_6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));

		JLabel lblRemarks = new JLabel("Remarks:");
		lblRemarks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRemarks.setBackground(Color.WHITE);
		lblRemarks.setBounds(31, 252, 184, 34);
		contentPanel.add(lblRemarks);

		try {
			set_max_function_4_slip();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void ShowUnit() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select category from bookingcategory order by category asc");
			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				comboBox.addItem(data);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}

	public void partyname_selection(String abc) throws SQLException {
		try {
			dbcon.connect();

			String sql = "Select * from bookingcategory where category='" + abc + "'";
			// JOptionPane.showMessageDialog(null, "asdasd");

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {

				double a = dbcon.rs.getDouble("rate");
				textField_4.setText(String.valueOf(a));
				textField_6.setText(String.valueOf(a));

			}
		}

		catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

	}

	public void set_max_function_4_slip() throws SQLException {
		try {
			dbcon.connect();
			// Select max(`Enquiry No`) as max from enquiry
			String query1 = "Select max(sno) as max from bookingdetails";
			dbcon.pst = dbcon.conn.prepareStatement(query1);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				int num = dbcon.rs.getInt("max");
				int inc = num + 1;
				lblNewLabel_2.setText(String.valueOf(inc));
//				 JOptionPane.showMessageDialog(null, inc);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, f);
		} finally {
			dbcon.conn.close();
		}
	}

	public void clearall() {

		Date date = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		spinner = new JSpinner(sm);
		spinner.setForeground(Color.RED);
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JSpinner.DateEditor de_spinner = new JSpinner.DateEditor(spinner, "hh:mm");
		comboBox.setSelectedIndex(0);
		textField.setText(null);
		textField_1.setText(null);
		textField_2.setText(null);
		textField_3.setText(null);
		textField.setText(null);
		spinner.setEditor(de_spinner);
		spinner_1.setEditor(de_spinner);

		textField_4.setText(String.valueOf("0"));
		textField_5.setText(String.valueOf("0"));
		textField_6.setText(String.valueOf("0"));

		dateChooser.setDate(new Date());
		dateChooser_1.setDate(new Date());
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
