<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers History Calls</title>
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
	<h1> Customers History Calls</h1>
<br>
	<table>
		<tr>
			<th>Calls id</th>
			<th>Bills id</th>
			<th>Calls Duration</th>
			<th>Calls Timestamp</th>
		</tr>
		<%= request.getAttribute("html") %>.
	</table>
</body>
</center>
</html>