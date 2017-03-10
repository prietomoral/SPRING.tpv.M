tpv.controller('TextilePrintingController', ['f03TextilePrintingService', TextilePrintingController]);

function TextilePrintingController(f03TextilePrintingService) {
  'use strict';
  var vm = this;
  vm.initList = initList;

  function initList(){
	  f03TextilePrintingService.listAllTextilePrinting().then(function success(response){
      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
}