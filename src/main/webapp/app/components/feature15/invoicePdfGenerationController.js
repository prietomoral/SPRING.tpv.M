tpv.controller('InvoicePdfGenerationController', [
		'$timeout',
		'f15Service',
		function($timeout, f15Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.generateInvoicePdf = generateInvoicePdf;
			vm.listInvoices = listInvoices;
			vm.response = "";
			vm.invoices = {};
			vm.invoice = {};

			function generateInvoicePdf() {
				const
				delay = 2000;
				f15Service.generateInvoicePdf(vm.invoice.id).then(
						function(result) {
							vm.completed = true;
							vm.response = result.token + ":" + result.rol;
							sessionStorage.token = result.token;
							sessionStorage.rol = result.rol;
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
				}, function error(errors) {
					console.log(errors);
				});
			}

		} ]);