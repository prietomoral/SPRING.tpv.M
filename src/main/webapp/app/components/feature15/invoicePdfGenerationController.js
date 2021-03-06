tpv.controller('InvoicePdfGenerationController', [
		'$timeout',
		'f15Service',
		function($timeout, f15Service) {
			"use strict";
			var vm = this;

			vm.authorized = f15Service.isAuthorized();
			
			vm.completed = false;
			vm.error = false;
			vm.generateInvoicePdf = generateInvoicePdf;
			vm.listInvoices = listInvoices;
			vm.invoices = [];
			vm.invoice = [];

			function generateInvoicePdf() {
				const
				delay = 2000;
				f15Service.generateInvoicePdf(vm.invoice.id).then(
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

			function listInvoices() {
				f15Service.findAllInvoices().then(function success(response) {
					console.log(response);
					vm.invoices = response;
					vm.invoice = vm.invoices[0];
				}, function error(errors) {
					console.log(errors);
				});
			}

		} ]);