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
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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

public class showissuestock_record extends JDialog {
	private JTextField filename = new JTextField(), dir = new JTextField();
	JFileChooser chooser = new JFileChooser();
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	Databaseconnection dbcon = new Databaseconnection();
	DefaultTableModel model = new DefaultTableModel();


	public showissuestock_record() throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage(showissuestock_record.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(200, 0, dim.width * 15 / 20, dim.height * 19 / 20);
//		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 150, 971, 505);
		contentPanel.add(panel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 971, 529);
		panel.add(scrollPane);

		table = new JTable();
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
		Table2();
		scrollPane.setViewportView(table);
		table.getAccessibleContext().setAccessibleName("\"Export\"");
		table.getAccessibleContext().setAccessibleDescription("");

		textField = new JTextField();
		textField.setBackground(SystemColor.inactiveCaptionBorder);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String txt = textField.getText().toUpperCase();
				Filter(txt);
			}
		});
		textField.setColumns(10);
		textField.setBounds(132, 112, 849, 27);
		contentPanel.add(textField);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(showissuestock_record.class.getResource("/hms/images/Zoom-icon.png")));
		lblSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSearch.setBounds(77, 99, 59, 56);
		contentPanel.add(lblSearch);

		JLabel lblShowAllVehicle = new JLabel("Stock Issue Record");
		lblShowAllVehicle.setOpaque(true);
		lblShowAllVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowAllVehicle.setForeground(SystemColor.activeCaptionText);
		lblShowAllVehicle.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblShowAllVehicle.setBackground(SystemColor.inactiveCaptionBorder);
		lblShowAllVehicle.setBounds(0, 0, 1008, 31);
		contentPanel.add(lblShowAllVehicle);
		
		JLabel label = new JLabel("Search Between Dates");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(440, 60, 150, 14);
		contentPanel.add(label);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(615, 44, 157, 20);
		contentPanel.add(dateChooser);
		
		JLabel label_1 = new JLabel("-TO-");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(778, 44, 35, 20);
		contentPanel.add(label_1);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		dateChooser_1.setBounds(812, 44, 157, 20);
		contentPanel.add(dateChooser_1);
		
		JButton button = new JButton("Click to Search");
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String data13=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
					String data14=((JTextField)dateChooser_1.getDateEditor().getUiComponent()).getText();
					
						datequery(data13, data14);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(615, 70, 366, 31);
		contentPanel.add(button);
		
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
				   textField.requestFocus();
			     }
			   } ); 
	}
	public void datequery(String data13, String data14) throws SQLException {
		dbcon.connect();
		String sql ="Select * from issuestockentry where date between '"+data13+"' and '"+data14+"'";
		dbcon.pst = dbcon.conn.prepareStatement(sql);
		dbcon.rs = dbcon.pst.executeQuery();
		model.setRowCount(0);
		table.setModel(DbUtils.resultSetToTableModel(dbcon.rs));
		
	}
	
	
	public void Table2() throws Exception {
		try {
			dbcon.connect();
			String sql = "Select * from issuestockentry";
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
