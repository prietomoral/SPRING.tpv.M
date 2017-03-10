tpv.controller('AddTextilePrintingController', AddTextilePrintingController);

function AddTextilePrintingController(f03Service) {
  'use strict';
  var vm = this;
  vm.addTextilePrinting = addTextilePrinting;
 
  function addTextilePrinting(){
    f03Service.addTextilePrinting(vm.textilePrinting).then(function success(response){
      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
	  
}
