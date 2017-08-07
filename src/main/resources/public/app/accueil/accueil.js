'use strict';

angular
    .module('mediatic.accueil',['ngRoute'])
    .controller('accueilCtrl', ['$scope','$location','AccueilService', function($scope, $location, AccueilService){

        $scope.badLogin = '';
        $scope.user = {};

        $scope.submit = function() {
            //console.log($scope.username == 'admin');
            //console.log($scope.password == 'admin1234');
            if (($scope.user.username == 'admin') && ($scope.user.password == 'admin1234')) {
                //console.log('recherche media');
                AccueilService.storeUser($scope.user);
                $location.path('/rechercheMedia');          
            } else {
                // console.log('accueil');
                $location.path('/accueil');
                $scope.badLogin = 'Mauvais login ou mot de passe !'
            }
             
        }
    }]
);    