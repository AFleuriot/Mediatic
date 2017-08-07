'use strict';

angular
    .module('mediatic.accueil',['ngRoute'])
    .controller('accueilCtrl', ['$scope','$location', function($scope, $location){

        $scope.submit = function() {
            console.log($scope.username == 'admin');
            console.log($scope.password == 'admin1234');
            if (($scope.username == 'admin') && ($scope.password == 'admin1234')) {
                console.log('recherche media');
                $location.path('/rechercheMedia');          
            } else {
                // console.log('accueil');
                $location.path('/accueil');
            }
             
        }
    }]
);    