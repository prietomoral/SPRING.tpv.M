tpv.controller('registrationController',
		function($timeout, $scope, f00Service) {
			"use strict";
			$scope.completed = false;
			$scope.error = false;
			$scope.respuesta = "";

			$scope.user = function() {
				if (sessionStorage.rol === "ADMIN")
					return "manager";
				else if (sessionStorage.rol === "MANAGER") {
					$scope.password="pass";
					return "customer";
				} else
					return "nobody";
			}

			$scope.registration = function() {
				const
				delay = 2000;

				f00Service.registration($scope.mobile, $scope.username,
						$scope.password, $scope.user()).then(function(result) {
					// promise was fullfilled
					$scope.completed = true;
					$scope.response = "";
					$timeout(function() {
						$scope.completed = false;
					}, delay)
				}, function(errors) {
					// handle errors
					$scope.error = true;
					$scope.response = errors;
					$timeout(function() {
						$scope.error = false;
					}, delay)
				});
			}

		});