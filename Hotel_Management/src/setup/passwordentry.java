package setup;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

import hms.Databaseconnection;
import hms.Tax_Master;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class passwordentry extends JDialog {
	private final JLabel lblNewLabel_1 = new JLabel("New label");
	private JPasswordField passwordField;
	Databaseconnection dbcon = new Databaseconnection();

	public passwordentry() {
		getContentPane().setBackground(Color.BLACK);
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension(500, 300);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));

		setBounds(ss.width / 2 - frameSize.width / 2, ss.height / 2 - frameSize.height / 2, frameSize.width,
				frameSize.height);
		getContentPane().setLayout(null);

		passwordField = new JPasswordField();

		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
					String password = new String(passwordField.getPassword());
					
					dbcon.connect();
					String query = "SELECT sno from profilepassword where profilepassword=?";
					dbcon.pst = dbcon.conn.prepareStatement(query);
					dbcon.pst.setString(1, password);
					dbcon.rs = dbcon.pst.executeQuery();
					
					if (dbcon.rs.next()) {

						Validation vd = new Validation();
						vd.setVisible(true);
						

					} else {

						JOptionPane.showMessageDialog(null, "Incorrect username or password,please try again!");

					}
					
					dbcon.conn.close();
					dispose();
					
					
					}catch(Exception f)
					{
						
						JOptionPane.showMessageDialog(null, f);
					}finally {
						try {
							dbcon.conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
		});

		passwordField.setBorder(new LineBorder(new Color(50, 205, 50)));
		passwordField.setBounds(161, 123, 217, 20);
		getContentPane().add(passwordField);

		JLabel lblNewLabel = new JLabel("Enter Password:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(71, 122, 183, 23);
		getContentPane().add(lblNewLabel);
		lblNewLabel_1.setIcon(new ImageIcon(
				passwordentry.class.getResource("/hms/images/System-Failure-The-Matrix-HD-Wallpaper.jpg")));
		lblNewLabel_1.setBounds(10, 11, 464, 239);
		getContentPane().add(lblNewLabel_1);
		setVisible(true);
	}
}
