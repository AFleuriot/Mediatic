'use strict';

// Declare app level module which depends on views, and components
angular.module('mediatic', [
  'ngRoute',
  'ngMessages',
  'ngResource',
  'ngStorage',
  'mediatic.RechercheMedia',
  'mediatic.RechercheAdherent',
  'mediatic.CreationMedia',
  'mediatic.CreationAdherent',
  'mediatic.VisuMedia',
  'mediatic.VisuAdherent',
  'mediatic.accueil'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider
  .when('/accueil', {
    templateUrl: 'accueil/accueil.html',
    controller: 'accueilCtrl',
    controllerAs: "ctrl"
  })  
  .when('/rechercheMedia', {
    templateUrl: 'RechercheMedia/RechercheMedia.html',
    controller: 'RechercheMediaCtrl',
    controllerAs: "ctrl"
  })  
  .when('/rechercheAdherent', {
    templateUrl: 'RechercheAdherent/RechercheAdherent.html',
    controller: 'RechercheAdherentCtrl',
    controllerAs: "ctrl"
  })  
  .when('/creationMedia', {
    templateUrl: 'CreationMedia/CreationMedia.html',
    controller: 'CreationMediaCtrl',
    controllerAs: "ctrl"
  })  
  .when('/creationAdherent', {
    templateUrl: 'CreationAdherent/CreationAdherent.html',
    controller: 'CreationAdherentCtrl',
    controllerAs: "ctrl"
  })  
  .when('/visuMedia', {
    templateUrl: 'VisuMedia/visualiser_media.html',
    controller: 'VisuMediaCtrl',
    controllerAs: "ctrl"
  })  
  .when('/visuAdherent', {
    templateUrl: 'VisuAdherent/visualiser_adherent.html',
    controller: 'VisuAdherentCtrl',
    controllerAs: "ctrl"
  }) 
  .otherwise({redirectTo: '/accueil'});
}])
.run(function($http, $location, $rootScope) {
	
	var getUser = function(){
    	
        if($location.path() == "/accueil") {
        	return true;
        }
        return $http({
            	method : 'GET',
            	url : '/api/authentication/user'
            	                	
        });
            
        }   
        
        var verifLogin = function() {
        	var user = getUser();
        	if (typeof user === "boolean" && user) {
        		return;
        	}
        	user.then(function successCallBack(response) {
       		 	$rootScope.user = response.data;
       		 	if ($location.path()=="/creationAdherent" || $location.path()=="/creationMedia") {
       		 		
       		 		if (!$rootScope.isAdmin()) {
       		 			$location.path("/accueil");
       		 		}
       		 	}
           	}, function errorCallback(response) {
                $location.path("/accueil");
           	});

        }
        
    $rootScope.isAdmin = function() {
    	var admin = false;
    	if ($rootScope.user==undefined) {
    		return false;
    	}
    	$rootScope.user.authorities.forEach(function(auth) {
	 			if (auth.authority=='ADMIN') {
	 				admin=true;
	 			}
	 		});
	 	return admin;
    }
        
    $rootScope.$on('$routeChangeSuccess', function () {
    	verifLogin();
    });
});
