<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Şifre Değiştirme</title>
</head>
<body>
<jsp:include page="../resources/template/header.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/lib/jquery-validate.js"></script>
	<div class="changePasswordField">
		<h2 align="center" style="margin-bottom: 20px">Şifre Değiştirme</h2>
		<form id="changePassword" action="${pageContext.request.contextPath}/changepassword/change" method="post">
			<input type="password" id="password" name="password" placeHolder="Şuanki şifre">
			<input type="password" id="newPassword" name="newPassword" placeHolder="Yeni şifre">
			<input type="password" id="reNewPassword" name="reNewPassword" placeHolder="Tekrar yeni şifre">
			<input type="submit" value="Değiştir" >
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
		<label id="result">${result}</label>
	</div>
	<!-- Script changepassword.js -->
	<script type="text/javascript">
	
	$(document).ready(function(){
		$.validator.addMethod('strongPassword',function(value,element){
			return this.optional(element) 
			|| value.length >= 8 
			&& /\d/.test(value) 
			&& /[a-z]/i.test(value);
		},"This password is very easyy");
		
		$("#changePassword").validate({
			rules : {
				password : {
					required : true
					
				} , 
				newPassword : {
					required : true ,
					strongPassword : true
				} , 
				reNewPassword : {
					equalTo : "#newPassword"
				}
			} , 
			messages : {
				password : { required : "*Bu alan gereklidir." } , 
				newPassword : {
					required : "*Bu alan gereklidir." , 
					strongPassword : "*Şifreniz çok zayıf."
				},
				reNewPassword : {
					equalTo : "Şifreleriniz uyuşmuyor."
				}
			}
		});
		
	});
	
	</script>
<jsp:include page="../resources/template/footer.jsp"></jsp:include>
</body>
</html>