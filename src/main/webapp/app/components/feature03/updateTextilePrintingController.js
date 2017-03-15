tpv.controller('updateTextilePrintingController', updateTextilePrintingController, 'Alertify', '$routeParams');


function updateTextilePrintingController(f03Service, Alertify, $routeParams) {
  'use strict';
  var vm = this;
  vm.findOne = findOne;
  vm.id = $routeParams.idTextilePrinting;
  vm.editTextilePrinting = editTextilePrinting;
 
 
  function findOne(id){
	  f03Service.findOneTextilePrinting(vm.id).then(function success(response){
      vm.textilePrinting = response;
    },
    function error(errors){
	      if (errors.status == 401 || errors.status == 403) {	    
			  Alertify.error("User Unathorized. You must login with user Manager!");
		  }
    });
  }
  
  
  function editTextilePrinting(){
	  f03Service.editTextilePrinting(vm.textilePrinting).then(
	      function success(response){
	          vm.textilePrinting = {};
	          Alertify.success("The textile printing has been updated successfully!");
          },
	      function error(errors){
    	      if (errors.status == 401 || errors.status == 403) {	    
    			  Alertify.error("User Unathorized. You must login with user Manager!");
    		  }else{
    			  Alertify.error("The textile printing has not been updated successfully.");
    		  }
	      });
  }
	  
}