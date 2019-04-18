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
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;

public class MasterPanel extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public MasterPanel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MasterPanel.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, dim.width, dim.height * 19 / 20);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaptionBorder);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNewButton = new JButton("");
			btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnNewButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					try {
						menuentry menuobj;
						menuobj = new menuentry();
						menuobj.toFront();
//						menuobj.setModal(true);
						menuobj.setVisible(true);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			

			btnNewButton.setBorder(null);
			btnNewButton.setBackground(SystemColor.inactiveCaptionBorder);
			btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
			btnNewButton.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/menulabel.png")));
			btnNewButton.setBounds(76, 45, 197, 198);
			contentPanel.add(btnNewButton);
		}

		{
			JButton btnNewButton_1 = new JButton("MENU CONTROLLER");
			btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnNewButton_1.setBorder(null);
			btnNewButton_1.setBackground(SystemColor.inactiveCaptionBorder);
			btnNewButton_1.setBounds(76, 243, 197, 23);
			contentPanel.add(btnNewButton_1);
		}
		{
			JButton button = new JButton("");
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					partynewentry partymaster=new partynewentry();
					partymaster.toFront();
//					partymaster.setModal(true);
					partymaster.setVisible(true);
				}
			});
			button.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/purchaselabel.jpg")));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setBorder(null);
			button.setBackground(SystemColor.inactiveCaptionBorder);
			button.setBounds(310, 45, 197, 198);
			contentPanel.add(button);
		}
		{
			JButton btnPurchaseController = new JButton("PARTY MASTER CONTROLLER");
			btnPurchaseController.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnPurchaseController.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnPurchaseController.setBorder(null);
			btnPurchaseController.setBackground(SystemColor.inactiveCaptionBorder);
			btnPurchaseController.setBounds(292, 243, 261, 23);
			contentPanel.add(btnPurchaseController);
		}
		{
			JButton button = new JButton("");
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Tax_Master taxmasterobj=new Tax_Master();
						taxmasterobj.toFront();
//						taxmasterobj.setModal(true);
						taxmasterobj.setVisible(true);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			button.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/taxlabel.png")));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setBorder(null);
			button.setBackground(SystemColor.inactiveCaptionBorder);
			button.setBounds(581, 45, 197, 198);
			contentPanel.add(button);
		}
		{
			JButton btnTaxController = new JButton("TAX CONTROLLER");
			btnTaxController.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnTaxController.setBorder(null);
			btnTaxController.setBackground(SystemColor.inactiveCaptionBorder);
			btnTaxController.setBounds(563, 243, 233, 23);
			contentPanel.add(btnTaxController);
		}
		
		JButton button = new JButton("");
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Staff_Entry staffentryobj=new Staff_Entry();
				staffentryobj.toFront();
