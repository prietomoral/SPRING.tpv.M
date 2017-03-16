tpv.controller('UserListController', [ '$timeout', 'f08Service',
	function($timeout, f08Service) {
		"use strict";
		var vm = this;

		vm.completed = false;
		vm.error = false;
		vm.userList = userList;
			
		function userList() {
			const
			delay = 2000;
			f08Service.userList().then(function(result) {
				// promise was fullfilled
				vm.completed = true;
				vm.data = result;
				$timeout(function() {
					vm.completed = false;
				}, delay)
			}, function(errors) {
				// handle errors
				vm.error = true;
				vm.response = errors;
				$timeout(function() {
					vm.error = false;
				}, delay)
			});
		}
	} 
]);