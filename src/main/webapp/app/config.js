var tpv = angular.module("tpv", ["ngRoute"]);

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
        .when("/feature03/list-embroidery", {
            templateUrl: "app/components/feature03/indexEmbroidery.html",
            controller: "ListController",
            controllerAs: "vm"
        })
        .when("/feature03/list-textile-printing", {
            templateUrl: "app/components/feature03/indexTextilePrinting.html",
            controller: "TextilePrintinController",
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
        .when("/feature10/:id", {
            templateUrl: "app/components/feature10/show.html",
            controller: "AlertsShowController",
            controllerAs: "vm"
        })
        .when("/feature10/new", {
            templateUrl: "app/components/feature10/new.html",
            controller: "AlertsNewController",
            controllerAs: "vm"
        })
        .when("/feature10/edit/:id", {
            templateUrl: "app/components/feature10/edit.html",
            controller: "AlertsEditController",
            controllerAs: "vm"
        })
        .when("/feature15", {
            templateUrl: "app/components/feature15/generatePDF.html",
            controller: "PDFGenerationController",
            controllerAs: "vm"
        })
        .when("/feature08", {
            templateUrl: "app/components/feature08/facturas.html",
            controller: "CreateInvoice",
            controllerAs: "vm"
        })
        .otherwise({
            redirectTo: '/'
        });

});
