'use strict';

angular.module('mediatic.RechercheMedia', ['ngRoute'])
.controller('RechercheMediaCtrl', ['$scope', '$location', 'MediaService', 'AdherentService', 'EmpruntService', function($scope, $location, MediaService, AdherentService, EmpruntService) {


    var initialiser = function() {
        $scope.medias.forEach(function(media) {
            if (media.empruntactuel) {
                var emprunt = EmpruntService.getEmpruntById(media.empruntactuel);
                emprunt.$promise.then(function() {
                    var emprunteur = AdherentService.getAdherentById(emprunt.adherent);
                    emprunteur.$promise.then(function() {
                        media.emprunteur = emprunteur.nom+" "+emprunteur.prenom;
                        media.dateRetourPrevue = emprunt.dateRetourPrevue;
                    });
                });
            }
        });
    }

    $scope.medias = MediaService.searchMedia({});
    $scope.medias.$promise.then(initialiser);

    $scope.sortBy = "";
    $scope.sortReverse = false;

    $scope.typeEst="Tous";

    $scope.getTypeIcon = function(media) {
        if (media.type =='Livre') {
            return 'glyphicon-book';
        }
        if (media.type =='CD') {
            return 'glyphicon-music';
        }
        if (media.type =='DVD') {
            return 'glyphicon-film';
        }
    };

    $scope.rechercher = function() {
        var criteria = {};
        criteria.titre_like=$scope.titreContient;
        criteria.auteur_like=$scope.auteurContient;
        if ($scope.typeEst!='Tous') {
            criteria.type=$scope.typeEst;
        }
        $scope.medias = MediaService.searchMedia(criteria);
        $scope.medias.$promise.then(initialiser);
        console.log($scope.medias);
    };

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
    
    $scope.goTo = function(mediaId) {
        $location.path('/visuMedia').search({'id':mediaId});
    };

}]);