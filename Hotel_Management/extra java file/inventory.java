package hms;

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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class inventory extends JFrame {
	DefaultTableModel model = new DefaultTableModel();
	int day, month, year;
	java.sql.Connection conn = null;;
	java.sql.ResultSet rs;
	// java.sql.PreparedStatement pst;
	JButton btnSavealt = new JButton("SAVE [Alt + S]");
	boolean verify;
	JLabel label, label_7;

	JButton btnClearaltc = new JButton("CLEAR [Alt+C]");
	JComboBox comboBox = new JComboBox();

	JDateChooser dateChooser_1;
	private JComboBox textField2;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField10;
	private JTextField textField12;
	private JTextField textField19;
	private JTextField textField20;
	private JTextField textField7;
double total_amount=0;
	Databaseconnection dbcon = new Databaseconnection();
	public static final String DateFormat = "yyyy/MM/dd";
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton;

	public inventory() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setType(javax.swing.JFrame.Type.UTILITY);
		setUndecorated(true);

		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(0, 0, 1092, 652);
			getContentPane().add(panel, BorderLayout.EAST);
			panel.setLayout(null);

			JLabel label_1 = new JLabel("Invoice No.");
			label_1.setBounds(23, 72, 101, 28);
			label_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_1);

			JLabel lblTodaysDate = new JLabel("Today's Date");
			lblTodaysDate.setBounds(909, 39, 98, 28);
			lblTodaysDate.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(lblTodaysDate);

			JLabel lblBillDate = new JLabel("Bill Date");
			lblBillDate.setBounds(376, 72, 173, 25);
			lblBillDate.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(lblBillDate);

			JLabel label_5 = new JLabel("Party Name");
			label_5.setBounds(23, 121, 101, 20);
			label_5.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_5);

			JLabel label_9 = new JLabel("Product Name");
			label_9.setBounds(376, 121, 111, 28);
			label_9.setHorizontalAlignment(SwingConstants.LEFT);
			label_9.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_9);

			JLabel label_10 = new JLabel("Quantity");
			label_10.setBounds(23, 172, 57, 14);
			label_10.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_10);

			JLabel lblDiscount = new JLabel("Discount %");
			lblDiscount.setBounds(23, 230, 110, 14);
			lblDiscount.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(lblDiscount);

			JLabel label_14 = new JLabel("M.R.P.");
			label_14.setBounds(707, 172, 57, 14);
			label_14.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_14);

			JLabel label_16 = new JLabel("Batch No.");
			label_16.setBounds(707, 121, 86, 14);
			label_16.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_16);

			JLabel label_18 = new JLabel("Expiry");
			label_18.setBounds(376, 230, 46, 14);
			label_18.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_18);

			JLabel label_23 = new JLabel("Purchase Rate");
			label_23.setBounds(377, 172, 110, 14);
			label_23.setHorizontalAlignment(SwingConstants.LEFT);
			label_23.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_23);

			textField2 = new JComboBox();
			textField2.setModel(new DefaultComboBoxModel(new String[] {"SELECT"}));
			textField2.setSelectedIndex(0);
			textField2.setBounds(487, 116, 189, 31);
			textField2.setBackground(Color.WHITE);
			try {
				showproduct();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			panel.add(textField2);

			textField4 = new JTextField();
			textField4.setBounds(487, 222, 189, 31);
			textField4.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						 textField.requestFocus();
					}
				}
			});
			textField4.setColumns(10);
			textField4.setBackground(Color.WHITE);
			panel.add(textField4);

			textField5 = new JTextField();
			textField5.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					try {
					String expression = "[0-9]*";
					String inputStr = textField10.getText();
					Pattern pattern = Pattern.compile(expression);
					Pattern.compile(expression);
					Matcher matcher = pattern.matcher(inputStr);
	
					if (matcher.matches()) {
						textField19.requestFocusInWindow();
	
					} else {	
	
						JOptionPane.showMessageDialog(null, "Please! Enter Valid Rate");
					}
				}catch(Exception f)
				{}
					
					
				}
			});
			textField5.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						
						try {
						Double qtydouble = Double.parseDouble(textField10.getText());
						Double purchaserate = Double.parseDouble(textField5.getText());
						textField20.setText(String.valueOf(qtydouble * purchaserate));
						
						textField19.requestFocusInWindow();
					}catch(Exception f)
					{}
						
						
					}
				}
			});
			textField5.setBounds(487, 164, 189, 31);
