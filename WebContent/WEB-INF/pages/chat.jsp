<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sohped</title>
</head>
<body>
<jsp:include page="../resources/template/header.jsp"></jsp:include>
<div class="infoText" style="margin: 10% 0 0 19%; color: #396769; font: bold 17px serif;" >
Sizler için hazırlanmış bu alanda , fikir alışverişi sağlayabilirsiniz.
</div>
<div class="room">
	<div class="roomTitle">
		<div class="chatTitle">Genel</div>
	</div>
	<div class="chatFieldWrapper">
			<div class="chatField" id="Genel"></div>
	</div>
	<div class="users">
		<ul>
		</ul>
	</div>
	<div class="channels">
		<ul>
			<li>
				<a href="#">Genel</a>
			</li>
		</ul>
	</div>
	<div class="messageFormWrapper">
		<textarea class="textArea" id="messageBox"></textarea>
		<button class="button" id="sendMessage">Gönder</button>
	</div>
</div>
<div class="username" style="display: none">${pageContext.request.userPrincipal.name}</div>


<!-- Script chat.js -->
<script type="text/javascript">

$(document).ready(function(){
	var socket = new WebSocket("ws://localhost:8080/MemorizingWord/chatserver");
	var $chatTitle = $(".chatTitle");
	var $chatFields = $(".chatField");
	var $userList = $(".users ul");
	var $channelList = $(".channels ul");
	var $messageBox = $("#messageBox");
	var $sendMessage = $("#sendMessage");
	var channels = [];
	var username = $(".username").text();
	
	{
		// Socket functionları mesaj gönderme mesaj alma çıkma felan
			socket.onmessage = function(message){
			var jsonData = JSON.parse(message.data);
			
			if(jsonData.returnType == "ChatMessage"){
				var elementBundleControllName = "";  
				
				if(jsonData.recipient == "Genel"){ elementBundleControllName = "Genel"}
					else { elementBundleControllName = jsonData.sender; if($("#" + elementBundleControllName).length == 0){ addNewChatChannel(elementBundleControllName); addNewChatField(elementBundleControllName); }  }

				$("#"+elementBundleControllName).append("<span class='message'> <b>"+ jsonData.sender + " </b> :" + jsonData.message + "</span> <br/>");
				messageNotification(elementBundleControllName);
				lowerScrollBar($("#"+elementBundleControllName));

			}
			else if(jsonData.returnType == "UsernameListWrapper") {
					// kullanıcıisimleri update olunca
					$userList.html("");
				for(var i=0 ; i<jsonData.usernameList.length ; i++){
					$userList.append("<li> <a href='#'>" + jsonData.usernameList[i] + "</a> </li>");
					$(document).trigger('newUserEvent' , $(".users ul li a").last());
				}
			}
		}
			
		window.onbeforeunload = function(){
			socket.close();
		};
		
		$sendMessage.click(function(){
			sendMessage();
		});
		
		$messageBox.keydown(function(e){
			var code = e.which;
			if(code == 13) { e.preventDefault(); sendMessage(); }
		});
		
		function sendMessage(){
			socket.send(JSON.stringify({'message' : $messageBox.val() , 'recipient' : $chatTitle.text()}));
			if($chatTitle.text() != "Genel"){ $("#" + $chatTitle.text()).append("<span class='message'> <b>"+ username + " </b> :" + $messageBox.val() + "</span> <br/>"); }
			$messageBox.val("");
			lowerScrollBar($("#" + $chatTitle.text()));
		}
	}
	
	
	$(document).on("newUserEvent" , function(e,target){
		$(".users ul li a").last().click(function(event){
			event.preventDefault();
			var username = $(this).text();
			// a etiketinin text'i ile chatField divimizin id alanı aynı ordan birleştiriyoruz
			if(channels.indexOf(username) == -1){
				channels.push(username);
				// kişinin sohped kanal oluşturuyoruz
				addNewChatChannel(username);
				// kişinin sohped alanını oluşturuyoruz
				addNewChatField(username);
			}
			// kişinin sohped alanı otomatik açılacağı için kanalını işaretliyoruz
			markerChannel($(".channels ul li a:contains("+username+")"));
			showChatField(username);
			$messageBox.focus();
		});
	});

	$(document).on('newChannelEvent' , function(e , target){
		$(".channels ul li a").last().click(function(event){
			event.preventDefault();
			markerChannel($(this));
			showChatField($(this).text());
			$messageBox.focus();
		});
	});
	


	{
		// bireysel function yeri
		function addNewChatChannel(channelText){
			var channelName = channelText.trim();
			if($(".channels ul li a:contains("+channelName+")").length == 0 ){
				$(".channels ul").append("<li><a href='#' >" +channelName+ "</a> </li>");
				$(document).trigger('newChannelEvent' , $(".channels ul li a").last());
			}
		}
		
		function addNewChatField(fieldId) {
			$(".chatFieldWrapper").append("<div class='chatField' id='"+ fieldId.trim() +"' style='display:none'></div>");
		}
		
		function markerChannel(channelObject){
			channelObject.css("background-color" , "#D5D4D4").css("border" , "1px solid #C2C2C2")
			$(".channels ul li a").not(channelObject).css("background-color" , "#EDEDED").css("border" , "1px solid #CECECE");
		}
		
		function lowerScrollBar(chatField){
			chatField.animate({
			 	scrollTop : chatField.height() * chatField.height(),
			 },0);
		}
		
		function showChatField(id) {
			$(".chatField").not("#" + id).hide();
			$("#" + id).show();
			$(".chatTitle").html(id);
		}
		
		function messageNotification(bundleName) {
			if($("#" + bundleName).css("display") == "none") { $(".channels ul li a:contains("+bundleName+")").css("background","#ABADFF"); } // divId,channelText,userName her userin aynı olduğu için bundleName
		}
		
	}
	
	/*Test
	 * setInterval(function(){
		socket.send(JSON.stringify({'message' : username , 'recipient' : "Genel"}));
	},1);*/
	
	$(document).trigger('newChannelEvent' , $(".channels ul li a").last());
	setTimeout(function(){
		try{socket.send(JSON.stringify({'message' : username , 'recipient' : ""}));}
		catch(err){alert("Hata oluştu"); location.reload(); }
	},500);

}); 

</script>
<jsp:include page="../resources/template/footer.jsp"></jsp:include>
</body>
</html>