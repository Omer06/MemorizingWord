<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Seçenekler</title>
</head>
<body>
<jsp:include page="../resources/template/header.jsp"></jsp:include>

<div id="extendWindowsTitle" class="titleOptionsTitle">Başlık</div>
<div id="extendWindows" class="titleOptionsWrapper">
	<div class="titleOptionsHeader">
		<div class="titleOptionsField">
			Seçenekler : 
			<select id="titleOptions">
				<option value="unSelected">Birini seçiniz.</option>
				<option value="rename">Yeniden isimlendir</option>
				<option value="delete">Sil</option>
				<option value="create">Oluştur</option>
			</select>
		</div>
	</div>
	<div class="titleOptionsContent">
		<div class="delete">
			<form id="deleteForm" action="${pageContext.request.contextPath}/options/delete" method="post">
				<select name="titleId">
					<option value="unSelected">Başlık</option>
					<c:forEach items="${titleList}" var="title">
						<option value="${title.id}">${title.name}</option>
					</c:forEach>
				</select><br/>
				<input type="submit" value="Sil"><br/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
		<div class="rename">
			<form id="renameForm" action="${pageContext.request.contextPath}/options/rename" method="post">
				<select name="titleId">
					<option value="unSelected">Başlık</option>
					<c:forEach items="${titleList}" var="title">
						<option value="${title.id}">${title.name}</option>
					</c:forEach>
				</select>
				<input type="text" name="newName" placeHolder="Yeni isim">
				<input type="submit" value="Yeniden isimlendir">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
		<div id="createForm" class="create">
			<form action="${pageContext.request.contextPath}/options/save" method="post">
				<input type="text" name="name" placeHolder="Başlık ismi"/>
				<select id="language" name="language" title="Başlıkdaki kelimelerin dilini seçiniz."><option value="unSelected">Başlık dili</option><option value="UK English Female">UK English Female</option><option value="UK English Male">UK English Male</option><option value="US English Female">US English Female</option><option value="US English Male">US English Male</option><option value="Arabic Male">Arabic Male</option><option value="Arabic Female">Arabic Female</option><option value="Armenian Male">Armenian Male</option><option value="Australian Female">Australian Female</option><option value="Brazilian Portuguese Female">Brazilian Portuguese Female</option><option value="Chinese Female">Chinese Female</option><option value="Chinese (Hong Kong) Female">Chinese (Hong Kong) Female</option><option value="Chinese Taiwan Female">Chinese Taiwan Female</option><option value="Czech Female">Czech Female</option><option value="Danish Female">Danish Female</option><option value="Deutsch Female">Deutsch Female</option><option value="Dutch Female">Dutch Female</option><option value="Finnish Female">Finnish Female</option><option value="French Female">French Female</option><option value="Greek Female">Greek Female</option><option value="Hatian Creole Female">Hatian Creole Female</option><option value="Hindi Female">Hindi Female</option><option value="Hungarian Female">Hungarian Female</option><option value="Indonesian Female">Indonesian Female</option><option value="Italian Female">Italian Female</option><option value="Japanese Female">Japanese Female</option><option value="Korean Female">Korean Female</option><option value="Latin Female">Latin Female</option><option value="Norwegian Female">Norwegian Female</option><option value="Polish Female">Polish Female</option><option value="Portuguese Female">Portuguese Female</option><option value="Romanian Male">Romanian Male</option><option value="Russian Female">Russian Female</option><option value="Slovak Female">Slovak Female</option><option value="Spanish Female">Spanish Female</option><option value="Spanish Latin American Female">Spanish Latin American Female</option><option value="Swedish Female">Swedish Female</option><option value="Tamil Male">Tamil Male</option><option value="Thai Female">Thai Female</option><option value="Turkish Female">Turkish Female</option><option value="Afrikaans Male">Afrikaans Male</option><option value="Albanian Male">Albanian Male</option><option value="Bosnian Male">Bosnian Male</option><option value="Catalan Male">Catalan Male</option><option value="Croatian Male">Croatian Male</option><option value="Czech Male">Czech Male</option><option value="Danish Male">Danish Male</option><option value="Esperanto Male">Esperanto Male</option><option value="Finnish Male">Finnish Male</option><option value="Greek Male">Greek Male</option><option value="Hungarian Male">Hungarian Male</option><option value="Icelandic Male">Icelandic Male</option><option value="Latin Male">Latin Male</option><option value="Latvian Male">Latvian Male</option><option value="Macedonian Male">Macedonian Male</option><option value="Moldavian Male">Moldavian Male</option><option value="Montenegrin Male">Montenegrin Male</option><option value="Norwegian Male">Norwegian Male</option><option value="Serbian Male">Serbian Male</option><option value="Serbo-Croatian Male">Serbo-Croatian Male</option><option value="Slovak Male">Slovak Male</option><option value="Swahili Male">Swahili Male</option><option value="Swedish Male">Swedish Male</option><option value="Vietnamese Male">Vietnamese Male</option><option value="Welsh Male">Welsh Male</option><option value="Fallback UK Female">Fallback UK Female</option></select>
				<input type="hidden" name="user.username" value="${pageContext.request.userPrincipal.name}">
				<input type="submit" value="Oluştur">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
	</div>
