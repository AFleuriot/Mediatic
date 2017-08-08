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
                console.log(resource);                 
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

            resource = MediaService.getMedias();
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
                console.log($scope.UserEmprunts);
            });
        } 

        $scope.$watch('emprunt.dateEmprunt',function(newvalue, oldvalue){
            var dateAller = new Date(newvalue);
            if($scope.emprunt.selectedMedia=='CD' || $scope.emprunt.selectedMedia=='DVD'){
                dateAller.setDate(dateAller.getDate()+15);
            }                    
            else{
                dateAller.setDate(dateAller.getDate()+30);
            }                                            
            $scope.emprunt.dateRetour = new Date(dateAller);
        });

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
                var date = new Date(dateBase.getFullYear(), dateBase.getMonth(), dateBase.getDate());
                console.log(date);
                date.setYear(date.getFullYear()+1);
                $scope.finCotisation = date.toLocaleDateString();   
            }
        })

        $scope.submit = function(){ 
            $("#myModalun").modal('hide');
            AdherentService.updateAdherent($scope.adherent);
            $timeout(function () {
                $location.path('/rechercheAdherent');
            }, 500);    
        }

        $scope.changeMedia = function(media){            
            $scope.selectedMedia = media;
            console.log( $scope.selectedMedia);
            var dateAller = new Date($scope.emprunt.dateEmprunt);
            if($scope.emprunt.selectedMedia.type=='CD' || $scope.emprunt.selectedMedia.type=='DVD'){
                dateAller.setDate(dateAller.getDate()+15);
            }                    
            else{
                dateAller.setDate(dateAller.getDate()+30);
            }                                            
            $scope.emprunt.dateRetour = new Date(dateAller);
        }

        $scope.submitEmprunt = function(){
            var emprunt={};
            emprunt.adherent = parseInt($scope.adherent.id);

            emprunt.media = parseInt($scope.emprunt.selectedMedia.id);
            
            emprunt.dateEmprunt = $scope.emprunt.dateEmprunt;
            emprunt.dateRetour = null;
            emprunt.dateRetourPrevue = $scope.emprunt.dateRetour;
            EmpruntService.addEmprunt(emprunt).then(function(resp) {
               
                var mediaEmprunte = MediaService.getMediaById(emprunt.media);
                mediaEmprunte.empruntactuel = resp.id;
                MediaService.updateMedia(mediaEmprunte);

            });
        }

        $scope.verifierDate = function(emprunt){
            if(new Date(emprunt.dateRetourPrevue)<new Date() && emprunt.dateRetour == null){
                return 'list-group-item-danger';
            }
            else{
                return 'list-group-item-success';
            }
        }

        $scope.emprunter = function(emprunt){
            return emprunt.media.titre + ' par ' + emprunt.media.auteur + ' (' + emprunt.media.type + ') (Date retour prévue le '+ new Date(emprunt.dateRetourPrevue).toLocaleDateString()+').';
        }


        $scope.rendreMedia = function() {
            console.log($scope.emprunt.selectedRendreMedia)
            var nouveaumedia = $scope.emprunt.selectedRendreMedia.media;
            var rendreemprunt = $scope.emprunt.selectedRendreMedia; 

            rendreemprunt.adherent = rendreemprunt.adherent.id;
            rendreemprunt.media = rendreemprunt.media.id;
            rendreemprunt.dateRetour = new Date();
            EmpruntService.updateEmprunt(rendreemprunt);   

            nouveaumedia.empruntactuel = null;
            MediaService.updateMedia(nouveaumedia);
            console.log($scope.emprunt.selectedRendreMedia)
        

        }

    }]
);    