tpv.controller('registrationController', ['$timeout', 'registrationService',   
                function ($timeout, registrationService) {
   "use strict";
    let self = this;
    self.registerCompleted = false;
    self.registerError = false;
  
    self.register = function (userName, userTelf) {
      const delay = 2000;
      let responsePromise = registrationService.register(userName, userTelf);
      responsePromise.
            then( function (response) {
                    if (response === "no error"){
                        self.registerCompleted = true;
                        $timeout( function(){
                                    self.registerCompleted = false;
                                    }, delay)
                        }
                    else{
                         self.registerError = true;
                         $timeout( function(){
                                    self.registerError = false;
                                    }, delay)
                        }
                    },
                  function (response) {
                        self.registerError = true;
                        $timeout( function(){
                                    self.registerError = false;
                                    }, delay)
                        }
                );
      }
}]);