'use strict';

angular.module('mediatic')
    .factory('EmpruntService', ['$resource', function($resource) {

        var Emprunt = $resource('http://localhost:8080/emprunt/:id', null,
                {
                    'update': { method:'PUT' }
                });

        return {
        
            getEmprunts: function() {
                return Emprunt.query();
            },

            addEmprunt: function(emprunt) {
                var e = new Emprunt();
                e.adherent = emprunt.adherent;
                e.media = emprunt.media;
                e.dateEmprunt = emprunt.dateEmprunt;
                e.dateRetour = emprunt.dateRetour;
                e.dateRetourPrevue = emprunt.dateRetourPrevue;
                console.log(JSON.stringify(e));
                return e.$save();
            },

            updateEmprunt: function(emprunt) {
                var e = this.getEmpruntById(emprunt.id);
                e.adherent = emprunt.adherent;
                e.media = emprunt.media;
                e.dateEmprunt = emprunt.dateEmprunt;
                e.dateRetour = emprunt.dateRetour;
                e.dateRetourPrevue = emprunt.dateRetourPrevue;
                return Emprunt.update({'id':emprunt.id}, e);
            },

            getEmpruntById: function(id) {
            	console.log(id);
                return Emprunt.get({'id':id});
            },

            searchEmprunt: function(criteria) {
                return Emprunt.query(criteria);
            },

            getEmpruntsActuelsOfAdherent: function(adherentId) {
                return this.searchEmprunt({'adherent':adherentId});
            }


        }

    }]);
