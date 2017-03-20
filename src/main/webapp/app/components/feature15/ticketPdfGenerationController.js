tpv.controller('TicketPdfGenerationController', [
		'$timeout',
		'f15Service',
		function($timeout, f15Service) {
			"use strict";
			var vm = this;
			
			vm.authorized = f15Service.isAuthorized();
			
			vm.completed = false;
			vm.error = false;
			vm.generateTicketPdf = generateTicketPdf;
			vm.listTickets = listTickets;
			vm.tickets = [];
			vm.selectedTicket = [];

			function generateTicketPdf() {
				const
				delay = 2000;
				f15Service.generateTicketPdf(vm.selectedTicket.id).then(
						function(result) {
							vm.completed = true;
							$timeout(function() {
								vm.completed = false;
							}, delay)
						}, function(errors) {
							vm.error = true;
							vm.response = errors;
							$timeout(function() {
								vm.error = false;
							}, delay)
						});
			}

			function listTickets() {
				f15Service.findAllTickets().then(function success(response) {
					console.log(response);
					vm.tickets = response;
					vm.selectedTicket = vm.tickets[0];
				}, function error(errors) {
					console.log(errors);
				});
			}

		} ]);