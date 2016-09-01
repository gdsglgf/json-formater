<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>

	<link rel="stylesheet" href="${cdnUrl}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${cdnUrl}/css/footer.css">
	<link rel="stylesheet" href="${cdnUrl}/css/s.css">

	<style type="text/css">
		tr td:nth-child(1) {
			width: 10%;
		}
		tr td:nth-child(2) {
			width: 20%;
		}
		tr td:nth-child(4) {
			width: 10%;
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
					<li class="active"><a href="#">History</a></li>
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
		<div class="page-header col-md-offset-1">
			<form>
				<div class="form-group">
					<button type="button" id="search" class="btn btn-primary">Search All</button>
				</div>
			</form>
		</div>
		<div class="row col-md-10 col-md-offset-1">
			<table class="table table-striped"></table>
			<p class="text-success" id="counts"></p>
		</div>
		<div id="formatModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">JSON Formater</h4>
					</div>
					<div class="modal-body">
						<div id="format-content"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
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
		$('#search').click(function () {
			$(this).attr('disabled', 'disabled');
			$(this).html('Searching...');

			$.ajax({
				type: 'POST',
				url: '<c:url value="/records/search.action" />',
				dataType: 'JSON',
				success: function(result) {
					return processSearchResult(result);
				}
			});

			$(this).html('Search All');
			$(this).removeAttr('disabled');
		});
	</script>
	<script type="text/javascript">
		var operString = '<td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#formatModal" onclick="formatJSON(this)">Format</button></td>'
		function processSearchResult(result) {
			$('.table').html('<tr><td>#</td><td>Submit Time</td><td>JSON Content</td><td></td></tr>');

			var html = '';
			for (var i = 0; i < result.length; i++) {
				html += '<tr>';
				html += '<td>' + result[i]['hid'] + '</td>';
				html += '<td>' + result[i]['submitTime'] + '</td>';
				html += '<td>' + result[i]['content'] + '</td>';
				html += operString;
				html += '</tr>';
			}
			$('.table').append(html);
			$('#counts').html('Find ' + result.length + ' record(s).');
		}
	</script>
	<script type="text/javascript">
		$("td>button").click( function() {
			formatJSON(this);
		});
		
		function formatJSON(o) {
			window.TAB = window.SINGLE_TAB;
			var row = $(o).closest('tr')
			// alert(JSON.stringify(row))	// json to string
			var json = row.find('td:nth-child(3)').text();
			var html = "";
			var obj = eval("["+json+"]");
			html = ProcessObject(obj[0], 0, false, false, false);
			$id("format-content").innerHTML = "<PRE class='CodeContainer'>"+html+"</PRE>";
		}
	</script>

</body>
</html>