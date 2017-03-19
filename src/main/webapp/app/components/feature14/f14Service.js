tpv.service('f14Service', ['$http', '$q', function ($http, $q) {
   "use strict";
   
   const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0/statistic";
   
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
   
   this.popular = function () {
	   let config = {
 	     method: 'GET',
 	     url: urlBase+"/poopularProducts",
	  };
      return this.request(config);
   }
   this.productDate = function (product_id,fecha_inicio,fecha_fin) {
	   let config = {
 	     method: 'POST',
 	     url: urlBase+"/byDate",
 	     data:{'id':product_id,'inicio':fecha_inicio,'fin':fecha_fin}
	  };
      return this.request(config);
   }
}]);