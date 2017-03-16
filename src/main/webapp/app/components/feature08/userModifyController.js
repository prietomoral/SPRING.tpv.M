tpv.controller('UserModifyController', UserModifyController, 'Alertify', '$routeParams');

function UserModifyController (f08Service, $routeParams) {
	"use strict";
	var vm = this;
	vm.completed = false;
	vm.error = false;
	vm.id = $routeParams.idUser;
	vm.getUserById = getUserById;
	vm.modifyUser = modifyUser;
			
	function getUserById() {
		f08Service.getUserById(vm.id).then(function(result) {
			vm.completed = true;
			vm.data = result;
		}, function(errors) {
			vm.error = true;
			vm.response = errors;
		});
	}
	
	function modifyUser() {
		f08Service.modifyUser(vm.id).then(function(result) {
			vm.completed = true;
			vm.data2 = result;
		}, function(errors) {
			vm.error = true;
			vm.response = errors;
		});
	}
}