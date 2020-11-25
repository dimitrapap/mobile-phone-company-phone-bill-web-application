
public class sql {
	public String Sellerlogin(String username, String password) {
		String s = "SELECT * FROM seller WHERE username='" + username + "'AND password='" + password + "'";
		return(s);
	}
	/*public String uniqueCustomerusername(String uname) {
		String s = "select customer (CASE  when username='"+uname+"'THEN 'YES' ELSE 'NO' END) FROM customer;";
		return(s);
	}*/
	public String insertCustomer(String uname,String password,String email,String name,String surname, String tax_number , String seller_name , String programmeid , String telephone_number) {
		String s = "INSERT INTO customer(username,password,email,name,surname,tax_number,seller_username,programme_id_programme,telephone_number) VALUES ('"+uname+"','"+password+"','"+email+"','"+name+"','"+surname+"','"+tax_number+"','"+seller_name+"','"+programmeid+"','"+telephone_number+"');";
		return(s);
	}
	public String insertTelephone(String numberid,String area,String type,String category,String programmeid,String telephone_number ) {
		String s = "INSERT INTO telephone_number(telephone_number_id,telephone_number_area,telephone_number_type,telephone_number_category,programme_id_programme,telephone_number) VALUES ('"+numberid+"','"+area+"','"+type+"','"+category+"','"+programmeid+"','"+telephone_number+"');";
		return(s);
	}
	public String printBill(String numbersid) {
		String s = "SELECT * FROM bill WHERE telephone_number_telephone_number_id= '"+numbersid+"'";
		return(s);
	}
	public String printProgrammes() {
		String s = "SELECT * FROM programme";
		return(s);
	}
	public String changeProgramme(String customersuname , String newprogrammeid) {
		String s = "UPDATE customer SET programme_id_programme ='"+newprogrammeid+"' WHERE username='"+customersuname+"'";
		return(s);
	}
	public String checkProgramme(String new_programmeid) {
		String s = "SELECT * FROM programme WHERE id_programme='"+new_programmeid+"'";
		return(s);
	}
	public String checkCustomer(String username) {
		String s = "SELECT * FROM customer WHERE username='"+username+"'";
		return(s);
	}
	public String checkTelephone(String telephone_number) {
		String s = "SELECT * FROM telephone_number WHERE telephone_number='"+telephone_number+"'";
		return(s);
	}
	public String assignProgramme(String username, String new_programmeid) {
		String s = "UPDATE customer SET programme_id_programme='"+new_programmeid+"' WHERE username='"+username+"'";
		return(s);
	}
	public String changeTelephonesProgramme(String telephone_number,String new_programmeid) {
		String s ="UPDATE telephone_number SET programme_id_programme='"+new_programmeid+"'WHERE telephone_number='"+telephone_number+"'";
		return(s);
	}
	public String Customerlogin(String username, String password) {
		String s = "SELECT * FROM customer WHERE username='" + username + "'AND password='" + password + "'";
		return(s);
	}
	public String Adminlogin(String username, String password) {
		String s = "SELECT * FROM admin WHERE username='" + username + "'AND password='" + password + "'";
		return(s);
	}
	public String printCallHistory(String billsid) {
		String s = "SELECT * FROM calls WHERE bill_bills_id1='"+billsid+"'";
		return(s);
	}
	public String checkBill(String billsmonth , String numbersid) {
		String s = "SELECT * FROM bill WHERE bills_month='"+billsmonth+"'AND telephone_number_telephone_number_id='"+numbersid+"'";
		return(s);
	}
	public String payBill(String billsmonth , String numbersid) {
		String s = "DELETE FROM bill WHERE bills_month='"+billsmonth+"'AND telephone_number_telephone_number_id='"+numbersid+"'";
		return(s);
	}
	public String addSeller(String uname , String pass , String email , String admin_uname) {
		String s = "INSERT INTO seller(username,password,email,admin_username)  VALUES ('"+uname+"','"+pass+"','"+email+"','"+admin_uname+"');";
		return(s);
	}
	public String checkSeller(String sellerusername) {
		String s = "SELECT * FROM seller WHERE username='"+sellerusername+"'";
		return(s);
	}
	public String deleteSeller(String sellerusername) {
		String s = "DELETE FROM seller WHERE username='"+sellerusername+"'";
		return(s);
	}
	public String checkTelephonec(String telephonenumber) {
		String s = "SELECT * FROM customer WHERE telephone_number='"+telephonenumber+"'";
		return(s);
	}
	public String checkTelephonet(String telephonenumber) {
		String s = "SELECT * FROM telephone_number WHERE telephone_number='"+telephonenumber+"'";
		return(s);
	}
	public String deleteCustomer(String telephonenumber) {
		String s = "DELETE FROM customer WHERE telephone_number='"+telephonenumber+"'";
		return(s);
	}
	public String deleteTelephone(String telephonenumber) {
		String s = "DELETE FROM telephone_number WHERE telephone_number='"+telephonenumber+"'";
		return(s);
	}
	public String addProgramme(String programme_id , String programme_description , String programme_speechtime , String programme_sms_number, String programme_data , String programme_cost) {
		String s = "INSERT INTO programme(id_programme,programme_description,programme_speechtime,programme_sms_number,programme_data,programme_cost) VALUES ('"+programme_id+"','"+programme_description+"','"+programme_speechtime+"','"+programme_sms_number+"','"+programme_data+"','"+programme_cost+"');";
		return(s);
	}
	public String checkProg(String programmeid) {
		String s = "SELECT * FROM programme WHERE id_programme='"+programmeid+"'";
		return(s);
	}
	public String changeDescription(String programmeid , String newattribute) {
		String s = "UPDATE programme SET programme_description='"+newattribute+"' WHERE id_programme='"+programmeid+"'";
		return(s);
	}
	public String changeSpeechtime(String programmeid , String newattribute) {
		String s = "UPDATE programme SET programme_speechtime='"+newattribute+"' WHERE id_programme='"+programmeid+"'";
		return(s);
	}
	public String changeSms(String programmeid , String newattribute) {
		String s = "UPDATE programme SET programme_sms_number='"+newattribute+"' WHERE id_programme='"+programmeid+"'";
		return(s);
	}
	public String changeData(String programmeid , String newattribute) {
		String s = "UPDATE programme SET programme_data='"+newattribute+"' WHERE id_programme='"+programmeid+"'";
		return(s);
	}
	public String changeCost(String programmeid , String newattribute) {
		String s = "UPDATE programme SET programme_cost='"+newattribute+"' WHERE id_programme='"+programmeid+"'";
		return(s);
	}
	public String addBill(String billsid, String billsmonth , String telephonesid,String programmesid,String billscost) {
		String s = "INSERT INTO bill(bills_id,bills_month,telephone_number_telephone_number_id,programme_id_programme,billscost) VALUES ('"+billsid+"','"+billsmonth+"','"+telephonesid+"','"+programmesid+"','"+billscost+"');";
		return(s);
	}

}
