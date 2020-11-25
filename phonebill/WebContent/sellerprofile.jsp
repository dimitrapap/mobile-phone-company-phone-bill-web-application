<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seller Profile Page</title>
</head>
<center>
<body style="background-color: #e6fffa;">
<h1>
		Welcome
		<%=session.getAttribute("username")%></h1>
	</br>
<form method="post" action="SellerServlet">
  <input type="hidden" name="method" value="createCustomer">
  <h2>Create a new Customer</h2>  
  <input type="submit" value="Submit">
</form>


<form method="post" action="SellerServlet">
  <input type="hidden" name="method" value="printBill">
  <h2>Show Customers Bill</h2>
  Insert Telephone numbers id:<br>
  <input type="text" name="numbersid" >  
  <br><br>
  <input type="submit" value="Submit">
</form>

<form method="post" action="SellerServlet">
  <input type="hidden" name="method" value="getAttributes">
  <h2>Change Customers Programme</h2>  
  <input type="submit" value="Submit">
</form>


<form method="post" action="SellerServlet">
  <input type="hidden" name="method" value="printProgrammes">
  <h2>Print all Programmes</h2>
  <input type="submit" value="Submit">
</form> 

<form method="post" action="SellerServlet">
<input type="hidden" name="method" value="makeBill">
<h2>Publicate customers Bill</h2>
<input type="submit" value="Submit">
</form>
<br><br>


<form method="post" action="SellerServlet">
  <input type="hidden" name="method" value="logout">
   <input type="submit" value="Logout">
</form>
	
</body>
</center>
</html>