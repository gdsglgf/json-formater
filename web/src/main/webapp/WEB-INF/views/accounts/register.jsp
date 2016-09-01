<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>

	<link rel="stylesheet" href="${cdnUrl}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${cdnUrl}/css/footer.css">
	<style type="text/css">
		body {
			padding-top: 20px;
			padding-bottom: 20px;
			background-color: #eee;
		}
		.auth-form {
			width: 300px;
			margin: 10% auto;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="auth-form">
			<div class="alert alert-error bg-danger text-success hide"></div>
			<form onsubmit="onSubmit(); return false;">
				<div class="form-group">
					<label>Username</label>
					<input type="text" autofocus="autofocus" class="form-control" id="username" name="username" required>
				</div>
				<div class="form-group">
					<label>Email Address</label>
					<input type="email" class="form-control" id="email" name="email" required>
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" class="form-control" id="password" name="password" required>
				</div>
				<button type="submit" class="btn btn-success btn-block">Create account</button>
			</form>
		</div>
	</div>

	<footer class="footer">
		<div class="container">
			<p class="text-muted">Copyright &copy; 2016. All rights reserved.</p>
		</div>
	</footer>

	<script type="text/javascript" src="${cdnUrl}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${cdnUrl}/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		function onSubmit() {
			$('.alert-error').addClass('hide');
			$('button[type=submit]').attr('disabled', 'disabled');
			$('button[type=submit]').html('Please wait...');
			var username   = $('#username').val(),
				password   = $('#password').val(),
				email	  = $('#email').val();

			doRegisterAction(username, password, email);
		}
	</script>
	<script type="text/javascript">
		function doRegisterAction(username, password, email) {
			var postData = {
				'username': username,
				'password': password,
				'email': email
			};
			
			console.log(username + '---' + email);

			$.ajax({
				type: 'POST',
				url: '<c:url value="/accounts/register.action" />',
				data: postData,
				dataType: 'JSON',
				success: function(result){
					return processRegisterResult(result);
				}
			});
		}
	</script>
	<script type="text/javascript">
		function processRegisterResult(result) {
			if ( result['isSuccessful'] ) {
				var forwardUrl = '${forwardUrl}' || '<c:url value="/" />';
				window.location.href = forwardUrl;
			} else {
				var errorMessage  = '';
				if ( result['isUsernameExists'] ) {
					errorMessage += 'Someone already has that username.<br>';
				}
				if ( result['isEmailExists'] ) {
					errorMessage += 'Someone already use that email.<br>';
				}
				
				console.log(errorMessage);

				$('.alert-error').html(errorMessage);
				$('.alert-error').removeClass('hide');
			}

			$('button[type=submit]').html('Create Account');
			$('button[type=submit]').removeAttr('disabled');
			$('html, body').animate({ scrollTop: 0 }, 100);
		}
	</script>
</body>
</html>