//				staffentryobj.setModal(true);
				staffentryobj.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/stafflabel.png")));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorder(null);
		button.setBackground(SystemColor.inactiveCaptionBorder);
		button.setBounds(824, 45, 197, 198);
		contentPanel.add(button);
		
		JButton btnStaffController = new JButton("STAFF CONTROLLER");
		btnStaffController.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnStaffController.setBorder(null);
		btnStaffController.setBackground(SystemColor.inactiveCaptionBorder);
		btnStaffController.setBounds(806, 243, 233, 23);
		contentPanel.add(btnStaffController);
		{
			JButton button_1 = new JButton("");
			button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					try {
					tablemaster tablemasterobj=new tablemaster();
					tablemasterobj.toFront();
//					tablemasterobj.setModal(true);
					tablemasterobj.setVisible(true);

					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1);
					}
					
				}
			});
			button_1.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/tablelabel2.jpg")));
			button_1.setHorizontalAlignment(SwingConstants.LEFT);
			button_1.setBorder(null);
			button_1.setBackground(SystemColor.inactiveCaptionBorder);
			button_1.setBounds(1049, 45, 197, 198);
			contentPanel.add(button_1);
		}
		{
			JButton btnStaffRecord = new JButton("TABLE CONTROLLER");
			btnStaffRecord.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnStaffRecord.setBorder(null);
			btnStaffRecord.setBackground(SystemColor.inactiveCaptionBorder);
			btnStaffRecord.setBounds(1049, 243, 197, 23);
			contentPanel.add(btnStaffRecord);
		}
		{
			JButton button_2 = new JButton("");
			button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Categorymaster categoryobj = new Categorymaster();
					categoryobj.toFront();
//					categoryobj.setModal(true);
					categoryobj.setVisible(true);
				}
			});
			button_2.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/ob_6c8220_istock-000011869612small.jpg")));
			button_2.setHorizontalAlignment(SwingConstants.LEFT);
			button_2.setBorder(null);
			button_2.setBackground(SystemColor.inactiveCaptionBorder);
			button_2.setBounds(419, 287, 197, 198);
			contentPanel.add(button_2);
		}
		{
			JButton btnCategoryController = new JButton("CATEGORY CONTROLLER");
			btnCategoryController.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnCategoryController.setBorder(null);
			btnCategoryController.setBackground(SystemColor.inactiveCaptionBorder);
			btnCategoryController.setBounds(401, 485, 233, 23);
			contentPanel.add(btnCategoryController);
		}
		{
			JButton button_2 = new JButton("");
			button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Unitmaster unitobj = new Unitmaster();
					unitobj.toFront();
//					unitobj.setModal(true);
					unitobj.setVisible(true);
				}
			});
			button_2.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/units_of_measure.gif")));
			button_2.setHorizontalAlignment(SwingConstants.LEFT);
			button_2.setBorder(null);
			button_2.setBackground(SystemColor.inactiveCaptionBorder);
			button_2.setBounds(680, 287, 197, 198);
			contentPanel.add(button_2);
		}
		{
			JButton btnUnitController = new JButton("UNIT CONTROLLER");
			btnUnitController.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnUnitController.setBorder(null);
			btnUnitController.setBackground(SystemColor.inactiveCaptionBorder);
			btnUnitController.setBounds(662, 485, 233, 23);
			contentPanel.add(btnUnitController);
		}
		{
			JButton button_2 = new JButton("");
			button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					locationmaster locationobj=new locationmaster();
//					locationobj.toFront();
//					locationobj.setModal(true);
//					locationobj.setVisible(true);
				}
				
			});
			button_2.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/locationlabel.png")));
			button_2.setHorizontalAlignment(SwingConstants.LEFT);
			button_2.setBorder(null);
			button_2.setBackground(SystemColor.inactiveCaptionBorder);
			button_2.setBounds(178, 287, 197, 198);
			contentPanel.add(button_2);
		}
		{
			JButton btnLocationController = new JButton("LOCATION CONTROLLER");
			btnLocationController.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnLocationController.setBorder(null);
			btnLocationController.setBackground(SystemColor.inactiveCaptionBorder);
			btnLocationController.setBounds(165, 485, 221, 23);
			contentPanel.add(btnLocationController);
		}
		
		JLabel lblMasterPanel = new JLabel("MASTER PANEL");
		lblMasterPanel.setOpaque(true);
		lblMasterPanel.setBackground(Color.WHITE);
		lblMasterPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblMasterPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMasterPanel.setBounds(0, 11, 1350, 23);
		contentPanel.add(lblMasterPanel);
		{
			JButton button_1 = new JButton("");
			button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
					designation desigobj = new designation();
					desigobj.toFront();
//					desigobj.setModal(true);
					desigobj.setVisible(true);
					}catch (Exception e1) {
						// TODO: handle exception
					}
				}
			});
			button_1.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/maxresdefault.jpg")));
			button_1.setHorizontalAlignment(SwingConstants.LEFT);
			button_1.setBorder(null);
			button_1.setBackground(SystemColor.inactiveCaptionBorder);
			button_1.setBounds(923, 287, 197, 198);
			contentPanel.add(button_1);
		}
		{
			JButton btnDesignationController = new JButton("DESIGNATION CONTROLLER");
			btnDesignationController.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnDesignationController.setBorder(null);
			btnDesignationController.setBackground(SystemColor.inactiveCaptionBorder);
			btnDesignationController.setBounds(905, 485, 233, 23);
			contentPanel.add(btnDesignationController);
		}
		{
			JButton button_1 = new JButton("");
			button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button_1.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/purchaselabel - Copy.png")));
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
					inventory desigobj = new inventory();
					desigobj.toFront();
//					desigobj.setModal(true);
					desigobj.setVisible(true);
					}catch (Exception e1) {
						// TODO: handle exception
					}
				}
			});
			button_1.setHorizontalAlignment(SwingConstants.LEFT);
			button_1.setBorder(null);
			button_1.setBackground(SystemColor.inactiveCaptionBorder);
			button_1.setBounds(505, 536, 129, 123);
			contentPanel.add(button_1);
		}
		{
			JButton btnPurchase = new JButton("Purchase");
			btnPurchase.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnPurchase.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnPurchase.setBorder(null);
			btnPurchase.setBackground(SystemColor.inactiveCaptionBorder);
			btnPurchase.setBounds(493, 658, 153, 23);
			contentPanel.add(btnPurchase);
		}
		{
			JButton button_1 = new JButton("");
			button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button_1.setIcon(new ImageIcon(MasterPanel.class.getResource("/hms/images/stock-issue-icon.png")));
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
//						issue_stock desigobj = new issue_stock();
//						desigobj.toFront();
//						desigobj.setModal(true);
//						desigobj.setVisible(true);
						}catch (Exception e1) {
							// TODO: handle exception
						}
				}
			});
			button_1.setHorizontalAlignment(SwingConstants.LEFT);
			button_1.setBorder(null);
			button_1.setBackground(SystemColor.inactiveCaptionBorder);
			button_1.setBounds(701, 536, 129, 123);
			contentPanel.add(button_1);
		}
		{
			JButton btnStockIssue = new JButton("Stock Issue");
			btnStockIssue.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnStockIssue.setBorder(null);
			btnStockIssue.setBackground(SystemColor.inactiveCaptionBorder);
			btnStockIssue.setBounds(689, 658, 153, 23);
			contentPanel.add(btnStockIssue);
		}
	}
}
