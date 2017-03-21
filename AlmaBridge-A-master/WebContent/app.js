var almaApp = angular.module('almaApp',['ngRoute']);
almaApp.config(["$routeProvider",function($routeProvider) {
	$routeProvider
	
	// route for the home page
	.when('/', {
		templateUrl : 'a_home/home.html',
		controller  : 'homeController'
	})

	// route for the about page
	.when('/about', {
		templateUrl : 'a_about/about.html',
		controller  : 'aboutController'
	})
	
	// route for the event page
	.when('/event', {
		templateUrl : 'a_event/event.html',
		controller  : 'eventController'
	})
	
	// route for the job page
	.when('/job', {
		templateUrl : 'a_job/job.html',
		controller  : 'jobController'
	})
	
	.when('/user', {
		templateUrl : 'a_user/user.html',
		controller  : 'registerController'
	})
	
	// route for the blog page
	.when('/blog', {
		templateUrl : 'a_blog/blog.html',
		controller  : 'blogController'
	})
	
	.when('/blogDetails', {
		templateUrl : 'a_blog/blogDetail.html',
		controller  : 'blogController'
	})

	// route for the friend page
	.when('/friend', {
		templateUrl : 'a_friend/friend.html',
		controller  : 'friendController'
	})
	
	// route for the login page
	.when('/login', {
		templateUrl : 'a_user/login.html',
		controller  : 'registerController'
	})
	
	.when('/logout', {
		templateUrl : 'a_home/home.html',
		controller  : 'registerController'
	})
	//route for users page
	
	
	// route for the job page
	.when('/register', {
		templateUrl : 'a_user/register.html',
		controller  : 'registerController'
	})
	// route for the contact page
	.when('/contact', {
		templateUrl : 'a_contact/contact.html',
		controller  : 'contactController'
	})
	
	// route for the chat 
	.when('/chat', {
		templateUrl : 'a_chat/chat.html',
		controller  : 'ChatController'
	})
	
	.otherwise({redirectTo:'/'});
}]);




