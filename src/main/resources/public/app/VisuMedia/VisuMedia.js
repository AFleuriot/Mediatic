'use strict';

angular
    .module('mediatic.VisuMedia',['ngRoute'])
    .controller('VisuMediaCtrl', ['$scope','$location','MediaService','$timeout', function($scope,$location,MediaService, $timeout){
        $scope.types = ['Livre','CD','DVD']     
        var location = $location.search();      
        if(location.id==undefined){
            $location.path('/rechercheMedia');
        }
        else{
            var resource = MediaService.getMediaById(location.id);        
            resource.$promise.then(function(response){
                console.log(resource);
                $scope.media = resource;
                $scope.media.id = location.id;
            }); 
        }                     
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
        };

        $scope.submitMedia = function(){
            $("#myModal").modal('hide');
            $timeout(function () {
                MediaService.updateMedia($scope.media);
                $location.path('/rechercheMedia');
            }, 500);            
        }
    }]
);    