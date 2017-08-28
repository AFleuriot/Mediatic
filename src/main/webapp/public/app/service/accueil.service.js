'use strict';

angular
    .module('mediatic')
    .factory('AccueilService', ['$localStorage','$rootScope', '$http', '$location', function($localStorage, $rootScope, $http, $location) {


    	
    var service = {
            disconnect: disconnect,
            storeUser: storeUser,
            addAuthorization: addAuthorization
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
            $location.path('/logout');
        }
        
        
    }])
    .run (function (AccueilService, $rootScope,$location, $http) {
           // AccueilService.addAuthorization();
            //$rootScope.disconnect = AccueilService.disconnect;
    	
    	
    	
            $rootScope.verifLocation = function(url){            
                if(url == $location.path()){
                    return 'active';
                }
            }
            $rootScope.verifType = function(type){
                if($location.path().indexOf(type)!=-1){
                    return 'active_dropdown';
                }
            } 
            
            
            
            
    });




