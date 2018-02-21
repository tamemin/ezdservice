mainApp.controller('InjectKeyPairCtrl', function($scope, $state, $http, $window,
		caUtils) {

	var self = this;

	self.ellipticCurves = caUtils.supportedEcdsaCurves;

	self.keyPair = $scope.keyPair;
	
	self.onSuccess = $scope.onSuccess;
	
	self.keyPair.$valid = false;

	self.onKeysSubmit = function() {
		if (self.keyPair.$valid) {
			self.onSuccess();
		} else {
			$window.alert("Please correct form errors, then retry");
		}
	}

	self.generateEcdsaKey = function() {
		caUtils.generateEcdsaKey(self.ellipticCurve, function(keyData) {
			self.keyPair.publicKey = keyData.publicKey;
			self.keyPair.privateKey = keyData.privateKey;
			self.activeKeyGenTab = 0;
		});
	};

	self.generateRsaKey = function() {
		caUtils.generateRsaKey(self.rsaKeyLength, function(keyData) {
			self.keyPair.publicKey = keyData.publicKey;
			self.keyPair.privateKey = keyData.privateKey;
			self.activeKeyGenTab = 0;
		})
	};
});
