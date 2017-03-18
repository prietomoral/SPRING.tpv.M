tpv.controller('UserListController', [ 'Alertify', 'f08Service',
	function(Alertify, f08Service) {
		"use strict";
		var vm = this;

		vm.userList = userList;
			
		function userList() {
			f08Service.userList().then(function(result) {
				vm.data = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	} 
]);