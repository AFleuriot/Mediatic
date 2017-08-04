'use strict';

angular
    .module('mediatic.VisuAdherent',['ngRoute'])
    .controller('VisuAdherentCtrl', ['$scope', '$location','AdherentService', function($scope,$location,AdherentService){
        var location = $location.search();      
        if(location.id==undefined){
            $location.path('/rechercheAdherent');
        }
        else{
            var resource = AdherentService.getAdherentById(location.id);        
            resource.$promise.then(function(response){
                console.log(resource); 
                $scope.adherent = resource;  
                $scope.adherent.cp = parseInt($scope.adherent.cp);
                $scope.adherent.dateNaissance = new Date($scope.adherent.dateNaissance);
                $scope.adherent.dateCotisation = new Date($scope.adherent.dateCotisation);
                $scope.adherent.montantCotisation = parseInt($scope.adherent.montantCotisation);
                $scope.dateEmprunt = new Date();
                var temp = new Date();
                temp.setFullYear($scope.dateEmprunt.getFullYear()+1)
                $scope.dateRetour = temp;
            }); 
        } 

        $scope.getAge = function getAge() 
        {
            if($scope.adherent != undefined){
                var today = new Date();
                var birthDate = new Date($scope.adherent.dateNaissance);
                var age = today.getFullYear() - birthDate.getFullYear();
                var m = today.getMonth() - birthDate.getMonth();
                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) 
                {
                    age--;
                }
                return age + " ans";
            }
        }

        $scope.$watch('adherent.dateCotisation',function() {
            if($scope.adherent != undefined){
                var dateBase = new Date($scope.adherent.dateCotisation);
                console.log(dateBase);
                var date = new Date(dateBase.getFullYear(), dateBase.getMonth(), dateBase.getDate());
                date.setYear(date.getFullYear()+1);
                $scope.finCotisation = date.toLocaleDateString();   
            }
        })

        $scope.submit = function(){ 
            $("#myModalun").modal('hide');
            $location.path('/rechercheAdherent');
        }

    }]
);    