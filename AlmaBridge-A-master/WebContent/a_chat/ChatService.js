almaApp.service("ChatService",function($q,$timeout,$rootScope){
	var service = {},listener = $q.defer(),
	socket ={
		client: null,
		stomp : null
		
	},
	messageIds = [] ;
	
	service.RECONNECT_TIMEOUT = 30000 ;
	service.SOCKET_URL = "http://localhost:8080/AlmaBridgeB/chat" ;
	service.CHAT_TOPIC = "/topic/message" ;
	service.CHAT_BROKER = "/app/chat" ;
	
	
	service.receive = function(){
		console.log("receive  in chat service..");
		return listener.promise ;
	};
	
	service.send = function(message){
		console.log("send in cchat service..");
		var id = Math.floor(Math.random()*1000000);
		socket.stomp.send(service.CHAT_BROKER,{
			priority : 9
		},JSON.stringify({
			message:message,
			fid:$rootScope.currentUserName,
			id:id
			
		}));
		messageIds.push(id);
	};
	
	var reconnect = function(){
		console.log("reconnect in chat service...")
		$timeout(function(){
			initialize();
		},this.RECONNECT_TIMEOUT);
	};
	
	var getMessage = function(data){
		console.log("getMessage in chat service..")
		var message = JSON.parse(data);out = {};
		out.message = message.message ;
		out.fid = message.fid ;
		out.time = new Date(message.time)
		return out ;
	};
	
	var startListener = function(){
		console.log("receive in chat service...")
		socket.stomp.subscribe(service.CHAT_TOPIC,function(data){
			listener.notify(getMessage(data.body));
		});
	};
	
	var initialize = function(){
		console.log("initialize in chat service..")
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({},startListener);
		socket.stomp.onclose = reconnect ;
	};
	
	initialize();
	return service ;
});