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
   
	this.getArticles = (pageNumber, pageSize, reference, description, minRetailPrice, maxRetailPrice, onlyOnStock) => {
		  let config = {
		 	 method: 'GET',
		 	 url: `${urlBase}/articles/search?page=${pageNumber}&size=${pageSize}&reference=${reference}&description=${description}` + 
		 	 `&minRetailPrice=${minRetailPrice}&maxRetailPrice=${maxRetailPrice}&onlyOnStock=${onlyOnStock}`
		  };
		  console.log(config.url);
	      return this.request(config);
	}
   
	this.getEmbroideries = (pageNumber, pageSize, reference, description, minRetailPrice, maxRetailPrice, minStitches, maxStitches, 
			minColors, maxColors, minSquareMillimeters, maxSquareMillimeters) => {
		let config = {
			method: 'GET',
			url: `${urlBase}/embroideries/search?page=${pageNumber}&size=${pageSize}&reference=${reference}&description=${description}` + 
		 	 `&minRetailPrice=${minRetailPrice}&maxRetailPrice=${maxRetailPrice}&minStitches=${minStitches}` + 
		 	 `&maxStitches=${maxStitches}&minColors=${minColors}&maxColors=${maxColors}` + 
		 	 `&minSquareMillimeters=${minSquareMillimeters}&maxSquareMillimeters=${maxSquareMillimeters}`
		};
		return this.request(config);
	}
	
	this.getTextilePrintings = (pageNumber, pageSize, reference, description, minRetailPrice, maxRetailPrice, type) => {
		let config = {
			method: 'GET',
			url: `${urlBase}/textileprintings/search?page=${pageNumber}&size=${pageSize}&reference=${reference}&description=${description}` + 
		 	 `&minRetailPrice=${minRetailPrice}&maxRetailPrice=${maxRetailPrice}&type=${type}`
		};
		return this.request(config);
	}
	
	this.formatEmptyNumber = emptyNumber => {
		if (emptyNumber == undefined){
			return "";
		} else {
			return emptyNumber;
		}
	}

});