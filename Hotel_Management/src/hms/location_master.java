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

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;

public class location_master extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JComboBox comboBox;
	String unitxyz;
	Databaseconnection dbcon = new Databaseconnection();
	private JButton btnAddalta;
	private JButton btnDeletealtd;
	private JLabel label;

	public location_master() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width*16/20, dim.height * 17 / 20);
		setUndecorated(true);
		setType(javax.swing.JFrame.Type.UTILITY);
		
		
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"SELECT"}));
		

		

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {

					String abc = (String) comboBox.getSelectedItem();
					textField.setText(abc);
					unitxyz = textField.getText();
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

		JLabel lblAdddeleteUnits = new JLabel("Add/Delete location for issue stock");
		lblAdddeleteUnits.setOpaque(true);
		lblAdddeleteUnits.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdddeleteUnits.setForeground(SystemColor.desktop);
		lblAdddeleteUnits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdddeleteUnits.setBackground(Color.WHITE);
		lblAdddeleteUnits.setBounds(0, 11, 1092, 23);
		contentPanel.add(lblAdddeleteUnits);

		btnDeletealtd = new JButton("DELETE [Alt+D]");
		btnDeletealtd.setMnemonic('D');
		btnDeletealtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					comboBox.requestFocus();
				}
			}
		});
		btnDeletealtd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletealtd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String data = textField.getText().toUpperCase();
					if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
						JOptionPane.showMessageDialog(null, "Please Select Category from List to Delete");
					} else

					{
						String message = "Do you want to Delete ? ";
						String title = "CONFIRM";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
						
							dbcon.locationdelete(data);
							comboBox.removeAllItems();

							comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
							Showlocation();
							comboBox.setSelectedIndex(0);
							textField.setText(null);
							
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Something went wrong, Please contact Expert!");
				}
			}
		});
		btnDeletealtd.setForeground(Color.WHITE);
		btnDeletealtd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeletealtd.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnDeletealtd.setBackground(Color.GRAY);
		btnDeletealtd.setBounds(619, 337, 172, 35);
		contentPanel.add(btnDeletealtd);

		btnAddalta = new JButton("ADD [Alt+A]");
		btnAddalta.setMnemonic('A');
		btnAddalta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnDeletealtd.requestFocus();
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

							dbcon.insert_locationentry(data);
							comboBox.removeAllItems();
							comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

							Showlocation();
							comboBox.setSelectedIndex(0);
							textField.setText(null);
							

						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);

				}
			}
		});
		btnAddalta.setForeground(Color.WHITE);
		btnAddalta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddalta.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnAddalta.setBackground(Color.GRAY);
		btnAddalta.setBounds(349, 337, 172, 35);
		contentPanel.add(btnAddalta);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(location_master.class.getResource("/hms/images/locationlabel.png")));
		label.setBounds(495, 450, 215, 177);
		contentPanel.add(label);
		try {
			Showlocation();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void ShowUnit() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select unitname from unitlist order by unitname asc");
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

	public void ShowUnitTable() throws SQLException {
		try {
			
			dbcon.connect();
			String sql = "Select unitname from unitlist order by unitname asc";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			
			}
		 catch (Exception e1) {
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
			}

		} catch (Exception e) {
			// TODO: handle exception
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
