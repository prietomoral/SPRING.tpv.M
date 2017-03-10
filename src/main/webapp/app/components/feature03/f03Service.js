tpv.factory('f03Service', ['$http', '$q', f03Service]);

function f03Service($http, $q) {
	
	'use strict';
     const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
     var feature03Service = {
    		 listAllEmbroideries: listAllEmbroideries,
    		 listAllTextilePrinting: listAllTextilePrinting,
    		 createEmbroidery:createEmbroidery,
    		 addTextilePrinting: addTextilePrinting
     };
     
     return feature03Service;
     
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
          
     
     function createEmbroidery(embroidery) {
 	    return $http({
 	      method: 'POST',
 	      url: urlBase + '/embroideries',
 	      data:{'id': embroidery.id, 'reference':embroidery.reference , 'description': embroidery.description,'retailPrice':embroidery.price,'stitches':embroidery.stitches,'colors':embroidery.colors,'squareMillimeters':embroidery.millimiters}
 	    }).then(function successCallback(response) {
 	        return response.data;
 	      }, function errorCallback(response) {
 	        return $q.reject(response);
 	      });
 	  }
     
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