tpv.controller('addTextilePrintingController', addTextilePrintingController, 'Alertify');

function addTextilePrintingController(f03Service, Alertify) {
  'use strict';
  var vm = this;
  vm.addTextilePrinting = addTextilePrinting;
 
  function addTextilePrinting(){
    f03Service.addTextilePrinting(vm.textilePrinting).then(
        function success(response){
	        vm.textilePrinting = {};
	        Alertify.success("The textile printing has been created successfully!");
        },
	    function error(errors){
	      Alertify.error("A product with this Id already exist");
	    });
  }
	  
}
