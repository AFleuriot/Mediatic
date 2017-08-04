'use strict';

angular.module('mediatic.RechercheMedia', ['ngRoute'])
.controller('RechercheMediaCtrl', ['$scope', 'MediaService', function($scope, MediaService) {
    

    $scope.medias = MediaService.getMedias();

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
        console.log($scope.medias);
    }

}]);