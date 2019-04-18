package hms;

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
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;

public class partynewentry extends JFrame {
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	JButton btnUpdateDetails;
	Databaseconnection dbcon = new Databaseconnection();
	JButton btnDeleteDetailsaltd;
	String t = "";
	JComboBox comboBox;
	boolean verify;
	private JButton SAVE;
	JLabel label_6;
	private JButton btnClearaltc;


	public partynewentry() {
//		getContentPane().setBackground(Color.LIGHT_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width*16/20, dim.height * 17 / 20);
		setUndecorated(true);
		setType(javax.swing.JFrame.Type.UTILITY);
		
		getContentPane().setLayout(null);
		

		JLabel label = new JLabel("Party Code");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(65, 107, 73, 14);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("City");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_1.setBounds(65, 165, 73, 14);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("Phone Office");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_2.setBounds(747, 165, 113, 14);
		getContentPane().add(label_2);

		JLabel label_4 = new JLabel("Aadhar No.");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_4.setBounds(433, 223, 113, 14);
		getContentPane().add(label_4);

		JLabel label_5 = new JLabel("PAN No.");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_5.setBounds(747, 219, 101, 14);
		getContentPane().add(label_5);

		JLabel label_7 = new JLabel("Mobile");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_7.setBounds(65, 228, 101, 14);
		getContentPane().add(label_7);

		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblState.setBounds(433, 165, 101, 14);
		getContentPane().add(lblState);

		JLabel label_9 = new JLabel("Party Name");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_9.setBounds(433, 107, 101, 14);
		getContentPane().add(label_9);

		JLabel label_10 = new JLabel("FSSAI");
		label_10.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_10.setBounds(433, 281, 46, 14);
		getContentPane().add(label_10);

		JLabel label_11 = new JLabel("GSTIN");
		label_11.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_11.setBounds(65, 281, 46, 14);
		getContentPane().add(label_11);

		JLabel label_12 = new JLabel("Address");
		label_12.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_12.setBounds(747, 107, 62, 14);
		getContentPane().add(label_12);

		comboBox = new JComboBox();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnUpdateDetails.requestFocus();
					
				}
				
			}
		});
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					SAVE.setEnabled(false);
					btnUpdateDetails.setEnabled(true);
					btnDeleteDetailsaltd.setEnabled(true);
					partyname_selection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		try {
			showparty_list();
			comboBox.setSelectedIndex(0);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		comboBox.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		comboBox.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox.setBounds(60, 550, 1016, 28);
		getContentPane().add(comboBox);

		JLabel label_13 = new JLabel("Select Party Name to See details");
		label_13.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_13.setBounds(60, 523, 268, 28);
		getContentPane().add(label_13);

		SAVE = new JButton("SAVE [Alt+S]");
		SAVE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnClearaltc.requestFocus();
					
				}
			}
		});
		SAVE.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SAVE.setForeground(Color.WHITE);
		
		SAVE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SAVE.setToolTipText("Click to Save");
		SAVE.setMnemonic('S');
		SAVE.setMnemonic(KeyEvent.VK_S);
		SAVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String partycode = "PARTY/".concat(label_6.getText());
					String partyname = textField_1.getText();
					String partycity = textField_3.getText();
					String partymobile = textField_6.getText();
					String aadhar = textField_10.getText();
					String panno = textField_11.getText();
					String gstin = textField_9.getText();

					String fssai = textField_12.getText();

					if (partyname.length() == 0) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter Party Name");
						textField_1.requestFocusInWindow();
					}
