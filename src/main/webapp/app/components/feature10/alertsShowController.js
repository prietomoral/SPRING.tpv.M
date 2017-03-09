
tpv.controller('AlertsShowController', ['AlertsService',
                                        '$routeParams' ,
                                        AlertsShowController]);

function AlertsShowController(AlertsService, $routeParams) {
  'use strict';
  var vm = this;

  init();

  function init(){
    AlertsService.getAlert($routeParams.id).then(function success(response){
      vm.alert = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
}
