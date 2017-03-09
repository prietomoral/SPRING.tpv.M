
tpv.controller('AlertsController', ['AlertsService', AlertsController]);

function AlertsController(AlertsService) {
  'use strict';
  var vm = this;

  init();

  function init(){
    return AlertsService.getAll().then(function success(response){
      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
}
