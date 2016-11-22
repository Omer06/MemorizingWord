<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Giriş</title>
</head>
<body>
<jsp:include page="../resources/template/header.jsp"></jsp:include>
<div class="loginForm">
	<h2 align="center">Oturum Aç</h2>
	<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="usernameInput">
				<input type="text" name="username" placeHolder="Kullanıcı Adı..." required  title="Kullanıcı adınızı unuttunuz." focusinBackgroundColor=""  focusoutBackgroundColor = ""  />
			</div>
			<div class="passwordInput">
				<input type="password" name="password" placeHolder="Şifre..." required title="Şifrenizi unuttunuz."/>
			</div>
			<div class="rememberMeInput">
				<label>
					Beni Hatırla : <input type="checkbox" name="rememberMe" checked="checked"/>
				</label>
			</div>
			<div class="submitInput">
				<input type="submit" value="Giriş Yap"/>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="error" style="margin: -20px 0 0 37px; color: red">
				<label class="error">${result}</label>
			</div>
	</form>
</div>
<div class="links">
		<ul>
			<li><a href="${pageContext.request.contextPath}/register/form">Kayıt ol</a><br/></li>
			<li><a href="${pageContext.request.contextPath}/forgotpassword/form">Şifremi unuttum</a></li>
		</ul>
	</div>
<jsp:include page="../resources/template/footer.jsp"></jsp:include>
</body>
</html>