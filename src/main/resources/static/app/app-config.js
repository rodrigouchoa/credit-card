(function() {
    'use strict';

    angular.module('app').config(['$routeProvider', function config($routeProvider) {
    	$routeProvider
    		.when('/creditcard', {
    			templateUrl: '/app/creditcard/creditcard-list.html',
    			controller: 'CreditCardListCtrl',
    			controllerAs: 'listCtrl'
    		})
    		.when('/creditcard/add', {
    			templateUrl: '/app/creditcard/creditcard-add.html',
    			controller: 'CreditCardAddCtrl',
    			controllerAs: 'addCtrl'
    		})
    		.otherwise( {redirectTo: '/'});		
    }]);
   
})();