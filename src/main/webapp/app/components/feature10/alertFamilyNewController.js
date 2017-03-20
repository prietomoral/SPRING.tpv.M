
tpv.controller('AlertFamilyNewController',['AlertFamilyService',
                                       'alerts',
                                       '$location',
                                       'Alertify',
                                       AlertFamilyNewController]);

function AlertFamilyNewController(AlertFamilyService, alerts, $location, Alertify) {
  'use strict';
  var vm = this;
  vm.alerts = alerts;
  vm.saveAlert = saveAlert;

  function saveAlert(){
    vm.alertFamily.alerts = vm.selectedAlerts;
    AlertFamilyService.create(vm.alertFamily).then(function success(response){
      $location.url('/feature10/families');
      Alertify.success('Familia de alertas creada satisfactoriamente');
    },
    function error(errors){
      Alertify.error(errors.data.description);
    });
  }


}
