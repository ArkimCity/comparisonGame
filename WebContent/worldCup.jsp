<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="worldCup.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://requirejs.org/docs/release/2.3.5/minified/require.js"></script>
</head>
<script>
	const gamedatas = JSON.parse(`${requestScope.gamedatas}`);

    let tempgamedatas;

    function startChoice() {
        document.getElementById("start").style.display = "none";
        tempgamedatas = gamedatas.map(v => v);
        choiceLeft();
        choiceRight();
        document.getElementById("comparison").style.display = "";
    }

    function choiceRight() {
        if (tempgamedatas.length > 0) {
            let tempIndex = parseInt(Math.random()*(tempgamedatas.length));
            let temp = tempgamedatas[tempIndex];
            document.getElementById("imageLeft").innerHTML = temp.imgsrc.replace(":","=");
            document.getElementById("leftTitle").innerHTML = temp.title + "<br><br>";
            document.getElementById("leftTitle").innerHTML += temp.parameter;
            tempgamedatas.splice(tempIndex, 1);
        } else {
            document.getElementById("comparison").innerHTML = 
            	document.getElementById("imageRight").innerHTML + "<center><br>우승은 " + document.getElementById("rightTitle").innerHTML + "!!"
            	+ "<br><br><br><br> <a href=\"${pageContext.request.contextPath}/worldofwords?command=getWorldCupList\">월드컵 리스트로 이동하기</a></center>";
        }
    }
    
    function choiceLeft() {
        if (tempgamedatas.length > 0) {
            let tempIndex = parseInt(Math.random() * (tempgamedatas.length));
            let temp = tempgamedatas[tempIndex];
            document.getElementById("imageRight").innerHTML = temp.imgsrc.replace(":","=");
            document.getElementById("rightTitle").innerHTML = temp.title + "<br><br>";
            document.getElementById("rightTitle").innerHTML += temp.parameter;
            tempgamedatas.splice(tempIndex, 1);
        } else {
            document.getElementById("comparison").innerHTML = 
            	document.getElementById("imageLeft").innerHTML + "<center><br>우승은 " + document.getElementById("leftTitle").innerHTML + "!!"
            	+ "<br><br><br><br> <a href=\"${pageContext.request.contextPath}/worldofwords?command=getWorldCupList\">월드컵 리스트로 이동하기</a></center>";
        }
    }
    
    //1. 월드컵 진행화면 - 진짜 월드컵 식으로 16강 처럼 - 2진수 비교하면서 자르겠습니다. 4 8 16
    //2. 월드컵 생성화면 - 
    //3. 월드컵 수정화면

</script>
<body style="background-color:#000000;">
    <div id="start" style="min-height: 100vh;">
        <div style="min-height: 100vh;">
            <div class="image-container" style="min-height: 100vh;">
                <article class="location-listing" style="min-height: 100vh;">
                    <a class="location-title" onclick="startChoice()" href="#" style="min-height: 100vh;">
                    	${requestScope.category} 월드컵을 선택하셨군요!<br><br>
                    	게임 시작!!</a>
                    <div id="startingImageContainer" class="location-image" style="min-height: 100vh;">
                    	<img src="https://www.loverugbyleague.com/content/uploads/2018/11/The-Rugby-League-World-Cup-trophy-restored-with-the-Cockerel.jpg" style="min-height: 100vh;">
                    </div>
                </article>
            </div>
        </div>
    </div>
    <div id="comparison" style="display: none; color: aliceblue;">
        <div>
            <div class="image-container">
                <article class="location-listing">
                    <a id="leftTitle" class="location-title" onclick="choiceLeft()" href="#"></a>
                    <div id="imageLeft" class="location-image">
                    	<img id="imageSourceLeft" src="" style="min-height: 50vh;">
                    </div>
                </article>
            </div>
        </div>
        <div id="comparison">
            <div class="image-container">
                <article class="location-listing">
                    <a id="rightTitle" class="location-title" onclick="choiceRight()" href="#"></a>
                    <div id="imageRight" class="location-image">
                    	<img id="imageSourceRight" src="" style="min-height: 50vh;">
                    </div>
                </article>
            </div>
        </div>
        <div id="result"></div>
    </div>
</body>
	<center><a href="${pageContext.request.contextPath}/index.html" style="position: absolute; bottom: 0px;">메인 화면으로 돌아가기</a></center>
</html>