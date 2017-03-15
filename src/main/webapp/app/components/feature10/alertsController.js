
tpv.controller('AlertsController', ['AlertsService', 'Alertify', AlertsController]);

function AlertsController(AlertsService, Alertify, $route) {
  'use strict';
  var vm = this;
  vm.popAlert = popAlert;
  init();
  
  function init(){
    AlertsService.getAll().then(function success(response){
      vm.data = response;
    },
    function error(errors){
      console.log(errors);
    });
  }
  
  function deleteAlert(id,index){
	  AlertsService.deletAlert(id).then(function success(response){
		  vm.data.splice(index,1);
		  Alertify.success("La alerta se eliminó correctamente");
	    },
	    function error(errors){
	    	Alertify.error("No se pudo borrar la alerta");
	    });   
  }
  
 function popAlert(id, index) {
	 Alertify.confirm('Está seguro que desea eliminar?').then(
		        function onOk() {
		        	deleteAlert(id,index);
		        },
		        function onCancel() { 
		        }
		    );
	 
 }
	


}