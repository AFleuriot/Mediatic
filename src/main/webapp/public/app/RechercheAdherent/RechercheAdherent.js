'use strict';

angular.module('mediatic.RechercheAdherent', ['ngRoute'])
.controller('RechercheAdherentCtrl', ['$scope', '$location', 'AdherentService', 'EmpruntService', '$http',
	function($scope, $location, AdherentService, EmpruntService, $http) {
	
    var initialiser = function() {
        $scope.adherents.forEach(function(adherent) {
            adherent.nomComplet = adherent.nom+" "+adherent.prenom
            adherent.cotisationAJour = cotisationAJour(adherent.cotisation.dateCotisation);
            EmpruntService.getEmpruntsActuelsOfAdherent(adherent.id).$promise.then(function(response) {
            	adherent.nombreEmprunts = response.length;
            })
        });
    };

    $scope.adherents = AdherentService.getAdherents();
    $scope.adherents.$promise.then(initialiser);

    $scope.sortBy = "nomComplet";
    $scope.sortReverse = false;

    $scope.rechercher = function() {
        var criteria = {};
        criteria.nom_like=$scope.nomContient;
        criteria.id=$scope.idCommencePar;
        $scope.adherents = AdherentService.searchAdherent(criteria);   
        $scope.adherents.$promise.then(initialiser);            
        
    }

    var cotisationAJour = function(dateCotisation) {
    	var date = new Date(dateCotisation);
    	
        if (new Date(date.getFullYear()+1, date.getMonth(), date.getDate()) > new Date()) {
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


    $scope.sort = function(col) {
        $scope.sortBy = col;
        $scope.sortReverse = !$scope.sortReverse
    };

    $scope.notSorted = function(col) {
        return $scope.sortBy!=col;
    };

    $scope.sorted = function(col) {
        return !$scope.sortReverse && $scope.sortBy==col;
    };

    $scope.reverseSorted = function(col) {
        return $scope.sortReverse && $scope.sortBy==col;
    };

    $scope.goTo = function(adherentId) {
        $location.path('/visuAdherent').search({'id':adherentId});
    }

}]);