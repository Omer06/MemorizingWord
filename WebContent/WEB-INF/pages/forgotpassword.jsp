<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Şifremi Unuttum</title>
</head>
<body>
<jsp:include page="../resources/template/header.jsp"></jsp:include>
	<div class="forgotPasswordField">
		<h2 align="center" style="margin-bottom: 20px">Şifremi Unuttum</h2>
		<form id="forgotPassword" action="${pageContext.request.contextPath}/forgotpassword/getpassword" method="post">
			<input type="text" name="username" placeHolder="Kullanıcı adı"  />
			<input type="text" name="email" placeHolder="Email"  />
			<input type="submit" value="Gönder" >
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div><br/><br/>
	<label id="result">${result}</label>
	
	<!-- Script forgotpassword.js -->
	<script type="text/javascript">
	$(document).ready(function(){
		$("#forgotPassword").submit(function(e){
			$("#forgotPassword input[type='text']").each(function(){
				if($(this).val().trim() == ""){
					$(this).animate({
						borderColor : "red"
					},200);
					e.preventDefault();
				}
				else {
					$(this).animate({
						borderColor : "#3CFF00"
					},200);
				}
			});
		});
	});
	</script>
<jsp:include page="../resources/template/footer.jsp"></jsp:include>
</body>
</html>