tpv.service('f08Service', ['$http', '$q', function ($http, $q) {
   "use strict";
   
   const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
   
   var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}
   
   this.request = function(config) {
	      let deferred = $q.defer();
	      $http(config).then(function (response) {
	    	  deferred.resolve(response.data);
	      }, function (response){
	    	  let errorMsg;
	    	  if(response.data.error === undefined) {
	    		  errorMsg="";
	    	  }else{
	    		  errorMsg = response.data.description;
	    	  }
		    	  if (response.status == 401 || response.status == 403){
		    		  deferred.reject("ERROR: User Unathorized.\n Login as Manager or Admin")
		    	  }else{
		    		  deferred.reject("ERROR: "+ errorMsg );
		    	  }
	      });
	      return deferred.promise;	   
   }
   
   this.create = function (id_ticket){
	   let config = {
			   headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
			   method: 'POST',
			   url: urlBase+"/invoices",
			   data:{'id': id_ticket}
	   };
	  return this.request(config);
  }
   
    this.initList = function (){
    	let config = {
    		   headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
			   method: 'GET',
			   url: urlBase+"/invoices"
	   };
		  return this.request(config);
	  }
    
    this.getInvoiceById = function (id){
    	let config = {
       		   headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
			   method: 'GET',
			   url: urlBase+"/invoices/"+id
	   };
		  return this.request(config);
	  }
    
    this.getUserById = function (id){
    	let config = {
     		   headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
			   method: 'GET',
			   url: urlBase+"/users/"+id
	   };
		  return this.request(config);
	}
    
    this.search = function (ticket_id){
  	   let config = {
      		   headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
  			   method: 'POST',
  			   url: urlBase+"/invoices/search",
  			   data:{'id': ticket_id}
  	   };
  	  return this.request(config);
    }
    
    this.populate = function (){
 	   let config = {
 			   method: 'POST',
 			   url: urlBase+"/invoices/populate",
 	   };
 	  return this.request(config);
    }
    
    this.userList = function (){
    	let config = {
     		   headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
    		   method: 'GET',
			   url: urlBase+"/users"
	   };
		  return this.request(config);
	}
    
    this.modifyUser = function (user) {
    	let config = {
     		   headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
 			   method: 'PUT',
 			   url: urlBase+"/users",
 			   data:{
 				   'id': user.id, 
 				   'mobile':user.mobile, 
 				   'username': user.username,
 				   'email':user.email, 
 				   'dni': user.dni, 
 				   'address': user.address,
 				   'password': user.password    
 			   }
 	   };
 		  return this.request(config);
 	}
    
}]);