package hms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class license_validation extends JFrame {

	private JPanel contentPane;
	private static final String A_VALID_LINK = "http://spvaig.in";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					license_validation frame = new license_validation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public license_validation() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Rmsbook.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, dim.width, dim.height * 19 / 20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(license_validation.class.getResource("/hms/images/016-caution.png")));
		label_6.setBounds(630, 85, 212, 192);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setOpaque(true);
		label_7.setBackground(Color.WHITE);
		label_7.setBounds(564, 77, 316, 211);
		contentPane.add(label_7);
		
		JLabel label = new JLabel("    SPVAIG Pvt. Ltd.");
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label.setBackground(Color.DARK_GRAY);
		label.setBounds(0, 0, 1424, 66);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setBackground(Color.RED);
		label_1.setBounds(0, 65, 1424, 229);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("You license has expired");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
		label_2.setBounds(0, 305, 1424, 149);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Don't worry, we already have a new one waiting for you.");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
		label_3.setBounds(0, 425, 1424, 100);
		contentPane.add(label_3);
		
		JLabel lblCallUs = new JLabel("Call us! \"\" 6391488880\"\"");
		lblCallUs.setHorizontalAlignment(SwingConstants.CENTER);
		lblCallUs.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
		lblCallUs.setBounds(0, 669, 1424, 60);
		contentPane.add(lblCallUs);
		
		JLabel label_5 = new JLabel("Another year of service is just a call away.");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
		label_5.setBounds(0, 505, 1424, 49);
		contentPane.add(label_5);
		
		JLabel lblNewLabel = new JLabel("www.spvaig.in");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(645, 740, 152, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("or");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(700, 728, 46, 14);
		contentPane.add(lblNewLabel_1);
	}
}
