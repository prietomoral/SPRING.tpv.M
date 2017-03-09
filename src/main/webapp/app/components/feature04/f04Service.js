tpv.service('f04Service', function ($http, $q) {
   "use strict"; 
   
   const urlBase = "http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
   
	var embroideries = [
		{
			"id": 5,
			"reference": "REFERENCE5",
			"description": "DESCRIPTION 5",
			"retailPrice": 666.5,
			"stitches": 205,
			"colors": 5,
			"squareMillimeters": 6
		},
		{
			"id": 6,
			"reference": "REFERENCE6",
			"description": "DESCRIPTION 6",
			"retailPrice": 666.6,
			"stitches": 206,
			"colors": 6,
			"squareMillimeters": 7
		},
		{
			"id": 7,
			"reference": "REFERENCE7",
			"description": "DESCRIPTION 7",
			"retailPrice": 666.7,
			"stitches": 207,
			"colors": 7,
			"squareMillimeters": 8
		},
		{
			"id": 8,
			"reference": "REFERENCE8",
			"description": "DESCRIPTION 8",
			"retailPrice": 666.8,
			"stitches": 208,
			"colors": 8,
			"squareMillimeters": 9
		}
	];
	
	var textilePrintings = [
		{
			"id": 9,
			"reference": "REFERENCE9",
			"description": "DESCRIPTION 9",
			"retailPrice": 666.9,
			"type": "Type 9"
		},
		{
			"id": 10,
			"reference": "REFERENCE10",
			"description": "DESCRIPTION 10",
			"retailPrice": 666.10,
			"type": "Type 10"
		},
		{
			"id": 11,
			"reference": "REFERENCE11",
			"description": "DESCRIPTION 11",
			"retailPrice": 666.11,
			"type": "Type 11"
		},
		{
			"id": 12,
			"reference": "REFERENCE12",
			"description": "DESCRIPTION 12",
			"retailPrice": 666.12,
			"type": "Type 12"
		}
	];
	
	this.request = (config) => {
	      let deferred = $q.defer();
	      $http(config).then((response) => {
	    	  deferred.resolve(response.data);
	      }, (response) => {
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
		 	 url: urlBase+`/articles/search?page=${pageNumber}&size=${pageSize}`
		  };
	      return this.request(config);
	}
   
	this.getEmbroideries = () =>{
		return embroideries;
	}
	
	this.getTextilePrintings = () =>{
		return textilePrintings;
	}

});