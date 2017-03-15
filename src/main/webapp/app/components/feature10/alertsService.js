
tpv.factory('AlertsService', ['$http', 'APP', '$q', AlertsService]);

function AlertsService($http, APP, $q) {
  'use strict';
  var alertService = {
    getAll: getAll,
    getAlert: getAlert,
    deletAlert: deletAlert,
    createAlert: createAlert,
    editAlert: editAlert,
    getAllActiveAlerts: getAllActiveAlerts

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

  function getAllActiveAlerts() {
    return $http({
      method: 'GET',
      url: APP.apiUrl + '/alerts/articles'
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

  function deletAlert(id) {
	    return $http({
	      method: 'DELETE',
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

    function editAlert(id, alert) {
        return $http({
          method: 'PUT',
          url: APP.apiUrl + '/alerts/' + id,
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
