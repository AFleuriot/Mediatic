'use strict';

angular
    .module('mediatic.VisuMedia',['ngRoute'])
    .controller('VisuMediaCtrl', ['$scope','$location','MediaService','$timeout','AdherentService', 'EmpruntService', 
    function($scope,$location,MediaService, $timeout, AdherentService, EmpruntService){
        $scope.types = ['Livre','CD','DVD']     
        var location = $location.search();      
        if(location.id==undefined){
            $location.path('/rechercheMedia');
        }
        else{
            var resource = MediaService.getMediaById(location.id);        
            resource.$promise.then(function(response){
                $scope.media = response;
                $scope.media.id = location.id;
            }); 

            resource = AdherentService.getAdherents();
            resource.$promise.then(function(response){                
                $scope.adherents = response;
                $scope.selectedAdherent = $scope.adherents[0];
            });

            resource = EmpruntService.searchEmprunt({'media':location.id});
            resource.$promise.then(function(response){                
                $scope.emprunts = response;                
                $scope.emprunts.forEach(function(element) {
                    resource = AdherentService.getAdherentById(element.adherent);
                    element.adherent = resource;
                    resource = MediaService.getMediaById(element.media);
                    element.media = resource;
                }, this);
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
            console.log($scope.selectedAdherent.id);
            var emprunt={};
            emprunt.adherent = parseInt($scope.selectedAdherent.id);
            emprunt.media = parseInt($scope.media.id);
            emprunt.dateEmprunt = $scope.datePret;
            emprunt.dateRetour = null;
            emprunt.dateRetourPrevue = $scope.dateRetour;
            EmpruntService.addEmprunt(emprunt);
        };

        $scope.changeAdherent =function(adherent){
            $scope.selectedAdherent = adherent;
        }

        $scope.submitMedia = function(){
            $("#myModal").modal('hide');
            $timeout(function () {
                MediaService.updateMedia($scope.media);
                $location.path('/rechercheMedia');
            }, 500);            
        }

        $scope.rechercheAdherent = function(){
            var combinaison='';
            var resultat = []
            $scope.adherents.forEach(function(element) {                
                combinaison = element.nom + ' ' + element.prenom
                if(combinaison.contains($scope.emprunteur)){
                    resultat.push(element);
                }
            }, this);
            return resultat;
        }

        $scope.changerDate = function(){
            var dateAller = $scope.datePret;
            if($scope.media != undefined){
                if($scope.media.type=='CD' || $scope.media.type=='DVD'){
                    dateAller.setDate(dateAller.getDate()+15);
                }                    
                else{
                    dateAller.setDate(dateAller.getDate()+30);
                }                
                $scope.dateRetour = dateAller;
            }
        };

        $scope.emprunter = function(emprunt){
            return emprunt.adherent.nom +  ' ' + emprunt.adherent.prenom + ' a emprunté ce média (Date retour prévue le '+ new Date(emprunt.dateRetourPrevue).toLocaleDateString()+').';
        }

        $scope.verifierDate = function(emprunt){
            if(new Date(emprunt.dateRetourPrevue)<new Date() && emprunt.dateRetour == null){
                return 'list-group-item-danger';
            }
            else{
                return 'list-group-item-success';
            }
        }
    }]
);    