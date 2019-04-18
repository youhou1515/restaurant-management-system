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

public class showbillrecord extends JFrame {

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
//	Strin data
	
	
	public showbillrecord(String u1) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width*16/20, dim.height * 17 / 20);
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
		panel.setBounds(0, 150, 536, 461);
		contentPanel.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(0, 0, 536, 461);
		panel.add(scrollPane);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				try {
//				model = (DefaultTableModel) table.getModel();
//				DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
////				int SelectedRowIndex = table.getSelectedRow();
//				
//
//				int x = table.getSelectedRow();
//				String item = model.getValueAt(x, 0).toString();
//				
//				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
//				String message = "Do you want to Delete ? ";
//				String title = "CONFIRM";
//
//				int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
//				if (reply == JOptionPane.YES_OPTION) {
//				
////					JOptionPane.showMessageDialog(null, item);
//					model.removeRow(x);
//						dbcon.billdelete(item);
//					
//						showtablebill(u1);
//						billamountshowtoday(u1);
//						model1.setRowCount(0);
////						model.setRowCount(0);
//						
//				}
//				}
//			} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					
				
			}});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					
				model = (DefaultTableModel) table.getModel();

				int x = table.getSelectedRow();
				String item = model.getValueAt(x, 0).toString();
//				try {
				
					showtheitems(item);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bill Number", "Customer ID", "Name", "Aadhar No.", "Mobile No.", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
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
		textField.setBounds(70, 110, 791, 31);
		contentPanel.add(textField);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(showbillrecord.class.getResource("/hms/images/Zoom-icon2.png")));
		lblSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSearch.setBounds(10, 96, 1142, 56);
		contentPanel.add(lblSearch);

		JLabel lblShowAllVehicle = new JLabel("Invoice Record");
		lblShowAllVehicle.setOpaque(true);
		lblShowAllVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowAllVehicle.setForeground(SystemColor.activeCaptionText);
		lblShowAllVehicle.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblShowAllVehicle.setBackground(Color.WHITE);
		lblShowAllVehicle.setBounds(0, 0, 868, 31);
		contentPanel.add(lblShowAllVehicle);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setDate(new Date());
//		dateChooser_2.setMinSelectableDate(new Date());
		dateChooser.setBounds(465, 42, 157, 20);
		contentPanel.add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		dateChooser_1.setDate(new Date());
//		dateChooser_3.setMinSelectableDate(new Date());
		dateChooser_1.setBounds(674, 42, 157, 20);
		contentPanel.add(dateChooser_1);
		
		JLabel label = new JLabel("-TO-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(628, 42, 35, 20);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("Search Between Dates");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(290, 58, 150, 14);
		contentPanel.add(label_1);
		
		
		JButton button = new JButton("Click to Search");
		button.setForeground(Color.WHITE);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String data13=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
					String data14=((JTextField)dateChooser_1.getDateEditor().getUiComponent()).getText();
					
						datequery(data13, data14,u1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1);
					}
				
				
			}
		});
		button.setBackground(Color.GRAY);
		button.setBounds(465, 68, 366, 31);
		contentPanel.add(button);
		
		JLabel lblTotalAmount = new JLabel("TOTAL:");
		lblTotalAmount.setForeground(Color.WHITE);
		lblTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotalAmount.setBounds(726, 624, 46, 14);
		contentPanel.add(lblTotalAmount);
		
		 label_2 = new JLabel("0.00");
		 label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(767, 622, 129, 14);
		contentPanel.add(label_2);
		
		label_3 = new JLabel("0.0");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(94, 622, 102, 15);
		contentPanel.add(label_3);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setForeground(Color.WHITE);
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSubtotal.setBounds(43, 622, 51, 14);
		contentPanel.add(lblSubtotal);
		
		label_4 = new JLabel("0.0");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(420, 620, 116, 15);
		contentPanel.add(label_4);
		
		JLabel lblCgstsgst = new JLabel("CGST:");
		lblCgstsgst.setForeground(Color.WHITE);
		lblCgstsgst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCgstsgst.setBounds(386, 622, 35, 14);
		contentPanel.add(lblCgstsgst);
		
		label_5 = new JLabel("0.0");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(577, 620, 129, 15);
		contentPanel.add(label_5);
		
		JLabel lblSgst = new JLabel("SGST:");
		lblSgst.setForeground(Color.WHITE);
		lblSgst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSgst.setBounds(546, 622, 35, 14);
		contentPanel.add(lblSgst);
		
		label_6 = new JLabel("0.0");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(253, 622, 123, 14);
		contentPanel.add(label_6);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setForeground(Color.WHITE);
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDiscount.setBounds(206, 622, 45, 14);
		contentPanel.add(lblDiscount);
		
		JLabel label_7 = new JLabel("+");
		label_7.setForeground(Color.WHITE);
		label_7.setBounds(374, 622, 20, 14);
		contentPanel.add(label_7);
		
		JLabel label_8 = new JLabel("+");
		label_8.setForeground(Color.WHITE);
		label_8.setBounds(536, 622, 20, 14);
		contentPanel.add(label_8);
		
		JLabel label_9 = new JLabel("-");
		label_9.setForeground(Color.WHITE);
		label_9.setBounds(194, 622, 20, 14);
		contentPanel.add(label_9);
		
		JLabel label_10 = new JLabel("=");
		label_10.setForeground(Color.WHITE);
		label_10.setBounds(716, 622, 20, 14);
		contentPanel.add(label_10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(546, 150, 322, 461);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		scrollPane_1.setBounds(0, 0, 322, 461);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		
//		 table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price", "Qty", "Amt."
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(93);
		table_1.getColumnModel().getColumn(0).setMinWidth(93);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(58);
		table_1.getColumnModel().getColumn(1).setMinWidth(58);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(42);
		table_1.getColumnModel().getColumn(2).setMinWidth(42);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(70);
		table_1.getColumnModel().getColumn(3).setMinWidth(70);
		scrollPane_1.setViewportView(table_1);
//		new JScrollPane(table_1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		try {
			billamountshowtoday(u1);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, e2);
		}
		
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
				   textField.requestFocus();
			     }
			   } ); 
		
		

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
			String sql = "Select * from tablecustomerdetail where Billing_Staffname='"+search+"'";
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {

			dbcon.conn.close();
		}
	}
	
	public void datequery(String data13, String data14, String search) throws SQLException {
		dbcon.connect();
		String sql ="Select * from tablecustomerdetail where savedate between '"+data13+"' and '"+data14+"' AND Billing_Staffname='"+search+"'";
		dbcon.pst = dbcon.conn.prepareStatement(sql);
		dbcon.rs = dbcon.pst.executeQuery();
		model.setRowCount(0);
		table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		
		
		totalsaleshow(model);
	
	}
	
	
	

