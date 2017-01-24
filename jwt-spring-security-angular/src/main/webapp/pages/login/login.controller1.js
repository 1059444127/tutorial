(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', '$http', 'AuthenticationService', 'FlashService'];
    function LoginController($location, $http, AuthenticationService, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.username, vm.password, function (response) {
                if (response.success) {
                    AuthenticationService.SetCredentials(vm.username, vm.password);
                    $location.path('/gps');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        };
        
        /*
        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.username, vm.password, function (response) {
                if (response.success) {
                    AuthenticationService.SetCredentials(vm.username, vm.password);
//                    $location.path('/');
                    $location.path('/gps');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                    $location.path('/register');
                }
            });
        };
        */
        
        
        
        vm.jwtLogin = jwtLogin;
        
        function jwtLogin() {
        	alert("jwtLogin");
            var token='eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTQ4NDg5NDMzNTE4MiwiZXhwIjoxNDg1NDk5MTM1fQ.UKc4oZnIoNReQVvT4B5OOPrm7CvQp8TV_Qf52KIdM0hjhyHav_RTJKTqRGBNGH0q1IIU7QKHAfDzTYtkoX2fXg';
            
            
            var req = {
            		 method: 'GET',
            		 url: '/protected',
            		 headers: {
            			 'Authorization': token,
            		     'Content-Type': 'text/plain'
            		 },
            		 transformResponse: [function (data, headersGetter, status) {
            		      //console.log("msg from server " + data +", status " + status);
            		      return data;
            		  }]
            		};
            

            $http(req)
              .then(function(response){
            	console.log('1,');
            	console.log(response.status);
            	console.log(response.data);
            }, function(response){
            	console.log('2,');
            	console.log(response);
            	console.log(response.status);
            	console.log(response.data);
            });
            
            
            /*
            $http({method: $scope.method, url: $scope.url, cache: $templateCache}).
            then(function(response) {
              $scope.status = response.status;
              $scope.data = response.data;
            }, function(response) {
              $scope.data = response.data || 'Request failed';
              $scope.status = response.status;
          });
          */
            /*
            $scope.form = {};

              $http.get('/protected', '{}')
                .success(function(data) {
                  console.log("success");
                })
                .error(function(data, status, headers, config) {
                  //$scope.error = data.error.message;
                  //console.log(data.error.message);
                	console.log("error");
                });
           */
            
            /*
            console.log(token);
            $http({
            	  method: 'GET',
            	  url: '/protected',
            	  headers: {
            		   'Authorization': token,
            		   'Content-Type': 'application/json'
            		 }
            	}).then(function errorCallback(response) {
            		console.log("s");
            	    console.log(response.statusText );
            	  }, function successCallback(response) {
            		  
            		  console.log("success");
            		  console.log(response.message);
            		  console.log("statusText" +　response.statusText );
            		  console.log("status" +　response.status );
            		  console.log("config" + response.config );
            	  });
            	  */
        };
    }

})();
