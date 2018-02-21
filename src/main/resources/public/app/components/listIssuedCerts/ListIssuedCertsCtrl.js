mainApp.controller(
				'ListIssuedCertsCtrl',
				function($http, $window, certificateTree) {
					
					var self = this;
					
					self.issuingAuthorityId = certificateTree.selectedEntry;
					
					self.response = {};
					
					self.listIssuedCerts = function () {
						var data = {};
						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						};
						$http.get(
								'http://localhost:8005/caserver/issuingAuthority/issuedCertificates/list/'+self.issuingAuthorityId,
								data, config).success(
								function(response, status, headers, config) {
									self.response = response;
								}).error(
								function(response, status, header, config) {
									$window.alert(""+response.error.message + "\n" + response.error.detail);
								});
					};
				});
