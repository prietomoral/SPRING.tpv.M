tpv.controller('SearchEmbroideriesController', function($route, f04Service) {
	"use strict";
	
	var vm = this;
	
	vm.loading = true;
	
	vm.pageNumber = 0;
	vm.pageSize = 3;
	vm.totalEmbroideries = 0;
	
	vm.advancedSearchVisibility = false;
	
	vm.reference = "";
	vm.description = "";
	vm.minRetailPrice = "";
	vm.maxRetailPrice = "";
	vm.minRetailPrice = "";
	vm.maxRetailPrice = "";
	vm.minStitches = "";
	vm.maxStitches = "";
	vm.minColors = "";
	vm.maxColors = "";
	vm.minSquareMillimeters = "";
	vm.maxSquareMillimeters = "";
		
	vm.sortType = "reference";
	vm.sortReverse = false;
	
	vm.error = false;
	vm.embroideries = [];
	loadEmbroideries();
	
	function loadEmbroideries(){
		formatEmptyNumbers();
		f04Service.getEmbroideries(vm.pageNumber, vm.pageSize, vm.reference, vm.description, vm.minRetailPrice, vm.maxRetailPrice,
				vm.minStitches, vm.maxStitches, vm.minColors, vm.maxColors, vm.minSquareMillimeters, vm.maxSquareMillimeters).then(result => {
			vm.loading = false;
			vm.embroideries = result.content;
			vm.pageNumber = result.number;
			vm.totalEmbroideries = result.totalElements;
			vm.pageSize = result.size;
			vm.error = false;
		}, errors => {
			vm.loading = false;
			vm.error = true;
		});
	}
	
	function formatEmptyNumbers(){
		vm.minRetailPrice = f04Service.formatEmptyNumber(vm.minRetailPrice);
		vm.maxRetailPrice = f04Service.formatEmptyNumber(vm.maxRetailPrice);
		vm.minStitches = f04Service.formatEmptyNumber(vm.minStitches);
		vm.maxStitches = f04Service.formatEmptyNumber(vm.maxStitches);
		vm.minColors = f04Service.formatEmptyNumber(vm.minColors);
		vm.maxColors = f04Service.formatEmptyNumber(vm.maxColors);
		vm.minSquareMillimeters = f04Service.formatEmptyNumber(vm.minSquareMillimeters);
		vm.maxSquareMillimeters = f04Service.formatEmptyNumber(vm.maxSquareMillimeters);
	}
	
	vm.changeToPage = pageNumber => {
		vm.pageNumber = pageNumber;
		loadEmbroideries();
	}
	
	vm.onClickAdvancedSearch = () => {
		vm.advancedSearchVisibility = !vm.advancedSearchVisibility;
	}
	
	vm.onClickSearchButton = () => {
		loadEmbroideries();
	}
	
	vm.clearFilters = () => {
		$route.reload();
	}
		
});