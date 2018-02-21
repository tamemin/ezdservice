mainApp.directive("required", function($window) {
    return {
        restrict: 'A', // only for attributes
        compile: function(element) {
            // insert asterisk after element
        	var parent = element.parent();
        	if(angular.isElement(parent)) {
        		var spans = parent.find("span");
        		if(spans.length == 0 || !spans.hasClass("required")) {
        			labelElem = parent.find("label").after("<span class='required'>*</span>");
        		}
        	}
        }
    };
});