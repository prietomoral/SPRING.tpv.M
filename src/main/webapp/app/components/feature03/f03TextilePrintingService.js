tpv.factory('f03TextilePrintingService', ['$http', '$q', f03TextilePrintingService]);

function f03TextilePrintingService($http, $q) {
	
	'use strict';
     const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
     var textilePrintingService = {
    		 listAllTextilePrinting: listAllTextilePrinting
     };
     
     return textilePrintingService;
     
     function listAllTextilePrinting() {
    	 return $http({
    		 method: 'GET',
    		 url: urlBase + '/textileprinting'
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }
}