$(document).ready(function(){
	$(".registerbtn").click(function(){
		var userName=$("#userName").val();
		var password=$("#password").val();
		if( userName =='' || password ==''){
			$('input[type="text"],input[type="password"]').css("border","2px solid red");
			$('input[type="text"],input[type="password"]').css("box-shadow","0 0 3px red");
			alert("Please fill all fields...!!!!!!");
		
	}
	
	})
	$.ajax({
		type:"POST",
		url: "login",
        data : "userName="+userName+"&password="+password,
        dataType: "json",
        success: function (data) {
        	console.log(data);
        	console.log(url);
        var success = data['success'];
        if(userName='',password=''){
        	alert('data is empty');
        }
        if(success == false){
var error = data['message'];
        alert(error); // just in case somebody to click on share witout writing anything :
        }
        if(success == true) {
        	  
        	    setTimeout("location.href = '/viewEmp';",1000);                                 
        	                                                }
        	                                                    
        }
        })
	})

