tpv.controller('ListProviderController', [ 'f02Service',
	function(f02Service) {
		"use strict";
		var vm = this;
		
		vm.listProviders = listProviders;
		
		function listProviders(){
			f02Service.listProviders().then(function success(response){
				vm.data = response;
		    },
		    function error(errors){
		      console.log(errors);
		    });
		}
	} 
]);