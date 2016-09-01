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
			padding-top: 40px;
			padding-bottom: 40px;
			background-color: #eee;
		}
		.auth-form {
			width: 300px;
			margin: 10% auto;
		}
		.label-link {
			float: right;
		}
		label {
			display: block;
		}
		.create-account-callout {
			padding: 15px 20px;
			text-align: center;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="auth-form">
			<div class="alert alert-error bg-danger text-success hide"></div>
			<form id="login-form" onsubmit="onSubmit(); return false;">
				<div class="form-group">
					<label>Username</label>
					<input type="text" autofocus="autofocus" class="form-control" id="username" name="username" placeholder="Username" required>
				</div>
				<div class="form-group">
					<label>Password <a onclick="alert('Please connect administrator!')" class="label-link">Forgot password?</a></label>
					<input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
				</div>
				<button type="submit" class="btn btn-success btn-block">Sign in</button>
			</form>
			<p class="create-account-callout">New to APP? <a href="<c:url value="/accounts/register" />">Create an account</a>.</p>
		</div>
	</div>

	<footer class="footer">
		<div class="container">
			<p class="text-muted">Copyright &copy; 2016. All rights reserved.</p>
		</div>
	</footer>

	<script type="text/javascript" src="${cdnUrl}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${cdnUrl}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${cdnUrl}/js/md5.min.js"></script>

	<script type="text/javascript">
		function onSubmit() {
			$('.alert-error').addClass('hide');
			$('button[type=submit]').attr('disabled', 'disabled');
			$('button[type=submit]').html('Please wait...');
			var username   = $('#username').val(),
				password   = md5($('#password').val());

			$('#password').val(password);

			doLoginAction(username, password);
		}
	</script>
	<script type="text/javascript">
		function doLoginAction(username, password) {
			var postData = {
				'username': username,
				'password': password
			};

			$.ajax({
				type: 'POST',
				url: '<c:url value="/accounts/login.action" />',
				data: postData,
				dataType: 'JSON',
				success: function(result){
					return processLoginResult(result);
				}
			});
		}
	</script>
	<script type="text/javascript">
		function processLoginResult(result) {
			if ( result['isSuccessful'] ) {
				var forwardUrl = '${forwardUrl}' || '<c:url value="/" />';
				window.location.href = forwardUrl;
			} else {
				var errorMessage = '';
				if ( !result['isAccountValid'] ) {
					errorMessage = 'Incorrect username or password.';
				} else if ( !result['isAllowedToAccess'] ) {
					errorMessage = 'You&acute;re not allowed to sign in.';
				}
				$('#password').val('');
				$('.alert-error').html(errorMessage);
				$('.alert-error').removeClass('hide');
			}
			$('button[type=submit]').html('Sign in');
			$('button[type=submit]').removeAttr('disabled');
		}
	</script>
</body>
</html>