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
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class i_ssue extends JFrame {

	private final JPanel contentPanel = new JPanel();
	String unitxyz;
	Databaseconnection dbcon = new Databaseconnection();
	private JTextField textField;
	private JTextField textField_1;
	public static final String DateFormat = "yyyy/MM/dd";
	public static final String TimeFormat = "HH:mm:ss";
	private JLabel label_3;
	private JTextField textField_4;
	private JComboBox textField_5;
	private JComboBox textField_6;
	private JComboBox comboBox;
	public JTextField textField_3;
	int data1;
	boolean verify;
	private JComboBox comboBox_2;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();

	public i_ssue() {
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

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(37, 68, 511, 656);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("Stock Issue");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBackground(SystemColor.menu);
		label.setBounds(0, 0, 511, 17);
		panel.add(label);

		JLabel label_1 = new JLabel("Issue No.");
		label_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_1.setBounds(34, 33, 75, 26);
		panel.add(label_1);

		JLabel label_2 = new JLabel("ISSUE/");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		label_2.setBounds(138, 35, 61, 22);
		panel.add(label_2);

		label_3 = new JLabel("");
		try {
			set_max_function();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(192, 35, 75, 22);
		panel.add(label_3);

		JLabel label_4 = new JLabel("Date");
		label_4.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_4.setBounds(34, 91, 75, 26);
		panel.add(label_4);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setText(datenow());
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(SystemColor.inactiveCaptionBorder);
		textField.setBounds(147, 91, 154, 26);
		panel.add(textField);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText(now());
		textField_1.setColumns(10);
		textField_1.setBackground(SystemColor.inactiveCaptionBorder);
		textField_1.setBounds(346, 91, 137, 26);
		panel.add(textField_1);

		JLabel label_5 = new JLabel("Time");
		label_5.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_5.setBounds(311, 91, 75, 26);
		panel.add(label_5);

		JLabel label_6 = new JLabel("Product Name");
		label_6.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_6.setBounds(35, 177, 102, 26);
		panel.add(label_6);

		JLabel label_7 = new JLabel("Available Stock");
		label_7.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_7.setBounds(35, 228, 119, 26);
		panel.add(label_7);

		JLabel label_8 = new JLabel("Issue Quantity");
		label_8.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_8.setBounds(36, 265, 102, 26);
		panel.add(label_8);

		JLabel label_9 = new JLabel("Issue Location");
		label_9.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_9.setBounds(35, 300, 102, 26);
		panel.add(label_9);

		JLabel label_10 = new JLabel("Issue To");
		label_10.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_10.setBounds(34, 335, 102, 26);
		panel.add(label_10);

		JLabel label_11 = new JLabel("Issue By");
		label_11.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_11.setBounds(34, 372, 102, 26);
		panel.add(label_11);
		try {
			showproduct();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(148, 228, 335, 24);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					String str = textField_4.getText();
					if (!(str.length() == 0)) {
						String expression = "[0-9]*";
						String inputStr = textField_4.getText();
						Pattern pattern = Pattern.compile(expression);
						Pattern.compile(expression);
						Matcher matcher = pattern.matcher(inputStr);

						if (matcher.matches()) {

							int abc = Integer.parseInt(str);
							String str1 = textField_3.getText();
							int abc1 = Integer.parseInt(str1);

							if (abc > abc1) {
								JOptionPane.showMessageDialog(null, "issued quantity not available in stock!");
								textField_4.setText("");
							}

						}
					} else {

					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		textField_4.setColumns(10);
		textField_4.setBounds(148, 265, 335, 24);
		panel.add(textField_4);

		textField_5 = new JComboBox();
		textField_5.setModel(new DefaultComboBoxModel(new String[] {"SELECT"}));
		try {
			Showstafflist();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		textField_5.setBounds(148, 335, 335, 24);
		panel.add(textField_5);

		textField_6 = new JComboBox();
		textField_6.setModel(new DefaultComboBoxModel(new String[] {"SELECT", "admin"}));
		try {
			Showstafflist2();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		textField_6.setBounds(148, 370, 335, 24);
		panel.add(textField_6);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		comboBox.setEditable(true);

		AutoCompleteDecorator.decorate(comboBox);

		try {
			Showlocation();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBox.setBounds(147, 300, 335, 26);
		panel.add(comboBox);

		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		comboBox_2.setEditable(true);

		AutoCompleteDecorator.decorate(comboBox_2);
		comboBox_2.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String data = (String) comboBox_2.getSelectedItem();
						int d;

						d = productquantity(data);
						textField_3.setText(String.valueOf(d));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
//						e.printStackTrace();
					}

				}
			}
		});

		comboBox_2.setBounds(148, 179, 335, 26);
		panel.add(comboBox_2);

		JButton btnIssueNow = new JButton("ISSUE [Alt+I]");
		btnIssueNow.setMnemonic('I');
		btnIssueNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "1");

				if (textField_3.getText().length() == 0)

				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Select product and press Enter to see available stock");
					textField_4.requestFocusInWindow();

				} else if (textField_4.getText().length() == 0)

				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Please Enter Issue quantity");
					textField_5.requestFocusInWindow();

				} else if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
					comboBox.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Select Issue Location");

				}
				else if (textField_5.getSelectedItem() == null || textField_5.getSelectedItem().equals("SELECT")) {
					textField_5.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Select Issue To");

				}
				else if (textField_6.getSelectedItem() == null || textField_6.getSelectedItem().equals("SELECT")) {
					textField_6.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Select Issue By");

				}


				else {
//						JOptionPane.showMessageDialog(null, "2");
					try {
						String issueto = textField_5.getSelectedItem().toString();
						String issueby = textField_6.getSelectedItem().toString();

						String Text_availablestock = textField_3.getText();

						String Text_issuestock = textField_4.getText();

						int Text_availablestock_inttype = Integer.parseInt(Text_availablestock);
						int Text_issuestock_inttype = Integer.parseInt(Text_issuestock);

						int totalstock = Text_availablestock_inttype - Text_issuestock_inttype;
						String totalstock_stringtype = String.valueOf(totalstock);

						String issuestocknum = "ISSUE/".concat(label_3.getText().toUpperCase());

//						JOptionPane.showMessageDialog(null, "3");
						verify = dbcon.issuestockentry(issuestocknum, textField.getText().toUpperCase(),
								textField_1.getText().toUpperCase(), comboBox_2.getSelectedItem().toString(),
								textField_3.getText().toUpperCase(), textField_4.getText().toUpperCase(),
								comboBox.getSelectedItem().toString(), textField_5.getSelectedItem().toString(),
								textField_6.getSelectedItem().toString());

						if (verify == true) {
							String data1 = issuestocknum;
							String stockbreak = label_3.getText();
							// rese();

							dbcon.insertuniqueid_stock(stockbreak);
							set_max_function();

							String product_primarykey = comboBox_2.getSelectedItem().toString();

							dbcon.updateproductquantity(totalstock, product_primarykey);

							model = (DefaultTableModel) table.getModel();

							Object[] row = { issuestocknum, comboBox_2.getSelectedItem().toString(),
									textField_4.getText().toUpperCase(), comboBox.getSelectedItem().toString(),
									textField_5.getSelectedItem().toString(),
									textField_6.getSelectedItem().toString() };
							model.addRow(row);

							Clearreset();
						}

					} catch (Exception f) {
						JOptionPane.showMessageDialog(null, f);
					}

				}
			}
		});
		btnIssueNow.setBounds(76, 522, 177, 41);
		panel.add(btnIssueNow);

		JButton btnClean = new JButton("CLEAR [Alt+C]");
		btnClean.setMnemonic('C');
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Clearreset();
			}

		});
		btnClean.setBounds(278, 522, 177, 41);
		panel.add(btnClean);
		
		JLabel lblNoteSelectProduct = new JLabel("Note: Select product and Press Enter to see available stock.");
		lblNoteSelectProduct.setBounds(155, 205, 328, 14);
		panel.add(lblNoteSelectProduct);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(558, 68, 476, 656);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 476, 656);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					DefaultTableModel model1 = (DefaultTableModel) table.getModel();
