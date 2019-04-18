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

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

public class showkotrecord_admin extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	Databaseconnection dbcon = new Databaseconnection();
	DefaultTableModel model;
//	Strin data

	public showkotrecord_admin() {
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
		panel.setBounds(101, 185, 781, 358);
		contentPanel.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 781, 358);
		panel.add(scrollPane);

		table = new JTable();
//		table.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				try {
//					model = (DefaultTableModel) table.getModel();
//				
//					int x = table.getSelectedRow();
//					String item = model.getValueAt(x, 1).toString();
//
//					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
//						String message = "Do you want to Delete ? ";
//						String title = "CONFIRM";
//
//						int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
//						if (reply == JOptionPane.YES_OPTION) {
//
//							model.removeRow(x);
//							dbcon.kotdelete(item);
//
//						}
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});

		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S No.", "Kot ID", "Invoice No.", "Item Name", "Unit Price", "Quantity", "Amount"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(57);
		table.getColumnModel().getColumn(0).setMinWidth(57);
		table.getColumnModel().getColumn(0).setMaxWidth(57);
		table.getColumnModel().getColumn(1).setPreferredWidth(113);
		table.getColumnModel().getColumn(1).setMaxWidth(113);
		table.getColumnModel().getColumn(2).setPreferredWidth(107);
		table.getColumnModel().getColumn(2).setMinWidth(107);
		table.getColumnModel().getColumn(3).setPreferredWidth(225);
		table.getColumnModel().getColumn(3).setMinWidth(225);
		table.getColumnModel().getColumn(5).setPreferredWidth(77);
		table.getColumnModel().getColumn(5).setMinWidth(77);
		table.getColumnModel().getColumn(6).setPreferredWidth(125);
		table.getColumnModel().getColumn(6).setMinWidth(125);
		
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
		textField.setBounds(91, 143, 794, 31);
		contentPanel.add(textField);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(showkotrecord_admin.class.getResource("/hms/images/Zoom-icon2.png")));
		lblSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSearch.setBounds(40, 135, 504, 56);
		contentPanel.add(lblSearch);

		JLabel lblShowAllVehicle = new JLabel("KOT Record");
		lblShowAllVehicle.setOpaque(true);
		lblShowAllVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowAllVehicle.setForeground(SystemColor.activeCaptionText);
		lblShowAllVehicle.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblShowAllVehicle.setBackground(Color.WHITE);
		lblShowAllVehicle.setBounds(32, 0, 865, 31);
		contentPanel.add(lblShowAllVehicle);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setDate(new Date());
		
		dateChooser.setBounds(516, 43, 157, 20);
		contentPanel.add(dateChooser);
		
		JLabel label = new JLabel("-TO-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(679, 43, 35, 20);
		contentPanel.add(label);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		dateChooser_1.setDate(new Date());
		dateChooser_1.setBounds(725, 43, 157, 20);
		contentPanel.add(dateChooser_1);
		
		JButton button = new JButton("Click to Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					model = (DefaultTableModel) table.getModel();
					if(model.getRowCount()>0)
					{
					model.setRowCount(0);
					}
				String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				
					showtablebill(data13, data14);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		button.setForeground(Color.WHITE);
		button.setBackground(Color.GRAY);
		button.setBounds(516, 69, 366, 31);
		contentPanel.add(button);

		try {
			model = (DefaultTableModel) table.getModel();
			if(model.getRowCount()>0)
			{
			model.setRowCount(0);
			}
			String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
			String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
			showtablebill(data13, data14);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	

	public void Filter(String sql) {

		model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(sql));
	}

	public void showtablebill(String data13, String data14) throws SQLException {
		try {
			dbcon.connect();
//			JOptionPane.showMessageDialog(null, search);
			String sql = "Select bill_no,item_name,unit_price,quantity,amount,kotid from bill_list where bill_no is not null AND entrydate between '"+data13+"' and '"+data14+"'";
//			String sql = "Select * from tablecustomerdetail where Billing_Staffname='"+search+"'";
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);
			int i=1;
			model = (DefaultTableModel) table.getModel();
			while (dbcon.rs.next()) {
				String bill_no = dbcon.rs.getString("bill_no");
				String item_name = dbcon.rs.getString("item_name");
				String unit_price = dbcon.rs.getString("unit_price");
				String quantity = dbcon.rs.getString("quantity");
				String amount = dbcon.rs.getString("amount");
				String kotid = dbcon.rs.getString("kotid");
				
				Object[] row = { i,kotid,bill_no, item_name, unit_price, quantity, amount };

				model.addRow(row);
				i++;
				
			}
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {

			dbcon.conn.close();
		}
	}

	
}
