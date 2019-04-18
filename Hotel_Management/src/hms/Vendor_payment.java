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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Vendor_payment extends JFrame {

	private final JPanel contentPanel = new JPanel();
	Databaseconnection dbcon = new Databaseconnection();
	private JComboBox comboBox;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	DefaultTableModel model;
	private JTextField textField_3;
	
	private JTextField textField_5;
	public static final String DateFormat = "yyyy/MM/dd";
	private JTextField valueget;
	private JButton btnSavealts;

	public Vendor_payment() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Vendor_payment.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
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
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(326, 110, 151, 34);
		contentPanel.add(textField);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(889, 110, 151, 35);
		contentPanel.add(textField_2);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(593, 108, 151, 35);
		contentPanel.add(textField_1);

		JLabel lblAdddeleteTaxRate = new JLabel("Vendor Payment");
		lblAdddeleteTaxRate.setBounds(0, 11, 1092, 23);
		lblAdddeleteTaxRate.setOpaque(true);
		lblAdddeleteTaxRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdddeleteTaxRate.setForeground(Color.BLACK);
		lblAdddeleteTaxRate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdddeleteTaxRate.setBackground(Color.WHITE);
		contentPanel.add(lblAdddeleteTaxRate);

		JLabel label = new JLabel("Party Name:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(23, 69, 95, 14);
		contentPanel.add(label);

		comboBox = new JComboBox();
		comboBox.setEditable(true);
		AutoCompleteDecorator.decorate(comboBox);
		comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					try {

						String partyname = comboBox.getSelectedItem().toString();
						if (partyname.length() != 0) {

							datequery(partyname);
						} else {

							comboBox.requestFocus();

						}
					} catch (Exception f) {
					}

				}
			}
		});
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {

					String partyname = comboBox.getSelectedItem().toString();
					if (partyname.length() != 0) {

						datequery(partyname);
					} else {

						comboBox.requestFocus();

					}
				} catch (Exception f) {
				}

			}
		});
		try {
			showparty_list();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBox.setEditable(true);
		comboBox.setBounds(124, 58, 673, 41);
		contentPanel.add(comboBox);

		JLabel label_1 = new JLabel("Bill Date:");
		label_1.setOpaque(true);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(243, 110, 73, 34);
		contentPanel.add(label_1);

		JLabel label_2 = new JLabel("Bill Amount:");
		label_2.setOpaque(true);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(486, 109, 95, 35);
		contentPanel.add(label_2);

		JLabel label_3 = new JLabel("Balance Amount:");
		label_3.setOpaque(true);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(754, 110, 113, 35);
		contentPanel.add(label_3);

		JLabel label_4 = new JLabel("Amount Paid");
		label_4.setOpaque(true);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBackground(Color.WHITE);
		label_4.setBounds(41, 175, 107, 33);
		contentPanel.add(label_4);

		btnSavealts = new JButton("SAVE [Alt+S]");
		btnSavealts.setMnemonic('S');
		btnSavealts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model = (DefaultTableModel) table.getModel();
				
					double Amount_in=0;
					try {
					Amount_in = Double.parseDouble(valueget.getText());
					}
					catch(Exception f)
					{
						Amount_in=0;
						
					}
				
//				String abc= textField_2.getText();
					double Balance_Amount = 0;
						
					
					try {
						Balance_Amount = Double.parseDouble(textField_2.getText());
						}
						catch(Exception f)
						{
							Balance_Amount=0;
							
						}
					
					if (Amount_in <= Balance_Amount) {
						String testString = " .4534 ";
						  testString = testString.trim();
						   
						  String regexDecimal = "^-?\\d*\\.\\d+$";
						  String regexInteger = "^-?\\d+$";
						  String regexDouble = regexDecimal + "|" + regexInteger;
						
						
						
						String expression = "[0-9]*";
						String inputStr = valueget.getText();
						Pattern pattern = Pattern.compile(regexDouble);
						Pattern.compile(expression);
						Matcher matcher = pattern.matcher(inputStr);
						if (matcher.matches()) {
							
							model.setRowCount(0);
							String BillNo = textField_3.getText();
							String Date = textField.getText();
							String partyname = (String) comboBox.getSelectedItem();
							String BillAmount = textField_1.getText();
							String todaydate = textField_5.getText();
		
							
							Double amount_amount_in = Math.round((Amount_in) * 100.0) / 100.0;	
//						Balance_Amount
						Double Balance_Amount_double = Math.round((Balance_Amount) * 100.0) / 100.0;	
						
					
						Double amount_amount_remainbalance = Math.round((Balance_Amount_double-amount_amount_in) * 100.0) / 100.0;		
	
						
						updateamount(BillNo, amount_amount_in, todaydate,amount_amount_remainbalance);
							
						balanceamountentry(Math.round(amount_amount_in * 100.0) / 100.0, partyname);
						
						recordentry(BillNo,Date,todaydate,BillAmount,partyname,String.valueOf(Amount_in));
//							
//						int sl_no = 1;
						
						
//						JOptionPane.showMessageDialog(null, "6");

							textField_2.setForeground(Color.RED);
							textField_2.setText(String.valueOf(amount_amount_remainbalance));

							
							if (partyname.length() != 0) {

								datequery(partyname);
							} else {

								comboBox.requestFocus();

							}
						
						
						}
					} else {
						JOptionPane.showMessageDialog(null, "Excess Amount!! please check enter amount again!");
					}
//					String BillNo, String Date, String partyname, String Bill_Amount,String Receive_Amount,String Balance_Amount
//					if(Final_Amount<Amount_in)
//					{
//						JOptionPane.showMessageDialog(null, "Amount ");
//					}

//				JOptionPane.showMessageDialog(null, Final_Amount);
nullset();
				} catch (Exception fuck) {
					JOptionPane.showMessageDialog(null, fuck);
				}
			}
		});
		btnSavealts.setEnabled(false);
		btnSavealts.setBounds(562, 176, 265, 33);
		contentPanel.add(btnSavealts);

		JButton btnClearaltc = new JButton("CLEAR [Alt+C]");
		btnClearaltc.setMnemonic('C');
		btnClearaltc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSavealts.setEnabled(false);
			}
		});
		btnClearaltc.setBounds(860, 175, 169, 33);
		contentPanel.add(btnClearaltc);

		JPanel panel = new JPanel();
		panel.setBounds(41, 253, 1006, 480);
		contentPanel.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1006, 480);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					btnSavealts.setEnabled(true);
					model = (DefaultTableModel) table.getModel();

					// get the selected row index
					int selectedRowIndex = table.getSelectedRow();
					textField_3.setText(model.getValueAt(selectedRowIndex, 0).toString());

					textField.setText(model.getValueAt(selectedRowIndex, 1).toString());

					textField_1.setText(model.getValueAt(selectedRowIndex, 2).toString());

					textField_2.setText(model.getValueAt(selectedRowIndex, 5).toString());
					