//				else if (partycity.length() == 0) {
//						Toolkit.getDefaultToolkit().beep();
//						JOptionPane.showMessageDialog(null, "Please Enter City Name");
//						textField_3.requestFocusInWindow();
//					}
//
//					else if (aadhar.length() == 0) {
//						Toolkit.getDefaultToolkit().beep();
//						JOptionPane.showMessageDialog(null, "Please Enter Aadhar No.");
//						textField_10.requestFocusInWindow();
//					} else if (panno.length() == 0) {
//						Toolkit.getDefaultToolkit().beep();
//						JOptionPane.showMessageDialog(null, "Please Enter Mobile Number");
//						textField_11.requestFocusInWindow();
//					} else if (gstin.length() == 0) {
//						Toolkit.getDefaultToolkit().beep();
//						JOptionPane.showMessageDialog(null, "Please Enter GSTIN Number");
//						textField_9.requestFocusInWindow();
//					} else if (fssai.length() == 0) {
//						Toolkit.getDefaultToolkit().beep();
//						JOptionPane.showMessageDialog(null, "Please Enter FSSAI Number");
//						textField_9.requestFocusInWindow();
//					}
					
					

					else {
						Double value=0.0;
						verify = dbcon.cashpartyentry(partycode.toUpperCase(),
								textField_1.getText().toUpperCase(), textField_2.getText().toUpperCase(),
								textField_3.getText().toUpperCase(), textField_4.getText().toUpperCase(),
								textField_5.getText().toUpperCase(), textField_6.getText().toUpperCase(),
								textField_9.getText().toUpperCase(), textField_10.getText().toUpperCase(),
								textField_11.getText().toUpperCase(), textField_12.getText(),value);
						// JOptionPane.showMessageDialog(null, verify);

						if (verify == true) {
							
							String billbreak = label_6.getText();
							
							// JOptionPane.showMessageDialog(null, billbreak);

							dbcon.insertuniqueid_master(billbreak);
							reset();
							
							comboBox.removeAllItems();
							comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

							showparty_list();
							comboBox.setSelectedIndex(0);
							SAVE.setEnabled(false);
							btnDeleteDetailsaltd.setEnabled(false);
							btnUpdateDetails.setEnabled(false);
							
							set_max_function();
						} 
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}

		});
		SAVE.setBackground(Color.GRAY);
		SAVE.setBounds(215, 436, 202, 37);
		getContentPane().add(SAVE);

		btnUpdateDetails = new JButton("Update details [Alt+U]");
		btnUpdateDetails.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnDeleteDetailsaltd.requestFocus();
					
				}
			}
		});
		btnUpdateDetails.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateDetails.setForeground(Color.WHITE);
		btnUpdateDetails.setBackground(Color.GRAY);
		btnUpdateDetails.setToolTipText("Click to Update");
		btnUpdateDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdateDetails.setMnemonic('U');
		btnUpdateDetails.setMnemonic(KeyEvent.VK_U);
		btnUpdateDetails.setEnabled(false);
		btnUpdateDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = " Are You sure? ";
				String title = "UPDATE PARTY";
				try {
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION ) {
						String partycode = "PARTY/".concat(label_6.getText());
					dbcon.updateparty_details(partycode.toUpperCase(), textField_1.getText().toUpperCase(),
							textField_2.getText().toUpperCase(), textField_3.getText().toUpperCase(),
							textField_4.getText().toUpperCase(), textField_5.getText().toUpperCase(),
							textField_6.getText().toUpperCase(), textField_9.getText().toUpperCase(),
							textField_10.getText().toUpperCase(), textField_11.getText().toUpperCase(),
							textField_12.getText().toUpperCase());

				
					reset();
					comboBox.removeAllItems();
					comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

					showparty_list();
					comboBox.setSelectedIndex(0);
					
					}
				} catch (SQLException e1) {
					
					
				}
			}
		});
		btnUpdateDetails.setBounds(197, 613, 235, 28);
		getContentPane().add(btnUpdateDetails);

		JLabel label_14 = new JLabel(" Party Master Entry");
		label_14.setOpaque(true);
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setForeground(Color.BLACK);
		label_14.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_14.setBorder(null);
		label_14.setBackground(SystemColor.text);
		label_14.setBounds(0, 26, 1152, 26);
		getContentPane().add(label_14);



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
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField_2.requestFocus();
					
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(544, 102, 181, 28);
		getContentPane().add(textField_1);

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
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField_3.requestFocus();
					
				}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(857, 102, 181, 28);
		getContentPane().add(textField_2);

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
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField_4.requestFocus();
					
				}
			}
		});
		textField_3.setColumns(10);
		textField_3.setBounds(183, 160, 181, 28);
		getContentPane().add(textField_3);

		textField_4 = new JTextField();
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_4.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_4.setText(null);

				}
			}
		});
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField_5.requestFocus();
					
				}
			}
		});
		textField_4.setColumns(10);
		textField_4.setBounds(544, 160, 181, 28);
		getContentPane().add(textField_4);

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
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField_6.requestFocus();
					
				}
			}
		});
		textField_5.setColumns(10);
		textField_5.setBounds(857, 160, 181, 28);
		getContentPane().add(textField_5);

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
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField_10.requestFocus();
					
				}
			}
		});
