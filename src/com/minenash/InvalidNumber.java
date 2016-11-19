package com.minenash;
/**
 * <h1>Invalid Number</h1>
 * The Invalid Number class adds the 
 * exception, "InvalidNumber". This 
 * exception was made for the NonStandard 
 * Integer Class(nsint). It is thrown 
 * when the base is less than 1, higher 
 * than 10, or if a digit is higher than 
 * the base allows.
 * 
 * @author Minenash
 * @version 1.1
 * @since 2016-11-13
 * @see nsint
 */
public class InvalidNumber extends Exception {
	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The NonStandard Integer that threw the error.
	 */
	private final nsint invalidNonStandardInteger;
	
	/**
	 * Constructs a InvalidNumber with no detail message.
	 */
	public InvalidNumber(){
		invalidNonStandardInteger = null;
	}	
	/**
	 * Constructs a InvalidNumber with a detail message.
	 * @param message A detailed message of the exception.
	 */
	public InvalidNumber(String message){
	  super(message);
	  invalidNonStandardInteger = null;
	 }
	/**
	 * Constructs a InvalidNumber with no detail message, and stores the invalid NonStandard Integer.
	 * @param input The invalid NonStandard Integer that threw the exception.
	 */
	public InvalidNumber(nsint input){
		  super();
		  invalidNonStandardInteger = input;
	}
	/**
	 * Constructs a InvalidNumber with a detail message, and stores the invalid NonStandard Integer.
	 * @param message A detailed message of the exception.
	 * @param input The invalid NonStandard Integer that threw the exception.
	 */
	public InvalidNumber(String message, nsint input){
		  super(message);
		  invalidNonStandardInteger = input;
	}
	/**
	 *  Returns the invalidNonStandardInteger.
	 * @return invalidNonStandardInteger
	 */
	public nsint getInvalidNonStandardInteger() {
		return invalidNonStandardInteger;
	}
}