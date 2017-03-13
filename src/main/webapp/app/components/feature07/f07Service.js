tpv.service('f07Service', ['$http', '$q', function ($http, $q) {
   "use strict";
   
   const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
   const resource = "vouchers";
   
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
    		 response.status + ":" + response.statusText + ":" + errorMsg );
      });
      return deferred.promise;   
   }
   
   
   this.createVoucher = function(value){
	  let config = {
	 	      method: 'POST',
	      url: urlBase+ "/" + resource,
	      data: value
	  };
	  return this.request(config);
   }
   
}]);