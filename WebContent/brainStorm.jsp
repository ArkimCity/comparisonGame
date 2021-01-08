<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Roboto'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html, body, h1, h2, h3, h4, h5, h6 {
	font-family: "Roboto", sans-serif
}
</style>
<title>World of Words</title>
<jsp:include page="NavigationBar.jsp"></jsp:include>
</head>
<body>
	<div style="margin-top: 100px;">
		<center>
			<%
				String id = (String) session.getAttribute("id");
				if (id == null) {
					out.print("<h2>아직 로그인 하지 않았나보네요ㅜㅜ 이 서비스는 로그인 하신 분께만 제공해드립니다</h2>");
					out.print("<h2><a href=\"login-page.jsp\">Log In</a> 해주세요!</h2>");
				} else {
					out.print("<h2>사진, 연관 검색어, 예시 문장 등으로 같이 브레인스토밍 해봐요!</h2>");
					out.print("<h2><a href=\"worldofwords?command=brainStormResult\">가볼까요!!</a></h2>");
				}
			%>
		</center>
	</div>
</body>
</html>