tpv.controller('listArticleController', listArticleController, 'Alertify');

function listArticleController(f03Service,Alertify) {
  'use strict';
  var vm = this;
  vm.initList = initList;
  vm.deleteArticle = deleteArticle;
 

  function initList(){
	  f03Service.listAllArticles().then(function success(response){
      vm.data = response;
    },
    function error(errors){
    	 if (errors.status == 401 || errors.status == 403) {	    
			  Alertify.error("User Unathorized. You must login with user Manager!");
		  }
    });
  }
 
  function deleteArticle(id){
		  f03Service.deleteOneArticle(id).then(function success(response){
			  Alertify.success("The article has been deleted successfully.");
			  initList();
		  },
		  function error(errors){
			  if (errors.status == 401 || errors.status == 403) {	    
				  Alertify.error("User Unathorized. You must login with user Manager!");
			  }else{
				  Alertify.error("The article has not been deleted successfully.");
			  }
			 
	  });
   }
	  
}


	  
