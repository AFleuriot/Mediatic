'use strict';

angular
    .module('mediatic')
    .factory('AccueilService', ['$localStorage','$rootScope', '$http', '$location', function($localStorage, $rootScope, $http, $location) {

    var service = {
            storeUser: storeUser,
            addAuthorization: addAuthorization,
            disconnect: disconnect
        };

        return service;

        function storeUser(user) {
            $localStorage.$reset();
            $localStorage.$default(user);
            $rootScope.username = $localStorage.$default().username;
            addAuthorization();
        }

        function addAuthorization() {
            $rootScope.username = $localStorage.$default().username;
            var password = $localStorage.$default().password;
            if ($rootScope.username && password) {
                var token = btoa($rootScope.username + ':' + password);
                $http.defaults.headers.common.Authorization = 'Basic ' + token
            }
        }

        function disconnect() {
            $localStorage.$reset();
            $rootScope.username = null;
            $location.path('/accueil');
        }
    }])
    .run (function (AccueilService, $rootScope) {
            AccueilService.addAuthorization();
            $rootScope.disconnect = AccueilService.disconnect;
    });