<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Çalışma Seçenekleri</title>
</head>
<body>
<jsp:include page="../resources/template/header.jsp"></jsp:include>
	<div id="extendWindows" class="studyOptions">
		<h3 align="center" style="font-size: 21px;">Çalışma Seçenekleri</h3>
		<form id="options" action="${pageContext.request.contextPath}/study/redirecter" method="post">
			<div class="titleSelection">
				<div class="fieldTitle">Başlık</div>
				<div class="titleList">
					<select name="titleId">
						<option value="">Başlık Seçiniz.</option>
						<c:forEach items="${titleMap}" var="title">
							<option value="${title.value}">${title.key}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="styleSelection">
				<div class="fieldTitle">Stil</div>
				<div class="styleList">
					<select name="pageName">
					<option value="">Stil Seçiniz.</option>
						<c:forEach items="${styleMap}" var="style">
							<option value="${style.value}">${style.key}</option>
						</c:forEach>
					</select>
				</div>
			</div>	
			<input id="submit" type="submit" value="BAŞLA" />
			<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}"/>
		</form>
	</div>

	<!-- Script studyoptions.js -->
	<script type="text/javascript">
	$(document).ready(function(){
		{
			
			/*
			 * Çalışma sayfasına göndermeden önce kullanıcının 
			 * çalışmak istediği seçenekleri seçmiş mi ? 
			 * Bir validate yapıyoruz
			 * */
			$("#options").submit(function(){
				var submitControll = true;
				$("select").each(function(){
					if($(this).val() == "") {
						$(this).css("border-color" ,"#FF5C5C");
						submitControll = false;
					}
					else {
						$(this).css("border-color" ,"#5CFF70");
					}
				});
				return submitControll;
			});
			
		}
	});
	
	</script>
<jsp:include page="../resources/template/footer.jsp"></jsp:include>
</body>
</html>