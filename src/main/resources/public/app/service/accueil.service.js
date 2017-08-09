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
    .run (function (AccueilService, $rootScope,$location) {
            AccueilService.addAuthorization();
            $rootScope.disconnect = AccueilService.disconnect;
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
            $rootScope.verifLogin = function(){
                if ($rootScope.username!=null) {
                    if($location.path()=='/accueil'){
                        $location.path('/rechercheAdherent');
                    }
                }    
                if($rootScope.username==undefined){
                    $location.path('/accueil');
                }
            }   
    });