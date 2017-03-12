
tpv.factory('AlertsService', ['$http', 'APP', '$q', AlertsService]);

function AlertsService($http, APP, $q) {
  'use strict';
  var alertService = {
    getAll: getAll,
    getAlert: getAlert,
    createAlert: createAlert
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

  function getAlert(id) {
    return $http({
      method: 'GET',
      url: APP.apiUrl + '/alerts/' + id
    }).then(function successCallback(response) {
        return response.data;
      }, function errorCallback(response) {
        return $q.reject(response);
      });
  }
  function createAlert(alert) {
	    return $http({
	      method: 'POST',
	      url: APP.apiUrl + '/alerts',
	      data:{'warning': alert.warning,
              'critical': alert.critical,
              'product_id': alert.product_id}
	    }).then(function successCallback(response) {
	        return response.data;
	      }, function errorCallback(response) {
	        return $q.reject(response);
	      });
	  }
}
