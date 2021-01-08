<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
	// Script to open and close sidebar
	function w3_open() {
		document.getElementById("mySidebar").style.display = "block";
	}

	function w3_close() {
		document.getElementById("mySidebar").style.display = "none";
	}
</script>

<!-- Top menu -->
<div class="w3-top">
	<div class="w3-white w3-xlarge" style="max-width: 1400px; margin: auto">
		<div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">☰</div>
		<div class="w3-center w3-padding-16">World of Words</div>
	</div>
</div>

<!-- Sidebar (hidden by default) -->
<nav
	class="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left"
	style="display: none; z-index: 2; width: 40%; min-width: 300px"
	id="mySidebar">
	<a href="javascript:void(0)" onclick="w3_close()"
		class="w3-bar-item w3-button">Close Menu</a> <a href="index.html"
		onclick="w3_close()" class="w3-bar-item w3-button">Home</a> 
		<a href="worldofwords?command=getWordList&userwordscommand=getUserWords"
		onclick="w3_close()" class="w3-bar-item w3-button">Find out Words</a>
		<a href="worldofwords?command=brainStorm&userwordscommand=getCurrentWords"
		onclick="w3_close()" class="w3-bar-item w3-button">Let's BrainStorm</a>
	<a href="about.jsp" onclick="w3_close()" class="w3-bar-item w3-button">About</a>
</nav>