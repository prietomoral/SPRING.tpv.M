tpv.controller('versionController', function($timeout, $scope, f00Service) {
	"use strict";
	$scope.completed = false;
	$scope.error = false;
	$scope.response = "";

	$scope.version = function() {
		const delay = 2000;
		f00Service.version().then(
			function(result) {
				// promise was fullfilled
				$scope.completed = true;
				$scope.response = result.version;
				$timeout(function() {
					$scope.completed = false;
				}, delay)
			},function(errors) {
				// handle errors
				$scope.error = true;
				$scope.response = errors;
					$timeout(function() {
						$scope.error = false;
					}, delay)
			});
		}
	}
);