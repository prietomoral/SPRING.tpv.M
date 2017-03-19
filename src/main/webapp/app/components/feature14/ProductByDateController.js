tpv.controller('ProductByDateController', [
		'$timeout',
		'f14Service',
		function($timeout, f14Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.response = "";
			vm.data = [];
			vm.product_id;
			vm.fecha_inicio;
			vm.fecha_fin;
			vm.productDate = productDate;
			vm.draw = draw;

			function productDate() {
				const
				delay = 2000;

				f14Service.productDate(vm.product_id,vm.fecha_inicio,vm.fecha_fin).then(function(result) {
					// promise was fullfilled
					vm.completed = true;
					vm.data = result;
					vm.draw();
					$timeout(function() {
						vm.completed = false;
					}, delay)
				}, function(errors) {
					// handle errors
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
			function draw() {
				google.charts.load('current', {
					packages : [ 'corechart', 'line' ]
				});
				google.charts.setOnLoadCallback(drawBasic);

				function drawBasic() {
					
					var data = new google.visualization.DataTable();
					data.addColumn('number', 'X');
					data.addColumn('number',vm.data[0].description);
					for(var i=0;i<vm.data.length;i++){
						data.addRows([[i+1,vm.data[i].amount]]);
					}
					

					var options = {
						hAxis : {
							title : 'Solds'
						},
						vAxis : {
							title : 'Amount'
						}
					};

					var chart = new google.visualization.LineChart(document
							.getElementById('chart_div'));

					chart.draw(data, options);
				}
			}

		} ]);