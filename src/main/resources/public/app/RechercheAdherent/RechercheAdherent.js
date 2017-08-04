'use strict';

angular.module('mediatic.RechercheAdherent', ['ngRoute'])
.controller('RechercheAdherentCtrl', ['$scope', 'AdherentService', function($scope, AdherentService) {

    $scope.adherents = AdherentService.getAdherents();

    $scope.rechercher = function() {
        var criteria = {};
        criteria.nom_like=$scope.nomContient;
        criteria.id_like=$scope.idCommencePar;
        $scope.adherents = AdherentService.searchAdherent(criteria);
        console.log($scope.adherents);
    }

    $scope.cotisationAJour = function(adherent) {
        if (new Date(adherent.dateFinCotisation) > new Date()) {
            return 'Oui';
        }
        return 'Non';
    }

}]);