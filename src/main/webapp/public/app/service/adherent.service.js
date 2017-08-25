'use strict';

angular.module('mediatic')
    .factory('AdherentService', ['$resource', '$http', function($resource, $http) {

        var Adherent = $resource('/adherent/:id', null,
                {
                    'update': { method:'PUT' }
                });

        return {
        
            getAdherents: function() {         	
                return Adherent.query();
            },

            addAdherent: function(adherent) {
                /*var a = new Adherent();
                a.nom = adherent.nom;
                a.prenom = adherent.prenom;
                a.email = adherent.email;
                a.dateNaissance = adherent.dateNaissance;
                a.rue = adherent.rue;
                a.ville = adherent.ville;
                a.cp = adherent.cp;
                a.dateCotisation = adherent.dateCotisation;
                a.dateFinCotisation = adherent.dateFinCotisation;
                a.montantCotisation = adherent.montantCotisation;*/
                return Adherent.save(adherent);
            },

            updateAdherent: function(adherent) {
                /*var a = this.getAdherentById(adherent.id);
                a.nom = adherent.nom;
                a.prenom = adherent.prenom;
                a.email = adherent.email;
                a.dateNaissance = adherent.dateNaissance;
                a.rue = adherent.rue;
                a.ville = adherent.ville;
                a.cp = adherent.cp;
                a.dateCotisation = adherent.dateCotisation;
                a.dateFinCotisation = adherent.dateFinCotisation;
                a.montantCotisation = adherent.montantCotisation;*/
                return Adherent.update({'id':adherent.id}, adherent);
            },

            getAdherentById: function(id) {
                return Adherent.get({'id':id});
            },

            searchAdherent: function(criteria) {
                return Adherent.query(criteria);
            }


        }

    }]);
