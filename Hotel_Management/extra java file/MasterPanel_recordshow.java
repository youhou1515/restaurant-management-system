package hms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Cursor;

public class MasterPanel_recordshow extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public MasterPanel_recordshow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MasterPanel_recordshow.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, dim.width, dim.height * 19 / 20);
//		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton button = new JButton("");
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						showpurchaserecord showpurchaseobj;

						showpurchaseobj = new showpurchaserecord();

						showpurchaseobj.toFront();
//						showpurchaseobj.setModal(true);
						showpurchaseobj.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			button.setIcon(new ImageIcon(MasterPanel_recordshow.class.getResource("/hms/images/purchaselabelshow.jpg")));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setBorder(null);
			button.setBackground(SystemColor.inactiveCaptionBorder);
			button.setBounds(190, 54, 197, 198);
			contentPanel.add(button);
		}
		{
			JButton btnPurchaseRecord = new JButton("PURCHASE RECORD");
			btnPurchaseRecord.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnPurchaseRecord.setBorder(null);
			btnPurchaseRecord.setBackground(SystemColor.inactiveCaptionBorder);
			btnPurchaseRecord.setBounds(190, 252, 197, 23);
			contentPanel.add(btnPurchaseRecord);
		}
		{
			JButton button = new JButton("");
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.setIcon(new ImageIcon(MasterPanel_recordshow.class.getResource("/hms/images/gp-staff_3 - Copy.jpg")));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showstaffdetails showstaffobj;

					showstaffobj = new showstaffdetails();

					showstaffobj.toFront();
//					showstaffobj.setModal(true);
					showstaffobj.setVisible(true);
				}
			});
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setBorder(null);
			button.setBackground(SystemColor.inactiveCaptionBorder);
			button.setBounds(512, 54, 197, 198);
			contentPanel.add(button);
		}
		{
			JButton btnStaffRecord = new JButton("STAFF RECORD");
			btnStaffRecord.setFocusTraversalPolicyProvider(true);
			btnStaffRecord.setFocusTraversalKeysEnabled(false);
			btnStaffRecord.setFocusPainted(false);
			btnStaffRecord.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnStaffRecord.setBorder(null);
			btnStaffRecord.setBackground(SystemColor.inactiveCaptionBorder);
			btnStaffRecord.setBounds(512, 252, 197, 23);
			contentPanel.add(btnStaffRecord);
		}
		{
			JButton btnBillOrderRecord = new JButton("BILL ORDER RECORD");
			btnBillOrderRecord.setFocusTraversalPolicyProvider(true);
			btnBillOrderRecord.setFocusTraversalKeysEnabled(false);
			btnBillOrderRecord.setFocusPainted(false);
			btnBillOrderRecord.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnBillOrderRecord.setBorder(null);
			btnBillOrderRecord.setBackground(SystemColor.inactiveCaptionBorder);
			btnBillOrderRecord.setBounds(789, 252, 197, 23);
			contentPanel.add(btnBillOrderRecord);
		}
		{
			JButton button = new JButton("");
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.setIcon(new ImageIcon(MasterPanel_recordshow.class.getResource("/hms/images/Show-off-your-invoice.jpg")));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					showbillrecord showbillobj = new showbillrecord();
//					showbillobj.toFront();
////					showbillobj.setModal(true);
//					showbillobj.setVisible(true);
				}
			});
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setBorder(null);
			button.setBackground(SystemColor.inactiveCaptionBorder);
			button.setBounds(789, 54, 197, 198);
			contentPanel.add(button);
		}
		{
			JButton button = new JButton("");
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						showissuestock_record showbillobj = new showissuestock_record();
						showbillobj.toFront();
						showbillobj.setModal(true);
						showbillobj.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			button.setIcon(new ImageIcon(MasterPanel_recordshow.class.getResource("/hms/images/stockx-homepage-logo.png")));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setBorder(null);
			button.setBackground(SystemColor.inactiveCaptionBorder);
			button.setBounds(277, 412, 197, 198);
			contentPanel.add(button);
		}
		{
			JButton btnStockIssueRecord = new JButton("Stock Issue Record");
			btnStockIssueRecord.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnStockIssueRecord.setBorder(null);
			btnStockIssueRecord.setBackground(SystemColor.inactiveCaptionBorder);
			btnStockIssueRecord.setBounds(277, 610, 197, 23);
			contentPanel.add(btnStockIssueRecord);
		}
		
		JButton button = new JButton("");
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setIcon(new ImageIcon(MasterPanel_recordshow.class.getResource("/hms/images/images.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showpartyrecord showbillobj = new showpartyrecord();
					showbillobj.toFront();
//					showbillobj.setModal(true);
					showbillobj.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorder(null);
		button.setBackground(SystemColor.inactiveCaptionBorder);
		button.setBounds(669, 412, 197, 198);
		contentPanel.add(button);
		
		JButton btnPurchasePartyRecord = new JButton("PURCHASE PARTY RECORD");
		btnPurchasePartyRecord.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnPurchasePartyRecord.setBorder(null);
		btnPurchasePartyRecord.setBackground(SystemColor.inactiveCaptionBorder);
		btnPurchasePartyRecord.setBounds(646, 610, 254, 23);
		contentPanel.add(btnPurchasePartyRecord);
		{
			JLabel lblRecordMasterpanel = new JLabel("RECORD  MASTERPANEL");
			lblRecordMasterpanel.setOpaque(true);
			lblRecordMasterpanel.setHorizontalAlignment(SwingConstants.CENTER);
			lblRecordMasterpanel.setForeground(Color.BLACK);
			lblRecordMasterpanel.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblRecordMasterpanel.setBackground(SystemColor.inactiveCaptionBorder);
			lblRecordMasterpanel.setBounds(0, 0, 1350, 31);
			contentPanel.add(lblRecordMasterpanel);
		}
	}
}
