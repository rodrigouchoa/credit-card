package com.rodrigouchoa.creditcard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Just the main controller
 * @author rodrigo.uchoa@gmail.com
 *
 */
@Controller
public class CreditCardController {
	
	@RequestMapping(value="/",method = RequestMethod.GET)
    public String index(){
        return "index.html";
    }

}
