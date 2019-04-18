package hms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Databaseconnection {
	public Connection conn = null;
	Statement st;
	public ResultSet rs;
	public PreparedStatement pst;
	boolean verify = true;

	public Connection connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/londonlive", "root", "root");
		} catch (Exception e) {
			System.err.println("Unable to get Database connection: " + e.toString());
		}
		return conn;
	}

//	public Connection connect() {
//		try {
//			// for run
//			conn = DriverManager.getConnection("jdbc:sqlite:E:\\eclipse workspace\\Hotel_Management\\Database\\londonlive.db");
////			RMS_05092018, Foodfactorydata
//
////			londonlivedb   lldatabase
//			Class.forName("org.sqlite.JDBC");
//
//			// FOR SETUP
//
////			conn = DriverManager.getConnection("jdbc:sqlite:londonlive_dbase.db");
//
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e);
//
//		}
//		return conn;
//	}

	public void insertmenuitem(String itemcode, String itemname, String itemdescription, String itemprice,
			String itemunit, String itemquantity, String itemcategory) throws SQLException {
		try {
			connect();
			st = conn.createStatement();

			String sql = "Insert into menuitem(Code,Name,Description,Price,Unit,Quantity,Category) VALUES('" + itemcode
					+ "','" + itemname + "','" + itemdescription + "','" + itemprice + "','" + itemunit + "','"
					+ itemquantity + "','" + itemcategory + "')";
			int x = st.executeUpdate(sql);

			JOptionPane.showMessageDialog(null, "Date Saved Successfully");
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Already Exists");
		} finally {
			conn.close();
		}

	}

	public boolean insertinventory(String data1, String dataparty, String data2, String data3, String data4,
			String data5, String data6, String data7, String data8, String data9, String data10, String data11,
			String data12, String data13, String data14, String data15, String data18, String data17)
			throws SQLException {

		try {
			connect();
			st = conn.createStatement();
//			 JOptionPane.showMessageDialog(null, "here2");

			String sql = "Insert into inventorypurchase(invoice,partyname,productname,gstin,batchno,mrp,additionalinfo,billdate,foodlicense,unit,quantity,hsn,discount,purchaserate,duedate,receivedate,expiry,maxdiscount) VALUES('"
					+ data1 + "','" + dataparty + "','" + data2 + "','" + data3 + "','" + data4 + "','" + data5 + "','"
					+ data6 + "','" + data7 + "','" + data8 + "','" + data9 + "','" + data10 + "','" + data11 + "','"
					+ data12 + "','" + data13 + "','" + data14 + "','" + data15 + "','" + data18 + "','" + data17
					+ "')";
			int x = st.executeUpdate(sql);
//			JOptionPane.showMessageDialog(null, "here3");
			JOptionPane.showMessageDialog(null, "Data Saved Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}
		return verify;
	}

	// dbcon.updateinventory(data1, productname, batch, mrp, additional, billdate,
	// unit, quantity, hsn, discount, purchaserate, date1,date2,maxdiscount,expiry);
	//
	public void updateinventory(String data1, String productname, String batch, String mrp, String additional,
			String billdate, String unit, String quantity, String hsn, String discount, String purchaserate,
			String date1, String date2, String maxdiscount, String expiry) throws SQLException {
		try {

			connect();

			String sql = "Update inventorypurchaserecord SET productname=?, batchno=?,mrp=?,additionalinfo=?,billdate=?,unit=?,quantity=?,hsn=?,discount=?,purchaserate=?,duedate=?,receivedate=?,maxdiscount=?,expiry=? WHERE invoice=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, productname);
			pst.setString(2, batch);
			pst.setString(3, mrp);
			pst.setString(4, additional);
			pst.setString(5, billdate);
			pst.setString(6, unit);
			pst.setString(7, quantity);
			pst.setString(8, hsn);
			pst.setString(9, discount);
			pst.setString(10, purchaserate);
			pst.setString(11, date1);
			pst.setString(12, date2);
			pst.setString(13, maxdiscount);
			pst.setString(14, expiry);
			pst.setString(15, data1);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Updated Successfully!");

		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err);
		} finally {

			conn.close();
		}
	}

	public void updatemenu(String a, String b, String c, String d, String e, String f, String g) throws SQLException {
		try {
			connect();

			String sql = "Update menuitem SET Name=?,Description=?, Price=?,Quantity=?,Unit=?,Category=? WHERE Code=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, b);
			pst.setString(2, c);
			pst.setString(3, d);
			pst.setString(4, e);
			pst.setString(5, f);
			pst.setString(6, g);
			pst.setString(7, a);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Updated Successfully!");

		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, "Already Exists");
		} finally {
			conn.close();
		}
	}

	public void insertable(String data) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into tablelist(tablename) VALUES('" + data + "')";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Added Successfully");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "Already Exists");
		} finally {
			conn.close();
		}

	}

	public void insertproduct(String data) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into productlist(productname) VALUES('" + data + "')";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Added Successfully");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "Already Exists");
		} finally {
			conn.close();
		}

	}

	public void tabledelete(String tablexyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from tablelist where tablename='" + tablexyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}
		// TODO Auto-generated method stub

	}

	public void productdelete(String tablexyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from productlist where productname='" + tablexyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}
		// TODO Auto-generated method stub

	}

	public void unitdelete(String tablexyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from unitlist where unitname='" + tablexyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}
		// TODO Auto-generated method stub

	}

	public void bookingcategorydelete(String tablexyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from bookingcategory where category='" + tablexyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}
		// TODO Auto-generated method stub

	}

	public void insert_unit(String data) throws SQLException {
		try {
			connect();
			st = conn.createStatement();

			String sql = "Insert into unitlist VALUES('" + data + "')";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Added Successfully");

		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "Already Exists");
		} finally {
			conn.close();
		}

	}

