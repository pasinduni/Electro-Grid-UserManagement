<%@ page import="com.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/user.js"></script>
	<title>User Management</title>
	
	<nav class="navbar navbar-expand-md navbar-dark" style="background-color:#27278c">
         <ul class="navbar-nav">
              <li><a href="user.jsp" class="nav-link" style="color:#ffffff", "font-size:20px;", "size: 50px;" >ElectroGrid Online Billing System</a></li>
         </ul>
    </nav>
</head>

<body>
	
		<h2 class="m-4"><center>User Management</center></h2>
		<hr width="90%">	
			
	<div class="container">
		<div class="row">
			<div class="col"><br>
				<div class="card" style="width:100%; padding-right:14px">
				<h4 style="margin:20px">Add New User<hr width="50%", align="left"></h4>
			
				<form class="" action="" id="formUser" name="formUser" >
					
					<div class="form-inline"> 
						<label class="control-label col" for="name">User Name: </label>	 
						<input id="userName" name="userName" type="text" class="form-control form-control-sm" style="width:60%">
					</div><br>
					<div class="form-inline"> 
						<label class="control-label col" for="type">User Type: </label>
						<input id="userType" name="userType" type="text" class="form-control form-control-sm" style="width:60%">
					</div><br>
					<div class="form-inline"> 
						<label class="control-label col" for="type">User Email: </label>
						<input id="userEmail" name="userEmail" type="text" class="form-control form-control-sm" style="width:60%">
					</div><br>
					<div class="form-inline"> 
						<label class="control-label col" for="type">User Phone:</label>
						<input id="userPhone" name="userPhone" type="text" class="form-control form-control-sm" style="width:60%">
					</div><br>
					<div class="form-inline"> 
						<label class="control-label col" for="type">User Password:</label>
						<input id="userPassword" name="userPassword" type="text" class="form-control form-control-sm" style="width:60%">
					</div><br>
					
					<div align="right">	
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary" style="margin-bottom:10px; width:20%;font-size:20px;font-weight:400">
					<input type="hidden" id="hidUserIdSave" name="hidUserIdSave" value=""></div>
					
				</form>
			</div>
			<br>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
		</div>
				
		 	<div class="col">
			<br>
				<h4 class="m-3">User Details</h4>
					<div id="divUserGrid">
						<%
							User userObj = new User();
							out.print(userObj.readUser()); 
						%>
					</div>
			</div>
		</div>
	</div>
</body>


</html>

