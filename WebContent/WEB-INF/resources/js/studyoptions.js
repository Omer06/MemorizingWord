$(document).ready(function(){
	{
		
		/*
		 * Çalışma sayfasına göndermeden önce kullanıcının 
		 * çalışmak istediği seçenekleri seçmiş mi ? 
		 * Bir validate yapıyoruz
		 * */
		$("#options").submit(function(){
			var submitControll = true;
			$("select").each(function(){
				if($(this).val() == "") {
					$(this).css("border-color" ,"#FF5C5C");
					submitControll = false;
				}
				else {
					$(this).css("border-color" ,"#5CFF70");
				}
			});
			return submitControll;
		});
		
	}
});