tpv.controller('PdfGenerationController', [ '$timeout', 'f15Service',
	function($timeout, f15Service) {
			"use strict";
			var vm = this;
	
			vm.completed = false;
			vm.error = false;
			vm.generatePdf = generatePdf;
			vm.response = "";
	
			function generatePdf() {
				const delay = 2000;
				f15Service.generatePdf().then(function(result) {
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