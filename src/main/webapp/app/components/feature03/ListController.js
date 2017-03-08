tpv.controller('ListController', function($route, f03Service) {
	"use strict";
	
	var vm = this;
	
	vm.embroideries = f03Service.allEmbroideries();
	vm.exactRetailPrice = 0;
	vm.minRetailPrice = 0;
	vm.maxRetailPrice = 0;	
	vm.exactStitches = 0;
	vm.minStitches = 0;
	vm.maxStitches = 0;
	vm.exactColors = 0;
	vm.minColors = 0;
	vm.maxColors = 0;
	vm.exactSquareMillimeters = 0;
	vm.minSquareMillimeters = 0;
	vm.maxSquareMillimeters = 0;
	vm.searchVisibility = false;
	vm.sortType = "reference";
	vm.sortReverse = false;
	

	

		
});