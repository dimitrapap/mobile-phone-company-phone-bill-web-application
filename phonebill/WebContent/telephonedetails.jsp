<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Telphone Details</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 50%;
}
td, th {
    border: 1px solid #5F9EA0;
    text-align: left;
    padding: 8px;
}
tr:nth-child(even) {
    background-color: #5F9EA0;
}
</style>
</head>
<center>
<body style="background-color: #e6fffa;">
<br>
	<h1> Insert Telephone Details</h1>
<br>
	<table>
		<tr>
			<th>Telephone Number id</th>
			<th>Telephone Number Area</th>
			<th>Telephone Number Type </th>
			<th>Telephone Number Category</th>
			<th>Programme id</th>
			<th>Telephone number</th>
		</tr>
		<form method="post" action="SellerServlet">
  <input type="hidden" name="method" value="insertTelephone">
  <tr>
  <td><input type="text" name="telephonenumberid"></td>
  <td><input type="text" name="area"></td>
  <td><input type="text" name="type"></td>
  <td><input type="text" name="category"></td>
  <td><input type="text" name="programmeid"></td>
  <td><input type="text" name="telephone_number"></td></tr>
   
  <input type="submit" value="Submit">
</form>
	</table>
</body>
</center>
</html>