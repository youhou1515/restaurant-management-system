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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class productmaster extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JComboBox comboBox;
	String tablexyz;
	LocalDate today = LocalDate.now();
	LocalTime time = LocalTime.now();
	Databaseconnection dbcon = new Databaseconnection();
	private JButton btnAddalta;
	private JButton btnDeletealtc;

	public productmaster() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setUndecorated(true);
		setType(javax.swing.JFrame.Type.UTILITY);

		// setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(264, 123, 544, 35);
		contentPanel.add(panel);
		panel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField.requestFocus();
				}
			}
		});

		try {
			ShowTable();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				try {
					dbcon.connect();
					String abc = (String) comboBox.getSelectedItem();
					textField.setText(abc);
					tablexyz = textField.getText();
					dbcon.conn.close();
				} catch (Exception fe) {
				}
			}
		});

		comboBox.setBounds(10, 8, 524, 20);
		panel.add(comboBox);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(293, 245, 46, 14);
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
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnAddalta.requestFocus();
				}
			}
		});
		textField.setBounds(379, 237, 412, 34);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblAdddeleteTable = new JLabel("Add/Delete Product for Purchase");
		lblAdddeleteTable.setOpaque(true);
		lblAdddeleteTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdddeleteTable.setForeground(Color.BLACK);
		lblAdddeleteTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdddeleteTable.setBackground(Color.WHITE);
		lblAdddeleteTable.setBounds(0, 11, 1092, 23);
		contentPanel.add(lblAdddeleteTable);

		btnDeletealtc = new JButton("DELETE  [Alt+D]");
		btnDeletealtc.setMnemonic('D');
		btnDeletealtc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					comboBox.requestFocus();
				}
			}
		});
		btnDeletealtc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletealtc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
						JOptionPane.showMessageDialog(null, "Please Select Category from List to Delete");
					} else

					{
						String message = "Do you want to Delete ? ";
						String title = "CONFIRM";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							tablexyz = textField.getText().toUpperCase();
							dbcon.productdelete(tablexyz);
							dbcon.product_stockdelete(tablexyz);
							comboBox.removeAllItems();
							comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

							ShowTable();

							comboBox.setSelectedIndex(0);

							textField.setText(null);
//							DefaultTableModel model = (DefaultTableModel) table.getModel();
//							model.setRowCount(0);
//							ShowTab_Table();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDeletealtc.setForeground(Color.WHITE);
		btnDeletealtc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeletealtc.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnDeletealtc.setBackground(Color.GRAY);
		btnDeletealtc.setBounds(619, 337, 172, 35);
		contentPanel.add(btnDeletealtc);

		btnAddalta = new JButton("ADD [Alt+A]");
		btnAddalta.setMnemonic('A');
		btnAddalta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnDeletealtc.requestFocus();
				}
			}
		});
		btnAddalta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddalta.addActionListener(new ActionListener() {
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

							dbcon.insertproduct(data);
							
							dbcon.insert_productstock(data);
							
							comboBox.removeAllItems();
							comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
							ShowTable();
							comboBox.setSelectedIndex(0);

							textField.setText(null);

//							DefaultTableModel model = (DefaultTableModel) table.getModel();
//							model.setRowCount(0);
//							ShowTab_Table();
						}
					}
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, "Already exists!");
				} finally {
					try {
						dbcon.conn.close();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		btnAddalta.setForeground(Color.WHITE);
		btnAddalta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddalta.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnAddalta.setBackground(Color.GRAY);
		btnAddalta.setBounds(349, 337, 172, 35);
		contentPanel.add(btnAddalta);
		try {
			ShowTab_Table();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void ShowTable() throws SQLException {
		try {
			dbcon.connect();
			Statement st = dbcon.conn.createStatement();
			ResultSet rs = st.executeQuery("Select productname from productlist order by productname asc");
			while (rs.next()) {
				String data = rs.getString(1);
				comboBox.addItem(data);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

	}

	public void ShowTab_Table() throws SQLException {
		try {

			dbcon.connect();
			String sql = "Select productname from productlist order by productname asc";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();

		} catch (Exception e1) {
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
