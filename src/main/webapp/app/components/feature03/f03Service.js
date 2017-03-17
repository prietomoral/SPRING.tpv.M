tpv.factory('f03Service', ['$http', '$q', f03Service]);

function f03Service($http, $q) {

	'use strict';
     const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
     var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}

     var feature03Service = {
    		 listAllEmbroideries: listAllEmbroideries,
    		 listAllTextilePrinting: listAllTextilePrinting,
    		 listAllArticles: listAllArticles,
    		 createEmbroidery:createEmbroidery,
    		 addTextilePrinting: addTextilePrinting,
    		 deleteOneEmbroidery: deleteOneEmbroidery,
    		 deleteOneTextilePrinting: deleteOneTextilePrinting,
    		 findOneEmbroidery: findOneEmbroidery,
    		 updateEmbroidery: updateEmbroidery,
    		 listAllArticles: listAllArticles,
    		 deleteOneArticle: deleteOneArticle,
    		 createArticle: createArticle,
    		 editTextilePrinting: editTextilePrinting,
    		 findOneTextilePrinting: findOneTextilePrinting,
    		 findOneArticle: findOneArticle,
    		 updateArticle: updateArticle
     };

     return feature03Service;

     function listAllArticles() {
    	 return $http({
    		 headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
    		 method: 'GET',
    		 url: urlBase + '/articles'
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }


     function findOneArticle(id) {
    	 return $http({
    		 headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
    		 method: 'GET',
    		 url: urlBase + '/articles/'+id
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }

     function createArticle(article) {
  	    return $http({
  	      headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
  	      method: 'POST',
  	      url: urlBase + '/articles',
  	      data:{
  	    	  'id': article.id,
  	    	  'reference': article.reference,
  	    	  'description': article.description,
  	    	  'retailPrice': article.retailPrice,
  	    	  'wholesalePrice': article.wholesalePrice,
  	    	  'providerId': article.providerId
  	    	  }
  	    }).then(function successCallback(response) {
  	        return response.data;
  	      }, function errorCallback(response) {
  	        return $q.reject(response);
  	      });
  	  }

     function deleteOneArticle(id) {
    	 return $http({
    		 headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
    		 method: 'DELETE',
    		 url: urlBase + '/articles/'+id
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }


     function updateArticle(article) {
   	    return $http({
   	      headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
   	      method: 'PUT',
   	      url: urlBase + '/articles',
   	      data:{
   	    	  'id': article.id,
	    	  'reference': article.reference,
	    	  'description': article.description,
	    	  'retailPrice': article.retailPrice,
	    	  'wholesalePrice': article.wholesalePrice,
	    	  'stock': article.stock,
	    	  'providerId': article.providerId
   	    	  }
   	    }).then(function successCallback(response) {
   	        return response.data;
   	      }, function errorCallback(response) {
   	        return $q.reject(response);
   	      });
   	  }


     function listAllEmbroideries() {
    	 return $http({
    		 headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
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
    		 headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
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
 	      headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
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
  	    	headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
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
    		 headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
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
    		 headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
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
   	      headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
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
    		 headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
    		 method: 'DELETE',
    		 url: urlBase + '/textileprintings/'+id
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }

     function editTextilePrinting(textilePrinting) {
   	    return $http({
   	      headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
   	      method: 'PUT',
   	      url: urlBase + '/textileprintings/',
   	      data:{
   	    	'id': textilePrinting.id,
   	    	 'reference':textilePrinting.reference,
   	    	 'description': textilePrinting.description,
   	    	 'retailPrice': textilePrinting.retailPrice,
   	    	 'type': textilePrinting.type
   	    	  }
   	    }).then(function successCallback(response) {
   	        return response.data;
   	      }, function errorCallback(response) {
   	        return $q.reject(response);
   	      });
   	  }

     function findOneTextilePrinting(id) {
    	 return $http({
    		 headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
    		 method: 'GET',
    		 url: urlBase + '/textileprintings/'+id
    	 }).then(function successCallback(response) {
    		 return response.data;
    	 }, function errorCallback(response) {
    		 return $q.reject(response);
    	 });
     }
}
