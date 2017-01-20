/**
 * @author zhengjunjie
 * created on 16.01.2017
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.cst', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('cst', {
          url: '/cst',
          template : '<ui-view></ui-view>',
          abstract: true,
          controller: 'CstPageCtrl',
          title: '远程诊断',
          sidebarMeta: {
            icon: 'ion-grid',
            order: 300,
          },
        }).state('cst.unreported', {
          url: '/unreported',
          templateUrl: 'app/pages/cst/unreported.html',
          title: '未诊断',
          sidebarMeta: {
            order: 0,
          },
        }).state('cst.reported', {
          url: '/reported',
          templateUrl: 'app/pages/cst/reported.html',
          title: '已诊断',
          sidebarMeta: {
            order: 100,
          },
        });
    $urlRouterProvider.when('/cst','/cst/unreported');
  }

})();
