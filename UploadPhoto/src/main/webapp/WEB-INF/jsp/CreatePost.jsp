<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Post</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	
	<link href="https://vjs.zencdn.net/6.8.0/video-js.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.2.0/css/videojs.record.min.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.7.2/video.min.js"></script>
<script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>


<link href="https://vjs.zencdn.net/6.6.3/video-js.css" rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.0/css/videojs.record.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.7.2/video.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/RecordRTC/5.4.6/RecordRTC.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/adapterjs/0.15.0/adapter.min.js"></script>
<script
	src="https://collab-project.github.io/videojs-record/dist/wavesurfer.min.js"></script>
<script
	src="https://collab-project.github.io/videojs-record/dist/wavesurfer.microphone.min.js"></script>
<script
	src="https://collab-project.github.io/videojs-record/dist/videojs.wavesurfer.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.2/videojs.record.min.js"></script>
 
<style>
.topnav-right {
  float: right;
}
tr {
    border: 3px solid gray;
}
   /* change player background color */
  #myImage {
      background-color: #efc3e6;
  }
  

/* change player background color */
#myAudio {
	background-color: #9FD6BA;
	height: 200px;
	width:200px
}
</style>

</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark"> <!-- Brand/logo -->
	<a class="navbar-brand" href="/profile"> <img src="/heart-icon.png"
		style="width: 40px;">PicShare
		
	</a>
	<form style="margin-left: 10%;" action="/editProfile" method="POST">
            <input name="EditProfile" type="submit" id="editfriend" value="Edit Profile">   
			</form>
	<form style="margin-left: 10%;" action="/friends" method="POST">
            <input name="ViewFriends" type="submit" id="btnfriendlist" value="View Friends">   
			</form>
	<form style="margin-left: 10%;" action="/recordAudio" method="GET">
            <input name="createpost" type="submit" id="btnfriendlpost" value="Create Post">   
			</form>
	<form style="margin-left: 10%;" action="/viewNotify" method="POST">
            <input name="createnoti" type="submit" id="btnnotify" value="Notifications">   
			</form>
		
	<!-- <div class="topnav-right"> -->
     <fb:login-button style="margin-left: 10%;" scope="public_profile,email,user_friends"
				data-max-rows="1" data-size="large" data-button-type="continue_with"
				data-show-faces="false" data-use-continue-as="false" data-auto-logout-link="true"
				onlogin="checkLoginState();">
	</fb:login-button>
	<!-- 		</div> --> 
    </nav>
<h2>Create a Post</h2>
<script>
		// This is called with the results from from FB.getLoginStatus().
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
		
	</script>
	
		<label><strong>Please provide a photo:</strong></label>
	<video id="myImage" class="video-js vjs-default-skin"></video>

<script>
var player2 = videojs("myImage", {
    controls: true,
    width: 320,
    height: 240,
    fluid: false,
    controlBar: {
        volumePanel: false,
        fullscreenToggle: false
    },
    plugins: {
        record: {
            image: true,
            debug: true
        }
    }
}, function(){
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'));
});
// error handling
player2.on('deviceError', function() {
    console.warn('device error:', player2.deviceErrorCode);
});
player2.on('error', function(error) {
    console.log('error:', error);
});
// snapshot is available
player2.on('finishRecord', function() {
    // the blob object contains the image data that
    // can be downloaded by the user, stored on server etc.
    console.log('snapshot ready: ', player2.recordedData);
   		$("#picture").val(player2.recordedData);
     
	
});
</script>
	
   <label><strong>Please record your voice:</strong></label>
	<audio id="myAudio" class="video-js vjs-default-skin"></audio>
	<script>
		var player = videojs("myAudio", {
			controls : true,
			width : 600,
			height : 300,
			fluid : false,
			plugins : {
				wavesurfer : {
					src : "live",
					waveColor : "#36393b",
					progressColor : "#black",
					debug : true,
					cursorWidth : 1,
					msDisplayMax : 20,
					hideScrollbar : true
				},
				record : {
					audio : true,
					video : false,
					maxLength : 20,
					debug : true
				}
			}
		}, function() {
			// print version information at startup
			videojs.log('Using video.js', videojs.VERSION,
					'with videojs-record', videojs.getPluginVersion('record'),
					'+ videojs-wavesurfer', videojs
							.getPluginVersion('wavesurfer'), 'and recordrtc',
					RecordRTC.version);
		});
		// error handling
		player.on('deviceError', function() {
			console.log('device error:', player.deviceErrorCode);
		});
		player.on('error', function(error) {
			console.log('error:', error);
		});
		// user clicked the record button and started recording
		player.on('startRecord', function() {
			console.log('started recording!');
		});
		// user completed recording and stream is available
		player.on('finishRecord', function() {
			// the blob object contains the recorded data that
			// can be downloaded by the user, stored on server etc.
			console.log('finished recording: ', player.recordedData);
			var reader = new FileReader();
			var base64data;
			reader.readAsDataURL(player.recordedData);
			reader.onloadend = function() {
				base64data = reader.result;
				console.log(base64data);
				$("#recording").val(base64data);
			}
		});

		$(document).ready(function() {
			$("#saveButton").on("click", function() {
			var picture=$('#picture').val();
			var desc=$('#desc').val();
			var recording=$('#recording').val();
			if(picture === '')
			{
			alert('Please upload your photo ');
			return false;
			}
			if(recording === '' || recording === null)
			{
				
	        if(desc === null || desc === '' )
	    	{
	    		alert('Please add description or recording');
				return false;

	    	}
	        
			}
			if(picture !== null && picture !== "" && (recording !== '' || desc !== ''))
			{
				$("#audioForm").submit();
			}

			   
				});
		});
			
	</script>

			
	<form id="audioForm" action="/base64Audio" method="post"
		enctype="multipart/form-data">
		
		<input type="hidden" id="recording" name="recording"/> 
		<input type="hidden" id="picture" name="picture"/> 
		<label><strong>Please provide caption:</strong></label>
		<input type="text" id="desc" name="desc"/> 
	    <button id="saveButton" class="btn btn-primary">Save</button>
	    
	</form>
</body>
</html>
