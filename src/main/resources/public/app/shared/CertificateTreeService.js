mainApp.factory('certificateTree', function($http, $window){
	var certificateTree = {};
	
	certificateTree.entries = [];
	
		certificateTree.loadFromServer = function(onSuccess) {
			var data = {};
			var config = {
				headers : {
					'Content-Type' : 'application/json'
				}
			};
			$http.get(
					'http://localhost:8005/caserver/caService/getTree',
					data, config).then( function(response) {
						if(response.data === "") {
							certificateTree.entries = [];
						} else {
							certificateTree.entries = {"root":response.data.data};
						}
						onSuccess();
					}, function(response) {
						$window.alert(""+response.data.error.message + "\n" + response.data.error.detail);
					});
		}
	
		certificateTree.setSelectedEntry = function(serialNumber) {
			certificateTree.selectedEntry = serialNumber;
		}
		
		certificateTree.deleteSelectedEntry = function(onSuccess) {
			var data = {};
			var config = {
				headers : {
					'Content-Type' : 'application/json'
				}
			};
			$http.delete(
					'http://localhost:8005/caserver/caService/deleteEntity/'+certificateTree.selectedEntry,
					data, config).then( function(response) {
						certificateTree.loadFromServer(onSuccess);
					}, function(response) {
						$window.alert(""+response.data.error.message + "\n" + response.data.error.detail);
					});
		}
		
		certificateTree.revokeSelectedEntry = function(onSuccess) {
			var data = {id:certificateTree.selectedEntry};
			var config = {
				headers : {
					'Content-Type' : 'application/json'
				}
			};
			$http.put(
					'http://localhost:8005/caserver/caService/revokeEntity',
					data, config).then( function(response) {
						certificateTree.loadFromServer(onSuccess);
					}, function(response) {
						$window.alert(""+response.data.error.message + "\n" + response.data.error.detail);
					});
		}
	
		certificateTree.createRoot = function(data, onSuccess) {
		var config = {
			headers : {
				'Content-Type' : 'application/json'
			}
		}
		$http.post(
				'http://localhost:8005/caserver/caService/initialise',
				data, config).then(function(response) {
					
					// If succeeded, retrieve the entity details
					
					$http.get(
							'http://localhost:8005/caserver/caService/getEntitySummary/' + response.data.data.id,
							data, config).then(function(response) {
								// if succeeded, add the entity details to out tree
								certificateTree.addRoot(response.data.data);
								onSuccess(response.data.data.serialNumber);
							}, function(response) {
								$window.alert(""+response.data.error.message + "\n" + response.data.error.detail);
							});
				}, function(response) {
					$window.alert(""+response.data.error.message + "\n" + response.data.error.detail);
				});
	};
	
	certificateTree.addRoot = function(entry) {
		
		certificateTree._addChildToList(certificateTree.entries, entry);
	};
	
	/**
	 * Add child to tree, return false if could not find parent, otherwise true
	 */
	certificateTree.addChild = function(parentEntry, entry) {
		var log = {};
		certificateTree._addChild(certificateTree.entries, parentEntry, entry, log);
		if(log.found) {
			return true;
		}
		return false;
	};
	
	
	certificateTree._addChildToList = function(items, entry) {
		
		var children = [];
		
		items.push({item:entry, children: children});
	};
	
	/**
	 * recursive method, don't call this one.
	 */
	certificateTree._addChild = function(entriesToSearch, parentEntry, entry, log) {
		angular.forEach(entriesToSearch, function(value) {
			
			if(!this.found && value.entry.serialNumber === parentEntry.serialNumber) {
				certificateTree._addChildToList(value.children, entry);
				this.found = true;
			}
			if(!this.found) {
				certificateTree._addChild(value.children, parentEntry, entry, log);
			}
		})
	}
	
	return certificateTree;
});