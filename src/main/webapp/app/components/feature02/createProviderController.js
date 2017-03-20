tpv.controller('CreateProviderController', CreateProviderController, 'Alertify', '$location');

function CreateProviderController(f02Service, Alertify, $location) {
	"use strict";
	var vm = this;
	
	vm.createProvider = createProvider;

	function createProvider(){
		f02Service.createProvider(vm.provider).then(function success(response){
			Alertify.success("The provider has been created successfully!");
			$location.path('/feature02/list-providers');
	    },
	    function error(errors){
	    	if (errors.status == 401 || errors.status == 403) {	    
	    		Alertify.error("User Unathorized. You must login with user Admin or Manager!");
	    	}else{
	    		Alertify.error("The provider has not been created successfully!");
	    	}
	    });
	}
}