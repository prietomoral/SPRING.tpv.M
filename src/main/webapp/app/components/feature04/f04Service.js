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
   
	this.getArticles = (pageInfo, searchValues) => {
		  let config = {
		 	 method: 'GET',
		 	 url: `${urlBase}/articles/search?page=${pageInfo.pageNumber}&size=${pageInfo.pageSize}` + 
		 	 `&sort=${pageInfo.sortParameter},${pageInfo.sortType}&reference=${searchValues.reference}&description=${searchValues.description}` + 
		 	 `&minRetailPrice=${searchValues.minRetailPrice}&maxRetailPrice=${searchValues.maxRetailPrice}&onlyOnStock=${searchValues.onlyOnStock}`
		  };
	      return this.request(config);
	}
   
	this.getEmbroideries = (pageInfo, searchValues) => {
		let config = {
			method: 'GET',
			url: `${urlBase}/embroideries/search?page=${pageInfo.pageNumber}&size=${pageInfo.pageSize}` + 
			`&sort=${pageInfo.sortParameter},${pageInfo.sortType}&reference=${searchValues.reference}&description=${searchValues.description}` + 
		 	 `&minRetailPrice=${searchValues.minRetailPrice}&maxRetailPrice=${searchValues.maxRetailPrice}&minStitches=${searchValues.minStitches}` + 
		 	 `&maxStitches=${searchValues.maxStitches}&minColors=${searchValues.minColors}&maxColors=${searchValues.maxColors}` + 
		 	 `&minSquareMillimeters=${searchValues.minSquareMillimeters}&maxSquareMillimeters=${searchValues.maxSquareMillimeters}`
		};
		return this.request(config);
	}
	
	this.getTextilePrintings = (pageInfo, searchValues) => {
		let config = {
			method: 'GET',
			url: `${urlBase}/textileprintings/search?page=${pageInfo.pageNumber}&size=${pageInfo.pageSize}` + 
			`&sort=${pageInfo.sortParameter},${pageInfo.sortType}&reference=${searchValues.reference}` + 
			`&description=${searchValues.description}&minRetailPrice=${searchValues.minRetailPrice}` + 
			`&maxRetailPrice=${searchValues.maxRetailPrice}&type=${searchValues.type}`
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