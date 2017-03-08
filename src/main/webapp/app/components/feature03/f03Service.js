tpv.service('f03Service', function () {
   "use strict"; 
   

   
	var embroideries = [
		{
			"id": 8,
			"reference": "REFERENCE8",
			"description": "DESCRIPTION 8",
			"retailPrice": 44.6,
			"stitches": 50,
			"colors": 4,
			"squareMillimeters": 7
		},
		{
			"id": 9,
			"reference": "REFERENCE9",
			"description": "DESCRIPTION 9",
			"retailPrice": 88,
			"stitches": 56,
			"colors": 4,
			"squareMillimeters": 8
		},
		{
			"id": 10,
			"reference": "REFERENCE10",
			"description": "DESCRIPTION 10",
			"retailPrice": 88.7,
			"stitches": 52,
			"colors": 7,
			"squareMillimeters": 9
		},

	];
	

   

	this.allEmbroideries = () =>{
		return embroideries;
	}
	

});