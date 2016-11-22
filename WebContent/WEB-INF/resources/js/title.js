$(document).ready(function(){
	
	{
		/*
		 *Bu blok içerisinde kullanıcıya başlıklarının içerisine yeni kelimeler ekleyeceği 
		 *inputları ayarlıyoruz. Başta kullanıcıya 6 input veriyoruz . Bunu 20 ye kadar
		 *yükselte biliyor. 
		 * */
		$("#addWordField").click(function(){
			var totalNewWord = $(".newWord").length;
			if(totalNewWord < 20) {
				$(".newWordListField").append("<div class='newWord'><input id='name' name='words["+ (totalNewWord) +"].name' type='text' value=''/> <=> <input id='meaning' name='words["+ (totalNewWord) +"].meaning' type='text' value=''/> <=> <input id='reminding' name='words["+ (totalNewWord) +"].reminding' type='text' value=''/></div>");
				$(".newWord").last().hide().fadeIn(300);
			}else {
				alert("En fazla 20 alan ekleyebilirsiniz.")
			}
			
			return false;
		});
		
		$("#removeWordField").click(function(){
			$(".newWordListField .newWord").last().remove();
			return false;
		});
	}
	
	
	
		{
			/*
			 * Yeni başlıklar update etmek üzere gönderilirken validate işlemi yapıyoruz
			 * boş geçilmiş mi diye ?
			 * */
			$("#titleForm").submit(function(){
				var submitResult = true;
				
				$(".newWord #name , .newWord #meaning").each(function(){
					if($(this).val() == "") {
						$(this).animate({backgroundColor : "#FF5C5C"},200);
						submitResult = false;
					}
					else {
						$(this).animate({backgroundColor : "#5CFF70"},200);
					}
				});
				return submitResult;
			});
		}
		
		{
			/*
			 * Yeni başlık ekleme divini görünür yapıyoruz.
			 * */
			$("#newTitleAdding").click(function(){
				
				$(".newTitle").toggle();
				return false;
			});
		}
		
		{
			$(".newTitle #name").keyup(function(){
				if($(this).val().length > 0) {
					$("#newTitleSubmit").fadeIn(300);
				}
				else
				{
					$("#newTitleSubmit").fadeOut(200);
				}
					
			});
		}
});