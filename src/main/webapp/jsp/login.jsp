<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title>Login - Bikesharing</title>
</head>
<body background="${request.contextPath}/bghd.jpg">
<div class="card" style="margin: auto; padding: 10px; width: 400px">
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login" />
        <div class="form-group">
            <label for="exampleInputLogin1">Login</label>
            <input name="login" type="text" class="form-control" id="exampleInputLogin1" placeholder="Enter login">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input name="password" type="password" class="form-control" id="exampleInputPassword1" aria-describedby="passwordHelp" placeholder="Password">
        </div>
        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Remember me</label>
        </div>
        <button type="submit" class="btn btn-primary" style="width: 100%">Log in</button>
        ${errorLoginPassMessage}
        ${wrongAction}
        ${nullPage}
    </form>
    <a href="${request.contextPath}/controller?command=signup">
        <button class="btn btn-secondary" style="width: 100%">Sign up</button>
    </a>
</div>
</body>
</html>