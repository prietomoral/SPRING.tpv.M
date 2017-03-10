tpv.controller('EmbroideryController', ['f03Service', EmbroideryController]);

function EmbroideryController(f03Service) {
  'use strict';
  var vm = this;
  vm.initList = initList;

  function initList(){
	  f03Service.listAllEmbroideries().then(function success(response){
      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
}