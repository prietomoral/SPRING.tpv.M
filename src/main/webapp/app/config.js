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
        .otherwise({
            redirectTo: '/'
        });

});
