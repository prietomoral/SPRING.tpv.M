tpv.controller('updateArticleController', updateArticleController,'Alertify','$routeParams');


function updateArticleController(f03Service,f02Service,Alertify,$routeParams) {
  'use strict';
  var vm = this;
  vm.findOneArticle = findOneArticle;
  vm.id=$routeParams.idArticle;
  vm.updateArticle=updateArticle;
  vm.providerList = providerList;
  vm.providers = [];
 
  function findOneArticle(id){
	  f03Service.findOneArticle(vm.id).then(function success(response){
      vm.article = response;
    },
    function error(errors){
    	 if (errors.status == 401 || errors.status == 403) {	    
			  Alertify.error("User Unathorized. You must login with user Manager!");
		  }
    });
  }
  
  
  function updateArticle(){
	  f03Service.updateArticle(vm.article).then(
	      function success(response){
	          vm.article = {};
	          Alertify.success("The article has been updated successfully!");
          },
	      function error(errors){
        	  if (errors.status == 401 || errors.status == 403) {	    
				  Alertify.error("User Unathorized. You must login with user Manager!");
			  }else{
				  Alertify.error("The article has not been updated successfully!");
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