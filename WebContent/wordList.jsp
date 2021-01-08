<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<title>World of Words</title>
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

<jsp:include page="NavigationBar.jsp"></jsp:include>

<!-- Page Container -->
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">
	<!-- The Grid -->
	<div class="w3-row-padding">
		<!-- Left Column -->
		<div class="w3-third" style="height: 100%;">
			<div class="w3-white w3-text-grey w3-card-4">
				<div class="w3-container">
					<br>
					<%
						String id = (String) session.getAttribute("id");
						if (id == null) {
							out.print("pleas <a href=\"login-page.jsp\">Log In</a> to save your words");
						} else {
							out.print("Welcome! " + id);
							out.print("<a href=\"worldofwords?command=logout&userwordscommand=getCurrentWords\"> Log Out</a>");
						}
					%>
					<hr>
					<%
						if (id == null) {
							out.print("로그인 하시고 나만의 단어를 저장해주세요!");
						} else {
					%>
					<c:forEach items="${requestScope.userwords}" var="userword">
								${userword} <button onclick="location.href='worldofwords?command=deleteUserWord&id=${sessionScope.id}&word=${userword}'">삭제</button><br>
					</c:forEach>
					<br>
					<a href="worldofwords?command=brainStorm&userwordscommand=getCurrentWords">충분히 저장되셨다면 브레인스토밍하러 가볼까요!</a>
					<% } %>
					<hr>
					<form action="worldofwords?command=saveUserWord&id=${sessionScope.id}&userwordscommand=getCurrentWords" method="post">
						<input type="submit" class="w3-large w3-animate-top" value="저장"></input>
						<input type="text" name="word" /> 수동도 가능!<br>
					</form>
					<hr>
					<form action="worldofwords?command=getWordList&userwordscommand=getCurrentWords" method="post">
						<input type="submit" class="w3-large w3-animate-top" value="FIND OUT"></input> 
						<input type="text" name="inputnumber" value="10" /> words<br>
					</form>
					<hr>
					also find out our <a href="wordCommunity.jsp">community</a>! <br>
					<br>
				</div>
			</div>
			<br>
			<div class="w3-white w3-text-grey w3-card-4">
				<div class="w3-container">
					<h1>For What?</h1>
					Welcome to the website. If you're here, you're likely looking to
					find random words. Random Word Generator is the perfect tool to
					help you do this. While this tool isn't a word creator, it is a
					word generator that will generate random words for a variety of
					activities or uses. Even better, it allows you to adjust the
					parameters of the random words to best fit your needs. The first
					option the tool allows you to adjust is the number of random words
					to be generated. You can choose as many or as few as you'd like.
					You also have the option of choosing words that only begin with a
					certain letter, only end with a certain letter or only begin and
					end with certain letters. If you leave these blank, the randomize
					words that appear will be from the complete list. You also have the
					option of choosing the number of syllables of the words or the word
					length of the randomized words. There are also options to further
					refine by choosing "less than" or "greater than" options for both
					syllables and word length. Again, if you leave the space blank, the
					complete list of randomized words will be used. Finally, you can
					choose between standard text or cursive words. The cursive words
					will all be in cursive using cursive letters. Once you have input
					all of your specifications, all you have to do is to press the
					Generate Random Words button, and a list of random words will
					appear. Below are some of the common ways people use this tool.
					<hr>
					<h1>Games</h1>
					This tool can be useful for games like Pictionary or MadLibs. Since
					the words are random, this helps to keep a game like Pictionary
					fair for all those playing. For a game like MadLibs, it can help
					kids improve their vocabulary by generating words they may not have
					ever considered to fill in the blank spaces. The tool has the
					potential to help with any word game that doesn't require a
					specific word.
					<hr>
					<h1>Creative Writing</h1>
					For those who write, this tool can be an excellent device to aid in
					the creative writing process. By using the tool to create 5 random
					words, a writer could then attempt to use all of them in a single
					paragraph. Longer random word lists could be incorporated into a
					short story. Since the writer doesn't know which words will appear,
					creativity must be used to successfully incorporate all the words.
					To make the challenge even more difficult, the writer could try to
					use the words in the exact order they were generated. In this way,
					writers can challenge their creativity to push their writing
					skills.
					<hr>
					<h1>Spelling and Vocabulary</h1>
					This tool can be an excellent way to improve vocabulary or practice
					for spelling bees. If an unfamiliar word appears, looking up the
					meaning will help students increase the number of words they know.
					Working with a partner, students can test each other on the
					spelling for the randomized words generated. <br> <br>
				</div>
			</div>
			<!-- End Left Column -->
		</div>
		<!-- Right Column -->
		<div class="w3-twothird" >
			<c:forEach items="${sessionScope.words}" var="word">
				<div class="w3-container w3-card w3-white w3-margin-bottom">
					<h2 class="w3-text-grey w3-padding-16">
						<i
							class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>
						${word.word}
					</h2>
						<a href="worldofwords?command=saveUserWord&id=${sessionScope.id}&word=${word.word}" style="float: right">저장</a>
					<div class="w3-container">
						<h5 class="w3-opacity">
							<b></b>
						</h5>
						<p>${word.definition}</p>
						<hr>
					</div>
				</div>
			</c:forEach>
			<!-- End Right Column -->
		</div>
		<!-- End Grid -->
	</div>
	<!-- End Page Container -->
	<center>
		<a href="index.html">메인 화면 이동</a>
	</center>
</div>
<footer class="w3-container w3-teal w3-center w3-margin-top">
	<p>Find me on social media.</p>
	<i class="fa fa-facebook-official w3-hover-opacity"></i> <i
		class="fa fa-instagram w3-hover-opacity"></i> <i
		class="fa fa-snapchat w3-hover-opacity"></i> <i
		class="fa fa-pinterest-p w3-hover-opacity"></i> <i
		class="fa fa-twitter w3-hover-opacity"></i> <i
		class="fa fa-linkedin w3-hover-opacity"></i>
</footer>
</body>
</html>
