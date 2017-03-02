tpv.service('f04Service', function () {
   "use strict"; 
   
   var articles = [
	   {
			"id": 1,
			"reference": "REFERENCE1",
			"description": "DESCRIPTION 1",
			"retailPrice": 666.1,
			"stock": 251,
			"wholesalePrice": 55.1
		},
		{
			"id": 2,
			"reference": "REFERENCE2",
			"description": "DESCRIPTION 2",
			"retailPrice": 666.2,
			"stock": 0,
			"wholesalePrice": 55.2
		},
		{
			"id": 3,
			"reference": "REFERENCE3",
			"description": "DESCRIPTION 3",
			"retailPrice": 666.3,
			"stock": 253,
			"wholesalePrice": 55.3
		},
		{
			"id": 4,
			"reference": "REFERENCE4",
			"description": "DESCRIPTION 4",
			"retailPrice": 666.4,
			"stock": 254,
			"wholesalePrice": 55.4
		}
   ];
   
   this.getArticles = () =>{
		return articles;
	}

});