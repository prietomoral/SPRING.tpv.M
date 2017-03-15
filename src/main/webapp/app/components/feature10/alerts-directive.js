
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
  alertsVm.warnings = [];
  alertsVm.criticals = [];
}
