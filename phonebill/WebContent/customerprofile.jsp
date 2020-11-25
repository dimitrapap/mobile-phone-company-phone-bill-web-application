<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Profile Page</title>
</head>
<center>
<body style="background-color: #e6fffa;">
<h1>
		Welcome
		<%=session.getAttribute("username")%></h1>
	</br>
<form method="post" action="CustomerServlet">
  <input type="hidden" name="method" value="printBill">
  <h2>Show Customers Bill</h2>
  Insert Telephone numbers id:<br>
  <input type="text" name="numbersid" >  
  <br><br>
  <input type="submit" value="Submit">
</form>

<form method="post" action="CustomerServlet">
<input type="hidden" name="method" value="callHistory">
  <h2>Show Customers Call History</h2>
  Insert Bills id:<br>
  <input type="text" name="billsid">
  <br><br>
  <input type="submit" value="Submit">
</form>

<form method="post" action="CustomerServlet">
<input type="hidden" name="method" value="payBill">
<h2>Pay Customers Bill</h2>
Insert Bills Month for Payment:<br>
<input type="text" name="month"><br>
Insert Telephone numbers id:<br>
  <input type="text" name="numbersid" > 
  <br><br>
<input type="submit" value="Submit">
</form>
<br>
<form method="post" action="CustomerServlet">
  <input type="hidden" name="method" value="logout">
   <input type="submit" value="Logout">
</form>
	
</body>
</center>
</html>