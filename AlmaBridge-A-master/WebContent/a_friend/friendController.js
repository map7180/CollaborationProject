

'use strict'
angular.module('almaApp').controller(
		'friendController',
		 [
		  '$scope',
		  'friendService',
		  '$location',
		  '$rootScope',
		  '$http',
		  
		  function($scope,friendService,$location,$rootScope,$http) {
			  console.log("Inside friendController ...");

			  var self = this;
			    self.friend={errorCode:'',errorMsg:'',userId:$rootScope.currentUserId,id:'',friendId:'',status:'',isOnline:''};
			   
			    self.friends=[];
			    self.pendingFriends=[];
			 
			    self.submit = submit;
			    
			    self.reset = reset;
			    self.fetchAllFriends = fetchAllFriends ;
			    self.pendingFriendRequests = pendingFriendRequests ;
			    
			    
			    if($rootScope.isLogged){
			    	fetchAllFriends();
			    	pendingFriendRequests();
			    	
			    };
			    
			    
			    
			    console.log("self initialized.."+self.friend.friendId);
			    
			 
			    function fetchAllFriends(){
			    	
			        friendService.fetchAllFriends()
			            .then(
			            function(d) {
			            	 console.log($rootScope.currentUserId);
			            	 console.log("fetching friends and asigning to self.."+d.length);
			                 self.friends = d;
			              
			            },
			            function(errResponse){
			                console.error('Error while fetching Friends');
			            }
			        );
			    }
			    
			    function pendingFriendRequests(){
			        friendService.pendingFriendRequests()
			            .then(
			       
			            function(d) {
			            	 console.log("fetching pending friend request  and asigning to self.."+d.length);
			                self.pendingFriends = d;
			                $scope.pendingFriends = d;
			                
			                
			            },
			            function(errResponse){
			                console.error('Error while fetching pending friend requests..');
			            }
			        );
			    }
			    
			    
			    
			    
			   
			    
			    $scope.addFriend = function addFriend(friendId){
			        friendService.addFriend(friendId)
			            .then(
			            function(d) {
			               
			                window.alert("friend request has been sent to  "+friendId);
			                console.log("friend request has been sent to  "+friendId);
			               
			            },
			            function(errResponse){
			                console.error('Error while sending friend request');
			            }
			        );
			    }
			    
			    $scope.approveFriend = function approveFriend(friendId){
			        friendService.approveFriend(friendId)
			            .then(
			            function(d) {
			               
			                window.alert("friend request has been approved . "+friendId);
			                console.log("friend request has been approved . "+friendId);
			               
			            },
			            function(errResponse){
			                console.error('Error while accepting friend request');
			            }
			        );
			    }
			    
			    $scope.rejectFriend = function rejectFriend(friendId){
			        friendService.rejectFriend(friendId)
			            .then(
			            function(d) {
			               
			                window.alert("friend request has been rejected .. "+friendId);
			                console.log("friend request has been rejected .. "+friendId);
			               
			            },
			            function(errResponse){
			                console.error('Error while rejecting friend request ');
			            }
			        );
			    }
			    
			    $scope.unFriend = function unFriend(friendId){
			        friendService.unFriend(friendId)
			            .then(
			            function(d) {
			               
			                window.alert("you have unfriended  .. "+friendId);
			                console.log("you have unfriended  .. "+friendId);
			                
			            },
			            function(errResponse){
			                console.error('Error while unfriending .. ');
			            }
			        );
			    }
			 
			    
			 
			    function submit() {
			    	 console.log('Inside submit...');
			    	 console.log('User name', self.friend.friendId);
			      
			    	 addFriend(self.friend);
		
			        reset();
			    }

			 
			 
			    function reset(){
			    	self.friend={errorCode:'',errorMsg:'',userId:null,id:'',friendId:'',status:'',isOnline:''};
			        
			    }
			  	
}]);