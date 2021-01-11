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

    let tempCurrentWinners;
    let tempgamedatas;
    let possibleGameLength = [];
    let detector = 2;
    let temptwogamedatas = [];
    let nextRound = [];

    while (detector <= gamedatas.length) {
        possibleGameLength.push(detector);
        detector = detector * 2;
    }

    function pickSize(number){
        tempgamedatas = gamedatas.map(v => v);
        for (i=0 ; i < number; i++){
            let tempIndex = parseInt(Math.random() * (tempgamedatas.length));
            temptwogamedatas.push(tempgamedatas[tempIndex]);
            tempgamedatas.splice(tempIndex, 1);
        }
    }

    function startChoice() {
        document.getElementById("start").style.display = "none";
        tempgamedatas = pickSize(possibleGameLength[-1]);
        choiceLeft();
        choiceRight();
        document.getElementById("comparison").style.display = "";
    }

    function roundChecker(direction) {
        let detector = true;
        if (tempgamedatas.length == 0 && nextRound.length == 1) {
            document.getElementById("comparison").innerHTML = 
            document.getElementById("image" + direction).innerHTML + "<center><br>우승은 " + document.getElementById("title" + direction).innerHTML + "!!"
            + "<br><br><br><br> <a href=\"${pageContext.request.contextPath}/index.html\">메인 화면으로 이동하기</a></center>";
            detector = false;
        } else if (tempgamedatas.length == 0 && nextRound.length > 1){
            tempgamedatas = nextRound.map(v => v);
            nextRound = [];
        }
        return detector;
    }
    
    function loadNext(){
        let tempIndex = parseInt(Math.random()*(tempgamedatas.length));
        let temp = tempgamedatas[tempIndex];
        document.getElementById("imageLeft").innerHTML = temp.imgsrc.replace(":","=");
        document.getElementById("titleLeft").innerHTML = temp.title;
        // document.getElementById("titleLeft").innerHTML += temp.parameter;
        tempgamedatas.splice(tempIndex, 1);

        let tempIndex2 = parseInt(Math.random() * (tempgamedatas.length));
        let temp2 = tempgamedatas[tempIndex2];
        document.getElementById("imageRight").innerHTML = temp2.imgsrc.replace(":","=");
        document.getElementById("titleRight").innerHTML = temp2.title;
        // document.getElementById("titleRight").innerHTML += temp2.parameter;
        tempgamedatas.splice(tempIndex2, 1);
    }
 
    function startChoice() {
        document.getElementById("start").style.display = "none";
        tempgamedatas = gamedatas.map(v => v);
        loadNext();
        document.getElementById("comparison").style.display = "";
    }

    function choiceRight() {
        nextRound.push(gamedatas.find(function(item) {return item.title === document.getElementById("titleRight").innerHTML}));
        if (roundChecker("Right")) {
            loadNext();
        }
    }
        
    function choiceLeft() {
        nextRound.push(gamedatas.find(function(item) {return item.title === document.getElementById("titleLeft").innerHTML}));
        if (roundChecker("Left")) {
            loadNext();
        }
    }
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
                    <a id="titleLeft" class="location-title" onclick="choiceLeft()" href="#"></a>
                    <div id="imageLeft" class="location-image">
                    	<img id="imageSourceLeft" src="" style="min-height: 50vh;">
                    </div>
                </article>
            </div>
        </div>
        <div id="comparison">
            <div class="image-container">
                <article class="location-listing">
                    <a id="titleRight" class="location-title" onclick="choiceRight()" href="#"></a>
                    <div id="imageRight" class="location-image">
                    	<img id="imageSourceRight" src="" style="min-height: 50vh;">
                    </div>
                </article>
            </div>
        </div>
        <div id="result"></div>
    </div>
</body>
</html>