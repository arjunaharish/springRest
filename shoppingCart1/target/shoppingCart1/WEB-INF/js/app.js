$(function(){
	
	var form=$('#ajax-login');
	var formUser=$('#user');
	var formPassword=$('#password');

	// Set up an event listener for the contact form.


	// Serialize the form data.
	


// Submit the form using AJAX.
$(form).submit(function(event) {
    // Stop the browser from submitting the form.
    event.preventDefault();

    // TODO
});
var formData = $(form).serialize();
$.ajax({
    type: 'POST',
    url: $(form).attr('action'),
    data: formData
}).done(function(response) {
// Make sure that the formMessages div has the 'success' class.
/*$(formMessages).removeClass('error');
$(formMessages).addClass('success');*/

// Set the message text.
/*$(formMessages).text(response);*/

// Clear the form.
$('#user').val('');
$('#password').val('');

}).fail(function(data) {
	// Make sure that the formMessages div has the 'error' class.
	/*$(formMessages).removeClass('success');
	$(formMessages).addClass('error');*/

	// Set the message text.
	if (data.responseText !== '') {
	    /*$(formMessages).text(data.responseText);*/
	} else {
	 /*   $(formMessages).text('Oops! An error occured and your message could not be sent.');*/
	}
});
});
