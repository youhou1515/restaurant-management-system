package hms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;

public class userpanel_signup extends JFrame {

	private final JPanel contentPanel = new JPanel();
	public JTextField textField;
	String categoryxyz;
	Databaseconnection dbcon = new Databaseconnection();
	private JTable table;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JButton button_1;
	private JButton button;
	private JLabel label;

	public userpanel_signup() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setType(javax.swing.JFrame.Type.UTILITY);
		setUndecorated(true);

		// setUndecorated(true);

		getContentPane().setLayout(new BorderLayout());
		// contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		try {

			ShowCategoryTable();
		} catch (SQLException e2) {

			JOptionPane.showMessageDialog(null, "AAJ KI RAT" + e2);

		}

		JLabel lblName = new JLabel("Username");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(258, 159, 89, 14);
		contentPanel.add(lblName);
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(textField.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					textField.setText(null);

				}
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordField.requestFocus();
				}
			}
		});
		textField.setBounds(379, 151, 412, 34);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblAdddeleteItemCategory = new JLabel("User Panel Regisration");
		lblAdddeleteItemCategory.setOpaque(true);
		lblAdddeleteItemCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdddeleteItemCategory.setForeground(Color.BLACK);
		lblAdddeleteItemCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdddeleteItemCategory.setBackground(Color.WHITE);
		lblAdddeleteItemCategory.setBounds(0, 11, 1092, 23);
		contentPanel.add(lblAdddeleteItemCategory);

		button = new JButton("DELETE [Alt+D]");
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField.requestFocus();
				}
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setToolTipText("Click to Delete");
		button.setMnemonic(KeyEvent.VK_D);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String categorys = textField_2.getText();
					if (categorys.length() == 0) {
						JOptionPane.showMessageDialog(null, "Please Select userrole from List to Delete");
					} else

					{
						String message = "Do you want to Delete ? ";
						String title = "CONFIRM";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							dbcon.userpanel_delete(categorys);

							clearall();

							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.setRowCount(0);
							ShowCategoryTable();

						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(593, 409, 172, 35);
		contentPanel.add(button);

		button_1 = new JButton("ADD [Alt+A]");
		button_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					button.requestFocus();
				}
			}
		});
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setToolTipText("Click to Add");
		button_1.setMnemonic(KeyEvent.VK_A);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String data = textField.getText().toUpperCase();
					if (data.length() == 0) {

					} else

					{
						String message = "Do you want to add? ";
						String title = "CONFIRM";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							String username = textField.getText();
							String password = passwordField.getText();
							String role = textField_2.getText().toUpperCase();

							dbcon.insert_userpanel(username, password, role);

							clearall();

							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.setRowCount(0);
							ShowCategoryTable();

						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_1.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		button_1.setBackground(Color.GRAY);
		button_1.setBounds(353, 409, 172, 35);
		contentPanel.add(button_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(855, 151, 227, 257);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					tableselect();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		scrollPane.setViewportView(table);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(258, 210, 89, 14);
		contentPanel.add(lblPassword);

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
					button_1.requestFocus();
				}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(379, 261, 412, 34);
		contentPanel.add(textField_2);

		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRole.setBounds(258, 269, 89, 14);
		contentPanel.add(lblRole);

		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean result = Regex_alphanumeric(passwordField.getText());
				if (result != true) {
					JOptionPane.showMessageDialog(null, "Please Enter Alphanumeric only,try again!!");
					passwordField.setText(null);

				}
				// passwordField.hasFocus();
			}
		});
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_2.requestFocus();
				}
			}
		});
		passwordField.setBounds(379, 209, 412, 34);
		contentPanel.add(passwordField);

		label = new JLabel("");
		label.setIcon(new ImageIcon(userpanel_signup.class.getResource("/hms/images/images.jpg")));
		label.setBounds(445, 505, 232, 177);
		contentPanel.add(label);
		try {
			ShowCategoryTable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		class IndexedFocusTraversalPolicy extends FocusTraversalPolicy {

			private ArrayList<Component> components = new ArrayList<Component>();

			public void addIndexedComponent(Component component) {
				components.add(component);
			}



			@Override
			public Component getComponentAfter(Container aContainer, Component aComponent) {
				int atIndex = components.indexOf(aComponent);
				int nextIndex = (atIndex + 1) % components.size();
				return components.get(nextIndex);
			}

			@Override
			public Component getComponentBefore(Container aContainer, Component aComponent) {
				int atIndex = components.indexOf(aComponent);
				int nextIndex = (atIndex + components.size() - 1) % components.size();
				return components.get(nextIndex);
			}

			@Override
			public Component getDefaultComponent(Container aContainer) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Component getFirstComponent(Container aContainer) {
				return components.get(0);
			}

			@Override
			public Component getLastComponent(Container aContainer) {
				// TODO Auto-generated method stub
				return null;
			}
		}
		
		IndexedFocusTraversalPolicy policy = new IndexedFocusTraversalPolicy();
		policy.addIndexedComponent(textField);
		policy.addIndexedComponent(passwordField);
		policy.addIndexedComponent(textField_2);
//		policy.addIndexedComponent(jTextField4);
//		policy.addIndexedComponent(jTextField5);
//		policy.addIndexedComponent(jTextField6);
		setFocusTraversalPolicy(policy);

	}

	public void ShowCategoryTable() throws SQLException {
		try {

			dbcon.connect();
			String sql = "Select usertype from userpanel order by usertype asc";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();

			// model.setRowCount(0);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));

		} catch (Exception e1) {
		} finally {
			dbcon.conn.close();
		}
	}

	public void tableselect() throws SQLException {
		try {
			dbcon.connect();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int row = table.getSelectedRow();
			String TableClick = table.getValueAt(row, 0).toString();

			String sql = "Select user,password,usertype from userpanel where usertype='" + TableClick + "'";

			// JOptionPane.showMessageDialog(null, "asdasd");

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {

				String add0 = dbcon.rs.getString("user");
				textField.setText(add0);
				String add1 = dbcon.rs.getString("password");
				passwordField.setText(add1);
				// textField_14.setText(add1);

				String add2 = dbcon.rs.getString("usertype");
				textField_2.setText(add2);

			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

	}

	public void clearall() {
		textField.setText(null);
		passwordField.setText(null);
		textField_2.setText(null);

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
