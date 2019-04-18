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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class staff_salary_calculation extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	JComboBox comboBox_2;
	DefaultTableModel model = new DefaultTableModel();
	Databaseconnection dbcon = new Databaseconnection();
	JLabel label_1;
	private JLabel lblStaff;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_10;
	private JComboBox textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	Calendar c = Calendar.getInstance();
	int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	private JButton btnClear;
	public static final String DateFormat = "yyyy/MM/dd";
	private JTextField textField_15;

	public staff_salary_calculation() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setType(javax.swing.JFrame.Type.UTILITY);
		setUndecorated(true);

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(staff_salary_calculation.class.getResource("/hms/images/food-128.png")));
		getContentPane().setFont(new Font("Calibri", Font.PLAIN, 13));

		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textField_13 = new JTextField();
		textField_13.setBounds(519, 567, 185, 38);
		textField_13.setEditable(false);
		contentPanel.add(textField_13);
		textField_13.setColumns(10);

		JLabel lblStaffentry = new JLabel("Staff Salary Management");
		lblStaffentry.setBounds(0, 11, 1092, 19);
		lblStaffentry.setOpaque(true);
		lblStaffentry.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblStaffentry.setForeground(Color.BLACK);
		lblStaffentry.setBackground(Color.WHITE);
		lblStaffentry.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblStaffentry);

		JLabel lblStaffId = new JLabel("ID");
		lblStaffId.setBounds(48, 130, 74, 24);
		lblStaffId.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffId.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblStaffId);

		JLabel lblStaffName = new JLabel("Name");
		lblStaffName.setBounds(48, 165, 112, 24);
		lblStaffName.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffName.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblStaffName);

		JLabel lblStaffFloor = new JLabel("Age");
		lblStaffFloor.setBounds(48, 200, 112, 24);
		lblStaffFloor.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffFloor.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblStaffFloor);

		JLabel lblStaffSalary = new JLabel("Salary");
		lblStaffSalary.setBounds(446, 181, 102, 24);
		lblStaffSalary.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffSalary.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblStaffSalary);

		JLabel lblStaffDesignation = new JLabel("Designation");
		lblStaffDesignation.setBounds(48, 235, 112, 24);
		lblStaffDesignation.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffDesignation.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblStaffDesignation);

		textField_1 = new JTextField();

		textField_1.setBounds(152, 166, 157, 24);
		textField_1.setEditable(false);
		textField_1.setBackground(Color.WHITE);
		textField_1.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_1.setColumns(10);
		contentPanel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setBounds(152, 200, 157, 24);
		textField_2.setEditable(false);
		textField_2.setBackground(Color.WHITE);
		textField_2.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_2.setColumns(10);
		contentPanel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setBounds(578, 181, 197, 24);
		textField_3.setEditable(false);
		textField_3.setBackground(Color.WHITE);
		textField_3.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_3.setColumns(10);
		contentPanel.add(textField_3);

		JButton btnPressAltsTo = new JButton("Press Alt+S To Save");
		btnPressAltsTo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnClear.requestFocus();

				}
			}
		});
		btnPressAltsTo.setBounds(320, 494, 197, 37);
		btnPressAltsTo.setEnabled(false);
		btnPressAltsTo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPressAltsTo.setBackground(SystemColor.inactiveCaption);
		btnPressAltsTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					String expression = "[0-9]*";
					String inputStr = textField_12.getText();
					Pattern pattern = Pattern.compile(expression);
					Pattern.compile(expression);
					Matcher matcher = pattern.matcher(inputStr);
					int noofdays = 0;

					String nameofmonth = textField_11.getSelectedItem().toString();
					int workdays = Integer.parseInt(textField_12.getText());
