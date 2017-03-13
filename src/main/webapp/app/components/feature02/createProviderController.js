tpv.controller('CreateProviderController', [ 'f02Service',
	function(f02Service) {
		"use strict";
		var vm = this;
		
		vm.createProvider = createProvider;
		
		function createProvider(){
			f02Service.createProvider(vm.provider).then(function success(response){
				vm.data = response;
		    },
		    function error(errors){
		      console.log(errors);
		    });
		}
	} 
]);