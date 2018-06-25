(function() {
    'use strict';
    //this will make possible to transform raw data into domain objects
    angular.module('app').factory('ModelTransformer', function() {
    	function ModelTransformer() {
    		this.transform = function(data, model) {
    			if (angular.isArray(data)) {
    	    		var models = [];
    	    		angular.forEach(data, function(object) {
    	    			models.push(model.build(object));
    	    		});
    	    		return models;
    	    	} else {
    	    		return model.build(data);
    	    	}    			
    		};
    	}
    	return new ModelTransformer();
    });   
   
})();