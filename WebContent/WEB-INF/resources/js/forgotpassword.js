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