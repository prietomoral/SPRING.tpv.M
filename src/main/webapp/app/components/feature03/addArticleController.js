tpv.controller('addArticleController', addArticleController,'Alertify');


function addArticleController(f03Service, f02Service, Alertify) {
  'use strict';
  var vm = this;
  vm.createArticle = createArticle;
  vm.providerList = providerList;
  vm.providers = [];
 
  function createArticle(){
	  f03Service.createArticle(vm.article).then(
	      function success(response){
	          vm.article = {};
	          Alertify.success("The article has been created successfully!");
          },
	      function error(errors){
        	  if (errors.status == 401 || errors.status == 403) {	    
				  Alertify.error("User Unathorized. You must login with user Manager!");
			  }else{
				  Alertify.error("A product with this Id already exist");
			  }
	          
	      });
  }
  
  function providerList(){
	  f02Service.listIdCompanyProviders().then(function success(response){
      vm.data = response;
      console.log(vm.data);
    	    $.each(vm.data, function (i, item) {
    	        vm.providers.push({"id": item['id'], "company": item['company']});
    	    });
    },
    function error(errors){
      console.log(errors);
    });
  }
	  
}