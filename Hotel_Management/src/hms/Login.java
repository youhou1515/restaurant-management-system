package hms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	JTextField textField;
	JPasswordField passwordField;
	JLabel lblUsername, lblPassword;
	JButton btnNewButton_1, btnCancel;
	private JLabel label_2;
	private JLabel label_3;
	Databaseconnection dbcon = new Databaseconnection();
	private JComboBox comboBox;
	public static final String DateFormat = "yyyy/MM/dd";
	boolean licensechecking = false;
	license_validation licenseobj = new license_validation();
	String d;
	private JLabel lblpressAlt;
	private JLabel lblNewLabel_1;
	userhomepage u;
	homepage a;

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(-21, -22, 1387, 751);
		contentPane.add(panel);
		panel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		comboBox.setSelectedIndex(0);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 13));
		comboBox.setForeground(Color.RED);
		try {
			ShowUnit();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBox.getSelectedItem() != "SELECT") {
					settrue();
					label_2.setVisible(true);
					textField.requestFocus();
				} else {
					// JOptionPane.showMessageDialog(null, "NOT SELECTED");
				}
			}

		});

		lblpressAlt = new JLabel("[Press Alt + C]");
		lblpressAlt.setVisible(false);

		lblpressAlt.setHorizontalAlignment(SwingConstants.CENTER);
		lblpressAlt.setBounds(710, 658, 148, 14);
		panel.add(lblpressAlt);

		lblNewLabel_1 = new JLabel("[Press Alt + L]");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(528, 658, 148, 14);
		panel.add(lblNewLabel_1);
		comboBox.setBounds(453, 149, 497, 52);
		panel.add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/hms/images/admin.png")));
		lblNewLabel_2.setBounds(381, 125, 54, 103);
		panel.add(lblNewLabel_2);

		lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setVisible(false);
		lblUsername.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		lblUsername.setBounds(381, 464, 119, 35);
		panel.add(lblUsername);

		lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setVisible(false);
		lblPassword.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		lblPassword.setBounds(381, 528, 119, 35);
		panel.add(lblPassword);

		textField = new JTextField("admin");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// passwordField
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordField.requestFocus();
				}
			}
		});
		textField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField.setToolTipText("Enter your username");
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setVisible(false);
		textField.setBounds(555, 456, 276, 43);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField("password");
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {

						String u1 = textField.getText();
						String p1 = passwordField.getText();
						String combodata = comboBox.getSelectedItem().toString();
						if (u1.length() != 0 && p1.length() != 0) {
							dbcon.connect();

							// String time = now();
							String query = "SELECT * FROM userpanel WHERE user=? and password=? and usertype=?";

							dbcon.pst = dbcon.conn.prepareStatement(query);
							dbcon.pst.setString(1, textField.getText());
							dbcon.pst.setString(2, passwordField.getText());
							dbcon.pst.setString(3, combodata);
							dbcon.rs = dbcon.pst.executeQuery();

							if (dbcon.rs.next()) {
								JOptionPane.showMessageDialog(null, "Welcome  " + dbcon.rs.getString("usertype"));

								if (combodata.equals("ADMIN")) {

									a = new homepage(u1);
									a.setVisible(true);
									a.currentuser(u1);
									dispose();

								}

								else {
									u = new userhomepage(u1);
									// u = new userhomepage(u1);
									u.setVisible(true);
									u.currentuser(u1);
									dispose();

								}

							}

							else {
								JOptionPane.showMessageDialog(null, "Incorrect username or password,please try again!");
							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "incorrect details" + ex);

					} finally {
						try {
							dbcon.conn.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setToolTipText("Enter your password");
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setVisible(false);
		passwordField.setBounds(555, 520, 277, 43);
		panel.add(passwordField);

		btnNewButton_1 = new JButton("LOGIN");
		btnNewButton_1.setMnemonic('L');
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnCancel.requestFocus();
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Login.class.getResource("/hms/images/login.jpg")));
		btnNewButton_1.setToolTipText("Click to Login");
		btnNewButton_1.setVisible(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// label.setVisible(true);
					String u1 = textField.getText();
					String p1 = passwordField.getText();
					if (u1.length() != 0 && p1.length() != 0) {
						dbcon.connect();
						// JOptionPane.showMessageDialog(null, "Welcome");
						String time = now();
						String query = "SELECT * FROM userpanel WHERE user=? and password=? and usertype=?";

						dbcon.pst = dbcon.conn.prepareStatement(query);
						dbcon.pst.setString(1, textField.getText());
						dbcon.pst.setString(2, passwordField.getText());
						dbcon.pst.setString(3, String.valueOf(comboBox.getSelectedItem()));
						dbcon.rs = dbcon.pst.executeQuery();
						// JOptionPane.showMessageDialog(null, "you");
						if (dbcon.rs.next()) {

							// JOptionPane.showMessageDialog(null, "Welcome" +
							// dbcon.rs.getString("usertype"));

							if (comboBox.getSelectedItem().equals("ADMIN")) {

								a = new homepage(u1);
								a.setVisible(true);
								setVisible(false);
								dispose();

								// admin a = new admin();

							}

							else {

								u = new userhomepage(u1);
								// u = new userhomepage(u1);
								u.setVisible(true);
								dispose();

							}
						}

						else {

							JOptionPane.showMessageDialog(null, "Incorrect username or password,please try again!");

						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "incorrect details");

				} finally {
					try {
						dbcon.conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				// homepage rmsobj = new homepage();
				// rmsobj.setVisible(true);
			}
		});

		btnNewButton_1.setBounds(528, 614, 141, 43);
		panel.add(btnNewButton_1);

		btnCancel = new JButton("CANCEL");
		btnCancel.setMnemonic('C');
		btnCancel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBox.requestFocus();
				}
			}
		});
		btnCancel.setIcon(new ImageIcon(Login.class.getResource("/hms/images/clearbtn.jpg")));
		btnCancel.setToolTipText("Clear all");
		btnCancel.setVisible(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		btnCancel.setBounds(710, 614, 141, 43);
		panel.add(btnCancel);

		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Login.class.getResource("/hms/images/oberoi_employee.png")));
		label_2.setBounds(518, 304, 350, 323);
		panel.add(label_2);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/hms/images/bog.jpg")));
		lblNewLabel.setBounds(24, 56, 1363, 695);
		panel.add(lblNewLabel);

		label_3 = new JLabel("");
		label_3.setBackground(Color.DARK_GRAY);
		label_3.setBounds(24, 33, 1363, 23);
		panel.add(label_3);

		JButton btnNewButton = new JButton("X");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(1323, 33, 63, 23);
		panel.add(btnNewButton);
		displaysize();
		setUndecorated(true);
	}

	public void displaysize() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, dim.width, dim.height * 19 / 20);
	}

	public void settrue() {

		lblPassword.setVisible(true);
		lblUsername.setVisible(true);
		textField.setVisible(true);
		passwordField.setVisible(true);
		btnNewButton_1.setVisible(true);
		btnCancel.setVisible(true);
		lblNewLabel_1.setVisible(true);
		lblpressAlt.setVisible(true);

	}

	public void ShowUnit() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select usertype from userpanel order by usertype asc");
			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				comboBox.addItem(data);
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

	public boolean licensecheck() throws SQLException {
		try {
			String time = now();

			dbcon.connect();
			String sql = "Select licensekey from license_product WHERE licensekey>'" + time + "'";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				licensechecking = true;

				// JOptionPane.showMessageDialog(null, dbcon.rs.next());

				// JOptionPane.showMessageDialog(null, licensechecking);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
		return licensechecking;
	}
}
