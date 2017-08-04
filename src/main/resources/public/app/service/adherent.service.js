'use strict';

angular.module('mediatic')
    .factory('AdherentService', ['$resource', function($resource) {

        var Adherent = $resource('http://192.168.1.65:3000/adherent/:id');

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
                a.montantCotisation = adherent.montantCotisation;
                a.$save();
            },

            getAdherentById: function(id) {
                return Adherent.get({'id':id});
            }


        }

    }]);