<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<!-- Bootstrap -->
<link href="/moana/css/bootstrap.min.css" rel="stylesheet">
<style>  
.vertical-center{
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  width:400px; height:200px
}

body {background:url(/moana/img/1.jpg) top center no-repeat; background-size:cover;}
</style> 
</head>


<body>
<div class="container">  
  <div class="row myCenter">  
    <div class="col-xs-6 col-md-4 vertical-center">  
      <form class="form-signin" action=/moana/tickets/loginsubmit>  
        <h2 class="form-signin-heading"><font color="#ffffff">请登录</frot></h2>  
        <label for="username" class="sr-only">账号</label>  
        <input type="text" name="username" class="form-control" placeholder="QQ号码" required autofocus> 
        <label for="inputPassword" class="sr-only">密码</label>  
        <input type="password" name="password" class="form-control" placeholder="QQ密码" required>  
        <div class="checkbox">  
          <label>  
            <input type="checkbox" value="remember-me">  
            记住我 </label>  
        </div>  
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>  
      </form>  
    </div>  
  </div>  
</div>
	
</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/moana/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/moana/js/bootstrap.min.js"></script>

</html>