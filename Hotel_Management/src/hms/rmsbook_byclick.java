package hms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class rmsbook_byclick extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	Databaseconnection dbcon = new Databaseconnection();
	DefaultTableModel model = new DefaultTableModel();
	private String[] array;
	String s;
	String tableclick2;
	ArrayList<String> invoice = new ArrayList<String>();
	String[] myList;

	public rmsbook_byclick(String tableClick) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(rmsbook_byclick.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - getWidth()) / 5;
		int y = (dim.height - getHeight()) / 5;
		setBounds(x, y, 454, 439);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 667, 312);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 439, 312);
				panel.add(scrollPane);
				{
					table = new JTable();
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.setModel(new DefaultTableModel(
							new Object[][] { { null, null, null, null }, { null, null, null, null },
									{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
									{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
									{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
									{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
									{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
									{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
									{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
									{ null, null, null, null }, { null, null, null, null }, },
							new String[] { "Item Name", "Item Price", "Quantity", "Amount" }) {
						boolean[] columnEditables = new boolean[] { false, false, false, false };

						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
					table.getColumnModel().getColumn(0).setResizable(false);
					table.getColumnModel().getColumn(1).setResizable(false);
					table.getColumnModel().getColumn(2).setResizable(false);
					table.getColumnModel().getColumn(3).setResizable(false);
					scrollPane.setViewportView(table);
				}
			}
		}
		//JOptionPane.showMessageDialog(null, tableClick);
		set(array, tableClick);
		showtheitems();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						dispose();
					}

				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	// TODO Auto-generated constructor stub

	// TODO Auto-generated constructor stub

	public void set(String[] myList, String tableClick) {
		this.tableclick2 = tableClick;
		this.array = myList;
	}

	public void showtheitems() {
		try {
		
			dbcon.connect();
			String sql = "Select item_name from bill_item_list where billno='" + tableclick2 + "'";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();

			while (dbcon.rs.next()) {
				invoice.add(dbcon.rs.getString("item_name"));
			}
		
			for (int qk = 0; qk < invoice.size(); qk++) {
				s = (String) invoice.get(qk);
				
			
				
				// JOptionPane.showMessageDialog(null, invoice);
			}

			// array in which items are stored in split format

			myList = s.split(",");
			
			model = (DefaultTableModel) table.getModel();

			int nrow = myList.length / 4;
			int ncol = table.getColumnCount();

	//		JOptionPane.showMessageDialog(null, myList);
	//		JOptionPane.showMessageDialog(null, nrow);
		//	JOptionPane.showMessageDialog(null, ncol);

			int a = 0;
			model = (DefaultTableModel) table.getModel();

			for (int i = 0; i < nrow; i++) {
				for (int j = 0; j < ncol; j++) {

					model.setValueAt(String.valueOf(myList[a]), i, j);
					a++;
				}

			}
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "CATC");
		}
	}
}
