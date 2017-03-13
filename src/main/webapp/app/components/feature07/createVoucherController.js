tpv.controller('CreateVoucherController', [ 'f07Service','Alertify',
	function(f07Service, Alertify) {
		"use strict";
		var vm = this;
		vm.create = create;
		
		
		function create(){
			var value = parseFloat(vm.value);
			if(!isNaN(value)){
				f07Service.createVoucher(value).then(function(result){
					Alertify.success("Voucher created successfully!");
					vm.value = "";
				},function(errors){
					Alertify.error(errors);
				})
			}else{
				 Alertify.error("Please write a valid number");
			}	
		}
		
	} 
]);