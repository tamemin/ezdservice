mainApp.controller(
				'ListAllCertsCtrl',
				function($http, $window, certificateTree) {
					
					var self = this;
					
					self.certificateTree = certificateTree;
					
					certificateTree.loadFromServer(function() {
						self.allowCreateRoot = false;
						
						if(certificateTree.entries.length === 0) {
							self.allowCreateRoot = true;
						}	
					});
					
					self.deleteSelectedEntry = function() {
						certificateTree.deleteSelectedEntry(function() {
							if(certificateTree.entries.length === 0) {
								self.allowCreateRoot = true;
							}
						});
						
					}
					
					self.revokeSelectedEntry = function() {
						certificateTree.revokeSelectedEntry(function(){});
						
					}
					
					self.setSelectedEntry = function(serialNumber) {
						certificateTree.setSelectedEntry(serialNumber);
					};
					
				});
				