//					int SelectedRowIndex = table.getSelectedRow();

					int x = table.getSelectedRow();
					String item = model1.getValueAt(x, 0).toString();
					String issuedqtyreverse_add = model1.getValueAt(x, 2).toString();
					String issuedqtyreverse_add_where_productname = model1.getValueAt(x, 1).toString();

					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
						String message = "Do you want to Delete ? ";
						String title = "CONFIRM";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

//						JOptionPane.showMessageDialog(null, item);
							model1.removeRow(x);
							dbcon.issuestockdelete(item);

							dbcon.stockup_addifdeleted(issuedqtyreverse_add_where_productname, issuedqtyreverse_add);
							Clearreset();
						}
					}
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f);
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Issue No.", "Name", "Qty", "Location", "Issue To", "Issue By" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(76);
		table.getColumnModel().getColumn(0).setMinWidth(76);
		table.getColumnModel().getColumn(1).setPreferredWidth(115);
		table.getColumnModel().getColumn(1).setMinWidth(115);
		table.getColumnModel().getColumn(2).setPreferredWidth(51);
		table.getColumnModel().getColumn(2).setMinWidth(51);
		table.getColumnModel().getColumn(3).setPreferredWidth(68);
		table.getColumnModel().getColumn(3).setMinWidth(68);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setMinWidth(85);
		table.getColumnModel().getColumn(5).setPreferredWidth(92);
		table.getColumnModel().getColumn(5).setMinWidth(92);
		scrollPane.setViewportView(table);

		JLabel lblStockIssue = new JLabel("STOCK ISSUE");
		lblStockIssue.setOpaque(true);
		lblStockIssue.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockIssue.setForeground(Color.BLACK);
		lblStockIssue.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblStockIssue.setBackground(Color.WHITE);
		lblStockIssue.setBounds(0, 0, 1034, 31);
		contentPanel.add(lblStockIssue);
		try {
			showproduct();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static String datenow() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat(DateFormat);

		return format.format(cal.getTime());

	}

	public static String now() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat(TimeFormat);

		return format.format(cal.getTime());

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

	public void Showlocation() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select locationname from locationlist");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("locationname");
				comboBox.addItem(data);
				comboBox.setSelectedIndex(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.conn.close();
		}
	}

	public void showproduct() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select Product_Name from Product_Stock");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("Product_Name");
				comboBox_2.addItem(data);

