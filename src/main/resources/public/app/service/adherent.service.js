'use strict';

angular.module('mediatic')
    .factory('AdherentService', ['$resource', function($resource) {

        var Adherent = $resource('http://192.168.1.65:3000/adherent/:id', null,
                {
                    'update': { method:'PUT' }
                });

        return {
        
            getAdherents: function() {
                return Adherent.query();
            },

            addAdherent: function(adherent) {
                var a = new Adherent();
                a.nom = adherent.nom;
                a.prenom = adherent.prenom;
                a.email = adherent.email;
                a.dateNaissance = adherent.dateNaissance;
                a.rue = adherent.rue;
                a.ville = adherent.ville;
                a.cp = adherent.cp;
                a.dateCotisation = adherent.dateCotisation;
                a.dateFinCotisation = adherent.dateFinCotisation;
                a.montantCotisation = adherent.montantCotisation;
                a.$save();
                return a.id;
            },

            updateAdherent: function(adherent) {
                var a = this.getAdherentById(adherent.id);
                a.nom = adherent.nom;
                a.prenom = adherent.prenom;
                a.email = adherent.email;
                a.dateNaissance = adherent.dateNaissance;
                a.rue = adherent.rue;
                a.ville = adherent.ville;
                a.cp = adherent.cp;
                a.dateCotisation = adherent.dateCotisation;
                a.dateFinCotisation = adherent.dateFinCotisation;
                a.montantCotisation = adherent.montantCotisation;
                Adherent.update({'id':adherent.id}, a);
                return a.id;
            },

            getAdherentById: function(id) {
                return Adherent.get({'id':id});
            },

            searchAdherent: function(criteria) {
                console.log(criteria);
                return Adherent.query(criteria);
            }


        }

    }]);
