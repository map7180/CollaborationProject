'use strict'
almaApp.service('eventService',['$http','$q','$rootScope',function($http,$q,$rootScope){
	var REST_SERVICE_URI = 'http://localhost:8080/AlmaBridgeB' 
	
		
		var factory = {
	        fetchAllEvents: fetchAllEvents,
	        createEvent: createEvent,
	        updateEvent:updateEvent,
	        deleteEvent:deleteEvent,
	        
	    };

	 return factory;
	 
	    function fetchAllEvents() {
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI+'/eventList')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching Events');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	    function createEvent(event) {
	        var deferred = $q.defer();
	        $http.post(REST_SERVICE_URI+'/createEvent', event)
	            .then(
	            function (response) {
	            	 window.alert("New Event has been created ");
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while creating Event');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	 
	    function updateEvent(event, id) {
	        var deferred = $q.defer();
	        $http.put(REST_SERVICE_URI+'/'+id, event)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while updating Event');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    
	    
	 
	    function deleteEvent(id) {
	        var deferred = $q.defer();
	        $http.get(REST_SERVICE_URI+'/deleteEvent/'+id)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while deleting Eent');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
}]);