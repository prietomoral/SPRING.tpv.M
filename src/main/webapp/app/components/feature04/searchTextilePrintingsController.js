tpv.controller('SearchTextilePrintingsController', function($route, f04Service) {
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
		type: ""
	}
	
	vm.loading = true;
	vm.error = false;
	
	vm.textilePrintings = [];
	loadTextilePrintings();
	
	function loadTextilePrintings(){
		formatEmptyNumbers();
		f04Service.getTextilePrintings(vm.pageInfo, vm.searchValues).then(result => {
			vm.loading = false;
			vm.textilePrintings = result.content;
			vm.pageInfo.pageNumber = result.number;
			vm.pageInfo.totalTextilePrintings = result.totalElements;
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
	}
	
	vm.changeToPage = pageNumber => {
		vm.pageInfo.pageNumber = pageNumber;
		loadTextilePrintings();
	}
	
	vm.onClickAdvancedSearch = () => {
		vm.advancedSearchVisibility = !vm.advancedSearchVisibility;
	}
	
	vm.onClickSearchButton = () => {
		loadTextilePrintings();
	}
	
	vm.sortBy = parameter => {
		if (vm.pageInfo.sortType == "asc" && vm.pageInfo.sortParameter == parameter) {
			vm.pageInfo.sortType = "desc";
		} else {
			vm.pageInfo.sortType = "asc";
		}
		vm.pageInfo.sortParameter = parameter;
		loadTextilePrintings();
	}
	
	vm.clearFilters = () => {
		$route.reload();
	}
		
});