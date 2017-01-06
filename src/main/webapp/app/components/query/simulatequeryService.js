tpv.service('simulatequeryService', ['$q','$timeout', function ($q, $timeout) {
     "use strict";
     let self = this;
    
     self.query = function (userTlf) {
         let time = Math.random() * 5;         
         let foundLimit = 3;
         let notfoundlimit = 4;
         let apromise = $q.defer();
         $timeout(function(){
             if (time < foundLimit){
                 apromise.resolve("Jesús Bernal");
                }
             else {
                 if (time < notfoundlimit){
                    apromise.resolve ("error");
                    }
                 else 
                     apromise.reject("ERROR en la conexión");
                 }
            }, time * 1000);
         return apromise.promise;
        }
}]);