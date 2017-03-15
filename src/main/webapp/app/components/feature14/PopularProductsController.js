tpv.controller('PopularProductsController', [ '$timeout', 'f14Service',
		function($timeout, f00Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.response = "";
			vm.popular = popular;

			function popular() {
				const
				delay = 2000;
				f14Service.popular().then(function(result) {
					// promise was fullfilled
					vm.completed = true;
					vm.response = result.popular;
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