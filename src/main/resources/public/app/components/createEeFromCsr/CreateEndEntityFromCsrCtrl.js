mainApp
		.controller(
				'CreateEndEntityFromCsrCtrl',
				function($scope, $state, $http, $window, certificateTree,
						caUtils) {

					var self = this;

					self.$state = $state;

					if (certificateTree.selectedEntry == null) {
						$state.go('listAllCerts');
					}
					
					self.open1 = function() {
						self.popup1.opened = true;
					};
					self.open2 = function() {
						self.popup2.opened = true;
					};

					self.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd',
							'dd.MM.yyyy', 'shortDate' ];
					self.format = self.formats[0];
					self.altInputFormats = [ 'M!/d!/yyyy' ];
					self.popup1 = {
						opened : false
					};
					self.popup2 = {
						opened : false
					};
					self.dateOptions = {
						dateDisabled : false,
						formatYear : 'yyyy',
						maxDate : new Date(2020, 5, 22),
						minDate : new Date(),
						startingDay : 1
					};

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

					self.onBasicsSubmit = function() {
						if (self.formData.basics.$valid) {
							$state.go('createEEFromCsr.extensions');
						} else {
							$window
									.alert("Please correct form errors, then retry");
						}
					};

					self.processForm = function() {

						if (!self.formData.extensions.$valid) {
							$window
									.alert("Please correct form errors, then retry");
							return;
						}
						if (!self.formData.keyPair.$valid) {
							$state.go('createEEFromCsr.keys');
							return;
						}
						if (!self.formData.basics.$valid) {
							$state.go('createEEFromCsr.basics');
							return;
						}

						var data = {
							'publicKey' : self.formData.keyPair.publicKey,
							'privateKey' : self.formData.keyPair.privateKey,
							'validFrom' : self.formData.basics.validFrom,
							'validTo' : self.formData.basics.validTo,
							'csr' : self.formData.basics.csr,
							'issuingAuthorityId' : self.formData.issuingAuthorityId,
							// 'signatureAlgorithm' :
							// self.formData.basics.signatureAlgorithm
						};
						caUtils.createEndEntityFromCsr(data, function() {
							$state.go('listAllCerts');
						});

					}

				});
