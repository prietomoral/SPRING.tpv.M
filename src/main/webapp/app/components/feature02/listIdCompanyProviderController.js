tpv.controller('ListIdCompanyProviderController', [ 'f02Service',
	function(f02Service) {
		"use strict";
		var vm = this;
		
		vm.listIdCompanyProviders = listIdCompanyProviders;
		
		function listIdCompanyProviders(){
			f02Service.listIdCompanyProviders().then(function success(response){
				vm.data = response;
		    },
		    function error(errors){
		      console.log(errors);
		    });
		}
	} 
]);