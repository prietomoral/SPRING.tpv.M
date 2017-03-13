tpv.controller('SearchTextilePrintingsController', function($route, f04Service) {
	"use strict";
	
	var vm = this;
	
	vm.loading = true;
	
	vm.pageNumber = 0;
	vm.pageSize = 3;
	vm.totalTextilePrintings = 0;
	
	vm.advancedSearchVisibility = false;
	
	vm.reference = "";
	vm.description = "";
	vm.minRetailPrice = "";
	vm.maxRetailPrice = "";
	vm.type = "";
	
	
	vm.sortType = "reference";
	vm.sortReverse = false;
	
	vm.textilePrintings = [];
	loadTextilePrintings();
	
	function loadTextilePrintings(){
		formatEmptyNumbers();
		f04Service.getTextilePrintings(vm.pageNumber, vm.pageSize, vm.reference, vm.description, vm.minRetailPrice, vm.maxRetailPrice, vm.type).then(result => {
			vm.loading = false;
			vm.textilePrintings = result.content;
			vm.pageNumber = result.number;
			vm.totalTextilePrintings = result.totalElements;
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
	}
	
	vm.changeToPage = pageNumber => {
		vm.pageNumber = pageNumber;
		loadTextilePrintings();
	}
	
	vm.onClickAdvancedSearch = () => {
		vm.advancedSearchVisibility = !vm.advancedSearchVisibility;
	}
	
	vm.onClickSearchButton = () => {
		loadTextilePrintings();
	}
	
	vm.clearFilters = () => {
		$route.reload();
	}
		
});