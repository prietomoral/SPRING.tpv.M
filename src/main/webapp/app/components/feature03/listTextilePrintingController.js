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
      console.log(errors);
    });
  }
  
  function deleteTextilePrinting(id){
	  f03Service.deleteOneTextilePrinting(id).then(function success(response){
		  Alertify.success("The textile printing has been deleted successfully.");
		  initList();
	  },
	  function error(errors){
		  Alertify.error("The textile printing has not been deleted successfully.");
     });
  }
  
}