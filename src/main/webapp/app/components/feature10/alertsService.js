
tpv.factory('AlertsService', ['$http', AlertsService]);

function AlertsService($http) {
  'use strict';
  var alertService = {
    getAll: getAll
  };

  return alertService;

  var apiUrl="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";

  function getAll() {
    return $http({
      method: 'GET',
      url: apiUrl + '/alerts'
    }).then(function successCallback(response) {
        // return response.data;
        return [];
      }, function errorCallback(response) {
        // return response.errors;
        return [];
      });
  }
}