//			textField5.addKeyListener(new KeyAdapter() {
//				@Override
//				public void keyReleased(KeyEvent e) {
//					try {
//						String qty = textField10.getText();
////					 || 
////					e.getKeyCode() == KeyEvent.VK_TAB)
//						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//							
//							Double qtydouble = Double.parseDouble(textField10.getText());
//							Double purchaserate = Double.parseDouble(textField5.getText());
//
//							textField20.setText(String.valueOf(qtydouble * purchaserate));
//							
//							
//						}
//						
//					} catch (Exception f) {
////						JOptionPane.showMessageDialog(null, "Invalid entry");
//					}
//				}
//			});
			textField5.setColumns(10);
			textField5.setBackground(Color.WHITE);
			panel.add(textField5);

			textField7 = new JTextField();
			textField7.setEnabled(false);
			textField7.setBounds(1005, 36, 67, 34);
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
			panel.add(textField7);

			textField10 = new JTextField();
			textField10.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
//					try {
//					String expression = "[0-9]*";
//					String inputStr = textField10.getText();
//					Pattern pattern = Pattern.compile(expression);
//					Pattern.compile(expression);
//					Matcher matcher = pattern.matcher(inputStr);
//	
//					if (matcher.matches()) {
//						textField5.requestFocusInWindow();
//	
//					} else {	
//	
//						JOptionPane.showMessageDialog(null, "Please! Enter Valid Quantity");
//					}
//					}catch(Exception f)
//					{}

				}
			});
			textField10.setBounds(155, 164, 190, 31);
			textField10.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//						textField5.requestFocus();
					}
				}
			});
			textField10.setColumns(10);
			textField10.setBackground(Color.WHITE);
			panel.add(textField10);

			textField12 = new JTextField();
			textField12.setBounds(837, 116, 190, 31);
			textField12.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						textField10.requestFocus();
					}
				}
			});
			textField12.setColumns(10);
			textField12.setBackground(Color.WHITE);
			panel.add(textField12);

			textField19 = new JTextField();
			textField19.setBounds(155, 222, 190, 31);
			textField19.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							String expression = "[0-9]*";
							String inputStr = textField10.getText();
							Pattern pattern = Pattern.compile(expression);
							Pattern.compile(expression);
							Matcher matcher = pattern.matcher(inputStr);
			
							if (matcher.matches()) {
								textField4.requestFocus();
			
							} else {	
			
								JOptionPane.showMessageDialog(null, "Please! Enter Valid Rate");
							}
						}catch(Exception f)
						{}
							
						
						
					}
				}
			});
			textField19.setColumns(10);
			textField19.setBackground(Color.WHITE);
			panel.add(textField19);
			comboBox.setBounds(155, 116, 189, 31);
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
						textField2.requestFocus();
					}
				}
			});

			comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
			try {
				showparty_list();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			panel.add(comboBox);

			textField20 = new JTextField();
			textField20.setEditable(false);
//			textField20.addKeyListener(new KeyAdapter() {
//				@Override
//				public void keyReleased(KeyEvent e) {
//					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//						textField19.requestFocus();
//					}
//				}
//			});
			textField20.setBounds(837, 164, 190, 31);

			textField20.setColumns(10);
			textField20.setBackground(Color.WHITE);
			panel.add(textField20);

			JLabel lblAdditionInformation = new JLabel("Total Amount");
			lblAdditionInformation.setBounds(762, 608, 146, 37);
			lblAdditionInformation.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(lblAdditionInformation);
//			btnSavealt.addKeyListener(new KeyAdapter() {
//				@Override
//				public void keyReleased(KeyEvent e) {
//					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//						btnClearaltc.requestFocus();
//					}
//				}
//			});
			btnSavealt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String message = " Are You Really wants to SUBMIT ? ";
						String title = "SUBMIT";
						DefaultTableModel mod=new DefaultTableModel();
						mod=(DefaultTableModel) table.getModel();
						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION && mod.getRowCount()!=0) {

							model = (DefaultTableModel) table.getModel();
							String todaydate = textField7.getText().toUpperCase();
							String invoice = model.getValueAt(0, 0).toString().toUpperCase();
							String billdate = model.getValueAt(0, 1).toString().toUpperCase();
							String partyname = model.getValueAt(0, 2).toString().toUpperCase();
							String billamount = model.getValueAt(0, 7).toString().toUpperCase();
							String discount = model.getValueAt(0, 8).toString().toUpperCase();
//						JOptionPane.showMessageDialog(null, "1");

							rmspurchase(invoice, billdate, todaydate, partyname, billamount, discount);
//						JOptionPane.showMessageDialog(null, "2");

							inventoryproductentry(model);
							clear();
							dateChooser_1.setDate(new Date());
							comboBox.setSelectedItem("");
							model.setRowCount(0);

							String check = label_7.getText();

							insertuniqueid_inventory(check);
							set_max_function();
						}
					} catch (Exception f) {
					}

				}

			});
			btnSavealt.setBounds(239, 611, 228, 34);
			btnSavealt.setBackground(Color.GRAY);
			btnSavealt.setForeground(Color.WHITE);
			btnSavealt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSavealt.setToolTipText("Click to Save");
			btnSavealt.setMnemonic('S');
			btnSavealt.setMnemonic(KeyEvent.VK_S);

			btnSavealt.setMnemonic(KeyEvent.VK_S);
			panel.add(btnSavealt);

			btnSavealt.setMnemonic(KeyEvent.VK_S);
			panel.add(btnSavealt);
