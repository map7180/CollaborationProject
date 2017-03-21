'use strict'
angular.module('almaApp').controller(
		'blogController',
		 [
		  '$scope',
		  'blogService',
		  '$location',
		  '$rootScope',
		  '$http',
		  function($scope,blogService,$location,$rootScope,$http) {
			  console.log("Inside blogController");
			  
			  var self = this;
			  $scope.blog = {errorCode:"",errorMsg:"",blogId:null,title:"",description:"",userId:$rootScope.currentUserId,blogDate:new Date().toDateString(),noOfViews:"28",status:"N",reason:"NA",likes:"30",dislikes:"40"};
			  self.blog = {errorCode:"",errorMsg:"",blogId:null,title:"",description:"",userId:$rootScope.currentUserId,blogDate:new Date().toDateString(),noOfViews:"28",status:"N",reason:"NA",likes:"30",dislikes:"40"};
			  self.blogs=[];
			  $scope.blogs =[];
			  
			    self.submit = submit;
			    self.edit = edit;
			    self.remove = remove;
			    self.reset = reset;
			    
			    console.log("self initialized.."+self.blog.description);
			  
			  getAllBlogs();
			  
			   function getAllBlogs(){
				  console.log("in fetch all blogs..")
				  blogService.fetchAllBlogs()
				       		.then(
				       				function(d){$scope.blogs =d;
				       				console.log("blogs array length is .."+d.length) ;
				       				},
				       				function(errResponse){console.error('Error while getting blogs');});
				       				
			  }
			   
			   $scope.getBlog = function getBlog(id){
			        blogService.getBlog(id)
			            .then(
			            function(d) {
			                /*$scope.currentBlog = d;*/
			                $location.path('/blogDetails');
			                console.log("getting blog with id .."+id +'/'+d.title) ;
			                $rootScope.currentBlog = d; 
			                $rootScope.currentBlogId = id; 
			            },
			            function(errResponse){
			                console.error('Error while fetching current blog');
			            }
			        );
			    }
			    function createBlog(blog){
			    	 console.log('Inside create blog...');
			    	blogService.createBlog(blog)
			            .then(
			       
			           
			            getAllBlogs,
			           /* $location.path('/login'),*/
			            function(errResponse){
			                console.error('Error while creating blog');
			            }
			        );
			    }
			   $scope.updateBlog = function updateBlog(blog, blogId){
			    	blogService.updateBlog(blog, blogId)
			            .then(
			            getAllBlogs,
			            function(errResponse){
			                console.error('Error while updating blog');
			            }
			        );
			    }
			   
			   $scope.deleteBlog = function deleteBlog(blogId){
			    	blogService.deleteBlog(blogId)
			            .then(
			            getAllBlogs,
			            function(errResponse){
			                console.error('Error while deleting Blog');
			            }
			        );
			    }
			   
			    function submit() {
			    	 console.log('Inside submit...');
			    	 console.log('User name', self.blog.description);
			       /* if(self.user.userId===null){*/
			            createBlog(self.blog);
		/*	        }else{
			            updateUser(self.user, self.user.userId);
			            console.log('User updated with id ', self.user.userId);
			        }*/
			        reset();
			    }
			   
			   function edit(id){
			        console.log('id to be edited', id);
			        for(var i = 0; i < self.blogs.length; i++){
			            if(self.blogs[i].blogId === id) {
			                self.blog = angular.copy(self.blogs[i]);
			                break;
			            }
			        }
			    }
			 
			    function remove(id){
			        console.log('id to be deleted', id);
			        if(self.blog.blogId === id) {//clean form if the user to be deleted is shown there.
			            reset();
			        }
			        deleteBlog(id);
			    }
			 
			 
			    function reset(){
			    	self.blog = {errorCode:"",errorMsg:"",blogId:null,title:"",description:"",userId:$rootScope.currentUserId,blogDate:new Date().toDateString(),noOfViews:"28",status:"N",reason:"NA",likes:"30",dislikes:"40"};
			    	$scope.blog = {errorCode:"",errorMsg:"",blogId:null,title:"",description:"",userId:$rootScope.currentUserId,blogDate:new Date().toDateString(),noOfViews:"28",status:"N",reason:"NA",likes:"30",dislikes:"40"};
			        $scope.blogForm.$setPristine(); //reset Form
			    }
			   
 /*			  	$scope.message = 'Look! I am a Blog page.';
			  	$scope.blog = 'Look! I am a abc page.';
			  	$scope.comments= [
                           {
                               rating:5,
                               comment:"Imagine all the eatables, living in conFusion!",
                               author:"John Lemon",
                               date:"2012-10-16T17:57:28.556094Z"
                           },
                           {
                               rating:4,
                               comment:"Sends anyone to heaven, I wish I could get my mother-in-law to eat it!",
                               author:"Paul McVites",
                               date:"2014-09-05T17:57:28.556094Z"
                           },
                           {
                               rating:3,
                               comment:"Eat it, just eat it!",
                               author:"Michael Jaikishan",
                               date:"2015-02-13T17:57:28.556094Z"
                           },
                           {
                               rating:4,
                               comment:"Ultimate, Reaching for the stars!",
                               author:"Ringo Starry",
                               date:"2013-12-02T17:57:28.556094Z"
                           },
                           {
                               rating:2,
                               comment:"It's your birthday, we're gonna party!",
                               author:"25 Cent",
                               date:"2011-12-02T17:57:28.556094Z"
                           }    
                        ];*/
                    
			  	
			  	
}]);