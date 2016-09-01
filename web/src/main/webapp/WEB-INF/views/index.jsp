<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>

	<link rel="stylesheet" href="${cdnUrl}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${cdnUrl}/css/footer.css">
	<link rel="stylesheet" href="${cdnUrl}/css/s.css">

	<style type="text/css">
		.setting-space {
			padding-left:20px;
		}

		textarea { resize: vertical; }
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
					<li class="active"><a href="#">Home</a></li>
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
		<div class="page-header">
			<h2>Original JSON:</h2>
		</div>
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<textarea name="json" id="RawJson" class="form-control">{"number":1,"array":[],"null":null,"string":"test","boolean":true,"obj":{},"level1":{"level2":{"level3":{"level4":{"level5":{"level6":{"level7":{"level8":{}}}}}}}}}</textarea>
			</div>
		</div>

		<div class="row" style="padding-top:20px;">
			<label for="TabSize" class="col-md-1 col-md-offset-1">Spaces:</label>
			<select id="TabSize" onchange="TabSizeChanged()" class="col-md-1 span1">
				<option value="2" selected>2</option>
				<option value="4">4</option>
				<option value="8">8</option>
			</select>
			<label for="QuoteKeys" class="col-md-1">
				<input type="checkbox" id="QuoteKeys" onclick="QuoteKeysClicked()" checked>Quote
			</label>
			<label for="CollapsibleView" class="col-md-1">
				<input type="checkbox" id="CollapsibleView" onclick="CollapsibleViewClicked()" checked>Control
			</label>
			<span id="CollapsibleViewDetail" class="col-md-4">
				<a href="javascript:void(0);" onclick="ExpandAllClicked()">Expand</a>
				<a href="javascript:void(0);" class="setting-space" onclick="CollapseAllClicked()">Collapse</a>
				<a href="javascript:void(0);" class="setting-space" onclick="CollapseLevel(3)">L2</a>
				<a href="javascript:void(0);" class="setting-space" onclick="CollapseLevel(4)">L3</a>
				<a href="javascript:void(0);" class="setting-space" onclick="CollapseLevel(5)">L4</a>
				<a href="javascript:void(0);" class="setting-space" onclick="CollapseLevel(6)">L5</a>
				<a href="javascript:void(0);" class="setting-space" onclick="CollapseLevel(7)">L6</a>
				<a href="javascript:void(0);" class="setting-space" onclick="CollapseLevel(8)">L7</a>
				<a href="javascript:void(0);" class="setting-space" onclick="CollapseLevel(9)">L8</a>
			</span>
			<input class="col-md-1 btn btn-small btn-primary" data-loading-text="Formatting json..." id="format" type="button" onclick="processJson()" value="Format"/>
		</div>

		<div class="page-header">
			<h2>Formated JSON:</h2>
		</div>
		<div class="row">
			<div id="Canvas" class="Canvas well resizable col-md-10 col-md-offset-1"></div>
		</div>
	</div>

	<footer class="footer">
		<div class="container-fluid">
			<p class="text-muted">Copyright &copy; 2016. All rights reserved.</p>
		</div>
	</footer>

	<script type="text/javascript" src="${cdnUrl}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${cdnUrl}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${cdnUrl}/js/c.js"></script>

	<script type="text/javascript">
		function processJson() {
			var json = $id("RawJson").value;
			if (json == '') {
				alert('Please enter json string.');
				return;
			}
			var formated = Process();
			if (formated) {
				
				doSaveAction(json);
			}
		}
	</script>

	<script type="text/javascript">
		function doSaveAction(json) {
			var postData = {
				'content': json
			}

			$.ajax({
				type: 'POST',
				url: '<c:url value="/records/save.action" />',
				data: postData,
				dataType: 'JSON',
				success: function(result){
					console.log(result)
					return true;
				}
			});
		}
	</script>
</body>
</html>