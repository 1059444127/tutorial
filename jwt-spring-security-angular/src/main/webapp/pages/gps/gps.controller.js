(function () {
    'use strict';

    angular
        .module('app')
        .controller('GPSController', GPSController);

    GPSController.$inject = ['GPSService', '$location', '$rootScope', 'FlashService'];
    function GPSController(GPSService, $location, $rootScope, FlashService) {
        var vm = this;
        
        vm.get = get;
        vm.doConfig = doConfig;
        
        function doConfig() {
            vm.dataLoading = true;
            GPSService.Create(vm.gps)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('成功配置采集仪', true);
                        $location.path('/gps/edit/success');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
        
        function get() {
        	vm.dataLoading = true;
        	GPSService.Get(vm.gps)
        		.then(function (response) {
        			
        		});
        }
    }

})();
