<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title>World of Words</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}

HTML CSSResult Skip Results Iframe
EDIT ON
body {
	padding: 20px;
	font-family: sans-serif;
	background: #f2f2f2;
}

img {
	width: auto; /* need to overwrite inline dimensions */
	height: auto;
}

h2 {
	margin-bottom: .5em;
}

.grid-container {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
	grid-gap: 1em;
}

/* hover styles */
.location-listing {
	position: relative;
}

.location-image {
	line-height: 0;
	overflow: hidden;
}

.location-image img {
	filter: blur(0px);
	transition: filter 0.3s ease-in;
	transform: scale(1.1);
}

.location-title {
	font-size: 1.5em;
	font-weight: bold;
	text-decoration: none;
	z-index: 1;
	position: absolute;
	height: 100%;
	width: 100%;
	top: 0;
	left: 0;
	opacity: 0;
	transition: opacity .5s;
	background: rgba(90, 0, 10, 0.4);
	color: white;
	/* position the text in t’ middle*/
	display: flex;
	align-items: center;
	justify-content: center;
}

.location-listing:hover .location-title {
	opacity: 1;
}

.location-listing:hover .location-image img {
	filter: blur(2px);
}

/* for touch screen devices */
@media ( hover : none) {
	.location-title {
		opacity: 1;
	}
	.location-image img {
		filter: blur(2px);
	}
}
Resources1×0


.5×0


.25×Rerun
</style>
<!-- Navigation Bar -->
<div style="position: relative; z-index: 2;">
	<jsp:include page="NavigationBar.jsp"></jsp:include>
</div>
<body class="w3-light-grey">

	<%-- <c:forEach items="${requestScope.userwords}" var="userword">
			${userword} <button onclick="location.href='worldofwords?command=deleteUserWord&id=${sessionScope.id}&word=${userword}'">삭제</button><br>
</c:forEach> --%>
	<center>
		<!-- word list -->
		<div style="margin-top: 100px">
			<h2>네이버는 뭘 떠올렸나요?</h2>
			<c:forEach items="${requestScope.relatedWordMap}" var="wordMap">
				<article>
					<h3>${wordMap}</h3>
					<br>
				</article>
			</c:forEach>
		</div>
		<!-- word list2 -->
		<div style="margin-top: 100px">
			<h2>그럼 구글은 뭘 떠올렸나요?</h2>
			<c:forEach items="${requestScope.relatedWordMap2}" var="wordMap">
				<article>
					<h3>${wordMap}</h3>
					<br>
				</article>
			</c:forEach>
		</div>
	</center>
	<!-- image grid -->
	<div class="child-page-listing" style="margin-top: 100px">
		<h2>그 다음 구글에서 해당 검색어로 검색한 사진들을 다 섞어서 보여드릴게요!</h2>
		<div class="grid-container">
			<c:forEach items="${requestScope.imageRandomBigList}" var="image">
				<article id="3691" class="location-listing">
					<a class="location-title" href="#"> ${image.title} </a>
					<div class="location-image">${image.source}</div>
				</article>
			</c:forEach>
		</div>
		<!-- end grid container -->
	</div>

</body>
</html>
