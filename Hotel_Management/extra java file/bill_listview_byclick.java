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
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class bill_listview_byclick extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
//	showbillrecord sbrobj = new showbillrecord();
	DefaultTableModel model = new DefaultTableModel();
	String[] array;

	public bill_listview_byclick(String[] myList, String tableClick) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(bill_listview_byclick.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - getWidth()) / 4;
		int y = (dim.height - getHeight()) / 4;
		setBounds(x, y, dim.width * 10 / 20, dim.height * 10 / 20);
//		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaptionBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 39, 667, 312);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 657, 312);
				panel.add(scrollPane);
				{
					table = new JTable();
//					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null }, },
							new String[] { "Bill No.", "Item Name", "Item Price", "Quantity", "Amount", "Date",
									"Time" }) {
						boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
					table.getColumnModel().getColumn(0).setResizable(false);
					table.getColumnModel().getColumn(1).setResizable(false);
					table.getColumnModel().getColumn(2).setResizable(false);
					table.getColumnModel().getColumn(3).setResizable(false);
					table.getColumnModel().getColumn(4).setResizable(false);
					table.getColumnModel().getColumn(5).setResizable(false);
					table.getColumnModel().getColumn(6).setResizable(false);
					scrollPane.setViewportView(table);
				}
			}
		}

		array = myList;
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
						// JOptionPane.showMessageDialog(null, myList);

					}

				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void set(String[] myList) {
		this.array = myList;
	}

	private void showtheitems() {
		try {

			model = (DefaultTableModel) table.getModel();

			int nrow = array.length / 4;
			int ncol = table.getColumnCount();

			 JOptionPane.showMessageDialog(null, array[1]);
			 JOptionPane.showMessageDialog(null, nrow);
			 JOptionPane.showMessageDialog(null, ncol);

			int a = 0;
			model = (DefaultTableModel) table.getModel();

			for (int i = 0; i < nrow; i++) {
				for (int j = 1; j < 5; j++) {

					model.setValueAt(String.valueOf(array[a]), i, j);
					a++;
				}

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

}
