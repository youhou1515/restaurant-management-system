package hms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.DecorationHelper;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;

import setup.passwordentry;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Splash_rms extends JFrame {
	Databaseconnection dbcon = new Databaseconnection();
	private JLabel imglabel;
	private ImageIcon img;
	private static JProgressBar pbar;
	Thread t = null;

	static Splash_rms frame = new Splash_rms();
	public static final String DateFormat = "yyyy-MM-dd";

	Date today = new Date();

	public static void main(String[] args) {
		try {
//			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			
			
			 Properties props = new Properties();
			  props.put("logoString", "SPVAIG");
			  McWinLookAndFeel.setCurrentTheme(props);
			  UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			  DecorationHelper.decorateWindows(false);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Splash_rms() {
		super("Banaras Cafe");
		setSize(404, 310);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		imglabel = new JLabel(new ImageIcon(
				Splash_rms.class.getResource("/hms/images/background-for-restaurant-vector-2838631.jpg")));
		getContentPane().add(imglabel);
		getContentPane().setLayout(null);
		pbar = new JProgressBar();
		pbar.setBackground(new Color(255, 255, 255));
		pbar.setMinimum(0);
		pbar.setMaximum(100);
		pbar.setStringPainted(true);
		pbar.setForeground(new Color(0, 0, 0));
		imglabel.setBounds(0, 0, 404, 310);
		getContentPane().add(pbar);
		pbar.setPreferredSize(new Dimension(310, 30));
		pbar.setBounds(0, 290, 404, 20);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				passwordentry pe = new passwordentry();
				pe.setVisible(true);
				pe.toFront();
			}
		});
		lblNewLabel.setBounds(0, 0, 29, 20);
		getContentPane().add(lblNewLabel);

		Thread t = new Thread() {

			public void run() {
				int i = 0;
				while (i <= 100) {
					pbar.setValue(i);
					try {
						sleep(25);
					} catch (InterruptedException ex) {
						Logger.getLogger(Splash_rms.class.getName()).log(Level.SEVERE, null, ex);
					}
					i++;

				}

				try {

					String expirydate = dbcon.getvalidationdate();

//					String expirydate="2019/12/01";
//					String expirydate = "2019/12/21";
					String currentdate = datenow();

					Date curr_date = new SimpleDateFormat("yyyy-MM-dd").parse(currentdate);
					Date futuredate = new SimpleDateFormat("yyyy-MM-dd").parse(expirydate);

					if (curr_date.after(futuredate))

					{

						license_validation lv = new license_validation();
						lv.setVisible(true);

					} else {
						Login home = new Login();

						home.setVisible(true);
						frame.dispose();
					}

				} catch (Exception f) {
				}
			}
		};
		t.start();

	}

	public static String datenow() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat(DateFormat);

		return format.format(cal.getTime());

	}

	public static String now() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat(DateFormat);

		return format.format(cal.getTime());

	}
}