package hms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
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

import net.proteanit.sql.DbUtils;

public class showstaffdetails extends JFrame {

	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	Databaseconnection dbcon = new Databaseconnection();
	DefaultTableModel model = new DefaultTableModel();

	public showstaffdetails() {
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
		panel.setBounds(88, 152, 971, 489);
		contentPanel.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 971, 489);
		panel.add(scrollPane);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					
					DefaultTableModel model1 = (DefaultTableModel) table.getModel();
//					int SelectedRowIndex = table.getSelectedRow();
					

					int x = table.getSelectedRow();
					String item = model1.getValueAt(x, 0).toString();
					
					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					String message = "Do you want to Delete ? ";
					String title = "CONFIRM";

					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
					
//						JOptionPane.showMessageDialog(null, item);
							model1.removeRow(x);
							dbcon.staffdelete(item);
							
					}
					}
					}catch(Exception f)
					{
						JOptionPane.showMessageDialog(null, f);	
					}
				
				
			}
		});
	//	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(111);
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setPreferredWidth(124);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		table.getColumnModel().getColumn(4).setPreferredWidth(189);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(103);
		table.getColumnModel().getColumn(7).setPreferredWidth(97);
		table.getColumnModel().getColumn(8).setPreferredWidth(98);
		table.getColumnModel().getColumn(9).setPreferredWidth(101);
		table.getColumnModel().getColumn(10).setPreferredWidth(117);
		try {
			Table2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		textField.setBounds(132, 83, 927, 39);
		contentPanel.add(textField);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(showstaffdetails.class.getResource("/hms/images/Zoom-icon.png")));
		lblSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSearch.setBounds(65, 71, 59, 56);
		contentPanel.add(lblSearch);
		
		JLabel lblStaffRecord = new JLabel("Staff Record");
		lblStaffRecord.setOpaque(true);
		lblStaffRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblStaffRecord.setForeground(Color.BLACK);
		lblStaffRecord.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblStaffRecord.setBackground(Color.WHITE);
		lblStaffRecord.setBounds(0, 0, 1092, 31);
		contentPanel.add(lblStaffRecord);
		
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
				   textField.requestFocus();
			     }
			   } ); 
	}

	
	public void Table2() throws Exception {
		try {
			dbcon.connect();
			String sql = "Select * from staffdetails";
			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {

			dbcon.conn.close();
		}
	}

	public void Filter(String sql) {
		model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(sql));
	}

}
