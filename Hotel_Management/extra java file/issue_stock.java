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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;

public class issue_stock extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	int day, month, year;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	String st;
	JLabel label_3;
	boolean verify;
	JLabel lblNewLabel;
	private JTextField textField_8;
	JComboBox comboBox_1;
	public static final String DateFormat = "yyyy/MM/dd";
	public static final String TimeFormat = "HH:mm:ss";

	DefaultTableModel model = new DefaultTableModel();
	Databaseconnection dbcon = new Databaseconnection();

	DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private JTextField product_txt;

	public issue_stock() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(issue_stock.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, dim.width, dim.height * 19 / 20);
//		setUndecorated(true);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaptionBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(622, 140, 718, 539);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					TableSelection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Party Name", "Product Name", "unit",
				"Available Stock", "Expiry", "Additional Info." }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		try {
			ShowTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.setBorder(BorderFactory.createLineBorder(Color.black));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String txt = textField.getText().toUpperCase();
				Filter(txt);
			}
		});
		textField.setColumns(10);
		textField.setBounds(675, 67, 665, 37);
		contentPanel.add(textField);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(issue_stock.class.getResource("/hms/images/Zoom-icon2.png")));
		label.setBounds(622, 67, 43, 41);
		contentPanel.add(label);
		clock();

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(36, 140, 555, 406);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblIssueNo = new JLabel("Issue No.");
		lblIssueNo.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblIssueNo.setBounds(61, 69, 75, 26);
		panel.add(lblIssueNo);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblDate.setBounds(61, 111, 75, 26);
		panel.add(lblDate);

		JLabel lblProductName = new JLabel("Available Stock");
		lblProductName.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblProductName.setBounds(61, 188, 119, 26);
		panel.add(lblProductName);

		JLabel lblIssueBy = new JLabel("Issue To");
		lblIssueBy.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblIssueBy.setBounds(62, 299, 102, 26);
		panel.add(lblIssueBy);

		JLabel lblIssueLocation = new JLabel("Issue Location");
		lblIssueLocation.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblIssueLocation.setBounds(62, 262, 102, 26);
		panel.add(lblIssueLocation);

		JLabel lblIssueQuantity = new JLabel("Issue Quantity");
		lblIssueQuantity.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblIssueQuantity.setBounds(62, 225, 102, 26);
		panel.add(lblIssueQuantity);

		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblTime.setBounds(338, 111, 75, 26);
		panel.add(lblTime);
		
		JTextField textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText(datenow());
		textField_2.setColumns(10);
		textField_2.setBackground(SystemColor.inactiveCaptionBorder);
		textField_2.setBounds(174, 111, 154, 26);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setText(now());
		// textField_3.setText(dateFormat.format(cal.getTime()));
		// textField_3.setText(reportDat1);
		textField_3.setColumns(10);
		textField_3.setBackground(SystemColor.inactiveCaptionBorder);
		textField_3.setBounds(373, 111, 137, 26);
		panel.add(textField_3);

		JLabel label_2 = new JLabel("Product Name");
		label_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_2.setBounds(61, 148, 102, 26);
		panel.add(label_2);

		textField_4 = new JTextField();

		textField_4.setColumns(10);
		textField_4.setBackground(SystemColor.inactiveCaptionBorder);
		textField_4.setBounds(174, 190, 340, 26);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					String str = textField_5.getText();
					if (!(str.length() == 0)) {
						String expression = "[0-9]*";
						String inputStr = textField_7.getText();
						Pattern pattern = Pattern.compile(expression);
						Pattern.compile(expression);
						Matcher matcher = pattern.matcher(inputStr);

						if (matcher.matches()) {

							int abc = Integer.parseInt(str);
							String str1 = textField_4.getText();
							int abc1 = Integer.parseInt(str1);

							if (abc > abc1) {
								JOptionPane.showMessageDialog(null, "issued quantity not available in stock!");
								textField_5.setText("");
							}

						}
					} else {

					//	JOptionPane.showMessageDialog(null, "Please! Enter quantity");
						//textField_5.setText("");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		textField_5.setColumns(10);
		textField_5.setBackground(SystemColor.inactiveCaptionBorder);
		textField_5.setBounds(174, 230, 336, 26);
		panel.add(textField_5);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBackground(SystemColor.inactiveCaptionBorder);
		textField_7.setBounds(174, 304, 336, 26);
		panel.add(textField_7);

		JLabel lblIssueBy_1 = new JLabel("Issue By");
		lblIssueBy_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblIssueBy_1.setBounds(66, 344, 102, 26);
		panel.add(lblIssueBy_1);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBackground(SystemColor.inactiveCaptionBorder);
		textField_8.setBounds(174, 349, 336, 26);
		panel.add(textField_8);

		comboBox_1 = new JComboBox();

		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		try {
			Showlocation();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setBounds(174, 267, 340, 26);
		panel.add(comboBox_1);

		lblNewLabel = new JLabel("");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setBounds(402, 11, 143, 27);
		panel.add(lblNewLabel);

		JLabel lblStockIssue = new JLabel("Stock Issue");
		lblStockIssue.setOpaque(true);
		lblStockIssue.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockIssue.setForeground(SystemColor.desktop);
		lblStockIssue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStockIssue.setBackground(SystemColor.menu);
		lblStockIssue.setBounds(0, 0, 555, 17);
		panel.add(lblStockIssue);
		
		JLabel lblIssue = new JLabel("ISSUE/");
		lblIssue.setForeground(Color.RED);
		lblIssue.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblIssue.setBounds(175, 69, 67, 22);
		panel.add(lblIssue);
		
		label_3 = new JLabel("");
		try {
			set_max_function();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(229, 69, 54, 22);
		panel.add(label_3);
		
		product_txt = new JTextField();
		product_txt.setColumns(10);
		product_txt.setBackground(SystemColor.inactiveCaptionBorder);
		product_txt.setBounds(174, 151, 340, 26);
		panel.add(product_txt);

		JButton btnIssueNowalti = new JButton("ISSUE NOW [Alt+I]");
		btnIssueNowalti.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIssueNowalti.setToolTipText("Click to Issue ");
		btnIssueNowalti.setMnemonic(KeyEvent.VK_I);
		btnIssueNowalti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String issueto = textField_7.getText();
					String issueby = textField_8.getText();

					String Text_availablestock = textField_4.getText();

					String Text_issuestock = textField_5.getText();

//					if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
//						comboBox.requestFocusInWindow();
//						JOptionPane.showMessageDialog(null, "Select Product Name from Table List");
//
//					}else
					 if (Text_availablestock.length() == 0)

					{
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Select available Stock details from Table");
						textField_4.requestFocusInWindow();

					} else if (Text_issuestock.length() == 0)

					{
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter Issue quantity");
						textField_5.requestFocusInWindow();

					} else if (comboBox_1.getSelectedItem() == null || comboBox_1.getSelectedItem().equals("SELECT")) {
						comboBox_1.requestFocusInWindow();
						JOptionPane.showMessageDialog(null, "Select Issue Location");

					} else if (issueto.length() == 0)

					{
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter name of the person Issue to:");
						textField_7.requestFocusInWindow();

					} else if (issueby.length() == 0)

					{
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter name of the person Issue by:");
						textField_8.requestFocusInWindow();

					}
					/*
					 * if (kot.length() == 0) { Toolkit.getDefaultToolkit().beep();
					 * JOptionPane.showMessageDialog(null, "Please Enter KOT ID");
					 * textField_6.requestFocusInWindow(); } else if (comboBox_2.getSelectedItem()
					 * == null || comboBox_2.getSelectedItem().equals("SELECT")) {
					 * comboBox_2.requestFocusInWindow(); JOptionPane.showMessageDialog(null,
					 * "Select Waiter Name");
					 * 
					 * } else if (name.length() == 0) { Toolkit.getDefaultToolkit().beep();
					 * JOptionPane.showMessageDialog(null, "Please Enter Customer Name");
					 * textField_1.requestFocusInWindow();
					 */
					else {
						int Text_availablestock_inttype = Integer.parseInt(Text_availablestock);
						int Text_issuestock_inttype = Integer.parseInt(Text_issuestock);

						int totalstock = Text_availablestock_inttype - Text_issuestock_inttype;
						String totalstock_stringtype = String.valueOf(totalstock);

						String issuestocknum="ISSUE/".concat(label_3.getText().toUpperCase());
						verify = dbcon.issuestockentry(issuestocknum,
								textField_2.getText().toUpperCase(), textField_3.getText().toUpperCase(),
								product_txt.getText().toUpperCase(), textField_4.getText().toUpperCase(),
								textField_5.getText().toUpperCase(), comboBox_1.getSelectedItem(),
								textField_7.getText().toUpperCase(), textField_8.getText().toUpperCase());

						if (verify == true) {
							String data1 = issuestocknum;
							String stockbreak = label_3.getText();
							// rese();

							dbcon.insertuniqueid_stock(stockbreak);
							set_max_function();

							String invoicelabel = lblNewLabel.getText();
							String invoicesubstring = invoicelabel.substring(12);
							dbcon.issuestockupdate(totalstock_stringtype, invoicesubstring);
							JOptionPane.showMessageDialog(null, "Stock issued- Successfully!");
							// JOptionPane.showMessageDialog(null, invoicesubstring);
							model.setRowCount(0);
							ShowTable();
							// JOptionPane.showMessageDialog(null, "Stock Aavailable" +
						} // totalstock_stringtype);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnIssueNowalti.setBounds(120, 611, 137, 41);
		contentPanel.add(btnIssueNowalti);

		JButton btnClear = new JButton("CLEAR [Alt+C]");
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setToolTipText("Click to Clear");
		btnClear.setMnemonic(KeyEvent.VK_C);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ClearAll();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnClear.setBounds(342, 611, 137, 41);
		contentPanel.add(btnClear);
		
		JLabel lblClickToIssue = new JLabel("Click to Select & Issue Selected Item from list below");
		lblClickToIssue.setOpaque(true);
		lblClickToIssue.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickToIssue.setBackground(Color.WHITE);
		lblClickToIssue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClickToIssue.setBounds(812, 115, 377, 22);
		contentPanel.add(lblClickToIssue);
		
		JLabel lblStockIssue_1 = new JLabel("STOCK ISSUE");
		lblStockIssue_1.setOpaque(true);
		lblStockIssue_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockIssue_1.setForeground(Color.BLACK);
		lblStockIssue_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblStockIssue_1.setBackground(Color.WHITE);
		lblStockIssue_1.setBounds(0, 11, 1366, 31);
		contentPanel.add(lblStockIssue_1);
	}

	public void clock() {
		Calendar cal = new GregorianCalendar();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
	}

	public void Filter(String sql) {
		model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(sql));
	}

	public void ShowTable() throws SQLException {
		try {
			dbcon.connect();
			// where Code='" +TableClick+ "'
			String sql = "Select partyname, productname, unit, quantity, expiry, additionalinfo from inventorypurchase";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();

			// model.setRowCount(0);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}

//	public void Showproduct() throws SQLException {
//		try {
//			dbcon.connect();
//			dbcon.st = dbcon.conn.createStatement();
//			dbcon.rs = dbcon.st.executeQuery("Select productname from inventorypurchase");
//
//			while (dbcon.rs.next()) {
//				String data = dbcon.rs.getString("productname");
//				comboBox.addItem(data);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			dbcon.conn.close();
//		}
//	}

	public void select_tablecolumn() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		// get the selected row index
		int selectedColumIndex = table.getSelectedColumn();

	}

	public static String now() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat(TimeFormat);

		return format.format(cal.getTime());

	}
	
	public static String datenow() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat(DateFormat);

		return format.format(cal.getTime());

	}

	public void Showlocation() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select locationname from locationlist");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("locationname");
				comboBox_1.addItem(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.conn.close();
		}
	}

	public void TableSelection() throws SQLException {
		try {
			dbcon.connect();
			model = (DefaultTableModel) table.getModel();
			int row = table.getSelectedRow();
			String TableClick = table.getValueAt(row, 0).toString();
			// String sql = "Select partyname, productname, unit, quantity, expiry,
			// additionalinfo from inventorypurchase";
			String sql = "Select invoice,productname,quantity from inventorypurchase where partyname='" + TableClick
					+ "'";
			// JOptionPane.showMessageDialog(null, "asdasd");

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {

				String add0 = dbcon.rs.getString("productname");
				product_txt.setText(add0);
				

				String add1 = dbcon.rs.getString("quantity");
				textField_4.setText(add1);

				String add2 = dbcon.rs.getString("invoice");
				lblNewLabel.setText("Invoice No.:" + add2);

			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

	}

	public void set_max_function() throws SQLException {
		try {
			dbcon.connect();
			// Select max(`Enquiry No`) as max from enquiry
			String query1 = "Select max(idgenerator) as max from UID_stock";
			dbcon.pst = dbcon.conn.prepareStatement(query1);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				int num = dbcon.rs.getInt("max");
				int inc = num + 1;
				label_3.setText(String.valueOf(inc));
				// JOptionPane.showMessageDialog(null, inc);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, f);
		} finally {
			dbcon.conn.close();
		}

	}

	public void ClearAll() throws SQLException {
		product_txt.setText("");
		textField_4.setText("");
		textField_5.setText("");
		comboBox_1.setSelectedIndex(0);
		textField_7.setText("");
		textField_8.setText("");
	
		
		set_max_function();

	}
}
