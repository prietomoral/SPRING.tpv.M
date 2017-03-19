tpv.controller('UseVoucherController', [ 'f07Service','Alertify',
	function(f07Service, Alertify) {
		"use strict";
		var vm = this;
		vm.useVoucher = useVoucher;
		
		
		function useVoucher(){
			f07Service.useVoucher(vm.identification).then(function(result){
				Alertify.success("Voucher used successfully!");
				vm.value = "";
			},function(errors){
				Alertify.error(errors);
			})
		}
		
	} 
]);