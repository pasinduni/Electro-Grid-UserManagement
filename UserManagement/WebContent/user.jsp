<%@ page import="com.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
//Save---------------------------------
if (request.getParameter("userName") != null)
{
    User userObj = new User();
    String stsMsg = "";

    //Insert-----------------------------------
    if (request.getParameter("hidUserIdSave") == "")
    {		    
    	stsMsg = userObj.insertUser(request.getParameter("userName"),
	    request.getParameter("userType"),
	    request.getParameter("userEmail"),
	    request.getParameter("userPhone"),
	    request.getParameter("userPassword"));
   	}
    else//Update--------------------------------
	{
	    stsMsg = userObj.updateUser(request.getParameter("hidUserIdSave"),
	    request.getParameter("userName"),
	    request.getParameter("userType"),
	    request.getParameter("userEmail"),
	    request.getParameter("userPhone"),
	    request.getParameter("userPassword"));
    }
	    session.setAttribute("statusMsg", stsMsg);
	}
   
   //Delete-----------------------------
   if (request.getParameter("hidUserIdDelete") != null)
   {
	    User userObj = new User();
	    String stsMsg = userObj.deleteUser(request.getParameter("hidUserIdDelete"));
	    session.setAttribute("statusMsg", stsMsg);
   }
   
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/user.js" type="text/javascript"></script>
	<title>User Management</title>
</head>

<body>
	<div class="container">
		<h2 class="m-4">User Details(V1.0)</h2>
			<hr>	
				<form class="form-horizontal" action="" id="formUser" name="formUser" >
					
					<div class="form-inline"> 
						<label class="control-label col-sm-3" for="name">User Name: </label>	 
						<input id="userName" name="userName" type="text" class="form-control form-control-sm">
					</div><br>
					<div class="form-inline"> 
						<label class="control-label col-sm-3" for="type">User Type: </label>
						<input id="userType" name="userType" type="text" class="form-control form-control-sm">
					</div><br>
					<div class="form-inline"> 
						<label class="control-label col-sm-3" for="type">User Email: </label>
						<input id="userEmail" name="userEmail" type="text" class="form-control form-control-sm">
					</div><br>
					<div class="form-inline"> 
						<label class="control-label col-sm-3" for="type">User Phone:</label>
						<input id="userPhone" name="userPhone" type="text" class="form-control form-control-sm">
					</div><br>
					<div class="form-inline"> 
						<label class="control-label col-sm-3" for="type">User Password:</label>
						<input id="userPassword" name="userPassword" type="text" class="form-control form-control-sm">
					</div><br>
						
					<input id="btnSave" name="btnSave" type="button" value="Save"  class="btn btn-primary" >
					<input type="hidden" id="hidUserIdSave" name="hidUserIdSave" value="">
					
				</form>
				<br>		
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divUserGrid">
			<%
				User userObj = new User();
			 	out.print(userObj.readUser()); 
			%>
		</div>
	</div>
</body>

</html>

