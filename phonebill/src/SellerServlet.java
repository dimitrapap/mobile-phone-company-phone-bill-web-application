

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class SellerServlet
 */
@WebServlet("/SellerServlet")
public class SellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource datasource = null;

	sql query = new sql();
	String html = "";
	String page = "sellerprofile";
	public static HttpSession session;
	
	
	public void init(ServletConfig config) throws ServletException {
		
		try {
			
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch(Exception e) {
			throw new ServletException(e.toString());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			String method = request.getParameter("method");
			
			if (method.equals("login"))
				login(request,response);
			else if (method.equals("createCustomer"))
				createCustomer(request,response);
			else if (method.equals("insertCustomer"))
				insertCustomer(request,response);
			else if (method.contentEquals("insertTelephone"))
				insertTelephone(request,response);
			else if (method.equals("printBill"))
				printBill(request,response);
			else if (method.equals("printProgrammes"))
				printProgrammes(request,response);
			else if (method.equals("getAttributes"))
				getAttributes(request,response);
			else if (method.equals("assignProgramme"))
				assignProgramme(request,response);
			else if (method.equals("makeBill"))
				makeBill(request,response);
			else if (method.equals("addBill"))
				addBill(request,response);
			else if (method.equals("logout")){
				session.invalidate();
				response.sendRedirect("index.html");
			}
        }
	
	
	public void login(HttpServletRequest request, HttpServletResponse response) {
		Connection con;
		try {
			con = datasource.getConnection();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//AM = getHash(AM.getBytes(),"MD5");
		PreparedStatement stmt = con.prepareStatement(query.Sellerlogin(username,password));
		ResultSet rs = stmt.executeQuery();		
		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				String r_fname = rs.getString("username");
				String r_sname = rs.getString("password");
				
				session=request.getSession();  
		        session.setAttribute("username",username);  
		        session.setAttribute("password",password);
				
				System.out.println("Welcome " + session.getAttribute("username"));

				response.sendRedirect("sellerprofile.jsp");
				
				con.close();
				rs.close();
			}
		}
		else {
			page="index";
			request.setAttribute("page",page);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			page = "sellerprofile.jsp";
		}
		} catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void createCustomer(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			
			String row = "<tr>\r\n" + 
					"			<th>User Name</th>\r\n" + 
					"			<th>Password</th>\r\n" + 
					"			<th>email</th>\r\n" + 
					"			<th>Name</th>\r\n" + 
					"			<th>Surname</th>\r\n" + 
					"			<th>Tax Number</th>\r\n" +
					"			<th>Sellers Name</th>\r\n" +
					"			<th>Programme id</th>\r\n" +
					"			<th>Telephone Number</th>\r\n" +
					"		</tr><form method=\"post\" action=\"SellerServlet\"><input type=\"hidden\" name=\"method\" value=\"insertCustomer\">"
					+ "<tr><td><input type=\"text\" name=\"uname\"></td>"
					+ "<td><input type=\"text\" name=\"pass\"></td>"
					+ "<td><input type=\"text\" name=\"email\"></td>"
					+ "<td><input type=\"text\" name=\"name\"></td>"
					+ "<td><input type=\"text\" name=\"surname\"></td>"
					+ "<td><input type=\"text\" name=\"tax_number\"></td>"
					+ "<td><input type=\"text\" name=\"selleruname\"></td>"
					+ "<td><input type=\"text\" name=\"programmeid\"></td>"
					+ "<td><input type=\"text\" name=\"telephonenumber\"></td></tr>";
			html += row.toString();
			
			String row1 ="<br><br>\r\n" + 
					"  <input type=\"submit\" value=\"submit\"></form> ";
			html =html + row1.toString();				
			request.setAttribute("html",html);
			request.getRequestDispatcher("sellercreate.jsp").forward(request, response); 
			con.close();
		}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void insertCustomer(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String uname = request.getParameter("uname").toString();
			String password = request.getParameter("pass").toString();
			String email = request.getParameter("email").toString(); 	 	
			String name = request.getParameter("name").toString(); 	
			String surname = request.getParameter("surname").toString();
			String tax_number = request.getParameter("tax_number").toString();
			String seller_name = request.getParameter("selleruname").toString();
			String programmeid = request.getParameter("programmeid").toString();
			String telephone_number = request.getParameter("telephonenumber").toString();
		
		
			boolean unique = true;
			/*PreparedStatement prestmt1 = con.prepareStatement(query.uniqueCustomerusername(uname));
			ResultSet rs1 = prestmt1.executeQuery();
			if(rs1.isBeforeFirst()) {
				while (rs1.next()) {
					if (rs1.getString("case").equals("YES")) {
						unique = false;
					}
				}
			}*/
			
		       if (unique=true) {
				PreparedStatement prestmt2 = con.prepareStatement(query.insertCustomer(uname,password,email,name,surname,tax_number,seller_name,programmeid,telephone_number));
				prestmt2.executeUpdate();
				
				
				response.sendRedirect("telephonedetails.jsp");
				//rs1.close();
				con.close();
				
			}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error1.jsp").forward(request, response);
			}
		}
		catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void insertTelephone(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String numberid = request.getParameter("telephonenumberid").toString();
			String area = request.getParameter("area").toString();
			String type = request.getParameter("type").toString(); 	 	
			String category = request.getParameter("category").toString(); 
			String programmeid = request.getParameter("programmeid").toString();
			String telephone_number = request.getParameter("telephone_number").toString();
		
			
			boolean unique = true;
			/*PreparedStatement prestmt1 = con.prepareStatement(query.uniqueCustomerusername(uname));
			ResultSet rs1 = prestmt1.executeQuery();
			if(rs1.isBeforeFirst()) {
				while (rs1.next()) {
					if (rs1.getString("case").equals("YES")) {
						unique = false;
					}
				}
			}*/
			
		       if (unique=true) {
				PreparedStatement prestmt = con.prepareStatement(query.insertTelephone(numberid,area,type,category,programmeid,telephone_number));
				prestmt.executeUpdate();
				
				
				response.sendRedirect("sellerprofile.jsp");
				//rs1.close();
				con.close();
				
			}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error1.jsp").forward(request, response);
			}
		}
		catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void printBill(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";	
			
			String numbersid = request.getParameter("numbersid");		
			PreparedStatement prestmt = con.prepareStatement(query.printBill(numbersid));
			ResultSet rs = prestmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String billsid = rs.getString("bills_id");
					String bills_month = rs.getString("bills_month");
					String programmeid = rs.getString("programme_id_programme");
					String billscost = rs.getString("billscost");
					String row = "<tr><td>"+billsid+"</td><td>"+bills_month.toString()+"</td><td>"+numbersid.toString()+"</td><td>"+programmeid.toString()+"</td><td>"+billscost.toString()+"</td></tr>";
					html += row.toString();
				}
				request.setAttribute("html",html);
				request.getRequestDispatcher("customersbill.jsp").forward(request, response); 
				rs.close();
				con.close();
				
				}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error1.jsp").forward(request, response);
			}}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
		
	}
	public void printProgrammes(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";	
			
			PreparedStatement prestmt = con.prepareStatement(query.printProgrammes());
			ResultSet rs = prestmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String programme_id = rs.getString("id_programme");
					String programme_desc = rs.getString("programme_description");
					String programme_speechtime = rs.getString("programme_speechtime");
					String programme_sms_number = rs.getString("programme_sms_number");
					String programme_data = rs.getString("programme_data");
					String programme_cost = rs.getString("programme_cost");
					String row = "<tr><td>"+programme_id.toString()+"</td><td>"+programme_desc.toString()+"</td><td>"+programme_speechtime.toString()+"</td><td>"+programme_sms_number+"</td><td>"+programme_data+"</td><td>"+programme_cost+"</td></tr>";
					html += row.toString();
				}
				request.setAttribute("html",html);
				request.getRequestDispatcher("allprogrammes.jsp").forward(request, response); 
				rs.close();
				con.close();
				
				}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error1.jsp").forward(request, response);
			}}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
		
	}
	
	public void getAttributes(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			
			String row = "<form method=\"post\" action=\"SellerServlet\"><input type=\"hidden\" name=\"method\" value=\"assignProgramme\">"
					+ "<tr><td><input type=\"text\" name=\"username\"></td><td><input type=\"text\" name=\"new_programmeid\"></td><td><input type=\"text\" name=\"telephone_number\"></td></tr>";
			html += row.toString();
			
			String row1 ="<br><br>\r\n" + 
					"  <input type=\"submit\" value=\"insert\"></form> ";
			html =html + row1.toString();				
			request.setAttribute("html",html);
			request.getRequestDispatcher("sellerpage.jsp").forward(request, response); 
			con.close();

		}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void assignProgramme(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String username = request.getParameter("username").toString();
			String new_programmeid = request.getParameter("new_programmeid").toString(); 
			String telephone_number = request.getParameter("telephone_number").toString();
			
			PreparedStatement prestmt1 = con.prepareStatement(query.checkProgramme(new_programmeid));
			PreparedStatement prestmt2 = con.prepareStatement(query.checkCustomer(username));
			PreparedStatement prestmt3 = con.prepareStatement(query.checkTelephone(telephone_number));
			ResultSet rs1 = prestmt1.executeQuery();
			ResultSet rs2 = prestmt2.executeQuery();
			ResultSet rs3 = prestmt3.executeQuery();
			if (rs1.isBeforeFirst() && rs2.isBeforeFirst() && rs3.isBeforeFirst()) {
				while (rs1.next() && rs2.next() && rs3.next()) {
					
					PreparedStatement prestmt = con.prepareStatement(query.assignProgramme(username, new_programmeid));
					PreparedStatement prestmt4 = con.prepareStatement(query.changeTelephonesProgramme(telephone_number,new_programmeid));
					prestmt.executeUpdate();
					prestmt4.executeUpdate();
				}
				
				
				response.sendRedirect("sellerprofile.jsp");
				rs1.close();
				con.close();
				
			}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error1.jsp").forward(request, response);
			}
		}
		catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	public void makeBill(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			
			String row = "<tr>\r\n" + 
					"			<th>Bills id</th>\r\n" + 
					"			<th>Bills Month</th>\r\n" + 
					"			<th>Telephones id</th>\r\n" + 
					"			<th>Programmes id</th>\r\n" + 
					"			<th>Bills Cost</th>\r\n" +
					"		</tr><form method=\"post\" action=\"SellerServlet\"><input type=\"hidden\" name=\"method\" value=\"addBill\">"
					+ "<tr><td><input type=\"text\" name=\"billsid\"></td>"
					+ "<td><input type=\"text\" name=\"billsmonth\"></td>"
					+ "<td><input type=\"text\" name=\"telephonesid\"></td>"
					+ "<td><input type=\"text\" name=\"programmesid\"></td>"
					+ "<td><input type=\"text\" name=\"billscost\"></td></tr>";
			html += row.toString();
			
			String row1 ="<br><br>\r\n" + 
					"  <input type=\"submit\" value=\"submit\"></form> ";
			html =html + row1.toString();				
			request.setAttribute("html",html);
			request.getRequestDispatcher("sellercreate.jsp").forward(request, response); 
			con.close();
		}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void addBill(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String billsid = request.getParameter("billsid").toString();
			String billsmonth = request.getParameter("billsmonth").toString();
			String telephonesid = request.getParameter("telephonesid").toString(); 	 	
			String programmesid = request.getParameter("programmesid").toString(); 	
			String billscost = request.getParameter("billscost").toString();
			
			boolean unique = true;
			/*PreparedStatement prestmt1 = con.prepareStatement(query.uniqueCustomerusername(uname));
			ResultSet rs1 = prestmt1.executeQuery();
			if(rs1.isBeforeFirst()) {
				while (rs1.next()) {
					if (rs1.getString("case").equals("YES")) {
						unique = false;
					}
				}
			}*/
			
		       if (unique=true) {
				PreparedStatement prestmt = con.prepareStatement(query.addBill(billsid,billsmonth,telephonesid,programmesid,billscost));
				prestmt.executeUpdate();
				
				
				response.sendRedirect("sellerprofile.jsp");
				//rs1.close();
				con.close();
				
			}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error1.jsp").forward(request, response);
			}
		}
		catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
