tpv.controller('listEmbroideryController', listEmbroideryController,'Alertify');

function listEmbroideryController(f03Service,Alertify) {
  'use strict';
  var vm = this;
  vm.initList = initList;
  vm.deleteEmbroidery = deleteEmbroidery;
 

  function initList(){
	  f03Service.listAllEmbroideries().then(function success(response){
      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
  
  
  
  function deleteEmbroidery(id){
	  f03Service.deleteOneEmbroidery(id).then(function success(response){
		  Alertify.success("Deleted Embroidery");
		  initList();
	  },
	  function error(errors){
		  Alertify.error("Not exist Embroidery");
  });
}
	  
}


	  
