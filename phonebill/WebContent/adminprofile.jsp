<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Profile Page</title>
</head>
<center>
<body style="background-color: #e6fffa;">
<h1>
		Welcome
		<%=session.getAttribute("username")%></h1>
	</br>
<form method="post" action="AdminServlet">
  <input type="hidden" name="method" value="createSeller">
  <h2>Add New Seller</h2>  
  <input type="submit" value="Submit">
</form>

<form method="post" action="AdminServlet">
 <input type="hidden" name="method" value="deleteSeller">
 <h2>Delete Seller</h2>
 Insert Sellers Username:<br>
  <input type="text" name="sellerusername" >  
  <br><br>
 <input type="submit" value="Submit">
</form>

<form method="post" action="AdminServlet">
 <input type="hidden" name="method" value="createCustomer">
 <h2>Create new Customer</h2>
 <input type="submit" value="Submit">
</form>

<form method="post" action="AdminServlet">
<ipnut type="hidden" name="method" value="deleteCustomer">
<h2>Delete Customer</h2>
 Insert  Customers Telephone Number:<br>
  <input type="text" name="telephonenumber" >  
  <br><br>
<input type="submit" value="Submit">
</form>

<form method="post" action="AdminServlet">
<input type="hidden" name="method" value="createProgramme">
<h2>Create New Programme</h2>
<input type="submit" value="Submit">
</form>

<form method="post" action="AdminServlet">
<input type="hidden" name="method" value="getProgrammesAttributes">
<h2>Change Programmes Attributes</h2>
<input type="submit" value="Submit">
</form>

<form method="post" action="AdminServlet">
<input type="hidden" name="method" value="makeBill">
<h2>Publicate customers Bill</h2>
<input type="submit" value="Submit">
</form>
<br><br>
<form method="post" action="AdminServlet">
  <input type="hidden" name="method" value="logout">
   <input type="submit" value="Logout">
</form>
	
</body>
</center>
</html>