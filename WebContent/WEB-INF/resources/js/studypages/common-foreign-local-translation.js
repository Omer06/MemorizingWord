$(document).ready(function(){
	{
		//formu validate ediyoruz.!
		$("form").submit(function(){
			formSubmit = true;
			var submitResult = true;
			$(":text").each(function(){
				if(isHasMistakeGuess($(this)) == true){
					submitResult = false;
				}
			});
			return submitResult;
		});
	}
	
	{
		// form submit edildiyse kullanıcı tuşa bastıkça validate isHasMistakeGuess çalışacak
		var formSubmit = false;
		$(":text").keyup(function(){
			if(formSubmit){
				isHasMistakeGuess($(this));
			}
		});
	}
	
	{
		//kullanıcının verdiği cevapları konroll ediyor.
		function isHasMistakeGuess(inputGuess) {
			
			var hasMistake = false;
			var guess = inputGuess.val().toLowerCase();
			var word = inputGuess.next().val().toLowerCase();
			
			if(guess == word) {
				inputGuess.animate({backgroundColor : "#5CFF70"},200);
			}
			else {
				hasMistake = true;
				inputGuess.animate({backgroundColor : "#FF5C5C"},200);
			}
			
			return hasMistake;
		}
	}
});