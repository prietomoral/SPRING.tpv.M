tpv.controller('CreateInvoiceController', [ '$timeout', 'f08Service',
	function($timeout, f08Service) {
		"use strict";
		var vm = this;

		vm.completed = false;
		vm.error = false;
		vm.create = create;
		vm.id_ticket;
		vm.respuesta = "";
		
		function create() {
			const delay = 4000;
			f08Service.create(vm.id_ticket).then(function(result) {
				// promise was fullfilled
				vm.completed = true;
				vm.response = "Invoice created";
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