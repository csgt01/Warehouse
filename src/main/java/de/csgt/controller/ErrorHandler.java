package de.csgt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**  
 * All controllers should extend this class and have appropriate error handling and mock error tests.
 */
public abstract class ErrorHandler {

    /**
	 * Anything not handled above.
	 */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
    @ExceptionHandler(Throwable.class)
    @ResponseBody 
    public String handleUnexpected(Throwable exception, HttpServletRequest request) {
    	String x = "Unexpected Exception for request " + request.getRequestURL() + "  " + exception.getMessage();
		System.out.println(x);
		return x;
    }

}
