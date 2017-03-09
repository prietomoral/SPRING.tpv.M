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
			const
			delay = 2000;
			//alert(vm.id_ticket);
			f08Service.create(vm.id_ticket).then(function(result) {
				// promise was fullfilled
				vm.completed = true;
				vm.response = "Elemento creado";
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
			
			/*function create2() {
				const
				delay = 2000;
				//alert(vm.id_ticket);
				switch(vm.id_ticket){
				   case "1":
					   alert(vm.id_ticket);
					   vm.completed = true;
					   vm.response = "OK";
					   $timeout(function() {
							vm.completed = false;
						}, delay)
					   break;
				   case "2":
					   vm.error = true;
					   vm.response = "ID Ticket no válido";
					   $timeout(function() {
							vm.error = false;
						}, delay)
					   break;
				   case "3":
					   vm.error = true;
					   vm.response = "El ticket no tiene un usuario válido";
					   $timeout(function() {
							vm.error = false;
						}, delay)
					   break;
				}
			}*/
	} ]);