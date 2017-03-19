tpv.controller('ListInvoicesController', [ '$timeout', 'Alertify', 'f08Service',

	function($timeout, Alertify, f08Service) {
		"use strict";
		var vm = this;

		vm.completed = false;
		vm.error = false;
		vm.ticket_id;
		vm.initList = initList;
		vm.search = search;
			
		function initList() {
			f08Service.initList().then(function(result) {
				vm.completed = true;
				vm.data = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function search() {
			f08Service.search(vm.ticket_id).then(function(result) {
				vm.completed = true;
				vm.data2 = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	} 
]);