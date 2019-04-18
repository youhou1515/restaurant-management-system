package hms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class purchase_page extends JDialog {
	DefaultTableModel model = new DefaultTableModel();
	int day, month, year;
	java.sql.Connection conn = null;;
	java.sql.ResultSet rs;
	// java.sql.PreparedStatement pst;
	JButton btnSavealt = new JButton("SAVE [Alt + S]");
	boolean verify;
	JLabel label, label_7;
	JButton btnNewButton_1;

	JButton btnClearaltc = new JButton("CLEAR");
	JComboBox comboBox = new JComboBox();
	
	JDateChooser dateChooser, dateChooser_1;
	private JTable table;
	private JTextField textField2;
	private JTextField textField4;
	private JTextField textField3;
	private JTextField textField5;
	private JTextField unitbox;
	private JTextField textField10;
	private JTextField textField11;
	private JTextField textField12;
	private JTextField textField19;
	private JTextField textField6;
	private JTextField textField13;
	private JTextField textField20;
	private JTextField textField7;
	private JTextField textField8;
	Databaseconnection dbcon = new Databaseconnection();
	public static final String DateFormat = "yyyy/MM/dd";

	public purchase_page() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(purchase_page.class.getResource("/hms/images/food-128.png")));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, dim.width, dim.height * 19 / 20);
//		setUndecorated(true);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(SystemColor.inactiveCaptionBorder);
			panel.setBounds(0, 0, 1350, 729);
			getContentPane().add(panel, BorderLayout.EAST);
			panel.setLayout(null);

			JPanel panel_2 = new JPanel();
			panel_2.setBounds(23, 484, 1317, 196);
			panel.add(panel_2);
			panel_2.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBackground(SystemColor.inactiveCaptionBorder);
			scrollPane.setBounds(0, 0, 1319, 205);
			panel_2.add(scrollPane);

			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						int nselectedrow=table.getSelectedRow();
//						int nselectedcol=table.getSelectedColumn();
						model=(DefaultTableModel) table.getModel();
						String invoicebreak=model.getValueAt(nselectedrow, 0).toString();
						label_7.setText(invoicebreak.substring(8));
						
//						JOptionPane.showMessageDialog(null,a);
//						partyname_selection();
						btnNewButton_1.setVisible(true);
						btnSavealt.setVisible(false);
						
						
						

						comboBox.setSelectedItem(model.getValueAt(nselectedrow, 1).toString());						
						textField2.setText(model.getValueAt(nselectedrow, 2).toString());			
						textField3.setText(model.getValueAt(nselectedrow, 3).toString());	
						textField12.setText(model.getValueAt(nselectedrow, 4).toString());
//						textField6
//						JOptionPane.showMessageDialog(null,model.getValueAt(nselectedrow, 5).toString());
						textField6.setText(model.getValueAt(nselectedrow, 5).toString());	
						textField20.setText(model.getValueAt(nselectedrow, 6).toString());	
						textField7.setText(model.getValueAt(nselectedrow, 7).toString());	
						unitbox.setText(model.getValueAt(nselectedrow, 8).toString());	
						
						textField10.setText(model.getValueAt(nselectedrow, 9).toString());	
						textField11.setText(model.getValueAt(nselectedrow, 10).toString());	
						textField19.setText(model.getValueAt(nselectedrow, 11).toString());	
