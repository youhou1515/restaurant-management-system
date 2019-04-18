package hms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import setup.companysetup;

public class homepage extends JFrame {
	private static final String title = null;
	static homepage frame;
	private JPanel contentPane;

	Databaseconnection dbcon = new Databaseconnection();
	public static final String DateFormat = "yyyy/MM/dd";
	birthdaylist bl = new birthdaylist();
//	advancebookinglist advancelist=new advancebookinglist();
	boolean check_boolean = false;
	boolean check_boolean4ab = false;

	final JButton btnNewButton = new JButton("Minimize");
	final JButton maximize = new JButton("Maximize");
	final JButton normal = new JButton("Normal");
	boolean verify;
	private JLabel lblNewLabel;
	JButton btnNewButton_1;
	private JLabel lblNewLabel_3;
	String cuser = "";
	JLabel label_4;
	ImageIcon format = null;
	JLabel lblNewLabel_5;

	menuentry menuobject = new menuentry();
	partynewentry newparty_object = new partynewentry();
	Tax_Master taxobj = new Tax_Master();
	Staff_Entry staffobj = new Staff_Entry();
	tablemaster tableobj = new tablemaster();
	Categorymaster categoryobj = new Categorymaster();

	Unitmaster unitobj = new Unitmaster();
	Vendor_paymentrecord show_vendorpaymentreocrd = new Vendor_paymentrecord();
	designation designationobj = new designation();
	productmaster productobj = new productmaster();
	i_ssue stockobj = new i_ssue();
	stock_management smobj = new stock_management();
	showbillrecor_admin showbillobj;
	inventory inventoryobj = new inventory();
	Vendor_payment vendorpaymentobj = new Vendor_payment();
	showstockissuerecord_admin stockissuerecordobj = new showstockissuerecord_admin();
	showpurchaserecord_admin showpurchaserecordobj = new showpurchaserecord_admin();
	showpartyrecord_admin showppartyobj = new showpartyrecord_admin();
	showstaffdetails showstaffobj = new showstaffdetails();
	pnl_page pnlpageobj = new pnl_page();
	staff_salary_calculation salarycalobj = new staff_salary_calculation();
	showsalarydetails staffsalaryobj = new showsalarydetails();
	userpanel_signup userpanel_signupobj = new userpanel_signup();
	location_master locationobj = new location_master();
	Customer_Records crecords = new Customer_Records();
	BirthdayAdvanceBooking birthdayadvanceobj = new BirthdayAdvanceBooking();
	Booking_Category Booking_Categoryobj = new Booking_Category();
	show_advancebookingdetails bookdetails = new show_advancebookingdetails();
	AdvanceBooking_Payment advancebooking_paymentobj = new AdvanceBooking_Payment();
	purchase_wages puchase_wagesobj;
	DailyStaffExpense_Record DailyStaffExpenseobj = new DailyStaffExpense_Record();
	DailyPurchaseExp_Record DailyPurchaseExpobj = new DailyPurchaseExp_Record();
	companysetup compnayobj = new companysetup();
	showkotrecord_admin kotobj = new showkotrecord_admin();

