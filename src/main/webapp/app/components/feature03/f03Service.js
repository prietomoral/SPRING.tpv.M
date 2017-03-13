tpv.factory('f03Service', ['$http', '$q', f03Service]);

function f03Service($http, $q) {

	'use strict';
     const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
     var feature03Service = {
    		 listAllEmbroideries: listAllEmbroideries,
    		 listAllTextilePrinting: listAllTextilePrinting,
    		 listAllArticles: listAllArticles,
    		 createEmbroidery:createEmbroidery,
    		 addTextilePrinting: addTextilePrinting,
    		 deleteOneEmbroidery:deleteOneEmbroidery,
    		 deleteOneTextilePrinting: deleteOneTextilePrinting,
    		 findOneEmbroidery:findOneEmbroidery,
    		 updateEmbroidery:updateEmbroidery,
    		 listAllArticles:listAllArticles
     };

     return feature03Service;
     
     function listAllArticles() {
    	 return $http({
    		 method: 'GET',
    		 url: urlBase + '/articles'
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }
     
     

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
     
     function findOneEmbroidery(id) {
    	 return $http({
    		 method: 'GET',
    		 url: urlBase + '/embroideries/'+id
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
 	      data:{
 	    	  'id': embroidery.id, 
 	    	  'reference':embroidery.reference, 
 	    	  'description': embroidery.description,
 	    	  'retailPrice':embroidery.price,
 	    	  'stitches':embroidery.stitches,
 	    	  'colors':embroidery.colors,
 	    	  'squareMillimeters':embroidery.millimiters
 	    	  }
 	    }).then(function successCallback(response) {
 	        return response.data;
 	      }, function errorCallback(response) {
 	        return $q.reject(response);
 	      });
 	  }
     
     
     function updateEmbroidery(embroidery) {
  	    return $http({
  	      method: 'PUT',
  	      url: urlBase + '/embroideries',
  	      data:{
  	    	'id': embroidery.id,
  	    	 'reference':embroidery.reference, 
  	    	 'description': embroidery.description,
  	    	 'retailPrice':embroidery.retailPrice,
  	    	 'stitches':embroidery.stitches,
  	    	 'colors':embroidery.colors,
  	    	 'squareMillimeters':embroidery.squareMillimeters
  	    	  }
  	    }).then(function successCallback(response) {
  	        return response.data;
  	      }, function errorCallback(response) {
  	        return $q.reject(response);
  	      });
  	  }
     
     

     
     function deleteOneEmbroidery(id) {
    	 return $http({
    		 method: 'DELETE',
    		 url: urlBase + '/embroideries/'+id
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }
        
     
     function listAllTextilePrinting() {
    	 return $http({
    		 method: 'GET',
    		 url: urlBase + '/textileprintings'
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }

     function addTextilePrinting(textilePrinting) {
   	    return $http({
   	      method: 'POST',
   	      url: urlBase + '/textileprintings',
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
     
     function deleteOneTextilePrinting(id) {
    	 return $http({
    		 method: 'DELETE',
    		 url: urlBase + '/textileprintings/'+id
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }

	 function listAllArticles() {
		return $http({
			method: 'GET',
			url: urlBase + '/articles'
		}).then(function successCallback(response) {
			return response.data;
		}, function errorCallback(response) {
			return $q.reject(response);
		});
	 }
}
