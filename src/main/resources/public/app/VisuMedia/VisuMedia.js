'use strict';

angular
    .module('mediatic.VisuMedia',['ngRoute'])
    .controller('VisuMediaCtrl', ['$scope', function($scope){
        $scope.types = ['Livre','CD','DVD']
        $scope.selectedType = 'CD';
        $scope.media ={};
        $scope.media.id = 1;
        $scope.media.titre = 'Musique d\'été';
        $scope.media.type = 'CD';
        $scope.media.auteur = 'Inconnu des dives';
        $scope.datePret = new Date();
        $scope.$watch('datePret',function(){
            if($scope.datePret!=null){
                var date = new Date($scope.datePret.getFullYear(), $scope.datePret.getMonth(), $scope.datePret.getDate());
                date.setYear(date.getFullYear()+1);
                $scope.dateRetour = date;   
            }            
        });

        $scope.submit = function(){
            console.log('ok');
        }
    }]
);    