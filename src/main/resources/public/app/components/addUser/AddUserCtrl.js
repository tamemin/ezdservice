mainApp.controller(
				'AddUserCtrl',
				function($http, $state, $window) {
					
					var self = this;
					
					self.$state = $state;
					
					self.formData = {}
					
					
					self.processForm = function() {

						
						if(!self.formData.$valid) {
							$window
							.alert("Please correct form errors, then retry");
							return;
						}

						var data = {
								
								'userName' : self.formData.username,
								'firstName' : self.formData.firstname,
								'lastName': self.formData.lastname,
								'email':self.formData.email
						};
						
						self.createUser(data);
						
					}
					
					self.createUser = function (data) {				
						
						$window
						.alert("Create user");
						
						
						//caUtils.generateCsr(data, function(data) {
							//self.csr = data.csr;
							//$state.go('createCsr.csr');
							
						//})
					};
				});
