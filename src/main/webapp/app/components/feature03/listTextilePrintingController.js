tpv.controller('listTextilePrintingController', listTextilePrintingController, 'Alertify');

function listTextilePrintingController(f03Service, Alertify) {
  'use strict';
  var vm = this;
  vm.initList = initList;
  vm.deleteTextilePrinting = deleteTextilePrinting;

  function initList(){
	  f03Service.listAllTextilePrinting().then(function success(response){
      vm.data = response;
    },
    function error(errors){
        if (errors.status == 401 || errors.status == 403) {	    
  		  Alertify.error("User Unathorized. You must login with user Manager!");
  	  }
    });
  }
  
  function deleteTextilePrinting(id){
	  f03Service.deleteOneTextilePrinting(id).then(function success(response){
		  Alertify.success("The textile printing has been deleted successfully.");
		  initList();
	  },
	  function error(errors){
	      if (errors.status == 401 || errors.status == 403) {	    
			  Alertify.error("User Unathorized. You must login with user Manager!");
		  }else{
			  Alertify.error("The textile printing has not been deleted successfully.");
		  }
     });
  }
  
}