//			btnClearaltc.addKeyListener(new KeyAdapter() {
//				@Override
//				public void keyReleased(KeyEvent e) {
////					dateChooser_1
//					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//						dateChooser_1.requestFocus();
//					}
//				}
//			});
			btnClearaltc.setBounds(497, 611, 228, 34);
			btnClearaltc.setBackground(Color.GRAY);
			btnClearaltc.setForeground(Color.WHITE);
			btnClearaltc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnClearaltc.setMnemonic('C');

			btnClearaltc.setMnemonic(KeyEvent.VK_C);
			btnClearaltc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
//						clear();

						clear();
						dateChooser_1.setDate(new Date());
						comboBox.setSelectedItem("");
						model.setRowCount(0);

					} catch (Exception e1) {

					}
				}

			});
			panel.add(btnClearaltc);

			dateChooser_1 = new JDateChooser();
			dateChooser_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						comboBox.requestFocus();
					}
				}
			});
			dateChooser_1.setBounds(484, 67, 189, 31);
			dateChooser_1.getCalendarButton().setBackground(SystemColor.inactiveCaptionBorder);
			dateChooser_1.setBackground(SystemColor.inactiveCaptionBorder);
			dateChooser_1.setDate(new Date());
			dateChooser_1.setMinSelectableDate(new Date());
			dateChooser_1.setDateFormatString("yyyy/MM/dd");
			panel.add(dateChooser_1);

			label = new JLabel("INVOICE/");
			label.setBounds(155, 72, 74, 22);
			label.setForeground(Color.RED);
			label.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
			panel.add(label);

			label_7 = new JLabel("");
			label_7.setBounds(239, 77, 54, 14);
			try {
				set_max_function();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel.add(label_7);

			JLabel lblNewPurchase = new JLabel("New Purchase");
			lblNewPurchase.setBounds(0, 0, 1092, 28);
			lblNewPurchase.setOpaque(true);
			lblNewPurchase.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewPurchase.setForeground(Color.BLACK);
			lblNewPurchase.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblNewPurchase.setBackground(Color.WHITE);
			panel.add(lblNewPurchase);

			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
//					btnNewButton
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnNewButton.requestFocus();
					}
				}
			});
			textField.setColumns(10);
			textField.setBackground(Color.WHITE);
			textField.setBounds(892, 611, 190, 31);
			panel.add(textField);

			btnNewButton = new JButton("Add Item to List [Alt+A]");
