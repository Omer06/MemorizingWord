$(document).ready(function(){
	
	
	{
		/*
		 * Kullanıcının <select> ile seçtiği seçeneğin
		 * temsil ettiği divi ona gösteriyoruz
		 * select.value = div.class
		 * */
		$("#titleOptions").change(function(){
			var selectedOption = $(this).val();
			$(".titleOptionsContent div").not("." + selectedOption).slideUp();
			$("." + selectedOption).slideDown();
			if(selectedOption == "unSelected") { $(".titleOptionsContent div").slideUp(); }
		});
	}
	
	{
		/*
		 * Seçiçideki her hangi bir form submit olarak tetiklendiğinde
		 * İçerisindeki inputları kontroll ediyoruz
		 * */
		$("#deleteForm , #renameForm , #createForm").submit(function(e){
			if($(this).find(":text").val() == ""){
				e.preventDefault();
				$(this).find(":text").animate({
					borderColor : "red",
				},300);
			}
			
			if($(this).find("select").val() == "unSelected"){
				e.preventDefault();
				$(this).find("select").animate({
					borderColor : "red",
				},300);
			}
		});
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	{
		/*
		 * Kullanıcının seçtiği titleyi veritabanından getTheWordListOfTheTitle(titleId)
		 * ile çekip uygun bir şekilde kullanıcıya gösteriyoruz.
		 * */
		$("#titleList").change(function(){
			
			if($("#titleList").val() == "unSelected") { $(".wordListField").slideUp(); }
			else{
				$(".wordListField #wordList").html("");
				//Gelen kelime listesini ekrana basıyoruz
				getTheWordListOfTheTitle($(this).val()).done(function(response){
					for(var i=0; i<response.wordList.length; i++){
						$(".wordListField #wordList").append("<div class='wordRow'><input id='wordId' type='checkbox' name='wordId' value='"+response.wordList[i].id+"'></input><label id='name'>"+response.wordList[i].name+"</label><label id='meaning'>"+response.wordList[i].meaning+"</label></div>");
					}
					//Kelime listesi doluysa ekrana butonları ekliyoruz
					if(response.wordList.length > 0 ){
						$(".wordListField #wordList").append("<input type='hidden' name='_csrf' value='2bc633cf-e73e-4c90-bf2e-39463436e23d'>");
						$(".wordListField #wordList").append("<input type='submit' value='Sil' />");
						$("#info").html("-" + response.result);
						$(".wordListField").slideDown();
						return null
					}
					$(".wordListField").slideUp(); // boşsa ekranı siliyoruz
					$("#info").html("*Seçtiğiniz başlıkda hiç kelime yok.");
				});
			}
			
		});
		
		function getTheWordListOfTheTitle(titleId){
			return $.ajax({
				type : "get",
				url : "/MemorizingWord/options/gettitle",
				data : { titleId : titleId},
			});
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	{
		/*
		 * Form submit olunca herhangi bir checkbox seçilmişse yani
		 * Kullanıcı silmek üzere bir kelime seçmişse bunu servere gönderiyoruz.
		 * */
		$("#wordList").submit(function(e){
			return hasSelectCheckbox();
		});
		
		function hasSelectCheckbox(){
			var hasSelectCheckbox = false;
			$(":checkbox").each(function(){
				if($(this).is(":checked")){
					hasSelectCheckbox =  true;
				}
			});
			return hasSelectCheckbox;
		}
	}
	
		
	
});