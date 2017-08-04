'use strict';

angular.module('mediatic.CreationMedia', ['ngRoute'])
    .controller('CreationMediaCtrl', ['$scope','$timeout',
        function($scope,$timeout){
            $scope.MediaCrees = [];
            $scope.MessageMediaCree = '';

            $scope.CreationMedia = function(){
                $scope.MediaCrees.push(
                    {'titre' : $scope.media.titre,
                     'auteur': $scope.media.auteur,
                     'type' : $scope.media.type                   
                    });
                $scope.MessageMediaCree = 'Le nouveau média a été enregistré !'
                $timeout(function(){
                $scope.MessageMediaCree ='';
                }, 5000);
            }

    }]);