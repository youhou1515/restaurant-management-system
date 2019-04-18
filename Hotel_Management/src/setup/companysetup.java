package setup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import hms.Databaseconnection;
import hms.Tax_Master;

public class companysetup extends JFrame {

	private final JPanel contentPanel = new JPanel();
	Databaseconnection dbcon = new Databaseconnection();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtIndia;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_2;
	JFileChooser fc = new JFileChooser();
	FileInputStream fis;
	ImageIcon format = null;

	String pathName;
	private JLabel lblUploadLogo;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	public companysetup() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tax_Master.class.getResource("/hms/images/food-128.png")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(270, 60, dim.width * 15 / 20, dim.height * 17 / 20);
		setUndecorated(true);
		setType(javax.swing.JFrame.Type.UTILITY);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblShowAllVehicle = new JLabel("Company Setup");
		lblShowAllVehicle.setOpaque(true);
		lblShowAllVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowAllVehicle.setForeground(Color.WHITE);
		lblShowAllVehicle.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblShowAllVehicle.setBackground(Color.BLACK);
		lblShowAllVehicle.setBounds(0, 0, 1092, 31);
		contentPanel.add(lblShowAllVehicle);

		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(40, 57, 477, 578);
		contentPanel.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(171, 54, 296, 36);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblCompanyDetails = new JLabel("Company Details");
		lblCompanyDetails.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCompanyDetails.setBounds(171, 11, 143, 19);
		panel.add(lblCompanyDetails);

		JLabel lblCompanyId = new JLabel("Company ID*");
		lblCompanyId.setBounds(37, 61, 98, 19);
		panel.add(lblCompanyId);
		lblCompanyId.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblCompanyName = new JLabel("Company Name*");
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCompanyName.setBounds(37, 125, 122, 19);
		panel.add(lblCompanyName);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(171, 110, 296, 36);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(171, 166, 296, 36);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(171, 223, 296, 36);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(171, 278, 296, 36);
		panel.add(textField_4);

		JLabel lblAddress = new JLabel("Address*");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(37, 177, 122, 19);
		panel.add(lblAddress);

		JLabel lblCity = new JLabel("City with Pincode*");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCity.setBounds(37, 234, 122, 19);
		panel.add(lblCity);

		JLabel lblState = new JLabel("State*");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblState.setBounds(37, 289, 122, 19);
		panel.add(lblState);

		txtIndia = new JTextField();
		txtIndia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIndia.setText("India");
		txtIndia.setColumns(10);
		txtIndia.setBounds(171, 334, 296, 36);
		panel.add(txtIndia);

		JLabel lblCountry = new JLabel("Country*");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCountry.setBounds(37, 341, 122, 19);
		panel.add(lblCountry);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_5.setColumns(10);
		textField_5.setBounds(171, 390, 296, 36);
		panel.add(textField_5);

		JLabel lblEmail = new JLabel("Phone*");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(37, 398, 122, 19);
		panel.add(lblEmail);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_6.setColumns(10);
		textField_6.setBounds(171, 446, 296, 36);
		panel.add(textField_6);

		JLabel lblGstin = new JLabel("GSTIN*");
		lblGstin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGstin.setBounds(37, 458, 122, 19);
		panel.add(lblGstin);

		btnNewButton = new JButton("Save");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String companyid = textField.getText().trim();

					String companyname = textField_1.getText().trim();
					String address = textField_2.getText().trim();
					String city = textField_3.getText().trim();
					String state = textField_4.getText().trim();
					String country = txtIndia.getText().trim();
					String phoneno = textField_5.getText().trim();
					String gstin = textField_6.getText().trim();

					if (companyid.equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter Company ID");
					} else if (companyname.toString().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter Company Name");
					} else if (address.toString().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter Address");
					} else if (city.toString().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter city");
					} else if (state.toString().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter state");
					} else if (gstin.toString().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter GSTIN No.");
					} else if (phoneno.toString().equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter phone no.");
					}

