package hms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class locationmaster extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField textField;
	JComboBox comboBox;
	String categoryxyz, abc;
	Databaseconnection dbcon = new Databaseconnection();

	public locationmaster() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(locationmaster.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - getWidth()) / 4;
		int y = (dim.height - getHeight()) / 4;
		setBounds(x, y, dim.width * 8 / 20, dim.height * 8 / 20);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaptionBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Add/Delete Location");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setOpaque(true);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setBounds(0, 11, 530, 23);
			contentPanel.add(lblNewLabel);
		}

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(41, 56, 398, 35);
		contentPanel.add(panel);
		panel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		try {

			Showlocation();
			comboBox.setSelectedIndex(0);

		} catch (SQLException e2) {

			JOptionPane.showMessageDialog(null, e2);

		}

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					abc = (String) comboBox.getSelectedItem();
					textField.setText(abc);
					categoryxyz = textField.getText().toUpperCase();

				} catch (Exception fe) {
				}

			}
		});

		comboBox.setBounds(6, 8, 385, 21);
		panel.add(comboBox);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(41, 126, 46, 14);
		contentPanel.add(lblName);
		textField = new JTextField();
		textField.setBounds(124, 118, 315, 35);
		contentPanel.add(textField);
		textField.setColumns(10);

		JButton btnDelete = new JButton("DELETE [Alt+D]");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setToolTipText("Click to Delete ");
		btnDelete.setMnemonic(KeyEvent.VK_D);
		btnDelete.addActionListener(new ActionListener() {
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
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setForeground(SystemColor.textHighlight);
		btnDelete.setBackground(SystemColor.control);
		btnDelete.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnDelete.setBounds(282, 192, 141, 35);
		contentPanel.add(btnDelete);

		JButton btnAdd = new JButton("ADD [Alt+D]");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setToolTipText("Click to Add ");
		btnAdd.setMnemonic(KeyEvent.VK_A);
		btnAdd.addActionListener(new ActionListener() {
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
		btnAdd.setForeground(SystemColor.textHighlight);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnAdd.setBackground(SystemColor.menu);
		btnAdd.setBounds(71, 192, 141, 35);
		contentPanel.add(btnAdd);
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
}
