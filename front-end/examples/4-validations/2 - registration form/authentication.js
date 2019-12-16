function login(usernameElement, passwordElement) {
    let username = usernameElement && usernameElement.value ? usernameElement.value : "";
    let password = passwordElement && passwordElement.value ? passwordElement.value : "";

	let fd = new FormData();
	fd.append( 'username', username);
	fd.append( 'password', password);

	$.ajax({
	  url: ROOT_PATH + '/login',
	  data: fd,
	  processData: false,
	  contentType: false,
	  type: 'POST',
	  success: function(data){
		 localStorage.setItem(LOCAL_STORAGE_LOGIN_TOKEN_NAME, username);
		 window.location.replace(ROOT_PATH + "/citizen_index.html");
	  },
	  statusCode: {
		401 : function() {
				alert("Invalid username or password!");
			}
		}
	});
}

function send(){
	window.location.replace(ROOT_PATH+"/register.html");
}

function register(amkaElement,firstnameElement,lastnameElement,usernameElement,passwordElement,emailElement,mobileElement) {

	let amka = amkaElement && amkaElement.value ? amkaElement.value : "";
	let firstname = firstnameElement && firstnameElement.value ? firstnameElement.value : "";
	let lastname = lastnameElement && lastnameElement.value ? lastnameElement.value : "";
	let username = usernameElement && usernameElement.value ? usernameElement.value : "";
	let password = passwordElement && passwordElement.value ? passwordElement.value : "";
	let email = emailElement && emailElement.value ? emailElement.value : "";
	let mobile = mobileElement && mobileElement.value ? mobileElement.value : "";

	let fd = new FormData();
	fd.append('amka',amka);
	fd.append('firstname',firstname);
	fd.append('lastname',lastname);
	fd.append( 'username', username);
	fd.append( 'password', password);
	fd.append('email',email);
	fd.append('mobile',mobile);

	$.ajax({
		url: ROOT_PATH + '/register',
		data: fd,
		processData: false,
		contentType: false,
		type: 'POST',
		success: function () {
			window.location.replace(ROOT_PATH + "/login.html");
		},
		statusCode: {
			401: function () {
				alert("Wrong Registration");
			}
		}
	});


}