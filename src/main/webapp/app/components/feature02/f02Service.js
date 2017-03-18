tpv.service('f02Service', ['$http', '$q', function ($http, $q) {
	"use strict";
   
   	const urlBase="http://localhost:8080/SPRING.tpv.M.1.2.0-SNAPSHOT/api/v0";
   
   	var feature02Service = {
   		createProvider:createProvider,
   		listProviders:listProviders,
   		listIdCompanyProviders:listIdCompanyProviders,
   		deleteProvider:deleteProvider
   	};
   
   	return feature02Service;
   
   	function createProvider(provider) {
   		return $http({
   			method: 'POST',
   			url: urlBase + '/providers',
   			data:{'company': provider.company , 'address': provider.address, 'mobile':provider.mobile, 'phone':provider.phone, 'paymentConditions':provider.paymentConditions, 'note':provider.note}
   		}).then(function successCallback(response) {
	        return response.data;
   		}, function errorCallback(response) {
	        return $q.reject(response);
   		});
	}
   	
   	function listProviders() {
   		return $http({
   			method: 'GET',
   			url: urlBase + '/providers'
   		}).then(function successCallback(response) {
	        return response.data;
   		}, function errorCallback(response) {
	        return $q.reject(response);
   		});
	}
   	
   	function listIdCompanyProviders() {
   		return $http({
   			method: 'GET',
   			url: urlBase + '/providers/idcompany'
   		}).then(function successCallback(response) {
	        return response.data;
   		}, function errorCallback(response) {
	        return $q.reject(response);
   		});
	}
   	
   	function deleteProvider(id) {
		return $http({
			method : 'DELETE',
			url : urlBase + '/providers/' + id
		}).then(function successCallback(response) {
			return response.data;
		}, function errorCallback(response) {
			return $q.reject(response);
		});
	}
}]);