</div>

<div class="operationResult">
<span><b>İşlem sonucu : </b></span>
<span>${result} 
<c:if test="${empty result }">
	Şuan herhangi bir işlem yok.
</c:if></span>
</div>

<div id="extendWindowsTitle" class="wordOptionsTitle">Kelime</div>
<div class="wordOptionsWrapper" id="extendWindows">
	<div class="wordOptionsHeader">
		<div class="titleListField">
			Başlık : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select id="titleList">
				<option value="unSelected">Birini seçiniz.</option>
				<c:forEach items="${titleList}" var="title">
					<option value="${title.id}">${title.name}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="wordOptionsContent">
		<div class="wordListField">
			<form id="wordList" action="${pageContext.request.contextPath}/options/deleteword">
			<!-- Burayı jquery ile kullanıcı hangi başlığı seçtiyse onun kelimeleri silinmek üzere yerleştiliyor.! -->
			</form>
		</div>
		<label id="info"></label>
	</div>
</div>

<!-- Script options.js -->
<script type="text/javascript">

$(document).ready(function(){
	{
		/*
		 * Kullanıcının <select> ile seçtiği seçeneğin
		 * temsil ettiği divi ona gösteriyoruz
		 * select.value = div.class
		 * */
		$("#titleOptions").change(function(){
			var selectedOption = $(this).val();
			$(".titleOptionsContent div").not("." + selectedOption).slideUp();
			$("." + selectedOption).slideDown();
			if(selectedOption == "unSelected") { $(".titleOptionsContent div").slideUp(); }
		});
	}
	
	{
		/*
		 * Seçiçideki her hangi bir form submit olarak tetiklendiğinde
		 * İçerisindeki inputları kontroll ediyoruz
		 * */
		$("#deleteForm , #renameForm , #createForm").submit(function(e){
			if($(this).find(":text").val() == ""){
				e.preventDefault();
				$(this).find(":text").animate({
					borderColor : "red",
				},300);
			}
			
			if($(this).find("select").val() == "unSelected"){
				e.preventDefault();
				$(this).find("select").animate({
					borderColor : "red",
				},300);
			}
		});
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	{
		/*
		 * Kullanıcının seçtiği titleyi veritabanından getTheWordListOfTheTitle(titleId)
		 * ile çekip uygun bir şekilde kullanıcıya gösteriyoruz.
		 * */
		$("#titleList").change(function(){
			
			if($("#titleList").val() == "unSelected") { $(".wordListField").slideUp(); }
			else{
				$(".wordListField #wordList").html("");
				//Gelen kelime listesini ekrana basıyoruz
				getTheWordListOfTheTitle($(this).val()).done(function(response){
					for(var i=0; i<response.wordList.length; i++){
						$(".wordListField #wordList").append("<div class='wordRow'><input id='wordId' type='checkbox' name='wordId' value='"+response.wordList[i].id+"'></input><label id='name'>"+response.wordList[i].name+"</label><label id='meaning'>"+response.wordList[i].meaning+"</label></div>");
					}
					//Kelime listesi doluysa ekrana butonları ekliyoruz
					if(response.wordList.length > 0 ){
						$(".wordListField #wordList").append("<input type='hidden' name='_csrf' value='2bc633cf-e73e-4c90-bf2e-39463436e23d'>");
						$(".wordListField #wordList").append("<input type='submit' value='Sil' />");
						$("#info").html("-" + response.result);
						$(".wordListField").slideDown();
						return null
					}
					$(".wordListField").slideUp(); // boşsa ekranı siliyoruz
					$("#info").html("*Seçtiğiniz başlıkda hiç kelime yok.");
				});
			}
			
		});
		
		function getTheWordListOfTheTitle(titleId){
			return $.ajax({
				type : "get",
				url : "/MemorizingWord/options/gettitle",
				data : { titleId : titleId},
			});
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	{
		/*
		 * Form submit olunca herhangi bir checkbox seçilmişse yani
		 * Kullanıcı silmek üzere bir kelime seçmişse bunu servere gönderiyoruz.
		 * */
		$("#wordList").submit(function(e){
			return hasSelectCheckbox();
		});
		
		function hasSelectCheckbox(){
			var hasSelectCheckbox = false;
			$(":checkbox").each(function(){
				if($(this).is(":checked")){
					hasSelectCheckbox =  true;
				}
			});
			return hasSelectCheckbox;
		}
	}
	
		
	
});

</script>
<jsp:include page="../resources/template/footer.jsp"></jsp:include>
</body>
</html>