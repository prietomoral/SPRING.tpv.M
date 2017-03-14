tpv.controller('ListVouchersController', [ 'f07Service','Alertify',
	function(f07Service, Alertify) {
		"use strict";
		var vm = this;
		vm.getVouchers = getVouchers;
		
		vm.getVouchers();
		
		function getVouchers(){
			f07Service.getVouchers().then(function(result){
				vm.vouchers = result;
			},function(errors){
				Alertify.error(errors);
			})
		}
		
	} 
]);