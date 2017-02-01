tpv.controller('versionController', ['$timeout','f00Service',function($timeout, f00Service) {
	"use strict";
	let vm = this;
	vm.completed = false;
	vm.error = false;
	vm.response = "";

	vm.version = function() {
		const delay = 2000;
		f00Service.version().then(
			function(result) {
				// promise was fullfilled
				vm.completed = true;
		    	vm.response = result.version;
				$timeout(function() {
					this.completed = false;
				}, delay)
			},function(errors) {
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