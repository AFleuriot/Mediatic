'use strict';

angular.module('mediatic')
    .factory('AdherentService', ['$resource', function($resource) {

        var adherentsList = [];

        var listeUsers = $resource('https://jsonplaceholder.typicode.com/users').query();

        listeUsers.forEach(function(user) {
            adherentsList.push({'id':user.id, 'nom':user.name, 'prenom':user.username, 'email':user.email, 'dateNaissance':'01/01/1980', 'rue':user.address.street, 'ville':user.address.city, 'cp':user.address.zipcode, 'dateCotisation':'02/02/2017', 'montant':10});
        });

        return {
        
            getAdherents: function() {
                return adherentsList;
            },

            addAdherent: function(adherent) {
                adherentsList.push(adherent);
            },

            getAdherentById: function(id) {
                getAdherents().forEach(function(adherent) {
                    if (id==adherent.id) {
                        return adherent;
                    }
                });
            }


        }

    }]);
