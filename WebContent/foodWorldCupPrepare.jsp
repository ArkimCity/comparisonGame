<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>왼쪽? 오른쪽?</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body, h1 {
	font-family: "Raleway", sans-serif
}

body, html {
	height: 100%
}

.bgimg {
	background-image: url('data:image/jpeg;base64,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');
	min-height: 100%;
	background-position: center;
	background-size: cover;
}
</style>
<!-- Navbar -->
<div class="w3-top">
 <div class="w3-bar w3-theme-d2 w3-left-align" >
  <a href="${pageContext.request.contextPath}/index.jsp" class="w3-bar-item w3-button w3-hide-small" style="padding:15px 30px"><i class="fa fa-trophy" style="font-size:15px"></i>  왼쪽? 오른쪽?</a>
  <a href="comparisonGame?command=getWorldCupList" class="w3-bar-item w3-button w3-hide-small" title="공개된 월드컵 리스트를 확인하려면 여기로!" style="padding:15px 30px">모든 월드컵</a>
  <a href="foodWorldCupPrepare.jsp" class="w3-bar-item w3-button w3-teal" title="점심, 저녁 메뉴 결정은 여기로!" style="padding:15px 30px">이따가 뭐 먹지?</a>
  <a href="LoginPage.jsp" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-teal" style="padding:15px 30px" title="Login" ><i class="fa fa-sign-in" style="font-size:15px"></i> Login</a>
  </div>
 </div>
<head>
<script>
	function getLocation() {
		if (navigator.geolocation) { // GPS를 지원하면
			navigator.geolocation.getCurrentPosition(
				function(position) {
					//alert(position.coords.latitude + ' ' + position.coords.longitude);
					document.getElementById("location").value = "위치 확인 완료!";
					document.getElementById("lattitude").value = position.coords.latitude;
					document.getElementById("longtitude").value = position.coords.longitude;
				}, function(error) {
					console.error(error);
				}, {
					enableHighAccuracy : false,
					maximumAge : 0,
					timeout : Infinity
				});
		} else {
			alert('GPS를 지원하지 않습니다');
		}
	}
	getLocation();
</script>
<script>
	// Script to open and close sidebar
	function w3_open() {
		document.getElementById("mySidebar").style.display = "block";
	}
	function w3_close() {
		document.getElementById("mySidebar").style.display = "none";
	}
</script>

<!-- Sidebar (hidden by default) -->
</head>
<body>
	<div class="bgimg w3-display-container w3-animate-opacity w3-text-white">
		<div class="w3-display-middle">
			<h4 class="w3-middle w3-animate-top">여기는 음식점 으깨기 리그!</h4>
			<form action="comparisonGame?command=foodWorldCup" method="post">
				위치 정보를 제공해주시면 <br>더 정확한 결과가 나옵니다!<br>
				<input id="location" type="text" value="위치 서비스 사용불가ㅜ" readonly><br><br>
				검색해 주세요! : <input name="searchKeyWords" type="text" value="아구찜"><br><br>
				<input id="lattitude" type="hidden" name="lattitude"> 
				<input id="longtitude" type="hidden" name="longtitude"> 
				<input type="submit" value="가즈아">
			</form>
		</div>
	</div>
</body>

</html>
