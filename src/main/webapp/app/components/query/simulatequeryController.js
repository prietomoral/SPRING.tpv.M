tpv.controller('simulatequeryController', ['$timeout', 'simulatequeryService', function ($timeout, simulatequeryService) {
   "use strict";
    let self = this;
    self.queryError = false;
    self.queryWait = false;
  
    self.userQuery = function (userTelf) {
      const delay = 2000;
      self.usuario = '';
      self.queryError = false;
      self.queryWait = true;    
      let responsePromise = simulatequeryService.query(userTelf);
      responsePromise.
            then( function (response) {
                    self.queryWait = false;
                    if (response !== "error"){
                        self.usuario = response;
                    }
                    else{
                         self.queryError = true;
                         $timeout( function(){
                                    self.queryError = false;
                                    }, delay)
                        }
                    },
                 function (response){
                    self.queryWait = false;
                    self.usuario = response;
                    }
                );
      }
}]);