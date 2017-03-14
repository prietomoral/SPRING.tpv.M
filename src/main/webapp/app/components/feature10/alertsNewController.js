
tpv.controller('AlertsNewController', ['AlertsService', 'Alertify', 'articles', '$location', AlertsNewController]);

function AlertsNewController(AlertsService, Alertify, articles, $location) {
  'use strict';
  var vm = this;
  vm.createAlert = createAlert;
  vm.articles = articles;

  function createAlert(){
    vm.alert.product_id = vm.alert.article.originalObject.id;
    AlertsService.createAlert(vm.alert).then(function success(response){
      $location.url('/feature10/' + response.id + '/show');
      Alertify.success('Alerta creada satisfactoriamente');
    },
    function error(errors){
      Alertify.error(errors.data.description);
    });
  }

}
