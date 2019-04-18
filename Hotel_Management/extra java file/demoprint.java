package hms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Cursor;


public class demoprint extends JDialog {
	public static final String DateFormat = "dd/MM/yyyy";
	public static final String TimeFormat = "HH:mm";

	private final JPanel contentPanel = new JPanel();
	DigitalClock clockobj = new DigitalClock();
	LocalDate today = LocalDate.now();
	LocalTime time = LocalTime.now();

	ClockLabel dateLable = new ClockLabel("date");
	ClockLabel timeLable = new ClockLabel("time");
	
	ClockLabel dayLable = new ClockLabel("day");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			demoprint dialog = new demoprint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public demoprint() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - getWidth()) / 4;
		int y = (dim.height - getHeight()) / 4;
		setBounds(x, y, dim.width * 5 / 20, dim.height * 12 / 20);
		setUndecorated(true);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSpvaigPvtLtd = new JLabel("SPVAIG PVT. LTD.");
			lblSpvaigPvtLtd.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSpvaigPvtLtd.setHorizontalAlignment(SwingConstants.CENTER);
			lblSpvaigPvtLtd.setBounds(10, 26, 321, 24);
			contentPanel.add(lblSpvaigPvtLtd);
		}
		{
			JLabel lblVaranasiUttarPradesh = new JLabel("Varanasi, Uttar Pradesh-22100*");
			lblVaranasiUttarPradesh.setHorizontalAlignment(SwingConstants.CENTER);
			lblVaranasiUttarPradesh.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblVaranasiUttarPradesh.setBounds(10, 47, 321, 14);
			contentPanel.add(lblVaranasiUttarPradesh);
		}
		{
			JLabel lblPhoneNo = new JLabel("Phone No.: *****-*****");
			lblPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
			lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblPhoneNo.setBounds(10, 65, 321, 14);
			contentPanel.add(lblPhoneNo);
		}
		{
			JLabel lblGstNo = new JLabel("GST No.:**************");
			lblGstNo.setHorizontalAlignment(SwingConstants.CENTER);
			lblGstNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblGstNo.setBounds(10, 82, 321, 14);
			contentPanel.add(lblGstNo);
		}
		{
			JLabel lblDate = new JLabel("Date:");
			lblDate.setHorizontalAlignment(SwingConstants.LEFT);
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblDate.setBounds(10, 104, 34, 14);
			contentPanel.add(lblDate);
		}
		{
			JLabel label = new JLabel("");
			label.setText(nowdate());
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label.setBounds(41, 105, 76, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			label.setText(now());
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label.setBounds(41, 126, 39, 14);
			contentPanel.add(label);
		}
		{
			JLabel lblTime = new JLabel("Time:");
			lblTime.setHorizontalAlignment(SwingConstants.LEFT);
			lblTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblTime.setBounds(10, 125, 34, 14);
			contentPanel.add(lblTime);
		}
		{
			JLabel lblDineIn = new JLabel("DINE IN:");
			lblDineIn.setHorizontalAlignment(SwingConstants.LEFT);
			lblDineIn.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDineIn.setBounds(41, 151, 76, 14);
			contentPanel.add(lblDineIn);
		}
		{
			JLabel lblTableNumber = new JLabel("TABLE No.:*");
			lblTableNumber.setHorizontalAlignment(SwingConstants.LEFT);
			lblTableNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblTableNumber.setBounds(122, 152, 107, 14);
			contentPanel.add(lblTableNumber);
		}
		{
			JLabel lblInvoice = new JLabel("Invoice:");
			lblInvoice.setHorizontalAlignment(SwingConstants.LEFT);
			lblInvoice.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblInvoice.setBounds(207, 107, 59, 14);
			contentPanel.add(lblInvoice);
		}
		{
			JLabel label = new JLabel("****");
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label.setBounds(265, 107, 46, 14);
			contentPanel.add(label);
		}
		{
			JLabel lblNewLabel = new JLabel(" name");
			lblNewLabel.setBounds(41, 185, 46, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblMobileNo = new JLabel("Mobile No.");
			lblMobileNo.setBounds(220, 185, 91, 14);
			contentPanel.add(lblMobileNo);
		}
		{
			JLabel lblQty = new JLabel("Qty");
			lblQty.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblQty.setBounds(26, 210, 46, 14);
			contentPanel.add(lblQty);
		}
		{
			JLabel lblProduct = new JLabel("Product");
			lblProduct.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblProduct.setBounds(69, 210, 46, 14);
			contentPanel.add(lblProduct);
		}
		{
			JLabel lblRate = new JLabel("Rate");
			lblRate.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblRate.setBounds(183, 211, 46, 14);
			contentPanel.add(lblRate);
		}
		{
			JLabel lblAmt = new JLabel("Amt.");
			lblAmt.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblAmt.setBounds(265, 210, 46, 14);
			contentPanel.add(lblAmt);
		}
		{
			JLabel lblitemqty = new JLabel("$Itemqty");
			lblitemqty.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblitemqty.setBounds(10, 248, 62, 14);
			contentPanel.add(lblitemqty);
		}
		{
			JLabel lblitemnme = new JLabel("$Itemnme");
			lblitemnme.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblitemnme.setBounds(69, 248, 104, 14);
			contentPanel.add(lblitemnme);
		}
		{
			JLabel lblrate = new JLabel("$Rate");
			lblrate.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblrate.setBounds(183, 249, 46, 14);
			contentPanel.add(lblrate);
		}
		{
			JLabel lblamt = new JLabel("$Amt.");
			lblamt.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblamt.setBounds(265, 248, 46, 14);
			contentPanel.add(lblamt);
		}
		{
			JLabel lblSubtotal = new JLabel("Subtotal");
			lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblSubtotal.setBounds(10, 313, 104, 14);
			contentPanel.add(lblSubtotal);
		}
		{
			JLabel lblsubtotal = new JLabel("$CGST");
			lblsubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
			lblsubtotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblsubtotal.setBounds(207, 332, 104, 14);
			contentPanel.add(lblsubtotal);
		}
		{
			JLabel lblCgst = new JLabel("CGST");
			lblCgst.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblCgst.setBounds(10, 332, 104, 14);
			contentPanel.add(lblCgst);
		}
		{
			JLabel lblSgst = new JLabel("SGST");
			lblSgst.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblSgst.setBounds(10, 348, 104, 14);
			contentPanel.add(lblSgst);
		}
		{
			JLabel lblGrandTotal = new JLabel("Grand Total");
			lblGrandTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblGrandTotal.setBounds(10, 365, 104, 14);
			contentPanel.add(lblGrandTotal);
		}
		{
			JLabel lblRoundOff = new JLabel("Round Off:");
			lblRoundOff.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblRoundOff.setBounds(10, 384, 104, 14);
			contentPanel.add(lblRoundOff);
		}
		{
			JLabel lblNetPayable = new JLabel("NET PAYABLE");
			lblNetPayable.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNetPayable.setBounds(26, 409, 104, 14);
			contentPanel.add(lblNetPayable);
		}
		{
			JButton button = new JButton("Cancel");
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button.setActionCommand("Cancel");
			button.setBounds(265, 436, 76, 23);
			contentPanel.add(button);
		}
		{
			JLabel lblWwwspvaigin = new JLabel("www.spvaig.in");
			lblWwwspvaigin.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblWwwspvaigin.setBounds(41, 438, 104, 14);
			contentPanel.add(lblWwwspvaigin);
		}
		{
			JLabel label = new JLabel("$Subtotal");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 15));
			label.setBounds(207, 315, 104, 14);
			contentPanel.add(label);
		}
		{
			JLabel lblsgst = new JLabel("$SGST");
			lblsgst.setHorizontalAlignment(SwingConstants.RIGHT);
			lblsgst.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblsgst.setBounds(207, 350, 104, 14);
			contentPanel.add(lblsgst);
		}
		{
			JLabel lblgtotal = new JLabel("$GTotal");
			lblgtotal.setHorizontalAlignment(SwingConstants.RIGHT);
			lblgtotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblgtotal.setBounds(207, 367, 104, 14);
			contentPanel.add(lblgtotal);
		}
		{
			JLabel label = new JLabel("$0.00");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 15));
			label.setBounds(207, 386, 104, 14);
			contentPanel.add(label);
		}
		{
			JLabel lbltotal = new JLabel("$TOTAL");
			lbltotal.setHorizontalAlignment(SwingConstants.RIGHT);
			lbltotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbltotal.setBounds(207, 411, 104, 14);
			contentPanel.add(lbltotal);
		}
		{
			JButton btnPrint = new JButton("Print");
			btnPrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnPrint.setEnabled(false);
			btnPrint.setActionCommand("Cancel");
			btnPrint.setBounds(183, 436, 76, 23);
			contentPanel.add(btnPrint);
		}
		{
			JLabel lblPreviewScreen = new JLabel("Preview Screen");
			lblPreviewScreen.setHorizontalAlignment(SwingConstants.CENTER);
			lblPreviewScreen.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblPreviewScreen.setBounds(0, 1, 341, 14);
			contentPanel.add(lblPreviewScreen);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBackground(Color.WHITE);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(271, 5, 65, 23);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.setLayout(null);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public static String nowdate() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(DateFormat);
		return format.format(cal.getTime());

	}

	public static String now() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(TimeFormat);
		return format.format(cal.getTime());

	}
}