//						JOptionPane.showMessageDialog(null,model.getValueAt(nselectedrow, 12).toString());
						textField5.setText(model.getValueAt(nselectedrow, 12).toString());	
						String date1=model.getValueAt(nselectedrow, 13).toString();
						String date2=model.getValueAt(nselectedrow, 14).toString();
						
						dateChooser.setDateFormatString(date1);
						dateChooser_1.setDateFormatString(date2);
						
						
						textField8.setText(model.getValueAt(nselectedrow, 15).toString());	
						textField4.setText(model.getValueAt(nselectedrow, 16).toString());	
						textField13.setText(model.getValueAt(nselectedrow, 17).toString());	
						
						 
						
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//JOptionPane.showMessageDialog(null, e1);
					}
				}
			});
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setFillsViewportHeight(true);
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Invoice No.", "Party Name", "Product Name", "GSTIN", "Batch No.", "M.R.P.", "Additional Info", "Bill Date", "Unit/Box", "Quantity", "HSN", "Discount", "Purchase Rate", "Due Date", "Receive Date", "Drug License", "Expiry", "Max Discount"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table.getColumnModel().getColumn(12).setPreferredWidth(89);
			try {
				 ShowTable();
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "AASHIQUI-2");
			}
			scrollPane.setViewportView(table);

			JLabel label_1 = new JLabel("Invoice No.");
			label_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_1.setBounds(161, 70, 101, 28);
			panel.add(label_1);

			JLabel label_2 = new JLabel("Bill Date");
			label_2.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_2.setBounds(595, 70, 57, 28);
			panel.add(label_2);

			JLabel label_3 = new JLabel("Recieve Date");
			label_3.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_3.setBounds(975, 128, 173, 25);
			panel.add(label_3);

			JLabel label_4 = new JLabel("Due Date");
			label_4.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_4.setBounds(974, 77, 74, 20);
			panel.add(label_4);

			JLabel label_5 = new JLabel("Party Name");
			label_5.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_5.setBounds(161, 130, 101, 20);
			panel.add(label_5);

			JLabel label_6 = new JLabel("GSTIN");
			label_6.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_6.setBounds(161, 257, 74, 14);
			panel.add(label_6);

			JLabel lblFoodLicense = new JLabel("Food License");
			lblFoodLicense.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblFoodLicense.setBounds(975, 190, 174, 17);
			panel.add(lblFoodLicense);

			JLabel label_9 = new JLabel("Product Name");
			label_9.setHorizontalAlignment(SwingConstants.LEFT);
			label_9.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_9.setBounds(161, 184, 111, 28);
			panel.add(label_9);

			JLabel label_10 = new JLabel("Quantity");
			label_10.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_10.setBounds(595, 197, 57, 14);
			panel.add(label_10);

			JLabel lblDiscount = new JLabel("Discount");
			lblDiscount.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblDiscount.setBounds(595, 317, 110, 14);
			panel.add(lblDiscount);

			JLabel label_14 = new JLabel("M.R.P.");
			label_14.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_14.setBounds(161, 317, 57, 14);
			panel.add(label_14);

			JLabel label_15 = new JLabel("H.S.N.");
			label_15.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_15.setBounds(595, 257, 46, 14);
			panel.add(label_15);

			JLabel label_16 = new JLabel("Batch No.");
			label_16.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_16.setBounds(975, 257, 86, 14);
			panel.add(label_16);

			JLabel label_18 = new JLabel("Expiry");
			label_18.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_18.setBounds(975, 317, 46, 14);
			panel.add(label_18);

			JLabel label_20 = new JLabel("Unit/box");
			label_20.setHorizontalAlignment(SwingConstants.LEFT);
			label_20.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_20.setBounds(595, 137, 67, 14);
			panel.add(label_20);

			JLabel label_22 = new JLabel("Max Discount");
			label_22.setHorizontalAlignment(SwingConstants.LEFT);
			label_22.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_22.setBounds(975, 377, 101, 14);
			panel.add(label_22);

			JLabel label_23 = new JLabel("Purchase Rate");
			label_23.setHorizontalAlignment(SwingConstants.LEFT);
			label_23.setFont(new Font("Calibri", Font.PLAIN, 16));
			label_23.setBounds(595, 377, 110, 14);
			panel.add(label_23);

			textField2 = new JTextField();
			textField2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {

					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						unitbox.requestFocusInWindow();
					}

				}
			});
			textField2.setColumns(10);
			textField2.setBackground(Color.WHITE);
			textField2.setBounds(293, 183, 189, 42);
			panel.add(textField2);

			textField4 = new JTextField();
			textField4.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// textField11.requestFocusInWindow();
					}
				}
			});
			textField4.setColumns(10);
			textField4.setBackground(Color.WHITE);
			textField4.setBounds(1131, 303, 189, 42);
			panel.add(textField4);

			textField3 = new JTextField();
			textField3.setEditable(false);
			textField3.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// textField10.requestFocusInWindow();
					}
				}
			});
			textField3.setColumns(10);
			textField3.setBackground(Color.WHITE);
			textField3.setBounds(293, 243, 189, 42);
			panel.add(textField3);

			textField5 = new JTextField();
			textField5.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// textField12.requestFocusInWindow();
					}
				}
			});
			textField5.setColumns(10);
			textField5.setBackground(Color.WHITE);
			textField5.setBounds(703, 363, 189, 42);
			panel.add(textField5);

			textField7 = new JTextField();
			textField7.setEditable(false);
			textField7.setText(now());
			textField7.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// textField14.requestFocusInWindow();
					}
				}
			});
			textField7.setColumns(10);
			textField7.setBackground(Color.WHITE);
			textField7.setBounds(703, 63, 189, 42);
			panel.add(textField7);

			textField8 = new JTextField();
			textField8.setEditable(false);
			textField8.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// textField15.requestFocusInWindow();
					}
				}
			});
			textField8.setColumns(10);
			textField8.setBackground(Color.WHITE);
			textField8.setBounds(1130, 183, 190, 42);
			panel.add(textField8);

			unitbox = new JTextField();
			unitbox.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// textField16.requestFocusInWindow();
					}
				}
			});
			unitbox.setColumns(10);
			unitbox.setBackground(Color.WHITE);
			unitbox.setBounds(703, 123, 190, 42);
			panel.add(unitbox);

			textField10 = new JTextField();
			textField10.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					}
				}
			});
			textField10.setColumns(10);
			textField10.setBackground(Color.WHITE);
			textField10.setBounds(703, 183, 190, 42);
			panel.add(textField10);

			textField11 = new JTextField();
			textField11.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						/// textField18.requestFocusInWindow();
					}
				}
			});
			textField11.setColumns(10);
			textField11.setBackground(Color.WHITE);
			textField11.setBounds(702, 243, 190, 42);
			panel.add(textField11);

			textField12 = new JTextField();
			textField12.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// textField19.requestFocusInWindow();
					}
				}
			});
			textField12.setColumns(10);
			textField12.setBackground(Color.WHITE);
			textField12.setBounds(1130, 243, 190, 42);
			panel.add(textField12);

			textField19 = new JTextField();
			textField19.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// textField6.requestFocusInWindow();
					}
				}
			});
			textField19.setColumns(10);
			textField19.setBackground(Color.WHITE);
			textField19.setBounds(702, 303, 190, 42);
			panel.add(textField19);
			comboBox.setBackground(Color.WHITE);
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {

					try {
						comboBox_selection();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			});
			comboBox.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						textField8.requestFocusInWindow();
					}
				}
			});

			comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
			showparty_list();
			comboBox.setBounds(293, 119, 189, 42);
			panel.add(comboBox);

			textField6 = new JTextField();
			textField6.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						textField13.requestFocusInWindow();
					}
				}
			});
			textField6.setColumns(10);
			textField6.setBackground(Color.WHITE);
			textField6.setBounds(293, 303, 189, 42);
			panel.add(textField6);

			textField13 = new JTextField();
			textField13.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						textField20.requestFocusInWindow();
					}
				}
			});
			textField13.setColumns(10);
			textField13.setBackground(Color.WHITE);
			textField13.setBounds(1130, 363, 190, 42);
			panel.add(textField13);

			textField20 = new JTextField();
			
			textField20.setColumns(10);
			textField20.setBackground(Color.WHITE);
			textField20.setBounds(293, 363, 190, 42);
			panel.add(textField20);

			JLabel lblAdditionInformation = new JLabel("<html>Addition<br> Information</html>");
			lblAdditionInformation.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblAdditionInformation.setBounds(161, 356, 146, 49);
			panel.add(lblAdditionInformation);
			btnSavealt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSavealt.setToolTipText("Click to Save");
			btnSavealt.setMnemonic(KeyEvent.VK_S);
			

			btnSavealt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
						String invoicenum = label_7.getText();
					String data1 = "INVOICE/".concat(invoicenum);
						
						String datapartyold = (String) comboBox.getSelectedItem();
						String dataparty = datapartyold.toUpperCase();
						String data2 = textField2.getText().toUpperCase();
						String data3 = textField3.getText().toUpperCase();
						String data4 = textField12.getText().toUpperCase();
						String data5 = textField6.getText().toUpperCase();
						String data6 = textField20.getText().toUpperCase();

						String data7 = textField7.getText().toUpperCase();
						String data8 = unitbox.getText().toUpperCase();
						String data9 = textField10.getText().toUpperCase();
						String data10 = textField11.getText().toUpperCase();
						String data11 = textField19.getText().toUpperCase();
						String data12 = textField5.getText().toUpperCase();

						String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
						
