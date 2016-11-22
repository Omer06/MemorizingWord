<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sayfa bulunamadı</title>
</head>
<body>
<jsp:include page="../../resources/template/header.jsp"></jsp:include>
<h3 align="center" style="margin-top: 100px;">Sayfa bulunamadı.</h3>
<div align="center" style="width:400px; margin: 30px auto; text-align: justify; font: 18px serif;">
Aradığınız sayfa bulunamadı. Eğer daha önce girdiğiniz ve şimdi ulaşamadığınız sayfa ise bize <a href="${pageContext.request.contextPath}/desire/form">bildirin.</a><br/>
Anasayfaya gitmek için <a href="${pageContext.request.contextPath}/">tıklayın.</a>
</div>
<jsp:include page="../../resources/template/footer.jsp"></jsp:include>
</body>
</html>