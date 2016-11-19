package com.minenash;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
/**
 * <h1>NonStandard Integer</h1>
 * The NonStandard Integer class adds the
 * ability to have integers in other bases.
 * 
 * @author Minenash
 * @version 1.2.3
 * @since 2016-11-13
 *
 */
public class nsint extends Number{
	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = -6790000516630866961L;
	
	//Fields
	
	/**
	 * The actual number.
	 */
	private int number;
	/**
	 * The base the number is in.
	 */
	private byte base;
	/**
	 * A constant holding the value of 0₁₀.
	 */
	public static final nsint BLANK;
	/**
	 * A constant holding the value of -1₀.
	 */
	public static final nsint INVALID;
	static{
		nsint tmp = null;
		try{
			tmp = new nsint();
		}
		catch(InvalidNumber in){}
		BLANK = tmp;
	}
	static{
		nsint tmp = null;
		try{
			tmp = new nsint(-1,(byte)0, false);
		}
		catch(InvalidNumber in){}
		INVALID = tmp;
	}
	
	//Constructors
	
	/**
	 * Creates a Blank NonStandard Integer(0₁₀); runs validate.
	 * @throws InvalidNumber Will throw if the base is less than 1, higher than 10, or if a digit is higher than the base allows.
	 * @see InvalidNumber
	 * @see #validate(int, int)
	 */
	public nsint() throws InvalidNumber{
		this(0);
	}
	/**
	 * Creates a Blank NonStandard Integer(0₁₀); may run validate.
	 * @param validate Will it run validate()?
	 * @throws InvalidNumber Will throw if the base is less than 1, higher than 10, or if a digit is higher than the base allows
	 * @see InvalidNumber
	 * @see #validate(int, int)
	 */	
	public nsint(boolean validate) throws InvalidNumber {
		this(0, validate);
	}
	/**
	 * Creates a NonStandard Integer with the number given and the base of 10; will runs validate.
	 * @param number - The actual number
	 * @throws InvalidNumber Will throw if the base is less than 1, higher than 10, or if a digit is higher than the base allows.
	 * @see InvalidNumber
	 * @see #validate(int, int)
	 */
	public nsint(int number) throws InvalidNumber{
		this(number, 10, true);
	}
	/**
	 * Creates a NonStandard Integer with the number given and the base of 10; may run validate.
	 * @param number - The number the NonStandard Integer will be.
	 * @param validate Will it run validate()?
	 * @throws InvalidNumber Will throw if the base is less than 1, higher than 10, or if a digit is higher than the base allows.
	 * @see InvalidNumber
	 * @see #validate(int, int)
	 */
	public nsint(int number, boolean validate) throws InvalidNumber{
		this(number, 10, validate);
	}
	/**
	 * Creates a NonStandard Integer with the number and base given; runs validate.
	 * @param number - The number the NonStandard Integer will be.
	 * @param base - The base the NonStandard Integer will be in.
	 * @throws InvalidNumber Will throw if the base is less than 1, higher than 10, or if a digit is higher than the base allows.
	 * @see InvalidNumber
	 * @see #validate(int, int)
	 */
	public nsint(int number, int base) throws InvalidNumber{
		this(number, base, true);
	}
	/**
	 * Creates a NonStandard Integer with the number and base given; may run validate.
	 * @param number - The number the NonStandard Integer will be.
	 * @param base - The base the NonStandard Integer will be in.
	 * @param validate Will it run validate()?
	 * @throws InvalidNumber Will throw if the base is less than 1, higher than 10, or if a digit is higher than the base allows.
	 * @see InvalidNumber
	 * @see #validate(int, int)
	 */
	public nsint(int number, int base, boolean validate) throws InvalidNumber{
		this.number = number;
		this.base = (byte)base;
		if(validate == true){
			validate(number, base);
		}
	}
	
	//Methods - ToDecimal & ToBase & Validate
	
	/**
	 * Turns a NonStandard Integer into a a decimal number.
	 * @return The decimal form of the NonStandard Integer.
	 */
	public int toDecimal(){
		return Integer.parseInt(Integer.toString(Integer.parseInt(new String("" + number), base), 10));
	}
	
	/**
	 * Turns a decimal number into a NonStandard Integer.
	 * @param number Decimal number being converted
	 * @param base The base the NonStandard Integer will be in.
	 * @return The converted decimal number 
	 */
	public static nsint toBase(int number, byte base){
		nsint output = BLANK;
		output.number = Integer.parseInt(Integer.toString(Integer.parseInt(new String("" + number), 10), base));
		output.base = base;
		return output;
	}
	
