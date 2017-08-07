'use strict';

angular
    .module('mediatic.VisuMedia',['ngRoute'])
    .controller('VisuMediaCtrl', ['$scope','$location','MediaService','$timeout','AdherentService', 'EmpruntService', 
    function($scope,$location,MediaService, $timeout, AdherentService, EmpruntService){
        $scope.types = ['Livre','CD','DVD']     
        $scope.emprunt ={};
        var location = $location.search();      
        if(location.id==undefined){
            $location.path('/rechercheMedia');
        }
        else{
            var resource = MediaService.getMediaById(location.id);        
            resource.$promise.then(function(response){
                $scope.media = response;
                $scope.media.id = location.id;
                var dateAller = new Date();
                if($scope.media.type=='CD' || $scope.media.type=='DVD'){
                    dateAller.setDate(dateAller.getDate()+15);
                }                    
                else{
                    dateAller.setDate(dateAller.getDate()+30);
                }                
                $scope.emprunt.dateRetour = dateAller;
                console.log($scope.media);
            }); 

            resource = AdherentService.getAdherents();
            resource.$promise.then(function(response){                
                $scope.adherents = response;
                $scope.emprunt.selectedAdherent = $scope.adherents[0];
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

        $scope.emprunt.datePret = new Date();
                
        $scope.$watch('emprunt.datePret',function(newvalue, oldvalue){
            var dateAller = new Date(newvalue);                        
            if($scope.media != undefined){                                
                if($scope.media.type=='CD' || $scope.media.type=='DVD'){
                    dateAller.setDate(dateAller.getDate()+15);
                }                    
                else{
                    dateAller.setDate(dateAller.getDate()+30);
                }                                            
                $scope.emprunt.dateRetour = new Date(dateAller);
            }
        });
        
        $scope.submit = function(){
            var emprunt={};
            emprunt.adherent = parseInt($scope.emprunt.selectedAdherent.id);
            emprunt.media = parseInt($scope.media.id);
            emprunt.dateEmprunt = $scope.emprunt.datePret;
            emprunt.dateRetour = null;
            emprunt.dateRetourPrevue = $scope.emprunt.dateRetour;
            EmpruntService.addEmprunt(emprunt).then(function(resp) {
                $scope.media.empruntactuel=resp.id;
                MediaService.updateMedia($scope.media);
            });
        };

        $scope.changeAdherent =function(adherent){
            $scope.selectedAdherent = adherent;
        }

        $scope.submitMedia = function(){
            $("#myModal").modal('hide');
            MediaService.updateMedia($scope.media);
            $timeout(function () {
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
            console.log($scope.datepret);
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