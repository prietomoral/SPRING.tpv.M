tpv.controller('SearchEmbroideriesController', function($route, f04Service) {
	"use strict";
	
	var vm = this;
	
	vm.authenticated = f04Service.isAuthenticated();
		
	vm.pageInfo = {
		pageNumber: 0,
		pageSize: 15,
		totalArticles: 0,
		sortParameter: "reference",
		sortType: "asc"
	};
	
	vm.advancedSearchVisibility = false;
	
	vm.searchValues = {
		reference: "",
		description: "",
		minRetailPrice: "",
		maxRetailPrice: "",
		minStitches: "",
		maxStitches: "",
		minColors: "",
		maxColors: "",
		minSquareMillimeters: "",
		maxSquareMillimeters: ""
	}
	
	vm.loading = true;
	vm.error = false;
	
	vm.embroideries = [];
	loadEmbroideries();
	
	function loadEmbroideries(){
		formatEmptyNumbers();
		f04Service.getEmbroideries(vm.pageInfo, vm.searchValues).then(result => {
			vm.loading = false;
			vm.embroideries = result.content;
			vm.pageInfo.pageNumber = result.number;
			vm.pageInfo.totalEmbroideries = result.totalElements;
			vm.pageInfo.pageSize = result.size;
			vm.error = false;
		}, errors => {
			vm.loading = false;
			vm.error = true;
		});
	}
	
	function formatEmptyNumbers(){
		vm.searchValues.minRetailPrice = f04Service.formatEmptyNumber(vm.searchValues.minRetailPrice);
		vm.searchValues.maxRetailPrice = f04Service.formatEmptyNumber(vm.searchValues.maxRetailPrice);
		vm.searchValues.minStitches = f04Service.formatEmptyNumber(vm.searchValues.minStitches);
		vm.searchValues.maxStitches = f04Service.formatEmptyNumber(vm.searchValues.maxStitches);
		vm.searchValues.minColors = f04Service.formatEmptyNumber(vm.searchValues.minColors);
		vm.searchValues.maxColors = f04Service.formatEmptyNumber(vm.searchValues.maxColors);
		vm.searchValues.minSquareMillimeters = f04Service.formatEmptyNumber(vm.searchValues.minSquareMillimeters);
		vm.searchValues.maxSquareMillimeters = f04Service.formatEmptyNumber(vm.searchValues.maxSquareMillimeters);
	}
	
	vm.changeToPage = pageNumber => {
		vm.pageInfo.pageNumber = pageNumber;
		loadEmbroideries();
	}
	
	vm.onClickAdvancedSearch = () => {
		vm.advancedSearchVisibility = !vm.advancedSearchVisibility;
	}
	
	vm.onClickSearchButton = () => {
		loadEmbroideries();
	}
	
	vm.sortBy = parameter => {
		if ((vm.pageInfo.sortType == "asc" && vm.pageInfo.sortParameter == parameter) || (vm.pageInfo.sortType == "desc" && vm.pageInfo.sortParameter != parameter)) {
			vm.pageInfo.sortType = "desc";
		} else {
			vm.pageInfo.sortType = "asc";
		}
		vm.pageInfo.sortParameter = parameter;
		loadEmbroideries();
	}
	
	vm.clearFilters = () => {
		$route.reload();
	}
		
});