	/**
	 * Turns a NonStandard Integer into another NonStandard Integer with the same or different base.
	 * @param input NonStandard Integer being converted
	 * @param base The base the new NonStandard Integer will be in.
	 * @return The converted NonStandard Integer 
	 */
	public static nsint toBase(nsint input, byte base){
		nsint output = BLANK;
		output.number = Integer.parseInt(Integer.toString(Integer.parseInt(new String("" + input.number), input.base ), base));
		output.base = base;
		return output;
	}
	
	/**
	 * Makes sure that the NonStandard Integer is valid.
	 * @param number The number being validated
	 * @param base The base the number is in
	 * @throws InvalidNumber Will throw if the base is less than 1, higher than 10, or if a digit is higher than the base allows. Will throw if the base is less than 1, higher than 10, or if a digit is higher than the base allows.
	 * @see InvalidNumber 
	 */
	public void validate(int number, int base) throws InvalidNumber{
		if(base <= 0 || base > 10){
			throw new InvalidNumber("Base must be between 1 and 10. Givin:" + base);
		}
		String num = "" + number;
		char bas =  (char)(base + '0');
		for(int i = 0; i < num.length(); i++){
			
			if(num.charAt(i) > bas){
				throw new InvalidNumber("Digit(s) higher than allowed with current base.");
			}
		}
	}
	
	//Methods - Basic Operations
	
	/**
	 * Checks if the two NonStandard Integer are equal.
	 * @param other This is the number being checked to the original.
	 * @return True if and only if the specified NonStandard Integer value is numerically equal to this NonStandard Integer.
	 */
	public boolean equals(nsint other){
		return this.toDecimal() == other.toDecimal();
	}
	/**
	 * Adds two NonStandard Integer together.
	 * @param other Value to be added to this NonStandard Integer.
	 * @return this + other
	 */
	public nsint add(nsint other){
		return toBase(this.toDecimal() + other.toDecimal(), base);
	}
	/**
	 * Subtracts two NonStandard Integer together.
	 * @param other Value to be subtracted to this NonStandard Integer.
	 * @return this - other
	 */
	public nsint subtract(nsint other){
		return toBase(this.toDecimal() - other.toDecimal(), base);
	}
	/**
	 * Multiples two NonStandard Integer together.
	 * @param other Value to be multiplied to this NonStandard Integer.
	 * @return this * other
	 */
	public nsint multiply(nsint other){
		return toBase(this.toDecimal() + other.toDecimal(), base);
	}
	/**
	 * Divides two NonStandard Integer together.
	 * @param other Value to be divided to this NonStandard Integer.
	 * @return this / other
	 */
	public nsint divide(nsint other){
		return toBase(this.toDecimal() + other.toDecimal(), base);
	}
	/**
	 * Modulate two NonStandard Integer together.
	 * @param other Value to be modulated to this NonStandard Integer.
	 * @return this % other
	 */
	public nsint mod(nsint other){
		return toBase(this.toDecimal() % other.toDecimal(), base);
	}
	/**
	 * Shifts left this NonStandard Integer by the amount given in other.
	 * @param other Value to shifted left the NonStandard Integer by.
	 * @return this shift-left other
	 */
	public int shiftLeft(nsint other){
		return this.toDecimal() << other.toDecimal();
	}
	/**
	 * Shifts left this NonStandard Integer by the amount given in other.
	 * @param other Value to shifted left the NonStandard Integer by.
	 * @param wantNsint True if the user wants a nsint instead of an int.
	 * @return Number - this shift-left other
	 */
	public Number shifLeft(nsint other, boolean wantNsint){
		if(wantNsint){
			try {
				return new nsint(this.toDecimal() << other.toDecimal(), 2);
			} catch (InvalidNumber e) {
				e.printStackTrace();
				return INVALID;
			}
		}
		else{
			return new Integer(this.toDecimal() << other.toDecimal());
		}
	}
	/**
	 * Shifts right this NonStandard Integer by the amount given in other.
	 * @param other Value to shifted right the NonStandard Integer by.
	 * @return this shift-right other
	 */
	public int shiftRight(nsint other){
		return this.toDecimal() >> other.toDecimal();
	}
	/**
	 * Shifts right this NonStandard Integer by the amount given in other.
	 * @param other Value to shifted right the NonStandard Integer by.
	 * @param wantNsint True if the user wants a nsint instead of an int.
	 * @return this shift-right other
	 */
	public Number shifRight(nsint other, boolean wantNsint){
		if(wantNsint){
			try {
				return new nsint(this.toDecimal() >> other.toDecimal(), 2);
			} catch (InvalidNumber e) {
				e.printStackTrace();
				return INVALID;
			}
		}
		else{
			return new Integer(this.toDecimal() >> other.toDecimal());
		}
	}
	
