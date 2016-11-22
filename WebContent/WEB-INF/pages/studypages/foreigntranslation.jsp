<%@page import="com.entities.Title"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
   	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Yabancı Dile Çevirme</title>
</head>
<body>
<jsp:include page="../../resources/template/header.jsp"></jsp:include>

<div class="foreignTranslationWrapper">
	<h1 align="center" style="margin: 40px 0 0 0; color: #3B3780;">YABANCI DİLE ÇEVİRME</h1>
	<div class="foreignTranslation">
		<form id="foreignTranslation" action="${pageContext.request.contextPath}/result/controll" method="post" >
			<div class="firstTitle">ANLAMI</div>
			<div class="arrow"></div>
			<div class="secondTitle">KELİME</div>
			<!-- Başlıkdaki kelimeleri ve taminini almak için guess leri ekrana basıyoruz -->
			<c:forEach items="${title.words}" var="word">
				<div class="meaning"> <label >${word.meaning} </label></div>
				<div class="arrow">= ></div>
				<div class="guess"><input type="text" name="${word.guess}" />
					<label class="${word.meaning}"  style="display: none">${word.name}</label>
				</div>
			</c:forEach>
			<!-- Aşşadaki alanda kullanıcının başlığındaki kelimeleri kontrol ediyoruz , yoksa bilgi veriyoruz. -->
			<c:choose>
				<c:when test="${fn:length(title.words) > 0 }">
					<input type="submit"  value="Cevapla"/>
				</c:when>
				<c:otherwise>
					<div class="emptyTitleError">Başlığınızda hiç kelime yok.<br/>Yüklemek için <a href="${pageContext.request.contextPath}/title/form">Buraya</a> tıklayınız.</div>
				</c:otherwise>
			</c:choose>
			<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>
</div>

<!-- Script responsiveVoice.js - texttospeech -->
<script src='${pageContext.request.contextPath}/resources/js/lib/responsivevoice.js'></script>

<!-- Script common-foreign-local-translation.js  -->
<script type="text/javascript">

$(document).ready(function(){
	{
		//formu validate ediyoruz.!
		$("form").submit(function(){
			formSubmit = true;
			var submitResult = true;
			$(":text").each(function(){
				if(isHasMistakeGuess($(this)) == true){
					submitResult = false;
				}
			});
			return submitResult;
		});
	}
	
	{
		// form submit edildiyse kullanıcı tuşa bastıkça validate isHasMistakeGuess çalışacak
		var formSubmit = false;
		$(":text").keyup(function(){
			if(formSubmit){
				isHasMistakeGuess($(this));
			}
		});
	}
	
	{
		//kullanıcının verdiği cevapları konroll ediyor.
		function isHasMistakeGuess(inputGuess) {
			
			var hasMistake = false;
			var guess = inputGuess.val().toLowerCase();
			var word = inputGuess.next().html().toLowerCase();
			
			if(guess == word) {
				inputGuess.animate({backgroundColor : "#5CFF70"},200);
			}
			else {
				hasMistake = true;
				inputGuess.animate({backgroundColor : "#FF5C5C"},200);
			}
			
			return hasMistake;
		}
	}
	
	{
		//sesli yanıt sistemi(ingilizce kelimeyi okuma)
		$(".meaning label").click(function(){
			var language = $(".language").html();
	 		var word = $("." + $(this).html()).html();
	 		try{
	 			if(responsiveVoice.voiceSupport()) {responsiveVoice.speak(word,language); }
	 			else { alert("Sesli kelime okuma sistemi çalışmamaktadır.\n Lütfen bize bildin."); }
	 		}catch(err){alert("Sesli kelime okuma sistemi çalışmamaktadır.\nİnternetinizi kontrol edin.\nSorun düzelmezse bize bildin.")}
		});
	}
});

</script>
<jsp:include page="../../resources/template/footer.jsp"></jsp:include>
</body>
</html>