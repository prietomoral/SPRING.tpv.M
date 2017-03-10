tpv.controller('listTextilePrintingController', ['f03Service', listTextilePrintingController]);

function listTextilePrintingController(f03Service) {
  'use strict';
  var vm = this;
  vm.initList = initList;

  function initList(){
	  f03Service.listAllTextilePrinting().then(function success(response){
      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
}