<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ezberleme</title>
</head>
<body>
<jsp:include page="../../resources/template/header.jsp"></jsp:include>

<div class="wordListWrapper">
	<h1 align="center" style="margin: 40px 0 0 0; color: #3B3780;">Ezberleme</h1>

	<div class="wordList">
		<div class="firstTitle">KELİME</div>
		<div class="arrow"></div>
		<div class="secondTitle">ANLAMI</div>
			<!-- Başlıkdaki kelimeleri ekrana basıyoruz -->
		<c:forEach items="${title.words}" var="word">
			<div class="name"><label id="englishWord">${word.name}</label></div>
			<div class="arrow"> < = ></div>
			<div class="meaning"><label>${word.meaning}</label></div>
		</c:forEach>
			<!-- Burda başlıkta kelime varmı kontroll ediyoruz , yoksa bilgi veriyoruz. -->
		<c:if test="${fn:length(title.words) == 0 }">
			<div class="emptyTitleError" align="center">Başlığınızda hiç kelime yok.<br/>Yüklemek için <a href="${pageContext.request.contextPath}/title/form">Buraya</a> tıklayınız.</div>
		</c:if>
	</div>
	<div class="remindingList">
			<!-- Başlıkdaki hatırlatmaları ekrana basıyoruz -->
		<c:forEach items="${title.words}" var="word">
			<c:if test="${not empty word.reminding }">
				<div remindingVal="${word.reminding}" class="${fn:replace(word.meaning,' ','_')}"></div>	
			</c:if>
			<c:if test="${empty word.reminding }">
				<div remindingVal="hatırlatma yok." class="${fn:replace(word.meaning,' ','_')}"></div>	
			</c:if>
		</c:forEach>
		<div class="language" style="display: none;">${title.language}</div>
	</div>
</div>
<!-- Script responsiveVoice.js - texttospeech -->
<script src='${pageContext.request.contextPath}/resources/js/lib/responsivevoice.js'></script>

<!-- Script memorize.js -->
<script type="text/javascript">
$(document).ready(function(){
	
	{
		 /*
		  * Meaning divinin içinde bulunan labellerin metin değerleri , aynı wordün hatırlatma divinin class
		  * adı eşittir yani.(meaning.label.text == .remindingList.remindingDiv.class )
		  * */
		$(".meaning label").click(function(){
			var remindingClassName = $(this).text().replace(' ' , '_');
			var remindingDiv = $(".remindingList ." + remindingClassName);
			if(remindingDiv.text() ==  "")
			{
				remindingDiv.text(remindingDiv.attr("remindingVal"));
			}
			else {
				remindingDiv.text("");
			}
			
		});
	}
	
	{
		//sesli yanıt sistemi(ingilizce kelimeyi okuma)
		$(".name label").click(function(){
			var language = $(".language").html();
	 		var word = $(this).text();
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