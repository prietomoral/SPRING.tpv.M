tpv.controller('addEmbroideryController', addEmbroideryController,'Alertify');


function addEmbroideryController(f03Service,Alertify) {
  'use strict';
  var vm = this;
  vm.createEmbroidery = createEmbroidery;
 
  function createEmbroidery(){
	  f03Service.createEmbroidery(vm.embroidery).then(
	      function success(response){
	          vm.embroidery = {};
	          Alertify.success("The embroidery has been created successfully!");
          },
	      function error(errors){
	          Alertify.error("This embroidery already exist");
	      });
  }
	  
}