//						String date1=sdf.format(data13);
//						String date2=sdf.format(data14);
//						JOptionPane.showMessageDialog(null, date1);
//						JOptionPane.showMessageDialog(null, date2);
						
						
						String data15 = textField8.getText().toUpperCase();
						String data17 = textField13.getText().toUpperCase();
						String data16 = textField4.getText();
						
						 if (data2.length() == 0) {
								Toolkit.getDefaultToolkit().beep();
								JOptionPane.showMessageDialog(null, "Please Enter Product Name");
								textField2.requestFocusInWindow();

							}
						 
						 else if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
							 comboBox.requestFocusInWindow();
								JOptionPane.showMessageDialog(null, "Please Select Party Name");

							}
						 else if (data9.length() == 0) {

								Toolkit.getDefaultToolkit().beep();

								JOptionPane.showMessageDialog(null, "Please Enter Quantity ");
								textField10.requestFocusInWindow();

							}
						 else if (data4.length() == 0) {

								Toolkit.getDefaultToolkit().beep();

								JOptionPane.showMessageDialog(null, "Please Enter Batch No. ");
								textField12.requestFocusInWindow();

							}
						 
						 
//						 textField12
						 else
						 {
							 String message = " Are You Really wants to SUBMIT ? ";
								String title = "SUBMIT";

								int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
								if (reply == JOptionPane.YES_OPTION) {
//									 JOptionPane.showMessageDialog(null, data1);
						
						dbcon.insertinventoryrecord(data1, dataparty, data2, data3, data4, data5, data6, data7, data8,
								data9, data10, data11, data12, data13, data14, data15, data16, data17);

//						verify = dbcon.insertinventory(data1, dataparty, data2, data3, data4, data5, data6, data7,
//								data8, data9, data10, data11, data12, data13, data14, data15, data16, data17);

//						JOptionPane.showMessageDialog(null, verify);

//						if (verify == true) {
							String check=invoicenum;
//							JOptionPane.showMessageDialog(null, check);
							dbcon.insertuniqueid_inventory(check);
							Object[] row = { data1, dataparty, data2, data3, data4, data5, data6, data7, data8, data9,
									data10, data11, data12, data13, data14, data15, data16,data17 };

							model = (DefaultTableModel) table.getModel();

							model.addRow(row);
							clear();
//						}

							

							

						
							set_max_function();

//						  ShowTable();

								}}	} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}

			});
			btnSavealt.setMnemonic(KeyEvent.VK_S);
			btnSavealt.setBounds(484, 407, 228, 34);
			panel.add(btnSavealt);
			
			
			btnSavealt.setMnemonic(KeyEvent.VK_S);
			btnSavealt.setBounds(513, 445, 228, 34);
			panel.add(btnSavealt);
			btnClearaltc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			

			btnClearaltc.setMnemonic(KeyEvent.VK_C);
			btnClearaltc.setBounds(793, 445, 228, 34);
			btnClearaltc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						clear();
						

						// ShowTable();

					} catch (Exception e1) {

					}
				}

			});
			panel.add(btnClearaltc);

			dateChooser = new JDateChooser();
			dateChooser.getCalendarButton().setBackground(SystemColor.inactiveCaptionBorder);
			dateChooser.setBackground(SystemColor.inactiveCaptionBorder);

			dateChooser.setDate(new Date());
			dateChooser.setMinSelectableDate(new Date());
			dateChooser.setDateFormatString("yyyy/MM/dd");
			dateChooser.setBounds(1131, 63, 189, 42);
			panel.add(dateChooser);

			dateChooser_1 = new JDateChooser();
			dateChooser_1.getCalendarButton().setBackground(SystemColor.inactiveCaptionBorder);
			dateChooser_1.setBackground(SystemColor.inactiveCaptionBorder);
			dateChooser_1.setDate(new Date());
			dateChooser_1.setMinSelectableDate(new Date());
			dateChooser_1.setDateFormatString("yyyy/MM/dd");
			dateChooser_1.setBounds(1131, 123, 189, 42);
			panel.add(dateChooser_1);

			label = new JLabel("INVOICE/");
			label.setForeground(Color.RED);
			label.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
			label.setBounds(293, 73, 90, 22);
			panel.add(label);

			label_7 = new JLabel("");
			set_max_function();
			label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
			label_7.setBounds(368, 73, 54, 22);
			panel.add(label_7);
			
			btnNewButton_1 = new JButton("UPDATE [Alt+U]");
			btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnNewButton_1.setMnemonic(KeyEvent.VK_U);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String message = " Are You sure? ";
					String title = "UPDATE PARTY";
					try {
						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
					
							String invoicenum = label_7.getText();
							String data1 = "INVOICE/".concat(invoicenum);
								
								
							String productname = textField2.getText().toUpperCase();
								
								String batch = textField12.getText().toUpperCase();
								String mrp = textField6.getText().toUpperCase();
								String additional = textField20.getText().toUpperCase();

								String billdate = textField7.getText().toUpperCase();
								String unit = unitbox.getText().toUpperCase();
								String quantity = textField10.getText().toUpperCase();
								String hsn = textField11.getText().toUpperCase();
								String discount = textField19.getText().toUpperCase();
								String purchaserate = textField5.getText().toUpperCase();

								String date1 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
								String date2 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
								
								String maxdiscount = textField13.getText().toUpperCase();
								String expiry = textField4.getText();
//								JOptionPane.showMessageDialog(null, data1);
								dbcon.updateinventory(data1, productname, batch, mrp, additional, billdate, unit, quantity, hsn, discount, purchaserate, date1,date2,maxdiscount,expiry);
								
								clear();
								model=(DefaultTableModel) table.getModel();
								model.setRowCount(0);
								ShowTable();
						
						}
					} catch (SQLException e1) {
						
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			});
			btnNewButton_1.setVisible(false);
			btnNewButton_1.setBounds(513, 445, 228, 34);
			panel.add(btnNewButton_1);
			
			JLabel label_8 = new JLabel("Purchase Record");
			label_8.setOpaque(true);
			label_8.setHorizontalAlignment(SwingConstants.CENTER);
			label_8.setForeground(Color.BLACK);
			label_8.setFont(new Font("Calibri", Font.PLAIN, 18));
			label_8.setBackground(Color.WHITE);
			label_8.setBounds(0, 0, 1344, 28);
			panel.add(label_8);
		}
	}

