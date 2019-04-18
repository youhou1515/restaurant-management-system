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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Rmsbook extends JFrame implements ActionListener {
	JComboBox comboBox_1;
	private final JPanel contentPanel = new JPanel();
	private JComboBox textField_1;
	private JTextField textField_5;
	private JTextField textField_13;
	private JComboBox textField_14;
	private JTable table_1;
	String billbreakdelete, kotbreakdelete;
	String[] Array, Arraycombo;
	String[] myList;
	String[][] tableData;
	String billfinalsave;
	JLabel lblKotidnumber;
	JTextFieldDateEditor editor;
	String tablenameupdate;
	JTextField textArea_1;

	String koitdmerge, lblKotidnumber_convert_to_string;
	ArrayList<String> invoice = new ArrayList<String>();
	String Kot_id, waiter_name, bill, cut, customerwishes, customer_name, occasion_date, cust_mob, Paymentmode, detail,
			amountconversion;
	JLabel label_7, label_8, label_10, label_9;
	double discountamount, grandtotal;
	// booked table set details

	JComboBox comboBox, comboBox_3, comboBox_2;
	String kotbreak;
	JButton btnNewButton_1 = new JButton("<html>Reserve A Table<br> &nbsp &nbsp[ Alt &nbsp + &nbsp R ]</html>");

	DefaultTableModel model = new DefaultTableModel();
	DecimalFormat df = new DecimalFormat("#.00");
	String name, contact, comboboxadd;
	JButton btnNewButton, btnNewBill;

	Databaseconnection dbcon = new Databaseconnection();
	java.sql.Connection conn = null;;
	java.sql.ResultSet rs;
	java.sql.PreparedStatement pst;
	private JTable table_2;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	tablemaster tableobj = new tablemaster();
	private JDateChooser textArea;
	JButton btnAdd, btnCancelATable;
	Double rate, quantity, amount;
	public JButton enable;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTable table;
	int serialno = 1;
	String listamount = "";
	String billbreak;

	static double a = 0, b = 0, result = 0;
	static int operator = 0;
	String[][] tablecheck;

	public static final String DateFormat = "yyyy/MM/dd";
	public static final String TimeFormat = "HH:mm";

	String[] itemarray1 = new String[50];
	String[] tablenames = null;
	String[][] multiarray = new String[50][];
	private JPanel panel_11;
	String savedate, savetime;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel label_13;
	private JLabel lblInvoice;
	private JButton btnNewButton_2;
	private JButton lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JComboBox comboBox_4;

	public Rmsbook(String current_user) throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Rmsbook.class.getResource("")));
		DigitalClock clockobj = new DigitalClock();
		LocalDate today = LocalDate.now();
		LocalTime time = LocalTime.now();

		ClockLabel dateLable = new ClockLabel("date");
		ClockLabel timeLable = new ClockLabel("time");
		timeLable.setForeground(new Color(255, 0, 0));
		ClockLabel dayLable = new ClockLabel("day");

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, dim.width, dim.height * 19 / 20);
//		setUndecorated(true);
		// setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.RED);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblNewLabel_4 = new JLabel("   Current User:");
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBounds(10, 2, 216, 17);
		setuser(current_user);

		contentPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_2 = new JLabel("RESTRAUNT ORDER PANEL");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(434, 2, 488, 17);
		contentPanel.add(lblNewLabel_2);

		JPanel panel_8 = new JPanel();
		panel_8.setBounds(17, 527, 913, 158);
		contentPanel.add(panel_8);
		panel_8.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);

		scrollPane_1.setBounds(0, 0, 915, 156);
		panel_8.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setFillsViewportHeight(true);
		table_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_1.setCellSelectionEnabled(true);
		table_1.setColumnSelectionAllowed(true);
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Table-No.", "Invoice No.", "Kot ID",
				"Occasion", "Name", "Occasion Date", "Mobile No.", "Amount", "Time", "Date", "Waiter" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(64);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(76);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(98);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(121);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(88);
		table_1.getColumnModel().getColumn(6).setPreferredWidth(79);
		table_1.getColumnModel().getColumn(8).setResizable(false);
		table_1.getColumnModel().getColumn(9).setResizable(false);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DefaultTableModel model12 = (DefaultTableModel) table.getModel();
					model12.setRowCount(0);
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					// get the selected row index
					int selectedRowIndex = table_1.getSelectedRow();
					tablenameupdate = model.getValueAt(selectedRowIndex, 0).toString();
					billfinalsave = model.getValueAt(selectedRowIndex, 1).toString();
					label_13.setText(billfinalsave.substring(8));
					showtheitem(billfinalsave);
					String tablefetch_kotid = model.getValueAt(selectedRowIndex, 2).toString();
					lblKotidnumber.setText(tablefetch_kotid.substring(6));
					textField_14.setSelectedItem((model.getValueAt(selectedRowIndex, 3).toString()));
					textField_14.setEnabled(true);
					textField_1.setSelectedItem(model.getValueAt(selectedRowIndex, 4).toString());
					String datestring = model.getValueAt(selectedRowIndex, 5).toString();
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					Date startDate = df.parse(datestring);
					textArea.setDate(startDate);
					textArea.setEnabled(true);
					textField_5.setText(model.getValueAt(selectedRowIndex, 6).toString());
					textField_4.setText(model.getValueAt(selectedRowIndex, 7).toString());
					comboBox_2.setSelectedItem(model.getValueAt(selectedRowIndex, 10).toString());
					int row = table_1.getSelectedRow();
					String TableClick = table_1.getValueAt(row, 1).toString();
					comboBox_3.setEnabled(true);
					btnCancelATable.setEnabled(true);
					textField_3.setText("0");
					textField_3.setEnabled(true);
					btnNewButton.setEnabled(true);
					btnNewButton_1.setVisible(false);
					btnNewButton_2.setVisible(true);
				} catch (Exception fg) {
//					 JOptionPane.showMessageDialog(null, fg+"");
				}

			}

		});
		table_1.getTableHeader().setReorderingAllowed(false);
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(396, 247, 657, 217);
		contentPanel.add(panel_7);
		panel_7.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 656, 217);
		panel_7.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() != null) {
					DefaultTableModel models = (DefaultTableModel) table.getModel();
					int SelectedRowIndex = table.getSelectedRow();
					String itemname = model.getValueAt(SelectedRowIndex, 1).toString();
					String price = model.getValueAt(SelectedRowIndex, 2).toString();
					String qty = model.getValueAt(SelectedRowIndex, 3).toString();
					String amount = model.getValueAt(SelectedRowIndex, 4).toString();
					String comments = model.getValueAt(SelectedRowIndex, 5).toString();
					textField_7.setText(qty);
					textField_8.setText(price);
					textField_9.setText(amount);
					comboBox.setSelectedItem(itemname);
					textArea_1.setText(comments);
					models.removeRow(SelectedRowIndex);
				}

			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
						DefaultTableModel models = (DefaultTableModel) table.getModel();

						int SelectedRowIndex = table.getSelectedRow();
						models.removeRow(SelectedRowIndex);
					}
				} catch (Exception f) {
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Sl No.", "Items", "Unit Price", "Quantity", "Total Amount", "Comments" }));
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(0, 21, 389, 315);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		textField_1 = new JComboBox();
		textField_1.setEditable(true);
		AutoCompleteDecorator.decorate(textField_1);

		textField_1.setBounds(209, 189, 170, 22);
		CustomerNameList();
		panel_1.add(textField_1);

		textField_5 = new JTextField();
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				lblNewLabel_3
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					lblNewLabel_3.requestFocus();
				}
			}
		});
		textField_5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

			}
		});

		textField_5.setBounds(209, 222, 170, 22);
		textField_5.setColumns(10);
		panel_1.add(textField_5);

		JLabel label = new JLabel("Contact No.:");
		label.setBounds(21, 221, 81, 24);
		label.setFont(new Font("Calibri", Font.PLAIN, 13));
		panel_1.add(label);

		JLabel label_2 = new JLabel("Customer Name:");
		label_2.setBounds(21, 188, 109, 24);
		label_2.setFont(new Font("Calibri", Font.PLAIN, 13));
		panel_1.add(label_2);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(8, 11, 211, 43);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Bill Type", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_1.add(panel_2);

		JLabel lblKotId = new JLabel("Kot ID/");
		lblKotId.setForeground(Color.RED);
		lblKotId.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblKotId.setBounds(10, 11, 67, 22);
		panel_2.add(lblKotId);

		lblKotidnumber = new JLabel("");
		set_max_function_for_KOTID();
		lblKotidnumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKotidnumber.setBounds(68, 11, 110, 22);
		panel_2.add(lblKotidnumber);

		JLabel label_4 = new JLabel("Bill No.-");
		label_4.setBounds(21, 157, 81, 18);
		label_4.setFont(new Font("Calibri", Font.PLAIN, 13));
		panel_1.add(label_4);

		textField_14 = new JComboBox();
		textField_14.setEnabled(false);
		textField_14.setModel(new DefaultComboBoxModel(new String[] { "NOT ANY OCCASION", "BIRTHDAY", "ANNIVERSARY" }));
		textField_14.setBounds(21, 282, 165, 22);
		panel_1.add(textField_14);

		textArea = new JDateChooser();
		textArea.setDateFormatString("yyyy/MM/dd");

		textArea.setEnabled(false);
		textArea.setDate(new Date());
		textArea.setBounds(209, 282, 170, 22);
		panel_1.add(textArea);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Waiter List",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBackground(SystemColor.window);
		panel_4.setBounds(21, 65, 358, 72);
		panel_1.add(panel_4);

		comboBox_2 = new JComboBox();
		comboBox_2.setEditable(true);
		AutoCompleteDecorator.decorate(comboBox_2);
		comboBox_2.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_1.requestFocus();
				}
			}
		});

		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		Showstafflist();
		comboBox_2.setSelectedIndex(0);
		comboBox_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		comboBox_2.setBounds(10, 20, 325, 30);
		panel_4.add(comboBox_2);

		label_13 = new JLabel("");
		set_max_function();
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_13.setBounds(284, 156, 54, 22);
		panel_1.add(label_13);

		lblInvoice = new JLabel("INVOICE/");
		lblInvoice.setForeground(Color.RED);
		lblInvoice.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		lblInvoice.setBounds(209, 157, 90, 22);
		panel_1.add(lblInvoice);

		lblNewLabel_3 = new JButton("Click for occasion, if any: [Alt+O]");
		lblNewLabel_3.setMnemonic('O');
		lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_3.setBackground(SystemColor.inactiveCaption);
		lblNewLabel_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBox_1.requestFocus();
				}
			}
		});
		lblNewLabel_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textArea.setEnabled(true);
				textField_14.setEnabled(true);
			}
		});
		lblNewLabel_3.setBounds(42, 255, 306, 23);
		panel_1.add(lblNewLabel_3);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setDate(new Date());
		dateChooser.setBounds(270, 19, 109, 34);
		panel_1.add(dateChooser);

		JLabel lblBillDate = new JLabel("<html>Bill <br>Date</html>");
		lblBillDate.setForeground(Color.RED);
		lblBillDate.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblBillDate.setBounds(231, 19, 40, 35);
		panel_1.add(lblBillDate);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "List of Tables Available",
				TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_3.setBackground(SystemColor.inactiveCaptionBorder);
		panel_3.setBounds(389, 21, 553, 41);
		contentPanel.add(panel_3);

		comboBox_1 = new JComboBox();
		comboBox_1.setEditable(true);
		AutoCompleteDecorator.decorate(comboBox_1);
		comboBox_1.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBox.requestFocus();
				}
			}
		});
		comboBox_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		comboBox_1.setBounds(31, 16, 512, 20);

		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "SELECT", "PARCEL", "HOME DELIVERY" }));
		ShowTable();
		comboBox_1.setSelectedIndex(0);
		panel_3.add(comboBox_1);

		JLabel lblUserName = new JLabel("");
		lblUserName.setOpaque(true);
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblUserName.setBackground(SystemColor.inactiveCaptionText);
		lblUserName.setBounds(0, 0, 1350, 21);
		contentPanel.add(lblUserName);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(0, 336, 387, 158);
		contentPanel.add(panel);

		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(Rmsbook.class.getResource("/hms/images/London_logo_125_1501.png")));
		label_6.setFont(new Font("Calibri", Font.PLAIN, 13));
		label_6.setBounds(128, 0, 125, 158);
		panel.add(label_6);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_5.setBackground(SystemColor.inactiveCaptionBorder);
		panel_5.setBounds(1074, 336, 276, 257);
		contentPanel.add(panel_5);

		label_8 = new JLabel("0.0");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBackground(Color.WHITE);
		label_8.setBounds(172, 177, 90, 20);
		panel_5.add(label_8);

		label_7 = new JLabel("0.0");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBackground(Color.WHITE);
		label_7.setBounds(172, 162, 90, 20);
		panel_5.add(label_7);

		JLabel label_11 = new JLabel("Grand Total");
		label_11.setFont(new Font("Calibri", Font.PLAIN, 13));
		label_11.setBounds(8, 208, 90, 20);
		panel_5.add(label_11);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(101, 202, 163, 33);
		panel_5.add(textField_2);

		JLabel lblServiceTax = new JLabel("G.S.T.");
		lblServiceTax.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblServiceTax.setBounds(8, 122, 38, 20);
		panel_5.add(lblServiceTax);

		JLabel label_1 = new JLabel("%");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(242, 125, 22, 14);
		panel_5.add(label_1);

		comboBox_3 = new JComboBox();
		comboBox_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				btnNewButton
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton.requestFocus();
				}
			}
		});
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));
		ShowTax();
		comboBox_3.setBounds(101, 122, 142, 20);
		panel_5.add(comboBox_3);

		textField_4 = new JTextField("0");
		textField_4.setBounds(103, 11, 161, 20);
		panel_5.add(textField_4);
		textField_4.setEditable(false);
		textField_4.setColumns(10);

		JLabel lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setBounds(10, 11, 90, 20);
		panel_5.add(lblSubTotal);
		lblSubTotal.setFont(new Font("Calibri", Font.PLAIN, 13));

		JLabel lblCgst = new JLabel("CGST");
		lblCgst.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblCgst.setBounds(101, 162, 38, 20);
		panel_5.add(lblCgst);

		JLabel lblSgst = new JLabel("SGST");
		lblSgst.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblSgst.setBounds(101, 177, 38, 20);
		panel_5.add(lblSgst);

		JLabel lblGst = new JLabel("GST");
		lblGst.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblGst.setBounds(101, 145, 38, 20);
		panel_5.add(lblGst);

		label_10 = new JLabel("0.0");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setBounds(172, 145, 90, 20);
		panel_5.add(label_10);

		textField_3 = new JTextField("0.0");
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					String expression = "[0-9]*";
					String inputStr = textField_3.getText();
					Pattern pattern = Pattern.compile(expression);
					Pattern.compile(expression);
					Matcher matcher = pattern.matcher(inputStr);

					if (matcher.matches()) {

						Double abc = Double.valueOf(inputStr);
						double subtot = Double.valueOf(textField_4.getText());
						double discount = Double.valueOf(textField_3.getText());
						discountamount = subtot * discount / 100;
						label_9.setText(String.valueOf(discountamount));

						// btnNewButton.requestFocusInWindow();
					} else {

						JOptionPane.showMessageDialog(null, "Please! Enter valid discount!!");
						textField_7.setText("");
					}
				} catch (Exception f) {
				}
			}
		});
		textField_3.setBounds(103, 34, 103, 29);
		panel_5.add(textField_3);

		textField_3.setColumns(10);

		JLabel lblBillDiscount = new JLabel("Bill Discount");
		lblBillDiscount.setBounds(10, 34, 90, 20);
		panel_5.add(lblBillDiscount);
		lblBillDiscount.setFont(new Font("Calibri", Font.PLAIN, 13));

		label_9 = new JLabel("0.00");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(214, 37, 46, 14);
		panel_5.add(label_9);

		JLabel label_12 = new JLabel("%");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(203, 38, 22, 14);
		panel_5.add(label_12);

		comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] { "Cash", "Card", "Online" }));
		comboBox_4.setBounds(101, 75, 161, 29);
		panel_5.add(comboBox_4);

		JLabel lblPaymentMode = new JLabel("<html><cetner>Payment Mode <br><center>By</center></html>");
		lblPaymentMode.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPaymentMode.setBounds(8, 72, 90, 33);
		panel_5.add(lblPaymentMode);

		textField_13 = new JTextField();
		textField_13.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql = textField_13.getText().toUpperCase();
				Filter(sql);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					btnNewButton_1.requestFocusInWindow();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (Character.isLowerCase(keyChar)) {
					e.setKeyChar(Character.toUpperCase(keyChar));
				}
			}
		});
		textField_13.setBounds(991, 26, 349, 36);
		contentPanel.add(textField_13);
		textField_13.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("List of Table Booked");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(114, 495, 162, 21);
		contentPanel.add(lblNewLabel_1);
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					btnCancelATable.requestFocusInWindow();
				}
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setForeground(Color.BLACK);

		btnNewButton_1.setToolTipText("Reserved the table");
		btnNewButton_1.setBackground(Color.WHITE);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboBox_2.getSelectedItem() == null || comboBox_2.getSelectedItem().equals("SELECT")) {
						comboBox_2.requestFocusInWindow();
						JOptionPane.showMessageDialog(null, "Select Waiter Name");
					} else if (comboBox_1.getSelectedItem() == null || comboBox_1.getSelectedItem().equals("SELECT")) {
						JOptionPane.showMessageDialog(null, "Select Table Number");
						comboBox_1.requestFocusInWindow();

					}

					else if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Please Add items First!! ");

					}

					else {
					Double subtotal = 0.0;
					if (model != null) {
						for (int i = 0; i < model.getRowCount(); i++) {

							amount = Double.parseDouble((String) model.getValueAt(i, 4));

							subtotal = subtotal + amount;

						}
					}
					listamount = Double.toString(subtotal);
					name = textField_1.getSelectedItem().toString();
					contact = textField_5.getText();

					

						savetime = now();
						savedate = nowdate();

						Kot_id = lblKotidnumber.getText();
						String waiter = (String) comboBox_2.getSelectedItem();
						waiter_name = waiter.toUpperCase();
						cut = label_13.getText().toUpperCase();
						// items list
						bill = label_13.getText().toUpperCase();
						//
						customer_name = textField_1.getSelectedItem().toString().toUpperCase();

						cust_mob = textField_5.getText().toUpperCase();

						customerwishes = textField_14.getSelectedItem().toString();

						occasion_date = ((JTextField) textArea.getDateEditor().getUiComponent()).getText();

						detail = (String) comboBox_1.getSelectedItem();

						DefaultTableModel model = (DefaultTableModel) table.getModel();
						int nRow = model.getRowCount(), nCol = model.getColumnCount();
						String curEntry = (String) comboBox.getSelectedItem();
						tableData = new String[nRow][nCol];

						ArrayList<String> examList = new ArrayList<String>();

						for (int i = 0; i < nRow; i++) {
							for (int j = 1; j < nCol; j++) {
								tableData[i][j] = (String) model.getValueAt(i, j);

							}
						}
						String billmerge = "INVOICE/".concat(bill);

						dbcon.connect();

						for (int i = 0; i < nRow; i++) {
							String sql1 = "Insert into bill_list(bill_no,item_name,unit_price,quantity,amount) VALUES('"
									+ billmerge + "','" + tableData[i][1].concat("->" + tableData[i][5].toUpperCase())
									+ "','" + tableData[i][2] + "','" + tableData[i][3] + "','" + tableData[i][4]
									+ "')";

							dbcon.pst = dbcon.conn.prepareStatement(sql1);
							dbcon.pst.executeUpdate();

						}
						dbcon.conn.close();

						String kotid_format = "KOTID/";
						lblKotidnumber_convert_to_string = String.valueOf(Kot_id);
						koitdmerge = kotid_format + lblKotidnumber_convert_to_string;

						Object[] row = { detail, billmerge, koitdmerge, customerwishes, customer_name, occasion_date,
								cust_mob, listamount, savetime, savedate, waiter_name };

						model = (DefaultTableModel) table_1.getModel();

						model.addRow(row);

						amountconversion = String.valueOf(amount);

						subtotal = 0.00;

						int comboindex = comboBox_1.getSelectedIndex();

						if (comboBox_1.getSelectedItem().equals("PARCEL")
								|| comboBox_1.getSelectedItem().equals("HOME DELIVERY")) {
						} else {
							comboBox_1.removeItemAt(comboindex);
						}
						JOptionPane.showMessageDialog(null, "Congratulations! Table is booked");

						dbcon.insertuniqueid_forkotid(Kot_id);
						dbcon.insertuniqueid(bill);

//						JOptionPane.showMessageDialog(null,Kot_id);
						printfunction4kot(detail, koitdmerge, savetime, savedate, waiter_name, nRow, billmerge);

						clearall();
						set_max_function_for_KOTID();
						serialno = 1;
//						JOptionPane.showMessageDialog(null,Kot_id);

					}

				} catch (SQLException e1) {
//					"Invoice Already Exists! Please Click New Bill"
					JOptionPane.showMessageDialog(null, e1);
				}
				// clearall();

			}

		});
		btnNewButton_1.setMnemonic(KeyEvent.VK_R);
		btnNewButton_1.setBounds(450, 470, 208, 51);
		contentPanel.add(btnNewButton_1);

		JPanel panel_9 = new JPanel();
		panel_9.setBounds(952, 64, 388, 148);
		contentPanel.add(panel_9);
		panel_9.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 390, 148);
		panel_9.add(scrollPane_2);

		table_2 = new JTable();

		table_2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Code", "Name", "Rate", "Quantity", "Amount", "Category" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(0).setPreferredWidth(63);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(167);
		table_2.getColumnModel().getColumn(1).setMinWidth(167);
		table_2.getColumnModel().getColumn(1).setMaxWidth(167);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(83);
		table_2.getColumnModel().getColumn(4).setPreferredWidth(84);
		table_2.getColumnModel().getColumn(4).setMaxWidth(84);
		table_2.getColumnModel().getColumn(5).setPreferredWidth(152);
		table_2.getColumnModel().getColumn(5).setMinWidth(152);
		Showmenutable();
		scrollPane_2.setViewportView(table_2);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_10.setBackground(Color.WHITE);
		panel_10.setBounds(399, 61, 543, 175);
		contentPanel.add(panel_10);
		panel_10.setLayout(null);

		comboBox = new JComboBox();

		comboBox.setEditable(true);
		AutoCompleteDecorator.decorate(comboBox);
		comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent event) {
				if (event.getKeyChar() == KeyEvent.VK_ENTER || event.getKeyChar() == KeyEvent.VK_TAB) {

					textField_7.requestFocusInWindow();

				}
			}
		});

