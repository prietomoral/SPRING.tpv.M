tpv.service('f04Service', function ($http, $q) {
   "use strict"; 
   
   	const urlBase = "http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
	
	this.request = config => {
	      let deferred = $q.defer();
	      $http(config).then(response => {
	    	  deferred.resolve(response.data);
	      }, response => {
	    	  let errorMsg;
	    	  if(response.data.error === undefined) {
	    		  errorMsg="";
	    	  }else{
	    		  errorMsg = ` --- ${response.data.error}:${response.data.description}`;
	    	  }
	    	  deferred.reject( 
	    	  	`Error (${response.status}:${response.statusText})${errorMsg}`);
	      });
	      return deferred.promise;	   
	}	
   
	this.getArticles = (pageNumber, pageSize) =>{
		  let config = {
		 	 method: 'GET',
		 	 url: `${urlBase}/articles/search?page=${pageNumber}&size=${pageSize}`
		  };
	      return this.request(config);
	}
   
	this.getEmbroideries = (pageNumber, pageSize) =>{
		let config = {
			method: 'GET',
			url: `${urlBase}/embroideries/search?page=${pageNumber}&size=${pageSize}`
		};
		return this.request(config);
	}
	
	// TODO poner la ruta en plural
	this.getTextilePrintings = (pageNumber, pageSize) =>{
		let config = {
			method: 'GET',
			url: `${urlBase}/textileprinting/search?page=${pageNumber}&size=${pageSize}`
		};
		return this.request(config);
	}

});