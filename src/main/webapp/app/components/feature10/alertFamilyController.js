tpv.controller('AlertFamilyController', ['AlertFamilyService', AlertFamilyController]);

function AlertFamilyController(AlertFamilyService) {
  'use strict';
  var vm = this;

  init();

  function init(){
    AlertFamilyService.getAll().then(function success(response){
      vm.alertFamilies = response;
    });
  }
}
