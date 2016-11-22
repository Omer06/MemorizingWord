$(document).ready(function(){
	
	{
		 /*
		  * Meaning divinin içinde bulunan labellerin metin değerleri , aynı wordün hatırlatma divinin class
		  * adı eşittir yani.(meaning.label.text == .remindingList.remindingDiv.class )
		  * */
		$(".meaning label").click(function(){
			var remindingClassName = $(this).text().replace(' ' , '_');
			var remindingDiv = $(".remindingList ." + remindingClassName);
			if(remindingDiv.text() ==  "")
			{
				remindingDiv.text(remindingDiv.attr("remindingVal"));
			}
			else {
				remindingDiv.text("");
			}
			
		});
	}
	
	{
		//sesli yanıt sistemi(ingilizce kelimeyi okuma)
		$(".name label").click(function(){
				$.ajax({
					type: "GET",
					url : "/MemorizingWord/speechtotext/speech",
					async: false,
					data : { word : $(this).text()},
				});
		});
	}
});