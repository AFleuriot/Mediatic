'use strict';

angular.module('mediatic.CreationAdherent', ['ngRoute'])
    .controller('CreationAdherentCtrl', ['$scope', '$timeout','AdherentService',
    function($scope,$timeout,AdherentService){
        
        $scope.AdherentsCrees = [];
        $scope.MessageAdhCree = '';
        
       /* $scope.dateReturn = function(dte){
            var temp = new Date(dte).toLocaleDateString();  
            return temp;
        }*/
            

        $scope.CreationAdherent = function(){
            /*var adhDateNaissance = $scope.adh.DateNaiss;
            var dateNaissFormat = $scope.dateReturn($scope.adh.DateNaiss);
            var dateCotiFormat = $scope.dateReturn($scope.adh.DateCoti);
            var dateFinCotiFormat = $scope.dateReturn($scope.adh.DateFinCoti);

           $scope.AdherentsCrees.push({'nom': $scope.adh.Nom,
                 'prenom': $scope.adh.Prenom,
                 'dateNaissance' :$scope.adh.DateNaiss,
                 'age': $scope.adh.Age,
                 'email': $scope.adh.Mail,
                 'rue': $scope.adh.Adresse, 
                 'cp': $scope.adh.CP,  
                 'ville': $scope.adh.Ville,
                 'dateCotisation':$scope.adh.DateCoti,
                 'montantCotisation': $scope.adh.MontantCoti,
                 'dateFinCotisation': $scope.adh.DateFinCoti,
                });*/
            var adherent =  {'nom': $scope.adh.Nom,
                 'prenom': $scope.adh.Prenom,
                 'dateNaissance': $scope.adh.DateNaiss,
                 'age': $scope.adh.Age,
                 'email': $scope.adh.Mail,
                 'rue': $scope.adh.Adresse, 
                 'cp': $scope.adh.CP,  
                 'ville': $scope.adh.Ville,
                 'dateCotisation': $scope.adh.DateCoti,
                 'montantCotisation': $scope.adh.MontantCoti,
                 'dateFinCotisation': $scope.adh.DateFinCoti,
                };
            AdherentService.addAdherent(adherent);
            $scope.MessageAdhCree = 'L\'adhérent a bien été enregistré !';
            $timeout(function(){
                $scope.MessageAdhCree ='';
            }, 5000);

        }

        $scope.$watch('adh.DateCoti',function() {
            var date = new Date($scope.adh.DateCoti.getFullYear(), $scope.adh.DateCoti.getMonth(), $scope.adh.DateCoti.getDate());
            date.setYear(date.getFullYear()+1);
            $scope.adh.DateFinCoti = date;
        })

        $scope.$watch('adh.DateNaiss',function() {
            var today = new Date();
            var naissance = $scope.adh.DateNaiss;
            var date = today.getFullYear() - naissance.getFullYear();
            var m = today.getMonth() - naissance.getMonth();
            if (m < 0 || ( m === 0 && today.getDate() > naissance.getDate() ) ) 
            {$scope.adh.Age = date - 1;} else {$scope.adh.Age = date;}
        })



 
    }]);