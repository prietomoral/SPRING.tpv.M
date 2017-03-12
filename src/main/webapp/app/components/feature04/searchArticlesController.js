tpv.controller('SearchArticlesController', function($route, f04Service) {
	"use strict";
	
	var vm = this;
	
	vm.loading = true;
	
	vm.pageNumber = 0;
	vm.pageSize = 3;
	vm.totalArticles = 0;
	
	vm.exactRetailPrice = 0;
	vm.minRetailPrice = 0;
	vm.maxRetailPrice = 0;
	vm.showOnlyOnStock = false;
	vm.advancedSearchVisibility = false;
	
	vm.sortType = "reference";
	vm.sortReverse = false;
	
	vm.error = false;
	vm.errors;
	vm.articles = [];
	
	loadArticles();
	
	function loadArticles(){
		f04Service.getArticles(vm.pageNumber, vm.pageSize).then(result => {
			vm.loading = false;
			vm.articles = result.content;
			vm.pageNumber = result.number;
			vm.totalArticles = result.totalElements;
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
		loadArticles();
	}
	
	vm.onClickAdvancedSearch = () => {
		vm.advancedSearchVisibility = !vm.advancedSearchVisibility;
	}
	
	vm.clearFilters = () => {
		$route.reload();
	}
		
});