<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
		html,
		body,
		h1,
		h2,
		h3,
		h4,
		h5,
		h6 {
			font-family: "Roboto", sans-serif
		}
	</style>
	<title>World of Words</title>
	<jsp:include page="NavigationBar.jsp"></jsp:include>
</head>

<body>
	<div style="margin-top: 100px;">
		<center>
			<img src="images/web3.PNG" style="width: 60%" alt="ours">
		</center>
	</div>
	이번 프로젝트를 통해 오라클 DB부터 WEB상에 화면까지 통합적으로 개발하는 프로젝트를 수행하였습니다.

	특히 JSP를 사용하여 MVC 패턴을 적용했기 때문에 사용자에게 보여지는 화면인 View, 실제 비즈니스로 로직이 들어가는 Model,

	그리고 View와 Model을 연결시켜주는 Controller에서 View와 Model 부분의 분업으로 훨씬 더 효율적인 개발이 가능합니다.

	이는 공통되는 로직의 재사용이 가능하여 생산성이 높아지게 됩니다.


</body>

</html>