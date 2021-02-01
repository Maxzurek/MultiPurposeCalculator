package utilities;

import GUI.EBaseSelection;

public class SignModuleComplement 
{
	enum ESigns
	{
		POSITIVE, NEGATIVE;
	}
	
	private ESigns sign;
	private EBaseSelection baseSelection;
	private BinaryConverter binaryConverter = new BinaryConverter();
	
	public boolean isValidInput(String input, EBaseSelection base)
	{
		final int MAXCHAR = 8; //must be 8 for binary/octal/hexadecimal and 512 for decimal base
		int length;
		char[] chars;
		int count = 0;
		
		length = input.length();
		
		if(length == 0)
		{
			return false;
		}
		else if (length > MAXCHAR)
		{
			return false;
		}
		
		baseSelection = base;
		
		if(base == EBaseSelection.DECIMAL)
		{
			double doubleValue;
			double floating;
				
			doubleValue = Float.valueOf(input);;
			floating = doubleValue - Math.floor(doubleValue);
				
			if(floating != 0)
			{
				return false;
			}
			else if(doubleValue > 127 || doubleValue < -128)
			{
				return false;
			}
				
		}

		chars = input.toCharArray();
		
		for(char c : chars)
		{
			if(!isValidChar(c))
			{
				return false;
			}
			if(count >= MAXCHAR)
			{
				return false;
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
			validChars = new char[]{'0','1','2','3','4','5','6','7','8','9','-'};
			break;
		case BINARY:
			validChars = new char[]{'0','1'};
			break;
		case OCTAL:
			validChars = new char[]{'0','1','2','3','4','5','6','7'};
			break;
		case HEXA:
			validChars = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
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
	
	
	public String toDecimal(String byteInput, EComplementSelection complementSelection, EBaseSelection base)
	{
		
		char firstChar;
		StringBuilder sevenBitesBinary = new StringBuilder(byteInput);
		StringBuilder invertedBites = new StringBuilder();
		StringBuilder decimalNumber = new StringBuilder();
		
		baseSelection = base;
		firstChar = byteInput.charAt(0);
		
		if(firstChar == '1')
		{
			sign = ESigns.NEGATIVE;
		}
		else
		{
			sign = ESigns.POSITIVE;
		}
		
		//Remove charater at index 0 (representing + or -)
		sevenBitesBinary = sevenBitesBinary.deleteCharAt(0);
		
		switch(complementSelection)
		{
		case SIGN_MODULE:
			//get the decimal value of the 7 bites 
			decimalNumber = decimalNumber.append(binaryConverter.toDecimal(sevenBitesBinary.toString(), baseSelection)); 
			//insert - sign if the original binary number was positive
			insertSign(decimalNumber);
			break;
		case COMPLEMENT_TWO:
			if(sign == ESigns.NEGATIVE)
			{
				invertedBites = getInvertedDigits(sevenBitesBinary);
				decimalNumber = decimalNumber.append(binaryConverter.toDecimal(invertedBites.toString(), baseSelection));
				insertSign(decimalNumber);
			}
			else
			{
				decimalNumber = decimalNumber.append(binaryConverter.toDecimal(sevenBitesBinary.toString(), baseSelection));
			}
			break;
		default:
			break;	
		}

		return decimalNumber.toString();
	}
	
	public String fromDecimal(String byteInput, EComplementSelection complementSelection, EBaseSelection base)
	{
		char firstChar;
		StringBuilder decimalNumber = new StringBuilder(byteInput);
		StringBuilder invertedBites = new StringBuilder();
		StringBuilder sevenBitesBinary = new StringBuilder();
		StringBuilder beforeComplement = new StringBuilder();
		StringBuilder eightBitesBinary = new StringBuilder();
		
		baseSelection = base;
		firstChar = byteInput.charAt(0);
		
		if(firstChar == '-')
		{
			sign = ESigns.NEGATIVE;
			decimalNumber = decimalNumber.deleteCharAt(0);
		}
		else
		{
			sign = ESigns.POSITIVE;
		}
		
		
		switch(complementSelection)
		{
		case SIGN_MODULE:
			sevenBitesBinary = sevenBitesBinary.append(binaryConverter.fromDecimal(decimalNumber.toString(), baseSelection)); 
			break;
		case COMPLEMENT_TWO:
			if(sign == ESigns.NEGATIVE)
			{
				beforeComplement = beforeComplement.append(binaryConverter.fromDecimal(decimalNumber.toString(), baseSelection));
				invertedBites = getInvertedDigits(beforeComplement);
				sevenBitesBinary = sevenBitesBinary.append(invertedBites);
			}
			else
			{
				sevenBitesBinary = sevenBitesBinary.append(binaryConverter.fromDecimal(decimalNumber.toString(), baseSelection));
			}
			break;
		default:
			break;	
		}
		
		if(sevenBitesBinary.length() < 7)
		{
			for(int i = sevenBitesBinary.length(); i < 7; i++)
			{
				sevenBitesBinary = sevenBitesBinary.insert(0, "0");
			}
		}

		insertBinarySign(sevenBitesBinary);	
		eightBitesBinary = eightBitesBinary.append(sevenBitesBinary);

		return eightBitesBinary.toString();	
	}
	
	private void insertSign(StringBuilder stringBuilder)
	{
		if(sign == ESigns.NEGATIVE)
		{
			stringBuilder = stringBuilder.insert(0, "-");
		}
	}
	
	private void insertBinarySign(StringBuilder stringBuilder)
	{
		if(sign == ESigns.NEGATIVE)
		{
			stringBuilder = stringBuilder.insert(0, "1");
		}
		else
		{
			stringBuilder = stringBuilder.insert(0, "0");
		}
	}
	
	private StringBuilder getInvertedDigits(StringBuilder digitsToInverse)
	{
		StringBuilder reversedSevenBites = new StringBuilder();
		boolean first1Found = false;
		int first1Position;
		
		if(digitsToInverse.length() == 8)
		{
			return digitsToInverse;
		}
		else if(digitsToInverse.length() < 7)
		{
			for(int i = digitsToInverse.length(); i<7; i++)
			{
				digitsToInverse.insert(0, "0");
			}
		}
		
		first1Position = 0;
		reversedSevenBites = digitsToInverse.reverse();
		
		
		//Look for the first occurrence of 1. The String is reversed so it's easier to invert digits after
		for(int i = 0; i< reversedSevenBites.length(); i++)
		{
			if(reversedSevenBites.charAt(i) == '1')
			{
				first1Found = true;
				first1Position = i;
				break;
			}
		}
		
		//If a 1 is found, invert all the remaining numbers
		if(first1Found)
		{
			for(int i = first1Position +1; i< reversedSevenBites.length(); i++)
			{
				if(reversedSevenBites.charAt(i) == '0')
				{
					reversedSevenBites.setCharAt(i, '1');
				}
				else
				{
					reversedSevenBites.setCharAt(i, '0');
				}
			}
		}
		
		//reverse the reversed string so it's in the right order
		return reversedSevenBites.reverse();
	}
}
