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
   
	this.getArticles = () =>{
		return articles;
	}
   
	this.getEmbroideries = () =>{
		return embroideries;
	}
	
	this.getTextilePrintings = () =>{
		return textilePrintings;
	}

});