	//Methods - Advance Operations
	
	/**
	 * Returns the estimated value of the NonStandard Integer raised to the power of the argument.
	 * @param other The Exponent
	 * @return thisᵒᵗʰᵉʳ
	 */
	public nsint pow(nsint other){
		return toBase((int) Math.round(Math.pow(this.toDecimal(), other.toDecimal())), base);
	}
	/**
	 * Returns The estimated value of the NonStandard Integer square-rooted.
	 * @return The estimated value of the square root of the NonStandard Integer.
	 */
	public nsint sqrt(){
		return toBase((int) Math.round(Math.sqrt(this.toDecimal())), base);
	}
	/**
	 * Returns The bigger NonStandard Integer. If they are of the same value, the first NonStandard Integer will be returned.
	 * @param other another argument.
	 * @return The bigger NonStandard Integer.
	 */
	public nsint max(nsint other){
		return  this.compareTo(other) >= 0? toBase(Math.max(this.toDecimal(), other.toDecimal()), base) : toBase(Math.max(this.toDecimal(), other.toDecimal()), other.base);
	}
	/**
	 * Returns The smaller NonStandard Integer. If they are of the same value, the first NonStandard Integer will be returned.
	 * @param other another argument.
	 * @return The smaller NonStandard Integer.
	 */
	public nsint min(nsint other){
		return  this.compareTo(other) >= 0? toBase(Math.min(this.toDecimal(), other.toDecimal()), base) : toBase(Math.min(this.toDecimal(), other.toDecimal()), other.base);
	}
	/**
	 * Returns the estimated sin value.
	 * @return The estimated sin value
	 */
	public nsint sin(){
		return toBase((int) Math.round(Math.sin(this.toDecimal())), base);
	}
	/**
	 * Returns the estimated hyperbolic sin value.
	 * @return The estimated hyperbolic sin value
	 */
	public nsint sinh(){
		return toBase((int) Math.round(Math.sinh(this.toDecimal())), base);
	}
	/**
	 * Returns the estimated cos value.
	 * @return The estimated cos value
	 */
	public nsint cos(){
		return toBase((int) Math.round(Math.cos(this.toDecimal())), base);
	}
	/**
	 * Returns the estimated hyperbolic cos value.
	 * @return The estimated hyperbolic cos value
	 */
	public nsint cosh(){
		return toBase((int) Math.round(Math.cosh(this.toDecimal())), base);
	}
	/**
	 * Returns the estimated tan value.
	 * @return The estimated tan value
	 */
	public nsint tan(){
		return toBase((int) Math.round(Math.tan(this.toDecimal())), base);
	}
	/**
	 * Returns the estimated hyperbolic tan value.
	 * @return The estimated hyperbolic tan value
	 */
	public nsint tanh(){
		return toBase((int) Math.round(Math.tanh(this.toDecimal())), base);
	}
	/**
	 * Returns the estimated absolute value of the NonStandard Integer.
	 * @return The absolute value of the NonStandard Integer.
	 */
	public nsint abs(){
		return toBase(Math.round(Math.abs(this.toDecimal())), base);
	}
	
	//Unary Operations
	
	/**
	 * Returns the not value of the NonStandard Integer.
	 * @return The not value of the NonStandard Integer.
	 */
	public nsint not(){
		return toBase(~this.toDecimal(), base);
	}
	/**
	 * Returns the negated value of the NonStandard Integer.
	 * @return The negated value of the NonStandard Integer.
	 */
	public nsint negate(){
		return toBase(-this.toDecimal(), base);
	}
	/**
	 * Compares two NonStandard Integers.
	 * @param other The number being compared to 'this'.
	 * @return +1 if 'this' is grater than than 'other'. 0 if 'this' is equal to 'other'. -1 if 'this' is less than 'other'.
	 */
	public int compareTo(nsint other){
		int a = this.toDecimal();
		int b = other.toDecimal();
		return a > b ? +1 : a < b ? -1 : 0;
	}

	//_____Value
	
