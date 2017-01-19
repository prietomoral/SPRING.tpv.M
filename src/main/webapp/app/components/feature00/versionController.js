tpv.controller('versionController', ['$http', '$timeout', function ($http, $timeout) {
   "use strict";
    let self = this;
    self.completed = false;
    self.error = false;
    self.respuesta="";
  
    self.version = function () {
      const delay = 2000;
      let config = {
 	            method: 'GET',
 	            url: "http://localhost:8080/SPRING.tpv.1.0.0-SNAPSHOT/api/v0/admins",
	           };
      $http(config).
            then( function (response) {
                        self.completed = true;
                        self.respuesta =  response.data.version;
                        $timeout( function(){
                                    self.completed = false;
                                    }, delay)
                        },
                  function (response) {
                        self.error = true;
                        self.respuesta = "Error:"+response.data.error + ":" + response.data.description;
                        $timeout( function(){
                                    self.error = false;
                                    }, delay)}
                );
      }
}]);