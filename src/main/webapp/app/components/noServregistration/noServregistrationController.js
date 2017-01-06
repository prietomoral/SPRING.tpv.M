tpv.controller('noServregistrationController', ['$http', '$timeout', function ($http, $timeout) {
   "use strict";
    let self = this;
    self.registerCompleted = false;
    self.registerError = false;
  
    self.register = function (userName, userTelf) {
      const delay = 2000;
      let config = {
 	            method: 'GET',
 	            url: "http://salonso.etsisi.upm.es/miw_serv/padel/username.php",
 	            params: { username: userName }
	           };
      $http(config).
            then( function (response) {
                        self.registerCompleted = true;
                        $timeout( function(){
                                    self.registerCompleted = false;
                                    }, delay)
                        },
                  function (response) {
                        self.registerError = true;
                        $timeout( function(){
                                    self.registerError = false;
                                    }, delay)}
                );
      }
}]);