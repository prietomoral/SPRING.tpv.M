tpv.controller('updateEmbroideryController', updateEmbroideryController,'Alertify','$routeParams');


function updateEmbroideryController(f03Service,Alertify,$routeParams) {
  'use strict';
  var vm = this;
  vm.findOneEmbroidery = findOneEmbroidery;
  vm.id=$routeParams.idEmbroidery;
  vm.updateEmbroidery=updateEmbroidery;
 
 
  function findOneEmbroidery(id){
	  f03Service.findOneEmbroidery(vm.id).then(function success(response){
      vm.embroidery = response;
    },
    function error(errors){
      if (errors.status == 401 || errors.status == 403) {	    
		  Alertify.error("User Unathorized. You must login with user Manager!");
	  }
    });
  }
  
  
  function updateEmbroidery(){
	  f03Service.updateEmbroidery(vm.embroidery).then(
	      function success(response){
	          vm.embroidery = {};
	          Alertify.success("The embroidery has been updated successfully!");
          },
	      function error(errors){
        	  if (errors.status == 401 || errors.status == 403) {	    
	    		  Alertify.error("User Unathorized. You must login with user Manager!");
	    	  }else{
	    		  Alertify.error("The embroidery has not been updated successfully!");
	    	  }	
	      });
  }
	  
}