//				textField_4.setText(model.getValueAt(selectedRowIndex, 6).toString());
//				String comboname=model.getValueAt(selectedRowIndex, 3).toString();
//				comboBox.setSelectedItem(comboname);
//				btnSavepressAlts.setEnabled(true);
				} catch (Exception f) {
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Sl.No.", "Bill No.", "Bill Date", "Bill Amount", "Amount Paid", "Balance Amount" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(103);
		table.getColumnModel().getColumn(1).setMinWidth(103);
		table.getColumnModel().getColumn(2).setPreferredWidth(103);
		table.getColumnModel().getColumn(2).setMinWidth(103);
		table.getColumnModel().getColumn(3).setPreferredWidth(103);
		table.getColumnModel().getColumn(3).setMinWidth(103);
		table.getColumnModel().getColumn(4).setPreferredWidth(103);
		table.getColumnModel().getColumn(4).setMinWidth(103);
		table.getColumnModel().getColumn(5).setPreferredWidth(103);
		table.getColumnModel().getColumn(5).setMinWidth(103);
		scrollPane.setViewportView(table);

		JLabel lblBillNo = new JLabel("Bill No.");
		lblBillNo.setOpaque(true);
		lblBillNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBillNo.setBackground(Color.WHITE);
		lblBillNo.setBounds(25, 108, 73, 34);
		contentPanel.add(lblBillNo);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(108, 110, 126, 34);
		contentPanel.add(textField_3);

		JLabel label_5 = new JLabel("Today's Date");
		label_5.setFont(new Font("Calibri", Font.PLAIN, 16));
		label_5.setBounds(889, 56, 98, 28);
		contentPanel.add(label_5);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setText(now());
		textField_5.setEnabled(false);
//		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(997, 45, 73, 38);
		contentPanel.add(textField_5);
		
		valueget = new JTextField();
		valueget.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_decimalonly(valueget.getText());
				boolean result1 = Regex_integeronly(valueget.getText());
				if (result != true && result1 != true) {
					JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
					valueget.setText(null);

				}
			}
		});
		valueget.setBounds(168, 175, 275, 34);
		contentPanel.add(valueget);
		valueget.setColumns(10);

	}

	protected void recordentry(String billNo, String date, String todaydate, String billAmount, String partyname,
			String valueOf) throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			String sql = "Insert into paymententryrecord(Billno,Bill_Date,Payment_Date,Bill_Amount,Partyname,Amount_Paid) VALUES('" + billNo
					+ "','" + date + "','" + todaydate + "','" + billAmount + "','" + partyname + "','"
					+ valueOf + "')";
			int x = dbcon.st.executeUpdate(sql);

