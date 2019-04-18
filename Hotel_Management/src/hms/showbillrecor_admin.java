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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class showbillrecor_admin extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	Databaseconnection dbcon = new Databaseconnection();
	DefaultTableModel model = new DefaultTableModel();
	String[][] tableData = new String[50][50];
	ArrayList<String> invoice = new ArrayList<String>();
	String invoicestring = null;
	String[] myList;
	JLabel label_2;
	public static final String DateFormat = "yyyy/MM/dd";
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JTable table_1;
	String search;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JComboBox comboBox;
	private JList list;
	private JButton btnNewButton_2;
	private String defaultValues[];

	DefaultListModel listmodel = new DefaultListModel();
	private JButton btnInclude;
	JList stmtList;
	ArrayList ar = new ArrayList();
	private JTextField textField_5;

//	Strin data

	public showbillrecor_admin(String u1) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setUndecorated(true);
		setType(javax.swing.JFrame.Type.UTILITY);

		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 184, 616, 333);
		contentPanel.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 616, 333);
		panel.add(scrollPane);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					model = (DefaultTableModel) table.getModel();
					DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
//				int SelectedRowIndex = table.getSelectedRow();

					int x = table.getSelectedRow();
					String item = model.getValueAt(x, 0).toString();

					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
						String message = "Do you want to Delete ? ";
						String title = "CONFIRM";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

//					JOptionPane.showMessageDialog(null, item);
							model.removeRow(x);
							dbcon.billdelete(item);

							showtablebill(u1);
							billamountshowtoday(u1);
							model1.setRowCount(0);
//						model.setRowCount(0);

						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					model = (DefaultTableModel) table.getModel();

					int x = table.getSelectedRow();
					String billnumber = model.getValueAt(x, 0).toString();
					String Customername = model.getValueAt(x, 1).toString();
					String Mobilenumber = model.getValueAt(x, 2).toString();
					String Amount = model.getValueAt(x, 8).toString();

					textField_1.setText(billnumber);
					textField_2.setText(Customername);
					textField_3.setText(Mobilenumber);
					textField_4.setText(Amount);

//				try {

					showtheitems(billnumber);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Bill Number", "Customer ID", "Name",
				"Aadhar No.", "Mobile No.", "New column", "New column", "New column" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(0).setMinWidth(80);
		table.getColumnModel().getColumn(0).setMaxWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(113);
		table.getColumnModel().getColumn(1).setMaxWidth(113);
		table.getColumnModel().getColumn(2).setPreferredWidth(229);
		table.getColumnModel().getColumn(2).setMaxWidth(229);
		table.getColumnModel().getColumn(3).setPreferredWidth(121);
		table.getColumnModel().getColumn(3).setMaxWidth(121);
		table.getColumnModel().getColumn(4).setPreferredWidth(98);
		table.getColumnModel().getColumn(4).setMaxWidth(100);
		try {
			showtablebill(u1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				String txt = textField.getText().toUpperCase();
				Filter(txt);
			}
		});
		textField.setColumns(10);
		textField.setBounds(464, 142, 460, 31);
		contentPanel.add(textField);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(showbillrecor_admin.class.getResource("/hms/images/Zoom-icon2.png")));
		lblSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSearch.setBounds(420, 133, 504, 56);
		contentPanel.add(lblSearch);

		JLabel lblShowAllVehicle = new JLabel("Invoice Record");
		lblShowAllVehicle.setOpaque(true);
		lblShowAllVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowAllVehicle.setForeground(SystemColor.activeCaptionText);
		lblShowAllVehicle.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblShowAllVehicle.setBackground(Color.WHITE);
		lblShowAllVehicle.setBounds(0, 0, 924, 31);
		contentPanel.add(lblShowAllVehicle);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setDate(new Date());
//		dateChooser_2.setMinSelectableDate(new Date());
		dateChooser.setBounds(672, 42, 98, 20);
		contentPanel.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		dateChooser_1.setDate(new Date());
