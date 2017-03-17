tpv.controller('PopularProductsController', [ '$timeout', 'f14Service',
		function($timeout, f14Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.response = "";
			vm.data=[];
			vm.popular = popular;
			vm.draw=draw;

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
			
			function draw(){
				google.charts.load('current', {
					'packages' : [ 'bar' ]
				});
				google.charts.setOnLoadCallback(drawStuff);
				function drawStuff() {
					var draw=[[ 'Products', 'Total Sold' ]];
					for(var i=0;i<vm.data.length;i++){
						draw.push([vm.data[i].description,vm.data[i].totalAmountSold])
					}
					var data = new google.visualization.arrayToDataTable(draw);

					var options = {
						width : 700,
						chart : {
							title : 'Popular products',
						}
					};

					var chart = new google.charts.Bar(document.getElementById('dual_y_div'));
					chart.draw(data, options);
				};
			}
						
			
		} ]);