//			JOptionPane.showMessageDialog(null, "Date Inserted Successfully");
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
		
	}

	public void showparty_list() throws SQLException {

		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select partyname from party_details order by partyname asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("partyname");
				comboBox.addItem(data);
				comboBox.setSelectedItem(null);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.st.close();
			dbcon.rs.close();
			dbcon.conn.close();
		}

	}

	public void datequery(String data13) throws SQLException {
		try {

			dbcon.connect();
			model = (DefaultTableModel) table.getModel();
//			JOptionPane.showMessageDialog(null, data13);
//			JOptionPane.showMessageDialog(null, data14);
//			JOptionPane.showMessageDialog(null, data13);
			String sql = "Select Invoice,Billdate,Totalamount,Last_paidamount,Last_paiddate,Balance_Amount from rmspurchase where Partyname='"
					+ data13 + "' AND Balance_Amount <> '0.0'";
//			String sql = "Select invoice,billdate,purchase_entrydate,partyname,billamount,discountrate from rmspurchase where billdate between '"
//					+ data13 + "' and '" + data14 + "'";
//			JOptionPane.showMessageDialog(null, "1");

			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();

//			JOptionPane.showMessageDialog(null, "3");

			model.setRowCount(0);

//			JOptionPane.showMessageDialog(null, dbcon.rs);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
//			totalsaleshow(model);
		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}

	public void updateamount(String billNo, double amount_in, String todaydate, Double amount_amount_remainbalance)
			throws SQLException {
		try {
			
			dbcon.connect();
//			String sql1 = "UPDATE rmspurchase SET  Total_paidamount=Total_paidamount+?, Current_paidamount=?,   Last_paidamount=?, Last_paiddate=?, Balance_Amount=Balance_Amount-? WHERE Invoice=?";
			String sql1 = "UPDATE rmspurchase SET  Last_paidamount=?, Last_paiddate=?, Balance_Amount=? WHERE Invoice=?";

		dbcon.pst = dbcon.conn.prepareStatement(sql1);
			dbcon.pst.setDouble(1, amount_in);
						
			dbcon.pst.setString(2, todaydate);
			dbcon.pst.setDouble(3, amount_amount_remainbalance);
			dbcon.pst.setString(4, billNo);
			dbcon.pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "updated bill details");

		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err);
		} finally {
			dbcon.conn.close();
		}

	}
	
	public void nullset()
	{
		String t=null;
		textField_3.setText(t);
		textField_1.setText(t);
		textField.setText(t);
		textField_2.setText(t);
		valueget.setText(t);
		btnSavealts.setEnabled(false);
	}

	public static String now() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat(DateFormat);

		return format.format(cal.getTime());

	}
	public void balanceamountentry(double gtotal, String partyname) throws SQLException {
		try {
			dbcon.connect();
//			JOptionPane.showMessageDialog(null, gtotal);
//			JOptionPane.showMessageDialog(null, partyname);
			String sql1 = "UPDATE party_details SET Balance_Amount=Balance_Amount-? WHERE partyname=?";
			dbcon.pst = dbcon.conn.prepareStatement(sql1);

			dbcon.pst.setDouble(1, gtotal);

			dbcon.pst.setString(2, partyname);

			dbcon.pst.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.pst.close();
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
