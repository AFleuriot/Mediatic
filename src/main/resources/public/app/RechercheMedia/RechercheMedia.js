'use strict';

angular.module('mediatic.RechercheMedia', ['ngRoute'])
.controller('RechercheMediaCtrl', ['$scope', 'MediaService', 'AdherentService', 'EmpruntService', function($scope, MediaService, AdherentService, EmpruntService) {
    

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

    $scope.medias = MediaService.getMedias();
    $scope.medias.$promise.then(initialiser);


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
    }

    

}]);