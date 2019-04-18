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
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class showpurchaserecord extends JFrame {
	private JTextField filename = new JTextField(), dir = new JTextField();
	JFileChooser chooser = new JFileChooser();
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	Databaseconnection dbcon = new Databaseconnection();
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel model1;
	DefaultTableModel model12;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JLabel label_2;
	private JTable table_1;
	private JTable table;

	public showpurchaserecord() {
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
		textField.setBounds(209, 117, 849, 27);
		contentPanel.add(textField);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(showpurchaserecord.class.getResource("/hms/images/Zoom-icon.png")));
		lblSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSearch.setBounds(77, 99, 59, 56);
		contentPanel.add(lblSearch);

		JLabel lblShowAllVehicle = new JLabel("Purchase Record");
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
		dateChooser.setBounds(674, 49, 157, 20);
		contentPanel.add(dateChooser);

		JLabel label = new JLabel("-TO-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(837, 49, 35, 20);
		contentPanel.add(label);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		dateChooser_1.setDate(new Date());
		dateChooser_1.setBounds(871, 49, 157, 20);
		contentPanel.add(dateChooser_1);

		JButton button = new JButton("Click to Search");
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();

					datequery(data13, data14);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBackground(Color.GRAY);
		button.setBounds(674, 75, 366, 31);
		contentPanel.add(button);

		JLabel label_1 = new JLabel("Search Between Dates");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(499, 65, 150, 14);
		contentPanel.add(label_1);

		JLabel lblTotalPurchase = new JLabel("Total Purchase:");
		lblTotalPurchase.setForeground(Color.RED);
		lblTotalPurchase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotalPurchase.setBounds(782, 717, 90, 14);
		contentPanel.add(lblTotalPurchase);

		label_2 = new JLabel("0.0");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(882, 712, 176, 20);
		contentPanel.add(label_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(87, 515, 968, 160);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(0, 0, 968, 160);
				panel_1.add(scrollPane_1);
				
						table_1 = new JTable();
						table_1.setModel(new DefaultTableModel(new Object[][] {},
								new String[] { "Sl.no.", "Product Name", "Quantity", "Rate", "Mrp", "Batch No.", "Expiry" }) {
							boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						});
						scrollPane_1.setViewportView(table_1);

		JPanel panel = new JPanel();
		panel.setBounds(87, 155, 971, 336);
		contentPanel.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 971, 336);
		panel.add(scrollPane);

		table = new JTable();
//		table.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				try {
//
//					DefaultTableModel model1 = (DefaultTableModel) table.getModel();
////					int SelectedRowIndex = table.getSelectedRow();
//
//					int x = table.getSelectedRow();
//					String item = model1.getValueAt(x, 0).toString();
//
//					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
//						String message = "Do you want to Delete ? ";
//						String title = "CONFIRM";
//
//						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
//						if (reply == JOptionPane.YES_OPTION) {
//
////						JOptionPane.showMessageDialog(null, item);
//							model1.removeRow(x);
//							dbcon.invoicepurchasedelete(item);
//							model1.setRowCount(0);
//							billamountshowtoday();
//						}
//					}
//				} catch (Exception f) {
//					JOptionPane.showMessageDialog(null, f);
//				}
//
//			}
////		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model = (DefaultTableModel) table.getModel();

				int x = table.getSelectedRow();
				String item = model.getValueAt(x, 0).toString();
//				try {
				try {
					showtheitems(item);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
		});
		scrollPane.setViewportView(table);
		
	
		try {
//			Table2();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				printfunction();
			}
		});
		btnNewButton_1.setBounds(360, 78, 89, 23);
//		contentPanel.add(btnNewButton_1);
		try {
			billamountshowtoday();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e2);
		}
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				textField.requestFocus();
			}
		});
		
		try {
			billamountshowtoday(); 
//			totalpurchaseamount();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	}

	public void datequery(String data13, String data14) throws SQLException {
		try {
			dbcon.connect();
			String sql = "Select Invoice, Billdate, Partyname,Subtotal,Discountamount,Cgst,Sgst,Totalamount from rmspurchase where Billdate between '"
					+ data13 + "' and '" + data14 + "'";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			model.setRowCount(0);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
			
			model=(DefaultTableModel) table.getModel();
			totalpurchaseamount(model);
//			totalsaleshow(model);
			
		} catch (Exception e) {
		} finally {
			dbcon.conn.close();
		}
	}

//	public void Table2() throws Exception {
//		try {
//
//			dbcon.connect();
//			String sql = "Select invoice,billdate,purchase_entrydate,partyname,billamount,discountrate from rmspurchase";
//			dbcon.pst = dbcon.conn.prepareStatement(sql);
//			dbcon.rs = dbcon.pst.executeQuery();
//			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, e);
//		} finally {
//
//			dbcon.conn.close();
//
//		}
//	}

	public void Filter(String sql) {
		model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(sql));
	}

	public void billamountshowtoday() throws SQLException {
		String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();

		datequery(data13, data14);
	}

	public void totalsaleshow(DefaultTableModel model2) {
		
		
//		JOptionPane.showMessageDialog(null, model);
//		JOptionPane.showMessageDialog(null, nRow);
		double total = 0;
		int nRow = model12.getRowCount();
		for (int i = 0; i < nRow; i++) {

			double amount = Double.parseDouble((String) model12.getValueAt(i, 7));
			total = total + amount;
			JOptionPane.showMessageDialog(null, total);
		}
		label_2.setText(String.valueOf(total));
	}

	public void showtheitems(String item) throws SQLException {
		try {
			DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
			model1.setRowCount(0);
//			DefaultTableModel table_model = (DefaultTableModel) table_1.getModel();
//			JOptionPane.showMessageDialog(null, item);
//			item
			dbcon.connect();
			String sql = "Select Productname,Quantity,Rate,MRP,expiry,Batch_No from purchase_productlist where Invoice='"
					+ item + "'";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			int i = 1;
			while (dbcon.rs.next()) {
				String item_name = dbcon.rs.getString("Productname");

				String quantity = dbcon.rs.getString("Quantity");
				String purchaserate = dbcon.rs.getString("Rate");
				String mrp = dbcon.rs.getString("MRP");
				String batchno = dbcon.rs.getString("expiry");
				String expiry = dbcon.rs.getString("Batch_No");

//				JOptionPane.showMessageDialog(null, expiry);
				Object[] row = { i, item_name, quantity, purchaserate, mrp, batchno, expiry };

//				JOptionPane.showMessageDialog(null, row);

				model1.addRow(row);

				i++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.pst.close();
			dbcon.rs.close();
			dbcon.conn.close();
		}
	}
	
public void totalpurchaseamount(DefaultTableModel model2) {
		
		int nRow = model2.getRowCount();
		
//		JOptionPane.showMessageDialog(null, model);
//		JOptionPane.showMessageDialog(null, nRow);
		double total = 0;
		
		for (int i = 0; i < nRow; i++) {

			double amount = Double.parseDouble(model2.getValueAt(i, 7).toString());
//			JOptionPane.showMessageDialog(null, amount);
			total = total + amount;
			
//			JOptionPane.showMessageDialog(null, total);
		}
		
		Double amount_amount_in = Math.round((total) * 100.0) / 100.0;	
		label_2.setText(String.valueOf(amount_amount_in));
	}

}
