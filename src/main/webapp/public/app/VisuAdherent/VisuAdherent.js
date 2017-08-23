'use strict';

angular
    .module('mediatic.VisuAdherent',['ngRoute'])
    .controller('VisuAdherentCtrl', ['$scope', '$location','AdherentService','$timeout','MediaService', 'EmpruntService',
    function($scope,$location,AdherentService,$timeout,MediaService, EmpruntService){
        var location = $location.search();      
        $scope.emprunt = {};
        
        if(location.id==undefined){
            $location.path('/rechercheAdherent');
        }
        else{
            var resource = AdherentService.getAdherentById(location.id);        
            resource.$promise.then(function(response){
                              
                $scope.adherent = response;  
                $scope.adherent.id = location.id;
                $scope.adherent.cp = parseInt($scope.adherent.cp);
                $scope.adherent.dateNaissance = new Date($scope.adherent.dateNaissance);
                $scope.adherent.dateCotisation = new Date($scope.adherent.dateCotisation);
                $scope.adherent.montantCotisation = parseInt($scope.adherent.montantCotisation);
                $scope.emprunt.dateEmprunt = new Date();
                var temp = new Date();
                temp.setDate(temp.getDate()+15);     
                $scope.emprunt.dateRetour = temp;                
            }); 
            var obj = {};
            obj.emprunte="true";
            resource = MediaService.searchMedia(obj);
            resource.$promise.then(function(response){
                $scope.medias = response;
                $scope.emprunt.selectedMedia = $scope.medias[0]
            })

            resource = EmpruntService.searchEmprunt({'adherent':location.id});
            resource.$promise.then(function(response){
                $scope.UserEmprunts = response;
                    $scope.UserEmprunts.forEach(function(element) {

                    resource = AdherentService.getAdherentById(element.adherent);
                    element.adherent = resource;
                    resource = MediaService.getMediaById(element.media);
                    element.media = resource;

                    }, this);
               
            });




            
        }
// MàJ date retour emprunt  =============================================== 
        var changerDateRetour = function(){
            
            var dateAller = new Date($scope.emprunt.dateEmprunt.getFullYear(),$scope.emprunt.dateEmprunt.getMonth(),$scope.emprunt.dateEmprunt.getDate());
            var typeMedia = $scope.emprunt.selectedMedia.type;
            var nbreJoursEmprunt = $scope.dureeEmprunt;
            if(typeMedia=='Livre'){
                nbreJoursEmprunt=30;
            } else {
                nbreJoursEmprunt=15;
            }
            dateAller.setDate(dateAller.getDate()+nbreJoursEmprunt);
            $scope.emprunt.dateRetour = dateAller;
        };

        $scope.$watch('emprunt.selectedMedia', changerDateRetour,true);
        $scope.$watch('emprunt.dateEmprunt', changerDateRetour, true);

// Voir l'âge de l'adhérent =============================================== 
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

// Voir date fin coti =============================================== 
        $scope.$watch('adherent.dateCotisation',function() {
            if($scope.adherent != undefined){
                var dateBase = new Date($scope.adherent.dateCotisation);
                var date = new Date(dateBase.getFullYear(), dateBase.getMonth(), dateBase.getDate());
               
                date.setYear(date.getFullYear()+1);
                $scope.finCotisation = date.toLocaleDateString();   
            }
        })
// Vérification date retour prévue dépassé ================================ 
        $scope.verifierDate = function(emprunt){
            if(new Date(emprunt.dateRetourPrevue)<new Date() && emprunt.dateRetour == null){
                return 'list-group-item-danger';
            }
            else{
                return 'list-group-item-success';
            }
        }
        
// Modal  =============================================== 
        $scope.submit = function(){ 
            $("#myModalun").modal('hide');
            AdherentService.updateAdherent($scope.adherent);
            $timeout(function () {
                $location.path('/rechercheAdherent');
            }, 500);    
        }

// Emprunter un média =============================================== 
$scope.MessMediaDejaEmprunte = '';
$scope.MediaBienEmprunte = '';

        $scope.submitEmprunt = function(){
            
            var emprunt={};
            emprunt.adherent = parseInt($scope.adherent.id);

            emprunt.media = parseInt($scope.emprunt.selectedMedia.id);
            
            emprunt.dateEmprunt = $scope.emprunt.dateEmprunt;
            emprunt.dateRetour = null;
            emprunt.dateRetourPrevue = $scope.emprunt.dateRetour;            
            EmpruntService.addEmprunt(emprunt).then(function (resp) {
               
                //$scope.emprunt.selectedMedia.empruntactuel = resp.id;
                //MediaService.updateMedia($scope.emprunt.selectedMedia);
                $scope.MediaBienEmprunte ='Le média a bien été emprunté !';
                $timeout(function () {
                $scope.MediaBienEmprunte ='';
            }, 2500);
            })
          
        }


        $scope.$watch('emprunt.selectedMedia', function(){
            if($scope.emprunt.selectedMedia.empruntactuel != null){
            $scope.MessMediaDejaEmprunte = 'Ce média a déjà été emprunté !';
                $timeout(function () {
                $scope.MessMediaDejaEmprunte ='';
            }, 2500);
            }
        })

        $scope.emprunter = function(emprunt){
            return emprunt.media.titre + ' par ' + emprunt.media.auteur + ' (' + emprunt.media.type + ') (Date retour prévue le '+ new Date(emprunt.dateRetourPrevue).toLocaleDateString()+').';
        }

// Rendre un média ============================================
        $scope.rendreMedia = function() {
            
            var nouveaumedia = $scope.emprunt.selectedRendreMedia.media;
            var rendreemprunt = $scope.emprunt.selectedRendreMedia; 

            rendreemprunt.adherent = rendreemprunt.adherent.id;
            rendreemprunt.media = rendreemprunt.media.id;
            rendreemprunt.dateRetour = new Date();
            EmpruntService.updateEmprunt(rendreemprunt);   

            nouveaumedia.empruntactuel = null;
            MediaService.updateMedia(nouveaumedia);
            
        

        }

    }]
);    