//			btnNewButton.addKeyListener(new KeyAdapter() {
//				@Override
//				public void keyReleased(KeyEvent e) {
////					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
////						btnSavealt.requestFocus();
////					}
//				}
//			});
			btnNewButton.setMnemonic('A');
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setBackground(Color.GRAY);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
//						JOptionPane.showMessageDialog(null, "please enter product name");
						model=(DefaultTableModel) table.getModel();
						String todaydate = textField7.getText();

						String invoice = "INVOICE/".concat(label_7.getText());
						String billdate = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();

						String partyname = comboBox.getSelectedItem().toString();
						String productname = textField2.getSelectedItem().toString();
						String batchno = textField12.getText();

						String qty = textField10.getText();
						String purchaserate = textField5.getText();
						String mrp = textField20.getText();
						String discount = textField19.getText();
						String expiry = textField4.getText();
						
						//calculation
						Double discount_double = Double.parseDouble(textField19.getText());
						Double mrp_double = Double.parseDouble(textField20.getText());
						
						Double mrp_discount_amount=mrp_double*(discount_double/100);
						
						
//						JOptionPane.showMessageDialog(null, mrp_discount_amount);
						
						Double mrp_after_discount=mrp_double-mrp_discount_amount;
						
//						JOptionPane.showMessageDialog(null, mrp_after_discount);
						
						

						total_amount=total_amount+mrp_after_discount;
						
						String total_amount_string=String.valueOf(total_amount);
						textField.setText(total_amount_string);
						
//						String additionalinfo = textField.getText();

						
						
						
//						if (productname.length() != 0 && partyname.length() != 0) {
							Object[] row = { invoice, billdate, partyname, productname, batchno, qty, purchaserate, mrp_after_discount.toString(),
									mrp_discount_amount.toString(), expiry};

							model.addRow(row);
//						if(productname.length() == 0 && partyname.length() == 0 && batchno.length()==0 && qty.length()==0 && purchaserate.length()==0 && mrp.length()==0 && discount.length()==0 && expiry.length()==0 && additionalinfo.length()==0)
//						{
//							batchno="";
//							qty="";
//							purchaserate="";
//							mrp="";
//							discount="";
//							expiry="";
//							additionalinfo="";
//							Object[] row1 = { invoice, billdate, partyname, productname, batchno, qty, purchaserate, mrp,
//									discount, expiry, additionalinfo };
//							
//						}
//						
//						} 
//					else {
//							JOptionPane.showMessageDialog(null, "please enter product name");
//						}
							clear();
					} catch (Exception f) {
					}
//					model.setRowCount(0);