//					JOptionPane.showMessageDialog(null, fulldays);
//					JOptionPane.showMessageDialog(null, workdays);

					if (nameofmonth.equals("January")) {
						noofdays = 31;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else if (nameofmonth.equals("February")) {
						noofdays = 28;
//						JOptionPane.showMessageDialog(null, noofdays);
					}

					else if (nameofmonth.equals("March")) {
						noofdays = 31;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else if (nameofmonth.equals("April")) {
						noofdays = 30;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else if (nameofmonth.equals("May")) {
						noofdays = 31;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else if (nameofmonth.equals("June")) {
						noofdays = 30;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else if (nameofmonth.equals("July")) {
						noofdays = 31;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else if (nameofmonth.equals("August")) {
						noofdays = 31;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else if (nameofmonth.equals("September")) {
						noofdays = 30;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else if (nameofmonth.equals("October")) {
						noofdays = 31;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else if (nameofmonth.equals("November")) {
						noofdays = 30;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else if (nameofmonth.equals("December")) {
						noofdays = 31;
//						JOptionPane.showMessageDialog(null, noofdays);

					} else {
						noofdays = 0;
//						JOptionPane.showMessageDialog(null, noofdays);

					}

					if (inputStr.length() != 0 && matcher.matches() && noofdays >= workdays
							&& !(nameofmonth.equals("SELECT"))) {

						String staff_id = label_1.getText();
						String staff_paymentdate = textField_10.getText();
						String remark = textField_14.getText();
						;

						String staff_name = textField_1.getText();
						double staff_salary = Double.parseDouble(textField_3.getText());
						double staffalreadypaidamt = Double.parseDouble(textField_15.getText().trim());
						double staff_salaryfinal = staff_salary - staffalreadypaidamt;
						double staff_workingdays = (double) noofdays;
//						double staff_workingdays = Double.parseDouble(noofdays);
						double no_of_workingdays = Double.parseDouble(textField_12.getText());

						double total = (staff_salaryfinal / staff_workingdays) * no_of_workingdays;
//						double totalround = Math.round((total* 100.0) / 100.0;
						double staff_paidsalary = Math.round(total * 100.0) / 100.0;
						textField_13.setText(String.valueOf(staff_paidsalary));

						dbcon.staff_paymentdetails(staff_id, staff_name, staff_salary, staff_paymentdate, nameofmonth,
								no_of_workingdays, staff_paidsalary, remark);
						reset();
						
					}

					else {

						JOptionPane.showMessageDialog(null, "Enter No. of working days");
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
//					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnPressAltsTo.setMnemonic('S');
		contentPanel.add(btnPressAltsTo);

		JLabel lblShift = new JLabel("Shift");
		lblShift.setBounds(48, 270, 63, 24);
		lblShift.setHorizontalAlignment(SwingConstants.LEFT);
		lblShift.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblShift);

		JLabel lblSelectStaffName = new JLabel("Select Staff to Proceed");
		lblSelectStaffName.setBounds(424, 58, 268, 19);
		lblSelectStaffName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectStaffName.setOpaque(true);
		lblSelectStaffName.setBackground(Color.WHITE);
		lblSelectStaffName.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPanel.add(lblSelectStaffName);

		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(48, 91, 1034, 28);
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					// SAVE.setEnabled(false);
					// btnUpdateDetails.setEnabled(true);
					// btnDeleteDetailsaltd.setEnabled(true);
					if (comboBox_2.getSelectedItem() == null || comboBox_2.getSelectedItem().equals("SELECT")) {
						comboBox_2.requestFocusInWindow();

					} else {
						btnPressAltsTo.setEnabled(true);
						btnClear.setEnabled(true);
//					button.setEnabled(true);
//					button_1.setEnabled(true);
						staffname_selection();
//					btnPressAltsTo.setEnabled(false);
						textField_12.requestFocus();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		try {
			Showstafflist();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		comboBox_2.setSelectedIndex(0);
		comboBox_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		comboBox_2.setBackground(SystemColor.inactiveCaptionBorder);
		contentPanel.add(comboBox_2);

		btnClear = new JButton("Clear [Alt+C]");
		btnClear.setBounds(519, 494, 189, 37);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					button.setEnabled(false);
//					button_1.setEnabled(false);
					btnPressAltsTo.setEnabled(false);
					reset();
//					set_max_function_for_staffid();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnClear.setMnemonic('C');
		btnClear.setBackground(SystemColor.inactiveCaption);
		contentPanel.add(btnClear);

		lblStaff = new JLabel("STAFF/");
		lblStaff.setBounds(152, 130, 63, 22);
		lblStaff.setForeground(Color.RED);
		lblStaff.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		contentPanel.add(lblStaff);

		label_1 = new JLabel("");
		label_1.setBounds(211, 131, 54, 22);

		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(label_1);

		JLabel lblStaffMobile = new JLabel("Mobile");
		lblStaffMobile.setBounds(48, 305, 86, 24);
		contentPanel.add(lblStaffMobile);
		lblStaffMobile.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffMobile.setFont(new Font("Calibri", Font.PLAIN, 15));

		textField_5 = new JTextField();
		textField_5.setBounds(151, 305, 158, 24);
		textField_5.setEditable(false);
		contentPanel.add(textField_5);
		textField_5.setBackground(Color.WHITE);
		textField_5.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_5.setColumns(10);

		JLabel lblStaffAddress = new JLabel("Address");
		lblStaffAddress.setBounds(48, 340, 112, 24);
		contentPanel.add(lblStaffAddress);
		lblStaffAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffAddress.setFont(new Font("Calibri", Font.PLAIN, 15));

		textField_6 = new JTextField();
		textField_6.setBounds(152, 342, 154, 24);
		textField_6.setEditable(false);
		contentPanel.add(textField_6);
		textField_6.setBackground(Color.WHITE);
		textField_6.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_6.setColumns(10);

		JLabel lblStaffEmail = new JLabel("Email");
		lblStaffEmail.setBounds(48, 375, 112, 24);
		contentPanel.add(lblStaffEmail);
		lblStaffEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffEmail.setFont(new Font("Calibri", Font.PLAIN, 15));

		textField_7 = new JTextField();
		textField_7.setBounds(152, 375, 154, 24);
		textField_7.setEditable(false);
		contentPanel.add(textField_7);
		textField_7.setBackground(Color.WHITE);
		textField_7.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_7.setColumns(10);

		JLabel lblStaffIdProof = new JLabel("ID Proof type");
		lblStaffIdProof.setBounds(48, 410, 112, 24);
		contentPanel.add(lblStaffIdProof);
		lblStaffIdProof.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffIdProof.setFont(new Font("Calibri", Font.PLAIN, 15));

		textField_8 = new JTextField();
		textField_8.setBounds(152, 408, 154, 24);
		textField_8.setEditable(false);
		contentPanel.add(textField_8);
		textField_8.setBackground(Color.WHITE);
		textField_8.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_8.setColumns(10);

		JLabel lblStaffProofValue = new JLabel("ID Number");
		lblStaffProofValue.setBounds(48, 445, 112, 24);
		contentPanel.add(lblStaffProofValue);
		lblStaffProofValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffProofValue.setFont(new Font("Calibri", Font.PLAIN, 15));

		textField_9 = new JTextField();
		textField_9.setBounds(152, 441, 154, 24);
		textField_9.setEditable(false);
		contentPanel.add(textField_9);
		textField_9.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				btnPressAltsTo.requestFocusInWindow();
			}
		});
		textField_9.setBackground(Color.WHITE);
		textField_9.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_9.setColumns(10);

		textField = new JTextField();
		textField.setBounds(152, 237, 157, 24);
		textField.setEditable(false);
		contentPanel.add(textField);
		textField.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(152, 272, 157, 24);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		contentPanel.add(textField_4);

		JLabel lblperMonth = new JLabel("/per month");
		lblperMonth.setBounds(785, 181, 74, 24);
		lblperMonth.setHorizontalAlignment(SwingConstants.LEFT);
		lblperMonth.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblperMonth);

		textField_10 = new JTextField();
		textField_10.setBounds(880, 38, 189, 42);
		textField_10.setText(now());
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBackground(Color.WHITE);
		contentPanel.add(textField_10);

		JLabel lblTodaysDate = new JLabel("Today's Date");
		lblTodaysDate.setBounds(770, 49, 100, 24);
		lblTodaysDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblTodaysDate.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblTodaysDate);

		textField_11 = new JComboBox();
		textField_11.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String Staffname = comboBox_2.getSelectedItem().toString();
					String month = textField_11.getSelectedItem().toString();

					double totalsalarypaid = stafftotalpaidinmonth(Staffname, month);
					textField_15.setText(String.valueOf(totalsalarypaid));

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		textField_11.setModel(new DefaultComboBoxModel(new String[] { "SELECT", "January", "February", "March", "April",
				"May", "June", "July", "August", "September", "October", "November", "December" }));
		textField_11.setBounds(578, 243, 197, 24);
		textField_11.setEditable(false);
		contentPanel.add(textField_11);

		JLabel lblNumberOfDays = new JLabel("Month");
		lblNumberOfDays.setBounds(446, 246, 112, 19);
		lblNumberOfDays.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumberOfDays.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblNumberOfDays);

		JLabel label = new JLabel("<html>Enter Number of<br/>Working Days</html>");
		label.setBounds(446, 312, 112, 37);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(label);

		textField_12 = new JTextField();
		textField_12.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				boolean result = Regex_integeronly(textField_12.getText());

				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
					textField_12.setText(null);

				}

			}
		});
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_14.requestFocus();

				}
			}
		});
		textField_12.setBounds(578, 313, 197, 24);

		textField_12.setColumns(10);
		contentPanel.add(textField_12);

		JLabel lblNewLabel = new JLabel("Salary of this month:");
		lblNewLabel.setBounds(332, 567, 307, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.RED);
		contentPanel.add(lblNewLabel);

		textField_14 = new JTextField();
		textField_14.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				boolean result = Regex_alphanumeric(textField_14.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
					textField_14.setText(null);

				}

			}
		});
		textField_14.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnPressAltsTo.requestFocus();

				}
			}
		});
		textField_14.setBounds(578, 391, 197, 24);
		contentPanel.add(textField_14);
		textField_14.setColumns(10);

		JLabel lblRemarks = new JLabel("Remarks,if any");
		lblRemarks.setBounds(446, 391, 112, 24);
		lblRemarks.setHorizontalAlignment(SwingConstants.LEFT);
		lblRemarks.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblRemarks);

		textField_15 = new JTextField();
		textField_15.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				boolean result = Regex_decimalonly(textField_15.getText());
				boolean result1 = Regex_integeronly(textField_15.getText());

				if (result != true && result1 != true) {
					JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
					textField_15.setText(null);

				}

			}
		});
		textField_15.setText("0");
		textField_15.setBounds(578, 272, 197, 30);
		contentPanel.add(textField_15);
		textField_15.setColumns(10);

		JLabel lblPaidAmount = new JLabel("Paid Amount:");
		lblPaidAmount.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaidAmount.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblPaidAmount.setBounds(446, 280, 112, 19);
		contentPanel.add(lblPaidAmount);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				comboBox_2.requestFocus();
			}
		});

	}

	/*
	 * public void ShowTable11() throws SQLException { try { dbcon.connect(); //
	 * where Code='" +TableClick+ "' String sql = "Select * from staffdetails";
	 * dbcon.pst = dbcon.conn.prepareStatement(sql); dbcon.rs =
	 * dbcon.pst.executeQuery();
	 * 
	 * // model.setRowCount(0); //
	 * table.setModel(DbUtils.resultSetToTableModel(dbcon.rs)); } catch
	 * (SQLException e) { JOptionPane.showMessageDialog(null, e); } finally {
	 * dbcon.conn.close(); } }
	 */

	/*
	 * public void Table2() throws Exception { try { dbcon.connect(); String sql =
	 * "Select * from staffdetails"; dbcon.st = dbcon.conn.createStatement();
	 * dbcon.rs = dbcon.st.executeQuery(sql); } catch (SQLException e) {
	 * JOptionPane.showMessageDialog(null, e); } finally {
	 * 
	 * dbcon.conn.close(); } }
	 */
	public void set_max_function_for_staffid() throws SQLException {
		try {
			dbcon.connect();
			// Select max(`Enquiry No`) as max from enquiry
			String query1 = "Select max(idgenerator) as max from Uniqueid4staffid";
			dbcon.pst = dbcon.conn.prepareStatement(query1);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				int num = dbcon.rs.getInt("max");
				int inc = num + 1;
				label_1.setText(String.valueOf(inc));

//				 JOptionPane.showMessageDialog(null, inc);

			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, f);
		} finally {
			dbcon.conn.close();
		}
	}