//				JOptionPane.showMessageDialog(null, data);

			}
			;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.conn.close();
		}
	}

	
	
	public int productquantity(String data) throws SQLException {
		int datas=0;
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			String sql = "Select Quantity from Product_Stock where Product_Name='" + data + "'";
			dbcon.rs = dbcon.st.executeQuery(sql);
			
			if (dbcon.rs.next()) {
				datas=dbcon.rs.getInt(1);
		
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
		return datas;
	}
	
	
	
	
	
	
	
	
//	public int productquantity(String data) throws SQLException {
//		
//		try {
//			JOptionPane.showMessageDialog(null, data);
//			dbcon.connect();
//		
//			String sql = "Select Quantity from Product_Stock where Product_Name='" + data + "'";
//
//			
//			JOptionPane.showMessageDialog(null, sql);
//			
//			dbcon.pst = dbcon.conn.prepareStatement(sql);
//
//			
//			datas = dbcon.rs.getInt("Quantity");
//			dbcon.rs = dbcon.pst.executeQuery();
//
//			JOptionPane.showMessageDialog(null,datas);
//			
//		} catch (Exception f) {
//
//			JOptionPane.showMessageDialog(null, f);
//		} finally {
//			dbcon.conn.close();
//		}
//		return datas;
//
//	}

	private void Clearreset() {
		String t = null;

		comboBox_2.setSelectedIndex(0);

		textField_3.setText(t);
		textField_4.setText(t);
		comboBox.setSelectedIndex(0);
		textField_5.setSelectedIndex(0);
		textField_6.setSelectedIndex(0);

	}

	public void Showstafflist() throws SQLException {
		try {

			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st.executeQuery("Select staffname from staffdetails  order by staffname asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				textField_5.addItem(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}

	public void Showstafflist2() throws SQLException {
		try {

			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st.executeQuery("Select staffname from staffdetails  order by staffname asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				textField_6.addItem(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}
}
