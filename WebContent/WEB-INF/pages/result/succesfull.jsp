<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Başarılı</title>
</head>
<body>
	<jsp:include page="../../resources/template/header.jsp"></jsp:include>

	<div class="successfullMessage">
		<h3 align="center" style="color: #E4D800; margin-bottom: 20px">Başarılı</h3>
		Tebrikler bütün kelimeleri bildiniz. "Seçeneklerden" bölümünden yeni
		başlık ekleyebilir , Eklediğiniz bu başlıkları "Başlık" menüsünden
		başlığınızı doldurabilirsiniz. <br />
		<br /> <b style="font-size: 16px">Çalışmaya Devam etmek için <a
			href="${pageContext.request.contextPath}/study/options">tıklayınız</a></b>.
	</div>

	<jsp:include page="../../resources/template/footer.jsp"></jsp:include>
</body>
</html>