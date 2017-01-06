tpv.service('registrationService', ['$http','$q', function ($http, $q) {
    "use strict";
    let self = this;
     self.register = function (userName, userTlf) {
        let config = {
                  method: 'GET',
                  url: "http://salonso.etsisi.upm.es/miw_serv/padel/username.php",
                  params: { username: userName }
                 };       
          let apromise = $q.defer();
          $http(config).then(
            (response) => { apromise.resolve(response.data.errorMessage);},
            (response) => { apromise.reject(response.data.errorMessage);}
          );
        return apromise.promise;
    }
}]);