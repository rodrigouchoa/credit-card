(function() {
    'use strict';

    angular.module('app').controller('CreditCardAddCtrl', ['$http', 'CreditCard', 'ModelTransformer', '$timeout', '$scope', '$location', CreditCardAddCtrl]);
    
    function CreditCardAddCtrl($http, CreditCard, modelTransformer, $timeout, $scope, $location) {
    	var self = this;
    	self.creditCard = CreditCard.newInstance();
    	
    	
    	//called by the submit button
    	self.submit = function(creditCard) {
    		if ($scope.formCc.$invalid) {
    			return; //form has validation errors
    		}
    		
    		$http.post('/api/creditcard', creditCard)
    			.success(function(data, status, headers, config) {
    				self.creditCard = CreditCard.newInstance();
    				self.showMsg = true;
    				self.successMsg = "Credit Card Added Successfully!"
    				$timeout(function() {
    					self.showMsg = false;
    				}, 5000);
    			})
    			.error(function(data, status, headers, config) {
    				self.errorMsg = data.message;
    			});
    			
    	};
    	
    	self.cancel = function(creditCard) {
    		$location.path("/");
    	};
    }
   
})();