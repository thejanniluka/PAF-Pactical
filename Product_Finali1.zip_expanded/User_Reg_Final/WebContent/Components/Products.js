$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});



///CLIENT-MODEL================================================================
function validateUserForm() {
	
    
    if ($("#ProductName").val().trim() == "") {
        return "Insert User Product Name";
    }
	if ($("#ResearcherName").val().trim() == "") {
        return "Insert User Researcher Name";
    }
   
    if ($("#ProductDescription").val().trim() == "") {
        return "Insert phone";
    }
	if ($("#ProductType").val().trim() == "") {
        return "Insert User ProductType";
    }
	if ($("#UserRole").val().trim() == "") {
        return "Insert UserRole";
    }
   
    if ($("#UPassword").val().trim() == "") {
        return "Insert Password.";
    }
    return true;
}



///SAVE-BUTTON================================================================
$(document).on("click", "#btnSave", function (event) 
{
    // Clear alerts
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    
    // Form validation
    var status = validateUserForm();
    if (status != true) 
    {
        $("#alertError").text(status);
        $("#alertError").show();
        
        return;
    }
    
    // If valid
    var type = ($("#hidProductIdSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
            url: "productsAPI",
            type: type,
            data: $("#formUser").serialize(),
            dataType: "text",
            complete: function (response, status) {
            	onproductsaveComplete(response.responseText, status);
            }
        });
});


function onproductsaveComplete(response, status) 
{
    	if (status == "success") 
    	{
    			var resultSet = JSON.parse(response);
    			
    			if (resultSet.status.trim() == "success") 
    			{
    					$("#alertSuccess").text("Successfully saved.");
	    				$("#alertSuccess").show();
	    				
	    				$("#divPaymentsGrid").html(resultSet.data);
    			} 
    			else if (resultSet.status.trim() == "error") 
    			{
    					$("#alertError").text(resultSet.data);
    					$("#alertError").show();
    			}
    	}
    	
    	else if (status == "error") 
    	{
    			$("#alertError").text("Error while saving.");
    			$("#alertError").show();
    	}	 
    	
    	else 
    	{
    			$("#alertError").text("Unknown error while saving..");
    			$("#alertError").show();
    	}
    	
    	$("#hidProductIdSave").val("");
    	$("#formUser")[0].reset();
}


///UPDATE-BUTTON================================================================
$(document).on("click", ".btnUpdate", function (event) 
{
    	$("#hidProductIdSave").val($(this).data("ProductId"));
    	$("#ProductName").val($(this).closest("tr").find('td:eq(0)').text());
		$("#ResearcherName").val($(this).closest("tr").find('td:eq(1)').text());
		$("#ProductDescription").val($(this).closest("tr").find('td:eq(2)').text());
    	$("#ProductType").val($(this).closest("tr").find('td:eq(3)').text());
		$("#UserRole").val($(this).closest("tr").find('td:eq(4)').text());
		$("#UPassword").val($(this).closest("tr").find('td:eq(5)').text());
		
		/* $("#hidProductIdSave").val($(this).closest("tr").find('#hidProductIdSave').val());
		 $("#ProductName").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#ResearcherName").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#ProductDescription").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#ProductType").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#UserRole").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#UPassword").val($(this).closest("tr").find('td:eq(5)').text()); */
});


///DELETE-BUTTON================================================================
$(document).on("click", ".btnRemove", function (event) 
{
    $.ajax(
        {
            url: "productsAPI",
            type: "DELETE",
            data: "ProductId=" + $(this).data("ProductId"),
            dataType: "text",
            complete: function (response, status) 
            {
            	onUserDeleteComplete(response.responseText, status);
            }
        });
});


function onDeleteComplete(response, status) 
{
    	if (status == "success") 
    	{
    			var resultSet = JSON.parse(response);
    			
    			if (resultSet.status.trim() == "success") 
    			{
    					$("#alertSuccess").text("Successfully deleted.");
    					$("#alertSuccess").show();
    					
    					$("#divproductsGrid").html(resultSet.data);
    			}
    			
    			else if (resultSet.status.trim() == "error")
    			{
    					$("#alertError").text(resultSet.data);
    					$("#alertError").show();
    			}
    	} 
    	
    	else if (status == "error") 
    	{
    			$("#alertError").text("Error while deleting.");
    			$("#alertError").show();
    	} 
    	
    	else 
    	{
    			$("#alertError").text("Unknown error while deleting..");
    			$("#alertError").show();
    	}
}