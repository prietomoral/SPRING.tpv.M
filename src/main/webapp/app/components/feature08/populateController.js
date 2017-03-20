tpv.controller('PopulateController', [ '$timeout', 'f08Service',
	function($timeout, f08Service) {
		"use strict";
		var vm = this;

		vm.completed = false;
		vm.error = false;
		vm.populate = populate;
		vm.respuesta = "";
		
		function populate() {
			const
			delay = 2000;
			f08Service.populate().then(function(result) {
				// promise was fullfilled
				vm.completed = true;
				vm.response = "Se ha poblado la base de datos";
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