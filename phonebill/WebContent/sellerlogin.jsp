<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seller login</title>
</head>
<body style="background-color: #e6fffa;">
<h2>Login as a Seller</h2>

<form method="post" action="SellerServlet">
		<input type="hidden" name="method" value="login" />
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username" /></td>
				<br />
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" /></td>
				<br />
			</tr>
				<td></td>
				<td><input type="submit" value="Submit" /></td>
				<br />
			</tr>

		</table>
	</form>


</body>
</html>