tpv.controller('ListCashierBalancesController', ['$timeout', 'Alertify', 'f09Service',

  function ($timeout, Alertify, f09Service) {
    "use strict";
    var vm = this;

    vm.completed = false;
    vm.error = false;
    vm.listCashierBalances = listCashierBalances;
    vm.isActualDate = isActualDate;
    listCashierBalances();

    function listCashierBalances () {
      f09Service.listCashierBalances().then(function (result) {
    	vm.completed = true;
    	vm.data = result.cashierBalanceWrappers;
      }, function (errors) {
    	Alertify.error(errors);
      });
    }

    function isActualDate (date) {
      return f09Service.isActualDate(date);
    }
  }
]);