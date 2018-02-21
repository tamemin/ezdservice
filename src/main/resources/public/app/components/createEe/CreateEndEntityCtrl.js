mainApp
		.controller(
				'CreateEndEntityCtrl',
				function($scope, $state, $http, $window, certificateTree,
						caUtils) {

					var self = this;

					self.$state = $state;

					if (certificateTree.selectedEntry == null) {
						$state.go('listAllCerts');
					}

					self.formData = {
						// 'signatureAlgorithm' : 'SHA256WITHECDSA', use server
						// defaults for now.
						'issuingAuthorityId' : certificateTree.selectedEntry,
						'basics' : {},
						'keyPair' : {},
						'extensions' : {}
					};

					self.onKeyInjectionSuccess = function() {
						$state.go('createEE.basics');
					}

					self.onBasicsSuccess = function() {
						$state.go('createEE.extensions');
					}

					self.processForm = function() {

						if (!self.formData.extensions.$valid) {
							$window
									.alert("Please correct form errors, then retry");
							return;
						}
						if (!self.formData.keyPair.$valid) {
							$state.go('createEE.keys');
							return;
						}
						if (!self.formData.basics.$valid) {
							$state.go('createEE.basics');
							return;
						}

						var data = {
							'publicKey' : self.formData.keyPair.publicKey,
							'privateKey' : self.formData.keyPair.privateKey,
							'validFrom' : self.formData.basics.validFrom,
							'validTo' : self.formData.basics.validTo,
							'x500Name' : self.formData.basics.x500Name,
							'issuingAuthorityId' : self.formData.issuingAuthorityId,
							// 'signatureAlgorithm' :
							// self.formData.basics.signatureAlgorithm
						};
						caUtils.createEndEntity(data, function() {
							$state.go('listAllCerts');
						});

					}

				});
