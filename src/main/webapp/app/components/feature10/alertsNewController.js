
tpv.controller('AlertsNewController', ['AlertsService', 'Alertify', AlertsNewController]);

function AlertsNewController(AlertsService, Alertify) {
  'use strict';
  var vm = this;
  vm.createAlert = createAlert;

  function createAlert(){
    AlertsService.createAlert(vm.alert).then(function success(response){
      vm.data = response;
    },
    function error(errors){
      Alertify.error(errors.data.description);
    });
  }

}
