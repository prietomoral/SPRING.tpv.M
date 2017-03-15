
tpv.directive('alertsTemplate', alertsTemplate);

function alertsTemplate() {
  var directive = {
    restrict: 'EA',
    templateUrl: 'app/components/feature10/alerts-directive.html',
    scope: {
    },
    link: linkFunc,
    controller: ['AlertsService', AlertsTemplateController],
    controllerAs: 'alertsVm',
    bindToController: true
  };

  return directive;

  function linkFunc(scope, el, attr, ctrl) {

  }
}

function AlertsTemplateController(AlertsService){
  var alertsVm = this;

  init();

  function init(){
    AlertsService.getAllActiveAlerts().then(function(response){
      alertsVm.warnings = buildWarnings(response);
      alertsVm.criticals = buildCriticals(response);
    });
  }

  function buildWarnings(alerts){
    return alerts.filter(function(row){
      return row.stock <= row.warning;
    });
  }

  function buildCriticals(alerts){
    return alerts.filter(function(row){
      return row.stock <= row.critical;
    });
  }


}
