tpv.controller('SearchArticlesController', function($route, f04Service) {
	"use strict";
	
	var vm = this;
	
	vm.loading = true;
	
	vm.pageNumber = 0;
	vm.pageSize = 3;
	vm.totalArticles = 0;
	
	vm.advancedSearchVisibility = false;

	
	vm.reference = "";
	vm.description = "";
	vm.minRetailPrice = "";
	vm.maxRetailPrice = "";
	vm.onlyOnStock = false;
	
	vm.sortType = "reference";
	vm.sortReverse = false;
	
	vm.error = false;
	vm.articles = [];
	
	loadArticles();
	
	function loadArticles(){
		f04Service.getArticles(vm.pageNumber, vm.pageSize, vm.reference, vm.description, vm.minRetailPrice, vm.maxRetailPrice, vm.onlyOnStock).then(result => {
			vm.loading = false;
			vm.articles = result.content;
			vm.pageNumber = result.number;
			vm.totalArticles = result.totalElements;
			vm.pageSize = result.size;
			vm.error = false;
		}, errors => {
			vm.loading = false;
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
	
	vm.onClickSearchButton = () => {
		loadArticles();
	}
	
	vm.clearFilters = () => {
		$route.reload();
	}
		
});