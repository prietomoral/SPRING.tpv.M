var tpv = angular.module("tpv", ["ngRoute"]);

tpv.config(function ($routeProvider) {
    "use strict";
    $routeProvider
        .when("/", {
            templateUrl: "app/components/home/home.html"
        })
        .when("/feature00/version", {
            templateUrl: "app/components/feature00/version.html",
            controller: "versionController",
            controllerAs: "versionCtrl"
        })
        .when("/feature00/login", {
            templateUrl: "app/components/feature00/login.html",
            controller: "loginController",
            controllerAs: "loginCtrl"
        })
        .when("/feature00/manager-registration", {
            templateUrl: "app/components/feature00/managerRegistration.html",
            controller: "managerRegistrationController",
            controllerAs: "managerRegistrationCtrl"
        })
        .when("/feature00/customer-registration", {
            templateUrl: "app/components/feature00/customerRegistration.html",
            controller: "customerRegistrationController",
            controllerAs: "customerRegistrationCtrl"
        })
        .when("/feature00/delete-all", {
            templateUrl: "app/components/feature00/deleteAll.html",
            controller: "deleteAllController",
            controllerAs: "deleteAllCtrl"
        })
        .otherwise({
            redirectTo: '/'
        });

});
