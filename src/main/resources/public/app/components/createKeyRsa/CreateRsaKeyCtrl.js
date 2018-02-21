mainApp.controller(
				'CreateRsaKeyCtrl',
				function($http, $window) {
					
					var self = this;
					
					self.generateRsaKey = function () {
						var data = JSON.stringify({
							
								'keyLength' : self.keyLength
							
						});
						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						}
						$http.post(
								'http://localhost:8005/caserver/keys/rsa/generate',
								data, config).success(
								function(response, status, headers, config) {
									self.response = response.data;
								}).error(
								function(response, status, header, config) {
									$window.alert(""+response.error.message + "\n" + response.error.detail);
								});
					};
				})