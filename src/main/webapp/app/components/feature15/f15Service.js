tpv.service('f15Service', function ($http, $q) {
   "use strict"; 
   
   	const urlBase = "http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
   		
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
	
	 this.generatePdf = function () {
	    let config = {
		     method: 'GET',
		     url: urlBase+"/pdfs",
		  };
	    return this.request(config);
	 }
});