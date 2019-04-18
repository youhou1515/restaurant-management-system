package hms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import com.toedter.calendar.JDateChooser;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class pnl_page extends JFrame {

	private JPanel contentPane;
	int i = 0;
	Databaseconnection dbcon = new Databaseconnection();
	DefaultPieDataset p = new DefaultPieDataset();
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_4;
	private JLabel label_3;
	private ChartPanel myChart;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JButton btnClear;
	private JLabel salarypart;
	private JLabel salepart;
	private JLabel purchasepart;
	private JLabel lblNewLabel;
//	PieChartExample pieobj=new PieChartExample();

	public pnl_page() {
		setType(javax.swing.JFrame.Type.UTILITY);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 16 / 20, dim.height * 17 / 20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
		setUndecorated(true);
		contentPane = new JPanel();
//		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Total Purchase:");
		label.setOpaque(true);
		label.setBackground(SystemColor.activeCaption);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		label.setBounds(480, 126, 287, 29);
		contentPane.add(label);

		label_1 = new JLabel("0.0");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBackground(new Color(240, 255, 255));
		label_1.setOpaque(true);
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_1.setBounds(767, 126, 287, 29);
		contentPane.add(label_1);

		JLabel lblProfitLoss = new JLabel("Profit & Loss");
		lblProfitLoss.setBorder(
				new BevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		lblProfitLoss.setOpaque(true);
		lblProfitLoss.setBackground(Color.GRAY);
		lblProfitLoss.setForeground(Color.WHITE);
		lblProfitLoss.setFont(new Font("Calibri", Font.ITALIC, 22));
		lblProfitLoss.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfitLoss.setBounds(0, 11, 1152, 38);
		contentPane.add(lblProfitLoss);

		JSeparator separator = new JSeparator();
		separator.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		separator.setOpaque(true);
		separator.setForeground(Color.WHITE);
		separator.setBounds(0, 98, 1152, 2);
		contentPane.add(separator);

		label_2 = new JLabel("0.0");
		label_2.setOpaque(true);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(new Color(0, 0, 0));
		label_2.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_2.setBackground(new Color(240, 255, 255));
		label_2.setBounds(767, 196, 287, 29);
		contentPane.add(label_2);

		JLabel lblTotalSale = new JLabel("Total Sale:");
		lblTotalSale.setOpaque(true);
		lblTotalSale.setForeground(Color.BLACK);
		lblTotalSale.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTotalSale.setBackground(SystemColor.activeCaption);
		lblTotalSale.setBounds(480, 196, 287, 29);
		contentPane.add(lblTotalSale);

		JLabel lblTotalExpenses = new JLabel("Total Salary Exp:");
		lblTotalExpenses.setOpaque(true);
		lblTotalExpenses.setForeground(Color.BLACK);
		lblTotalExpenses.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTotalExpenses.setBackground(SystemColor.activeCaption);
		lblTotalExpenses.setBounds(480, 265, 287, 29);
		contentPane.add(lblTotalExpenses);

		label_4 = new JLabel("0.0");
		label_4.setOpaque(true);
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setForeground(new Color(0, 0, 0));
		label_4.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_4.setBackground(new Color(240, 255, 255));
		label_4.setBounds(767, 265, 287, 29);
		contentPane.add(label_4);

		JLabel lblTotalProfitloss = new JLabel("Total Profit/Loss:");
		lblTotalProfitloss.setOpaque(true);
		lblTotalProfitloss.setForeground(Color.WHITE);
		lblTotalProfitloss.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTotalProfitloss.setBackground(SystemColor.activeCaption);
		lblTotalProfitloss.setBounds(480, 305, 287, 29);
		contentPane.add(lblTotalProfitloss);

		label_3 = new JLabel("0.0");
		label_3.setOpaque(true);
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_3.setBackground(SystemColor.textHighlight);
		label_3.setBounds(767, 305, 287, 29);
		contentPane.add(label_3);
		JFreeChart chart = ChartFactory.createPieChart3D("INCOME/EXPENDITURE", p, rootPaneCheckingEnabled,
				rootPaneCheckingEnabled, rootPaneCheckingEnabled);

		p.setValue("Income", 50);
		p.setValue("Expense", 50);

		TextTitle tt = new TextTitle("Popular fruits this season", new Font("Arial", Font.CENTER_BASELINE, 12));
		tt.setPadding(5, 5, 5, 5);
		final PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
//		add(panel);
		Timer t = new Timer(150, new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				// Set the start angle, this increases everytime
				// timer is executed
				plot.setStartAngle(i--);
			}
		});

		t.start();

		dateChooser = new JDateChooser();
		dateChooser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dateChooser_1.requestFocus();

				}
			}
		});
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setDate(new Date());
		dateChooser.setBounds(238, 60, 258, 29);
		contentPane.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		dateChooser_1.setDate(new Date());
		dateChooser_1.setBounds(554, 60, 258, 29);
		contentPane.add(dateChooser_1);

		JLabel label_5 = new JLabel("Search Between Dates");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(63, 65, 150, 14);
		contentPane.add(label_5);

		JButton button = new JButton("Click to Search");
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnClear.requestFocus();

				}

			}
		});
		button.setForeground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showprofitloss();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button.setBackground(Color.GRAY);
		button.setBounds(814, 60, 130, 29);
		contentPane.add(button);

		JLabel lblto = new JLabel("-TO-");
		lblto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblto.setBounds(512, 63, 46, 24);
		contentPane.add(lblto);

		btnClear = new JButton("Clear");
		btnClear.setForeground(Color.WHITE);
		btnClear.setBackground(Color.GRAY);
		btnClear.setBounds(946, 60, 108, 29);
		contentPane.add(btnClear);

		JPanel panel = new JPanel();

		panel.setBounds(480, 345, 574, 307);
		panel.validate();
		contentPane.add(panel);
		contentPane.setBackground(Color.WHITE);
		panel.setLayout(null);
		myChart = new ChartPanel(chart);
		myChart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		myChart.setBounds(0, 0, 574, 307);
		panel.add(myChart);
		myChart.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		myChart.setForeground(Color.WHITE);
		myChart.setBackground(Color.WHITE);

		myChart.setEnabled(true);

		salarypart = new JLabel("New label");
		salarypart.setForeground(Color.WHITE);
		salarypart.setFont(new Font("Dialog", Font.PLAIN, 15));
		salarypart.setHorizontalAlignment(SwingConstants.CENTER);
		salarypart.setBackground(Color.BLACK);
		salarypart.setOpaque(true);
		salarypart.setBounds(480, 236, 574, 29);
		contentPane.add(salarypart);

		salepart = new JLabel("New label");
		salepart.setForeground(Color.WHITE);
		salepart.setFont(new Font("Dialog", Font.PLAIN, 15));
		salepart.setHorizontalAlignment(SwingConstants.CENTER);
		salepart.setOpaque(true);
		salepart.setBackground(Color.BLACK);
		salepart.setBounds(480, 166, 574, 29);
		contentPane.add(salepart);

		purchasepart = new JLabel("New label");
		purchasepart.setForeground(Color.WHITE);
		purchasepart.setFont(new Font("Dialog", Font.PLAIN, 15));
		purchasepart.setHorizontalAlignment(SwingConstants.CENTER);
		purchasepart.setOpaque(true);
		purchasepart.setBackground(Color.BLACK);
		purchasepart.setBounds(480, 98, 574, 29);
		contentPane.add(purchasepart);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(pnl_page.class.getResource("/hms/images/pnl.png")));
		lblNewLabel.setBounds(115, 244, 287, 267);
		contentPane.add(lblNewLabel);
		try {
			showprofitloss();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				button.requestFocus();
			}
		});

	}

	public double datequery4purchase(String data13, String data14, String sql) throws SQLException {
		dbcon.connect();

		dbcon.st = dbcon.conn.createStatement();
		dbcon.rs = dbcon.st.executeQuery(sql);
		double totalamount = 0;
		while (dbcon.rs.next()) {
			String datas = dbcon.rs.getString(1);
			double d = Double.parseDouble(datas);
			totalamount = totalamount + d;
		}
		return totalamount;

	}

	public double datequery4sum(String data13, String data14, String sql) throws SQLException {
		double totalamount = 0;
		try {
			dbcon.connect();

			dbcon.st = dbcon.conn.createStatement();
			dbcon.rs = dbcon.st.executeQuery(sql);

			if (dbcon.rs.next()) {
				totalamount = dbcon.rs.getDouble(1);

//			totalamount = totalamount + d;
			}

		}

		catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		} finally {
			dbcon.conn.close();
		}
		return totalamount;

	}

	public void showprofitloss() throws SQLException {
		String data13 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String data14 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
//		datequery4purchase(String data13, String data14)
		double totalpurchase = 0;
		double totalsale1 = 0;
		double totalexpense = 0;
		double totaldailysalaryexpense = 0;
		double totalpurchasedaily = 0;

		double totalprofitnloss = 0;

		double bookingadvancetotal = 0;
		double bookingpaidtotal = 0;

		// total purchase
		String sqlpurchase = "Select Totalamount from rmspurchase where billdate between '" + data13 + "' and '"
				+ data14 + "'";
		totalpurchase = datequery4purchase(data13, data14, sqlpurchase);

		String sqlpurchasedaily = "Select amount from dailyotherexpenses where entereddate between '" + data13
				+ "' and '" + data14 + "'";
		totalpurchasedaily = datequery4purchase(data13, data14, sqlpurchasedaily);

		double sumpurchase = totalpurchase + totalpurchasedaily;
		double totalpurchase_roundoff = Math.round(sumpurchase * 100.0) / 100.0;

		purchasepart.setText(String.valueOf("Daily Purchase:" + (Math.round(totalpurchasedaily * 100.0) / 100.0)  + " || Purchase:" + (Math.round(totalpurchase * 100.0) / 100.0)));
		label_1.setText(String.valueOf(totalpurchase_roundoff));

		// total sale calculation
		String sqlsale = "Select grandtotal from tablecustomerdetail where savedate between '" + data13 + "' and '"
				+ data14 + "'";
		totalsale1 = datequery4purchase(data13, data14, sqlsale);

		String sqlsalebookingadvanceamt = "Select sum(advanceamount) from bookingdetails where entereddate between '"
				+ data13 + "' and '" + data14 + "'";
		bookingadvancetotal = datequery4sum(data13, data14, sqlsalebookingadvanceamt);

		String sqlsalebookingpaidamt = "Select sum(paidamount) from bookingdetails where paiddate between '" + data13
				+ "' and '" + data14 + "'";
		bookingpaidtotal = datequery4sum(data13, data14, sqlsalebookingpaidamt);

		double totalsale = totalsale1 + bookingadvancetotal + bookingpaidtotal;

		salepart.setText(String
				.valueOf("Booking Amount:" + ((Math.round(bookingadvancetotal * 100.0) / 100.0) + (Math.round(bookingpaidtotal * 100.0) / 100.0)) + " || Sale:" + (Math.round(totalsale1 * 100.0) / 100.0)));
//		JOptionPane.showMessageDialog(null, bookingadvancetotal+" "+bookingpaidtotal+" "+bookingpaidtotal+" "+totalsale);

		double totalsale_roundoff = Math.round(totalsale * 100.0) / 100.0;
		label_2.setText(String.valueOf(totalsale_roundoff));

		// salary calculation
		String sqlsalary = "Select staff_paidsalary from staffpayment where staff_paymentdate between '" + data13
				+ "' and '" + data14 + "'";
		totalexpense = datequery4purchase(data13, data14, sqlsalary);

		String sqldailysalary = "Select amount from staffotherexpenses where entereddate between '" + data13 + "' and '"
				+ data14 + "'";
		totaldailysalaryexpense = datequery4purchase(data13, data14, sqldailysalary);

		double totalsalary = totalexpense + totaldailysalaryexpense;

		salarypart.setText(String.valueOf("Part Salary:" + (Math.round(totaldailysalaryexpense * 100.0) / 100.0) + " || Salary:" + (Math.round(totalexpense * 100.0) / 100.0)));

		double totalexpense_roundoff = Math.round(totalsalary * 100.0) / 100.0;
		label_4.setText(String.valueOf(totalexpense_roundoff));

		double totalxp = totalpurchase_roundoff + totalexpense_roundoff;
		double totalxp_roundoff = Math.round(totalxp * 100.0) / 100.0;

		totalprofitnloss = totalsale_roundoff - totalxp_roundoff;

		double totalprofitnloss_roundoff = Math.round(totalprofitnloss * 100.0) / 100.0;

		label_3.setText(String.valueOf(totalprofitnloss_roundoff));

		// income+expense;
		double totalnumber = totalxp_roundoff + totalsale_roundoff;

		// income percent
		double percentInDecimal = totalxp_roundoff / totalnumber;
		double totalincome_percent = percentInDecimal * 100;
		int a = (int) totalincome_percent;
		String income = String.valueOf(a);

		// expense percent
		double percentInDecimal1 = totalsale_roundoff / totalnumber;
		double totalincome_percent1 = percentInDecimal1 * 100;
		int b = (int) totalincome_percent1;
		String expense = String.valueOf(b);

//		JOptionPane.showMessageDialog(null, totalincome_percent);
//
//		JOptionPane.showMessageDialog(null, income);

		p.setValue("Income", b);
		p.setValue("Expense", a);
	}
}
