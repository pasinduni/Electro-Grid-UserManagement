$(document).ready(function() 
{ 
	($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
		$("#alertError").hide(); 
}); 

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{
	// Clear status---------------
	$("#alertSuccess").text(""); 
	$("#alertSuccess").hide(); 
	$("#alertError").text(""); 
	$("#alertError").hide(); 
	 
	// Form validation----------------
	var status = validateUserForm(); 
	if (status != true) 
	{ 
		$("#alertError").text(status); 
		$("#alertError").show(); 
		return; 
 	} 
	// If valid----------------------
	var type = ($("#hidUserIdSave").val() == "") ? "POST" : "PUT";
	//$("#formUser").submit(); 
		
	$.ajax(
	{
		url : "UserAPI",
 		type : type,
 		data : $("#formUser").serialize(),
 		dataType : "text",
 		complete : function(response, status)
		{
		 	onUserSaveComplete(response.responseText, status);
		}
	});	
});


//UPDATE ========================================
$(document).on("click", ".btnUpdate", function(event)
{
	//$("#hidUserIdSave").val($(this).closest("tr").find('#hidUserIdUpdate').val());
	$("#hidUserIdSave").val($(this).data("userId"));
	$("#userName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#userType").val($(this).closest("tr").find('td:eq(1)').text());
	$("#userEmail").val($(this).closest("tr").find('td:eq(2)').text());
	$("#userPhone").val($(this).closest("tr").find('td:eq(3)').text());
	$("#userPassword").val($(this).closest("tr").find('td:eq(4)').text());
});

//REMOVE=======================================================================
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
	 {
		 url : "UserAPI",
		 type : "DELETE",
		 data : "id=" + $(this).data("userId"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 	onUserDeleteComplete(response.responseText, status);
		 }
	 });
});


//CLIENT-MODEL=================================================================
function onUserSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success")
			{
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				$("#divUserGrid").html(resultSet.data);
				
			} else if (resultSet.status.trim() == "error")
			{
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
	 	$("#alertError").show();
	}
	 $("#hidUserIdSave").val("");
	 $("#formUser")[0].reset();
}


function onUserDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
	 	if (resultSet.status.trim() == "success")
		{
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 
			 $("#divUserGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
	 } else if (status == "error")
	 {
		 $("#alertError").text("Error while deleting.");
		 $("#alertError").show();
	 } else
	 {
		 $("#alertError").text("Unknown error while deleting..");
		 $("#alertError").show();
	 }
	 
}

function validateUserForm() 
{ 
	//NAME
	if ($("#userName").val().trim() == "") 
	{ 
		return "Insert User Name."; 
	} 
	//TYPE
	if ($("#userType").val().trim() == "")
	{
		return "Insert User Type.";
	}
	//EMAIL
	if ($("#userEmail").val().trim() == "") 
	{
		return "Insert User Email.";
	}
	//PHONE
	if ($("#userPhone").val().trim() == "") 
	{
		return "Insert User Phone.";
	}
	//PASSWORD
	if ($("#userPassword").val().trim() == "") 
	{
		return "Insert User Password.";
	} 
	
	
	
	return true; 
	
}