//		dateChooser_3.setMinSelectableDate(new Date());
		dateChooser_1.setBounds(815, 42, 98, 20);
		contentPanel.add(dateChooser_1);

		JLabel label = new JLabel("-TO-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(780, 42, 35, 20);
		contentPanel.add(label);

		JButton button = new JButton("Click to Search");
		button.setForeground(Color.WHITE);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Object[] namelist = list.getSelectedValues();

//					
					String Customername = comboBox.getSelectedItem().toString();
					String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
//					
//JOptionPane.showMessageDialog(null, data13+","+data14+","+u1+","+Customername+","+namelist);
					datequery(data13, data14, u1, Customername, namelist);
//					

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}

			}
		});
		button.setBackground(Color.GRAY);
		button.setBounds(666, 100, 126, 31);
		contentPanel.add(button);

		JLabel lblTotalAmount = new JLabel("TOTAL:");
		lblTotalAmount.setForeground(Color.WHITE);
		lblTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotalAmount.setBounds(780, 530, 46, 14);
		contentPanel.add(lblTotalAmount);

		label_2 = new JLabel("0.00");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(825, 528, 110, 14);
		contentPanel.add(label_2);

		label_3 = new JLabel("0.0");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(61, 528, 121, 15);
		contentPanel.add(label_3);

		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setForeground(Color.WHITE);
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSubtotal.setBounds(10, 528, 51, 14);
		contentPanel.add(lblSubtotal);

		label_4 = new JLabel("0.0");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(488, 528, 110, 15);
		contentPanel.add(label_4);

		JLabel lblCgstsgst = new JLabel("CGST:");
		lblCgstsgst.setForeground(Color.WHITE);
		lblCgstsgst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCgstsgst.setBounds(454, 528, 35, 14);
		contentPanel.add(lblCgstsgst);

		label_5 = new JLabel("0.0");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(651, 528, 110, 15);
		contentPanel.add(label_5);

		JLabel lblSgst = new JLabel("SGST:");
		lblSgst.setForeground(Color.WHITE);
		lblSgst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSgst.setBounds(620, 528, 35, 14);
		contentPanel.add(lblSgst);

		label_6 = new JLabel("0.0");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(257, 528, 157, 14);
		contentPanel.add(label_6);

		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setForeground(Color.WHITE);
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDiscount.setBounds(202, 528, 45, 14);
		contentPanel.add(lblDiscount);

		JLabel label_7 = new JLabel("+");
		label_7.setForeground(Color.WHITE);
		label_7.setBounds(424, 528, 20, 14);
		contentPanel.add(label_7);

		JLabel label_8 = new JLabel("+");
		label_8.setForeground(Color.WHITE);
		label_8.setBounds(608, 528, 20, 14);
		contentPanel.add(label_8);

		JLabel label_9 = new JLabel("-");
		label_9.setForeground(Color.WHITE);
		label_9.setBounds(186, 528, 20, 14);
		contentPanel.add(label_9);

		JLabel label_10 = new JLabel("=");
		label_10.setForeground(Color.WHITE);
		label_10.setBounds(771, 530, 20, 14);
		contentPanel.add(label_10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(626, 184, 299, 333);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 299, 333);
		panel_1.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Price", "Quantity", "Amt" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(112);
		table_1.getColumnModel().getColumn(0).setMinWidth(112);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(57);
		table_1.getColumnModel().getColumn(1).setMinWidth(57);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(52);
		table_1.getColumnModel().getColumn(2).setMinWidth(52);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(79);
		table_1.getColumnModel().getColumn(3).setMinWidth(79);
		scrollPane_1.setViewportView(table_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(8, 555, 916, 56);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1
				.setBorder(new TitledBorder(null, "Bill Number", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_1.setBounds(10, 11, 160, 36);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer Name",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_2.setBounds(180, 11, 160, 36);
		panel_2.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mobile Number",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_3.setBounds(350, 11, 153, 36);
		panel_2.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bill Amount",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_4.setBounds(513, 11, 138, 36);
		panel_2.add(textField_4);

		JButton btnNewButton = new JButton("Print Selected Bill");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String invoice = textField_1.getText().trim();

					if (invoice.length() != 0) {

						Rmsbook rmsobj;

						rmsobj = new Rmsbook(invoice);

						rmsobj.printfunctionworking(invoice);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1 + "Bill Not Found");
				}

			}
		});
		btnNewButton.setBounds(661, 11, 153, 34);
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearPrintFields();
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(824, 11, 89, 34);
		panel_2.add(btnNewButton_1);

		comboBox = new JComboBox();

		comboBox.setEditable(true);
		AutoCompleteDecorator.decorate(comboBox);
