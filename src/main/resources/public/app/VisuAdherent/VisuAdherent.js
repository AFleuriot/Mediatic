'use strict';

angular
    .module('mediatic.VisuAdherent',['ngRoute'])
    .controller('VisuAdherentCtrl', ['$scope', '$location', function($scope,$location){
        $scope.adherent={};
        $scope.adherent.nom = 'DEZIER';
        $scope.adherent.prenom = 'Alexandre';
        $scope.adherent.ddn = new Date('1994','05','02');
        $scope.adherent.mail = 'alex@or.fr';
        $scope.adherent.cp = 14123;
        $scope.adherent.ville = 'Fleury';
        $scope.adherent.rue = 'Chemin des roulettes russes';
        $scope.adherent.dateCotisation = new Date('2015','05','30');
        $scope.adherent.cotisation = 3.5000;

        $scope.getAge = function getAge() 
        {
            var today = new Date();
            var birthDate = $scope.adherent.ddn;
            var age = today.getFullYear() - birthDate.getFullYear();
            var m = today.getMonth() - birthDate.getMonth();
            if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) 
            {
                age--;
            }
            return age + " ans";
        }

        $scope.$watch('adherent.dateCotisation',function() {
            var date = new Date($scope.adherent.dateCotisation.getFullYear(), $scope.adherent.dateCotisation.getMonth(), $scope.adherent.dateCotisation.getDate());
            date.setYear(date.getFullYear()+1);
            $scope.finCotisation = date.toLocaleDateString();   
        })

        $scope.submit = function(){ 
            $("#myModalun").modal('hide');
            $location.path('/rechercheAdherent');
        }

    }]
);    