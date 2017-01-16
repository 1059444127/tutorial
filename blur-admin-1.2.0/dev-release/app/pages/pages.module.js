/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages', [
    'ui.router',

    'BlurAdmin.pages.dashboard',
    'BlurAdmin.pages.ui',
    'BlurAdmin.pages.components',
    'BlurAdmin.pages.form',
    'BlurAdmin.pages.tables',
    'BlurAdmin.pages.charts',
    //'BlurAdmin.pages.maps',
    'BlurAdmin.pages.profile',
  ])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider, $urlRouterProvider, baSidebarServiceProvider) {
    $urlRouterProvider.otherwise('/dashboard');

    baSidebarServiceProvider.addStaticItem({
      title: 'Pages',
      icon: 'ion-document',
      subMenu: [{
        title: 'Sign In',
        fixedHref: 'auth.html',
        blank: true
      }, {
        title: 'Sign Up',
        fixedHref: 'reg.html',
        blank: true
      }, {
        title: 'User Profile',
        stateRef: 'profile'
      }, {
        title: '404 Page',
        fixedHref: '404.html',
        blank: true
      }]
    });
    baSidebarServiceProvider.addStaticItem({
      title: 'Menu Level 1',
      icon: 'ion-ios-more',
      subMenu: [{
        title: 'Menu Level 1.1',
        disabled: true
      }, {
        title: 'Menu Level 1.2',
        subMenu: [{
          title: 'Menu Level 1.2.1',
          disabled: true
        }]
      }]
    });

    //远程诊断
    baSidebarServiceProvider.addStaticItem({
      title: '远程诊断',
      icon: 'ion-document',
      subMenu: [{
        title: '未诊断',
        fixedHref: 'auth.html',
        blank: true
      }, {
        title: '已诊断',
        fixedHref: 'reg.html',
        blank: true
      }]
    });

    $stateProvider
        .state('cst', {
          url: '/tables',
          template : '<ui-view></ui-view>',
          abstract: true,
          controller: 'TablesPageCtrl',
          title: '远程诊断',
          sidebarMeta: {
            icon: 'ion-grid',
            order: 300,
          },
        }).state('cst.reported', {
          url: '/basic',
          templateUrl: 'app/pages/tables/basic/tables.html',
          title: '已诊断',
          sidebarMeta: {
            order: 0,
          },
        }).state('cst.unreported', {
          url: '/smart',
          templateUrl: 'app/pages/tables/smart/tables.html',
          title: '未诊断',
          sidebarMeta: {
            order: 100,
          },
        });
    $urlRouterProvider.when('/tables','/tables/basic');
  }

})();
