tpv.controller('loginController', ['$http', '$timeout', function ($http, $timeout) {
   "use strict";
    let self = this;
    self.completed = false;
    self.error = false;
    self.respuesta="";
  
    self.login = function () {
      const delay = 2000;
      let config = {
 	            method: 'POST',
 	            url: "http://localhost:8080/SPRING.tpv.1.0.0-SNAPSHOT/api/v0/tokens",
 	           headers: {'micabecera': "unvalordecabecera"},
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