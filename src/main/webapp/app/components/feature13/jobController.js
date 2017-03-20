tpv.controller('JobController', [
		'$timeout',
		'f13Service',
		function($timeout, f13Service) {
			"use strict";
			
			var vm = this;
			vm.completed = false;
			vm.error = false;
			vm.respuesta = "";
			
			vm.launchJob = function (){
				const delay = 2000;
				f13Service.launchJob().then(function(result){
					vm.completed = true;
					vm.response = "Job lanzado";
					$timeout(function() {
						vm.completed = false;
					}, delay)
				}, function (errors) {
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}

		} ]);