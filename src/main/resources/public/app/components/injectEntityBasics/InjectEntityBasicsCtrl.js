mainApp.controller('InjectEntityBasicsCtrl', function($scope, $state, $http, $window) {

	var self = this;

	self.formData = $scope.entityBasics;
	
	self.onSuccess = $scope.onSuccess;
	
	self.formData.$valid = false;
	
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
		maxDate : new Date(2100, 1, 1),
		minDate : new Date(),
		startingDay : 1
	};


	self.open1 = function() {
		self.popup1.opened = true;
	};
	self.open2 = function() {
		self.popup2.opened = true;
	};

	
	self.onBasicsSubmit = function() {
		if (self.formData.$valid) {
			self.onSuccess();
		} else {
			$window.alert("Please correct form errors, then retry");
		}
	}

});