//		comboBox_2.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
//
//			@Override
//			public void keyReleased(KeyEvent event) {
//				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
//					textField_1.requestFocus();
//				}
//			}
//		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		comboBox.setBounds(431, 83, 196, 38);
		contentPanel.add(comboBox);
		try {
			CustomerNameList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(489, 42, 66, 14);
		contentPanel.add(lblName);

		textField_5 = new JTextField();
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String filter = textField_5.getText();
				searchFilter(filter);

			}

//				 JOptionPane.showMessageDialog(null, defaultValues);
//				    filterModel((DefaultListModel<String>)list.getModel(), filter);
//			}
		});
		textField_5.setBounds(10, 44, 196, 26);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);

		btnNewButton_2 = new JButton("<= Not Include");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setSelectedIndex(0);
				btnInclude.setEnabled(false);
				comboBox.setEnabled(false);
				list.setEnabled(true);
				textField_5.setEnabled(true);

			}
		});
		btnNewButton_2.setBounds(231, 42, 150, 31);
		contentPanel.add(btnNewButton_2);

		btnInclude = new JButton("-> Include");
		btnInclude.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.clearSelection();
				btnNewButton_2.setEnabled(false);
				list.setEnabled(false);
				textField_5.setEnabled(false);
				comboBox.setEnabled(true);

			}
		});
		btnInclude.setBounds(231, 87, 150, 31);
		contentPanel.add(btnInclude);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 77, 196, 100);
		contentPanel.add(scrollPane_2);

		list = new JList();
		scrollPane_2.setViewportView(list);
		list.setToolTipText("Press Ctrl+Mouse Click To Select Multiple Feature");
		list.setFont(new Font("Calibri", Font.PLAIN, 15));

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				onloadrun();

			}
		});
		btnClear.setBounds(231, 129, 150, 31);
		contentPanel.add(btnClear);
		
		JButton btnNewButton_3 = new JButton("Print All");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] namelist = list.getSelectedValues();

