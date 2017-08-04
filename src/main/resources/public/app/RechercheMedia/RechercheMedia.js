'use strict';

angular.module('mediatic.RechercheMedia', ['ngRoute'])
.controller('RechercheMediaCtrl', ['$scope', 'MediaService', function($scope, MediaService) {
    

    $scope.medias = MediaService.getMedias();

    console.log($scope.medias);

    console.log(MediaService.getMediaById(4));

}]);