function myDropDown(){
	document.getElementById("myDropDown").classList.toggle("show");
	if(document.getElementById("")){
		document.getElementById("myDropDown").classList.toggle("hide");
	}
};

function Tennis(){
	document.getElementById("Tennis");
	var data = 'Tennis';
	console.log(data);
	
$.ajax({
	url:'/searchBarFetchImages/name',
	data:data,
	type:"GET",
		 success : function(response) {
             alert( response );
         },
         error : function(xhr, status, error) {
             alert(xhr.responseText);
             console.log('data is '+data);
         }
     });
     return false;
}

function func(){	
	window.location.href = "fetch";
}