	/**
	 * Returns the double value of the NonStandard Integer in base 10.
	 * @return The double value of the NonStandard Integer.
	 */
	@Override
	public double doubleValue() {
		return this.toDecimal();
	}
	/**
	 * Returns the float value of the NonStandard Integer in base 10.
	 * @return The float value of the NonStandard Integer.
	 */
	@Override
	public float floatValue() {
		return this.toDecimal();
	}
	/**
	 * Returns the int value of the NonStandard Integer in base 10.
	 * @return The int value of the NonStandard Integer.
	 */
	@Override
	public int intValue() {
		return this.toDecimal();
	}
	/**
	 * Returns the long value of the NonStandard Integer in base 10.
	 * @return The long value of the NonStandard Integer.
	 */
	@Override
	public long longValue() {
		return this.toDecimal();
	}
	/**
	 * Returns the short value of the NonStandard Integer in base 10.
	 * @return The short value of the NonStandard Integer.
	 */
	@Override
	public short shortValue() {
		return (short)this.toDecimal();
	}
	/**
	 * Returns the char value of the NonStandard Integer in base 10.
	 * @return The char value of the NonStandard Integer.
	 */
	public char charValue() {
		return (char)this.toDecimal();
	}
	
	//valueOf
	
	/**
	 * Returns the NonStandard Integer value for 'input', an int.
	 * @param input The number the user is finding the value for.
	 * @return The NonStandard Integer for 'input'.
	 */
	public static nsint valueOf(int input){
		try {
			return new nsint(input, 10, false);
		}
		catch (InvalidNumber e) {
			e.printStackTrace();
			return INVALID;
		}
	}
	/**
	 * Returns the NonStandard Integer value for 'input', a long.
	 * @param input The number the user is finding the value for.
	 * @return The NonStandard Integer for 'input'.
	 */
	public static nsint valueOf(long input){
		try {
			return new nsint((int)input, 10, false);
		}
		catch (InvalidNumber e) {
			e.printStackTrace();
			return INVALID;
		}
	}
	/**
	 * Returns the NonStandard Integer value for 'input', a short.
	 * @param input The number the user is finding the value for.
	 * @return The NonStandard Integer for 'input'.
	 */
	public static nsint valueOf(short input){
		try {
			return new nsint(input, 10, false);
		}
		catch (InvalidNumber e) {
			e.printStackTrace();
			return INVALID;
		}
	}
	/**
	 * Returns the NonStandard Integer value for 'input', a byte.
	 * @param input The number the user is finding the value for.
	 * @return The NonStandard Integer for 'input'.
	 */
	public static nsint valueOf(byte input){
		try {
			return new nsint(input, 10, false);
		}
		catch (InvalidNumber e) {
			e.printStackTrace();
			return INVALID;
		}
	}
	/**
	 * Returns the NonStandard Integer value for 'input', a char.
	 * @param input The number the user is finding the value for.
	 * @return The NonStandard Integer for 'input'.
	 */
	public static nsint valueOf(char input){
		try {
			return new nsint(input, 10, false);
		}
		catch (InvalidNumber e) {
			e.printStackTrace();
			return INVALID;
		}
	}
	/**
	 * Returns the NonStandard Integer value for 'input', a double.
	 * @param input The number the user is finding the value for.
	 * @return The NonStandard Integer for 'input'.
	 */
	public static nsint valueOf(double input){
		try {
			return new nsint((int)input, 10, false);
		}
		catch (InvalidNumber e) {
			e.printStackTrace();
			return INVALID;
		}
	}
	/**
	 * Returns the NonStandard Integer value for 'input', a float.
	 * @param input The number the user is finding the value for.
	 * @return The NonStandard Integer for 'input'.
	 */
	public static nsint valueOf(float input){
		try {
			return new nsint((int)input, 10, false);
		}
		catch (InvalidNumber e) {
			e.printStackTrace();
			return INVALID;
		}
	}
	/**
	 * Returns the NonStandard Integer value for 'input', a boolean.
	 * @param input The number the user is finding the value for.
	 * @return The NonStandard Integer for 'input'.
	 */
	public static nsint valueOf(boolean input){
		try {
			return new nsint(input? 1 : 0, 2, false);
		}
		catch (InvalidNumber e) {
			e.printStackTrace();
			return INVALID;
		}
	}
	/**
	 * Returns the NonStandard Integer value for 'input', a string.
	 * @param input The string the user is finding the value for.
	 * @return The NonStandard Integer for 'input'.
	 */
	public static nsint valueOf(String input){
		try {
			return new nsint(Integer.parseInt(input), 10, false);
		}
		catch (InvalidNumber e) {
			e.printStackTrace();
			return INVALID;
		}
		catch (NumberFormatException e){
			e.printStackTrace();
			return INVALID;
		}
	}
	
	//ToBase
	
