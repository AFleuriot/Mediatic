'use strict';

angular
    .module('mediatic.accueil',['ngRoute'])
    .controller('accueilCtrl', ['$scope','$location', function($scope, $location){

        $scope.submit = function() {
            console.log('submitted');
            $location.path('/rechercheMedia');          
        }
    }]
);    