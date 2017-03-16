tpv.service('f08Service', ['$http', '$q', function ($http, $q) {
   "use strict";
   
   const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
   
   this.request = function(config) {
	      let deferred = $q.defer();
	      $http(config).then(function (response) {
	    	  deferred.resolve(response.data);
	      }, function (response){
	    	  let errorMsg;
	    	  if(response.data.error === undefined) {
	    		  errorMsg="";
	    	  }else{
	    		  errorMsg = " --- " + response.data.error + ":" + response.data.description;
	    	  }
	    	  deferred.reject( 
	    		 "Error (" + response.status + ":" + response.statusText + ")" + errorMsg );
	      });
	      return deferred.promise;	   
   }
   
   this.create = function (id_ticket){
	   let config = {
			   method: 'POST',
			   url: urlBase+"/invoices",
			   data:{'id': id_ticket}
	   };
	  return this.request(config);
  }
   
    this.initList = function (){
    	let config = {
			   method: 'GET',
			   url: urlBase+"/invoices"
	   };
		  return this.request(config);
	  }
    
    this.getInvoiceById = function (id){
    	let config = {
			   method: 'GET',
			   url: urlBase+"/invoices/"+id
	   };
		  return this.request(config);
	  }
    
    this.search = function (ticket_id){
  	   let config = {
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
}]);