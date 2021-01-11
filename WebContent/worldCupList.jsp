<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<title>왼쪽? 오른쪽?</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="sweetalert.css">
<link rel="stylesheet" href="worldCupList.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
.w3-bar,h1,button {font-family: "Montserrat", "Lato"}
.fa-anchor,.fa-coffee {font-size:200px}
</style>
<body>

<!-- 강아지 월드컵 | 시작 -->
<c:forEach items="${requestScope.worldCupList}" var="worldCup">
<div class="child-page-listing w3-animate-opacity" style="margin-top: 100px">
<div class="col-xs-6 col-sm-3 col-lg-2" style="padding:2px">
<div class="ibox">
<div class="ibox-content product-box">
<a class="w3-xlarge w3-wide w3-animate-opacity">강아지 월드컵</a>
<div class="product-imitation" style="height:200px;padding:0px">
<div style="float:left;width:50%;height:180px;background-image: url('https://contents.sixshop.com/thumbnails/uploadedFiles/79226/product/image_1583484044683_750.jpg');background-repeat:no-repeat;background-size: cover;background-position: center top;display:inline-block;">
</div>
<div style="width:50%;height:180px;background-image: url('https://d1bg8rd1h4dvdb.cloudfront.net/upload/imgServer/storypick/editor/2020062615503065168.jpg');background-repeat:no-repeat;background-size: cover;background-position: center top;display:inline-block;">
</div>
<div style="float:left;color:#676a6c;width:50%;max-height:20px;overflow: hidden; text-overflow: ellipsis;white-space: nowrap;display:inline-block;">포메라니안</div>
<div style="color:#676a6c;width:50%;max-height:20px;overflow: hidden; text-overflow: ellipsis;white-space: nowrap;display:inline-block;">골든리트리버</div>
</div>
<a href="comparisonGame?command=getWorldCup&category=${worldCup.title}" class="btn btn-sm btn-outline btn-danger"  style="padding:7px 126px"><i class="fa fa-play"></i> 시작하기</a>
</div>
</div>
</div>
</div>
<!-- 강아지 월드컵 | 끝 -->

<!--  라면 월드컵 | 시작 -->
<div class="child-page-listing w3-animate-opacity" style="margin-top: 100px">
<div class="col-xs-6 col-sm-3 col-lg-2" style="padding:2px">
<div class="ibox">
<div class="ibox-content product-box">
<a class="w3-xlarge w3-wide w3-animate-opacity">라면 월드컵</a>
<div class="product-imitation" style="height:200px;padding:0px">
<div style="float:left;width:50%;height:180px;background-image: url('https://www.costco.co.kr/medias/sys_master/images/h64/h96/9867844452382.jpg');background-repeat:no-repeat;background-size: cover;background-position: center top;display:inline-block;">
</div>
<div style="width:50%;height:180px;background-image: url('https://www.costco.co.kr/medias/sys_master/images/h16/h00/17480497725470.jpg');background-repeat:no-repeat;background-size: cover;background-position: center top;display:inline-block;">
</div>
<div style="float:left;color:#676a6c;width:50%;max-height:20px;overflow: hidden; text-overflow: ellipsis;white-space: nowrap;display:inline-block;">신라면</div>
<div style="color:#676a6c;width:50%;max-height:20px;overflow: hidden; text-overflow: ellipsis;white-space: nowrap;display:inline-block;">진라면</div>
</div>
<div class="product-desc" style="cursor:Pointer" data-id="RAMEN">
<div class="btn-group" data-id="RAMEN">
<a href="comparisonGame?command=getWorldCup&category=${worldCup.title}" class="btn btn-sm btn-outline btn-danger" style="padding:7px 126px"><i class="fa fa-play"></i> 시작하기</a>
</div>
</div>
</div>
</div>
</div>
</div>
</c:forEach>
<!--  라면 월드컵 | 끝 -->

	<%-- <div class="child-page-listing w3-animate-opacity" style="margin-top: 80px">
		<div class="grid-container">
			<c:forEach items="${requestScope.worldCupList}" var="worldCup">
				<article class="location-listing" onclick="location.href='comparisonGame?command=getWorldCup&category=${worldCup.title}'">
					<a class="location-title" href="#" > ${worldCup.title} </a>
					<div class="location-image">${worldCup.source}</div>
				</article>
			</c:forEach>
		</div>
	</div> --%>

<!-- Navbar -->
<div class="w3-top">
	<div class="w3-bar w3-theme-d2 w3-left-align" >
		<a href="${pageContext.request.contextPath}/index.jsp" class="w3-bar-item w3-button w3-teal" style="padding:15px 30px"><i class="fa fa-trophy" style="font-size:15px"></i>  왼쪽? 오른쪽?</a>
		<a href="comparisonGame?command=getWorldCupList" class="w3-bar-item w3-button w3-hide-small" title="공개된 월드컵 리스트를 확인하려면 여기로!" style="padding:15px 30px">모든 월드컵</a>
		<a href="foodWorldCupPrepare.jsp" class="w3-bar-item w3-button w3-hide-small" title="점심, 저녁 메뉴 결정은 여기로!" style="padding:15px 30px">이따가 뭐 먹지?</a>
		<a href="LoginPage.jsp" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-teal" style="padding:15px 30px" title="Login" ><i class="fa fa-sign-in" style="font-size:15px"></i> Login</a>
	</div>
</div>
<header>
</header>
</body>
</html>