//	public void showdesignation() throws SQLException {
//
//		try {
//
//			dbcon.connect();
//			dbcon.st = dbcon.conn.createStatement();
//
//			dbcon.rs = dbcon.st.executeQuery("Select designation from Designationlist");
//
//			while (dbcon.rs.next()) {
//				String data = dbcon.rs.getString(1);
//				comboBox_1.addItem(data);
//
//			}
//
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "CONTACT SUPPORT TEAM!");
//		} finally {
//			dbcon.conn.close();
//		}
//
//	}

	public void reset() throws SQLException {

		// textField.setText(t);
		String t = null;

		textField_1.setText(t);
		textField_2.setText(t);
		textField_3.setText(t);
		textField_7.setText(t);
		textField_8.setText(t);
		textField_5.setText(t);
		textField_6.setText(t);
		textField_9.setText(t);
		textField_3.setText(t);
		textField_11.setSelectedIndex(0);
		textField_12.setText(t);
		textField.setText(t);
		textField_4.setText(t);
//		textField_11.setText(String.valueOf(monthMaxDays));
		label_1.setText(t);
		comboBox_2.setSelectedIndex(0);
		textField_13.setText(t);

//		set_max_function_for_staffid();

	}

	public void Showstafflist() throws SQLException {
		try {

			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st.executeQuery("Select staffname from staffdetails  order by staffname asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				comboBox_2.addItem(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "CONTACT SUPPORT TEAM!");
		} finally {
			dbcon.conn.close();
		}
	}

	public void staffname_selection() throws SQLException {
		try {
			dbcon.connect();
			String conversion = (String) comboBox_2.getSelectedItem();

			String sql = "Select * from staffdetails where staffname='" + conversion + "'";
			// JOptionPane.showMessageDialog(null, "asdasd");

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {

				String a = dbcon.rs.getString("staff_id");
				label_1.setText(a.substring(6));

				String b = dbcon.rs.getString("staffname");
				textField_1.setText(b);

				String c = dbcon.rs.getString("staffage");
				textField_2.setText(c);

				String e = dbcon.rs.getString("staffsalary");
				textField_3.setText(e);

				String f = dbcon.rs.getString("staffdesignation");
//				textField
				textField.setText(f);

				String g = dbcon.rs.getString("staffshift");
				textField_4.setText(g);
//				comboBox.setSelectedItem(g);

				String h = dbcon.rs.getString("staffmobile");
				textField_5.setText(h);

				String k = dbcon.rs.getString("staffaddress");
				textField_6.setText(k);

				String l = dbcon.rs.getString("staffemail");
				textField_7.setText(l);

				String m = dbcon.rs.getString("staffidproof");
				textField_8.setText(m);

				String n = dbcon.rs.getString("staffproofvalue");
				textField_9.setText(n);

				// String t=null;
				// textField.setText(t);
				// textField_1.setText(t);
				// textField_2.setText(t);
				// textField_3.setText(t);
				// textField_7.setText(t);
				// textField_8.setText(t);
				// textField_5.setText(t);
				// textField_6.setText(t);
				// textField_9.setText(t);
				// comboBox.setSelectedIndex(0);
				//// comboBox_1.setSelectedIndex(0);
			}
		}

		catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

	}

	public static String now() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat(DateFormat);

		return format.format(cal.getTime());

	}

	public double stafftotalpaidinmonth(String staffname, String month) throws SQLException {
		double total = 0;
		try {
			dbcon.connect();

			String sql = "Select sum(amount) from staffotherexpenses where staffname='" + staffname + "' AND month='"
					+ month + "'";
			// JOptionPane.showMessageDialog(null, "asdasd");

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {

				total = dbcon.rs.getDouble(1);

			}
		}

		catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

		return total;

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
