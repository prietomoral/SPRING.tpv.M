tpv.controller('addTextilPrintingController', addTextilPrintingController);

function addTextilPrintingController(f03TextilPrintingService) {
  'use strict';
  var vm = this;
  vm.addTextilPrinting = addTextilPrinting;
 
  function addTextilPrinting(){
    f03TextilPrintingService.addTextilPrinting(vm.textilPrinting).then(function success(response){
      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
	  
}