//	public void TableSelection() throws SQLException {
//		try {
//
//			dbcon.connect();
//			int row = table.getSelectedRow();
//			String TableClick = (table.getModel().getValueAt(row, 0).toString());
//
//			String sql = "Select partyname,productname,gstin,batchno,mrp,maxdiscount,billdate,foodlicense,unit,quantity,hsn,discount,purchaserate,duedate,receivedate,gst,availablestock,minimumstock,expiry,additionalinfo where invoice='"
//					+ TableClick + "'";
//			dbcon.pst = dbcon.conn.prepareStatement(sql);
//			dbcon.rs = dbcon.pst.executeQuery();
//			if (dbcon.rs.next()) {
//				String add1 = dbcon.rs.getString("partyname");
//				comboBox.setSelectedItem(add1);
//
//				String add2 = dbcon.rs.getString("productname");
//				textField2.setText(add2);
//
//				String add3 = dbcon.rs.getString("gstin");
//				textField3.setText(add3);
//
//				String add4 = dbcon.rs.getString("batchno");
//				textField4.setText(add4);
//
//				String add5 = dbcon.rs.getString("mrp");
//				textField5.setText(add5);
//
//				String add6 = dbcon.rs.getString("billdate");
//				textField6.setText(add6);
//
//				String add7 = dbcon.rs.getString("foodlicense");
//				textField7.setText(add7);
//
//				String add8 = dbcon.rs.getString("unit");
//				textField8.setText(add8);
//
//				String add9 = dbcon.rs.getString("quantity");
//				textField9.setText(add9);
//
//				String add10 = dbcon.rs.getString("hsn");
//				textField10.setText(add10);
//
//				String add11 = dbcon.rs.getString("discount");
//				textField10.setText(add11);
//
//				String add12 = dbcon.rs.getString("purchaserate");
//				textField10.setText(add12);
//
//				String add13 = dbcon.rs.getString("duedate");
//				textField10.setText(add13);
//
//				String add14 = dbcon.rs.getString("receivedate");
//				textField10.setText(add14);
//
//				String add15 = dbcon.rs.getString("gst");
//				textField10.setText(add15);
//
//				String add16 = dbcon.rs.getString("availablestock");
//				textField10.setText(add16);
//
//				String add17 = dbcon.rs.getString("minimumstock");
//				textField10.setText(add17);
//
//				String add18 = dbcon.rs.getString("expiry");
//				textField10.setText(add18);
//
//				String add19 = dbcon.rs.getString("additionalinfo");
//				textField10.setText(add19);
//
//			}
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e);
//		} finally {
//			dbcon.conn.close();
//		}
//
//	}

	public void Delete() throws SQLException {
		try {
			dbcon.connect();
			String value1 = label_7.getText();
			String sql = "delete from inventorypurchaserecord where code='" + value1 + "' ";
			dbcon.pst = conn.prepareStatement(sql);
			dbcon.pst.execute();
			JOptionPane.showMessageDialog(null, "Succesfully deleted");
			ShowTable();
			clear();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}finally {
			dbcon.conn.close();
		}
		
	}

	public void clock() {
		Calendar cal = new GregorianCalendar();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
	}

	public void ShowTable() throws SQLException {
		try {
			dbcon.connect();
			String sql = "Select * from inventorypurchaserecord";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();

			model.setRowCount(0);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}

	public void enableComponents(boolean enable) {
		Component[] components = getContentPane().getComponents();

		for (int i = 0; i < components.length; ++i) {
			components[i].setEnabled(enable);
		}

	}

	public void showparty_list() throws SQLException {

		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select partyname from party_details");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("partyname");
				comboBox.addItem(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.conn.close();
		}

	}

	public void partyname_selection() throws SQLException {
		try {
			dbcon.connect();
			int row = table.getSelectedRow();
			String TableClick = (table.getModel().getValueAt(row, 0).toString());

			JOptionPane.showMessageDialog(null, TableClick);
			String sql = "Select * from inventorypurchaserecord where invoice='" + TableClick + "'";

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {
			

			String a = dbcon.rs.getString("invoice");
			String cut=a.substring(3);
			label_7.setText(cut);

			String b = dbcon.rs.getString("partyname");
			comboBox.setSelectedItem(b);

			String c = dbcon.rs.getString("productname");
			textField2.setText(c);

			String d = dbcon.rs.getString("gstin");
			textField3.setText(d);

			String e = dbcon.rs.getString("batchno");
			textField12.setText(e);

			String f = dbcon.rs.getString("mrp");
			textField6.setText(f);

			String g = dbcon.rs.getString("additionalinfo");
			textField20.setText(g);

			String h = dbcon.rs.getString("billdate");
			textField7.setText(h);

			String j = dbcon.rs.getString("unit");
			unitbox.setText(j);
			JOptionPane.showMessageDialog(null, j);
			
			String k = dbcon.rs.getString("quantity");
			textField10.setText(k);

			String l = dbcon.rs.getString("hsn");
			textField11.setText(l);

			String m = dbcon.rs.getString("discount");
			textField19.setText(m);

			String n = dbcon.rs.getString("purchaserate");
			textField5.setText(n);
			
			String i = dbcon.rs.getString("foodlicense");
			textField8.setText(i);

			

			

			String o = dbcon.rs.getString("duedate");
			java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(o);
			dateChooser.setDate(date);

			String p = dbcon.rs.getString("receivedate");
			java.util.Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(p);
			dateChooser.setDate(date1);

			String q = dbcon.rs.getString("expiry");
			textField4.setText(q);

			String r = dbcon.rs.getString("maxdiscount");
			textField13.setText(r);
			
			}

		} catch (Exception e) {
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

	public void set_max_function() throws SQLException {
		try {
			dbcon.connect();
			// Select max(`Enquiry No`) as max from enquiry
			String query1 = "Select max(idgenerator) as max from UID_inventory";
			dbcon.pst = dbcon.conn.prepareStatement(query1);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				int num = dbcon.rs.getInt("max");
				int inc = num + 1;
				label_7.setText(String.valueOf(inc));
				// JOptionPane.showMessageDialog(null, inc);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, f);
		} finally {
			dbcon.conn.close();
		}
	}

	public void clear() {
		try {
			textField7.setText(now());


			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");
			textField6.setText("");
			// textField7.setText("");
			textField8.setText("");
			unitbox.setText("");
			textField10.setText("");
			textField11.setText("");
			textField12.setText("");
			textField13.setText("");
			// datechooser.setText("");
			// textField15.setText("");

			dateChooser.setDate(new Date());
			dateChooser.setMinSelectableDate(new Date());
			dateChooser_1.setDate(new Date());
			dateChooser_1.setMinSelectableDate(new Date());
			textField19.setText("");
			textField20.setText("");
			comboBox.setSelectedIndex(0);
			
			btnNewButton_1.setVisible(false);
			btnSavealt.setVisible(true);
			set_max_function();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void comboBox_selection() throws SQLException {
		try {
			dbcon.connect();
			String data = (String) comboBox.getSelectedItem();

			String sql = "Select gstin,fssaino from party_details where partyname='" + data + "'";

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {

				String a = dbcon.rs.getString("gstin");
				textField3.setText(a);

				String b = dbcon.rs.getString("fssaino");
				textField8.setText(b);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.conn.close();
		}

	}
}
