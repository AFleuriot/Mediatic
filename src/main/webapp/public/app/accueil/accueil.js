'use strict';

angular
    .module('mediatic.accueil',['ngRoute'])
    .controller('accueilCtrl', ['$scope','$location','AccueilService', '$http', function($scope, $location, AccueilService, $http){

        $scope.badLogin = '';
        $scope.user = {};

      /* $scope.submit = function() {
    
            if (($scope.user.username == 'admin') && ($scope.user.password == 'admin1234')) {
   
                AccueilService.storeUser($scope.user);
                $location.path('/rechercheMedia');          
            } else {
              
                $location.path('/accueil');
                $scope.badLogin = 'Le login ou le mot de passe est incorrect'
            }
          
        } */  
        
        $scope.submit = function() { console.log('Lancement');
        	$http({
        		method : 'POST',
        		url : '/login',
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        		transformRequest: function(obj) {
        			var str = [];
        			for(var p in obj)
        				str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        			return str.join('&');
        		},
        		data: {username: $scope.user.username, password: $scope.user.password}
        	}).then(function successCallBack(response) {
        		console.log('Success');
        		 $location.path('/rechercheMedia'); 
        	}, function errorCallback(response) {
        		console.log('Error');
        		$location.path('/accueil');
                $scope.badLogin = 'Le login ou le mot de passe est incorrect'
        	});
        }
        
        
        
        
    }]
);    