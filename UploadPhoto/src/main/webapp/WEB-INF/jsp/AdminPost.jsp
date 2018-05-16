<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark"> <!-- Brand/logo -->
	<a class="navbar-brand" href="/profile"> <img src="/heart-icon.png"
		style="width: 40px;">PicShare
		
	</a>
		
	<!-- <div class="topnav-right"> -->
     <fb:login-button style="margin-left: 10%;" scope="public_profile,email,user_friends"
				data-max-rows="1" data-size="large" data-button-type="continue_with"
				data-show-faces="false" data-use-continue-as="false" data-auto-logout-link="true"
				onlogin="checkLoginState();">
	</fb:login-button>

    </nav>
    
<h3>User Posts</h3>
<script>
//This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
	console.log('statusChangeCallback');
	console.log(response);
	// The response object is returned with a status field that lets the
	// app know the current login status of the person.
	// Full docs on the response object can be found in the documentation
	// for FB.getLoginStatus().
	if (response.status === 'connected') {
		// Logged into your app and Facebook.
		testAPI();
	} else {
		// The person is not logged into your app or we are unable to tell.
		/* document.getElementById('status').innerHTML = 'Please log '
				+ 'into this app.'; */
		window.location.href="/FacebookLogin";		
				
	}
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

window.fbAsyncInit = function() {
	FB.init({
		appId : '111877416330705',
		cookie : true, // enable cookies to allow the server to access 
		// the session
		xfbml : true, // parse social plugins on this page
		version : 'v2.8' // use graph api version 2.8
	});

	// Now that we've initialized the JavaScript SDK, we call 
	// FB.getLoginStatus().  This function gets the state of the
	// person visiting this page and can return one of three states to
	// the callback you provide.  They can be:
	//
	// 1. Logged into your app ('connected')
	// 2. Logged into Facebook, but not your app ('not_authorized')
	// 3. Not logged into Facebook and can't tell if they are logged into
	//    your app or not.
	//
	// These three cases are handled in the callback function.

	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
	

};

// Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "https://connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function testAPI() {
	console.log('Welcome!  Fetching your information.... ');
	FB
			.api(
					'/me?fields=id,name,email',
					function(response) {
						console.log('Successful login for: '
								+ response.name);
						document.getElementById('status').innerHTML = 'Thanks for logging in, '
								+ response.name + '!';
						$('[name="myID"]').val(response.id);
						$('[name="myName"]').val(response.name);
						$('[name="myEmail"]').val(response.email);

						FB.api('/me/friends', function(response) {
							console.log(response);
							response.data.forEach(function(ele, i) {
								$("#tableBody").append(
										'<tr><th scope="row">' + i
												+ '</th>' + '<td>'
												+ ele.name + '</td>'
												+ '<td>' + ele.id
												+ '</td>' + '<tr>');
								$('[name="myFriends"]').val(
										JSON.stringify(response.data));

								/* var earlierVal = $(
								'[name="myFriends"]')
								.val();
								$('[name="myFriends"]').val(
								earlierVal + ele.id
										+ "/"
										+ ele.name
										+ "/"); */
								console.log(response);
							});
							$("#redirectForm").submit();
						});
					});
	
}

function sendid(a)
{
	$('[name="postId"]').val(a);
	$("#admindelForm").submit()
	}
</script>
<table>
               <c:forEach items="${posts}" var="post">
      			  <tr>
      			   <audio autoplay> <source src="${post.postaudioURL}"
		                                         type="audio/webm"> </source></audio>
      			    <td><img src="${post.postphotoURL}" height="200" width="200" /></td>
      			     <td>${post.text}</td>
           			<td><button name="delete" onclick="sendid('${post.postId}')">Delete</button><td>			   
                  </tr>
    			</c:forEach>
</table>
<form id="admindelForm" action="/delete" method="POST">
<input type="hidden" name="postId">
</form>

</body>
</html>