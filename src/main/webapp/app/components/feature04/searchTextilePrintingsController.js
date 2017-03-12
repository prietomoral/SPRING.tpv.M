tpv.controller('SearchTextilePrintingsController', function($route, f04Service) {
	"use strict";
	
	var vm = this;
	
	vm.loading = true;
	
	vm.pageNumber = 0;
	vm.pageSize = 3;
	vm.totalTextilePrintings = 0;
	
	vm.exactRetailPrice = 0;
	vm.minRetailPrice = 0;
	vm.maxRetailPrice = 0;
	
	vm.advancedSearchVisibility = false;
	
	vm.sortType = "reference";
	vm.sortReverse = false;
	
	vm.textilePrintings = [];
	loadTextilePrintings();
	
	function loadTextilePrintings(){
		f04Service.getTextilePrintings(vm.pageNumber, vm.pageSize).then(result => {
			vm.loading = false;
			vm.textilePrintings = result.content;
			vm.pageNumber = result.number;
			vm.totalTextilePrintings = result.totalElements;
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
		loadTextilePrintings();
	}
	
	vm.onClickAdvancedSearch = () => {
		vm.advancedSearchVisibility = !vm.advancedSearchVisibility;
	}
	
	vm.clearFilters = () => {
		$route.reload();
	}
		
});