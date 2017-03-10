tpv.factory('f03Service', ['$http', '$q', f03Service]);

function f03Service($http, $q) {
	
	'use strict';
     const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
     var embroideryService = {
    		 listAllEmbroideries: listAllEmbroideries
     };
     
     return embroideryService;
     
     function listAllEmbroideries() {
    	 return $http({
    		 method: 'GET',
    		 url: urlBase + '/embroideries'
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }
}