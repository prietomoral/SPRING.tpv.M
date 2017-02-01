tpv.controller('loginController', function ($timeout,$scope,f00Service) {
   "use strict";
    $scope.completed = false;
    $scope.error = false;
    $scope.respuesta="";
  
	$scope.login = function () {
      const delay = 2000;
      f00Service.login($scope.mobile,$scope.password).then(
    	  function(result) {
    		// promise was fullfilled
    		$scope.completed = true;
    		$scope.response = result.token + ":" + result.rol;
    		sessionStorage.token = result.token;
    		sessionStorage.rol = result.rol;
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