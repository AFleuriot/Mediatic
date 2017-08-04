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

    $scope.returnDate = function(dte){
        //1. on convertit la chaine en Date : new Date(dte)
        //2. on convertit en format dd/MM/yyyy : toLocaleDateString()
        //3. on vérifie si la date est valide sinon on retourne une chaine vide
        var temp = new Date(dte).toLocaleDateString();
        return temp!='Invalid Date' ? temp : '' ;
    }
}]);