tpv.controller('versionController', ['$http', '$timeout', function ($http, $timeout) {
   "use strict";
    let vm = this;
    vm.completed = false;
    vm.error = false;
    vm.response="";
  
    vm.version = function () {
      const delay = 2000;
      let config = {
 	            method: 'GET',
 	            url: "http://localhost:8080/SPRING.tpv.1.0.0-SNAPSHOT/api/v0/admins",
	           };
      $http(config).
            then( function (response) {
                        vm.completed = true;
                        vm.response =  response.data.version;
                        $timeout( function(){
                                    vm.completed = false;
                                    }, delay)
                        },
                  function (response) {
                        vm.error = true;
                        vm.response = "Error:"+response.data.error + ":" + response.data.description+"("+response.status+")";
                        $timeout( function(){
                                    vm.error = false;
                                    }, delay)}
                );
      }
}]);