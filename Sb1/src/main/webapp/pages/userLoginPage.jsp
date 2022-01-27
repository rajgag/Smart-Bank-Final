<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<style type="text/css">

body{
	background: url(pages/137962.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

#btn {
	width: 110px;
	border: none;
	border-radius: 10px;
	background-color: black;
	color:white;
}

#btn:hover {
	background-color: white;
	color:black;
	cursor: pointer;
}
#anchor:hover {
	
	color:black;
	cursor: pointer;
}
</style>
</head>
<body style="background-color: #28282B;">

	<%            
  response.setHeader("pragma", "no-cache");            
  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");           
  response.setHeader("Expires", "0");
	%>

	<div
		style="background-color: black; text-align: center; width: 50%; margin: auto; height: 420px; margin-top: 100px; border-radius: 10px; padding-top: 90px;opacity: 0.75;">
		<h2 style="color: white;">User Login</h2>
		<br>
		<form:form action="userLogin" method="post" modelAttribute="user">
			<form:label path="userLoginId" cssStyle="color:white">
				<b>Login-Id:&nbsp;</b>
			</form:label>
			<form:input path="userLoginId" />
			<br>
			<br>
			<form:label path="userPassword" cssStyle="color:white">
				<b>Password:</b>
			</form:label>
			<form:password path="userPassword" />

			<br>
			<br>

			<form:button type="submit" id="btn"><b>Login</b></form:button>
		</form:form>
		<br>
		<button id="btn">
			<a href="/home" id="anchor" style="text-decoration: none;"><b>Admin Login</b></a>
		</button>
	</div>
	
	<script type="text/javascript">
	function preventBack() {window.history.forward(); }  
	setTimeout("preventBack()", 0);  
	window.onunload = function () {null}; 
	</script>

</body>
</html>