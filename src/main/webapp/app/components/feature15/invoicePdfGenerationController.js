tpv.controller('InvoicePdfGenerationController', [ '$timeout', 'f15Service',
	function($timeout, f15Service) {
			"use strict";
			var vm = this;
	
			vm.completed = false;
			vm.error = false;
			vm.generateInvoicePdf = generateInvoicePdf;
			vm.response = "";
			vm.invoices = ["Testing", "Testing2"];
			vm.invoiceId = 0;
	
			function generateInvoicePdf() {
				const delay = 2000;
				f15Service.generateInvoicePdf(vm.invoiceId).then(function(result) {
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
	}
]);