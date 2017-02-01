tpv.service('feature00Service', ['$http','$q', function ($http, $q) {
    "use strict";
    let self = this;
     self.version = function () {
        let config = {
                  method: 'GET',
                  url: "http://localhost:8080/SPRING.tpv.1.0.0-SNAPSHOT/api/v0/admins"
                 };       
          let apromise = $q.defer();
          $http(config).then(
            (response) => { apromise.resolve(response.data.errorMessage);},
            (response) => { apromise.reject(response.data.errorMessage);}
          );
        return apromise.promise;
    }
}]);