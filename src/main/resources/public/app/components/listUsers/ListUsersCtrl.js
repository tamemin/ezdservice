mainApp.controller(
				'ListUsersCtrl',
				function($http, $window) {
					
					var self = this;
					
					self.openUser = function(userId) {						
						console.info(userId);
					};
					
					$http.get('/rest/service/users').then(function(response) {
						
						debugger;
						
						self.response = response.data;
						
					});		
				}
);
