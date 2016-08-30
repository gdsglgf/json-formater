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

	<script type="text/javascript" src="assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
</body>
</html>