tpv.controller('CreateVoucherController', [ 'f07Service',
	function(f07Service) {
		"use strict";
		var vm = this;
				
	
		vm.create = create;
		
		function create(){
			alert(vm.value);
		}
	} 
]);