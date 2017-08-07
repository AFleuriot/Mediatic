'use strict';

angular.module('mediatic')
    .factory('EmpruntService', ['$resource', function($resource) {

        var Emprunt = $resource('http://192.168.1.65:3000/emprunt/:id', null,
                {
                    'update': { method:'PUT' }
                });

        return {
        
            getEmprunts: function() {
                return Emprunt.query();
            },

            addEmprunt: function(emprunt) {
                var e = new Adherent();
                e.adherent = emprunt.adherent;
                e.media = emprunt.media;
                e.dateEmprunt = emprunt.dateEmprunt;
                e.dateRetour = emprunt.dateRetour;
                e.dateRetourPrevue = emprunt.dateRetourPrevue;
                e.$save();
            },

            updateAdherent: function(emprunt) {
                var e = this.getEmpruntById(emprunt.id);
                e.adherent = emprunt.adherent;
                e.media = emprunt.media;
                e.dateEmprunt = emprunt.dateEmprunt;
                e.dateRetour = emprunt.dateRetour;
                e.dateRetourPrevue = emprunt.dateRetourPrevue;
                Adherent.update({'id':emprunt.id}, e);
            },

            getEmpruntById: function(id) {
                return Emprunt.get({'id':id});
            },

            searchEmprunt: function(criteria) {
                return Emprunt.query(criteria);
            },

            getEmpruntsActuelsOfAdherents: function(adherentId) {
                return this.searchEmprunt({'adherent':adherentId, 'dateRetour':null});
            }


        }

    }]);