//		textField_6.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				String expression = "[0-9]*";
//				String inputStr = textField_6.getText();
//				Pattern pattern = Pattern.compile(expression);
//				Pattern.compile(expression);
//				Matcher matcher = pattern.matcher(inputStr);
//
//				if (matcher.matches()) {
//					textField_10.requestFocusInWindow();
//
//				} else {
//
//					JOptionPane.showMessageDialog(null, "Please! Enter Valid Mobile No.");
//				}
//			}
//
//		});
		textField_6.setColumns(10);
		textField_6.setBounds(183, 218, 181, 28);
		getContentPane().add(textField_6);

		textField_9 = new JTextField();
		textField_9.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_9.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_9.setText(null);

				}
			}
		});
		textField_9.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField_12.requestFocus();
					
				}
			}
		});
		textField_9.setColumns(10);
		textField_9.setBounds(183, 276, 181, 28);
		getContentPane().add(textField_9);

		textField_10 = new JTextField();
		textField_10.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_10.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_10.setText(null);

				}
			}
		});
		textField_10.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField_11.requestFocus();
					
				}
			}
		});
		textField_10.setColumns(10);
//		textField_10.requestDefaultFocus();
		textField_10.setBounds(544, 218, 181, 28);
		getContentPane().add(textField_10);

		textField_11 = new JTextField();
		textField_11.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_11.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_11.setText(null);

				}
			}
		});
		textField_11.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField_9.requestFocus();
					
				}
			}
		});
		textField_11.setColumns(10);
		textField_11.setBounds(857, 214, 181, 28);
		getContentPane().add(textField_11);

		textField_12 = new JTextField();
		textField_12.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField_12.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField_12.setText(null);

				}
			}
		});
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					SAVE.requestFocus();
					
				}
			}
		});
		textField_12.setColumns(10);
		textField_12.setBounds(544, 276, 181, 28);
		getContentPane().add(textField_12);

		JLabel lblClickToUpdate = new JLabel("Click to Update details of Selected Party");
		lblClickToUpdate.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblClickToUpdate.setBounds(183, 651, 268, 28);
		getContentPane().add(lblClickToUpdate);

		btnClearaltc = new JButton("CLEAR [Alt+C]");
		btnClearaltc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					comboBox.requestFocus();
					
				}
			}
		});
		btnClearaltc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClearaltc.setForeground(Color.WHITE);
		btnClearaltc.setToolTipText("Click to Clear");
		btnClearaltc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClearaltc.setMnemonic('C');
		btnClearaltc.setMnemonic(KeyEvent.VK_C);
		btnClearaltc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reset();
					btnDeleteDetailsaltd.setEnabled(false);
					SAVE.setEnabled(true);
					btnUpdateDetails.setEnabled(false);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnClearaltc.setBackground(Color.GRAY);
		btnClearaltc.setBounds(576, 436, 202, 37);
		getContentPane().add(btnClearaltc);

		btnDeleteDetailsaltd = new JButton("Delete details [Alt+D]");
		btnDeleteDetailsaltd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteDetailsaltd.setForeground(Color.WHITE);
		btnDeleteDetailsaltd.setBackground(Color.GRAY);
		btnDeleteDetailsaltd.setToolTipText("Click to Delete");
		btnDeleteDetailsaltd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteDetailsaltd.setMnemonic('D');
		btnDeleteDetailsaltd.setMnemonic(KeyEvent.VK_D);
		btnDeleteDetailsaltd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = " Are You sure? ";
				String title = "DELETE PARTY";
				try {
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						String inputclick = "PARTY/".concat(label_6.getText());
//						JOptionPane.showMessageDialog(null, inputclick);
						String partyname = (String) comboBox.getSelectedItem();
						if (inputclick.length() == 0) {

						} else {
//							JOptionPane.showMessageDialog(null, inputclick);
							dbcon.newpartydelete(inputclick);
						
							comboBox.removeAllItems();
							comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

							showparty_list();
							comboBox.setSelectedIndex(0);
							reset();
							btnDeleteDetailsaltd.setEnabled(false);
							btnUpdateDetails.setEnabled(false);
							SAVE.setEnabled(true);

						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
				}
			}
		});
		btnDeleteDetailsaltd.setEnabled(false);
		btnDeleteDetailsaltd.setBounds(554, 613, 235, 28);
		getContentPane().add(btnDeleteDetailsaltd);

		JLabel lblClickToDelete = new JLabel("Click to Delete details of Selected Party");
		lblClickToDelete.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblClickToDelete.setBounds(554, 651, 268, 28);
		getContentPane().add(lblClickToDelete);
		
		JLabel lblpa = new JLabel("PARTY/");
		lblpa.setForeground(Color.RED);
		lblpa.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblpa.setBounds(185, 99, 67, 22);
		getContentPane().add(lblpa);
		
		label_6 = new JLabel("");
		try {
			set_max_function();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_6.setBounds(246, 99, 54, 22);
		getContentPane().add(label_6);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(partynewentry.class.getResource("/hms/images/purchaselabel.jpg")));
		label_3.setBounds(849, 295, 215, 177);
		getContentPane().add(label_3);

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
			String conversion = (String) comboBox.getSelectedItem();

			String sql = "Select * from party_details where partyname='" + conversion + "'";
			// JOptionPane.showMessageDialog(null, "asdasd");

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {

				String a = dbcon.rs.getString("party_code");
				label_6.setText(a.substring(6));
//				JOptionPane.showMessageDialog(null, a);

				String b = dbcon.rs.getString("partyname");
				textField_1.setText(b);

				String c = dbcon.rs.getString("partyaddress");
				textField_2.setText(c);

				String e = dbcon.rs.getString("city");
				textField_3.setText(e);

				String f = dbcon.rs.getString("state");
				textField_4.setText(f);

				String g = dbcon.rs.getString("phoneoffice");
				textField_5.setText(g);

				String h = dbcon.rs.getString("mobile");
				textField_6.setText(h);

				String k = dbcon.rs.getString("gstin");
				textField_9.setText(k);

				String l = dbcon.rs.getString("aadharno");
				textField_10.setText(l);

				String m = dbcon.rs.getString("panno");
				textField_11.setText(m);

				String n = dbcon.rs.getString("fssaino");
				textField_12.setText(n);

			}
		}

		catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

	}

	public void reset() throws SQLException {

		// textField.setText(t);
		textField_1.setText(t);
		textField_2.setText(t);
		textField_3.setText(t);
		textField_4.setText(t);
		textField_5.setText(t);
		textField_6.setText(t);
		textField_9.setText(t);
		textField_10.setText(t);
		textField_11.setText(t);
		textField_12.setText(t);
		set_max_function();
	}

	public void set_max_function() throws SQLException {
		try {
			dbcon.connect();
			// Select max(`Enquiry No`) as max from enquiry
			String query1 = "Select max(idgenerator) as max from UID_partymaster";
			dbcon.pst = dbcon.conn.prepareStatement(query1);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				int num = dbcon.rs.getInt("max");
				int inc = num + 1;
				label_6.setText(String.valueOf(inc));
				// JOptionPane.showMessageDialog(null, inc);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, f);
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
}