	public homepage(String u1) {
		puchase_wagesobj = new purchase_wages(u1);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));

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
		try {
			showtheitem4ab();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e2);
		}
		showbillobj = new showbillrecor_admin(u1);
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/bell.png")));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setEnabled(false);
		try {
			check_boolean = showtheitem();
			check_boolean4ab = showtheitem4ab();
			

			JButton button = new JButton("");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
//					advancelist.setVisible(true);
				}
			});
			button.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/advancebooking.png")));
			button.setForeground(Color.BLACK);
			button.setEnabled(false);
			button.setBackground(Color.WHITE);
			button.setBounds(1094, 204, 131, 63);
			contentPane.add(button);

			JLabel lbltodaysAdvanceBooking = new JLabel("<html>Today's Advance Booking");
			lbltodaysAdvanceBooking.setHorizontalAlignment(SwingConstants.CENTER);
			lbltodaysAdvanceBooking.setForeground(Color.BLACK);
			lbltodaysAdvanceBooking.setBounds(1075, 270, 168, 14);
			contentPane.add(lbltodaysAdvanceBooking);

			JLabel lblNewLabel_7 = new JLabel("<html>Today's Customer Wishes");
			lblNewLabel_7.setForeground(Color.BLACK);
			lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_7.setBounds(1067, 152, 176, 14);
			contentPane.add(lblNewLabel_7);

			JLabel lblNewLabel_6 = new JLabel("[Press Alt+B]");
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_6.setFont(new Font("Corbel", lblNewLabel_6.getFont().getStyle(), 20));
			lblNewLabel_6.setForeground(SystemColor.BLACK);
			lblNewLabel_6.setBounds(746, 454, 176, 26);
			contentPane.add(lblNewLabel_6);

			lblNewLabel_5 = new JLabel("");
			lblNewLabel_5.setBackground(Color.BLACK);
			lblNewLabel_5.setOpaque(true);
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_5.setForeground(new Color(255, 255, 255));
			lblNewLabel_5.setFont(new Font("Script MT Bold", Font.PLAIN, 25));
			lblNewLabel_5.setBounds(687, 335, 295, 37);
			contentPane.add(lblNewLabel_5);
			if (check_boolean == true) {
				btnNewButton_1.setEnabled(true);
				btnNewButton_1.setText("");
				btnNewButton_1
						.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/icon-push-notifications.png")));
			}

			if (check_boolean4ab == true) {
				button.setEnabled(true);

			}

		} catch (SQLException e2) {
			// TODO Auto-generated catch block

		}
		btnNewButton_1.setBounds(1094, 78, 131, 63);

		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bl.setVisible(true);
					// rmsbookobj.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}

			}
		});

		JButton btnBookNowalts = new JButton("");
		btnBookNowalts.setBounds(722, 404, 230, 48);
		contentPane.add(btnBookNowalts);
		btnBookNowalts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBookNowalts.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/book2.jpg")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBookNowalts.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/book1.jpg")));
			}
		});
		btnBookNowalts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Rmsbook rmsbookobj = new Rmsbook(lblNewLabel_3.getText());
					rmsbookobj.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Something went wrong,please Call Expert:" + e1);
				}

			}
		});
		btnBookNowalts.setBorder(null);
		btnBookNowalts.setHorizontalAlignment(SwingConstants.LEFT);
		btnBookNowalts.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/book1.jpg")));
		btnBookNowalts.setForeground(Color.BLACK);
		btnBookNowalts.setFont(new Font("Calibri", Font.BOLD, 15));
		btnBookNowalts.setToolTipText("Click to Book");
		btnBookNowalts.setMnemonic('B');
		btnBookNowalts.setMnemonic(KeyEvent.VK_B);
		btnBookNowalts.setBackground(Color.WHITE);

		label_4 = new JLabel("");

		// label_4.setIcon(new
		// ImageIcon(homepage.class.getResource("/hms/images/London_logo_company.png")));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_4.setBorder(new LineBorder(Color.WHITE));
		label_4.setBounds(687, 68, 295, 256);
		contentPane.add(label_4);
		try {
			showcompanydetails();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JPanel panel_3 = new JPanel();
		panel_3.setName("WELCOME");
		panel_3.setBorder(
				new BevelBorder(BevelBorder.RAISED, null, null, new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(634, 522, 374, 37);
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
		panel.setBounds(0, 40, 252, 689);
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
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 252, 31);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.BLACK);
		lblNewLabel_2.setBounds(0, 42, 252, 4);
		panel.add(lblNewLabel_2);

		JLabel label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setBackground(Color.BLACK);
		label_1.setBounds(0, 64, 252, 4);
		panel.add(label_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 69, 252, 620);
		panel.add(scrollPane);

		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("HOME") {
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("SETTING");
				node_1.add(new DefaultMutableTreeNode("Company Profile"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("MASTER PANEL");
				node_1.add(new DefaultMutableTreeNode("Item Master"));

				node_1.add(new DefaultMutableTreeNode("Tax Master"));

				node_1.add(new DefaultMutableTreeNode("Table Master"));
				node_1.add(new DefaultMutableTreeNode("Category Master"));
				node_1.add(new DefaultMutableTreeNode("Unit Master"));
				node_1.add(new DefaultMutableTreeNode("Location Master"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("SALE RECORD");
				node_1.add(new DefaultMutableTreeNode("Bill Records"));
				node_1.add(new DefaultMutableTreeNode("KOT Records"));

				add(node_1);
				node_1 = new DefaultMutableTreeNode("Profit/Loss");

				node_1.add(new DefaultMutableTreeNode("P/L Record"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Staff Management");
				node_1.add(new DefaultMutableTreeNode("Staff Master"));
				node_1.add(new DefaultMutableTreeNode("Designation Master"));
				node_1.add(new DefaultMutableTreeNode("Staff Records"));
				node_1.add(new DefaultMutableTreeNode("Staff Salary Calculation"));
				node_1.add(new DefaultMutableTreeNode("Staff Salary Records"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Purchase Management");
				node_1.add(new DefaultMutableTreeNode("Party Master"));
				node_1.add(new DefaultMutableTreeNode("Product Master"));
				node_1.add(new DefaultMutableTreeNode("New Purchase"));
				node_1.add(new DefaultMutableTreeNode("Vendor/Party Record"));

				node_1.add(new DefaultMutableTreeNode("Purchase Records"));
				node_1.add(new DefaultMutableTreeNode("Vendor Payment"));
				node_1.add(new DefaultMutableTreeNode("Vendor Payment Record"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("USER RIGHTS MANAGEMENT");
				node_1.add(new DefaultMutableTreeNode("userpanel creation"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("STOCK MANAGEMENT");
				node_1.add(new DefaultMutableTreeNode("Issue Stock"));
				node_1.add(new DefaultMutableTreeNode("Stock Statement"));
				node_1.add(new DefaultMutableTreeNode("Stock Issue Record"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Customer Record");
				node_1.add(new DefaultMutableTreeNode("Records"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Advance Booking");
				node_1.add(new DefaultMutableTreeNode("Booking"));
				node_1.add(new DefaultMutableTreeNode("Booking Category Master"));
				node_1.add(new DefaultMutableTreeNode("Booking Records"));
				node_1.add(new DefaultMutableTreeNode("Advance Booking Payment"));
				//
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Other Expenses");
				node_1.add(new DefaultMutableTreeNode("Miscellaneous Expenses"));
				node_1.add(new DefaultMutableTreeNode("Staff Daily Expenses Record"));
				node_1.add(new DefaultMutableTreeNode("Daily Other Expenses Record"));
				//
				add(node_1);

				// Booking Category Master
				// Vendor Payment
			}
		}));

		tree.setBackground(new Color(255, 255, 255));
		tree.setFont(new Font("Calibri", Font.PLAIN, 16));
		tree.setRowHeight(30);
		tree.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {

				jTree1ValueChanged(evt);
			}

			private void jTree1ValueChanged(TreeSelectionEvent evt) {
				try {
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
					} else if (node.equals("Customer Birthday list")) {
						bl.setVisible(true);
					} else if (node.equals("Product Master")) {
						productobj.setVisible(true);
					}
					// Customer Birthday list
					// Designation Master
					else if (node.equals("Designation Master")) {
						designationobj.setVisible(true);
					} else if (node.equals("Bill Records")) {

						showbillobj.setVisible(true);

					} else if (node.equals("Vendor Payment Record")) {

						show_vendorpaymentreocrd.setVisible(true);

					} else if (node.equals("Purchase Records")) {
						showpurchaserecordobj.setVisible(true);
					} else if (node.equals("Staff Records")) {
						showstaffobj.setVisible(true);
					} else if (node.equals("Vendor/Party Record")) {
						showppartyobj.setVisible(true);
					} else if (node.equals("Stock Issue Record")) {
						stockissuerecordobj.setVisible(true);
					}

					else if (node.equals("P/L Record")) {
						pnlpageobj.setVisible(true);
					} else if (node.equals("Staff Salary Calculation")) {
						salarycalobj.setVisible(true);
					} else if (node.equals("Staff Salary Records")) {
						staffsalaryobj.setVisible(true);
					} else if (node.equals("New Purchase")) {
						inventoryobj.setVisible(true);
					} else if (node.equals("Vendor Payment")) {
						vendorpaymentobj.setVisible(true);
					}

					else if (node.equals("Location Master")) {
						locationobj.setVisible(true);
					} else if (node.equals("Issue Stock")) {
						stockobj.setVisible(true);
					} else if (node.equals("Stock Statement")) {
						smobj.setVisible(true);
					} else if (node.equals("Records")) {
						crecords.setVisible(true);
					}

					else if (node.equals("userpanel creation")) {
						userpanel_signupobj.setVisible(true);
					} else if (node.equals("Booking")) {
						birthdayadvanceobj.setVisible(true);
					} else if (node.equals("Booking Category Master")) {
						Booking_Categoryobj.setVisible(true);
					} else if (node.equals("Booking Records")) {
						bookdetails.setVisible(true);
					} else if (node.equals("Advance Booking Payment")) {
						advancebooking_paymentobj.setVisible(true);
					}

					else if (node.equals("Miscellaneous Expenses")) {

						puchase_wagesobj.setVisible(true);
					} else if (node.equals("Staff Daily Expenses Record")) {

						DailyStaffExpenseobj.setVisible(true);
					} else if (node.equals("Daily Other Expenses Record")) {

						DailyPurchaseExpobj.setVisible(true);
					}

					else if (node.equals("Company Profile")) {

						compnayobj.setVisible(true);
					} else if (node.equals("KOT Records")) {

						kotobj.setVisible(true);
					}
					// kotobj KOT Record
					else {

					}

				} catch (Exception f) {
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1367, 689);
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
		label.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/design3.png")));
		label.setBounds(0, 22, 252, 769);
		panel.add(label);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 21, 1440, 21);
		contentPane.add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmClose = new JMenuItem("Log Out");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login a = new Login();
				a.setVisible(true);
				dispose();
				all_dispose();
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

		JMenuItem mntmStaffRecord = new JMenuItem("Staff Record");
		mntmStaffRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showstaffobj.setVisible(true);
			}

		});
		mnRecord.add(mntmStaffRecord);

		JMenuItem mntmBillRecord = new JMenuItem("Bill Record");
		mntmBillRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showbillobj.setVisible(true);
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
				label_3.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/close2a.jpg")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label_3.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/close2.jpg")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String message = "Do you want to exit";
				String title = "CONFIRM EXIT";
				try {
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						dispose();
						all_dispose();

					}
					// JOptionPane.showMessageDialog(null,"Tray icon: Mouse
					// minimize");

				} catch (Exception f) {
				}
			}

		});
		label_3.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/close2.jpg")));

		label_3.setBounds(1232, 0, 25, 21);
		contentPane.add(label_3);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/close 1a.jpg")));
				SystemTray tray = SystemTray.getSystemTray();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/close1.jpg")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// JOptionPane.showMessageDialog(null, "MINIMIZED");
				setExtendedState(JFrame.ICONIFIED);
				try {
					all_dispose();
					// setState(JFrame.ICONIFIED);

				} catch (Exception f) {
					// JOptionPane.showMessageDialog(null, f);
				}

			}
		});

		lblNewLabel_4.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/close1.jpg")));

		lblNewLabel_4.setBounds(1200, 0, 25, 21);
		contentPane.add(lblNewLabel_4);

		lblNewLabel_3 = new JLabel("   Current User:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBorder(new LineBorder(new Color(255, 255, 255)));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(Color.BLACK);
		currentuser(u1);
		lblNewLabel_3.setBounds(0, 0, 1366, 21);

		contentPane.add(lblNewLabel_3);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.MAGENTA);
		lblNewLabel.setIcon(new ImageIcon(homepage.class.getResource("/hms/images/FOODFACTORYBC.jpg")));
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
				// else if ((arg0.getNewState() & frame.MAXIMIZED_BOTH) ==
				// frame.MAXIMIZED_BOTH){
				// JOptionPane.showMessageDialog(null,"Tray icon: Mouse
				// maximize");
				// }

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
		show_vendorpaymentreocrd.dispose();
		designationobj.dispose();
		showbillobj.dispose();
		inventoryobj.dispose();
		showpurchaserecordobj.dispose();
		locationobj.dispose();
		showppartyobj.dispose();
		showstaffobj.dispose();
		pnlpageobj.dispose();
		salarycalobj.dispose();
		staffsalaryobj.dispose();
		userpanel_signupobj.dispose();
		bl.dispose();
		productobj.dispose();
		stockobj.dispose();
		stockissuerecordobj.dispose();
		smobj.dispose();
		vendorpaymentobj.dispose();
		crecords.dispose();
		Booking_Categoryobj.dispose();
		birthdayadvanceobj.dispose();
		bookdetails.dispose();
		advancebooking_paymentobj.dispose();
		puchase_wagesobj.dispose();
		DailyStaffExpenseobj.dispose();
		DailyPurchaseExpobj.dispose();
		compnayobj.dispose();
		kotobj.dispose();

	}

	public boolean showtheitem() throws SQLException {
		try {
			String time = now();
			dbcon.connect();
			String sql = "Select Customer_Name from tablecustomerdetail WHERE (Ocassion_Date='" + time
					+ "' AND Ocassion='ANNIVERSARY') OR (Ocassion_Date='" + time
					+ "' AND Ocassion='BIRTHDAY') OR (Ocassion_Date='" + time
					+ "' AND Ocassion='BIRTHDAY' AND Ocassion='ANNIVERSARY')  ";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			while (dbcon.rs.next()) {
				check_boolean = true;
			}

		} catch (SQLException e) {
			// JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
		return check_boolean;
	}
	
	public boolean showtheitem4ab() throws SQLException {
		try {
			String time = now();
			dbcon.connect();
			String sql = "Select bookingocassion from bookingdetails WHERE datefrom='" + time	+ "'";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			while (dbcon.rs.next()) {
				check_boolean4ab = true;
			
			}

		} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
		return check_boolean4ab;
	}

	public static String now() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat(DateFormat);

		return format.format(cal.getTime());

	}

	public void currentuser(String u1) {
		lblNewLabel_3.setText("   Current User  :   " + u1);

	}

	public void showcompanydetails() throws SQLException {
		try {

			dbcon.connect();
			String sql = "Select companyname,companyimage from companyprofile";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			if (dbcon.rs.next()) {
				// pathName=dbcon.rs.getString("logopath");
				byte[] imagedata = dbcon.rs.getBytes("companyimage");

				ByteArrayInputStream bis = new ByteArrayInputStream(imagedata);
				BufferedImage bImage2 = ImageIO.read(bis);
				//
				BufferedImage newimage = resizeImage(bImage2, 293, 254);
				//

				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ImageIO.write(newimage, "png", bos);

				byte[] data = bos.toByteArray();

				format = new ImageIcon(data);
				// format = new ImageIcon(imagedata);
				label_4.setIcon(format);
				lblNewLabel_5.setText(dbcon.rs.getString("companyname"));

			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		} finally {
			dbcon.conn.close();
		}
	}

	public static BufferedImage resizeImage(BufferedImage image, int areaWidth, int areaHeight) {
		float scaleX = (float) areaWidth / image.getWidth();
		float scaleY = (float) areaHeight / image.getHeight();
		float scale = Math.min(scaleX, scaleY);
		int w = Math.round(image.getWidth() * scale);
		int h = Math.round(image.getHeight() * scale);

		int type = image.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;

		boolean scaleDown = scale < 1;

		if (scaleDown) {
			// multi-pass bilinear div 2
			int currentW = image.getWidth();
			int currentH = image.getHeight();
			BufferedImage resized = image;
			while (currentW > w || currentH > h) {
				currentW = Math.max(w, currentW / 2);
				currentH = Math.max(h, currentH / 2);

				BufferedImage temp = new BufferedImage(currentW, currentH, type);
				Graphics2D g2 = temp.createGraphics();
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.drawImage(resized, 0, 0, currentW, currentH, null);
				g2.dispose();
				resized = temp;
			}
			return resized;
		} else {
			Object hint = scale > 2 ? RenderingHints.VALUE_INTERPOLATION_BICUBIC
					: RenderingHints.VALUE_INTERPOLATION_BILINEAR;

			BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = resized.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(image, 0, 0, w, h, null);
			g2.dispose();
			return resized;
		}
	}
}
