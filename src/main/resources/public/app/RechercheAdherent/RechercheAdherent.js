'use strict';

angular.module('mediatic.RechercheAdherent', ['ngRoute'])
.controller('RechercheAdherentCtrl', ['$scope', 'AdherentService', function($scope, AdherentService) {

    $scope.adherents = AdherentService.getAdherents();

    console.log($scope.adherents);

    console.log(AdherentService.getAdherentById(1));

}]);