//		comboBox.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
////				textField_7
//				
//			}
//		});

		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SELECT" }));

		Showproductitem();

		comboBox.setSelectedIndex(0);
		productlist();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					int i = 0;

					String s = (String) comboBox.getSelectedItem();
					// JOptionPane.showMessageDialog(null, "YES!!!!!!!!!!");

					for (i = 0; i < Array.length; i++) {
						if (s.equals(Array[i])) {
							Double d = Double.valueOf(Arraycombo[i]);
							textField_8.setText(String.valueOf(d));

						}

					}
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f);
				}
			}

		});

		comboBox.setBounds(113, 7, 420, 34);
		panel_10.add(comboBox);

		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductName.setBounds(10, 15, 107, 14);
		panel_10.add(lblProductName);

		btnAdd = new JButton("<html><center>ADD<br>item<br>to<br>Cart<br>[Alt+A]</center></html> ");
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton_1.requestFocus();
				}
			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setMnemonic(KeyEvent.VK_A);

		btnAdd.setBounds(422, 44, 100, 86);
		panel_10.add(btnAdd);
		btnAdd.setToolTipText("Add items to particulars");
		btnAdd.setBackground(Color.WHITE);

		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(165, 74, 222, 28);
		panel_10.add(textField_8);
		textField_8.setColumns(10);

		textField_7 = new JTextField("");
		textField_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB) {
					textArea_1.requestFocus();
				}
			}
		});
		textField_7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					String expression = "[0-9]*";
					String inputStr = textField_7.getText();
					Pattern pattern = Pattern.compile(expression);
					Pattern.compile(expression);
					Matcher matcher = pattern.matcher(inputStr);

					if (matcher.matches()) {

						Double a = Double.valueOf(textField_8.getText());
						Double b = Double.valueOf(textField_7.getText());

						Double sum = a * b;
						textField_9.setText(String.valueOf(sum));
					} else {

//						JOptionPane.showMessageDialog(null, "Please! Enter quantity");
						textField_7.setText("");
					}

				} catch (Exception z) {
					// TODO: handle exception
				}
			}
		});

		textField_7.setBounds(165, 45, 222, 28);
		panel_10.add(textField_7);
		textField_7.setColumns(10);

		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(35, 82, 54, 14);
		panel_10.add(lblUnitPrice);
		lblUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(35, 52, 54, 14);
		panel_10.add(lblQuantity);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));

		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(165, 103, 222, 28);
		panel_10.add(textField_9);

		JLabel lblTime = new JLabel("Total Amount");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTime.setBounds(35, 112, 82, 14);
		panel_10.add(lblTime);

		textArea_1 = new JTextField();
		textArea_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea_1.setBackground(new Color(250, 250, 210));
		textArea_1.setBounds(102, 137, 431, 27);
		panel_10.add(textArea_1);

		JLabel lblComments = new JLabel("Comments:");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComments.setBounds(35, 142, 82, 14);
		panel_10.add(lblComments);

		btnCancelATable = new JButton("<html>Cancel A Table<br> &nbsp &nbsp[ Alt &nbsp + &nbsp C ]</html>");
		btnCancelATable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				comboBox_3
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					comboBox_3.requestFocusInWindow();
				}
			}
		});
		btnCancelATable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelATable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelATable.setForeground(Color.BLACK);
		btnCancelATable.setMnemonic(KeyEvent.VK_C);
		btnCancelATable.setBounds(688, 470, 208, 51);
		contentPanel.add(btnCancelATable);
		btnCancelATable.setSelected(true);
		btnCancelATable.setToolTipText("Cancel the existing table");
		btnCancelATable.setBackground(Color.WHITE);

		panel_11 = new JPanel();
		panel_11.setBackground(new Color(255, 248, 220));
		panel_11.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, Color.GRAY, Color.GRAY));

		panel_11.add(dateLable);
		panel_11.add(timeLable);
		panel_11.add(dayLable);

		panel_11.setBounds(1060, 234, 290, 67);
		contentPanel.add(panel_11);

		JLabel label_14 = new JLabel("Billing Information");
		label_14.setBounds(1083, 312, 258, 14);
		contentPanel.add(label_14);
		label_14.setOpaque(true);
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Calibri", Font.PLAIN, 14));
		label_14.setBackground(SystemColor.inactiveCaptionBorder);

		JLabel label_5 = new JLabel("");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setIcon(new ImageIcon(Rmsbook.class.getResource("/hms/images/welcome - Copy.png")));
		label_5.setBounds(958, 540, 95, 145);
		contentPanel.add(label_5);

		btnNewButton = new JButton("<html>Pay Now <br>  [Alt+P]</html>");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				btnNewBill
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					btnNewBill.requestFocusInWindow();
				}
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setMnemonic(KeyEvent.VK_P);
		btnNewButton.setBounds(1074, 604, 126, 67);
		contentPanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel models = (DefaultTableModel) table_1.getModel();

					int SelectedRowIndex = table_1.getSelectedRow();
					String billprint = models.getValueAt(SelectedRowIndex, 1).toString();

					if (table_1.getSelectionModel().isSelectionEmpty()) {

						JOptionPane.showMessageDialog(null,
								"Please Select Booked Table Details/Invoice No. *** to Pay");
					} else if (comboBox_3.getSelectedItem() == null || comboBox_3.getSelectedItem().equals("SELECT")) {

						comboBox_3.requestFocusInWindow();
						JOptionPane.showMessageDialog(null, "Please Select GST Tax Rate");

					} else {
						String message = " Are You Really wants to SUBMIT ? ";
						String title = "SUBMIT";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						Double bill_discount = 0.0;

						bill_discount = Double.valueOf(textField_3.getText());
						double bill_discount_roundoff = Math.round(bill_discount * 100.0) / 100.0;
						if (reply == JOptionPane.YES_OPTION) {

							Double sum = 0.0;

							Double subtotal = Double.valueOf(textField_4.getText());
							double subtotal_roundoff = Math.round(subtotal * 100.0) / 100.0;

							String combobox_data = (String) comboBox_3.getSelectedItem();

							Double gst = Double.valueOf(combobox_data);

							Double discount = subtotal_roundoff * (bill_discount_roundoff / 100);
							double discount_roundoff = Math.round(discount * 100.0) / 100.0;
							label_9.setText(String.valueOf(discount_roundoff));

							// grandtotal
							sum = subtotal_roundoff - discount_roundoff;
							double sum_roundoff = Math.round(sum * 100.0) / 100.0;

							Double gstcaculation = sum_roundoff * gst / 100;
							double gstcaculation_roundoff = Math.round(gstcaculation * 100.0) / 100.0;

							double cgst_sgst = gstcaculation_roundoff / 2;
							double cgst_sgst_roundoff = Math.round(cgst_sgst * 100.0) / 100.0;

							label_10.setText(String.valueOf(gstcaculation_roundoff));
							label_7.setText(String.valueOf(cgst_sgst_roundoff));
							label_8.setText(String.valueOf(cgst_sgst_roundoff));

							Double grandtotal = sum_roundoff + gstcaculation_roundoff;
							double grandtotal_roundoff = Math.round(grandtotal * 100.0) / 100.0;
							textField_2.setText(String.valueOf(grandtotal_roundoff));

							String discounttype = String.valueOf(discount_roundoff);
							String subtotaltype = String.valueOf(subtotal_roundoff);
							String cgsttype = String.valueOf(cgst_sgst_roundoff);
							String sgsttype = String.valueOf(cgst_sgst_roundoff);
							String grandtotaltype = String.valueOf(grandtotal_roundoff);
							String paymentmode = comboBox_4.getSelectedItem().toString();
							String datebill = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
//							JOptionPane.showMessageDialog(null, "A");

							dbcon.cust_detailentry(billprint, customer_name, cust_mob, customerwishes, occasion_date,
									waiter_name, datebill, savetime, subtotaltype, cgsttype, sgsttype, discounttype,
									grandtotaltype, lblNewLabel_4.getText().substring(21), paymentmode);
//							JOptionPane.showMessageDialog(null, "B");
							// second table data

							DefaultTableModel model = (DefaultTableModel) table.getModel();
							int nRow = model.getRowCount(), nCol = model.getColumnCount();

							String[][] tableDatanew = new String[nRow][nCol];

//							ArrayList<String> examList = new ArrayList<String>();

							for (int i = 0; i < nRow; i++) {
								for (int j = 1; j < nCol; j++) {
									tableDatanew[i][j] = (String) model.getValueAt(i, j);
								}
							}

							dbcon.connect();

							for (int i = 0; i < nRow; i++) {

								String sql1 = "Insert into tablecustomerdetail_itemlist VALUES('" + billprint + "','"
										+ customer_name + "', '" + cust_mob + "','" + customerwishes + "','"
										+ occasion_date + "','" + waiter_name + "','" + savedate + "','" + savetime
										+ "','" + subtotaltype + "','" + cgsttype + "','" + sgsttype + "','"
										+ discounttype + "','" + grandtotaltype + "','" + tableDatanew[i][1] + "','"
										+ tableDatanew[i][2] + "','" + tableDatanew[i][3] + "','" + tableDatanew[i][4]
										+ "','" + lblNewLabel_4.getText().substring(21) + "')";

								dbcon.pst = dbcon.conn.prepareStatement(sql1);
								dbcon.pst.executeUpdate();

							}
							dbcon.conn.close();

							DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();

							String tablenumber_addafter_bill_paid = table_1.getValueAt(table_1.getSelectedRow(), 0)
									.toString();

							comboBox_1.addItem(tablenumber_addafter_bill_paid);
//							JOptionPane.showMessageDialog(null, tablenumber_addafter_bill_paid);

							model1.removeRow(table_1.getSelectedRow());

							printfunctionworking(billprint);

							clearall();

						}
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}

			}
		});
		btnNewButton.setToolTipText("Pay Now");
		btnNewButton.setBackground(SystemColor.inactiveCaption);

		btnNewBill = new JButton("<html>New Bill<br> [Alt+N]</html>");
		btnNewBill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewBill.setMnemonic(KeyEvent.VK_N);
		btnNewBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = " Are You sure? ";
				String title = "NEW BILL";
				try {
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {

						btnNewButton.setEnabled(true);
//						btnAddToBill.setEnabled(false);
						String t = label_13.getText();
						clearall();
						label_10.setText("0.00");
						label_7.setText("0.00");
						label_8.setText("0.00");

						label_13.setText(t);
						set_max_function();
						set_max_function_for_KOTID();
						btnNewButton_1.setVisible(true);
						btnNewButton_2.setVisible(false);
//						DefaultTableModel modelclear = (DefaultTableModel) table_1.getModel();
//						modelclear.setRowCount(0);

//						DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
//						tablemodel.setRowCount(0);

//						btnCancelATable.setEnabled(false);

					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong,please Call Expert:" + e1);
				}
				// DefaultTableModel models = (DefaultTableModel) table_1.getModel();
				// models.setRowCount(0);
			}
		});
		btnNewBill.setToolTipText("Add to Main bill");
		btnNewBill.setBackground(SystemColor.inactiveCaption);
		btnNewBill.setBounds(1210, 603, 126, 68);
		contentPanel.add(btnNewBill);

		btnNewButton_2 = new JButton("UPDATE BILL");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.setVisible(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
					int selectedRow = table_1.getSelectedRow();

//					JOptionPane.showMessageDialog(null, selectedRow);

					model1.removeRow(selectedRow);

					DefaultTableModel model = (DefaultTableModel) table.getModel();

					String billmerge = "INVOICE/".concat(label_13.getText());

					dbcon.deleteolddetail(billmerge);

					String Kot_id = "KOTID/".concat(lblKotidnumber.getText());

					String waiter = comboBox_2.getSelectedItem().toString();

					customer_name = textField_1.getSelectedItem().toString().toUpperCase();
					cust_mob = textField_5.getText().toUpperCase();
					customerwishes = textField_14.getSelectedItem().toString();
					occasion_date = ((JTextField) textArea.getDateEditor().getUiComponent()).getText();
					String tablename = comboBox_1.getSelectedItem().toString();

					//

					int nRow = model.getRowCount(), nCol = model.getColumnCount();
					tableData = new String[nRow][nCol];

					for (int i = 0; i < nRow; i++) {
						for (int j = 1; j < nCol; j++) {
							tableData[i][j] = (String) model.getValueAt(i, j);

						}
					}

					Double subtotal = 0.0;
					for (int i = 0; i < model.getRowCount(); i++) {

						amount = Double.parseDouble((String) model.getValueAt(i, 4));

						subtotal = subtotal + amount;

						listamount = Double.toString(subtotal);
					}

					savetime = now();
					savedate = nowdate();

					dbcon.connect();
					for (int i = 0; i < nRow; i++) {

						String sql1 = "Insert into bill_list(bill_no,item_name,unit_price,quantity,amount) VALUES('"
								+ billmerge + "','" + tableData[i][1] + "','" + tableData[i][2] + "','"
								+ tableData[i][3] + "','" + tableData[i][4] + "')";

						dbcon.pst = dbcon.conn.prepareStatement(sql1);
						dbcon.pst.executeUpdate();
						// JOptionPane.showMessageDialog(null, tableData[i][j]);

					}
					dbcon.conn.close();

					model.setRowCount(0);
					Object[] row = { tablenameupdate, billmerge, Kot_id, customerwishes, customer_name, occasion_date,
							cust_mob, listamount, savetime, savedate, waiter };
					model = (DefaultTableModel) table_1.getModel();

					model.addRow(row);
//					JOptionPane.showMessageDialog(null, "UPDATED");
					printfunction4kot(tablenameupdate, Kot_id, savetime, savedate, waiter, nRow, billmerge);

					clearall();
					btnNewButton_2.setVisible(false);
					btnNewButton_1.setVisible(true);
					int comboindex = comboBox_1.getSelectedIndex();
					comboBox_1.removeItemAt(comboindex);

					JOptionPane.showMessageDialog(null, "Congratualations! updated successfully");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				} finally {
					try {
						dbcon.conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					}
				}
			}

		});
		btnNewButton_2.setBounds(450, 470, 208, 51);
		contentPanel.add(btnNewButton_2);

		comboBox.setNextFocusableComponent(textField_7);
		textField_7.setNextFocusableComponent(textArea_1);
		textArea_1.setNextFocusableComponent(btnAdd);
		btnAdd.setNextFocusableComponent(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Rmsbook.class.getResource("/hms/images/Zoom-icon2.png")));
		lblNewLabel.setBounds(951, 27, 46, 35);
		contentPanel.add(lblNewLabel);
		btnCancelATable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRowIndex = 0;
					selectedRowIndex = table_1.getRowCount();
					if (selectedRowIndex == 0) {

						JOptionPane.showMessageDialog(null, "Please Select Bill to Cancel");
					} else {
						DefaultTableModel modeltoadd = (DefaultTableModel) table_1.getModel();
						int row = table_1.getSelectedRow();
						String TableClick = table_1.getValueAt(row, 0).toString();
						comboBox_1.addItem(TableClick);
						// ------------------------------------------------------------------------------------
						String str = table_1.getValueAt(row, 1).toString();
						String str1 = table_1.getValueAt(row, 2).toString();
						billbreakdelete = str.substring(8);
						kotbreakdelete = str1.substring(6);

						dbcon.deleteuniqueid(billbreakdelete);
						dbcon.deleteuniqueid_forkotid(kotbreakdelete);
						dbcon.delete_billitemlist(str);

						// ------------------------------------------------------------------------------------

						btnCancelATable.setEnabled(true);
						DefaultTableModel model = (DefaultTableModel) table_1.getModel();

						int SelectedRowIndex = table_1.getSelectedRow();
						model.removeRow(SelectedRowIndex);
						clearall();
						set_max_function();
						set_max_function_for_KOTID();

					}

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f);
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {

					String expression = "[0-9]*";
					String inputStr = textField_7.getText();
					Pattern pattern = Pattern.compile(expression);
					Pattern.compile(expression);
					Matcher matcher = pattern.matcher(inputStr);

					String a = "0";
					a = textField_7.getText();
					if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("SELECT")) {
						comboBox.requestFocusInWindow();
						JOptionPane.showMessageDialog(null, "Select Product");

					}

					else if (a.length() == 0 || a.equals("0")) {
						JOptionPane.showMessageDialog(null, "Please Enter a quantity");
					}

					else if (matcher.matches()) {

						String itemname = (String) comboBox.getSelectedItem();
						String unitprice = textField_8.getText();
						String quantity = textField_7.getText();
						String totalamount = textField_9.getText();
						String Comments = textArea_1.getText();

						Object[] row = { serialno, itemname, unitprice, quantity, totalamount, Comments };

						model = (DefaultTableModel) table.getModel();
						model.addRow(row);
						textField_7.setText("");
						textField_8.setText("");

						textField_9.setText("");
						textArea_1.setText("");
						comboBox.setSelectedIndex(0);
						// JOptionPane.showMessageDialog(null, "Iem Added");
						serialno++;

					} else {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please Enter a Quantity");
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);

				}
			}

		});

	}

	public void setuser(String current_user) {
		// TODO Auto-generated method stub
		lblNewLabel_4.setText(current_user);

	}

	public void ShowTable() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st.executeQuery("Select tablename from tablelist order by tablename asc");
			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				comboBox_1.addItem(data);

			}
			comboBox_1.setSelectedItem(null);
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

	}

	public void TableSelection() throws SQLException {
		try {
			dbcon.connect();
			model = (DefaultTableModel) table_1.getModel();
			int row = table_1.getSelectedRow();
			String TableClick = table_1.getValueAt(row, 0).toString();

			String sql = "Select bill,customer_id,customer_name,customer_add,customer_mobile from tablecustomerdetail where customer_bill='"
					+ TableClick + "'";

			// JOptionPane.showMessageDialog(null, "asdasd");

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {

				String add0 = dbcon.rs.getString("customer_bill");
				label_13.setText(add0);
				String add1 = dbcon.rs.getString("customer_id");
				textField_14.setSelectedItem(add1);
//				textField_14.setText(add1);

				String add2 = dbcon.rs.getString("customer_name");
				textField_1.setSelectedItem(add2);

				String add3 = dbcon.rs.getString("customer_add");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				Date startDate = df.parse(add3);
				textArea.setDate(startDate);
//				textArea.setText(add3);

				String add4 = dbcon.rs.getString("customer_mobile");
				textField_5.setText(add4);
				/*
				 * 
				 */

			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}

	}

	public void Filter(String sql) {
		model = (DefaultTableModel) table_2.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table_2.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(sql));
	}

	public void clearall() throws SQLException {

		textField_14.setSelectedIndex(0);
		textField_1.setSelectedItem(null);
		textArea.setDate(new Date());
		textField_5.setText("");
		comboBox.setSelectedIndex(0);
		comboBox_1.setSelectedIndex(0);
		comboBox_2.setSelectedIndex(0);
		textField_8.setText("");
		textField_9.setText("");
		textField_7.setText("");
		textField_3.setText("");
		textField_2.setText("");
		textField_4.setText("");
		comboBox_3.setSelectedIndex(0);
		label_10.setText("0.0");
		label_7.setText("0.0");
		label_8.setText("0.0");
		textField_13.setText("");
		model.setRowCount(0);
		set_max_function_for_KOTID();
		set_max_function();
		label_9.setText("0.0");
		textArea.setEnabled(false);
		textField_14.setEnabled(false);
		btnNewButton_2.setVisible(false);
		btnNewButton_1.setVisible(true);
		textArea_1.setText("");

	}

	public void Showmenutable() throws SQLException {
		try {
			dbcon.connect();
			String sql = "Select * from menuitem order by Name asc ";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();

			model.setRowCount(0);
			table_2.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}

	public void Showstafflist() throws SQLException {
		try {

			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st.executeQuery(
					"Select staffname from staffdetails  WHERE staffdesignation='COUNTER SERVER' order by staffname asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				comboBox_2.addItem(data);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "CONTACT SUPPORT TEAM!");
		} finally {
			dbcon.conn.close();
		}
	}

	public void select_itemrow(String abc) throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			String sql = "Select * from menuitem where Code='" + abc + "'";
			dbcon.rs = dbcon.st.executeQuery(sql);
			while (dbcon.rs.next()) {
				String text1 = dbcon.rs.getString(1);
				String text2 = dbcon.rs.getString(2);
				String text3 = dbcon.rs.getString(4);
				String text4 = dbcon.rs.getString(6);
				String text5 = dbcon.rs.getString(5);
				String text6 = dbcon.rs.getString(7);
				String text7 = dbcon.rs.getString(7);

				model = (DefaultTableModel) table.getModel();
				Object[] row = { text1, text2, text3, text4, text5, text6, text7 };

				model.addRow(row);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.conn.close();
		}

	}

	public void ShowTax() throws SQLException {
		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st.executeQuery("Select gst from gstlist order by gst asc");
			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				comboBox_3.addItem(data);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.conn.close();
		}
	}

	public void setenabledfalse(JButton enable) {
		// setting the firstName variable text
		this.enable = enable;
		enable.setEnabled(false);
	}

	public void setenabledtrue(JButton enable) {
		// setting the firstName variable text
		this.enable = enable;
		enable.setEnabled(true);
	}

	public void Showproductitem() throws SQLException {
		try {
			dbcon.connect();
			String sql = "Select Name from menuitem order by Name asc";
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("Name");
				comboBox.addItem(data);

			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}

	public static String nowdate() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(DateFormat);
		return format.format(cal.getTime());

	}

	public static String now() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(TimeFormat);
		return format.format(cal.getTime());

	}

	public void set_max_function_for_KOTID() throws SQLException {
		try {
			dbcon.connect();
			// Select max(`Enquiry No`) as max from enquiry
			String query1 = "Select max(idgenerator) as max from Uniqueid4kotid";
			dbcon.pst = dbcon.conn.prepareStatement(query1);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				int num = dbcon.rs.getInt("max");
				int inc = num + 1;
				lblKotidnumber.setText(String.valueOf(inc));
//				JOptionPane.showMessageDialog(null, inc);
				// JOptionPane.showMessageDialog(null, inc);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, f);
		} finally {
			dbcon.conn.close();
		}
	}

	public void set_max_function() throws SQLException {
		try {
			dbcon.connect();
			// Select max(`Enquiry No`) as max from enquiry
			String query1 = "Select max(idgenerator) as max from Uniqueid";
			dbcon.pst = dbcon.conn.prepareStatement(query1);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				int num = dbcon.rs.getInt("max");
				int inc = num + 1;
				label_13.setText(String.valueOf(inc));
				// JOptionPane.showMessageDialog(null, inc);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, f);
		} finally {
			dbcon.conn.close();
		}
	}

	public void productlist() throws SQLException {
		try {
			dbcon.connect();
			int i = 0;
			Array = new String[1000];

			Arraycombo = new String[1000];

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select Name,Price from menuitem");

			while (dbcon.rs.next()) {

				Array[i] = dbcon.rs.getString("Name");

				Arraycombo[i] = dbcon.rs.getString("Price");

				i++;

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.conn.close();
		}

	}

//	public void demoprint() {
//
//		demoprint partymaster = new demoprint();
//		partymaster.toFront();
//		partymaster.setModal(true);
//		partymaster.setVisible(true);
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

//	public void showtheitems(String finalsave) throws SQLException {
//		try {
//			DefaultTableModel table_model = (DefaultTableModel) table.getModel();
//
//			dbcon.connect();
//			String sql = "Select item_name,unit_price,quantity,amount from bill_list where bill_no='" + finalsave + "'";
//			dbcon.pst = dbcon.conn.prepareStatement(sql);
//			dbcon.rs = dbcon.pst.executeQuery();
//			int i = 1;
//			
//			
//			while (dbcon.rs.next()) {
//				String item_name_withcomment = dbcon.rs.getString("item_name");
//				String[] arrOfStr = item_name_withcomment.split("->");
//			
//				String dash = "->";
//				String[] temp;
//				temp = item_name_withcomment.split(dash);
//				String item_name = temp[0];
//				String comments = temp[1];
////				JOptionPane.showMessageDialog(null, item_name);
////				JOptionPane.showMessageDialog(null, comments);
//				String unit_price = dbcon.rs.getString("unit_price");
//				String quantity = dbcon.rs.getString("quantity");
//				String amount = dbcon.rs.getString("amount");
//
//				Object[] row = { i, item_name, unit_price, quantity, amount, comments };
////				JOptionPane.showMessageDialog(null, item_name);
//				table_model.addRow(row);
//				i++;
//			}
//		} catch (Exception e) {
////			JOptionPane.showMessageDialog(null, e);
//		} finally {
//			dbcon.conn.close();
//		}
//	}

	public void showtheitem(String finalsave) throws SQLException {
		try {
			DefaultTableModel table_model = (DefaultTableModel) table.getModel();

			dbcon.connect();
			String sql = "Select item_name,unit_price,quantity,amount from bill_list where bill_no='" + finalsave + "'";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			int i = 1;
			while (dbcon.rs.next()) {
				String item_name = dbcon.rs.getString("item_name");
				String[] item_namesplit = item_name.split("->");
				String dash = "->";
//				JOptionPane.showMessageDialog(null, item_namesplit);

//				if(item_namesplit[1].equals(""))
//				{
//					item_namesplit[1]="na";
//				}
//				JOptionPane.showMessageDialog(null, item_namesplit[0]);
//				JOptionPane.showMessageDialog(null, item_namesplit[1]);

				String unit_price = dbcon.rs.getString("unit_price");
				String quantity = dbcon.rs.getString("quantity");
				String amount = dbcon.rs.getString("amount");

				Object[] row = { i, item_namesplit[0], unit_price, quantity, amount };
//				JOptionPane.showMessageDialog(null, item_name);
				table_model.addRow(row);
				i++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}

	public void printfunctionworking(String invoice) throws SQLException {
		try {
			dbcon.connect();

			InputStream file = getClass().getResourceAsStream("/restrauntbill4foodfactory.jrxml");

			JasperDesign jd = JRXmlLoader.load(file);

			// JasperDesign jd =
			// JRXmlLoader.load("E:\\workspace\\Hotel_Management\\src\\restrauntbill.jrxml");
			String sql = "select * from tablecustomerdetail_itemlist WHERE Invoice_Number='" + invoice + "'";
			JRDesignQuery newquery = new JRDesignQuery();
			newquery.setText(sql);
			jd.setQuery(newquery);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, dbcon.conn);
			JasperViewer.viewReport(jp, false);
//			JasperPrintManager.printReport(jp, false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}

	public void printfunction4kot(String tableno, String kotnumber, String savetime, String savedate,
			String waiter_name, int totalitems, String bl) throws SQLException {
		try {
			dbcon.connect();
			HashMap params = new HashMap();
			params.put("tablenumber", tableno);
			params.put("kotid", kotnumber);
			params.put("savetime", savetime);
			params.put("savedate", savedate);
			params.put("waitername", waiter_name);
			params.put("totalitems", totalitems);

			///////////////////////////////////////////////////////////////////////////////////////
			InputStream file = getClass().getResourceAsStream("/restkot4foodfactory.jrxml");

			JasperDesign jd = JRXmlLoader.load(file);

//				JasperDesign jd = JRXmlLoader.load("D:\\workspace\\Hotel_Management\\src\\restkot.jrxml");
			String sql = "select * from bill_list WHERE bill_no='" + bl + "'";
			JRDesignQuery newquery = new JRDesignQuery();
			newquery.setText(sql);
			jd.setQuery(newquery);

			JasperReport jr = JasperCompileManager.compileReport(jd);
			JasperPrint jp = JasperFillManager.fillReport(jr, params, dbcon.conn);
			JasperViewer.viewReport(jp, false);

//			JasperPrintManager.printReport(jp, false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}

	public void CustomerNameList() throws SQLException {
		try {

			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();

			dbcon.rs = dbcon.st
					.executeQuery("Select DISTINCT Customer_Name from tablecustomerdetail  order by Customer_Name asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString(1);
				textField_1.addItem(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "CONTACT SUPPORT TEAM!" + e);
		} finally {
			dbcon.conn.close();
		}
	}
}