//	public void insert_bookingcategory(String data) throws SQLException {
//		try {
//			connect();
//			st = conn.createStatement();
//
//			String sql = "Insert into bookingcategory(category) VALUES('" + data + "')";
//			int x = st.executeUpdate(sql);
//			JOptionPane.showMessageDialog(null, "Added Successfully");
//
//		} catch (Exception z) {
//			JOptionPane.showMessageDialog(null, z);
//		} finally {
//			conn.close();
//		}
//
//	}

	public void insert_bookcategory(String data, double rate) throws SQLException {
		try {
			connect();
			st = conn.createStatement();

			String sql = "Insert into bookingcategory(category,rate) VALUES('" + data + "','" + rate + "')";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Added Successfully");

		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, z);
		} finally {
			conn.close();
		}

	}

	public boolean check_bookcategory(String data) throws SQLException {
		boolean result = false;
		try {

			connect();
			PreparedStatement ps = conn.prepareStatement("Select category from bookingcategory where category=?");
			ps.setString(1, data);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}
		return result;
	}

	public void insert_category(String data) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into categorylist VALUES('" + data + "')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Already Exists!");
		} finally {
			conn.close();
		}
	}

	public void insert_userpanel(String data, String password, String role) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into userpanel VALUES('" + data + "','" + password + "','" + role + "')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Already Exists!");
		} finally {
			conn.close();
		}
	}

	public void categorydelete(String categoryxyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
//			JOptionPane.showMessageDialog(null, categoryxyz);
			String sql = "Delete from categorylist where categoryname='" + categoryxyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void billdelete(String categoryxyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from tablecustomerdetail where Invoice_Number='" + categoryxyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void kotdelete(String categoryxyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from bill_list where bill_no='" + categoryxyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!kotdelete:" + z);
		} finally {
			conn.close();
		}

	}

	public void staffdailyexp_delete(int sno) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from staffotherexpenses where sno='" + sno + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void dailyotherexp_delete(int sno) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from dailyotherexpenses where sno='" + sno + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void partydelete(String categoryxyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from party_details where party_code='" + categoryxyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void staffpaymentdelete(String categoryxyz, String paymentdate, String paidsalary) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from staffpayment where staff_id='" + categoryxyz + "' AND staff_paymentdate='"
					+ paymentdate + "' AND staff_paidsalary='" + paidsalary + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void advancebookingdelete(String slipno) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from bookingdetails where slipno='" + slipno + "' ";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "Error:" + z);
		} finally {
			conn.close();
		}

	}

	public void staffdelete(String categoryxyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from staffdetails where staff_id='" + categoryxyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void invoicepurchasedelete(String categoryxyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from rmspurchase where invoice='" + categoryxyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void issuestockdelete(String categoryxyz) throws SQLException {
		try {
			connect();
//			JOptionPane.showMessageDialog(null, categoryxyz);
			st = conn.createStatement();
			String sql = "Delete from issuestockentry where issueno='" + categoryxyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void userpanel_delete(String categoryxyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from userpanel where usertype='" + categoryxyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");

		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void taxentry(String tax) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
//			INSERT OR REPLACE INTO table(column_list)
//			VALUES(value_list);
			String query = "INSERT INTO gstlist(gst) VALUES('" + tax + "')";
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Added Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Already Exists!");
		} finally {
			conn.close();
		}

	}

	public boolean cashpartyentry(String a, String b, String c, String d, String e, String f, String g, String h,
			String i, String j, String k, Double value) throws SQLException {
		try {
			connect();

			st = conn.createStatement();
			String query = "INSERT INTO party_details(party_code,partyname,partyaddress,city,state,phoneoffice,mobile,gstin,aadharno,panno,fssaino,Balance_Amount) VALUES('"
					+ a + "','" + b + "','" + c + "','" + d + "','" + e + "','" + f + "','" + g + "','" + h + "','" + i
					+ "','" + j + "','" + k + "','" + value + "')";
			int x = st.executeUpdate(query);

			JOptionPane.showMessageDialog(null, "Data Entered Successfully");

		} catch (Exception varia) {

			JOptionPane.showMessageDialog(null, "Duplicate Entry!!Party Code Already Exists");
			verify = false;
		} finally {
			conn.close();
		}
		return verify;
		// JOptionPane.showMessageDialog(null, verify);

	}

	public void updateparty_details(String a, String b, String c, String d, String e, String f, String g, String h,
			String i, String j, String k) throws SQLException {
		try {
			connect();

			String sql = "Update party_details SET partyname=?,partyaddress=?, city=?,state=?,phoneoffice=?,mobile=?,gstin=?,aadharno=?,panno=?,fssaino=? WHERE party_code=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, b);
			pst.setString(2, c);
			pst.setString(3, d);
			pst.setString(4, e);
			pst.setString(5, f);
			pst.setString(6, g);
			pst.setString(7, h);
			pst.setString(8, i);
			pst.setString(9, j);
			pst.setString(10, k);
			pst.setString(11, a);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Updated Successfully!");

		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err);
		} finally {
			conn.close();
		}
	}

	public void staffentry(String text, String text2, String text3, String text4, String text5, Object selectedItem,
			String text6, String text7, String text8, String text9, String text10) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String query = "INSERT INTO staffdetails VALUES('" + text + "','" + text2 + "','" + text3 + "','" + text4
					+ "','" + text5 + "','" + selectedItem + "','" + text6 + "','" + text7 + "','" + text8 + "','"
					+ text9 + "','" + text10 + "')";
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Data Successfully Saved!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Already Exists");
		} finally {
			conn.close();
		}

	}

	public void insert_locationentry(String data) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into locationlist VALUES('" + data + "')";
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Added Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Already Exists");
		} finally {
			conn.close();
		}

	}

	public void locationdelete(String categoryxyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from locationlist where locationname='" + categoryxyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void billitemlist_entry(String bill, String mynewstring, String savetime, String savedate)
			throws SQLException {
		try {
			connect();
			String sql1 = "Insert into bill_item_list(billno,item_name,bill_bookingtime, bill_bookingdate) VALUES('"
					+ bill + "','" + mynewstring + "','" + savetime + "','" + savedate + "')";

			pst = conn.prepareStatement(sql1);
			pst.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void insertuniqueid(String billbreak) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into Uniqueid VALUES('" + billbreak + "')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void deleteuniqueid(String billbreakdelete) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from Uniqueid where idgenerator='" + billbreakdelete + "'";
			int x = st.executeUpdate(sql);

		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void delete_billitemlist(String str) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from bill_list where bill_no='" + str + "'";
			int x = st.executeUpdate(sql);

		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!delete_billitemlist:" + z);
		} finally {
			conn.close();
		}

	}

	public boolean issuestockentry(String issuestocknum, String string, String string2, String string3, String string4,
			String string5, String string6, String string7, String string8) throws SQLException {
		try {
			connect();
			String sql1 = "Insert into issuestockentry(issueno,date,time,productname,availablestock,issuequantity,issuelocation,issueto,issueby) VALUES('"
					+ issuestocknum + "','" + string + "','" + string2 + "','" + string3 + "','" + string4 + "','"
					+ string5 + "','" + string6 + "','" + string7 + "','" + string8 + "')";

			pst = conn.prepareStatement(sql1);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Issued Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}
		return verify;
	}

	public void issuestockupdate(String totalstock_stringtype, String invoicelabel) throws SQLException {
		try {
			connect();

			String sql = "Update inventorypurchase SET quantity=? WHERE invoice=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, totalstock_stringtype);

			pst.setString(2, invoicelabel);

			pst.executeUpdate();

		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err);
		} finally {
			conn.close();
		}

//		connect();
//		String sql = "UPDATE warehouses SET name = ? , " + "capacity = ? " + "WHERE id = ?";

	}

	public void newpartydelete(String inputclick) throws SQLException {
		try {
//			JOptionPane.showMessageDialog(null, inputclick);
			connect();
			st = conn.createStatement();
//			JOptionPane.showMessageDialog(null,inputclick);
			String sql = "Delete from party_details where party_code='" + inputclick + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Deleted Successfully");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "Something went wrong, Please Contact EXPERT!" + z);
		} finally {
			conn.close();
		}

	}

	public void newstaffdelete(String inputclick) throws SQLException {
		try {

			connect();
			st = conn.createStatement();
//			JOptionPane.showMessageDialog(null,inputclick);
			String sql = "Delete from staffdetails where staff_id='" + inputclick + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Deleted Successfully");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "Something went wrong, Please Contact EXPERT!" + z);
		} finally {
			conn.close();
		}

	}

	public boolean itemdelete(String inputclick) throws SQLException {
		try {
			verify = false;
			connect();
			st = conn.createStatement();
//			JOptionPane.showMessageDialog(null,inputclick);

			String sql = "Delete from menuitem where Code='" + inputclick + "'";
			int x = st.executeUpdate(sql);
			verify = true;
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "Something went wrong, Please Contact EXPERT!" + z);
		} finally {
			conn.close();
		}
		return verify;

	}

	public void insertuniqueid_master(String billbreak) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
