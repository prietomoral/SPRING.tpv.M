
tpv.controller('AlertFamilyEditController',['AlertFamilyService',
                                       '$routeParams',
                                       'alerts',
                                       '$location',
                                       'Alertify',
                                       AlertFamilyEditController]);

function AlertFamilyEditController(AlertFamilyService, $routeParams, alerts, $location, Alertify) {
  'use strict';
  var vm = this;
  vm.alerts = alerts;
  vm.editAlert = editAlert;

  init();

  function init(){
    AlertFamilyService.getAlertFamily($routeParams.id).then(function success(response){
      vm.alertFamily = response;
    },
    function error(errors){
      console.log(errors);
    });
  }

  function editAlert(){
    vm.alertFamily.alerts = vm.selectedAlerts;
    AlertFamilyService.edit($routeParams.id, vm.alertFamily).then(function success(response){
      $location.url('/feature10/families/' + $routeParams.id + '/show');
      Alertify.success('Familia de alertas actualizada satisfactoriamente');
    },
    function error(errors){
      Alertify.error(errors.data.description);
    });
  }


}
