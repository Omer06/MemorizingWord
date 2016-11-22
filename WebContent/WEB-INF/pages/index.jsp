<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ana Sayfa</title>
</head>
<body>
<jsp:include page="../resources/template/header.jsp"></jsp:include>

<h2 align="center" style="color: #002FB0; font-family:arial; margin-top: 75px;"><label>Sistemin Amacı && Kullanımı</label></h2>
<!-- İndex sayfasında tanıtım ve kullanım videoları mevcuttur. -->
<div class="informUsersWrapper">
	<div id="extendWindowsTitle"  class="ourAimTitle" style="text-align: center;"><b>Sistemin Amacı</b></div>
	<div id="extendWindows" class="ourAim">

	</div>
	
	
	<div id="extendWindowsTitle" class="userGuideTitle" style="text-align: center;"><b>Sistemin Kullanımı</b></div>
	<div id="extendWindows" class="userGuide">
		
	</div>
</div>


<jsp:include page="../resources/template/footer.jsp"></jsp:include>
</body>
</html>