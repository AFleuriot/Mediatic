'use strict';

angular.module('mediatic.CreationMedia', ['ngRoute'])
    .controller('CreationMediaCtrl', ['$scope','$timeout','MediaService',
        function($scope,$timeout,MediaService){
            $scope.MediaCrees = [];
            $scope.MessageMediaCree = '';
            $scope.AddedMedia = false;

            $scope.CreationMedia = function(){
                $scope.MessageMediaCree = 'Le nouveau média a été enregistré !'
                $scope.AddedMedia = true;
                $timeout(function(){
                $scope.MessageMediaCree ='';
                }, 5000);
                var media = {'titre' : $scope.media.titre,
                     'auteur': $scope.media.auteur,
                     'type' : $scope.media.type, 
                     'empruntactuel': null             
                    };
                var test = MediaService.addMedia(media);
                console.log(test);
            }

     
           

    }]);