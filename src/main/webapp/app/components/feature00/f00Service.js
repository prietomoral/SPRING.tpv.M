tpv.service('f00Service', ['$http', '$q', function ($http, $q) {
   "use strict";
   this.version = function () {
      let config = {
 	     method: 'GET',
 	     url: "http://localhost:8080/SPRING.tpv.1.0.0-SNAPSHOT/api/v0/admins",
	  };
      let deferred = $q.defer();
      $http(config).then(function (response) {
    	  deferred.resolve(response.data);
      }, function (response){
    	  let errorMsg;
    	  if(response.data.error === undefined) {
    		  errorMsg="";
    	  }else{
    		  errorMsg = " --- "+response.data.error + ":"+ response.data.description;
    	  }
    	  deferred.reject( 
    		 "Error (" + response.status +":"+ response.statusText +")"+ errorMsg );
      });
      return deferred.promise;
   }
}]);