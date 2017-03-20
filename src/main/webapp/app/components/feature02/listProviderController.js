tpv.controller('ListProviderController', ListProviderController, 'Alertify');

function ListProviderController (f02Service, Alertify) {
	"use strict";
	var vm = this;
	
	vm.listProviders = listProviders;
	vm.deleteProvider = deleteProvider;
	
	function listProviders() {
		f02Service.listProviders().then(function success(response){
			vm.data = response;
	    },
	    function error(errors){
			if (errors.status == 401 || errors.status == 403) {	    
				Alertify.error("User Unathorized. You must login with user Admin or Manager!");
			}
		});
	}
	
	function deleteProvider(id) {
		f02Service.deleteProvider(id).then(function success(response){
			Alertify.success("The provider has been deleted successfully.");
			listProviders();
		},
		function error(errors){
			if (errors.status == 401 || errors.status == 403) {	    
				Alertify.error("User Unathorized. You must login with user Admin or Manager!");
			} else{
				Alertify.error("The provider has not been deleted successfully.");
			}
		});
	}
}