<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>

	<link rel="stylesheet" href="${cdnUrl}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${cdnUrl}/css/footer.css">
</head>
<body>
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">JSON Formater</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="<c:url value="/" />">Home</a></li>
					<li><a href="<c:url value="/records/history" />">History</a></li>
					<li><a href="">Help</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">Welcome ${username}<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/accounts/profile" />">
								<i class="glyphicon glyphicon-user"></i> Profile</a>
							</li>
							<li class="active"><a href="#">
								<i class="glyphicon glyphicon-cog"></i> Settings</a>
							</li>
							<li><a href="<c:url value="/accounts/login?logout=true" />">
								<i class="glyphicon glyphicon-log-out"></i> Logout</a>
							</li>
						</ul>
					</li>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div>
	<div class="container-fluid">
		<div class="page-header"></div>
		<div class="row col-md-offset-2">
			
			<form class="form-horizontal" onsubmit="onSubmit(); return false;">
				<div class="form-group">
					<div class="col-md-offset-2 col-md-3 alert alert-error bg-danger text-success hide">Something error!</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">Old Password</label>
					<div class="col-md-3">
						<input type="password" class="form-control" id="old-password">
					</div>
				</div>
				<div class="form-group">
					<label  class="col-md-2 control-label">New Password</label>
					<div class="col-md-3">
						<input type="password" class="form-control" id="new-password">
					</div>
				</div>
				<div class="form-group">
					<label  class="col-md-2 control-label">Confirm Password</label>
					<div class="col-md-3">
						<input type="password" class="form-control" id="confirm-password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-2 col-md-3">
						<button type="submit" class="btn btn-success btn-block">Reset Password</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<footer class="footer">
		<div class="container-fluid">
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

			var oldPassword = md5($('#old-password').val()),
			    newPassword = $('#new-password').val(),
			    confirmPassword = $('#confirm-password').val();

			if (newPassword != confirmPassword) {
				var errorMessage = 'Confirm password is different.'
				$('.alert-error').html(errorMessage);
				$('.alert-error').removeClass('hide');
				$('button[type=submit]').html('Reset Password');
				$('button[type=submit]').removeAttr('disabled');
			} else {
				doResetPasswordAction(oldPassword, newPassword);
			}
		}
	</script>
	<script type="text/javascript">
		function doResetPasswordAction(oldPassword, password) {
			var postData = {
				'oldPassword': oldPassword,
				'password': password
			}

			$.ajax({
				type: 'POST',
				url: '<c:url value="/accounts/reset-password.action" />',
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
				alert('Reset password success.');
				$('#old-password').val('');
				$('#new-password').val('');
				$('#confirm-password').val('');
			} else {
				var errorMessage = 'Incorrect old password.';
				$('.alert-error').html(errorMessage);
				$('.alert-error').removeClass('hide');
			}
			
			$('button[type=submit]').html('Reset Password');
			$('button[type=submit]').removeAttr('disabled');
		}
	</script>
</body>
</html>