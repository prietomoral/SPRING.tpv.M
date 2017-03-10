tpv.controller('addEmbroideryController', addEmbroideryController);


function addEmbroideryController(f03Service) {
  'use strict';
  var vm = this;
  vm.createEmbroidery = createEmbroidery;
  
 
  function createEmbroidery(){
	  f03Service.createEmbroidery(vm.embroidery).then(function success(response){
	      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
	  
}