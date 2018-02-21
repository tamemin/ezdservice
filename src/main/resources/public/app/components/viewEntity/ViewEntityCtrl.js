mainApp.controller(
				'ViewEntityCtrl',
				function($scope, $http, $window, certificateTree) {
					
					var self = this;
					
					self.getEntity = function (entityId) {
						
						var data = {};
						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						}
						$http.get(
								'http://localhost:8005/caserver/caService/getEntity/' + entityId,
								data, config).then(function(response) {
									self.entity = response.data.data;
									// needed for the keypair template to work
									$scope.keyPair=self.entity.keyPair;
								},function(response) {
									$window.alert(response.data.error.message + "\n" + response.data.error.detail);
								});
					};
					
					self.getEntity(certificateTree.selectedEntry);
					
				});
				