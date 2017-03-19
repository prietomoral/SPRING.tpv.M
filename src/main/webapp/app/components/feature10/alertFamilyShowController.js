tpv.controller('AlertFamilyShowController', ['AlertFamilyService',
                                             '$routeParams',
                                             AlertFamilyShowController]);

function AlertFamilyShowController(AlertFamilyService, $routeParams) {
  'use strict';
  var vm = this;

  init();

  function init(){
    AlertFamilyService.getAlertFamily($routeParams.id).then(function success(response){
      vm.alertFamily = response;
    });
  }
}
