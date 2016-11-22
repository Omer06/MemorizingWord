<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Arzu/Talep</title>
</head>
<body>
<jsp:include page="../resources/template/header.jsp"></jsp:include>
<!-- Script validate.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/lib/jquery-validate.js"></script>
<div class="desireFormWrapper">
	<h3 align="center">Arzu/Talep Formu</h3>
	<div class="desireForm">
		<form:form id="desireForm" modelAttribute="desireObj" action="${pageContext.request.contextPath}/desire/save">
			<div class="nameField">
				<form:input id="name" path="name" placeHolder="name"/>
			</div>
			<div class="emailField">
				<form:input id="email" path="email" placeHolder="email"/>
			</div>
			<div class="desireField">
			<form:textarea path="desire" placeHolder="Arzu/Talep"/>
			</div>
			<input type="submit" value="Gönder" />
		</form:form>
	</div>
	<div><label id="result">${result}</label></div>
</div>

<!-- Script desire.css -->
<script type="text/javascript">

$(document).ready(function(){
	
	$("#desireForm").validate({
		rules : {
			name : {
				required : true
			},
			email : {
				required : true,
				email : true
			},
			desire : {
				required : true
			}
		},
		messages : {
			name : {
				required : "*İsim alanı zorunlu."
			},
			email : {
				required : "*Email alanı zorunlu.",
				email : "*Geçersiz E-posta."
			},
			desire : {
				required : "*Lütfen talebinizi yazınız."
			}
		}
	});
	
});


</script>
<jsp:include page="../resources/template/footer.jsp"></jsp:include>
</body>
</html>