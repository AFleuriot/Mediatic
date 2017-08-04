'use strict';

angular.module('mediatic')
    .factory('MediaService', ['$resource', function($resource) {

        var Media = $resource('http://192.168.1.65:3000/media/:id');

        return {
        
            getMedias: function() {
                return Media.query();
            },

            addMedia: function(media) {
                var m = new Media();
                m.titre = media.titre;
                m.auteur = media.auteur;
                m.type = media.type;
                m.$save();
            },

            getMediaById: function(id) {
                return Media.get({'id':id});
            }


        }

    }]);
