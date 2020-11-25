

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataSource datasource = null;

	sql query = new sql();
	String html = "";
	String page = "adminprofile";
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
			else if (method.equals("createSeller"))
				createSeller(request,response);
			else if (method.equals("addSeller"))
				addSeller(request,response);
			else if (method.equals("deleteSeller"))
				deleteSeller(request,response);
			else if (method.equals("createCustomer"))
				createCustomer(request,response);
			else if (method.equals("insertCustomer"))
				insertCustomer(request,response);
			else if (method.equals("insertTelephone"))
				insertTelephone(request,response);
			else if (method.equals("deleteCustomer"))
				deleteCustomer(request,response);
			else if (method.equals("createProgramme"))
				createProgramme(request,response);
			else if (method.equals("addProgramme"))
				addProgramme(request,response);
			else if (method.equals("getProgrammesAttributes"))
				getProgrammesAttributes(request,response);
			else if (method.equals("changeProgrammesAttributes"))
				changeProgrammesAttributes(request,response);
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
		
		PreparedStatement stmt = con.prepareStatement(query.Adminlogin(username,password));
		ResultSet rs = stmt.executeQuery();		
		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				String r_fname = rs.getString("username");
				String r_sname = rs.getString("password");
				
				session=request.getSession();  
		        session.setAttribute("username",username);  
		        session.setAttribute("password",password);
				
				System.out.println("Welcome " + session.getAttribute("username"));

				response.sendRedirect("adminprofile.jsp");
				
				con.close();
				rs.close();
			}
		}
		else {
			page="index";
			request.setAttribute("page",page);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			page = "adminprofile.jsp";
		}
		} catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void createSeller(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			
			String row = "<tr>\r\n" + 
					"			<th>User Name</th>\r\n" + 
					"			<th>Password</th>\r\n" + 
					"			<th>email</th>\r\n" + 
					"			<th>Admin User Name</th>\r\n" + 
					
					"		</tr><form method=\"post\" action=\"AdminServlet\"><input type=\"hidden\" name=\"method\" value=\"addSeller\">"
					+ "<tr><td><input type=\"text\" name=\"uname\"></td>"
					+ "<td><input type=\"text\" name=\"pass\"></td>"
					+ "<td><input type=\"text\" name=\"email\"></td>"
					+ "<td><input type=\"text\" name=\"adminuname\"></td></tr>";
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
	
	public void addSeller(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String uname = request.getParameter("uname").toString();
			String password = request.getParameter("pass").toString();
			String email = request.getParameter("email").toString(); 	 	
			String admin_uname = request.getParameter("adminuname").toString(); 	
		
					
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
				PreparedStatement prestmt = con.prepareStatement(query.addSeller(uname,password,email,admin_uname));
				prestmt.executeUpdate();
				
				
				response.sendRedirect("adminprofile.jsp");
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
	
	public void deleteSeller(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";	
			
			String sellerusername = request.getParameter("sellerusername");	
			
			PreparedStatement prestmt1 = con.prepareStatement(query.checkSeller(sellerusername));
			ResultSet rs = prestmt1.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					PreparedStatement prestmt = con.prepareStatement(query.deleteSeller(sellerusername));
					prestmt.executeUpdate();
				}
				request.setAttribute("html",html);
				request.getRequestDispatcher("adminprofile.jsp").forward(request, response); 
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
					"		</tr><form method=\"post\" action=\"AdminServlet\"><input type=\"hidden\" name=\"method\" value=\"insertCustomer\">"
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
				
				
				response.sendRedirect("telephonedetailsa.jsp");
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
				
				
				response.sendRedirect("adminprofile.jsp");
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
	public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";	
			
			String telephonenumber = request.getParameter("telephonenumber");	
			
			PreparedStatement prestmt1 = con.prepareStatement(query.checkTelephonec(telephonenumber));
			PreparedStatement prestmt2 = con.prepareStatement(query.checkTelephonet(telephonenumber));
			ResultSet rs1 = prestmt1.executeQuery();
			ResultSet rs2 = prestmt2.executeQuery();
			if (rs1.isBeforeFirst() && rs2.isBeforeFirst() ) {
				while (rs1.next() && rs2.next() ) {
					PreparedStatement prestmt = con.prepareStatement(query.deleteCustomer(telephonenumber));
					PreparedStatement prestmt3 = con.prepareStatement(query.deleteTelephone(telephonenumber));
					prestmt.executeUpdate();
					prestmt3.executeUpdate();
				}
				request.setAttribute("html",html);
				request.getRequestDispatcher("adminprofile.jsp").forward(request, response); 
				rs1.close();
				rs2.close();
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
	
	public void createProgramme(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			
			String row = "<tr>\r\n" + 
					"			<th>programme id</th>\r\n" + 
					"			<th>programme description</th>\r\n" + 
					"			<th>programme speech time</th>\r\n" + 
					"			<th>programme sms number</th>\r\n" + 
					"			<th>programme data</th>\r\n" + 
					"			<th>programme cost</th>\r\n" + 
					"		</tr><form method=\"post\" action=\"AdminServlet\"><input type=\"hidden\" name=\"method\" value=\"addProgramme\">"
					+ "<tr><td><input type=\"text\" name=\"programme_id\"></td>"
					+ "<td><input type=\"text\" name=\"programme_description\"></td>"
					+ "<td><input type=\"text\" name=\"programme_speechtime\"></td>"
					+ "<td><input type=\"text\" name=\"programme_sms_number\"></td>"
					+ "<td><input type=\"text\" name=\"programme_data\"></td>"
					+ "<td><input type=\"text\" name=\"programme_cost\"></td></tr>";
			html += row.toString();
			
			String row1 ="<br><br>\r\n" + 
					"  <input type=\"submit\" value=\"submit\"></form> ";
			html =html + row1.toString();				
			request.setAttribute("html",html);
			request.getRequestDispatcher("programmecreate.jsp").forward(request, response); 
			con.close();
		}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void addProgramme(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String programme_id = request.getParameter("programme_id").toString();
			String programme_description = request.getParameter("programme_description").toString();
			String programme_speechtime = request.getParameter("programme_speechtime").toString(); 	 	
			String programme_sms_number = request.getParameter("programme_sms_number").toString(); 
			String programme_data = request.getParameter("programme_data").toString();
			String programme_cost = request.getParameter("programme_cost").toString();
		
			
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
				PreparedStatement prestmt = con.prepareStatement(query.addProgramme(programme_id,programme_description,programme_speechtime,programme_sms_number,programme_data,programme_cost));
				prestmt.executeUpdate();
				
				
				response.sendRedirect("adminprofile.jsp");
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
	public void getProgrammesAttributes(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			
			String row = "<tr>\r\n" + 
					"			<th>programme id</th>\r\n" + 
					"			<th>field for change</th>\r\n" + 
					"			<th>new attribute</th>\r\n" + 
				
					"		</tr><form method=\"post\" action=\"AdminServlet\"><input type=\"hidden\" name=\"method\" value=\"changeProgrammesAttributes\">"
					+ "<tr><td><input type=\"text\" name=\"programme_id\"></td>"
					+ "<td><input type=\"text\" name=\"fieldforchange\"></td>"
					+ "<td><input type=\"text\" name=\"newattribute\"></td></tr>";
			html += row.toString();
			
			String row1 ="<br><br>\r\n" + 
					"  <input type=\"submit\" value=\"submit\"></form> ";
			html =html + row1.toString();				
			request.setAttribute("html",html);
			request.getRequestDispatcher("programmecreate.jsp").forward(request, response); 
			con.close();
		}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void changeProgrammesAttributes(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String programmeid = request.getParameter("programme_id").toString();
			String fieldforchange = request.getParameter("fieldforchange").toString();
			String newattribute = request.getParameter("newattribute").toString();
			
			
			PreparedStatement prestmt = con.prepareStatement(query.checkProg(programmeid));
			
			ResultSet rs1 = prestmt.executeQuery();
			
			if (rs1.isBeforeFirst() && fieldforchange.equals("description") ) {
				while (rs1.next()) {
					
					PreparedStatement prestmt1 = con.prepareStatement(query.changeDescription(programmeid,newattribute));
					prestmt1.executeUpdate();
					response.sendRedirect("adminprofile.jsp");
					rs1.close();
					con.close();
				}}
				else if(rs1.isBeforeFirst() && fieldforchange.equals("speechtime")) {
				while (rs1.next()) {
					PreparedStatement prestmt2 = con.prepareStatement(query.changeSpeechtime(programmeid,newattribute));
					prestmt2.executeUpdate();
					response.sendRedirect("adminprofile.jsp");
					rs1.close();
					con.close();
					}
			      }
				else if (rs1.isBeforeFirst() && fieldforchange.equals("sms")) {
					while (rs1.next()) {
						PreparedStatement prestmt3 = con.prepareStatement(query.changeSms(programmeid,newattribute));
						prestmt3.executeUpdate();
						response.sendRedirect("adminprofile.jsp");
						rs1.close();
						con.close();
					}
				}
				else if (rs1.isBeforeFirst() && fieldforchange.equals("data")) {
					while (rs1.next()) {
						PreparedStatement prestmt4 = con.prepareStatement(query.changeData(programmeid,newattribute));
						prestmt4.executeUpdate();
						response.sendRedirect("adminprofile.jsp");
						rs1.close();
						con.close();
					}
				}
				else if (rs1.isBeforeFirst() && fieldforchange.equals("cost")) {
					while (rs1.next()) {
						PreparedStatement prestmt5 = con.prepareStatement(query.changeCost(programmeid,newattribute));
						prestmt5.executeUpdate();
						response.sendRedirect("adminprofile.jsp");
						rs1.close();
						con.close();
					}
				}
			
				
				
				
				
			else   {
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
					"		</tr><form method=\"post\" action=\"AdminServlet\"><input type=\"hidden\" name=\"method\" value=\"addBill\">"
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
				
				
				response.sendRedirect("adminprofile.jsp");
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
