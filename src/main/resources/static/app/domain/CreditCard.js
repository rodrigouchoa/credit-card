(function() {
    'use strict';
    
    angular.module('app').factory('CreditCard', function() {
    	function CreditCard(id, number, name, limit, balance) {
        	this.id = id;
        	this.number = number;
        	this.name = name;
        	this.limit = limit;
        	this.balance = balance;
        }     
        
        CreditCard.prototype.build = function(data) {
        	if (!data) {
        		return null;
        	}
        	if (angular.equals({}, data)) {
        		return new CreditCard();
        	}
        	return new CreditCard(data.id, data.number, data.name, data.limit, data.balance);        	
        };
        
        return {
        	newInstance: function() {return new CreditCard();}
        };        	
    }); 
    
})();