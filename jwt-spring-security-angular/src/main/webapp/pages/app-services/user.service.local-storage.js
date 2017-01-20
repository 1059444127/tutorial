(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$timeout', '$filter', '$q', '$http'];
    function UserService($timeout, $filter, $q, $http) {

    	var TOKEN_KEY = "jwtToken";
    		
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function GetAll() {
            var deferred = $q.defer();
            deferred.resolve(getUsers());
            return deferred.promise;
        }

        function GetById(id) {
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

        function Create(user) {
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
        }

        function Update(user) {
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

        function getUsers() {
            if(!localStorage.users){
                localStorage.users = JSON.stringify([]);
            }

            return JSON.parse(localStorage.users);
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