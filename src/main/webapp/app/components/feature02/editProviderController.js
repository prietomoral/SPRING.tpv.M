tpv.controller('EditProviderController', EditProviderController, 'Alertify', '$routeParams', '$location');

function EditProviderController(f02Service, Alertify, $routeParams, $location) {
	  'use strict';
	  var vm = this;
	  vm.id = $routeParams.idProvider;
	  vm.findOneProvider = findOneProvider;
	  vm.editProvider = editProvider;
	 
	  function findOneProvider(){
		  f02Service.findOneProvider(vm.id).then(function success(response){
	      vm.provider = response;
	    },
	    function error(errors){
	    	 if (errors.status == 401 || errors.status == 403) {	    
				  Alertify.error("User Unathorized. You must login with user Admin or Manager!");
			  }
	    });
	  } 
	  
	  function editProvider(){
		  f02Service.updateProvider(vm.provider).then(
		      function success(response){
		          Alertify.success("The provider has been updated successfully!");
		          $location.path('/feature02/list-providers');
	          },
		      function error(errors){
	        	  if (errors.status == 401 || errors.status == 403) {	    
					  Alertify.error("User Unathorized. You must login with user Admin or Manager!");
				  }else{
					  Alertify.error("The provider has not been updated successfully!");
				  }
		      });
	  }
}
