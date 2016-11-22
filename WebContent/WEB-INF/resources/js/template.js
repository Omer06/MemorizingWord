$(document).ready(function(){
	{
		/*
		 * Bu blok içerisinde menulerimizin davranışlarını ayarlıyoruz.
		 * */
		$(".menu ul li a").mouseover(function(){
			$(this).animate({
				backgroundColor : "#FF7300",
				height : 30 ,
			    color : "#2F6062"
			},180);
		}).mouseout(function(){
			$(this).animate({
				backgroundColor : "#CF9903",
				height : 23 ,
			    color : "#000"
			},80);
		});
	}
	
	{
		/*
		 * Profil linkine tıkladığımızda altta gizlediğimiz alt menüler açılıp kapanacak.
		 * */
		$("#profileMainLink").click(function(e){
			e.preventDefault();
			$("#profileSubList").slideToggle(300);
		});
		
	}
	
	
	
	{
		/*
		 * Bu blok içerisinde inputlarımızın davranışlarını ayarlıyoruz
		 * */
		var focusedInput = null;
		$("input[type='text'] , input[type='password'] , textArea").focusin(function(){
				
				focusedInput = $(this);
				$(this).animate({
					backgroundColor : "#fff",
					borderColor : "#D1ED35",
					
				},300);
		}).focusout(function(){
				focusedInput = null;
				$(this).animate({
					backgroundColor : "#F3F3F3",
					borderColor : "#d3d3d3"
				},300);
		}).mouseover(function(){
			if(!($(this).is(focusedInput))){
					$(this).animate({
					borderColor : "#BBDC82",
				},300);
			}
		}).mouseout(function(){
			if(!($(this).is(focusedInput))){
				$(this).animate({
					borderColor : "#d3d3d3"
				},300);
			}
		});
	}

});