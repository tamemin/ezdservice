mainApp.controller('InitialiseRootCtrl', function($http, $window,
		certificateTree) {

	var self = this;

	self.open1 = function() {
		self.popup1.opened = true;
	};
	self.open2 = function() {
		self.popup2.opened = true;
	};

	self.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate' ];
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

	self.isCollapsed = true;

	self.privateKey = {};
	self.keyPair = {
		'privateKey' : self.privateKey
	};

	self.initialiseRoot = function() {

		var data = {
			'validFrom' : self.validFrom,
			'validTo' : self.validTo,
			'publicKey' : self.keyPair.publicKey,
			'privateKey' : self.keyPair.privateKey,
			'maxIssuerChainLength' : self.maxIssuingAuthorityChainLength,
			'x500Name' : self.x500Name,
			'signatureAlgorithm' : self.signatureAlgorithm
		};

		certificateTree.createRoot(data, function(entityId) {
			self.responseEntityId = entityId;
		});

	};

});
