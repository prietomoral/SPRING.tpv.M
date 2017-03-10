tpv.factory('f03TextilePrintingService', ['$http', '$q', f03TextilePrintingService]);

function f03TextilePrintingService($http, $q) {
	
	'use strict';
     const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
     var textilePrintingService = {
    		 listAllTextilePrinting: listAllTextilePrinting,
    		 addTextilePrinting: addTextilePrinting
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
     
     function addTextilePrinting(textilePrinting) {
   	    return $http({
   	      method: 'POST',
   	      url: urlBase + '/textileprinting',
   	      data:{
   	    	  'id': textilePrinting.id, 
   	    	  'description': textilePrinting.description, 
   	    	  'reference': textilePrinting.reference, 
   	    	  'retailPrice': textilePrinting.retailPrice, 
   	    	  'type': textilePrinting.type
   	    	  }
   	    }).then(function successCallback(response) {
   	        return response.data;
   	      }, function errorCallback(response) {
   	        return $q.reject(response);
   	      });
   	  }
}