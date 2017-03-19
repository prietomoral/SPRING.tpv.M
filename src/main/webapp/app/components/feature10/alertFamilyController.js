tpv.controller('AlertFamilyController', ['AlertFamilyService', 'Alertify', AlertFamilyController]);

function AlertFamilyController(AlertFamilyService, Alertify) {
  'use strict';
  var vm = this;
  vm.popAlert = popAlert;
  init();

  function init(){
    AlertFamilyService.getAll().then(function success(response){
      vm.alertFamilies = response;
    });
  }

  function deleteAlert(id,index){
    AlertFamilyService.deleteAlertFamily(id).then(function success(response){
      vm.alertFamilies.splice(index,1);
      Alertify.success("La alerta se eliminó correctamente");
      },
      function error(errors){
        Alertify.error("No se pudo borrar la familia de alertas");
      });
  }

  function popAlert(id, index) {
    Alertify.confirm('Está seguro que desea eliminar?').then(
      function onOk() {
        deleteAlert(id,index);
      },
      function onCancel() {
      }
    );
  }
}
