var nav = angular.module('my.nav', ['ui.router']);

nav.config(function($stateProvider, $urlRouterProvider) {
    
		$urlRouterProvider.when('/listUsers', '/listUsers');
    	$stateProvider
        
    
	    // Home view
    	.state('home', {
            url: '/home',
            templateUrl: 'app/components/default/default.tpl.html',
        	controller: 'DefaultCtrl',
        	controllerAs: 'def'
        })
        .state('listUsers', {
	    	url: '/listUsers',
	        templateUrl: 'app/components/listUsers/listUsers.tpl.html',
	        controller: 'ListUsersCtrl',
        	controllerAs: 'lu'
	    })
	    .state('addUser', {
	    	url: '/addUser',
	        templateUrl: 'app/components/addUser/addUser.tpl.html',
	        controller: 'AddUserCtrl',
        	controllerAs: 'au'
	    })
        
        
        $urlRouterProvider.otherwise('/home');
        
});

var main = angular.module('my.bootstrap', ['ui.bootstrap']);

var mainApp = angular.module('app', ['my.nav','my.bootstrap', 'ngResource', 'mgo-angular-wizard']);