					else {
						dbcon.companycreate(companyid, companyname, address, city, state, country, phoneno, gstin, fis, pathName);

						showcompanydetails();

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Please Upload Logo/Image" + e);
				}

			}
		});
		btnNewButton.setBounds(171, 513, 157, 36);
		panel.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("Update Logo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					dbcon.updatecompany(fis, textField_1.getText().toString(), pathName);
					showcompanydetails();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Please Upload Logo/Image" + e);
				}
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2.setBounds(171, 513, 157, 36);
		panel.add(btnNewButton_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Upload Logo*",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(645, 366, 263, 99);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		btnNewButton_1 = new JButton("Click to Upload");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FileFilter filter = new FileNameExtensionFilter("PNG file", "png");
					fc.setFileFilter(filter);
					int response = fc.showOpenDialog(null);
					try {
						if (response == JFileChooser.APPROVE_OPTION) {
							pathName = fc.getSelectedFile().getPath();

							ImageIcon icon = new ImageIcon(pathName);
							lblUploadLogo.setIcon(icon);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}

			}
		});
		btnNewButton_1.setBounds(59, 38, 161, 39);
		panel_1.add(btnNewButton_1);

		lblUploadLogo = new JLabel("");
		lblUploadLogo.setBounds(644, 99, 295, 256);
		contentPanel.add(lblUploadLogo);

		JLabel lblWidthXHeight = new JLabel("* Size Maximum: width:293 x height:254");
		lblWidthXHeight.setBounds(645, 469, 263, 14);
		contentPanel.add(lblWidthXHeight);
		try {
			showcompanydetails();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showcompanydetails() throws SQLException {
		try {

			dbcon.connect();
			String sql = "Select companyimage,companyid,companyname,address,city,state,country,phoneno,gstin from companyprofile";
			dbcon.pst = dbcon.conn.prepareStatement(sql);
			dbcon.rs = dbcon.pst.executeQuery();
			while (dbcon.rs.next()) {
//				pathName=dbcon.rs.getString("logopath");
//				byte[] imagedata = dbcon.rs.getBytes("companyimage");
				byte[] imagedata = dbcon.rs.getBytes("companyimage");

				ByteArrayInputStream bis = new ByteArrayInputStream(imagedata);
				BufferedImage bImage2 = ImageIO.read(bis);
//
				BufferedImage newimage = resizeImage(bImage2, 293, 254);
//
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ImageIO.write(newimage, "png", bos);

				byte[] data = bos.toByteArray();

				format = new ImageIcon(data);
				
//				format = new ImageIcon(imagedata);
				lblUploadLogo.setIcon(format);
				textField.setText(dbcon.rs.getString("companyid"));
				textField_1.setText(dbcon.rs.getString("companyname"));
				textField_2.setText(dbcon.rs.getString("address"));
				textField_3.setText(dbcon.rs.getString("city"));
				textField_4.setText(dbcon.rs.getString("state"));
				txtIndia.setText(dbcon.rs.getString("country"));
				textField_5.setText(dbcon.rs.getString("phoneno"));
				textField_6.setText(dbcon.rs.getString("gstin"));

				textField.setEditable(false);
				textField_1.setEditable(false);
				textField_2.setEditable(false);
				textField_3.setEditable(false);
				textField_4.setEditable(false);
				txtIndia.setEditable(false);
				textField_5.setEditable(false);
				textField_6.setEditable(false);

				btnNewButton.setVisible(false);

//				btnNewButton_1.setEnabled(false);

			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		} finally {
			dbcon.conn.close();
		}
	}
	
	public static BufferedImage resizeImage(BufferedImage image, int areaWidth, int areaHeight) {
		float scaleX = (float) areaWidth / image.getWidth();
		float scaleY = (float) areaHeight / image.getHeight();
		float scale = Math.min(scaleX, scaleY);
		int w = Math.round(image.getWidth() * scale);
		int h = Math.round(image.getHeight() * scale);

		int type = image.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;

		boolean scaleDown = scale < 1;

		if (scaleDown) {
			// multi-pass bilinear div 2
			int currentW = image.getWidth();
			int currentH = image.getHeight();
			BufferedImage resized = image;
			while (currentW > w || currentH > h) {
				currentW = Math.max(w, currentW / 2);
				currentH = Math.max(h, currentH / 2);

				BufferedImage temp = new BufferedImage(currentW, currentH, type);
				Graphics2D g2 = temp.createGraphics();
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.drawImage(resized, 0, 0, currentW, currentH, null);
				g2.dispose();
				resized = temp;
			}
			return resized;
		} else {
			Object hint = scale > 2 ? RenderingHints.VALUE_INTERPOLATION_BICUBIC
					: RenderingHints.VALUE_INTERPOLATION_BILINEAR;

			BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = resized.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(image, 0, 0, w, h, null);
			g2.dispose();
			return resized;
		}
	}
}
