'use strict'
almaApp.service('friendService',['$http','$q','$rootScope',function($http,$q,$rootScope){
	var REST_SERVICE_URI = 'http://localhost:8080/AlmaBridgeB' 
	
		
		var factory = {
			fetchAllFriends: fetchAllFriends,
			pendingFriendRequests: pendingFriendRequests,
			addFriend:addFriend,
			approveFriend:approveFriend,
			rejectFriend:rejectFriend,
	        unFriend:unFriend
	       
	    };

	 return factory;
	 
	    function fetchAllFriends() {
	        var deferred = $q.defer();
	       
	        $http.get(REST_SERVICE_URI+'/friends/'+$rootScope.currentUserId+'/')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	                console.log('Inside fetchAllFriends with current user '+$rootScope.currentUserId);
	            },
	            function(errResponse){
	            	
	                console.error('Error while fetching friends..');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    function pendingFriendRequests() {
	        var deferred = $q.defer();
	        
	        $http.get(REST_SERVICE_URI+'/pendingFriendRequests/'+$rootScope.currentUserId+'/')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching pending friendrequests..');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    function addFriend(friendId) {
	        var deferred = $q.defer();
	        console.log('Inside addFriend with current user '+$rootScope.currentUserId);
	        $http.get(REST_SERVICE_URI+'/addFriend/'+friendId+'/'+$rootScope.currentUserId+'/')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while adding friend..');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    function approveFriend(friendId) {
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI+'/approveFriend/'+friendId+'/'+$rootScope.currentUserId+'/')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while approving friend..');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    function rejectFriend(friendId) {
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI+'/rejectFriend/'+friendId+'/'+$rootScope.currentUserId+'/')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while rejecting friend..');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    function unFriend(friendId) {
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI+'/unFriend/'+friendId+'/'+$rootScope.currentUserId+'/')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while unfriending friend..');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	    
}]);