//			JOptionPane.showMessageDialog(null,billbreak);
			String sql = "Insert into UID_partymaster VALUES('" + billbreak + "')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void insertuniqueid_inventory(String billbreak) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into UID_inventory VALUES('" + billbreak + "')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void insertuniqueid_forkotid(String kotbreak) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into Uniqueid4kotid VALUES('" + kotbreak + "')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void deleteuniqueid_forkotid(String billbreakdelete) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from Uniqueid4kotid where idgenerator='" + billbreakdelete + "'";
			int x = st.executeUpdate(sql);

		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void insertuniqueid_forstaffid(String kotbreak) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into Uniqueid4staffid VALUES('" + kotbreak + "')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void insertdesignation(String data) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into Designationlist(designation) VALUES('" + data + "')";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Added Successfully");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "Already Exists");
		} finally {
			conn.close();
		}

	}

	public void deletedesignation(String tablexyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from Designationlist where designation='" + tablexyz + "'";
			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void updatestaff_details(String a, String b, String c, String d, String e, String f, String g, String h,
			String i, String j, String k) throws SQLException {
		try {
			connect();

			String sql1 = "UPDATE staffdetails SET staffname=?,staffage=?,staffsalary=?,staffdesignation=?,staffshift=?,staffmobile=?,staffaddress=?,staffemail=?,staffidproof=?,staffproofvalue=? WHERE staff_id=?";
			String sql = "Update staffdetails SET staffname=?,staffage=?,staffsalary=?,staffdesignation=?,staffshift=?,staffmobile=?,staffaddress=?,staffemail=?,staffidproof=?,staffproofvalue=? WHERE staff_id=?";
			pst = conn.prepareStatement(sql1);
			pst.setString(1, b);
			pst.setString(2, c);
			pst.setString(3, d);
			pst.setString(4, e);
			pst.setString(5, f);
			pst.setString(6, g);
			pst.setString(7, h);
			pst.setString(8, i);
			pst.setString(9, j);
			pst.setString(10, k);
			pst.setString(11, a);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Updated Successfully!");

		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err);
		} finally {
			conn.close();
		}
		// TODO Auto-generated method stub

	}

	public void updateItemtoY(String billinvoice) throws SQLException {
		try {
			connect();

			String sql1 = "UPDATE bill_list SET status='Y' where bill_no=?";
			pst = conn.prepareStatement(sql1);
			pst.setString(1, billinvoice);

			pst.executeUpdate();

		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err);
		} finally {
			conn.close();
		}
		// TODO Auto-generated method stub

	}

	public void insertuniqueid_foritemid(String itembreak) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into Uniqueid4itemid VALUES('" + itembreak + "')";
