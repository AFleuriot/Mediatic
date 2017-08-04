'use strict';

angular
    .module('mediatic.VisuMedia',['ngRoute'])
    .controller('VisuMediaCtrl', ['$scope','$location','MediaService', function($scope,$location,MediaService){
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
        }
    }]
);    