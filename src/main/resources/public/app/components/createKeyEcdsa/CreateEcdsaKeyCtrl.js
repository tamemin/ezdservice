mainApp.controller(
				'CreateEcdsaKeyCtrl',
				function($http, $window) {
					
					var self = this;
					
					
					self.generateEcdsaKey = function () {
						var data = JSON.stringify({
							
								'curve' : self.curve
							
						});
						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						}
						$http.post(
								'http://localhost:8005/caserver/keys/ecdsa/generate',
								data, config).success(
								function(response, status, headers, config) {
									self.response = response.data;
								}).error(
								function(response, status, header, config) {
									$window.alert(""+response.error.message + "\n" + response.error.detail);
								});
					};
				})