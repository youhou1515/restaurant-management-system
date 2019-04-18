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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class show_advancebookingdetails extends JFrame {

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
	private JTextArea textArea;

	public show_advancebookingdetails() {
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
		panel.setBounds(10, 151, 810, 484);
		contentPanel.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 810, 484);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				model = (DefaultTableModel) table.getModel();
				int x = table.getSelectedRow();
				String slino = model.getValueAt(x, 0).toString();
				
					showtheitems(slino);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {

					DefaultTableModel model1 = (DefaultTableModel) table.getModel();


					int x = table.getSelectedRow();
					String slipno = model1.getValueAt(x, 0).toString();


					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
						String message = "Do you want to Delete ? ";
						String title = "CONFIRM";

						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

//						JOptionPane.showMessageDialog(null, item);
							model1.removeRow(x);
							dbcon.advancebookingdelete(slipno);
							
//							model1.setRowCount(0);
//							billamountshowtoday();			
						}
					}
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f);
				}
			}
		});

		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Bill Number", "Customer ID", "Name",
				"Aadhar No.", "Mobile No.", "New column", "New column", "New column", "New column" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(0).setMaxWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(113);
		table.getColumnModel().getColumn(1).setMaxWidth(113);
		table.getColumnModel().getColumn(2).setPreferredWidth(229);
		table.getColumnModel().getColumn(2).setMaxWidth(229);
		table.getColumnModel().getColumn(3).setPreferredWidth(121);
		table.getColumnModel().getColumn(3).setMaxWidth(121);
		table.getColumnModel().getColumn(4).setPreferredWidth(98);
		table.getColumnModel().getColumn(4).setMaxWidth(100);
//		try {
//			showtablebill();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		scrollPane.setViewportView(table);

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
		textField.setBounds(70, 110, 1012, 31);
		contentPanel.add(textField);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(show_advancebookingdetails.class.getResource("/hms/images/Zoom-icon2.png")));
		lblSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSearch.setBounds(10, 96, 1142, 56);
		contentPanel.add(lblSearch);

		JLabel lblShowAllVehicle = new JLabel("Advance Booking Records");
		lblShowAllVehicle.setOpaque(true);
		lblShowAllVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowAllVehicle.setForeground(SystemColor.activeCaptionText);
		lblShowAllVehicle.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblShowAllVehicle.setBackground(Color.WHITE);
		lblShowAllVehicle.setBounds(0, 0, 1092, 31);
		contentPanel.add(lblShowAllVehicle);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setDate(new Date());
//		dateChooser_2.setMinSelectableDate(new Date());
		dateChooser.setBounds(716, 42, 157, 20);
		contentPanel.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		dateChooser_1.setDate(new Date());
//		dateChooser_3.setMinSelectableDate(new Date());
		dateChooser_1.setBounds(925, 42, 157, 20);
		contentPanel.add(dateChooser_1);

		JLabel label = new JLabel("-TO-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(879, 42, 35, 20);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("Search Between Dates");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(541, 58, 150, 14);
		contentPanel.add(label_1);

		JButton button = new JButton("Click to Search");
		button.setForeground(Color.WHITE);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();

					datequery(data13, data14);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}

			}
		});
		button.setBackground(Color.GRAY);
		button.setBounds(716, 68, 366, 31);
		contentPanel.add(button);

		JLabel lblTotalAmount = new JLabel("Total Paid:");
		lblTotalAmount.setForeground(Color.WHITE);
		lblTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotalAmount.setBounds(573, 670, 74, 14);
		contentPanel.add(lblTotalAmount);

		label_2 = new JLabel("0.00");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(636, 665, 139, 20);
		contentPanel.add(label_2);
		
		JLabel lblTotalCost = new JLabel("Total Cost:");
		lblTotalCost.setForeground(Color.WHITE);
		lblTotalCost.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotalCost.setBounds(10, 669, 74, 14);
		contentPanel.add(lblTotalCost);
		
		label_3 = new JLabel("0.00");
		label_3.setInheritsPopupMenu(false);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(74, 665, 119, 20);
		contentPanel.add(label_3);
		
		JLabel lblAdvance = new JLabel("Advance:");
		lblAdvance.setForeground(Color.WHITE);
		lblAdvance.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAdvance.setBounds(187, 669, 74, 14);
		contentPanel.add(lblAdvance);
		
		label_4 = new JLabel("0.00");
		label_4.setInheritsPopupMenu(false);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(253, 664, 139, 20);
		contentPanel.add(label_4);
		
		JLabel lblRemain = new JLabel("Remain:");
		lblRemain.setForeground(Color.WHITE);
		lblRemain.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRemain.setBounds(402, 668, 74, 14);
		contentPanel.add(lblRemain);
		
		label_5 = new JLabel("0.00");
		label_5.setInheritsPopupMenu(false);
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(468, 663, 139, 20);
		contentPanel.add(label_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(841, 151, 210, 484);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		textArea = new JTextArea(10,20);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(0, 0, 210, 484);
		panel_1.add(textArea);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				textField.requestFocus();
			}
		});
	}

	public void Filter(String sql) {
		model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(sql));
	}

