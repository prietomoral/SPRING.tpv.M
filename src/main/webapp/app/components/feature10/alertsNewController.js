
tpv.controller('AlertsNewController', ['AlertsService', 'Alertify', 'articles', AlertsNewController]);

function AlertsNewController(AlertsService, Alertify, articles) {
  'use strict';
  var vm = this;
  vm.createAlert = createAlert;
  vm.articles = articles;

  function createAlert(){
    vm.alert.product_id = vm.alert.article.originalObject.id;
    AlertsService.createAlert(vm.alert).then(function success(response){
      //TODO go to /show state
      vm.data = response;
    },
    function error(errors){
      Alertify.error(errors.data.description);
    });
  }

}
