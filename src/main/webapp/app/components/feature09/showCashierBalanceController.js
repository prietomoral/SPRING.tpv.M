tpv.controller('ShowCashierBalanceController', ['$timeout', 'Alertify', 'f09Service', '$routeParams',

  function ($timeout, Alertify, f09Service, $routeParams) {
    "use strict";
    var vm = this;

    vm.completed = false;
    vm.error = false;

    vm.id = $routeParams.id;
    vm.showCashierBalance = showCashierBalance;
    showCashierBalance();

    function showCashierBalance () {
      f09Service.showCashierBalance(vm.id).then(function (result) {
        vm.completed = true;
        vm.data = result;
      }, function (errors) {
        Alertify.error(errors);
      });
    }
  }
]);