tpv.controller('AddTextilePrintingController', AddTextilePrintingController);

function AddTextilePrintingController(f03TextilePrintingService) {
  'use strict';
  var vm = this;
  vm.addTextilePrinting = addTextilePrinting;
 
  function addTextilePrinting(){
    f03TextilePrintingService.addTextilePrinting(vm.textilePrinting).then(function success(response){
      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
	  
}
