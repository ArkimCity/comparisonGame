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
	const foods = JSON.parse(`${requestScope.restaurants}`);
	/*
    const foods = [
        {"food": "한식", "imageSource": `<img id="bg" src="https://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/07/01/20190701000670_0.jpg" style="min-height: 50vh;">` },
        {"food": "양식", "imageSource": `<img id="bg" src="https://cdn.r-corona.jp/prd.jln.r-corona.jp/assets/site_files/3x9cf5gc/632160/ecav_bw760h570.jpg" style="min-height: 50vh;">` },
        {"food": "중식", "imageSource": `<img id="bg" src="https://blog.kakaocdn.net/dn/cQadDg/btqIZmNixWI/kkef5cKMY4YiWIDBcsX7IK/img.png" style="min-height: 50vh;">` },
        {"food": "일식", "imageSource": `<img id="bg" src="https://media.istockphoto.com/photos/serving-sushi-rolls-and-other-traditional-japanese-and-asian-food-on-picture-id1136188508" style="min-height: 50vh;">` }
    ]
	*/
    
    let tempfoods;

    function startChoice() {
        document.getElementById("start").style.display = "none";
        tempfoods = foods.map(v => v);
        choiceLeft();
        choiceRight();
        document.getElementById("comparison").style.display = "";
    }

    function choiceRight() {
        if (tempfoods.length > 0) {
            let tempIndex = parseInt(Math.random()*(tempfoods.length));
            let temp = tempfoods[tempIndex];
            document.getElementById("imageSourceLeft").src = temp.thumUrl;
            document.getElementById("leftTitle").innerText = temp.name;
            tempfoods.splice(tempIndex, 1);
        } else {
            document.getElementById("comparison").innerHTML = document.getElementById("imageRight").innerHTML + "우승은 " + document.getElementById("rightTitle").innerHTML + "!!";
        }
    }
    
    function choiceLeft() {
        if (tempfoods.length > 0) {
            let tempIndex = parseInt(Math.random() * (tempfoods.length));
            let temp = tempfoods[tempIndex];
            document.getElementById("imageSourceRight").src = temp.thumUrl;
            document.getElementById("rightTitle").innerText = temp.name;
            tempfoods.splice(tempIndex, 1);
        } else {
            document.getElementById("comparison").innerHTML = document.getElementById("imageLeft").innerHTML + "우승은 " + document.getElementById("leftTitle").innerHTML + "!!";
        }
    }

</script>
<body style="background-color:#000000;">
    <div id="start">
        <button onclick="startChoice()">게임 시작</button>
    </div>
    <div id="comparison" style="display: none; color: aliceblue;">
        <div>
            <div class="image-container">
                <article class="location-listing">
                    <a id="leftTitle" class="location-title" onclick="choiceLeft()" href="#"></a>
                    <div id="imageLeft" class="location-image">
                    	<img id="imageSourceLeft" src="https://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/07/01/20190701000670_0.jpg" style="min-height: 50vh;">
                    </div>
                </article>
            </div>
        </div>
        <div id="comparison">
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
    </div>
</body>
</html>