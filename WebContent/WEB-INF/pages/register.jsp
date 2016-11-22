<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Kayıt Ol</title>
</head>
<body>
<jsp:include page="../resources/template/header.jsp"></jsp:include>
<!-- Script validate.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/lib/jquery-validate.js"></script>

<div class="registerForm">
	<h2 align="center" >Kayıt Formu</h2>
	<form:form id="registerForm" action="${pageContext.request.contextPath}/register/save" modelAttribute="newUser" method="post">
		<div class="usernameInput">
			<form:input path="username" placeHolder="Kullanıcı Adı..."/>
		</div>
		<div class="emailInput">
			<form:input path="email" placeHolder="Email..."/>
		</div>
		<div class="passwordInput">
			<form:password path="password" placeholder="Şifre...!"/>
		</div>
		<div class="passwordInput">
			<input type="password" id="repassword" name="repassword" placeHolder="Tekrar Şifre..."/>
		</div>
		<div class="submitInput">
			<input type="submit"  value="Kayıt Ol"/>
		</div>
		<label class="result">${result}</label>
		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}"/>
		<input type="hidden" name="role.id" value="1" />
	</form:form>
</div>

<!-- Script register.js -->
<script type="text/javascript">
$(document).ready(function(){
	/*
	 * Bu sayfada komple kayıt olma sayfasının validate işlemini yapıyoruz
	 * isHasUserByAttribute(attributeName , value) ile veritabanından validate yapıyoruz yani
	 * bu özelliğe sahip bir kullanıcı var mı db ' de bakıyoruz.
	 * Örnek : isHasUserByAttribute(username , Qmer06) = sonucda aynı emaile sahip iki kullanıcıyı
	 * barındıramayız
	 * */
	
	$.validator.addMethod('strongPassword',function(value,element){
		return this.optional(element) 
		|| value.length >= 8 
		&& /\d/.test(value) 
		&& /[a-z]/i.test(value);
	},"This password is very easyy");
	
	{
		/*
		 * validateye eklediğimiz isHasUserByAttribute methodu ile databasede böyle bi kullanıcı varmı diye bakıyoruz
		 * element.name = columnName
		 * */
	
		 
		$.validator.addMethod('isHasUserByAttribute',function(value,element){
			var result = null;
			
			isHasUserByAttribute(element.name , value).done(function(response){
				// responsenin tersini almamızın sebebi : böyle bi kullanıcı varsa true döner
				result = !response.isHasUser;
			});
			
			return result;
		},"*Bu {0} zaten kullanılıyor.");
	}
	
	
	
		$("#registerForm").validate({
			rules : {
				username : {
					required : true , 
					maxlength: 15 ,
					minlength : 4 ,
					isHasUserByAttribute : true
				
				},
				email : {
					required : true,
					email : true , 
					isHasUserByAttribute : true
				},
				password : {
					required : true,
					strongPassword : true ,
				},
				repassword : {
					equalTo : "#password"
				}
			} , 
			messages : {
				username : {
					required : "*Bu alan gereklidir.",
					maxlength : "*Kullanıcı adınız minimum 15 olabilir",
					minlength : "*Kullanıcı adınız en az 4 olabilir" ,
					isHasUserByAttribute : "*Bu kullanıcı adına sahip kullanıcı zaten var."
				},
				email : {
					required : "*Bu alan gereklidir.",
					email : "*Emailiniz geçersiz.",
					isHasUserByAttribute : "*Bu emaile sahip kullanıcı zaten var."
				},
				password : {
					required : "*Bu alan gereklidir.",
					strongPassword : "*Şifreniz çok zayıf."
				}
			}
		});
		
		{
			/*
			 * Veritabanında bu özelliklere sahip bir kullanıcı varmı diye kontrol ediyor
			 * columnName : email
			 * */
			function isHasUserByAttribute(columnName , value){
				return $.ajax({
					type : "get",
					url : "/MemorizingWord/register/validate",
					async: false,
					data : { columnName : columnName , value : value  },
				});
			}
		}
		
});

</script>

<jsp:include page="../resources/template/footer.jsp"></jsp:include>
</body>
</html>