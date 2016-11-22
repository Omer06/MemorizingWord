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