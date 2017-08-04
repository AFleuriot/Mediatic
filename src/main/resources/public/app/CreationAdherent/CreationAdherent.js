'use strict';

angular.module('mediatic.CreationAdherent', ['ngRoute'])
    .controller('CreationAdherentCtrl', ['$scope', '$timeout',
    function($scope,$timeout){

        $scope.AdherentsCrees = [];
        $scope.MessageAdhCree = '';
          $scope.CreationAdherent = function(){

        $scope.$watch('adh.DateFinCoti', function(){
            var plusAn = $scope.adh.DateFinCoti.getFullYear + 1;
            $scope.adh.DateFinCoti += $scope.adh.DateFinCoti.setFullYear(plusAn);
        }, true);
         

        $scope.AdherentsCrees.push(
                {'nom': $scope.adh.Nom,
                 'prenom': $scope.adh.Prenom,
                 'dateNaissance':$scope.adh.DateNaiss,
                 'age': $scope.adh.Age,
                 'email': $scope.adh.Mail,
                 'adresse': $scope.adh.Adresse + ' ' + $scope.adh.CP +' '+ $scope.adh.Ville,
                 'dateCotisation': $scope.adh.DateCoti,
                 'montantCotisation': $scope.adh.MontantCoti,
                 'dateFinCotisation': $scope.adh.DateFinCoti,
                });
            $scope.MessageAdhCree = 'L\'adhérent a bien été enregistré !';
            $timeout(function(){
                $scope.MessageAdhCree ='';
            }, 5000);
        }



 
    }]);