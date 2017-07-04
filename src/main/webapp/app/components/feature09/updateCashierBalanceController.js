tpv.controller('UpdateCashierBalanceController', ['$location', 'Alertify', 'f09Service', '$routeParams',

  function ($location, Alertify, f09Service, $routeParams) {
    "use strict";
    var vm = this;

    vm.completed = false;
    vm.error = false;
    vm.id = $routeParams.id;

    getCashierBalance(vm.id);

    vm.updateCashierBalance = updateCashierBalance;

    vm.date = {
      "day": new Date().getDate(),
      "month": new Date().getMonth() + 1,
      "year": new Date().getFullYear()
    }

    function updateCashierBalance () {
      f09Service.updateCashierBalance(vm.id, vm.cashier_balance).then(function (result) {
        vm.completed = true;
        $location.url('/feature09');
        Alertify.success('Cashier Balance Updated');
      }, function (errors) {
        Alertify.error(errors);
      });
    }

    function getCashierBalance (id) {
      f09Service.showCashierBalance(id).then(function (result) {
        vm.cashier_balance = {
          "total_card": result.totalCard,
          "total_cash": result.totalCash,
          "total_change": result.totalChange,
          "total_check": result.totalCheck,
          "total_sales": result.totalSales,
          "created_date": result.createdDate
        }
      })
    }
  }
]);