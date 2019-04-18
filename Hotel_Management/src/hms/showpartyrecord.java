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

import net.proteanit.sql.DbUtils;

public class showpartyrecord extends JFrame {
	private JTextField filename = new JTextField(), dir = new JTextField();
	JFileChooser chooser = new JFileChooser();
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	Databaseconnection dbcon = new Databaseconnection();
	DefaultTableModel model = new DefaultTableModel();


	public showpartyrecord() {
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
		panel.setBounds(64, 161, 971, 480);
		contentPanel.add(panel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 971, 480);
		panel.add(scrollPane);

		table = new JTable();
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(11).setPreferredWidth(92);
		table.getColumnModel().getColumn(11).setMinWidth(92);
	
		try {
			Table2();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
//		table.getAccessibleContext().setAccessibleName("\"Export\"");
//		table.getAccessibleContext().setAccessibleDescription("");

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
		textField.setBounds(180, 121, 849, 27);
		contentPanel.add(textField);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(showpartyrecord.class.getResource("/hms/images/Zoom-icon.png")));
		lblSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSearch.setBounds(125, 108, 59, 56);
		contentPanel.add(lblSearch);

		JLabel lblShowAllVehicle = new JLabel("Party Record");
		lblShowAllVehicle.setOpaque(true);
		lblShowAllVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowAllVehicle.setForeground(SystemColor.activeCaptionText);
		lblShowAllVehicle.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblShowAllVehicle.setBackground(Color.WHITE);
		lblShowAllVehicle.setBounds(0, 0, 1092, 31);
		contentPanel.add(lblShowAllVehicle);
		
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
				   textField.requestFocus();
			     }
			   } ); 
	}
	public void datequery(String data13) throws SQLException {
		dbcon.connect();
		String sql ="Select party_code,partyname,partyaddress,city,state,phoneoffice,mobile,gstin,aadharno,panno,fssaino from party_details where party_code='"+data13+"'";
		dbcon.pst = dbcon.conn.prepareStatement(sql);
		dbcon.rs = dbcon.pst.executeQuery();
		model.setRowCount(0);
		table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		
	}
	
	
	public void Table2() throws Exception {
		try {
			dbcon.connect();
			String sql = "Select party_code,partyname,partyaddress,city,state,phoneoffice,mobile,gstin,aadharno,panno,fssaino  from party_details";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			try {

				dbcon.conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	public void Filter(String sql) {
		model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(sql));
	}
}
