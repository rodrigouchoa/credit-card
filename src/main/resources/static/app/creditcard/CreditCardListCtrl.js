(function() {
    'use strict';

    angular.module('app').controller('CreditCardListCtrl', ['$http', 'CreditCard', 'ModelTransformer', CreditCardListCtrl]);
    
    function CreditCardListCtrl($http, CreditCard, modelTransformer) {
    	var self = this;
    	self.creditCard = CreditCard.newInstance();
    	loadCreditCards();
    	
    	function loadCreditCards() {
    		$http.get('/api/creditcard').then(function(response) {
    			self.creditCards = modelTransformer.transform(response.data, self.creditCard);
    		});
    	};
    }
   
})();