	/**
	 * Returns the Binary value of a NonStandard Integer.
	 * @return The Binary value of the NonStandard Integer.
	 */
	public nsint toBinary(){
		return  toBase(this, (byte)2);
	}
	/**
	 * Returns the Ternary value of a NonStandard Integer.
	 * @return The Ternary value of the NonStandard Integer.
	 */
	public nsint toTernary(){
		return  toBase(this, (byte)3);
	}
	/**
	 * Returns the Quaternary value of a NonStandard Integer.
	 * @return The Quaternary value of the NonStandard Integer.
	 */
	public nsint toQuaternary(){
		return  toBase(this, (byte)4);
	}
	/**
	 * Returns the Quinary value of a NonStandard Integer.
	 * @return The Quinary value of the NonStandard Integer.
	 */
	public nsint toQuinary(){
		return  toBase(this, (byte)5);
	}
	/**
	 * Returns the Senary value of a NonStandard Integer.
	 * @return The Senary value of the NonStandard Integer.
	 */
	public nsint toSenary(){
		return  toBase(this, (byte)6);
	}
	/**
	 * Returns the Sevennary value of a NonStandard Integer.
	 * @return The Sevennary value of the NonStandard Integer.
	 */
	public nsint toSevennary(){
		return  toBase(this, (byte)7);
	}
	/**
	 * Returns the Octal value of a NonStandard Integer.
	 * @return The Octal value of the NonStandard Integer.
	 */
	public nsint toOctal(){
		return  toBase(this, (byte)8);
	}
	/**
	 * Returns the Binary value of a NonStandard Integer.
	 * @return The Binary value of the NonStandard Integer.
	 */
	public nsint toNonnary(){
		return  toBase(this, (byte)9);
	}
	/**
	 * Returns the Nonnary value of a NonStandard Integer.
	 * @return The Nonnary value of the NonStandard Integer.
	 */

	//ToArray
	
	/**
	 * Returns an Array, where the first slot is the number, and the second slot is the base the number is in.
	 * @return An Array: {number, base}
	 */
	public int[] toArray(){
		 int[] output =  {number, base};
		return output;
	}
	/**
	 * Returns an ArrayList, where the first slot is the number, and the second slot is the base the number is in.
	 * @return List with the generic Integer. An List: {number, base}
	 */
	public List<Integer> toArrayList(){
		List<Integer> output =  new ArrayList<Integer>(2);
		output.set(0, number);
		output.set(1,(int)base);
		return output;
	}
	
	/*public static String getBaseString(int base) {
		if (base <= 9)
			return new String(new byte[] { -30, -126, (byte) (-128 + base) });
		return getBaseString(base / 10) + new String(new byte[] { -30, -126, (byte) (-128 + base % 10) });
	}
	*/
	
	//ToStrings
	
	/**
	 * Returns the string representation of the NonStandard Integer.
	 * @return The string representation of the NonStandard Integer.
	 * @see nsint#toString(int)
	  */
 	@Override
	public String toString(){
 		if(base < 0){
 			return toString(2);
 		}
 		if (base <= 9) {
 			return number + new String(new byte[] { -30, -126, (byte) (-128 + base) });
 		}
 		return number + "₁₀";
 		
	}
 	/**
 	 * Returns a string representation of the NonStandard Integer.
 	 * <p>DisplayMethods:
 	 * <br>0 - The number, then the base in subscript. Example: 21001₃
 	 * <br>1 - The number, then the word "base" in small caps, then the base in subscript. Example: 21001ʙᴀsᴇ₃
 	 * <br>2 - The number, then the word "base" in small caps, then the base. Example: 21001ʙᴀsᴇ3
 	 * <br>3 - The number, then the letter 'B', then the base. Example: 21001B3
 	 * <br>4 - The number, then ", base", then the base. Example: 21001, Base 3
 	 * @param displayMethod How the user wants to use it.
 	 * @return The String representation of the NonStandard Integer.
 	 */
	public String toString(int displayMethod){
		if(displayMethod == 1){
			if(base == 10){return number + "ʙᴀsᴇ₁₀";}
			String subscript = toString();
			return number + "ʙᴀsᴇ" + subscript.substring(subscript.length()-1);
		}
		if(displayMethod == 2){
			return number + "ʙᴀsᴇ" + base;
		}
		return displayMethod == 3? number + "B" + base : displayMethod == 4? number + ", Base" + base : toString();
	}
	
	//Getters and Setters
	
		/**
		 * Returns the serialVersionUID.
		 * @return serialVersionUID
		 */
		public static long getSerialversionuid() {
		return serialVersionUID;
	}
}