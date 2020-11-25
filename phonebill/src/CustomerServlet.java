

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
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource datasource = null;

	sql query = new sql();
	String html = "";
	String page = "customerprofile";
	public static HttpSession session;
	
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
			else if (method.equals("printBill"))
				printBill(request,response);
			else if (method.equals("callHistory"))
				callHistory(request,response);
			else if (method.equals("payBill"))
				payBill(request,response);
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
		PreparedStatement stmt = con.prepareStatement(query.Customerlogin(username,password));
		ResultSet rs = stmt.executeQuery();		
		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				String r_fname = rs.getString("username");
				String r_sname = rs.getString("password");
				
				session=request.getSession();  
		        session.setAttribute("username",username);  
		        session.setAttribute("password",password);
				
				System.out.println("Welcome " + session.getAttribute("username"));

				response.sendRedirect("customerprofile.jsp");
				
				con.close();
				rs.close();
			}
		}
		else {
			page="index";
			request.setAttribute("page",page);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			page = "customerprofile.jsp";
		}
		} catch (SQLException | IOException |  ServletException e) {
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
       
	public void callHistory(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";	
			
			String billsid = request.getParameter("billsid");		
			PreparedStatement prestmt = con.prepareStatement(query.printCallHistory(billsid));
			ResultSet rs = prestmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String callsid = rs.getString("calls_id");
					String callsduration = rs.getString("calls_duration");
					String callstimestamp = rs.getString("calls_timestamp");
					String row = "<tr><td>"+callsid.toString()+"</td><td>"+billsid.toString()+"</td><td>"+callsduration.toString()+"</td><td>"+callstimestamp.toString()+"</td></tr>";
					html += row.toString();
				}
				request.setAttribute("html",html);
				request.getRequestDispatcher("customershistorycalls.jsp").forward(request, response); 
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
	
	public void payBill(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";	
			
			String billsmonth = request.getParameter("month");	
			String numbersid = request.getParameter("numbersid");
			PreparedStatement prestmt1 = con.prepareStatement(query.checkBill(billsmonth,numbersid));
			ResultSet rs = prestmt1.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					PreparedStatement prestmt = con.prepareStatement(query.payBill(billsmonth,numbersid));
					prestmt.executeUpdate();
				}
				request.setAttribute("html",html);
				request.getRequestDispatcher("customerprofile.jsp").forward(request, response); 
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
	
	
}