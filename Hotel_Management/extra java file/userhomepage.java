package hms;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class userhomepage extends JFrame {
	static userhomepage frame;
	private JPanel contentPane;
	menuentry menuobject = new menuentry();
	partynewentry newparty_object = new partynewentry();
	Tax_Master taxobj = new Tax_Master();
	Staff_Entry staffobj = new Staff_Entry();
	tablemaster tableobj = new tablemaster();
	Categorymaster categoryobj = new Categorymaster();
	Unitmaster unitobj = new Unitmaster();
	designation designationobj = new designation();
//	showbillrecord showbillobj = new showbillrecord();
	inventory inventoryobj = new inventory();
	showpurchaserecord showpurchaserecordobj = new showpurchaserecord();
	showpartyrecord showppartyobj = new showpartyrecord();
	showstaffdetails showstaffobj = new showstaffdetails();
	pnl_page pnlpageobj = new pnl_page();
	staff_salary_calculation salarycalobj = new staff_salary_calculation();
	showsalarydetails staffsalaryobj = new showsalarydetails();
	userpanel_signup userpanel_signupobj=new userpanel_signup();
	Databaseconnection dbcon=new Databaseconnection();
	public static final String DateFormat = "yyyy/MM/dd";
	birthdaylist bl=new birthdaylist();
	boolean check_boolean=false;
	
	

	final JButton btnNewButton = new JButton("Minimize");
	final JButton maximize = new JButton("Maximize");
	final JButton normal = new JButton("Normal");
	boolean verify;
	private JLabel lblNewLabel;
	JButton btnNewButton_1;

	
	 
	 public userhomepage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, dim.width, dim.height * 19 / 20);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			showtheitem();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e2);
		}
		
		btnNewButton_1 = new JButton("<html>Today's Customer<br> wishes</html>");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setIcon(null);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setEnabled(false);
		try {
		check_boolean=showtheitem();

		
		JLabel lblNewLabel_5 = new JLabel("BANARAS CAFE");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(139, 0, 0));
		lblNewLabel_5.setFont(new Font("Script MT Bold", Font.PLAIN, 21));
		lblNewLabel_5.setBounds(687, 335, 295, 37);
		contentPane.add(lblNewLabel_5);
		if(check_boolean==true)
		{
			btnNewButton_1.setEnabled(true);
			btnNewButton_1.setText("");
			btnNewButton_1.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/icon-push-notifications.png")));		
		}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			
		}
		btnNewButton_1.setBounds(1228, 74, 128, 63);
		
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bl.setVisible(true);
//					rmsbookobj.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}

			}
		});

		JButton btnBookNowalts = new JButton("");
		btnBookNowalts.setBounds(735, 404, 230, 48);
		contentPane.add(btnBookNowalts);
		btnBookNowalts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBookNowalts.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/book2.jpg")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBookNowalts.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/book1.jpg")));
			}
		});
		btnBookNowalts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					Rmsbook rmsbookobj=new Rmsbook();
