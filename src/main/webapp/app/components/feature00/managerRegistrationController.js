tpv.controller('managerRegistrationController', function ($timeout, $scope, f00Service) {
   "use strict";
    $scope.completed = false;
    $scope.error = false;
    $scope.respuesta="";
  
   
    $scope.managerRegistration = function () {
      const delay = 2000;
      
      f00Service.registration($scope.mobile, $scope.username, $scope.password).then(
        	  function(result) {
        		// promise was fullfilled
        		$scope.completed = true;
        		$scope.response = "";
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
    
});