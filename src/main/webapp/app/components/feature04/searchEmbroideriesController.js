tpv.controller('SearchEmbroideriesController', function($route, f04Service) {
	"use strict";
	
	var vm = this;
	
	vm.loading = true;
	
	vm.pageNumber = 0;
	vm.pageSize = 3;
	vm.totalEmbroideries = 0;
	
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
	
	vm.advancedSearchVisibility = false;
	
	vm.sortType = "reference";
	vm.sortReverse = false;
	
	vm.error = false;
	vm.errors;
	vm.embroideries = [];
	loadEmbroideries();
	
	function loadEmbroideries(){
		f04Service.getEmbroideries(vm.pageNumber, vm.pageSize).then(result => {
			vm.loading = false;
			vm.embroideries = result.content;
			vm.pageNumber = result.number;
			vm.totalEmbroideries = result.totalElements;
			vm.pageSize = result.size;
			vm.error = false;
		}, errors => {
			vm.loading = false;
			vm.errors = errors;
			vm.error = true;
		});
	}
	
	vm.changeToPage = pageNumber => {
		vm.pageNumber = pageNumber;
		loadEmbroideries();
	}
	
	vm.onClickAdvancedSearch = () => {
		vm.advancedSearchVisibility = !vm.advancedSearchVisibility;
	}
	
	vm.clearFilters = () => {
		$route.reload();
	}
		
});