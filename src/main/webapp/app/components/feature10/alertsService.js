
tpv.factory('AlertsService', ['$http', 'APP', '$q', AlertsService]);

function AlertsService($http, APP, $q) {
  'use strict';
  var alertService = {
    getAll: getAll
  };

  return alertService;

  function getAll() {
    return $http({
      method: 'GET',
      url: APP.apiUrl + '/alerts'
    }).then(function successCallback(response) {
        return response.data;
      }, function errorCallback(response) {
        return $q.reject(response);
      });
  }
}
