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
