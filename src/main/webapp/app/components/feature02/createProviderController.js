tpv.controller('CreateProviderController', [ 'f02Service',
	function(f02Service) {
		"use strict";
		var vm = this;
		
		vm.completed = false;
		vm.error = false;
		vm.createProvider = createProvider;
		
		function createProvider(){
			f02Service.createProvider(vm.provider).then(function success(response){
				vm.data = response;
				vm.completed = true;
		    },
		    function error(errors){
		    	vm.error = true;
		    	console.log(errors);
		    });
		}
	} 
]);