//	public void showtablebill() throws SQLException {
//		try {
//			dbcon.connect();
//			String sql = "Select * from tablecustomerdetail";
//			dbcon.st = dbcon.conn.createStatement();
//			dbcon.rs = dbcon.st.executeQuery(sql);
//			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, e);
//		} finally {
//
//			dbcon.conn.close();
//		}
//	}

	public void datequery(String data13, String data14) throws SQLException {
		try {
			dbcon.connect();

			String sql = "Select slipno,entereddate as Booking_Date, bookingocassion,customername,address,aadharno,mobile,timeto,timefrom,totalcost,advanceamount,remainingamount,paidamount,paiddate from bookingdetails where entereddate between '"
					+ data13 + "' and '" + data14 + "'";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			model.setRowCount(0);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Error:" + e);
		} finally {
			dbcon.conn.close();
		}
	}

	public void totalsaleshow(DefaultTableModel model2) {
		double totalcost = 0;
		double advanceamounttotal=0;
		double remainingtotal=0;
		double paidtotal=0;
		model2 = (DefaultTableModel) table.getModel();
		table.setModel(model2);
		int nRow = model2.getRowCount();

//	JOptionPane.showMessageDialog(null, model2);

//	JOptionPane.showMessageDialog(null, abc);

		for (int i = 0; i < nRow; i++) {
			Double cost = Double.parseDouble(model2.getValueAt(i, 9).toString());
			totalcost=totalcost+cost;
			
			Double advanceamt = Double.parseDouble(model2.getValueAt(i, 10).toString());
			advanceamounttotal=advanceamounttotal+advanceamt;
			
			Double remaining = Double.parseDouble(model2.getValueAt(i, 11).toString());
			remainingtotal=remainingtotal+remaining;
			
			Double paid = Double.parseDouble(model2.getValueAt(i, 12).toString());
			paidtotal=paidtotal+paid;
			
			
			
			
//		double amount=Double.parseDouble((String) model2.getValueAt(i, 5));

		

		}
		label_3.setText(String.valueOf(totalcost));
		label_4.setText(String.valueOf(advanceamounttotal));
		label_5.setText(String.valueOf(remainingtotal));
		label_2.setText(String.valueOf(paidtotal));
		
//		label_2.setText(String.valueOf(total));
	}

	public void billamountshowtoday() throws SQLException {
		String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();

		datequery(data13, data14);
	}
	
	public void showtheitems(String item) throws SQLException {
		try {
			textArea.setText(null);
			dbcon.connect();
			String sql = "Select remarks from bookingdetails where slipno='"
					+ item + "'";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			int i = 1;
			while (dbcon.rs.next()) {
				String remarks = dbcon.rs.getString("remarks");
				textArea.setText(remarks);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.pst.close();
			dbcon.rs.close();
			dbcon.conn.close();
		}
	}
}
