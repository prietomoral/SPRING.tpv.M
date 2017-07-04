tpv.controller('CreateCashierBalanceController', ['$location', 'Alertify', 'f09Service', 'existToday',

  function ($location, Alertify, f09Service, existToday) {
    "use strict";
    var vm = this;

    vm.completed = false;
    vm.error = false;

    vm.createCashierBalance = createCashierBalance;
    vm.cashier_balance = {
      "total_card": 0.0,
      "total_cash": 0.0,
      "total_change": 0.0,
      "total_check": 0.0,
      "total_sales": 0.0
    }
    vm.date = {
      "day": new Date().getDate(),
      "month": new Date().getMonth() + 1,
      "year": new Date().getFullYear()
    }

    function createCashierBalance () {
      f09Service.createCashierBalance(vm.cashier_balance).then(function (result) {
        vm.completed = true;
        $location.url('/feature09');
        Alertify.success('Cashier Balance Created');
      }, function (errors) {
        Alertify.error(errors);
      });
    }
  }
]);