//			JOptionPane.showMessageDialog(null, itembreak);
			st.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invoice error");
		} finally {
			conn.close();
		}

	}

	public void insertuniqueid_stock(String stockbreak) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into UID_stock VALUES('" + stockbreak + "')";
			st.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invoice error");
		} finally {
			conn.close();
		}

	}

	public void insertinventoryrecord(String data1, String dataparty, String data2, String data3, String data4,
			String data5, String data6, String data7, String data8, String data9, String data10, String data11,
			String data12, String data13, String data14, String data15, String data18, String data17)
			throws SQLException {

		try {

			connect();
			st = conn.createStatement();
			String sql = "Insert into inventorypurchaserecord(invoice,partyname,productname,gstin,batchno,mrp,additionalinfo,billdate,foodlicense,unit,quantity,hsn,discount,purchaserate,duedate,receivedate,expiry,maxdiscount) VALUES('"
					+ data1 + "','" + dataparty + "','" + data2 + "','" + data3 + "','" + data4 + "','" + data5 + "','"
					+ data6 + "','" + data7 + "','" + data8 + "','" + data9 + "','" + data10 + "','" + data11 + "','"
					+ data12 + "','" + data13 + "','" + data14 + "','" + data15 + "','" + data18 + "','" + data17
					+ "')";
			int x = st.executeUpdate(sql);
			// JOptionPane.showMessageDialog(null, "Data Saved Successfully");
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Already Exists");
		} finally {
			conn.close();
		}

	}

	public void staff_paymentdetails(String staff_id, String staff_name, double staff_salary, String staff_paymentdate,
			String nameofmonth, double staff_workingdays, double staff_paidsalary, String remark) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Insert into staffpayment VALUES('" + staff_id + "','" + staff_name + "','" + staff_salary
					+ "','" + staff_paymentdate + "','" + nameofmonth + "','" + staff_workingdays + "','"
					+ staff_paidsalary + "','" + remark + "')";
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Data Saved Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void deleteolddetail(String billmerge) throws SQLException {
		try {
			connect();

			String sql = "Delete from bill_list where bill_no='" + billmerge + "'";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
//		JOptionPane.showMessageDialog(null, "Data UPDATED Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void rmspurchase(String invoice, String billdate, String todaydate, String partyname, String billamount,
			String discount) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
//		JOptionPane.showMessageDialog(null, invoice);
			String sql = "Insert into rmspurchase values('" + invoice + "','" + billdate + "','" + todaydate + "','"
					+ partyname + "','" + billamount + "','" + discount + "')";
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Data Saved Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void cust_detailentry(String billprint, String customer_name, String cust_mob, String customerwishes,
			String occasion_date, String waiter_name, String savedate, String savetime, String subtotaltype,
			String cgsttype, String sgsttype, String discount, String grandtotaltype, String billinguser,
			String paymentmode) throws SQLException {
		try {
//			JOptionPane.showMessageDialog(null, savetime+savedate);
			connect();
			st = conn.createStatement();
			String sql = "Insert into tablecustomerdetail VALUES('" + billprint + "','" + customer_name + "','"
					+ cust_mob + "','" + customerwishes + "','" + occasion_date + "','" + waiter_name + "','" + savedate
					+ "','" + savetime + "','" + subtotaltype + "','" + cgsttype + "','" + sgsttype + "','" + discount
					+ "','" + grandtotaltype + "','" + billinguser + "','" + paymentmode + "')";
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "THANKYOU!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void insert_productstock(String data) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			int value = 0;
			String sql = "Insert into Product_Stock(Product_Name,Quantity) VALUES('" + data + "','" + value + "')";
			int x = st.executeUpdate(sql);
//			JOptionPane.showMessageDialog(null, "Added Successfully");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "Already Exists");
		} finally {
			conn.close();
		}

	}

	public void product_stockdelete(String tablexyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from Product_Stock where Product_Name='" + tablexyz + "'";
			int x = st.executeUpdate(sql);
//			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!");
		} finally {
			conn.close();
		}

	}

	public void updateproductquantity(int totalstock, String product_primarykey) throws SQLException {
		try {
			connect();
			String sql1 = "UPDATE Product_Stock SET Quantity=? WHERE Product_Name=?";

			pst = conn.prepareStatement(sql1);

			pst.setInt(1, totalstock);

			pst.setString(2, product_primarykey);

			pst.executeUpdate();
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, z);
		} finally {
			conn.close();
		}

	}

	public void stockup_addifdeleted(String issuedqtyreverse_add_where_productname, String issuedqtyreverse_add)
			throws SQLException {
		try {
			connect();
			String sql1 = "UPDATE Product_Stock SET Quantity=Quantity+? WHERE Product_Name=?";

			pst = conn.prepareStatement(sql1);

			pst.setInt(1, Integer.parseInt(issuedqtyreverse_add));

			pst.setString(2, issuedqtyreverse_add_where_productname);

			pst.executeUpdate();

		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, z);
		} finally {
			conn.close();
		}

	}

	public void stockup_addifdeletedarray(String[][] tablelist, int nRow, int nColumn, DefaultTableModel model2)
			throws SQLException {
		try {
			connect();
//			JOptionPane.showMessageDialog(null, "running");
//			JOptionPane.showMessageDialog(null, model2.getValueAt(0, 1).toString());
//			JOptionPane.showMessageDialog(null, model2.getValueAt(0, 2).toString());

			for (int i = 0; i < nRow; i++) {
				String sql1 = "UPDATE Product_Stock SET Quantity=Quantity-? WHERE Product_Name=?";

				pst = conn.prepareStatement(sql1);

				pst.setInt(1, Integer.parseInt(model2.getValueAt(i, 2).toString()));

				pst.setString(2, model2.getValueAt(i, 1).toString());

				pst.executeUpdate();

			}

		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, z);
		} finally {
			conn.close();
		}

	}

	public void insertbookingdetails(String ocassion, String customer, String address, String aadhar, String mobile,
			String datefrom, String dateto, String timeto, String timefrom, double totalcost, double advanceamt,
			double remainingamt, String Slipno, String remarks) throws SQLException {
		try {

			String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			connect();
			pst = conn.prepareStatement(
					"insert into bookingdetails(bookingocassion,customername,address,aadharno,mobile,datefrom,dateto,timeto,timefrom,totalcost,advanceamount,remainingamount,entereddate,slipno,remarks,paidamount) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst.setString(1, ocassion);
			pst.setString(2, customer);
			pst.setString(3, address);
			pst.setString(4, aadhar);
			pst.setString(5, mobile);

			pst.setString(6, datefrom);
			pst.setString(7, dateto);
			pst.setString(8, timeto);
			pst.setString(9, timefrom);
			pst.setDouble(10, totalcost);
			pst.setDouble(11, advanceamt);
			pst.setDouble(12, remainingamt);
			pst.setString(13, date);
			pst.setString(14, Slipno);
			pst.setString(15, remarks);
			pst.setDouble(16, advanceamt);

			int rows = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Saved Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}
	}

	public void updatebookingdetails(int slipno, String date, double amountpaid, double remainingamount)
			throws SQLException {
		try {
			connect();

			pst = conn.prepareStatement(
					"UPDATE bookingdetails SET advanceamount=advanceamount+?,paiddate=?,remainingamount=?,paidamount=paidamount+?, updateddate=NOW() where slipno=?");

			pst.setDouble(1, amountpaid);
			pst.setString(2, date);
			pst.setDouble(3, remainingamount);
			
			pst.setDouble(4, amountpaid);
			pst.setInt(5, slipno);

			int rows = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Saved Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void addstaffdailysalary(String staffid, String staffname, double salary, String month, String currentuser2)
			throws SQLException {
		try {
			String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			connect();

			pst = conn.prepareStatement(
					"insert into staffotherexpenses(staffid,staffname,amount,month,entereddate,issuedby) values(?,?,?,?,?,?)");

			pst.setString(1, staffid);
			pst.setString(2, staffname);
			pst.setDouble(3, salary);
			pst.setString(4, month);
			pst.setString(5, date);
			pst.setString(6, currentuser2);

			int rows = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Saved Successfully");
		} catch (Exception e) {
//		JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void insertdailyexpenses(String purpose, double amount, String currentuser) throws SQLException {
		try {
			String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			connect();
			pst = conn.prepareStatement(
					"insert into dailyotherexpenses(purpose,amount,entereddate,enteredby) values(?,?,?,?)");

			pst.setString(1, purpose);
			pst.setDouble(2, amount);
			pst.setString(3, date);
			pst.setString(4, date);
			pst.setString(4, currentuser);

			int rows = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Saved Successfully");
		} catch (Exception e) {
//		JOptionPane.showMessageDialog(null, e);
		} finally {
			conn.close();
		}

	}

	public void companycreate(String companyid, String companyname, String address, String city, String state,
			String country, String phoneno, String gstin, FileInputStream fis, String pathName)
			throws SQLException, FileNotFoundException {
		try {
			File image = null;
			image = new File(pathName);
			fis = new FileInputStream(image);
			connect();
			pst = conn.prepareStatement(
					"insert into companyprofile(companyid,companyname,address,city,state,country,phoneno,gstin,companyimage) values(?,?,?,?,?,?,?,?,?)");
			pst.setString(1, companyid);
			pst.setString(2, companyname);
			pst.setString(3, address);
			pst.setString(4, city);
			pst.setString(5, state);
			pst.setString(6, country);
			pst.setString(7, phoneno);
			pst.setString(8, gstin);
			pst.setBinaryStream(9, (InputStream) fis, (int) (image.length()));
			int rows = pst.executeUpdate();
			if (rows > 0) {
				JOptionPane.showMessageDialog(null, "Data Saved Successfully");

			} else {
				JOptionPane.showMessageDialog(null, "Data Not Saved Successfully");
			}

		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, f);
		} finally {
			conn.close();
		}

	}

	public void updatecompany(FileInputStream fis, String string, String pathName) throws SQLException {
		try {
			File image = new File(pathName);
			fis = new FileInputStream(image);
			connect();

			pst = conn.prepareStatement("UPDATE companyprofile SET companyimage=? where companyname=?");

			pst.setBinaryStream(1, (InputStream) fis, (int) (image.length()));
			pst.setString(2, string);
			int rows = pst.executeUpdate();
			if (rows > 0) {
				JOptionPane.showMessageDialog(null, "Data Updated Successfully");

			} else {
				JOptionPane.showMessageDialog(null, "Data Not Updated Successfully");
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Please Upload Logo/Image" + f);
		} finally {
			conn.close();
		}

	}

	public void companydelete(String categoryxyz) throws SQLException {
		try {
			connect();
			st = conn.createStatement();
			String sql = "Delete from companyprofile where sno='" + categoryxyz + "'";

			int x = st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Successfully Deleted");
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!" + z);
		} finally {
			conn.close();
		}

	}

	public void profilepasswordchange(String data13, String item) throws SQLException {
		try {
			connect();

			pst = conn.prepareStatement("UPDATE profilepassword SET profilepassword=? where sno=?");

			pst.setString(1, data13);
			pst.setInt(2, 1);
			int rows = pst.executeUpdate();
			if (rows > 0) {
				JOptionPane.showMessageDialog(null, "Data Updated Successfully");

			} else {
				JOptionPane.showMessageDialog(null, "Data Not Updated Successfully");
			}
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!" + z);
		} finally {
			conn.close();
		}

	}

	public void validate(String data13, String item) throws SQLException {
		try {
			connect();

			pst = conn.prepareStatement("UPDATE profilepassword SET validupto=? where sno=?");

			pst.setString(1, data13);
			pst.setInt(2, 1);
			int rows = pst.executeUpdate();
			if (rows > 0) {
				JOptionPane.showMessageDialog(null, "Data Updated Successfully");

			} else {
				JOptionPane.showMessageDialog(null, "Data Not Updated Successfully");
			}
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!" + z);
		} finally {
			conn.close();
		}

	}

	public void updateprofilepassword(String data13, String item) throws SQLException {
		try {
			connect();

			pst = conn.prepareStatement("UPDATE profilepassword SET profilepassword=? where sno=?");

			pst.setString(1, data13);
			pst.setInt(2, 1);
			int rows = pst.executeUpdate();
			if (rows > 0) {
				JOptionPane.showMessageDialog(null, "Data Updated Successfully");

			} else {
				JOptionPane.showMessageDialog(null, "Data Not Updated Successfully");
			}
		} catch (Exception z) {
			JOptionPane.showMessageDialog(null, "NOT WORKING!" + z);
		} finally {
			conn.close();
		}

	}

	public void delete_billitemwise(String itemname, String billmerge) throws SQLException {
		try {

			connect();
			st = conn.createStatement();
			String sql = "Delete from bill_list where bill_no='" + billmerge + "'and item_name='" + itemname + "' ";
			int x = st.executeUpdate(sql);

		} catch (Exception z) {

		} finally {
			conn.close();
		}

	}

	public String getvalidationdate() throws SQLException {
		String data = "";
		try {

			connect();
			st = conn.createStatement();
			rs = st.executeQuery("Select validupto from profilepassword");
			while (rs.next()) {
				data = rs.getString("validupto");

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return data;
	}
	
	


}
