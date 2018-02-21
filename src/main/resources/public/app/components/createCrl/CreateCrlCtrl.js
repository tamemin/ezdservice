mainApp.controller(
				'CreateCrlCtrl',
				function($http, $window, certificateTree) {
					
					var self = this;
					
					self.data = {};
					
					self.data.issuingAuthorityId = certificateTree.selectedEntry;
					
					self.createCrl = function () {
						
						var data = {
								issuingAuthorityId : self.data.issuingAuthorityId,
								nextUpdateDate     : self.data.nextUpdateAt,
								crlNumber          : self.data.crlNumber,
								signatureAlgorithm : self.data.signatureAlgorithm
						};
						
						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						}
						$http.post(
								'http://localhost:8005/caserver/caService/createCrl',
								data, config).then(function(response) {
									self.responseCrl = response.data.data.crl;
								},function(response) {
									$window.alert(response.data.error.message + "\n" + response.data.error.detail);
								});
					};
					
					
				});
				