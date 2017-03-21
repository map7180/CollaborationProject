'use strict'
almaApp.service('blogService',['$http','$q','$rootScope',function($http,$q,$rootScope){
	var BASE_URL = 'http://localhost:8080/AlmaBridgeB' 
	
		
		var factory = {
	        fetchAllBlogs: fetchAllBlogs,
	        getBlog:getBlog,
	        createBlog: createBlog,
	        updateBlog:updateBlog,
	        deleteBlog:deleteBlog,
	        /*getCurrentBlog:getCurrentBlog*/
	    };

		return factory;

		function fetchAllBlogs() {
	        var deferred = $q.defer();
	        $http.get(BASE_URL+'/blogs')
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching Blogs');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
		
		function getBlog(id) {
	        var deferred = $q.defer();
	        $http.get(BASE_URL+'/blog/'+id)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching the Blog');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
		
		function createBlog(blog) {
	        var deferred = $q.defer();
	        $http.post(BASE_URL+'/postBlog', blog)
	            .then(
	            function (response) {
	            	 window.alert("You have posted blog successfully !");
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while posting blog');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
		
		function updateBlog(blog, id) {
	        var deferred = $q.defer();
	        $http.put(BASE_URL+'/updateBlog/'+id, blog)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while updating blog');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
		function deleteBlog(id) {
	        var deferred = $q.defer();
	        $http.get(BASE_URL+'/deleteBlog/'+id)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while deleting Blog');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
}]);