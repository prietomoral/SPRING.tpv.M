tpv.controller('listEmbroideryController', listEmbroideryController, 'Alertify');

function listEmbroideryController(f03Service,Alertify) {
  'use strict';
  var vm = this;
  vm.initList = initList;
  vm.deleteEmbroidery = deleteEmbroidery;
 

  function initList(){
	  f03Service.listAllEmbroideries().then(function success(response){
      vm.data = response;
    },
    function error(errors){
    	if (errors.status == 401 || errors.status == 403) {	    
  		  Alertify.error("User Unathorized. You must login with user Manager!");
  	  }
    });
  }
 
  function deleteEmbroidery(id){
		  f03Service.deleteOneEmbroidery(id).then(function success(response){
			  Alertify.success("The embroidery has been deleted successfully.");
			  initList();
		  },
		  function error(errors){
			  if (errors.status == 401 || errors.status == 403) {	    
	    		  Alertify.error("User Unathorized. You must login with user Manager!");
	    	  }else{
	    		  Alertify.error("The embroidery has not been deleted successfully.");
	    	  }	
			 
	  });
   }
	  
}


	  
