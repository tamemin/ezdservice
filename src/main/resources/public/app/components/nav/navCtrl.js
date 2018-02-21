mainApp.controller(
				'NavCtrl',
				 function($scope, $window) {
					
					var self = this;
					
				    self.selectedItem = function(sectionIndex, index) {
//				    	$window.alert($scope.items[sectionIndex].subItems[index].name + ':' + sectionIndex+'.' +index);
				    };
				    
					self.collapseAnother = function(index){
				        for(var i=0; i<self.items.length; i++){
				            if(i!=index){
				                self.items[i].active = false;
				            }
				        }
				    };
				    
					self.showChilds = function(index){
				        self.items[index].active = !self.items[index].active;
				        self.collapseAnother(index);
				    };

				    

				    self.items = [
				    	{
				            name: "Users",
				            subItems: [
				                       	{name: "List Users", sref: 'listUsers'},
						                {name: "Edit Users", sref: 'editUsers'},
						                {name: "Add user", sref: 'addUser'}
						            ]
				        }
				    ];
				}
);
