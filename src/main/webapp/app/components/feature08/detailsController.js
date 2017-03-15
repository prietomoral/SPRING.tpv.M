tpv.controller('DetailsInvoiceController', DetailsInvoiceController, 'Alertify', '$routeParams');

function DetailsInvoiceController (f08Service, $routeParams) {
	"use strict";
	var vm = this;
	vm.completed = false;
	vm.error = false;
	vm.id = $routeParams.idInvoice;
	vm.getInvoiceById = getInvoiceById;
		
	function getInvoiceById() {
		f08Service.getInvoiceById(vm.id).then(function(result) {
			// promise was fullfilled
			vm.completed = true;
			vm.data = result;
		}, function(errors) {
			// handle errors
			vm.error = true;
			vm.response = errors;
		});
	}
}