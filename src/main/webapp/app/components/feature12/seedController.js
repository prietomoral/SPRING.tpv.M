tpv.controller('SeedController', SeedController, 'Alertify');

function SeedController(f12Service, Alertify) {
	  'use strict';
	  var vm = this;
	  vm.seedDatabase = seedDatabase;
	  vm.authorized = f12Service.isAuthorized();
	 
	  function seedDatabase() {
		  f12Service.seedDatabase(vm.fileName).then(
		      function success(response){    	  
		          vm.fileName = "";
		          Alertify.success("The database has been seeded successfully!");
	          },
		      function error(errors) {
	        	  if (errors.status == 401 || errors.status == 403) {
		    		  Alertify.error("User Unathorized. You must login with an user granted with ADMIN role!");
		    	  } else if (errors.status == 404) {
		    		  Alertify.error("File not found!");
		    	  }
		      });
	  }
}