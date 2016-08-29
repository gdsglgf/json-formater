<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>

	<link rel="stylesheet" href="${cdnUrl}/css/bootstrap.min.css">
	<style type="text/css">
		.footer {
			position: absolute;
			bottom: 0;
			width: 100%;
			height: 60px;
			/*background-color: #f5f5f5;*/
		}
		.text-muted {
			margin: 20px 0;
		}
	</style>
	<style type="text/css">
		body {
		    padding-top: 20px;
			padding-bottom: 20px;
			background-color: #eee;
			background: url(${cdnUrl}/images/HubeiSinkhole_ZH-CN8831229647_1920x1080.jpg)no-repeat;
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
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="auth-form">
			<form action="" method="get">
				<div class="form-group">
					<label>Username</label>
					<input type="text" autofocus="autofocus" class="form-control" id="username" name="username" required>
				</div>
				<div class="form-group">
					<label>Email Address</label>
					<input type="email" class="form-control" id="email" name="email" required>
				</div>
				<div class="form-group">
					<label>Phone Number</label>
					<input type="text" class="form-control" id="phone" name="phone" required>
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" class="form-control" id="password" name="password" required>
				</div>
				<button type="submit" class="btn btn-success btn-block">Create an account</button>
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
</body>
</html>