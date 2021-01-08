<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>World of Words</title>
<link rel="stylesheet" href="login-page.css">
</head>
<body>
	<main id="main-holder">
	<h1 id="login-header">Login</h1>
	${requestScope.errorMsg}
	<form id="login-form" action="worldofwords?command=login" method="post">
		<input id="username-field" type="text" name="id" placeholder="Username"><br>
		<input id="password-field" type="password" name="pw" placeholder="Password"><br>
		<input id="login-form-submit" type="submit" value="login">
		<input type="hidden" name="userwordscommand" value="getUserWords">
	</form>
	<a href="index.html">메인 화면 이동</a>
	</main>
</body>
</html>

<!-- html css javascript -->