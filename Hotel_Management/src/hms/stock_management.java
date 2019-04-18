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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import com.toedter.calendar.JDateChooser;

public class stock_management extends JFrame {

	private final JPanel contentPanel = new JPanel();
	String tablexyz;
	LocalDate today = LocalDate.now();
	LocalTime time = LocalTime.now();
	Databaseconnection dbcon = new Databaseconnection();
	private JTable table;
	private JComboBox comboBox;
	DefaultTableModel model;
	boolean success = false;
	
	public stock_management() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setUndecorated(true);
		setType(javax.swing.JFrame.Type.UTILITY);

		// setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 99, 1036, 74);
		contentPanel.add(panel);
		
		JLabel lblPartyName = new JLabel("Party Name");
		lblPartyName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPartyName.setBounds(10, 27, 79, 36);
		panel.add(lblPartyName);
		
		comboBox = new JComboBox();
		try {
			showparty_list();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBox.setBounds(86, 27, 225, 36);
		panel.add(comboBox);
		
		JLabel label_1 = new JLabel("Start Date");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_1.setBounds(321, 27, 131, 36);
		panel.add(label_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setDate(new Date());
		dateChooser.setBounds(396, 27, 159, 36);
		panel.add(dateChooser);
		
		JLabel label_2 = new JLabel("End Date");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_2.setBounds(573, 27, 131, 36);
		panel.add(label_2);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		
		dateChooser_1.setDate(new Date());
		dateChooser_1.setBounds(647, 27, 159, 36);
		panel.add(dateChooser_1);
		
		JButton button = new JButton("FIND");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
					String partyname=comboBox.getSelectedItem().toString();
				if (partyname.length() == 0) {
					
					comboBox.requestFocus();
				} else {
					
					
					
					
					String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();

					
					datequery(partyname,data13, data14);
					
					
					
				}
				}catch(Exception f)
				{}
				}
		});
		
		
		
		button.setFont(new Font("Dialog", Font.PLAIN, 14));
		button.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		button.setBounds(816, 11, 89, 52);
		panel.add(button);
		
		JButton btnNewButton = new JButton("See ALL");
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Table2();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(915, 11, 106, 52);
		panel.add(btnNewButton);
		
		JLabel lblStockStatement = new JLabel("Stock Statement");
		lblStockStatement.setOpaque(true);
		lblStockStatement.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockStatement.setForeground(Color.BLACK);
		lblStockStatement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStockStatement.setBackground(Color.WHITE);
		lblStockStatement.setBounds(0, 32, 1092, 23);
		contentPanel.add(lblStockStatement);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(78, 220, 955, 507);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 955, 507);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try {
			Table2();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}
	public void showparty_list() throws SQLException {

		try {
			dbcon.connect();
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery("Select partyname from party_details order by partyname asc");

			while (dbcon.rs.next()) {
				String data = dbcon.rs.getString("partyname");
				comboBox.addItem(data);
				comboBox.setSelectedItem(null);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbcon.st.close();
			dbcon.rs.close();
			dbcon.conn.close();
		}

	}
	public boolean Cashshow(String sql) {
		try {
			
			int sl_no = 1;
			dbcon.connect();

			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			while (dbcon.rs.next()) {
				String billno = dbcon.rs.getString("Invoice");
				String Date = dbcon.rs.getString("Billdate");
				//
				String Final_Amount = dbcon.rs.getString("Productname");
//				int fa=Integer.parseInt(Final_Amount);
//				double Final_Amount_round=Math.round(fa);
				//
				
				//
				String Balance_Amount = dbcon.rs.getString("Quantity");
//				int ba=Integer.parseInt(Balance_Amount);
//				double Balance_Amount_round=Math.round(ba);
				//
				
//				int ra=Integer.parseInt(Recieve_Amount);
//				double Recieve_Amount_round=Math.round(ra);
				
				
				success=true;
				
				model.addRow(
						new Object[] { sl_no, billno, Date, Final_Amount, Balance_Amount });
				sl_no++;
				
			}
			
			
			
			

		} catch (Exception ef) {
		} finally {
			try {
				dbcon.conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		return success;

	}
	public void Table2() throws Exception {
		try {

			dbcon.connect();
			String sql = "Select * from Product_Stock";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {

			dbcon.conn.close();

		}
	}
	public void datequery(String data13, String data14, String data142) throws SQLException {
		try {
			model=(DefaultTableModel) table.getModel();
			dbcon.connect();
//			JOptionPane.showMessageDialog(null, data13);
//			JOptionPane.showMessageDialog(null, data14);
//			JOptionPane.showMessageDialog(null, data142);
			String sql = "Select purchase_productlist.Invoice,purchase_productlist.Billdate,purchase_productlist.Productname,purchase_productlist.Quantity from purchase_productlist,rmspurchase where purchase_productlist.Invoice=rmspurchase.Invoice AND purchase_productlist.Partyname='"
					+ data13 + "' AND purchase_productlist.Billdate between '"+data14+"' AND '"+data142+"'";
//			String sql = "Select invoice,billdate,purchase_entrydate,partyname,billamount,discountrate from rmspurchase where billdate between '"
//					+ data13 + "' and '" + data14 + "'";
//			JOptionPane.showMessageDialog(null, "1");
			
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			
			
			
//			JOptionPane.showMessageDialog(null, "3");
			
			model.setRowCount(0);
			
//			JOptionPane.showMessageDialog(null, dbcon.rs);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
//			totalsaleshow(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
	}
}
