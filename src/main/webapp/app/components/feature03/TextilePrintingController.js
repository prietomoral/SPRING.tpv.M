tpv.controller('TextilePrintingController', [ '$timeout', 'f03Service',
		function($timeout, f03Service) {
			"use strict";
			var vm = this;
			
            vm.completed = false;
			vm.listTextilePrinting = listTextilePrinting;
			vm.error = false;
			vm.response = "";

			function listTextilePrinting() {
				const
				delay = 2000;
				f03Service.listTextilePrinting().then(function(result) {
					alert(result);
					// promise was fullfilled
					vm.completed = true;
					vm.response = "";
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

		} ]);