//					int qty=Integer.parseInt(textField10.getText());

				}
			});
			btnNewButton.setBounds(376, 264, 323, 37);
			panel.add(btnNewButton);

			JPanel panel_1 = new JPanel();
			panel_1.setBounds(33, 312, 1039, 288);
			panel.add(panel_1);
			panel_1.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 1039, 288);
			panel_1.add(scrollPane);

			table = new JTable();
			table.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					try {
						if (e.getKeyCode() == KeyEvent.VK_DELETE) {
							DefaultTableModel models = (DefaultTableModel) table.getModel();
							
							int SelectedRowIndex = table.getSelectedRow();
							String amount_dedudct=(String) models.getValueAt(SelectedRowIndex, 7);
							
							String total_bill_amount=textField.getText();
//							JOptionPane.showMessageDialog(null, total_bill_amount);
							
//							double bill_discount_roundoff = Math.round(bill_discount * 100.0) / 100.0;
							Double total_bill_amount_double=Math.round(Double.parseDouble(total_bill_amount)*100.0)/100.0;
							Double amount_dedudct_double=Math.round(Double.parseDouble(amount_dedudct)*100.0)/100.0;
							
							JOptionPane.showMessageDialog(null, total_bill_amount_double);
							JOptionPane.showMessageDialog(null, amount_dedudct_double);
							Double final_am=Math.round(total_bill_amount_double-amount_dedudct_double)*100.0/100.0;
							
							JOptionPane.showMessageDialog(null, final_am);
							
							textField.setText(final_am.toString());
							
							models.removeRow(SelectedRowIndex);
							
							total_bill_amount_double=0.0;
							amount_dedudct_double=0.0;
						}
					} catch (Exception f) {
					}
				}
			});
			table.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "Invoice Number", "Bill Date", "partyname", "productname", "batchno", "Quantity",
							"Purchase Rate", "Mrp", "Discount", "Expiry" }));
			table.getColumnModel().getColumn(0).setPreferredWidth(96);
			table.getColumnModel().getColumn(0).setMinWidth(96);
			table.getColumnModel().getColumn(1).setPreferredWidth(86);
			table.getColumnModel().getColumn(1).setMinWidth(86);
			table.getColumnModel().getColumn(2).setPreferredWidth(125);
			table.getColumnModel().getColumn(2).setMinWidth(125);
			table.getColumnModel().getColumn(3).setPreferredWidth(125);
			table.getColumnModel().getColumn(3).setMinWidth(125);
			table.getColumnModel().getColumn(6).setPreferredWidth(88);
			table.getColumnModel().getColumn(6).setMinWidth(88);
			table.getColumnModel().getColumn(7).setPreferredWidth(103);
			table.getColumnModel().getColumn(7).setMinWidth(103);
			scrollPane.setViewportView(table);
			
			JButton btnNewButton_1 = new JButton("New Bill");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clear();
					dateChooser_1.setDate(new Date());
					comboBox.setSelectedItem("");
					model.setRowCount(0);
					textField.setText(null);
				}
			});
			btnNewButton_1.setBounds(35, 615, 189, 26);
			panel.add(btnNewButton_1);
			
			
			addWindowListener( new WindowAdapter() {
				   public void windowOpened( WindowEvent e ){
					   comboBox.requestFocus();
				     }
				   } ); 
		}
		
		
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
				   comboBox.requestFocus();
			     }
			   } ); 
		
	}

	public void clock() {
		Calendar cal = new GregorianCalendar();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
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
			dbcon.rs = dbcon.st.executeQuery("Select partyname from party_details order by partyname asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("partyname");
				comboBox.addItem(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.st.close();
			dbcon.rs.close();
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

			String t = null;
			textField7.setText(now());
			textField2.setSelectedIndex(0);
			textField12.setText(t);
			textField10.setText(t);
			textField5.setText(t);
			textField5.setText(t);
			textField20.setText(t);
			textField19.setText(t);
			textField4.setText(t);
//			textField.setText(t);

		} catch (Exception e) {
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
//				textField3.setText(a);

				String b = dbcon.rs.getString("fssaino");
//				textField8.setText(b);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.st.close();
			dbcon.rs.close();
			dbcon.conn.close();
		}

	}

	public void inventoryproductentry(DefaultTableModel model) throws SQLException {
		try {

			int nRow = model.getRowCount(), nCol = model.getColumnCount();
			String[][] tableData;
			tableData = new String[nRow][nCol];
			String sql1 = null;
			for (int i = 0; i < nRow; i++) {
				for (int j = 0; j < nCol; j++) {
					tableData[i][j] = model.getValueAt(i, j).toString().toUpperCase();
				}

			}
			dbcon.connect();
			for (int i = 0; i < nRow; i++) {

				sql1 = "Insert into purchase_productlist VALUES('" + tableData[i][0].toUpperCase() + "','"
						+ tableData[i][1].toUpperCase() + "','" + tableData[i][2].toUpperCase() + "','"
						+ tableData[i][3].toUpperCase() + "','" + tableData[i][4].toUpperCase() + "','"
						+ tableData[i][5].toUpperCase() + "','" + tableData[i][6].toUpperCase() + "','"
						+ tableData[i][7].toUpperCase() + "','" + tableData[i][8].toUpperCase() + "','"
						+ tableData[i][9].toUpperCase() + "','" + tableData[i][10].toUpperCase() + "')";

				dbcon.pst = dbcon.conn.prepareStatement(sql1);
				dbcon.pst.executeUpdate();
//			JOptionPane.showMessageDialog(null, "INSERTED");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.pst.close();
			dbcon.conn.close();
		}

	}

	public void rmspurchase(String invoice, String billdate, String todaydate, String partyname, String billamount,
			String discount) throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
//			JOptionPane.showMessageDialog(null, invoice);
			String sql = "Insert into rmspurchase values('" + invoice + "','" + billdate + "','" + todaydate + "','"
					+ partyname + "','" + billamount + "','" + discount + "')";
			dbcon.st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Data Saved Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.st.close();
			dbcon.conn.close();
		}

	}

	public void insertuniqueid_inventory(String check) throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			String sql = "Insert into UID_inventory VALUES('" + check + "')";
			dbcon.st.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.st.close();

			dbcon.conn.close();
		}

	}
	
	public void showproduct() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select productname from productlist order by productname asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("productname");
				textField2.addItem(data);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.conn.close();
		}
	}
}