//					rmsbookobj.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnBookNowalts.setBorder(null);
		btnBookNowalts.setHorizontalAlignment(SwingConstants.LEFT);
		btnBookNowalts.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/book1.jpg")));
		btnBookNowalts.setForeground(Color.BLACK);
		btnBookNowalts.setFont(new Font("Calibri", Font.BOLD, 15));
		btnBookNowalts.setToolTipText("Click to Book");
		btnBookNowalts.setMnemonic(KeyEvent.VK_S);
		btnBookNowalts.setBackground(Color.WHITE);

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/banaras cafe copy.png")));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_4.setBorder(new LineBorder(Color.WHITE));
		label_4.setBounds(687, 104, 295, 233);
		contentPane.add(label_4);

		JPanel panel_3 = new JPanel();
		panel_3.setName("WELCOME");
		panel_3.setBorder(
				new BevelBorder(BevelBorder.RAISED, null, null, new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(687, 524, 374, 37);
		contentPane.add(panel_3);

		hms.ClockLabel clockLabel = new hms.ClockLabel("date");
		panel_3.add(clockLabel);

		hms.ClockLabel clockLabel_1 = new hms.ClockLabel("time");
		clockLabel_1.setForeground(Color.RED);
		panel_3.add(clockLabel_1);

		hms.ClockLabel clockLabel_2 = new hms.ClockLabel("day");
		panel_3.add(clockLabel_2);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 218, 185));
		panel.setBounds(0, 40, 252, 795);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label_2 = new JLabel("");
		label_2.setForeground(Color.WHITE);
		label_2.setOpaque(true);
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(22, 54, 200, 4);
		panel.add(label_2);
		JLabel lblNewLabel_1 = new JLabel("DASHBOARD");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 252, 31);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(0, 42, 252, 4);
		panel.add(lblNewLabel_2);

		JLabel label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setBackground(Color.BLACK);
		label_1.setBounds(0, 64, 252, 4);
		panel.add(label_1);

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("HOME") {
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("MASTER PANEL");
				node_1.add(new DefaultMutableTreeNode("Item Master"));
				node_1.add(new DefaultMutableTreeNode("Party Master"));
				node_1.add(new DefaultMutableTreeNode("Tax Master"));

				node_1.add(new DefaultMutableTreeNode("Table Master"));
				node_1.add(new DefaultMutableTreeNode("Category Master"));
				node_1.add(new DefaultMutableTreeNode("Unit Master"));

				add(node_1);
				node_1 = new DefaultMutableTreeNode("RECORD PANEL");
				node_1.add(new DefaultMutableTreeNode("Bill Records"));
				node_1.add(new DefaultMutableTreeNode("Purchase Records"));
			
				node_1.add(new DefaultMutableTreeNode("Purchase Party Records"));
				add(node_1);
				
				node_1 = new DefaultMutableTreeNode("Purchase");
				node_1.add(new DefaultMutableTreeNode("New Purchase"));
				add(node_1);
				
				
			}
		}));

		tree.setBackground(new Color(255, 255, 255));
		tree.setFont(new Font("Calibri", Font.PLAIN, 16));
		tree.setRowHeight(30);
		tree.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tree.setBounds(0, 69, 252, 622);
		panel.add(tree);

		tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {

				jTree1ValueChanged(evt);
			}

			private void jTree1ValueChanged(TreeSelectionEvent evt) {
				String node = evt.getNewLeadSelectionPath().getLastPathComponent().toString();
				if (node.equals("Item Master")) {
					newparty_object.setVisible(false);
					taxobj.setVisible(false);
					menuobject.setVisible(true);
				} else if (node.equals("Party Master")) {
					newparty_object.setVisible(true);
				} else if (node.equals("Tax Master")) {
					taxobj.setVisible(true);
				} else if (node.equals("Staff Master")) {
					staffobj.setVisible(true);
				} else if (node.equals("Table Master")) {
					tableobj.setVisible(true);
				} else if (node.equals("Category Master")) {
					categoryobj.setVisible(true);
				} else if (node.equals("Unit Master")) {
					unitobj.setVisible(true);
				}
				 else if (node.equals("Customer Birthday list")) {
						bl.setVisible(true);
					}
//				Customer Birthday list
				// Designation Master
				else if (node.equals("Designation Master")) {
					designationobj.setVisible(true);
				} else if (node.equals("Bill Records")) {
//					showbillobj.setVisible(true);
				} else if (node.equals("Purchase Records")) {
					showpurchaserecordobj.setVisible(true);
				} else if (node.equals("Staff Records")) {
					showstaffobj.setVisible(true);
				} else if (node.equals("Purchase Party Records")) {
					showppartyobj.setVisible(true);
				} else if (node.equals("P/L Record")) {
					pnlpageobj.setVisible(true);
				} else if (node.equals("Staff Salary Calculation")) {
					salarycalobj.setVisible(true);
				} else if (node.equals("Staff Salary Records")) {
					staffsalaryobj.setVisible(true);
				}
				else if (node.equals("New Purchase")) {
					inventoryobj.setVisible(true);
				}
				else if (node.equals("userpanel creation")) {
					userpanel_signupobj.setVisible(true);
				}
//				Staff Salary Calculation  New Purchase
//				userpanel creation
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, -11, 1367, 702);
		panel.add(panel_1);
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);

		JLabel lblLogo = new JLabel("LOGO");
		lblLogo.setBorder(new LineBorder(Color.WHITE));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLogo.setBounds(746, 38, 276, 209);
		panel_1.add(lblLogo);

		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/design3.png")));
		label.setBounds(0, 22, 252, 769);
		panel.add(label);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 21, 1440, 21);
		contentPane.add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		mnFile.add(mntmClose);

		JMenu mnMaster = new JMenu("Master");
		menuBar.add(mnMaster);

		JMenuItem mntmNewMenuItem = new JMenuItem("Item Master");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			menuobject.setVisible(true);
			}
			
		});
		mnMaster.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Party Master");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			newparty_object.setVisible(true);
			}
			
		});
		mnMaster.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Tax Master");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			taxobj.setVisible(true);
			}
			
		});
		mnMaster.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Staff Master");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			staffobj.setVisible(true);
			}
			
		});
		mnMaster.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Table Master");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			tableobj.setVisible(true);
			}
			
		});
		mnMaster.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Category Master");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			categoryobj.setVisible(true);
			}
			
		});
		mnMaster.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Unit Master");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			unitobj.setVisible(true);
			}
			
		});
		mnMaster.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Designation Master");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			designationobj.setVisible(true);
			}
			
		});
		mnMaster.add(mntmNewMenuItem_7);

		JMenu mnRecord = new JMenu("Record");
		menuBar.add(mnRecord);

		JMenuItem mntmPurchaseRecord = new JMenuItem("Purchase Record");
		mntmPurchaseRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showpurchaserecordobj.setVisible(true);
			}
			
		});
		mnRecord.add(mntmPurchaseRecord);

		

		JMenuItem mntmBillRecord = new JMenuItem("Bill Record");
		mntmBillRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//			showbillobj.setVisible(true);
			}
			
		});
		mnRecord.add(mntmBillRecord);

		JMenuItem mntmPurchasePartyRecord = new JMenuItem("Purchase Party Record");
		mntmPurchasePartyRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showppartyobj.setVisible(true);
			}
			
		});
		mnRecord.add(mntmPurchasePartyRecord);

		

		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_3.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/close2a.jpg")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label_3.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/close2.jpg")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String message = "Do you want to exit";
				String title = "CONFIRM EXIT";
				try {
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						all_dispose();
						dispose();
						
						
				
			
					}
