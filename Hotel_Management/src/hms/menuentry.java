package hms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.Rectangle;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;

public class menuentry extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	JComboBox comboBox, comboBox_1;
	JLabel lblitemid;
	JButton btnNewButton, btnClearaltc, btnUpdatealtu;
	Databaseconnection dbcon = new Databaseconnection();
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	private JButton btnDelete;
	private JTextField textField;

	public menuentry() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setType(javax.swing.JFrame.Type.UTILITY);
		setUndecorated(true);

		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(menuentry.class.getResource("/hms/images/menulabel.png")));
		label_2.setBounds(44, 0, 197, 131);
		contentPanel.add(label_2);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 131, 522, 498);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JPanel menumaster = new JPanel();
		menumaster.setBounds(0, 0, 522, 498);
		panel_2.add(menumaster);
		menumaster.setBackground(Color.WHITE);
		menumaster.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		menumaster.setLayout(null);

		JLabel lblItemCode = new JLabel("Item Code");
		lblItemCode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItemCode.setBounds(90, 101, 71, 14);
		menumaster.add(lblItemCode);

		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItemName.setBounds(90, 144, 71, 14);
		menumaster.add(lblItemName);

		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				boolean result = Regex_alphanumeric(textField_1.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only");
					textField_1.setText(null);

				}

			}
		});
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				textField_2
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_2.requestFocus();
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(209, 136, 234, 33);
		menumaster.add(textField_1);

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
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_3.requestFocus();
				}
			}
		});

		textField_2.setColumns(10);
		textField_2.setBounds(209, 187, 234, 33);
		menumaster.add(textField_2);

		textField_3 = new JTextField();
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_decimalonly(textField_3.getText());
				boolean result1 = Regex_integeronly(textField_3.getText());
				if (result != true && result1 != true) {
					JOptionPane.showMessageDialog(null, "Please Enter correct value,try again!!");
					textField_3.setText(null);

				}

			}
		});
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBox.requestFocus();
				}
			}
		});

		textField_3.setColumns(10);
		textField_3.setBounds(209, 236, 234, 33);
		menumaster.add(textField_3);

		JLabel lblItemPrice = new JLabel("Item Price");
		lblItemPrice.setToolTipText("");
		lblItemPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItemPrice.setBounds(90, 244, 71, 14);
		menumaster.add(lblItemPrice);

		JLabel lblItemDescription = new JLabel("Item Description");
		lblItemDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItemDescription.setBounds(90, 194, 93, 14);
		menumaster.add(lblItemDescription);

		comboBox = new JComboBox();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_4.requestFocus();
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(209, 286, 234, 33);
		menumaster.add(comboBox);

		textField_4 = new JTextField();
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_integeronly(textField_4.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter integer/correct value,try again!!");
					textField_4.setText(null);

				}

			}
		});
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBox_1.requestFocus();
				}
			}
		});

		textField_4.setColumns(10);
		textField_4.setBounds(209, 336, 234, 33);
		menumaster.add(textField_4);

		JLabel lblCategoryType = new JLabel("Quantity");
		lblCategoryType.setToolTipText("");
		lblCategoryType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategoryType.setBounds(90, 344, 93, 14);
		menumaster.add(lblCategoryType);

		JLabel lblQuantity = new JLabel("Unit");
		lblQuantity.setToolTipText("");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(90, 295, 71, 14);
		menumaster.add(lblQuantity);

		comboBox_1 = new JComboBox();
		comboBox_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton.requestFocus();

				}
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		comboBox_1.setSelectedIndex(0);

		comboBox_1.setBounds(209, 386, 234, 33);
		menumaster.add(comboBox_1);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setToolTipText("");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategory.setBounds(90, 395, 71, 14);
		menumaster.add(lblCategory);

		btnNewButton = new JButton("SAVE [Alt+S]");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnClearaltc.requestFocus();

				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setToolTipText("Click to Save ");
		btnNewButton.setMnemonic('S');
		btnNewButton.setMnemonic(KeyEvent.VK_S);
		btnNewButton.setBackground(Color.GRAY);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String code = "ITEM/".concat(lblitemid.getText());

					String itembreak = "", name = "", description = "", price = "", abc = "", unit = "", quantity = "0",
							def = "", category = "";

					try {
						itembreak = lblitemid.getText();
					} catch (Exception f) {

						itembreak = "";
					}

					try {
						name = textField_1.getText().toUpperCase();
					} catch (Exception f) {

						name = "";
					}

					try {
						description = textField_2.getText().toUpperCase();
					} catch (Exception f) {

						description = "";
					}

					try {
						price = textField_3.getText().toUpperCase();
					} catch (Exception f) {

						price = "";
					}

					try {
						abc = (String) comboBox.getSelectedItem();
					} catch (Exception f) {

						abc = "";
					}

					try {
						unit = abc.toUpperCase();
					} catch (Exception f) {

						unit = "";
					}

					try {
						quantity = textField_4.getText().toUpperCase();
					} catch (Exception f) {

						quantity = "0";
					}

					try {
						def = (String) comboBox_1.getSelectedItem();
					} catch (Exception f) {

						def = "";
					}

					try {
						category = def.toUpperCase();
					} catch (Exception f) {

						category = "";
					}

					if (name.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter Item Name");
						textField_1.requestFocusInWindow();

					} else if (price.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter Price");
						textField_2.requestFocusInWindow();

					} else if (unit.length() == 0 || comboBox.getSelectedItem().equals("SELECT")) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Select Unit");
						comboBox.requestFocusInWindow();

					} else if (quantity.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter Quantity");
						comboBox.requestFocusInWindow();

					} else if (category.length() == 0 || comboBox_1.getSelectedItem().equals("SELECT")) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Select Category");
						comboBox_1.requestFocusInWindow();

					} else {
						dbcon.insertuniqueid_foritemid(itembreak);
						dbcon.insertmenuitem(code, name, description, price, unit, quantity, category);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setRowCount(0);

						ClearAll();
						ShowTable();
					}
				}

				catch (Exception e1) {

					JOptionPane.showMessageDialog(null, "Already Exists" + e1);
				} finally {
					try {
						dbcon.conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(92, 446, 140, 41);
		menumaster.add(btnNewButton);

		btnClearaltc = new JButton("CLEAR [Alt+C]");
		btnClearaltc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnDelete.requestFocus();

				}
			}
		});
		btnClearaltc.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		btnClearaltc.setForeground(Color.WHITE);
		btnClearaltc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClearaltc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClearaltc.setToolTipText("Click to Clear ");
		btnClearaltc.setMnemonic('C');
		btnClearaltc.setMnemonic(KeyEvent.VK_C);
		btnClearaltc.setBackground(Color.GRAY);
		btnClearaltc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClearAll();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnClearaltc.setBounds(233, 446, 140, 41);
		menumaster.add(btnClearaltc);

		JLabel lblItemEntry = new JLabel("Item Entry");
		lblItemEntry.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemEntry.setForeground(Color.BLACK);
		lblItemEntry.setOpaque(true);
		lblItemEntry.setBackground(SystemColor.control);
		lblItemEntry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemEntry.setBounds(0, 0, 591, 17);
		menumaster.add(lblItemEntry);

		btnUpdatealtu = new JButton("Update [Alt+U]");
		btnUpdatealtu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnClearaltc.requestFocus();

				}
			}
		});
		btnUpdatealtu.setForeground(Color.WHITE);
		btnUpdatealtu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdatealtu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdatealtu.setToolTipText("Click to Update ");
		btnUpdatealtu.setMnemonic(KeyEvent.VK_U);

		btnUpdatealtu.setVisible(false);
		btnUpdatealtu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String itemcode = "ITEM/".concat(lblitemid.getText());
					String data1 = textField_1.getText();
					String data2 = textField_2.getText();
					String data3 = textField_3.getText();
					String data4 = textField_4.getText();
					String data5 = (String) comboBox.getSelectedItem();

					String data7 = (String) comboBox_1.getSelectedItem();

					if (data1.length() == 0) {

					} else

					{
						String message = " Are You sure? ";
						String title = "UPDATE Item";
						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							dbcon.updatemenu(itemcode, data1, data2, data3, data4, data5, data7);
							model.setRowCount(0);
							ShowTable();
							ClearAll();
							btnUpdatealtu.setVisible(false);
							btnNewButton.setVisible(true);
						} else {
						}
					}
				} catch (Exception z) {
					JOptionPane.showMessageDialog(null, z);
				}
			}
		});
		btnUpdatealtu.setBackground(Color.GRAY);
		btnUpdatealtu.setBounds(92, 446, 140, 41);
		menumaster.add(btnUpdatealtu);

		lblitemid = new JLabel("");
		lblitemid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblitemid.setBounds(258, 93, 54, 22);
		menumaster.add(lblitemid);

		JLabel lblItem = new JLabel("ITEM/");
		lblItem.setForeground(Color.RED);
		lblItem.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblItem.setBounds(209, 93, 47, 22);
		menumaster.add(lblItem);

		btnDelete = new JButton("DELETE [Alt+D]");
		btnDelete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_1.requestFocus();

				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setVisible(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = " Are You sure? ";
				String title = "DELETE ITEM";

				try {

					String x = String.valueOf(table.getSelectedRow());
					if (x.length() != 0) {

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							boolean res = myMethod(table);

							JOptionPane.showMessageDialog(null, "Data Deleted Successfully");

							model.setRowCount(0);
							ShowTable();
							ClearAll();

						}

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		// btnDelete.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyReleased(KeyEvent e) {
		//
		//
		// }
		// });
		btnDelete.setToolTipText("Click to Clear ");
		btnDelete.setMnemonic('D');
		btnDelete.setMnemonic(KeyEvent.VK_D);
		btnDelete.setBackground(Color.GRAY);
		btnDelete.setBounds(373, 446, 140, 41);
		menumaster.add(btnDelete);
		try {
			ShowUnit();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			showcategory();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			set_max_function_itemid();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(532, 131, 440, 498);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 440, 498);
		panel_1.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "New column", "New column", "New column",
				"New column", "New column", "New column", "New column" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(65);
		table.getColumnModel().getColumn(0).setMinWidth(65);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		table.getColumnModel().getColumn(1).setMinWidth(99);
		table.getColumnModel().getColumn(3).setPreferredWidth(69);
		table.getColumnModel().getColumn(3).setMinWidth(69);
		table.getColumnModel().getColumn(4).setPreferredWidth(68);
		table.getColumnModel().getColumn(4).setMinWidth(68);
		table.getColumnModel().getColumn(5).setPreferredWidth(63);
		table.getColumnModel().getColumn(5).setMinWidth(63);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					String message = " Are You sure? ";
					String title = "DELETE ITEM";

					try {
						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							String inputclick = "ITEM/".concat(lblitemid.getText());
//							JOptionPane.showMessageDialog(null, inputclick);

							if (inputclick.length() == 0) {

							} else {
								myMethod(table);
//								dbcon.itemdelete(inputclick);

								model.setRowCount(0);
								ShowTable();
								ClearAll();

							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			}
		});

		table.setDefaultEditor(Object.class, null);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					btnDelete.setVisible(true);
					TableSelection();
					btnUpdatealtu.setVisible(true);
					btnNewButton.setVisible(false);

				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		try {
			ShowTable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scrollPane.setViewportView(table);

		JLabel label = new JLabel("MENU CONTROLLER");
		label.setOpaque(true);
		label.setForeground(SystemColor.controlText);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBackground(SystemColor.text);
		label.setBounds(0, 28, 958, 29);
		contentPanel.add(label);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String txt = textField.getText().toUpperCase();
				Filter(txt);
			}
		});
		textField.setBounds(532, 89, 440, 29);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(menuentry.class.getResource("/hms/images/Zoom-icon.png")));
		label_1.setBounds(495, 85, 46, 41);
		contentPanel.add(label_1);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				textField_1.requestFocus();
			}
		});

	}

	public void ShowTable() throws SQLException {
		try {
			dbcon.connect();
			String sql = "Select * from menuitem";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			model.setRowCount(0);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));

		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "SHOWtable");
		} finally {
			dbcon.conn.close();
		}
	}

	public void ClearAll() throws SQLException {

		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");

		comboBox.setSelectedIndex(0);
		comboBox_1.setSelectedIndex(0);
		set_max_function_itemid();
		btnNewButton.setVisible(true);
		btnUpdatealtu.setVisible(false);

		btnDelete.setVisible(false);

	}

	public void TableSelection() throws SQLException {
		try {
			dbcon.connect();
			model = (DefaultTableModel) table.getModel();

			int row = table.getSelectedRow();
			String TableClick = table.getValueAt(row, 0).toString();

			String sql = "Select Code,Name,Description,Price,Unit,Quantity,Category from menuitem where Code='"
					+ TableClick + "'";
			// JOptionPane.showMessageDialog(null, "asdasd");

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {

				String add0 = dbcon.rs.getString("Code");
				lblitemid.setText(add0.substring(5));
				String add1 = dbcon.rs.getString("Name");
				textField_1.setText(add1);

				String add2 = dbcon.rs.getString("Description");
				textField_2.setText(add2);

				String data6 = dbcon.rs.getString("Price");
				textField_3.setText(data6);

				String add3 = dbcon.rs.getString("Unit");
				comboBox.setSelectedItem(add3);

				String add4 = dbcon.rs.getString("Category");
				comboBox_1.setSelectedItem(add4);

				String add5 = dbcon.rs.getString("Quantity");
				textField_4.setText(add5);

			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

	}

	public void Delete() throws SQLException {
		try {
			dbcon.connect();
			String value1 = "ITEM/".concat(lblitemid.getText());
			String sql = "delete from menuitem where code='" + value1 + "' ";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.pst.execute();
			JOptionPane.showMessageDialog(null, "Succesfully deleted");

			ShowTable();
			ClearAll();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Delete");
		} finally {
			dbcon.conn.close();

		}
	}

	public void ShowUnit() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select unitname from unitlist");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("unitname");
				comboBox.addItem(data);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unit");
		} finally {
			dbcon.conn.close();

		}
	}

	public void showcategory() throws SQLException {
		try {
			dbcon.connect();

			dbcon.st = dbcon.conn.createStatement();
			String sql = "Select categoryname from categorylist";
			dbcon.rs = dbcon.st.executeQuery(sql);

			while (dbcon.rs.next()) {
				String datas = dbcon.rs.getString(1);
				comboBox_1.addItem(datas);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "SHOW");
		} finally {
			dbcon.conn.close();

		}
	}

	public void set_max_function_itemid() throws SQLException {
		try {
			dbcon.connect();
			// Select max(`Enquiry No`) as max from enquiry
			String query1 = "Select max(idgenerator) as max from Uniqueid4itemid";
			dbcon.pst = dbcon.conn.prepareStatement(query1);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				int num = dbcon.rs.getInt("max");
				int inc = num + 1;
				lblitemid.setText(String.valueOf(inc));
				// textField.setText("Item/" + String.valueOf(inc));
				// JOptionPane.showMessageDialog(null, inc);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "ID CODE ERROR");
		} finally {
			dbcon.conn.close();
		}
	}

	public void Filter(String sql) {
		model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(sql));
	}

	public boolean myMethod(JTable entryTable) throws SQLException {
		boolean res = false;
		String val1 = null;
		DefaultTableModel model = (DefaultTableModel) entryTable.getModel();
		if (entryTable.getRowCount() > 0) {
			if (entryTable.getSelectedRowCount() > 0) {
				int selectedRow[] = entryTable.getSelectedRows();
				for (int i : selectedRow) {

					val1 = entryTable.getValueAt(i, 0).toString();

					res = dbcon.itemdelete(val1);

				}
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
