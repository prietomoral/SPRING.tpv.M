
tpv.controller('AlertsNewController', AlertsNewController);

function AlertsNewController(AlertsService) {
  'use strict';
  var vm = this;
  vm.createAlert = createAlert;
  
 
  
  function createAlert(){
    AlertsService.createAlert(vm.alert).then(function success(response){
      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
	  
}
