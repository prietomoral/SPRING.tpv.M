var tpv = angular.module("tpv", ["ngRoute", 'bw.paging', "Alertify"]);

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
        .when("/feature03/list-textile-printing", {
            templateUrl: "app/components/feature03/indexTextilePrinting.html",
            controller: "listTextilePrintingController",
            controllerAs: "vm"
        })
        .when("/feature03/create-textile-printing", {
            templateUrl: "app/components/feature03/newTextilePrinting.html",
            controller: "AddTextilePrintingController",
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
            controllerAs: "vm"
        })
        .when("/feature10", {
            templateUrl: "app/components/feature10/index.html",
            controller: "AlertsController",
            controllerAs: "vm"
        })
        .when("/feature10/:id/show", {
            templateUrl: "app/components/feature10/show.html",
            controller: "AlertsShowController",
            controllerAs: "vm"
        })
        .when("/feature10/new", {
            templateUrl: "app/components/feature10/new.html",
            controller: "AlertsNewController",
            controllerAs: "vm"
        })
        .when("/feature10/:id/edit", {
            templateUrl: "app/components/feature10/edit.html",
            controller: "AlertsEditController",
            controllerAs: "vm"
        })
        .when("/feature15", {
            templateUrl: "app/components/feature15/generatePDF.html",
            controller: "PDFGenerationController",
            controllerAs: "vm"
        })
        .when("/feature08/create-invoice", {
            templateUrl: "app/components/feature08/create.html",
            controller: "CreateInvoiceController",
            controllerAs: "vm"
        })
        .otherwise({
            redirectTo: '/'
        });

});