//				JOptionPane.showMessageDialog(null,"Tray icon: Mouse minimize");
				
			}catch(Exception f)
				{}
			}

			
		});
		label_3.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/close2.jpg")));

		label_3.setBounds(1341, 0, 25, 21);
		contentPane.add(label_3);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/close 1a.jpg")));
				SystemTray tray = SystemTray.getSystemTray();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/close1.jpg")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//				JOptionPane.showMessageDialog(null, "MINIMIZED");
				setExtendedState(JFrame.ICONIFIED);
				try {
					all_dispose();
//				setState(JFrame.ICONIFIED);
				
				}catch(Exception f)
				{
//					JOptionPane.showMessageDialog(null, f);
				}

			}
		});

		lblNewLabel_4.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/close1.jpg")));

		lblNewLabel_4.setBounds(1317, 0, 25, 21);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("    BANARAS CAFE");
		lblNewLabel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBounds(0, 0, 1366, 21);
		contentPane.add(lblNewLabel_3);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(userhomepage.class.getResource("/hms/images/backdesign1.jpg")));
		lblNewLabel.setBounds(252, 40, 1114, 795);
		contentPane.add(lblNewLabel);

		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				frame__windowStateChanged(arg0);
			}

			private void frame__windowStateChanged(WindowEvent arg0) {
				if ((arg0.getNewState() & frame.ICONIFIED) == frame.ICONIFIED) {
					all_dispose();
					
				}
				// maximized
//					   else if ((arg0.getNewState() & frame.MAXIMIZED_BOTH) == frame.MAXIMIZED_BOTH){
//						   JOptionPane.showMessageDialog(null,"Tray icon: Mouse maximize");
//					   }

			}
		});
	}
	public void all_dispose() {
		menuobject.dispose();
		newparty_object.dispose();
		taxobj.dispose();
		staffobj.dispose();
		tableobj.dispose();
		categoryobj.dispose();
		unitobj.dispose();
		designationobj.dispose();
//		showbillobj.dispose();
		inventoryobj.dispose();
		showpurchaserecordobj.dispose();

		showppartyobj.dispose();
		showstaffobj.dispose();
		pnlpageobj.dispose();
		salarycalobj.dispose();
		staffsalaryobj.dispose();
		userpanel_signupobj.dispose();
		bl.dispose();
		
	}
	
	public boolean showtheitem() throws SQLException
	{
		try {
			String time=now();
//		JOptionPane.showMessageDialog(null,time);
		dbcon.connect();
		String sql = "Select Customer_Name from tablecustomerdetail WHERE (Ocassion_Date='"+time+"' AND Ocassion='ANNIVERSARY') OR (Ocassion_Date='"+time+"' AND Ocassion='BIRTHDAY') OR (Ocassion_Date='"+time+"' AND Ocassion='BIRTHDAY' AND Ocassion='ANNIVERSARY')  ";
		dbcon.pst = dbcon.conn.prepareStatement(sql);
		dbcon.rs = dbcon.pst.executeQuery();
		while(dbcon.rs.next())
		{
			check_boolean=true;
			
//			JOptionPane.showMessageDialog(null, check_boolean);
		}
//		JOptionPane.showMessageDialog(null, check_boolean);
//		// model.setRowCount(0);
//		btnNewButton_1.setEnabled(true);
//		btnNewButton_1.setBackground(Color.GREEN);
//			

		

	} catch (SQLException e) {
//		JOptionPane.showMessageDialog(null, e);
	} finally {
		dbcon.conn.close();
	}
		return check_boolean;
	}
	public static String now() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat(DateFormat);

		return format.format(cal.getTime());

	}
}
