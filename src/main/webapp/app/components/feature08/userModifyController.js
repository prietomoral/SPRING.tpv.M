tpv.controller('UserModifyController', UserModifyController, 'Alertify', '$routeParams');

function UserModifyController (f08Service, Alertify, $routeParams) {
	"use strict";
	var vm = this;

	vm.id = $routeParams.idUser;
	vm.getUserById = getUserById;
	vm.modifyUser = modifyUser;
			
	function getUserById() {
		f08Service.getUserById(vm.id).then(function(result) {
			vm.completed = true;
			vm.user = result;
		}, function(errors) {
			vm.error = true;
			vm.response = errors;
		});
	}
	
	function modifyUser() {
		f08Service.modifyUser(vm.user).then(function(result) {
			vm.completed = true;
			vm.user = {};
	        Alertify.success("User has been updated successfully!");
		}, function(errors) {
			Alertify.error(errors);
		});
	}
}