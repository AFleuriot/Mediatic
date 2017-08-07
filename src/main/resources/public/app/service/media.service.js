'use strict';

angular.module('mediatic')
    .factory('MediaService', ['$resource', function($resource) {

        var Media = $resource('http://192.168.1.65:3000/media/:id', null,
                {
                    'update': { method:'PUT' }
                });

        return {
        
            getMedias: function() {
                return Media.query();
            },

             getMediaById: function(id) {
                return Media.get({'id':id});
            },

            addMedia: function(media) {
                var m = new Media();
                m.titre = media.titre;
                m.auteur = media.auteur;
                m.type = media.type;
                m.empruntactuel = media.empruntactuel;
                return m.$save();
            },

            updateMedia: function(media) {
                var m = this.getMediaById(media.id);
                m.titre = media.titre;
                m.auteur = media.auteur;
                m.type = media.type;
                m.empruntactuel = media.empruntactuel;
                return Media.update({'id':media.id}, m);
            },

            searchMedia: function(criteria) {
                console.log(criteria);
                return Media.query(criteria);
            }


        }

    }]);
