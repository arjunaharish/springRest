$(document).ready(function(){
/*	$(".registerbtn").click(function(){
		var userName=$("#userName").val();
		var password=$("#password").val();*/
	function OnSubmitForm()
	{
		var userName=$("#userName").val();
		var password=$("#password").val();
		password
	
	$.ajax({
		type:"POST",
		url: "/shoppingCart1/login",
        data : "userName="+userName+"&password="+password,
        dataType: "json",
        success: function (data) {
        var myJSON = JSON.stringify(data);
        var success = data['success'];
        if(success == false){
var error = data['message'];
        alert(error); // just in case somebody to click on share witout writing anything :
        }
        if(success == true) {
        	   $('#mask , .login-popup').fadeOut(300 , function() {
        	   $('#mask').remove();  
        	                                });// end fadeOut function()
        	    setTimeout("location.href = 'home.php';",1000);                                 
        	                                                }
        	                                                    
        }
        })
	}
	})
/*})*/