var tpv = angular.module("tpv", ["ngRoute",
                                 'bw.paging',
                                 "Alertify",
                                 "angucomplete-alt",
                                 "ngMessages",
                                 "checklist-model"]);

tpv.config(function ($routeProvider) {
    "use strict";
    $routeProvider
        .when("/", {
            templateUrl: "app/components/home/home.html"
        })
        .when("/feature00/version", {
            templateUrl: "app/components/feature00/version.html",
            controller: "VersionController",
            controllerAs: "vm"
        })
        .when("/feature00/login", {
            templateUrl: "app/components/feature00/login.html",
            controller: "LoginController",
            controllerAs: "vm"
        })
        .when("/feature00/registration", {
            templateUrl: "app/components/feature00/registration.html",
            controller: "RegistrationController",
            controllerAs: "vm"
        })
        .when("/feature00/delete-all", {
            templateUrl: "app/components/feature00/deleteAll.html",
            controller: "DeleteAllController",
            controllerAs: "vm"
        })
        .when("/feature02/create-provider", {
            templateUrl: "app/components/feature02/createProvider.html",
            controller: "CreateProviderController",
            controllerAs: "vm"
        })
        .when("/feature02/list-providers", {
            templateUrl: "app/components/feature02/listProviders.html",
            controller: "ListProviderController",
            controllerAs: "vm"
        })
        .when("/feature02/list-id-company-providers", {
            templateUrl: "app/components/feature02/listIdCompanyProviders.html",
            controller: "ListIdCompanyProviderController",
            controllerAs: "vm"
        })
        .when("/feature02/edit-provider/:idProvider", {
            templateUrl: "app/components/feature02/editProvider.html",
            controller: "EditProviderController",
            controllerAs: "vm"
        })
        .when("/feature03/list-embroidery", {
            templateUrl: "app/components/feature03/indexEmbroidery.html",
            controller: "listEmbroideryController",
            controllerAs: "vm"
        })
        .when("/feature03/new-embroidery", {
            templateUrl: "app/components/feature03/newEmbroidery.html",
            controller: "addEmbroideryController",
            controllerAs: "vm"
        })
        .when("/feature03/update-embroidery/:idEmbroidery", {
            templateUrl: "app/components/feature03/updateEmbroidery.html",
            controller: "updateEmbroideryController",
            controllerAs: "vm"
        })
        .when("/feature03/update-textile-printing/:idTextilePrinting", {
            templateUrl: "app/components/feature03/updateTextilePrinting.html",
            controller: "updateTextilePrintingController",
            controllerAs: "vm"
        })
        .when("/feature03/list-textile-printing", {
            templateUrl: "app/components/feature03/indexTextilePrinting.html",
            controller: "listTextilePrintingController",
            controllerAs: "vm"
        })
        .when("/feature03/create-textile-printing", {
            templateUrl: "app/components/feature03/newTextilePrinting.html",
            controller: "addTextilePrintingController",
            controllerAs: "vm"
        })
        .when("/feature03/list-articles", {
            templateUrl: "app/components/feature03/indexArticle.html",
            controller: "listArticleController",
            controllerAs: "vm"
        })
        .when("/feature03/create-article", {
            templateUrl: "app/components/feature03/newArticle.html",
            controller: "addArticleController",
            controllerAs: "vm"
        })
        .when("/feature03/update-article/:idArticle", {
            templateUrl: "app/components/feature03/updateArticle.html",
            controller: "updateArticleController",
            controllerAs: "vm"
        })
        .when("/feature04/search-articles", {
            templateUrl: "app/components/feature04/searchArticles.html",
            controller: "SearchArticlesController",
            controllerAs: "vm"
        })
        .when("/feature04/search-embroideries", {
            templateUrl: "app/components/feature04/searchEmbroideries.html",
            controller: "SearchEmbroideriesController",
            controllerAs: "vm"
        })
        .when("/feature04/search-textile-printings", {
            templateUrl: "app/components/feature04/searchTextilePrintings.html",
            controller: "SearchTextilePrintingsController",
            controllerAs: "vm"
        })
        .when("/feature07/create-voucher", {
            templateUrl: "app/components/feature07/createVoucher.html",
            controller: "CreateVoucherController",
            controllerAs: "vm",
            resolve: {
                notAutorized: checkAuthVouchers
              }
        })
        .when("/feature07/list-vouchers", {
            templateUrl: "app/components/feature07/listVouchers.html",
            controller: "ListVouchersController",
            controllerAs: "vm"
        })
        .when("/feature07/use-voucher", {
            templateUrl: "app/components/feature07/useVoucher.html",
            controller: "UseVoucherController",
            controllerAs: "vm",
            resolve: {
                notAutorized: checkAuthVouchers
              }
        })
        .when("/feature10", {
            templateUrl: "app/components/feature10/index.html",
            controller: "AlertsController",
            controllerAs: "vm",
            resolve: {
              notAutorized: resolverAlerts
            }
        })
        .when("/feature10/:id/show", {
            templateUrl: "app/components/feature10/show.html",
            controller: "AlertsShowController",
            controllerAs: "vm",
            resolve: {
              notAutorized: resolverAlerts
            }
        })
        .when("/feature10/new", {
            templateUrl: "app/components/feature10/new.html",
            controller: "AlertsNewController",
            controllerAs: "vm",
            resolve: {
              notAutorized: resolverAlerts,
              articles: function(f03Service, $location, Alertify){
                return f03Service.listAllArticles().then(function success(response){
                  if (response.length === 0) {
                    $location.url('/feature10');
                    Alertify.log('No existen artículos.\nNecesitas crear uno antes de crear alertas');
                  }else{
                    return response;
                  }
                });
              }
            }
        })
        .when("/feature10/:id/edit", {
            templateUrl: "app/components/feature10/edit.html",
            controller: "AlertsEditController",
            controllerAs: "vm",
            resolve: {
              notAutorized: resolverAlerts,
              articles: function(f03Service, $location, Alertify){
                return f03Service.listAllArticles().then(function success(response){
                  if (response.length === 0) {
                    $location.url('/feature10');
                    Alertify.log('No existen artículos.\nNecesitas crear uno antes de crear alertas');
                  }else{
                    return response;
                  }
                });
              }
            }
        })
        .when("/feature10/families", {
            templateUrl: "app/components/feature10/index-families.html",
            controller: "AlertFamilyController",
            controllerAs: "vm",
            resolve: {
              notAutorized: resolverAlerts
            }
        })
        .when("/feature10/families/new", {
            templateUrl: "app/components/feature10/new-family.html",
            controller: "AlertFamilyNewController",
            controllerAs: "vm",
            resolve: {
              notAutorized: resolverAlerts,
              alerts: function(AlertsService, $location, Alertify){
                return AlertsService.getAll().then(function success(response){
                  if (response.length === 0) {
                    $location.url('/feature10');
                    Alertify.log('No existen alertas.\nNecesitas crear una antes de crear una familia');
                  }else{
                    return response;
                  }
                });
              }
            }
        })
        .when("/feature10/families/:id/show", {
            templateUrl: "app/components/feature10/show-family.html",
            controller: "AlertFamilyShowController",
            controllerAs: "vm",
            resolve: {
              notAutorized: resolverAlerts
            }
        })
        .when("/feature10/families/:id/edit", {
            templateUrl: "app/components/feature10/edit-family.html",
            controller: "AlertFamilyEditController",
            controllerAs: "vm",
            resolve: {
              notAutorized: resolverAlerts,
              alerts: function(AlertsService, $location, Alertify){
                return AlertsService.getAll().then(function success(response){
                  if (response.length === 0) {
                    $location.url('/feature10');
                    Alertify.log('No existen alertas.\nNecesitas crear una antes de crear una familia');
                  }else{
                    return response;
                  }
                });
              }
            }
        })
        .when("/feature12",{
        	templateUrl: "app/components/feature12/seedDatabase.html",
            controller: "SeedController",
            controllerAs: "vm"
        })
        .when("/feature15/test", {
            templateUrl: "app/components/feature15/pdfGeneration.html",
            controller: "PdfGenerationController",
            controllerAs: "vm"
        })
        .when("/feature15/invoices", {
            templateUrl: "app/components/feature15/invoicePdfGeneration.html",
            controller: "InvoicePdfGenerationController",
            controllerAs: "vm"
        })
        .when("/feature15/tickets", {
            templateUrl: "app/components/feature15/ticketPdfGeneration.html",
            controller: "TicketPdfGenerationController",
            controllerAs: "vm"
        })
        .when("/feature08/create-invoice", {
            templateUrl: "app/components/feature08/create.html",
            controller: "CreateInvoiceController",
            controllerAs: "vm"
        })
        .when("/feature08/list-invoices", {
            templateUrl: "app/components/feature08/list.html",
            controller: "ListInvoicesController",
            controllerAs: "vm"
        })
        .when("/feature08/details-invoice/:idInvoice", {
            templateUrl: "app/components/feature08/details.html",
            controller: "DetailsInvoiceController",
            controllerAs: "vm"
        })
        .when("/feature08/populate", {
            templateUrl: "app/components/feature08/populate.html",
            controller: "PopulateController",
            controllerAs: "vm"
        })
        .when("/feature08/list-users", {
            templateUrl: "app/components/feature08/userList.html",
            controller: "UserListController",
            controllerAs: "vm"
        })
        .when("/feature08/modify-user/:idUser", {
            templateUrl: "app/components/feature08/userModify.html",
            controller: "UserModifyController",
            controllerAs: "vm"
        })
        .when("/feature14/popular",{
        	templateUrl: "app/components/feature14/popularProducts.html",
            controller: "PopularProductsController",
            controllerAs: "vm"
        })
        .when("/feature14/productByDate",{
        	templateUrl: "app/components/feature14/productByDate.html",
            controller: "ProductByDateController",
            controllerAs: "vm"
        })
        .when("/feature13",{
        	templateUrl: "app/components/feature13/jobIndex.html",
            controller: "JobController",
            controllerAs: "vm"
        })
        .when("/feature09/", {
            templateUrl: "app/components/feature09/listCashierBalances.html",
            controller: "ListCashierBalancesController",
            controllerAs: "vm",
            resolve: {
              notAutorized: resolveCashierBalances
            }
        })
        .when("/feature09/:id/show", {
            templateUrl: "app/components/feature09/showCashierBalance.html",
            controller: "ShowCashierBalanceController",
            controllerAs: "vm",
            resolve: {
              notAutorized: resolveCashierBalances
            }
        })
        .when("/feature09/new", {
          templateUrl: "app/components/feature09/newCashierBalance.html",
          controller: "CreateCashierBalanceController",
          controllerAs: "vm",
          resolve: {
            notAutorized: resolveCashierBalances,
            existToday: function(f09Service, $location, Alertify){
              return f09Service.existsTodayCashierBalance().then(function success(response){
                if (response) {
                  $location.url('/feature09');
                  Alertify.log('A Cashier Balance has already been created for today.');
                }
              });
            }
          }
        })
        .when("/feature09/:id/update", {
          templateUrl: "app/components/feature09/updateCashierBalance.html",
          controller: "UpdateCashierBalanceController",
          controllerAs: "vm",
          resolve: {
            notAutorized: resolveCashierBalances
          }
        })
        .otherwise({
            redirectTo: '/'
        });

    function checkAuthVouchers($window, $location, Alertify){
        var role = $window.sessionStorage.getItem('rol');
        if (!role || role !== "MANAGER" || role !== "OPERATOR") {
          $location.url('/feature00/login');
          Alertify.error('You must be logged as Manager or Operator to create or used a Voucher.');
        }
      }

        function resolverAlerts($window, $location, Alertify){
          var rol = $window.sessionStorage.getItem('rol');
          if (!rol || rol !== "MANAGER" ) {
            $location.url('/feature00/login');
            Alertify.error('Debes iniciar sesión como Mánager para acceder a Alertas');
          }
        }

      function resolveCashierBalances($window, $location, Alertify){
          var rol = $window.sessionStorage.getItem('rol');
          if (!rol || (rol !== "MANAGER" && rol !== "OPERATOR")) {
            $location.url('/feature00/login');
            Alertify.error('You must be logged as Manager or Operator to list, show, create or update a Cashier Balance');
          }
        }

});

tpv.config(['$httpProvider',
  function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
  }
]);