public void totalsaleshow(DefaultTableModel model2)
{
	double subtotal=0,cgst=0,sgst=0,grandtotal=0,discount=0;
	model2= (DefaultTableModel) table.getModel();
	table.setModel(model2);
	int nRow = model2.getRowCount();
			
			
	
//	JOptionPane.showMessageDialog(null, nRow);
	
	
	for(int i=0;i<nRow;i++)
	{
		
		double amount=Double.parseDouble(model2.getValueAt(i, 8).toString());
		
		subtotal=subtotal+amount;
		
		
		double cgst_1=Double.parseDouble((String) model2.getValueAt(i, 9).toString());
		cgst=cgst+cgst_1;
		
		double sgst_1=Double.parseDouble((String) model2.getValueAt(i, 10).toString());
		sgst=sgst+sgst_1;
		
		double grandtotal_1=Double.parseDouble((String) model2.getValueAt(i, 12).toString());
		grandtotal=grandtotal+grandtotal_1;
		
		
		double discount_1=Double.parseDouble((String) model2.getValueAt(i, 11).toString());
		discount=discount+discount_1;
		
		

	}
	
	double subtotal_roundoff = Math.round(subtotal * 100.0) / 100.0;
	double cgst_rf = Math.round(cgst * 100.0) / 100.0;
	double sgst_rf= Math.round(sgst * 100.0) / 100.0;
	double discount_rf = Math.round(discount * 100.0) / 100.0;
	double grandtotal_rf = Math.round(grandtotal * 100.0) / 100.0;
	
	
	
	label_3.setText(String.valueOf(subtotal_roundoff));
	label_4.setText(String.valueOf(cgst_rf));
	
	label_5.setText(String.valueOf(sgst_rf));
	label_6.setText(String.valueOf(discount_rf));
	
	label_2.setText(String.valueOf(grandtotal_rf));
}

public void billamountshowtoday(String search) throws SQLException
{
	String data13=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
	String data14=((JTextField)dateChooser_1.getDateEditor().getUiComponent()).getText();
	
		datequery(data13, data14, search);
}


public void showtheitems(String item) throws SQLException {
	try {
		DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
		model1.setRowCount(0);
//		DefaultTableModel table_model = (DefaultTableModel) table_1.getModel();
//		JOptionPane.showMessageDialog(null, item);
//		item
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
			

//			JOptionPane.showMessageDialog(null, expiry);
			Object[] row = { item_name, quantity,mrp, batchno };

//			JOptionPane.showMessageDialog(null, row);

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
}
