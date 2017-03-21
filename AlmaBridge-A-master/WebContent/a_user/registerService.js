'use strict'
almaApp.service('registerService',['$http','$q','$rootScope',function($http,$q,$rootScope){
	var REST_SERVICE_URI = 'http://localhost:8080/AlmaBridgeB' 
	
		
		var factory = {
	        fetchAllUsers: fetchAllUsers,
	        createUser: createUser,
	        updateUser:updateUser,
	        deleteUser:deleteUser,
	        makeAdmin:makeAdmin,
	        approve:approve,
	        reject:reject,
	        authenticateCurrentUser:authenticateCurrentUser,
	    };

	 return factory;
	 
	    function fetchAllUsers() {
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI+'/users')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching Users');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	    function createUser(user) {
	        var deferred = $q.defer();
	        $http.post(REST_SERVICE_URI+'/registerUser', user)
	            .then(
	            function (response) {
	            	 window.alert("You are registered successfully !");
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while creating User');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	    function authenticateCurrentUser(user) {
	        var deferred = $q.defer();
	        $http.post(REST_SERVICE_URI+'/login',user)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching Users');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	    function updateUser(user, id) {
	        var deferred = $q.defer();
	        $http.put(REST_SERVICE_URI+'/'+id, user)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while updating User');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    function makeAdmin(userId) {
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI+'/makeAdmin/'+userId)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while making admin');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    function reject(userId) {
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI+'/reject/'+userId+'/')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while rejecting');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    function approve(userId){
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI+'/approve/'+userId+'/')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while approving..');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	    function deleteUser(id) {
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI+'/'+id)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while deleting User');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
}]);