mainApp.controller(
				'CreateCsrCtrl',
				function($http, $state, $window, caUtils) {
					
					var self = this;
					
					self.$state = $state;
					
					self.formData = {
							'keyPair' : {},
							'basics' : {}
					}
					
					self.onKeyInjectionSuccess = function() {
						$state.go('createCsr.basics');
					}
					
					self.processForm = function() {

						if(!self.formData.keyPair.$valid) {
							$state.go('createCsr.keys');
							return;
						}
						if(!self.formData.basics.$valid) {
							$window
							.alert("Please correct form errors, then retry");
							return;
						}

						var data = {
								
								'publicKey' : self.formData.keyPair.publicKey,
								'privateKey': self.formData.keyPair.privateKey,
								'x500Name':self.formData.basics.x500Name,
//								'signatureAlgorithm':self.formData.basics.signatureAlgorithm
							
						};
						
						self.generateCsr(data);
						
					}
					
					self.generateCsr = function (data) {
						
						
						caUtils.generateCsr(data, function(data) {
							self.csr = data.csr;
							$state.go('createCsr.csr');
						})
					};
				});
