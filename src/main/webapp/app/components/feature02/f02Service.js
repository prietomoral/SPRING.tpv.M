tpv.service('f02Service', ['$http', '$q', function ($http, $q) {
	"use strict";
   
   	const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
   
   	var feature02Service = {
   		createProvider:createProvider
   	};
   
   	return feature02Service;
   
   	function createProvider(provider) {
   		return $http({
   			method: 'POST',
   			url: urlBase + '/providers',
   			data:{'id': provider.id, 'company': provider.company , 'address': provider.address, 'mobile':provider.mobile, 'phone':provider.phone, 'paymentConditions':provider.paymentConditions, 'note':provider.note}
   		}).then(function successCallback(response) {
	        return response.data;
   		}, function errorCallback(response) {
	        return $q.reject(response);
   		});
	}
}]);