almaApp.controller("ChatController",function($scope,ChatService){
	
	$scope.messages = [] ;
	$scope.message = "" ;
	$scope.max = 140 ;
	
	$scope.addMessage = function(){
		console.log("addMessage in chat controller..")
		ChatService.send($scope.message);
		$scope.message = "";
	};
	ChatService.receive().then(null,null,function(message){
		console.log("receive in chat controller..");
		console.log("friend id " + message.fid);
		$scope.messages.push(message);
		
	});
});