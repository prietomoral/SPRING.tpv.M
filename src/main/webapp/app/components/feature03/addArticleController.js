tpv.controller('addArticleController', addArticleController,'Alertify');


function addArticleController(f03Service,Alertify) {
  'use strict';
  var vm = this;
  vm.createArticle = createArticle;
 
  function createArticle(){
	  f03Service.createArticle(vm.article).then(
	      function success(response){
	          vm.article = {};
	          Alertify.success("The article has been created successfully!");
          },
	      function error(errors){
	          Alertify.error("A product with this Id already exist");
	      });
  }
	  
}