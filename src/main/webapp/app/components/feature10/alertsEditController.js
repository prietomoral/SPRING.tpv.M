
tpv.controller('AlertsEditController',['AlertsService',
                                       '$routeParams',
                                       'articles',
                                       '$location',
                                       'Alertify',
                                       AlertsEditController]);

function AlertsEditController(AlertsService, $routeParams, articles, $location, Alertify) {
  'use strict';
  var vm = this;
  vm.articles = articles;
  vm.editAlert = editAlert;

  init();

  function init(){
    AlertsService.getAlert($routeParams.id).then(function success(response){
      vm.alert = response;
      vm.alert.article = setSelectedArticle();
    },
    function error(errors){
      console.log(errors);
    });
  }

  function editAlert(){
    vm.alert.product_id = vm.alert.article.originalObject.id;
    AlertsService.editAlert($routeParams.id, vm.alert).then(function success(response){
      $location.url('/feature10/' + $routeParams.id + '/show');
      Alertify.success('Alerta actualizada satisfactoriamente');
    },
    function error(errors){
      Alertify.error(errors.data.description);
    });
  }

  function setSelectedArticle(){
    var article = vm.articles.filter(function(row){
      return row.id === vm.alert.product_id
    });
    return article[0];
  }
}
