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
			vm.popular = popular;
			vm.draw = draw;

			function popular() {
				const
				delay = 2000;

				f14Service.popular().then(function(result) {
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
					data.addColumn('number', 'article');

					data.addRows([ [ 0, 0 ], [ 1, 10 ], [ 2, 23 ], [ 3, 17 ],
							[ 4, 18 ], [ 5, 9 ], [ 6, 11 ], [ 7, 27 ],
							[ 8, 33 ], [ 9, 40 ], [ 10, 32 ], [ 11, 35 ],
							[ 12, 30 ], [ 13, 40 ] ]);

					var options = {
						hAxis : {
							title : 'Time'
						},
						vAxis : {
							title : 'Sold'
						}
					};

					var chart = new google.visualization.LineChart(document
							.getElementById('chart_div'));

					chart.draw(data, options);
				}
			}

		} ]);