//				
				String Customername = comboBox.getSelectedItem().toString();
				String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				
				try {
					model = (DefaultTableModel) table.getModel();
					if(model.getRowCount()>0)
					{
					printfunction4billall(data13, data14, u1, Customername, namelist);
					
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Click Search First, then Click Print Button to Print Report");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(802, 100, 122, 31);
		contentPanel.add(btnNewButton_3);
		try {
			adddatatolist();
			stmtList = new JList(listmodel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			billamountshowtoday(u1);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e2);
		}
		onloadrun();
		
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				textField.requestFocus();
				Object[] namelist = list.getSelectedValues();

				String Customername = comboBox.getSelectedItem().toString();
				String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();

			
					try {
						datequery(data13, data14, search, Customername, namelist);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});

	}

	

	public void Filter(String sql) {

		model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(sql));
	}



	public void showtablebill(String search) throws SQLException {
		try {
			dbcon.connect();
//			JOptionPane.showMessageDialog(null, search);
			String sql = "Select * from tablecustomerdetail";
//			String sql = "Select * from tablecustomerdetail where Billing_Staffname='"+search+"'";
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {

			dbcon.conn.close();
		}
	}

	public void datequery(String data13, String data14, String search, String Customername, Object[] namelist)
			throws SQLException {
		try {

			String datequery = " AND savedate between '" + data13 + "' and '" + data14 + "' ";

			String excludedpartiesquery = "";

			for (int index = 0; index < namelist.length; index++) {
				excludedpartiesquery = excludedpartiesquery
						.concat(" and Customer_Name not like '%" + namelist[index] + "%'");
			}

			dbcon.connect();

			String userquery = Customername.equalsIgnoreCase("Select") ? ""
					: " AND Customer_Name='" + Customername + "'";

			String sql = "Select * from tablecustomerdetail where savedate is not null " + excludedpartiesquery
					+ datequery + userquery + " ";

//			String sql = "Select * from tablecustomerdetail where savedate is not null and savedate between '" + data13	+ "' and '" + data14 + excludedpartiesquery + "'";
//
//		String sql ="Select * from tablecustomerdetail where savedate between '"+data13+"' and '"+data14+"' AND Billing_Staffname='"+search+"'";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			model.setRowCount(0);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));

			totalsaleshow(model);
		}

		catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error" + e);
		} finally {
			dbcon.conn.close();
		}

	}

	public void totalsaleshow(DefaultTableModel model2) {
		double subtotal = 0, cgst = 0, sgst = 0, grandtotal = 0, discount = 0;
		model2 = (DefaultTableModel) table.getModel();
		table.setModel(model2);
		int nRow = model2.getRowCount();

//	JOptionPane.showMessageDialog(null, nRow);

		for (int i = 0; i < nRow; i++) {

			double amount = Double.parseDouble(model2.getValueAt(i, 8).toString());

			subtotal = subtotal + amount;

			double cgst_1 = Double.parseDouble((String) model2.getValueAt(i, 9).toString());
			cgst = cgst + cgst_1;

			double sgst_1 = Double.parseDouble((String) model2.getValueAt(i, 10).toString());
			sgst = sgst + sgst_1;

			double grandtotal_1 = Double.parseDouble((String) model2.getValueAt(i, 12).toString());
			grandtotal = grandtotal + grandtotal_1;

			double discount_1 = Double.parseDouble((String) model2.getValueAt(i, 11).toString());
			discount = discount + discount_1;

		}

		double subtotal_roundoff = Math.round(subtotal * 100.0) / 100.0;
		double cgst_rf = Math.round(cgst * 100.0) / 100.0;
		double sgst_rf = Math.round(sgst * 100.0) / 100.0;
		double discount_rf = Math.round(discount * 100.0) / 100.0;
		double grandtotal_rf = Math.round(grandtotal * 100.0) / 100.0;

		label_3.setText(String.valueOf(subtotal_roundoff));
		label_4.setText(String.valueOf(cgst_rf));

		label_5.setText(String.valueOf(sgst_rf));
		label_6.setText(String.valueOf(discount_rf));

		label_2.setText(String.valueOf(grandtotal_rf));
	}

	public void billamountshowtoday(String search) throws SQLException {

		Object[] namelist = list.getSelectedValues();

		String Customername = comboBox.getSelectedItem().toString();
		String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();

		datequery(data13, data14, search, Customername, namelist);
	}

	public void showtheitems(String item) throws SQLException {
		try {
			DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
			model1.setRowCount(0);
			dbcon.connect();
			String sql = "Select item_name,unit_price,quantity,item_amount from tablecustomerdetail_itemlist where Invoice_Number='"
					+ item + "'";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			int i = 1;
			while (dbcon.rs.next()) {
				String item_name = dbcon.rs.getString("item_name");

				String quantity = dbcon.rs.getString("unit_price");

				String mrp = dbcon.rs.getString("quantity");
				String batchno = dbcon.rs.getString("item_amount");
				Object[] row = { item_name, quantity, mrp, batchno };

				model1.addRow(row);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.pst.close();
			dbcon.rs.close();
			dbcon.conn.close();
		}
	}

	public void ClearPrintFields() {

		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");

	}

	public void CustomerNameList() throws SQLException {
		try {

			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st
					.executeQuery("Select DISTINCT Customer_Name from tablecustomerdetail  order by Customer_Name asc");
			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);

				comboBox.addItem(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "CONTACT SUPPORT TEAM!" + e);
		} finally {
			dbcon.conn.close();
		}
	}

	public void adddatatolist() throws SQLException {
		try {

			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st.executeQuery(
					"Select DISTINCT Customer_Name from tablecustomerdetail  order by Customer_Name desc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);

				listmodel.addElement(data);
				ar.add(data);
			}
			list.setModel(listmodel);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "CONTACT SUPPORT TEAM!" + e);
		} finally {
			dbcon.conn.close();
		}
	}

	private void searchFilter(String searchTerm) {
		DefaultListModel filteredItems = new DefaultListModel();
		ArrayList stars = ar;

		stars.stream().forEach((star) -> {
			String starName = star.toString().toLowerCase();
			if (starName.contains(searchTerm.toLowerCase())) {
				filteredItems.addElement(star);
			}
		});
		listmodel = filteredItems;
		list.setModel(listmodel);

	}

	public void onloadrun() {
		list.clearSelection();
		btnNewButton_2.setEnabled(true);
		comboBox.setSelectedIndex(0);
		btnInclude.setEnabled(true);
		list.setEnabled(false);
		textField_5.setEnabled(false);
		comboBox.setEnabled(false);
	}
	protected void printfunction4billall(String data13, String data14, String u1, String customername, Object[] namelist) throws SQLException {
		
		
		String subtotal_roundoff=label_3.getText();
		String cgst_rf=label_4.getText();
		String sgst_rf=label_5.getText();
		String discount_rf=label_6.getText();
		String grandtotal_rf=label_2.getText();

		
		
		try {
			dbcon.connect();
		String datequery = " AND savedate between '" + data13 + "' and '" + data14 + "' ";

		String excludedpartiesquery = "";

		for (int index = 0; index < namelist.length; index++) {
			excludedpartiesquery = excludedpartiesquery
					.concat(" and Customer_Name not like '%" + namelist[index] + "%'");
		}

		

		String userquery = customername.equalsIgnoreCase("Select") ? ""
				: " AND Customer_Name='" + customername + "'";

		String sql = "Select * from tablecustomerdetail where savedate is not null " + excludedpartiesquery
				+ datequery + userquery + " ";
		
		
		HashMap params = new HashMap();
		
		
		
//company name address details
//		HashMap hashmap = new HashMap();
//		hashmap.putAll(getBranchDetails());
		String companyaddress = "", companyname = "", companygstin = "", companymobile = "", companycity = "";

		String sql1 = "Select companyimage,companyid,companyname,address,city,state,country,phoneno,gstin from companyprofile";

		// java.sql.Connection conn = null;;
		// java.sql.ResultSet rs;
		dbcon.connect();
		dbcon.pst = dbcon.conn.prepareStatement(sql1);

		dbcon.rs = dbcon.pst.executeQuery();
		if (dbcon.rs.next()) {
			companyaddress = dbcon.rs.getString("address");
			companyname = dbcon.rs.getString("companyname");
			companycity = dbcon.rs.getString("city");

			companygstin = dbcon.rs.getString("gstin");

			companymobile = dbcon.rs.getString("phoneno");

		}

		params.put("companyaddress", (companyaddress == null ? "" : companyaddress));
		params.put("companyname", (companyname == null ? "" : companyname));
		params.put("companygstin", (companygstin == null ? "" : companygstin));
		params.put("companymobile", (companymobile == null ? "" : companymobile));
		params.put("companycity", (companycity == null ? "" : companycity));
		
		
		//
		params.put("date1", data13);
		params.put("date2", data14);
		params.put("subtotal", subtotal_roundoff);
		params.put("cgst", cgst_rf);
		params.put("sgst", sgst_rf);
		params.put("discount", discount_rf);
		params.put("grandtotal", grandtotal_rf);

		
		
		
		
		
		
//		params.put("partyname", comboBox.getSelectedItem().toString());
//		params.put("productname", comboBox_1.getSelectedItem().toString());
//		params.put("amount", label.getText());
		
		java.io.InputStream file = getClass().getResourceAsStream("/a4billReport.jrxml");

		JasperDesign jd = JRXmlLoader.load(file);

//			JasperDesign jd = JRXmlLoader.load("D:\\workspace\\Hotel_Management\\src\\restkot.jrxml");
		//sql = "select * from bill_list WHERE bill_no='" + bl + "'";
		JRDesignQuery newquery = new JRDesignQuery();
		newquery.setText(sql);
		jd.setQuery(newquery);

		JasperReport jr = JasperCompileManager.compileReport(jd);
		JasperPrint jp = JasperFillManager.fillReport(jr, params, dbcon.connect());
		JasperViewer.viewReport(jp, false);
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	} finally {
		dbcon.conn.close();
	}
	}
	

}
