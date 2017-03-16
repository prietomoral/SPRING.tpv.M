tpv.controller('ListInvoicesController', [ '$timeout', 'f08Service',
	function($timeout, f08Service) {
		"use strict";
		var vm = this;

		vm.completed = false;
		vm.error = false;
		vm.ticket_id;
		vm.initList = initList;
		vm.search = search;
			
		function initList() {
			const
			delay = 2000;
			f08Service.initList().then(function(result) {
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
		
		function search() {
			const
			delay = 2000;
			f08Service.search(vm.ticket_id).then(function(result) {
				// promise was fullfilled
				vm.completed = true;
				vm.data2 = result;
			}, function(errors) {
				// handle errors
				vm.error = true;
				vm.response = errors;
			});
		}
	} 
]);