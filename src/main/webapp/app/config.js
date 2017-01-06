var tpv = angular.module("tpv", ["ngRoute"]);

tpv.config(function ($routeProvider) {
    "use strict";
    $routeProvider
        .when("/", {
            templateUrl: "app/components/home/home.html"
        })
        .when("/registration", {
            templateUrl: "app/components/registration/registration.html",
            controller: "registrationController",
            controllerAs: "regCtrl"
        })
        .when("/noServregistration", {
            templateUrl: "app/components/noServregistration/noServregistration.html",
            controller: "noServregistrationController",
            controllerAs: "nSregCtrl"
        })    
        .when("/query", {
            templateUrl: "app/components/query/query.html",
            controller: "simulatequeryController",
            controllerAs: "qryCtrl"      
        })
        .otherwise({
            redirectTo: '/'
        });

});
