<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title>World of Words</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="worldCupList.css">
<style>
	img
</style>
<!-- Navigation Bar -->
<div style="position: relative; z-index: 2;">
	<jsp:include page="NavigationBar.jsp"></jsp:include>
</div>

<body class="w3-light-grey"> <!-- image grid -->
	<div class="child-page-listing" style="margin-top: 80px">
		<div class="grid-container">
			<c:forEach items="${requestScope.worldCupList}" var="worldCup">
				<article id="3691" class="location-listing" onclick="location.href='worldofwords?command=getWorldCup&category=${worldCup.title}'">
					<a class="location-title" href="#"> ${worldCup.title} </a>
					<div class="location-image">${worldCup.source}</div>
				</article>
			</c:forEach>
		</div>
	</div>
</body> 
<!-- end grid container -->
<!-- onclick="location.href='worldofwords?command=getWorldCup&category=${worldCup.title}'" -->
</html>