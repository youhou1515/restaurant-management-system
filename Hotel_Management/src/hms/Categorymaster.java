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
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;

public class Categorymaster extends JFrame {

	private final JPanel contentPanel = new JPanel();
	public JTextField textField;
	JComboBox comboBox;
	String categoryxyz;
	Databaseconnection dbcon = new Databaseconnection();
	private JButton button_1;
	private JButton button;
	private JLabel label;

	public Categorymaster() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setType(javax.swing.JFrame.Type.UTILITY);
		setUndecorated(true);
		
//		setUndecorated(true);

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
//				textField
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					textField.requestFocus();
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		try {

			Showcategory();
			comboBox.setSelectedIndex(0);
		} catch (SQLException e2) {

			JOptionPane.showMessageDialog(null, "AAJ KI RAT" + e2);

		}

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String abc = (String) comboBox.getSelectedItem();
					textField.setText(abc);
					categoryxyz = textField.getText();

				} catch (Exception fe) {
				}

			}
		});

		comboBox.setBounds(10, 8, 524, 21);
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
					button_1.requestFocus();
				}
			}
		});
		textField.setBounds(379, 237, 412, 34);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblAdddeleteItemCategory = new JLabel("Add/Delete Item Category");
		lblAdddeleteItemCategory.setOpaque(true);
		lblAdddeleteItemCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdddeleteItemCategory.setForeground(Color.BLACK);
		lblAdddeleteItemCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdddeleteItemCategory.setBackground(Color.WHITE);
		lblAdddeleteItemCategory.setBounds(0, 11, 1152, 23);
		contentPanel.add(lblAdddeleteItemCategory);

		button = new JButton("DELETE [Alt+D]");
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					comboBox.requestFocus();
					
				}
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setToolTipText("Click to Delete");
		button.setMnemonic('D');
		button.setMnemonic(KeyEvent.VK_D);
		button.addActionListener(new ActionListener() {
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
							dbcon.categorydelete(categoryxyz);
							comboBox.removeAllItems();
							textField.setText(null);

							comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

							Showcategory();
							comboBox.setSelectedIndex(0);
							
//							DefaultTableModel model = (DefaultTableModel) table.getModel();
//							model.setRowCount(0);
//							ShowCategoryTable();

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
		button.setBounds(619, 337, 172, 35);
		contentPanel.add(button);

		button_1 = new JButton("ADD [Alt+A]");
		button_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					button.requestFocus();
				}
			}
		});
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setToolTipText("Click to Add");
		button_1.setMnemonic('A');
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

							dbcon.insert_category(data);

							comboBox.removeAllItems();
							comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

							Showcategory();
							comboBox.setSelectedIndex(0);
							textField.setText(null);
//							DefaultTableModel model = (DefaultTableModel) table.getModel();
//							model.setRowCount(0);
//							ShowCategoryTable();

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
		button_1.setBounds(349, 337, 172, 35);
		contentPanel.add(button_1);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Categorymaster.class.getResource("/hms/images/ob_6c8220_istock-000011869612small.jpg")));
		label.setBounds(459, 418, 215, 177);
		contentPanel.add(label);
		try {
			ShowCategoryTable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
				   comboBox.requestFocus();
			     }
			   } ); 
	}

	public void Showcategory() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select categoryname from categorylist order by categoryname asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("categoryname");
				comboBox.addItem(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.conn.close();
		}
	}
	
	public void ShowCategoryTable() throws SQLException {
		try {
			
			dbcon.connect();
			String sql = "Select categoryname from categorylist order by categoryname asc";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			
			}
		 catch (Exception e1) {
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
