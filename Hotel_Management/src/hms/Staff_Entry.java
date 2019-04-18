package hms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class Staff_Entry extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	JComboBox comboBox_1, comboBox_2;
	DefaultTableModel model = new DefaultTableModel();
	Databaseconnection dbcon = new Databaseconnection();
	JComboBox comboBox;
	JLabel label_1;
	JButton button_1, button;
	private JLabel lblStaff;
	private JButton btnClear;

	public Staff_Entry() {
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

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Staff_Entry.class.getResource("/hms/images/2-10-512.png")));
		label.setBounds(25, 152, 94, 106);
		contentPanel.add(label);

		JLabel lblStaffentry = new JLabel("Staff_Entry");
		lblStaffentry.setBounds(0, 11, 1092, 19);
		lblStaffentry.setOpaque(true);
		lblStaffentry.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblStaffentry.setForeground(Color.BLACK);
		lblStaffentry.setBackground(Color.WHITE);
		lblStaffentry.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblStaffentry);

		JLabel lblStaffId = new JLabel("ID");
		lblStaffId.setBounds(153, 65, 112, 24);
		lblStaffId.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffId.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblStaffId);

		JLabel lblStaffName = new JLabel("Name");
		lblStaffName.setBounds(153, 115, 112, 24);
		lblStaffName.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffName.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblStaffName);

		JLabel lblStaffFloor = new JLabel("Age");
		lblStaffFloor.setBounds(153, 165, 112, 24);
		lblStaffFloor.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffFloor.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblStaffFloor);

		JLabel lblStaffSalary = new JLabel("Salary");
		lblStaffSalary.setBounds(153, 215, 112, 24);
		lblStaffSalary.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffSalary.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblStaffSalary);

		JLabel lblStaffDesignation = new JLabel("Designation");
		lblStaffDesignation.setBounds(153, 265, 112, 24);
		lblStaffDesignation.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffDesignation.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblStaffDesignation);

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
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_2.requestFocus();
				}
			}
		});
		textField_1.setBounds(352, 116, 157, 24);
		textField_1.setBackground(Color.WHITE);
		textField_1.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_1.setColumns(10);
		contentPanel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_integeronly(textField_2.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter integer/correct value,try again!!");
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
		textField_2.setBounds(352, 166, 157, 24);
		textField_2.setBackground(Color.WHITE);
		textField_2.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_2.setColumns(10);
		contentPanel.add(textField_2);

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
					comboBox_1.requestFocus();
				}
			}
		});
		textField_3.setBounds(352, 216, 157, 24);
		textField_3.setBackground(Color.WHITE);
		textField_3.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_3.setColumns(10);
		contentPanel.add(textField_3);

		JButton btnPressAltsTo = new JButton("Press Alt+S To Save");
		btnPressAltsTo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				btnClear
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnClear.requestFocus();
				}
			}
		});
		btnPressAltsTo.setForeground(Color.WHITE);
		btnPressAltsTo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPressAltsTo.setBounds(352, 401, 197, 37);
		btnPressAltsTo.setBackground(Color.GRAY);
		btnPressAltsTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String Name = textField_1.getText();
					String Age = textField_2.getText();
					String Salary = textField_3.getText();
					String staffid = "STAFF/".concat(label_1.getText());
					String mobile = textField_5.getText();
					String address = textField_6.getText();
					String idtype = textField_8.getText();
					String idnumber = textField_9.getText();
					String comboconvert = (String) comboBox.getSelectedItem();
					String staffidbreak = label_1.getText();
					// JOptionPane.showMessageDialog(null, staffidbreak);

					String comboconvertdesignation = (String) comboBox_1.getSelectedItem();

					if (Name.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter Staff Name");
						textField_1.requestFocusInWindow();

					} else if (Age.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter Age");
						textField_2.requestFocusInWindow();

					} else if (Salary.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter Salary");
						textField_3.requestFocusInWindow();
					} else if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Select Designation");
						comboBox_1.requestFocusInWindow();
					} else if (comboBox_1.getSelectedItem() == null || comboBox_1.getSelectedItem().equals("SELECT")) {
//						if (comboBox_2.getSelectedItem() == null || comboBox_2.getSelectedItem().equals("SELECT")) {
//							comboBox_2.requestFocusInWindow();
//							JOptionPane.showMessageDialog(null, "Select Waiter Name");
//
//						}
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Select Shift");
						comboBox.requestFocusInWindow();
					} else if (mobile.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter Mobile Number");
						textField_5.requestFocusInWindow();
					} else if (address.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter Address");
						textField_6.requestFocusInWindow();
					} else if (idtype.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter ID PROOF Type");
						textField_8.requestFocusInWindow();
					} else if (idnumber.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter ID PROOF number");
						textField_9.requestFocusInWindow();
					} else {

						dbcon.insertuniqueid_forstaffid(staffidbreak);
						dbcon.staffentry(staffid.toUpperCase(), textField_1.getText().toUpperCase(),
								textField_2.getText().toUpperCase(), textField_3.getText().toUpperCase(),
								comboconvertdesignation.toUpperCase(), comboconvert.toUpperCase(),
								textField_5.getText().toUpperCase(), textField_6.getText().toUpperCase(),
								textField_7.getText().toUpperCase(), textField_8.getText().toUpperCase(),
								textField_9.getText().toUpperCase());
						set_max_function_for_staffid();
						reset();
						comboBox_2.removeAllItems();
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
						try {
							Showstafflist();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnPressAltsTo.setMnemonic('S');
		contentPanel.add(btnPressAltsTo);

		JPanel panel = new JPanel();
		panel.setBounds(568, 70, 323, 286);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Personal Information",
				TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblStaffMobile = new JLabel("Mobile");
		lblStaffMobile.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffMobile.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblStaffMobile.setBounds(10, 25, 112, 24);
		panel.add(lblStaffMobile);

		JLabel lblStaffAddress = new JLabel("Address");
		lblStaffAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffAddress.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblStaffAddress.setBounds(10, 75, 112, 24);
		panel.add(lblStaffAddress);

		JLabel lblStaffEmail = new JLabel("Email");
		lblStaffEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffEmail.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblStaffEmail.setBounds(10, 125, 112, 24);
		panel.add(lblStaffEmail);

		JLabel lblStaffIdProof = new JLabel("ID Proof type");
		lblStaffIdProof.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffIdProof.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblStaffIdProof.setBounds(10, 175, 112, 24);
		panel.add(lblStaffIdProof);

		JLabel lblStaffProofValue = new JLabel("ID Number");
		lblStaffProofValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblStaffProofValue.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblStaffProofValue.setBounds(10, 225, 112, 24);
		panel.add(lblStaffProofValue);

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
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_6.requestFocus();
				}
			}
		});
		textField_5.setBackground(Color.WHITE);
		textField_5.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_5.setColumns(10);
		textField_5.setBounds(132, 26, 168, 24);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_6.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_6.setText(null);

				}
			}
		});
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_7.requestFocus();
				}
			}
		});
		textField_6.setBackground(Color.WHITE);
		textField_6.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_6.setColumns(10);
		textField_6.setBounds(132, 76, 168, 24);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_email(textField_7.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please check email ###@###.###,try again!!");
					textField_7.setText(null);

				}

			}
		});
		textField_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_8.requestFocus();
				}
			}
		});
		textField_7.setBackground(Color.WHITE);
		textField_7.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_7.setColumns(10);
		textField_7.setBounds(132, 126, 168, 24);
		panel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_8.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_8.setText(null);

				}
			}
		});
		textField_8.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_9.requestFocus();
				}
			}
		});
		textField_8.setBackground(Color.WHITE);
		textField_8.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_8.setColumns(10);
		textField_8.setBounds(132, 176, 168, 24);
		panel.add(textField_8);

		textField_9 = new JTextField();
		textField_9.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				btnPressAltsTo
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnPressAltsTo.requestFocus();
				}
			}
		});
		textField_9.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				btnPressAltsTo.requestFocusInWindow();
			}
		});
		textField_9.setBackground(Color.WHITE);
		textField_9.setFont(new Font("Calibri", Font.PLAIN, 13));
		textField_9.setColumns(10);
		textField_9.setBounds(132, 226, 168, 24);
		panel.add(textField_9);

		JLabel lblShift = new JLabel("Shift");
		lblShift.setBounds(153, 316, 112, 24);
		lblShift.setHorizontalAlignment(SwingConstants.LEFT);
		lblShift.setFont(new Font("Calibri", Font.PLAIN, 15));
		contentPanel.add(lblShift);

		comboBox = new JComboBox();
		comboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textField_5.requestFocusInWindow();
			}
		});
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_5.requestFocus();
				}
			}
		});
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(352, 316, 157, 24);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT", "Morning", "Evening", "Night" }));
		contentPanel.add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBox.requestFocus();
				}
			}
		});

		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		try {
			showdesignation();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e2);
		}
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(352, 265, 157, 24);
		contentPanel.add(comboBox_1);

		JLabel lblSelectStaffName = new JLabel("Select Staff Name to See details");
		lblSelectStaffName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSelectStaffName.setBounds(66, 466, 268, 28);
		contentPanel.add(lblSelectStaffName);

		comboBox_2 = new JComboBox();
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					// SAVE.setEnabled(false);
					// btnUpdateDetails.setEnabled(true);
					// btnDeleteDetailsaltd.setEnabled(true);
					if (comboBox_2.getSelectedItem() == null || comboBox_2.getSelectedItem().equals("SELECT")) {
						comboBox_2.requestFocusInWindow();

					} else {
						button.setEnabled(true);
						button_1.setEnabled(true);
						staffname_selection();
						btnPressAltsTo.setEnabled(false);
						button.requestFocus();
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
		comboBox_2.setBounds(66, 493, 978, 28);
		contentPanel.add(comboBox_2);

		button = new JButton("Update details [Alt+U]");
		button.setMnemonic('U');
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					button_1.requestFocus();
				}

			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(Color.GRAY);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String message = " Are You sure? ";
				String title = "UPDATE Staff Details";
				try {
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {

						String comboconvert = comboBox.getSelectedItem().toString();

//						 JOptionPane.showMessageDialog(null, comboconvert);
						String staffid = "STAFF/".concat(label_1.getText());
						String comboconvertdesignation = comboBox_1.getSelectedItem().toString();
//						 JOptionPane.showMessageDialog(null, staffid);
//						 JOptionPane.showMessageDialog(null, comboconvertdesignation);

						dbcon.updatestaff_details(staffid.toUpperCase(), textField_1.getText().toUpperCase(),
								textField_2.getText().toUpperCase(), textField_3.getText().toUpperCase(),
								comboconvertdesignation.toUpperCase(), comboconvert.toUpperCase(),
								textField_5.getText().toUpperCase(), textField_6.getText().toUpperCase(),
								textField_7.getText().toUpperCase(), textField_8.getText().toUpperCase(),
								textField_9.getText().toUpperCase());

						reset();
						comboBox_2.removeAllItems();
						comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

						Showstafflist();
						comboBox_2.setSelectedIndex(0);
						button.setEnabled(false);
						button_1.setEnabled(false);
						btnPressAltsTo.setEnabled(true);

					}
				} catch (SQLException e1) {

					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button.setEnabled(false);
		button.setBounds(192, 574, 235, 28);
		contentPanel.add(button);

		button_1 = new JButton("Delete details [Alt+D]");
		button_1.setMnemonic('D');
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.GRAY);
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String message = " Are You sure? ";
				String title = "DELETE Staff";
				try {
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {

//						JOptionPane.showMessageDialog(null,label_1.getText());
						String abc = lblStaff.getText();
						String inputclick = abc.concat(label_1.getText());
						if (inputclick.length() == 0) {

						} else {

							dbcon.newstaffdelete(inputclick);

							comboBox_2.removeAllItems();
							comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

							Showstafflist();
							comboBox_2.setSelectedIndex(0);
							reset();
							set_max_function_for_staffid();
							button_1.setEnabled(false);
							button.setEnabled(false);
							btnPressAltsTo.setEnabled(true);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button_1.setEnabled(false);
		button_1.setBounds(563, 574, 235, 28);
		contentPanel.add(button_1);

		JLabel lblClickToDelete = new JLabel("Click to Delete details of Selected Staff");
		lblClickToDelete.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblClickToDelete.setBounds(549, 601, 268, 28);
		contentPanel.add(lblClickToDelete);

		JLabel lblClickToUpdate = new JLabel("Click to Update details of Selected Staff");
		lblClickToUpdate.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblClickToUpdate.setBounds(178, 601, 268, 28);
		contentPanel.add(lblClickToUpdate);

		btnClear = new JButton("Clear [Alt+C]");
		btnClear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				comboBox_2
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBox_2.requestFocus();
				}
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					button.setEnabled(false);
					button_1.setEnabled(false);
					btnPressAltsTo.setEnabled(true);
					reset();
					set_max_function_for_staffid();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnClear.setMnemonic('C');
		btnClear.setBackground(Color.GRAY);
		btnClear.setBounds(671, 401, 197, 37);
		contentPanel.add(btnClear);

		lblStaff = new JLabel("STAFF/");
		lblStaff.setForeground(Color.RED);
		lblStaff.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblStaff.setBounds(352, 67, 63, 22);
		contentPanel.add(lblStaff);

		label_1 = new JLabel("");
		try {
			set_max_function_for_staffid();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(408, 67, 54, 22);
		contentPanel.add(label_1);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				textField_1.requestFocus();
			}
		});

	}

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

	public void showdesignation() throws SQLException {

		try {

			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st.executeQuery("Select designation from Designationlist");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				comboBox_1.addItem(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "CONTACT SUPPORT TEAM!");
		} finally {
			dbcon.conn.close();
		}

	}

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
		comboBox.setSelectedIndex(0);
		comboBox_1.setSelectedIndex(0);
		set_max_function_for_staffid();

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
				comboBox_1.setSelectedItem(f);

				String g = dbcon.rs.getString("staffshift");
				comboBox.setSelectedItem(g);

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

			}
		}

		catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

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

	public boolean Regex_email(String abc) {
		boolean res = true;
		if (abc.length() != 0) {
			final String regex = "^(.+)@(.+)$";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(abc);

			if (matcher.matches() != true) {
				res = false;
			}
		}
		return res;
	}
}
