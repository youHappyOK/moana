<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>电影列表</title>
<!-- Bootstrap -->
<link href="/moana/css/bootstrap.min.css" rel="stylesheet">

</head>


<body>
	<div class="container-fluid">

		<nav class="navbar navbar-inverse" role="navigation">
			<h1><strong><font color="#ffffff">电影列表<strong></font> <small><font color="#ffffff">点击进入抢购页面开始抢票</font></small></h1>
		</nav>
		
		<c:forEach var="movie" items="${movielist}" varStatus="status">
			<c:if test="${status.index%2==0}"><div class="row"></c:if>
				<div class="col-xs-6">
					<table class="table table-hover">
						<tr class="active">
							<td rowspan="7" width="25%"><img src="${pageContext.servletContext.contextPath}/img/${movie.movieId}.jpg" alt="Responsive image" class="img-rounded"></td>
							<td style="vertical-align:middle; text-align:center;"><font size="5">电影名：${movie.name}</font></td>
						</tr>
						<tr class="success">
							<td style="vertical-align:middle; text-align:center;"><font size="5">剩余：${movie.number}</font></td>
						</tr>
						<tr class="warning">
							<td style="vertical-align:middle; text-align:center;"><font size="5">抢购开始时间：<fmt:formatDate value="${movie.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></font></td>
						</tr>
						<tr class="danger">
							<td style="vertical-align:middle; text-align:center;"><font size="5">抢购结束时间：<fmt:formatDate value="${movie.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></font></td>
						</tr>
						<tr class="info">
							<td style="vertical-align:middle; text-align:center;"><font size="5">上映时间：<fmt:formatDate value="${movie.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></font></td>
						</tr>
						<tr class="warning">	
							<td style="vertical-align:middle; text-align:center;"><a href="/moana/tickets/movie/${movie.movieId}" class="btn btn-primary btn-lg" role="button">抢购</a></td>
						</tr>
		  			</table>	
				</div>
			<c:if test="${status.index%2==1}"></div></c:if>
		</c:forEach>	
	</div>
</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/moana/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/moana/js/bootstrap.min.js"></script>

</html>