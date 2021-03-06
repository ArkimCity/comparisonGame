<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>왼쪽? 오른쪽?</title>
    <link rel="stylesheet" href="foodWorldCup.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://requirejs.org/docs/release/2.3.5/minified/require.js"></script>
</head>
<script>
	const foods = JSON.parse(`${requestScope.restaurants}`);

    let tempfoods;

    function startChoice() {
        document.getElementById("start").style.display = "none";
        tempfoods = foods.map(v => v);
        choiceLeft();
        choiceRight();
        document.getElementById("comparison").style.display = "";
    }
    
    function statusUpdate() {
        document.getElementById("currentStatus").innerHTML = "검색된 " + (foods.length) + "개의 장소 중 " + (tempfoods.length) + "개 남았습니다.";
    }

    function choiceRight() {
        if (tempfoods.length > 0) {
            let tempIndex = parseInt(Math.random()*(tempfoods.length));
            let temp = tempfoods[tempIndex];
            document.getElementById("imageSourceLeft").src = temp.thumUrl;
            document.getElementById("leftTitle").innerHTML = temp.name + "<br><br>";
            document.getElementById("leftTitle").innerHTML += temp.category + "<br><br>";
            document.getElementById("leftTitle").innerHTML += temp.roadAddress;
            tempfoods.splice(tempIndex, 1);
            statusUpdate();
        } else {
            document.getElementById("comparison").innerHTML = 
            	document.getElementById("imageRight").innerHTML + "<center><br>우승은 " + document.getElementById("rightTitle").innerHTML + "!!"
            	+ "<br><br><br><br> <a href=\"${pageContext.request.contextPath}/index.jsp\">메인 화면으로 이동하기</a></center>";
        }
    }

    function choiceLeft() {
        if (tempfoods.length > 0) {
            let tempIndex = parseInt(Math.random() * (tempfoods.length));
            let temp = tempfoods[tempIndex];
            document.getElementById("imageSourceRight").src = temp.thumUrl;
            document.getElementById("rightTitle").innerHTML = temp.name + "<br><br>";
            document.getElementById("rightTitle").innerHTML += temp.category + "<br><br>";
            document.getElementById("rightTitle").innerHTML += temp.roadAddress;
            tempfoods.splice(tempIndex, 1);
            statusUpdate();
        } else {
            document.getElementById("comparison").innerHTML = 
            	document.getElementById("imageLeft").innerHTML + "<center><br>우승은 " + document.getElementById("leftTitle").innerHTML + "!!"
            	+ "<br><br><br><br> <a href=\"${pageContext.request.contextPath}/index.jsp\">메인 화면으로 이동하기</a></center>";;
        }
    }
</script>
	<body style="background-color:#000000;">
	    <div id="start" style="min-height: 100vh;">
	        <div style="min-height: 100vh;">
	            <div class="image-container" style="min-height: 100vh;">
	                <article class="location-listing" style="min-height: 100vh;">
	                    <a class="location-title" onclick="startChoice()" href="#" style="min-height: 100vh;">
	                    	${requestScope.address}
	                    	<br><br>
	                    	${requestScope.searchKeyWords} 을/를 검색하셨군요!<br><br>
	                    	게임 시작!!</a>
	                    <div class="location-image" style="min-height: 100vh;">
	                    	<img src="https://offloadmedia.feverup.com/secretldn.com/wp-content/uploads/2017/08/18173246/FullSizeRender4.jpg" style="min-height: 100vh;">
	                    </div>
	                </article>
	            </div>
	        </div>
	    </div>
	    <div id="comparison" style="display: none; color: aliceblue;">
            <div class="image-container">
                <article class="location-listing">
                    <a id="leftTitle" class="location-title" onclick="choiceLeft()" href="#"></a>
                    <div id="imageLeft" class="location-image">
                    	<img id="imageSourceLeft" src="https://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/07/01/20190701000670_0.jpg" style="min-height: 50vh;">
                    </div>
                </article>
            </div>
            <div id="currentStatus" style="color: aliceblue; text-align: center;" ></div>
            <div class="image-container">
                <article class="location-listing">
                    <a id="rightTitle" class="location-title" onclick="choiceRight()" href="#"></a>
                    <div id="imageRight" class="location-image">
                   		<img id="imageSourceRight" src="https://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/07/01/20190701000670_0.jpg" style="min-height: 50vh;">
                   </div>
               </article>
        	</div>
    	</div>
        <div id="result"></div>
	</body>
</html>