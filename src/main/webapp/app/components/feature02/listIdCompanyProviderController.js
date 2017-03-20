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
		    	if (errors.status == 401 || errors.status == 403) {	    
		    		Alertify.error("User Unathorized. You must login with user Admin or Manager!");
		    	}
		    });
		}
	} 
]);