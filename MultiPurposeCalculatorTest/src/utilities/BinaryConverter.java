package utilities;

import GUI.EBaseSelection;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Stack;

public class BinaryConverter
{
	private static EBaseSelection baseSelection = EBaseSelection.NONE;
	
	public boolean isValidInput(String input, EBaseSelection selection)
	{
		final int BIT = 512;
		String toLowerString;
		char[] chars;
		int intDecimal = 0;
		int floatingDecimal = 0;
		boolean isFloating = false;
		
		baseSelection = selection;
		toLowerString = input.toLowerCase();
		chars = toLowerString.toCharArray();
		
		for(char c : chars)
		{
			if(!isValidChar(c))
			{
				return false;
			}
			else if(c == '.' || c == ',')
			{
				isFloating = true;
			}
			else if(c == ' ')
			{
				continue;
			}
			else if(!isFloating)
			{
				intDecimal++;
				
				if(intDecimal > BIT)
				{
					return false;
				}
			}
			else if(isFloating)
			{
				floatingDecimal++;
				
				if((intDecimal + floatingDecimal) >= BIT)
				{
					return false;
				}
			}
		}
		
		return true; 
	}
	
	private boolean isValidChar(char c)
	{
		char[] validChars = new char[0];
		
		switch(baseSelection) 
		{
		case DECIMAL:
			validChars = new char[]{'0','1','2','3','4','5','6','7','8','9','-',',','.',' '};
			break;
		case BINARY:
			validChars = new char[]{'0','1',',','.',' '};
			break;
		case OCTAL:
			validChars = new char[]{'0','1','2','3','4','5','6','7','-',',','.',' '};
			break;
		case HEXA:
			validChars = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','-',',','.',' '};
			break;
		default:
			break;
		}
		
		for(char validChar : validChars)
		{
			if(c == validChar)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public String toDecimal(String string, EBaseSelection baseSelection)
	{
		String[] formatedStrings;
		int numInteger = 0;
		int numFloating = 0;
		int base = 0;
		int factor;
		boolean isFloating = false;
		boolean isNegative = false;
		MathContext mc = new MathContext(512, RoundingMode.HALF_UP);
		BigDecimal converted = new BigDecimal(0, mc);

		formatedStrings = getFormatedArray(string);
		base = baseSelection.getBaseValue();
		
		//Count the number of decimal before floating point 
		for(String s : formatedStrings)
		{
			if(s.equals("-"))
			{
				isNegative = true;
			}
			else if(s.equals(".") ||s.equals(","))
			{
				break;
			}
			else if(s.equals(" "))
			{
				continue;
			}
			else if(!isFloating)
			{
				numInteger++;
			}
		}
		
		//Multiply factor x base  x power
		for(String s : formatedStrings)
		{		
			if(s.equals("-"))
			{
				continue;
			}
			else if(s.equals(" "))
			{
				continue;
			}
			else if(s.equals(".") ||s.equals(","))
			{
				isFloating = true;
			}
			else if(!isFloating)
			{
				factor = Integer.valueOf(s);
				
				BigDecimal pow = new BigDecimal(0, mc);
				BigDecimal bdBase = new BigDecimal(base);
				BigDecimal product = new BigDecimal(0, mc);
				BigDecimal bdFactor = new BigDecimal(factor);
				
				int bdExponent = (numInteger-1);
				pow = bdBase.pow(bdExponent, mc);
				product = bdFactor.multiply(pow);
				converted = converted.add(product);
				
				numInteger--;
			}
			else if(isFloating)
			{
				factor = (Integer.valueOf(s));
				BigDecimal pow = new BigDecimal(0, mc);
				BigDecimal bdBase = new BigDecimal(base);
				BigDecimal product = new BigDecimal(0, mc);
				BigDecimal bdFactor = new BigDecimal(factor);
				
				int bdExponent = -(numFloating+1);
				pow = bdBase.pow(bdExponent, mc);
				product = bdFactor.multiply(pow);
				converted = converted.add(product);
				
				numFloating++;
			}
		}
		
		if(isNegative)
		{
			converted = converted.negate();
		}
		
		return converted.toPlainString();
		/*
		 * if(!isFloating) { return Double.toString(converted); }
		 * 
		 * return Double.toString(converted);
		 */
	}
	
	private static String[] getFormatedArray(String string)
	{
		String toLowerString;
		char[] tempChars;
		String[] convertedStringArray = new String[string.length()];
		
		toLowerString = string.toLowerCase();
		tempChars = toLowerString.toCharArray();
		
		for(int i = 0; i< tempChars.length; i++)
		{
			String s = Character.toString(tempChars[i]);
			
			switch(s)
			{
			case "a":
				s = "10";
				break;
			case "b":
				s = "11";
				break;
			case "c":
				s = "12";
				break;
			case "d":
				s = "13";
				break;
			case "e":
				s = "14";
				break;
			case "f":
				s = "15";
				break;
			default:
				break;
			}
			
			convertedStringArray[i] = s;
		}
		
		return convertedStringArray;
	}
	
	
	//Convert from decimal base to any other base (base 2, 8 or 16)
	public String fromDecimal(String decimalValue, EBaseSelection baseSelection)
	{
		final int DECIMALBASE = 10;
		char[] chars;
		int factor;
		int numInteger = 0;
		int numFloating = 0;
		long integer = 0;
		double floatingResult = 0;
		boolean isFloating = false;
		boolean isNegative = false;
		String converted= "";
		MathContext mc = new MathContext(512, RoundingMode.HALF_UP);
		BigDecimal floating = new BigDecimal(0, mc);

		chars =  decimalValue.toCharArray();
		
		//Count the number of decimal before floating point
		for(char c : chars)
		{
			if(c == '-')
			{
				isNegative = true;
			}
			else if(c == '.' || c == ',')
			{
				break;
			}
			else if(c == ' ')
			{
				continue;
			}
			else if(!isFloating)
			{
				numInteger++;
			}
		}
		
		//Get the integer and the floating number, separated
		for(char c : chars)
		{		
			if(c == '-')
			{
				continue;
			}
			else if(c == ' ')
			{
				continue;
			}
			else if(c == '.' || c == ',')
			{
				isFloating = true;
			}
			else if(!isFloating)
			{
				factor = (Character.getNumericValue(c));
				integer += factor * (Math.pow(DECIMALBASE, numInteger-1));
				numInteger--;
			}
			else if(isFloating)
			{
				BigDecimal pow = new BigDecimal(0, mc);
				BigDecimal bdBase = new BigDecimal(DECIMALBASE);
				BigDecimal product = new BigDecimal(0, mc);
				factor = (Character.getNumericValue(c));
				BigDecimal bdFactor = new BigDecimal(factor);
					
				int bdExponent = -(numFloating+1);
				pow = bdBase.pow(bdExponent, mc);
				product = bdFactor.multiply(pow);
				floating = floating.add(product);
				
				numFloating++;
			}
		}
		 
		//Concat "-" sign to our converted number if the number is negatice
		floatingResult = floating.doubleValue();
		
		if(isNegative)
		{
			converted = converted.concat("-");
		}
		
		if(floatingResult > 0) //If the number to convert has a floating number, format it correctly
		{
			converted = converted.concat(convertInteger(integer, baseSelection));
			converted = converted.concat(".");
			converted = converted.concat(convertFloating(floating.doubleValue(), baseSelection));
		}
		else//if not concat the converted number to a string
		{
			converted = converted.concat(convertInteger(integer, baseSelection));
		}
			
		return  converted;
	}
	
	
	//Convert the integer part to any other base (base 2, 8 or 16)
	private String convertInteger(long integer, EBaseSelection baseSelection)
	{
		String convertedString = "";
		double remainder = 0;
		int productOfRemainder = 0;
		double divident = 0;
		int base;
		double tempNum;
		Stack<String> stringStack = new Stack<String>();
		
		if(integer == 0)
		{
			return convertedString = "0";
		}
		
		base = baseSelection.getBaseValue();
		tempNum = (double)integer;

		while(tempNum != 0)
		{
			divident = Math.floor(tempNum/base);
			remainder = (tempNum/base) - divident;
			tempNum = divident;
				
			if(remainder != 0)
			{
				productOfRemainder = (int)( base * remainder);
					
				stringStack.push(formatToString(productOfRemainder));
			}
			else
			{
				stringStack.push("0");
			}
		}
		
		//Inverse stringStack
		while(!stringStack.empty())
		{
			convertedString = convertedString.concat(stringStack.lastElement());
			stringStack.pop();
		}
		
		return convertedString;
	}
	
	
	//Convert the floating part to any other base (base 2, 8 or 16)
	private String convertFloating(double floating, EBaseSelection baseSelection)
	{
		String convertedString = "";
		double tempNum = 0;
		int productOfRemainder = 0;
		int base;
		double remainder = -1;
		
		base = baseSelection.getBaseValue();
		tempNum = floating;
		
		while(remainder != 0)
		{
			productOfRemainder = (int)(Math.floor(tempNum * base));
			remainder = (tempNum * base) - productOfRemainder;
			tempNum = remainder;
			
			convertedString = convertedString.concat(formatToString(productOfRemainder));
		}
		
		return convertedString;
	}
	
	
	//Used to format t
	private static String formatToString(int value)
	{	
		if(value == 10)
		{
			return "A";
		}
		if(value == 11)
		{
			return "B";
		}
		if(value == 12)
		{
			return "C";
		}
		if(value == 13)
		{
			return "D";
		}
		if(value == 14)
		{
			return "E";
		}
		if(value == 15)
		{
			return "F";
		}

		return Long.toString(value);
	}
}
