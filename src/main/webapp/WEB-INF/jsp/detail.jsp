<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title>抢购详情页</title>
<!-- Bootstrap -->
<link href="/moana/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panel-heading"><h1>${movieInfo.name}</h1></div>
			<div class="panel-heading"><h1>当前剩余：${movieInfo.number}</h1></div>
		</div>
		<div class="panel-body">
			<h2 class="text-danger  text-center">
				<span class="glyphicon glyphicon-time"></span>
				<span class="glyphicon" id="seckill-box"></span>
			</h2>
		</div>
	</div>
	
	<!-- 模态框 -->
	<div id="killMailModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>填写邮箱
					</h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killMail" id="killMailKey" placeholder="填写邮箱" class="form-control" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<span id="killMailMessage" class="glyphcion"></span>
					<button type="button" id="killMailBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>Sumbit
					</button>
				</div>
			</div>
		</div>
	</div>
</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/moana/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/moana/js/bootstrap.min.js"></script>

<!-- jquery-cookie插件 -->
<script type="text/javascript" src="/moana/js/jquery.cookie.min.js"></script>
<!-- Countdown插件 -->
<script src="/moana/js/jquery.countdown.min.js"></script>
<!-- 逻辑 -->
<script type="text/javascript" src="/moana/js/seckill.js"></script>
<script type="text/javascript">
	$(function(){
		seckill.detail.init({
			movieId : ${movieInfo.movieId},
			startTime : ${movieInfo.startTime.time},
			endTime : ${movieInfo.endTime.time}
		});
	});
</script>

</html>