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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

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

	JButton btnClearaltc = new JButton("RESET [Alt+R]");
	JComboBox comboBox = new JComboBox();

	JDateChooser dateChooser_1;
	private JComboBox textField2;
	private JTextField textField5;
	private JTextField textField10;
	private JTextField textField19;
	private JTextField textField20;
	private JTextField textField7;
	double total_amount = 0;
	Databaseconnection dbcon = new Databaseconnection();
	public static final String DateFormat = "yyyy/MM/dd";
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton;
	private JTextField textField_3;
	private JComboBox textField_4;
	private JDateChooser dateChooser;
	int sl = 0;
	 Double mrp_double = 0.0;
	private JTextField textField_1;
	private JTextField comboBox_1;
	private JTextField textField_2;
	private JTextField textField_5;

	public inventory() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setType(javax.swing.JFrame.Type.UTILITY);
		setUndecorated(true);

		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			// panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(0, 0, 1092, 652);
			getContentPane().add(panel, BorderLayout.EAST);
			panel.setLayout(null);

			JLabel label_1 = new JLabel("Invoice No.");
			label_1.setBounds(23, 72, 101, 28);
			label_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_1);

			JLabel lblTodaysDate = new JLabel("Today's Date");
			lblTodaysDate.setBounds(994, 39, 98, 28);
			lblTodaysDate.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(lblTodaysDate);

			JLabel lblBillDate = new JLabel("Bill Date");
			lblBillDate.setBounds(392, 74, 101, 25);
			lblBillDate.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(lblBillDate);

			JLabel label_5 = new JLabel("Party Name");
			label_5.setBounds(23, 121, 101, 20);
			label_5.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_5);

			JLabel label_9 = new JLabel("Product Name");
			label_9.setBounds(113, 152, 111, 28);
			label_9.setHorizontalAlignment(SwingConstants.LEFT);
			label_9.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_9);

			JLabel label_10 = new JLabel("Quantity");
			label_10.setBounds(327, 159, 57, 14);
			label_10.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(label_10);

			JLabel lblAmount = new JLabel("Amount");
			lblAmount.setBounds(664, 159, 109, 14);
			lblAmount.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(lblAmount);

			JLabel lblRate = new JLabel("Rate");
			lblRate.setBounds(461, 159, 110, 14);
			lblRate.setHorizontalAlignment(SwingConstants.CENTER);
			lblRate.setFont(new Font("Calibri", Font.PLAIN, 16));
			panel.add(lblRate);

			textField2 = new JComboBox();

			textField2.setEditable(true);
			AutoCompleteDecorator.decorate(textField2);
			textField2.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent event) {
					if (event.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							if (event.getKeyCode() == KeyEvent.VK_ENTER) {
								textField10.requestFocus();
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});

			textField2.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
			textField2.setSelectedIndex(0);
			textField2.setBounds(33, 179, 236, 31);
			textField2.setBackground(Color.WHITE);
			try {
				showproduct();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			panel.add(textField2);

			textField5 = new JTextField();
			textField5.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					boolean result = Regex_decimalonly(textField5.getText());
					boolean result1 = Regex_integeronly(textField5.getText());
					if (result != true && result1 != true) {
						JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
						textField5.setText(null);

					}
					// try {
					//
					// String expression = "[0-9]*";
					// String inputStr = textField10.getText();
					// Pattern pattern = Pattern.compile(expression);
					// Pattern.compile(expression);
					// Matcher matcher = pattern.matcher(inputStr);
					//
					// if (matcher.matches()) {
					// textField19.requestFocusInWindow();
					//
					// } else {
					//
					// JOptionPane.showMessageDialog(null, "Please! Enter Valid
					// Rate");
					// }
					// } catch (Exception f) {
					// }

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

							dateChooser.requestFocusInWindow();
						} catch (Exception f) {
						}

					}
				}
			});
			textField5.setBounds(444, 179, 161, 31);
			// textField5.addKeyListener(new KeyAdapter() {
			// @Override
			// public void keyReleased(KeyEvent e) {
			// try {
			// String qty = textField10.getText();
			//// ||
			//// e.getKeyCode() == KeyEvent.VK_TAB)
			// if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//
			// Double qtydouble = Double.parseDouble(textField10.getText());
			// Double purchaserate = Double.parseDouble(textField5.getText());
			//
			// textField20.setText(String.valueOf(qtydouble * purchaserate));
			//
			//
			// }
			//
			// } catch (Exception f) {
			//// JOptionPane.showMessageDialog(null, "Invalid entry");
			// }
			// }
			// });
			textField5.setColumns(10);
			textField5.setBackground(Color.WHITE);
			panel.add(textField5);

			textField7 = new JTextField();
			textField7.setEnabled(false);
			textField7.setBounds(1005, 63, 67, 34);
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
					boolean result = Regex_decimalonly(textField10.getText());
					boolean result1 = Regex_integeronly(textField10.getText());
					if (result != true && result1 != true) {
						JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
						textField10.setText(null);

					}
					// try {
					// String expression = "[0-9]*";
					// String inputStr = textField10.getText();
					// Pattern pattern = Pattern.compile(expression);
					// Pattern.compile(expression);
					// Matcher matcher = pattern.matcher(inputStr);
					//
					// if (matcher.matches()) {
					// textField5.requestFocusInWindow();
					//
					// } else {
					//
					// JOptionPane.showMessageDialog(null, "Please! Enter Valid
					// Quantity");
					// }
					// }catch(Exception f)
					// {}

				}
			});
			textField10.setBounds(279, 179, 161, 31);
			textField10.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						textField5.requestFocus();
					}
				}
			});
			textField10.setColumns(10);
			textField10.setBackground(Color.WHITE);
			panel.add(textField10);

			textField19 = new JTextField();
			textField19.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					boolean result = Regex_decimalonly(textField19.getText());
					boolean result1 = Regex_integeronly(textField19.getText());
					if (result != true && result1 != true) {
						JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
						textField19.setText(null);

					}
				}
			});
			textField19.setHorizontalAlignment(SwingConstants.RIGHT);
			textField19.setBounds(275, 565, 67, 31);
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
								Double subtotal_amount = Double.parseDouble(textField.getText());
								Double discount_percentage = Double.parseDouble(textField19.getText());

								Double dis_calci = subtotal_amount * (discount_percentage / 100);
								Double amount_amount = Math.round((dis_calci) * 100.0) / 100.0;

								textField_2.setText(String.valueOf(amount_amount));

								Double subtotal_after_discount = Math.round((subtotal_amount - amount_amount) * 100.0)
										/ 100.0;

								textField_3.setText(String.valueOf(subtotal_after_discount));

								// textField_4.setEnabled(true);
							} else {

								JOptionPane.showMessageDialog(null, "Please! Enter Valid Discount");
							}
						} catch (Exception f) {
						}

					}
				}
			});
			textField19.setColumns(10);
			textField19.setBackground(Color.WHITE);
			panel.add(textField19);
			comboBox.setBounds(155, 116, 897, 31);
			// comboBox.setEditable(true);

			comboBox.setBackground(Color.WHITE);
			// comboBox.addItemListener(new ItemListener() {
			// public void itemStateChanged(ItemEvent e) {
			//
			// try {
			// comboBox_selection();
			// } catch (SQLException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			// }
			//
			// });
			comboBox.setEditable(true);

			AutoCompleteDecorator.decorate(comboBox);
			comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent event) {
					if (event.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							if (event.getKeyCode() == KeyEvent.VK_ENTER) {
								textField2.requestFocus();
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});

			try {
				showparty_list();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			AutoCompleteDecorator.decorate(comboBox);
			panel.add(comboBox);

			textField20 = new JTextField();
			textField20.setEditable(false);
			// textField20.addKeyListener(new KeyAdapter() {
			// @Override
			// public void keyReleased(KeyEvent e) {
			// if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// textField19.requestFocus();
			// }
			// }
			// });
			textField20.setBounds(606, 179, 167, 31);

			textField20.setColumns(10);
			textField20.setBackground(Color.WHITE);
			panel.add(textField20);
			// btnSavealt.addKeyListener(new KeyAdapter() {
			// @Override
			// public void keyReleased(KeyEvent e) {
			// if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// btnClearaltc.requestFocus();
			// }
			// }
			// });
			btnSavealt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {

						String message = " Are You Really wants to SUBMIT ? ";
						String title = "SUBMIT";
						DefaultTableModel mod = new DefaultTableModel();
						mod = (DefaultTableModel) table.getModel();
						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION && mod.getRowCount() != 0
								&& comboBox.getSelectedItem() != null) {

							model = (DefaultTableModel) table.getModel();

							String todaydate = textField7.getText().toUpperCase();
							String invoice = label.getText().concat(label_7.getText());
							String billdate = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
							String partyname = comboBox.getSelectedItem().toString();
							String subtotal = textField.getText();
							String discount_amount = textField_2.getText();
							String amount_afterdiscount = textField_3.getText();
							String taxvalue = textField_1.getText();

							Double taxvalue_double = Math.round(Double.parseDouble(taxvalue) * 100.0) / 100.0;
							Double dividedby2 = taxvalue_double / 2;

							Double cgst = Math.round((dividedby2) * 100.0) / 100.0;
							String gtotal = comboBox_1.getText();

							rmspurchase(invoice, billdate, todaydate, partyname, subtotal, discount_amount,
									amount_afterdiscount, cgst, cgst, gtotal);

							// JOptionPane.showMessageDialog(null, "DD");
							inventoryproductentry(invoice, billdate, partyname, model);

							stockentry(model);
							balanceamountentry(Math.round((Double.parseDouble(gtotal)) * 100.0) / 100.0, partyname);
							clear();
							dateChooser_1.setDate(new Date());
							comboBox.setSelectedItem("");
							model.setRowCount(0);

							String check = label_7.getText();

							insertuniqueid_inventory(check);
							set_max_function();
							mrp_double=0.0;

							// JOptionPane.showMessageDialog(null, "Select
							// Vendor Name");
						} else {
							JOptionPane.showMessageDialog(null, "Select Vendor Name");

						}
					} catch (Exception f) {
					}

				}

			});
			btnSavealt.setBounds(383, 607, 304, 34);
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
			// btnClearaltc.addKeyListener(new KeyAdapter() {
			// @Override
			// public void keyReleased(KeyEvent e) {
			//// dateChooser_1
			// if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// dateChooser_1.requestFocus();
			// }
			// }
			// });
			btnClearaltc.setBounds(728, 607, 300, 34);
			btnClearaltc.setBackground(Color.GRAY);
			btnClearaltc.setForeground(Color.WHITE);
			btnClearaltc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnClearaltc.setMnemonic('R');

			btnClearaltc.setMnemonic(KeyEvent.VK_R);
			btnClearaltc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						// clear();

						clear();

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
			textField.setHorizontalAlignment(SwingConstants.RIGHT);
			textField.setText("0.0");
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					// btnNewButton
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnNewButton.requestFocus();
					}
				}
			});
			textField.setColumns(10);
			textField.setBackground(Color.WHITE);
			textField.setBounds(87, 565, 180, 31);
			panel.add(textField);

			btnNewButton = new JButton("Add Item to List [Alt+A]");
			// btnNewButton.addKeyListener(new KeyAdapter() {
			// @Override
			// public void keyReleased(KeyEvent e) {
			//// if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//// btnSavealt.requestFocus();
			//// }
			// }
			// });
			btnNewButton.setMnemonic('A');
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setBackground(Color.GRAY);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String productname = textField2.getSelectedItem().toString();
						String qty = textField10.getText();
						String purchaserate = textField5.getText();
						String mrp = textField20.getText();
						String expiry = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						String batchno = textField_5.getText();
						sl++;

						if (mrp.length() != 0)

						{
							Double mrp_doub = Math.round(Double.parseDouble(mrp) * 100.0) / 100.0;

							Object[] row = { sl, productname, qty, purchaserate, mrp_doub, expiry, batchno };

							model = (DefaultTableModel) table.getModel();
							model.addRow(row);

					
							mrp_double = mrp_double + Math.round(Double.parseDouble(mrp) * 100.0) / 100.0;

							textField.setText(String.valueOf(Math.round(mrp_double * 100.0) / 100.0));
							// clear();
							textField10.setText(null);
							textField5.setText(null);

							textField20.setText(null);
							textField_5.setText(null);
							dateChooser.setCalendar(null);
							textField2.setSelectedIndex(0);

						}

					} catch (Exception f) {
					}

				}
			});
			btnNewButton.setBounds(364, 232, 323, 37);
			panel.add(btnNewButton);

			JPanel panel_1 = new JPanel();
			panel_1.setBounds(33, 269, 1039, 254);
			panel.add(panel_1);
			panel_1.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 1039, 254);
			panel_1.add(scrollPane);

			table = new JTable();
			table.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					try {
						if (e.getKeyCode() == KeyEvent.VK_DELETE) {
							// JOptionPane.showMessageDialog(null, "YYYY");

							model.getValueAt(table.getSelectedRow(), 4);

							String total_bill_amount = textField.getText();
							String amount_dedudct = model.getValueAt(table.getSelectedRow(), 4).toString();
							
							

							Double total_bill_amount_double = Math.round(Double.parseDouble(total_bill_amount) * 100.0)
									/ 100.0;

							
							Double amount_dedudct_double = Math.round(Double.parseDouble(amount_dedudct) * 100.0)
									/ 100.0;

							
							Double final_am = Math.round(total_bill_amount_double - amount_dedudct_double) * 100.0
									/ 100.0;

							mrp_double=mrp_double-amount_dedudct_double;
							// JOptionPane.showMessageDialog(null,
							// total_bill_amount_double);
							// JOptionPane.showMessageDialog(null,
							// amount_dedudct_double);
							// JOptionPane.showMessageDialog(null, final_am);
							textField.setText(final_am.toString());
							int selectedrowindex = table.getSelectedRow();
							model.removeRow(selectedrowindex);
							total_bill_amount_double = 0.0;
							amount_dedudct_double = 0.0;
							final_am = 0.0;
						}
					} catch (Exception f) {
					}
				}
			});
			table.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "Sl.No.", "Product Name", "Quantity", "Rate", "Amount", "Expiry", "Batch No." }) {
				boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, true };

				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table.getColumnModel().getColumn(0).setPreferredWidth(42);
			table.getColumnModel().getColumn(0).setMinWidth(42);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(1).setMinWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(96);
			table.getColumnModel().getColumn(2).setMinWidth(96);
			table.getColumnModel().getColumn(3).setPreferredWidth(95);
			table.getColumnModel().getColumn(3).setMinWidth(95);
			table.getColumnModel().getColumn(4).setPreferredWidth(96);
			table.getColumnModel().getColumn(4).setMinWidth(96);
			scrollPane.setViewportView(table);

			JButton btnNewButton_1 = new JButton("New Bill [Alt+N]");
			btnNewButton_1.setMnemonic('N');
			btnNewButton_1.setMnemonic(KeyEvent.VK_N);
			btnNewButton_1.setForeground(Color.WHITE);
			btnNewButton_1.setBackground(Color.GRAY);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						clear();
						String t = null;
						dateChooser_1.setDate(new Date());
						comboBox.setSelectedItem("");
						model.setRowCount(0);
						textField.setText(null);
						textField19.setText(t);
						textField_2.setText("0");
						textField_3.setText("0.0");
						textField_4.setSelectedIndex(0);
						textField_1.setText("0");
						comboBox_1.setText(t);
						mrp_double=0.0;
						set_max_function();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block

					}
				}
			});
			btnNewButton_1.setBounds(87, 607, 263, 34);
			panel.add(btnNewButton_1);

			dateChooser = new JDateChooser();
			dateChooser.getCalendarButton().setBackground(SystemColor.inactiveCaptionBorder);
			dateChooser.setDateFormatString("yyyy/MM/dd");
			dateChooser.setCalendar(null);
			dateChooser.setBackground(SystemColor.inactiveCaptionBorder);
			dateChooser.setBounds(782, 179, 137, 31);
			panel.add(dateChooser);

			JLabel lblExpiry = new JLabel("Expiry");
			lblExpiry.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblExpiry.setBounds(828, 159, 57, 14);
			panel.add(lblExpiry);

			JLabel lblSubTotal = new JLabel("Sub Total");
			lblSubTotal.setHorizontalAlignment(SwingConstants.CENTER);
			lblSubTotal.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblSubTotal.setBounds(101, 544, 123, 25);
			panel.add(lblSubTotal);

			JLabel lblDiscount_1 = new JLabel("Discount ");
			lblDiscount_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblDiscount_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblDiscount_1.setBounds(252, 529, 123, 25);
			panel.add(lblDiscount_1);

			textField_3 = new JTextField();
			textField_3.setText("0.0");
			textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
			textField_3.setColumns(10);
			textField_3.setBounds(456, 565, 180, 31);
			panel.add(textField_3);

			JLabel lblAmountAfterDiscount = new JLabel("Amount after discount");
			lblAmountAfterDiscount.setHorizontalAlignment(SwingConstants.CENTER);
			lblAmountAfterDiscount.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblAmountAfterDiscount.setBounds(466, 544, 154, 25);
			panel.add(lblAmountAfterDiscount);

			JLabel lblTaxRate = new JLabel("Tax Rate");
			lblTaxRate.setHorizontalAlignment(SwingConstants.CENTER);
			lblTaxRate.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblTaxRate.setBounds(650, 544, 154, 25);
			panel.add(lblTaxRate);

			textField_4 = new JComboBox();

			comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent event) {
					try {
						if (event.getKeyChar() == KeyEvent.VK_ENTER) {
							Double taxrate = Double.parseDouble(textField_4.getSelectedItem().toString());
							Double amount_after_discount = Double.parseDouble(textField_3.getText());

							Double taxvalue = amount_after_discount * (taxrate / 100);
							textField_1.setText(taxvalue.toString());

							Double amount_total = Math.round((amount_after_discount + taxvalue) * 100.0) / 100.0;
							comboBox_1.setText(amount_total.toString());

						}
					} catch (Exception e) {
					}
				}
			});

			// textField_4.addActionListener(new ActionListener() {
			// public void actionPerformed(ActionEvent e) {
			// Double taxrate =
			// Double.parseDouble(textField_4.getSelectedItem().toString());
			// Double amount_after_discount =
			// Double.parseDouble(textField_3.getText());
			//
			// Double taxvalue = amount_after_discount * (taxrate / 100);
			// textField_1.setText(taxvalue.toString());
			//
			// Double amount_total = Math.round((amount_after_discount +
			// taxvalue) * 100.0) / 100.0;
			// comboBox_1.setText(amount_total.toString());
			// }
			// });
			try {
				ShowTax();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// textField_4.setEditable(true);

			textField_4.setEditable(true);

			AutoCompleteDecorator.decorate(textField_4);
			textField_4.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent event) {
					if (event.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							Double taxrate = Double.parseDouble(textField_4.getSelectedItem().toString());
							Double amount_after_discount = Double.parseDouble(textField_3.getText());

							Double taxvalue = amount_after_discount * (taxrate / 100);
							Double taxvalue_roundoff = Math.round((taxvalue) * 100.0) / 100.0;
							textField_1.setText(taxvalue_roundoff.toString());

							Double amount_total = Math.round((amount_after_discount + taxvalue) * 100.0) / 100.0;
							comboBox_1.setText(amount_total.toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
						}

					}
				}
			});

			textField_4.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					try {
						Double taxrate = Double.parseDouble(textField_4.getSelectedItem().toString());
						Double amount_after_discount = Double.parseDouble(textField_3.getText());

						Double taxvalue = amount_after_discount * (taxrate / 100);
						Double taxvalue_roundoff = Math.round((taxvalue) * 100.0) / 100.0;
						textField_1.setText(taxvalue_roundoff.toString());

						Double amount_total = Math.round((amount_after_discount + taxvalue) * 100.0) / 100.0;
						comboBox_1.setText(amount_total.toString());
						//
						// textField_3.setText(String.valueOf(amount_after_discount));
					} catch (Exception f) {
					}
				}

			});
			textField_4.setEditable(true);
			AutoCompleteDecorator.decorate(textField_4);
			textField_4.setBounds(650, 565, 154, 31);
			panel.add(textField_4);

			JLabel lblTotalAmount = new JLabel("Total Amount");
			lblTotalAmount.setHorizontalAlignment(SwingConstants.CENTER);
			lblTotalAmount.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblTotalAmount.setBounds(898, 544, 154, 25);
			panel.add(lblTotalAmount);

			comboBox_1 = new JTextField();
			comboBox_1.setEditable(false);
			comboBox_1.setHorizontalAlignment(SwingConstants.RIGHT);
			comboBox_1.setBounds(898, 565, 154, 31);
			panel.add(comboBox_1);

			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setBounds(814, 565, 74, 31);
			panel.add(textField_1);
			textField_1.setColumns(10);

			JLabel lblTaxValue = new JLabel("Tax Value");
			lblTaxValue.setHorizontalAlignment(SwingConstants.CENTER);
			lblTaxValue.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblTaxValue.setBounds(770, 544, 154, 25);
			panel.add(lblTaxValue);

			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setText("0");
			textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
			textField_2.setColumns(10);
			textField_2.setBackground(Color.WHITE);
			textField_2.setBounds(364, 565, 67, 31);
			panel.add(textField_2);

			JLabel lblDisvalue = new JLabel("Value");
			lblDisvalue.setHorizontalAlignment(SwingConstants.CENTER);
			lblDisvalue.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblDisvalue.setBounds(338, 544, 123, 25);
			panel.add(lblDisvalue);

			JLabel lblDiscount = new JLabel("Discount");
			lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
			lblDiscount.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblDiscount.setBounds(338, 529, 123, 25);
			panel.add(lblDiscount);

			JLabel lblIn = new JLabel("in %");
			lblIn.setHorizontalAlignment(SwingConstants.CENTER);
			lblIn.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblIn.setBounds(252, 544, 123, 25);
			panel.add(lblIn);

			textField_5 = new JTextField();
			textField_5.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					boolean result = Regex_alphanumeric(textField_5.getText());
					if (result != true) {
						JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
						textField_5.setText(null);

					}
				}
			});
			textField_5.setColumns(10);
			textField_5.setBackground(Color.WHITE);
			textField_5.setBounds(924, 179, 148, 31);
			panel.add(textField_5);

			JLabel lblBatchNo = new JLabel("Batch No.");
			lblBatchNo.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblBatchNo.setBounds(963, 159, 109, 14);
			panel.add(lblBatchNo);
			
			JLabel lblNewLabel = new JLabel("Note: Press Enter to calculate");
			lblNewLabel.setBounds(444, 210, 161, 14);
			panel.add(lblNewLabel);

			addWindowListener(new WindowAdapter() {
				public void windowOpened(WindowEvent e) {
					comboBox.requestFocus();
				}
			});
		}

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				comboBox.requestFocus();
			}
		});

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
			// textField12.setText(t);

			textField19.setText(t);

			textField.setText("0.0");
			textField_2.setText(t);
			textField_3.setText("0.0");
			// textField4.setText(t);
			textField.setText(t);
			textField_4.setSelectedIndex(0);
			textField_1.setText("0.0");
			comboBox_1.setText("0.0");
			// dateChooser_1.setDate(null);
			comboBox.setSelectedItem("");
			model.setRowCount(0);

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
				// textField3.setText(a);

				String b = dbcon.rs.getString("fssaino");
				// textField8.setText(b);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.st.close();
			dbcon.rs.close();
			dbcon.conn.close();
		}

	}

	public void inventoryproductentry(String invoice, String billdate, String partyname, DefaultTableModel model)
			throws SQLException {
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

				sql1 = "Insert into purchase_productlist(Invoice,Billdate,Partyname,Productname,Quantity,Rate,MRP,expiry,Batch_No) VALUES('"
						+ invoice + "','" + billdate + "','" + partyname + "','" + tableData[i][1].toUpperCase() + "','"
						+ tableData[i][2].toUpperCase() + "','" + tableData[i][3].toUpperCase() + "','"
						+ tableData[i][4].toUpperCase() + "','" + tableData[i][5].toUpperCase() + "','"
						+ tableData[i][6].toUpperCase() + "')";

				dbcon.pst = dbcon.conn.prepareStatement(sql1);
				dbcon.pst.executeUpdate();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.pst.close();
			dbcon.conn.close();
		}

	}

	public void rmspurchase(String invoice, String billdate, String todaydate, String partyname, String subtotal,
			String discount_amount, String amount_afterdiscount, Double cgst, Double cgst2, String gtotal)
			throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			String b = "";
			Double v = 0.0;
			// JOptionPane.showMessageDialog(null, invoice);
			String sql = "Insert into rmspurchase(Invoice,Billdate,Purchasedate,Partyname,Subtotal,Discountamount, Amount_afterdiscount, Cgst,Sgst,Totalamount,Last_paiddate,Balance_Amount,Last_paidamount) values('"
					+ invoice + "','" + billdate + "','" + todaydate + "','" + partyname + "','" + subtotal + "','"
					+ discount_amount + "','" + amount_afterdiscount + "','" + cgst + "','" + cgst2 + "','" + gtotal
					+ "','" + b + "','" + gtotal + "','" + v + "')";
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

	public void ShowTax() throws SQLException {
		try {
			dbcon.connect();
			Statement st = dbcon.conn.createStatement();

			ResultSet rs = st.executeQuery("Select gst from gstlist");
			while (rs.next()) {
				String data = rs.getString(1);
				textField_4.addItem(data);

			}
		} catch (Exception e1) {
		} finally {
			dbcon.conn.close();
		}
	}

	public void stockentry(DefaultTableModel model) throws SQLException {

		try {
			dbcon.connect();
			String[][] tableData;
			int nRow = model.getRowCount(), nCol = model.getColumnCount();
			// JOptionPane.showMessageDialog(null, nRow);
			// JOptionPane.showMessageDialog(null, nCol);

			tableData = new String[nRow][nCol];
			for (int i = 0; i < nRow; i++) {
				for (int j = 0; j < nCol; j++) {
					tableData[i][j] = model.getValueAt(i, j).toString().toUpperCase();
					// JOptionPane.showMessageDialog(null, tableData[i][j]);

				}
				String sql1 = "UPDATE Product_Stock SET Quantity=Quantity+? WHERE Product_Name=?";

				dbcon.pst = dbcon.conn.prepareStatement(sql1);

				dbcon.pst.setInt(1, Integer.parseInt(tableData[i][2]));
				dbcon.pst.setString(2, tableData[i][1]);
				dbcon.pst.executeUpdate();

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.pst.close();
			dbcon.conn.close();
		}

	}

	public void balanceamountentry(double gtotal, String partyname) throws SQLException {
		try {
			dbcon.connect();
			String sql1 = "UPDATE party_details SET Balance_Amount=Balance_Amount+? WHERE partyname=?";
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
