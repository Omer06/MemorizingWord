<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Başlık</title>
</head>
<body>
<jsp:include page="../resources/template/header.jsp"></jsp:include>
<div class="addingOptions">
<label><input type="radio" name="option" value="manuelFormWrapper" checked="checked">Manuel Ekleme</input></label>
<label><input type="radio" name="option" value="withFileFormWrapper">Dosya ile Ekleme (Önerilen)</input></label>
</div>
<div class="titleForm">
	<div class="manuelFormWrapper">
		<form:form id="manuelForm" method="post" action="${pageContext.request.contextPath}/title/update" modelAttribute="titleObj">
			<div class=theUserOfTheTitle">
				<input type="hidden" name="user.username" value="${pageContext.request.userPrincipal.name}" />
			</div>
			<div class="titleName">
					Başlık : 
					<form:select path="id"> 
						<c:forEach items="${titleList}" var="title">
							<form:option value="${title.id}">${title.name}</form:option>
						</c:forEach>
					</form:select>
			</div>
			<div class="adjustWordField">
				<h4 align="center" style="margin-left: 30px">Kelime alanı</h4>
				<h4 align="center" style="margin-left: 30px">Ekle/Sil</h4>
				<button id="addWordField" ></button>
				<button id="removeWordField"></button>
			</div>
			<div class="newWordListField">
			<div class="newWordListColumnTitle" align="center">
				<label>Kelime</label> <=> 
				<label>Anlamı</label> <=>
				<label>Hatırlatma</label>
			</div>
			<c:forEach begin="0" end="5" var="i">
				<div class="newWord">
					<form:input id ="name" path="words[${i}].name"/> <=> 
					<form:input id="meaning" path="words[${i}].meaning"/> <=> 
					<form:input id="reminding" path="words[${i}].reminding"/>
				</div>
			</c:forEach>
			</div>
			<input id="titleSubmit" type="submit" value="Güncelle" /> 
		</form:form>
	</div>
	<div class="withFileFormWrapper" style="display: none; float: left;">
		<form:form action="${pageContext.request.contextPath}/title/updatewithfile" 
			modelAttribute="titleObj" method="post" enctype="multipart/form-data">
			<div class="titleName">
					Başlık : 
					<select name="titleId"> 
						<c:forEach items="${titleList}" var="title">
							<option value="${title.id}">${title.name}</option>
						</c:forEach>
					</select>
			</div>
			<div class="filePathWrapper">
				<input type="file" id="file" name="file" accept=".txt" required  />
			</div>
			<div class="markerField">
				<input type="text" id="marker" name="marker" placeHolder="Ayıraç" required>
			</div>
			<input type="submit" value="Güncelle" style="float:left; margin: 11px 0 20px 330px;"/> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form:form>
	</div>
	<label  style="float: left; margin: 0 0 20px 10px;" class="result">${result}</label>
</div>

<!-- Script Title.js -->
<script type="text/javascript">
$(document).ready(function(){
	
	{
		/*
		 *Bu blok içerisinde kullanıcıya başlıklarının içerisine yeni kelimeler ekleyeceği 
		 *inputları ayarlıyoruz. Başta kullanıcıya 6 input veriyoruz . Bunu 20 ye kadar
		 *yükselte biliyor. 
		 * */
		$("#addWordField").click(function(){
			var totalNewWord = $(".newWord").length;
			if(totalNewWord < 20) {
				$(".newWordListField").append("<div class='newWord'><input id='name' name='words["+ (totalNewWord) +"].name' type='text' value=''/> <=> <input id='meaning' name='words["+ (totalNewWord) +"].meaning' type='text' value=''/> <=> <input id='reminding' name='words["+ (totalNewWord) +"].reminding' type='text' value=''/></div>");
				$(".newWord").last().hide().fadeIn(300);
			}else {
				alert("En fazla 20 alan ekleyebilirsiniz.")
			}
			
			return false;
		});
		
		$("#removeWordField").click(function(){
			$(".newWordListField .newWord").last().fadeOut(300 , function(){
				$(this).remove();
			});
			return false;
		});
	}
	
	
	
		{
			/*
			 * Yeni başlıklar update etmek üzere gönderilirken validate işlemi yapıyoruz
			 * boş geçilmiş mi diye ?
			 * */
			$("#manuelForm").submit(function(){
				var submitResult = true;
				
				$(".newWord #name , .newWord #meaning").each(function(){
					if($(this).val() == "") {
						$(this).animate({backgroundColor : "#FF5C5C"},200);
						submitResult = false;
					}
					else {
						$(this).animate({backgroundColor : "#5CFF70"},200);
					}
				});
				return submitResult;
			});
		}
		
		{
			/*
			 *Kullanıcı hangi seçeneği seçer ise o form kullanıcının karşısına çıkıyor
			 */
			$(":radio").click(function(){
				$(".titleForm > div").not("." + $(this).val()).slideUp();
				$(".titleForm ." + $(this).val()).slideDown();
			});
		}
		
});

</script>
<jsp:include page="../resources/template/footer.jsp"></jsp:include>
</body>
</html>