(function () {
    'use strict';

    angular
        .module('app')
        .factory('GPSService', GPSService);

    GPSService.$inject = ['$timeout', '$filter', '$q', '$http'];
    function GPSService($timeout, $filter, $q, $http) {

    	var TOKEN_KEY = "jwtToken";
    		
        var service = {};

        service.Create = Create;
        service.Get = Get;
        
        return service;

        function Get(gps) {
        	var token = getJwtToken();
        	console.log("token = " + token);
        	console.log("thermBarcode = " + gps.thermBarcode);
        	var thermBarcode = gps.thermBarcode;
            var req = {
           		 method: 'GET',
           		 url: '/therm/' + thermBarcode,
           		 headers: {
           			   //'Content-Type': 'application/json; charset=utf-8',
           			 	'Authorization': token,
           			 	'Content-Type': 'text/plain'
           		 },
           		 transformResponse: [function (data, headersGetter, status) {
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
                  	
        	
        	var deferred = $q.defer();
            var filtered = $filter('filter')(getUsers(), { id: id });
            var user = filtered.length ? filtered[0] : null;
            deferred.resolve(user);
            return deferred.promise;
        }
        

        function GetByUsername(username) {
            var deferred = $q.defer();
            var filtered = $filter('filter')(getUsers(), { username: username });
            var user = filtered.length ? filtered[0] : null;
            deferred.resolve(user);
            
            
            
            
            /*
            console.log(JSON.stringify(user));
            $http({
            	  method: 'POST',
            	  url: '/auth',
            	  data: JSON.stringify(user),
            	  contentType: "application/json; charset=utf-8"
            	}).then(function successCallback(response) {
            	    // this callback will be called asynchronously
            	    // when the response is available
            		console.log("success" +　response.data.token);
            		var TOKEN_KEY = "jwtToken";
            		var token = response.data.token;
            		localStorage.setItem(TOKEN_KEY, token);
            		console.log("token from localStorage" +　localStorage.getItem(TOKEN_KEY));
            	  }, function errorCallback(response) {
            	    // called asynchronously if an error occurs
            	    // or server returns response with an error status.
            		  console.log("error");
            	  });
            */
            return deferred.promise;
        }
        
        //参考 GetByUsername
        function GetById(id) {
            var deferred = $q.defer();
            var filtered = $filter('filter')(getGPSs(), { id: id });
            var gps = filtered.length ? filtered[0] : null;
            deferred.resolve(gps);
            return deferred.promise;
        }

/*        function Create(user) {
            var deferred = $q.defer();

            // simulate api call with $timeout
            $timeout(function () {
                GetByUsername(user.username)
                    .then(function (duplicateUser) {
                        if (duplicateUser !== null) {
                            deferred.resolve({ success: false, message: 'Username "' + user.username + '" is already taken' });
                        } else {
                            var users = getUsers();

                            // assign id
                            var lastUser = users[users.length - 1] || { id: 0 };
                            user.id = lastUser.id + 1;

                            // save to local storage
                            users.push(user);
                            setUsers(users);

                            deferred.resolve({ success: true });
                        }
                    });
            }, 1000);

            return deferred.promise;
        }*/
        
        function Create(gps) {
        	console.log("gps = " + JSON.stringify(gps));
            var deferred = $q.defer();

            // simulate api call with $timeout
            $timeout(function () {
                GetById(gps.id)
                    .then(function (duplicateUser) {
                    	
                    	deferred.resolve({ success: true });
                        /*if (duplicateUser !== null) {
                            deferred.resolve({ success: false, message: 'Username "' + user.username + '" is already taken' });
                        } else {
                            var users = getUsers();

                            // assign id
                            var lastUser = users[users.length - 1] || { id: 0 };
                            user.id = lastUser.id + 1;

                            // save to local storage
                            users.push(user);
                            setUsers(users);

                            deferred.resolve({ success: true });
                        }*/
                    });
            }, 1000);

            return deferred.promise;
        }        

/*        function Update(user) {
            var deferred = $q.defer();

            var users = getUsers();
            for (var i = 0; i < users.length; i++) {
                if (users[i].id === user.id) {
                    users[i] = user;
                    break;
                }
            }
            setUsers(users);
            deferred.resolve();

            return deferred.promise;
        }*/
        
        function Update(gps) {
        	alert("update gps");
            var deferred = $q.defer();

            var gpss = getGPSs();
            for (var i = 0; i < gpss.length; i++) {
                if (gpss[i].id === gps.id) {
                	gpss[i] = gps;
                    break;
                }
            }
            console.log("1");
            setGPSs(gpss);
            console.log("2");
            deferred.resolve();
            console.log("3");
            return deferred.promise;
        }

        function Delete(id) {
            var deferred = $q.defer();

            var users = getUsers();
            for (var i = 0; i < users.length; i++) {
                var user = users[i];
                if (user.id === id) {
                    users.splice(i, 1);
                    break;
                }
            }
            setUsers(users);
            deferred.resolve();

            return deferred.promise;
        }

        // private functions

        function getGPSs() {
            if(!localStorage.gpss){
                localStorage.gpss = JSON.stringify([]);
            }
            return JSON.parse(localStorage.gpss);
        }
        
        function getUsers() {
            if(!localStorage.users){
                localStorage.users = JSON.stringify([]);
            }

            return JSON.parse(localStorage.users);
        }

        function setGPSs(gpss) {
            localStorage.gpss = JSON.stringify(gpss);
        }
        
        function setUsers(users) {
            localStorage.users = JSON.stringify(users);
        }
        
        function getJwtToken() {
            return localStorage.getItem(TOKEN_KEY);
        }

        function setJwtToken(token) {
            localStorage.setItem(TOKEN_KEY, token);
        }
        
        function createAuthorizationTokenHeader() {
            var token = getJwtToken();
            if (token) {
                return {"Authorization": token};
            } else {
                return {};
            }
        }
    }
})();