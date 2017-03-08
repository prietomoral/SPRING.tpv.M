
tpv.controller('AlertsController', ['AlertsService', AlertsController]);

function AlertsController(AlertsService) {
  'use strict';
  var vm = this;

  init();

  function init(){
    AlertsService.getAll().then(function success(response){

    },
    function error(errors){

    });
  }
}
