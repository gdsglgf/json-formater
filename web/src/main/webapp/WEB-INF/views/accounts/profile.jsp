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
		input[type="text"]:disabled {
			background:#dddddd;
		}
	</style>
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
					<li><a href="<c:url value="<c:url value="/records/history" />" />">History</a></li>
					<li><a href="">Help</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">Welcome ${username}<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li  class="active"><a href="#">
								<i class="glyphicon glyphicon-user"></i> Profile</a>
							</li>
							<li><a href="<c:url value="/accounts/reset-password" />">
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
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-md-2 control-label">Username</label>
					<div class="col-md-3">
						<input type="text" class="form-control" id="username" name="username" value="${username}" disabled>
					</div>
				</div>
				<div class="form-group">
					<label  class="col-md-2 control-label">Email</label>
					<div class="col-md-3">
						<input type="text" class="form-control" id="email" name="email" value="${email}" disabled>
					</div>
				</div>
				<div class="form-group">
					<label  class="col-md-2 control-label">Register time</label>
					<div class="col-md-3">
						<input type="text" class="form-control" id="register-time" name="register-time" value="${registerTime}" disabled>
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
</body>
</html>