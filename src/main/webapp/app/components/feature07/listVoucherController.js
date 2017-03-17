tpv.controller('ListVouchersController', [ 'f07Service','Alertify',
	function(f07Service, Alertify) {
		"use strict";
		var vm = this;
		vm.getVouchers = getVouchers;
		
		vm.getVouchers();
		
		function getVouchers(){
			f07Service.getVouchers().then(function(results){
				vm.vouchers = results;
				vm.total = getTotalActiveVouchers(results);
			},function(errors){
				Alertify.error(errors);
			})
		}
		
		function getTotalActiveVouchers(vouchers){
			var total = 0;
			vouchers.forEach(voucher => {
				if(!voucher.dateOfUse){
					total += voucher.value;
				}
			});
			return total;
		}
		
	} 
]);