<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<title>왼쪽? 오른쪽?</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
.w3-bar,h1,button {font-family: "Montserrat", "Lato"}
.fa-anchor,.fa-coffee {font-size:200px}
</style>
<body>

<!-- Navbar -->
<div class="w3-top">
 <div class="w3-bar w3-theme-d2 w3-left-align" >
  <a href="${pageContext.request.contextPath}/index.jsp" class="w3-bar-item w3-button w3-teal" style="padding:15px 30px"><i class="fa fa-trophy" style="font-size:15px"></i>  왼쪽? 오른쪽?</a>
  <a href="worldofwords?command=getWorldCupList" class="w3-bar-item w3-button w3-hide-small" title="공개된 월드컵 리스트를 확인하려면 여기로!" style="padding:15px 30px">모든 월드컵</a>
  <a href="foodWorldCupPrepare.jsp" class="w3-bar-item w3-button w3-hide-small" title="점심, 저녁 메뉴 결정은 여기로!" style="padding:15px 30px">이따가 뭐 먹지?</a>
  <a href="LoginPage.jsp" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-teal" style="padding:15px 30px" title="Login" ><i class="fa fa-sign-in" style="font-size:15px"></i> Login</a>
  </div>
 </div>

<!-- Header -->
<header class="w3-container w3-white w3-center">
<img src="https://media.istockphoto.com/vectors/cartoon-man-choosing-between-a-and-b-decisions-two-plans-hand-drawn-vector-id1178558982?k=6&m=1178558982&s=170667a&w=0&h=GnYOl4y0OFqHt79X_5pADXM_E6IGUrcoc98kEiiZw2c=" alt="Girl in a jacket" width="500" height="600">
<h1>「왼쪽? 오른쪽?」 가입을 환영합니다!</h1>
<p>이메일 주소만으로 간단히 회원가입이 가능합니다.</p>
<form action="comparisonGame?command=userInsert" method="post" target="_blank">
	<div class="w3-row-padding" style="margin:0 -16px 8px -16px">
		<div>
			<input class="w3-input w3-border" type="text" placeholder="이메일" name="userId" id="userId">
			<input class="w3-input w3-border" type="password" placeholder="비밀번호" name="password" id="password">
			<input class="w3-input w3-border" type="text" placeholder="성함" name="userName" id="userName">
			<input class="w3-input w3-border" type="text" placeholder="닉네임" name="nickName" id="nickName">
		</div>
		<button onclick="SignUpSuccess()" class="w3-button w3-black w3-padding-large w3-large w3-margin-top w3-animate-opacity" type="submit">
			<i class="fa fa-paper-plane"></i> 계정 생성
		</button>
	</div>
</form>
<p><small>이미 계정이 있으신가요?</small></p>
<a href="LoginPage.jsp" class="w3-button w3-black w3-padding-large w3-large w3-margin-top w3-animate-opacity">로그인</a>
</header>

<script>
function SignUpSuccess() {
  alert("계정 생성이 완료되었습니다. 메인 페이지로 돌아갑니다.");
}
</script>

</body>
</html>