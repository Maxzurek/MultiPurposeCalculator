package utilities;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import GUI.PanelGraphics;

public class SetOperationCalculator 
{	
	public static class Token
	{
		String setName = "";
		String operator = "";
		boolean isBoolean = false;
		String booleanString = "";
		boolean isOperator = false;
		Set<String> elements = new TreeSet<String>();
		
		Token(){}								//Default constructor
		
		Token(String elements)						//Overloaded constructor 1 argument
		{
			for(char c : elements.toLowerCase().toCharArray())
			{
				this.elements.add(Character.toString(c));
			}
		}
		
		Token(Set<String> elements)
		{
			for(String string : elements)
			{
				this.elements.add(string);
			}
		}
		
		Token(Set<String> elements, String setName)
		{
			this.setName = setName;
			for(String string : elements)
			{
				this.elements.add(string);
			}
		}
		
		Token(String operator, boolean isOperator)	//Overloaded constructor 2 argument
		{
			this.operator = operator;
			this.isOperator = isOperator;
		}
		
		String elementsToString()
		{
			StringBuilder elements = new StringBuilder();
			
			if(this.isBoolean)
			{
				return this.booleanString;
			}
			
			elements = elements.append("{");
			
			for(String s : this.elements)
			{
				elements = elements.append(s);
				elements = elements.append(";");
			}
			
			elements = elements.deleteCharAt(elements.length() -1);
			elements = elements.append("}");
			
			return elements.toString();
		}
	}
	
	public String evaluate(String input, String universalSet, int definedSet)
	{
		ArrayList<Token> expression = new ArrayList<Token>();
		ArrayList<Token> universalElements = new ArrayList<Token>();
		
		if(universalSet != null)
		{
			toArrayList(universalSet.toLowerCase(), universalElements);
		}
		
		if(definedSet == 0 && drawingRequired(input))
		{
			if(parseExpressionToDraw(input, expression, universalElements))
			{
				PanelGraphics.setDraw3Circles(true);
			}
			else
			{
				PanelGraphics.setDraw3Circles(false);
			}
		}
		else
		{
			toArrayList(input.toLowerCase(),expression);		
		}
		
		
		return getResult(expression, universalElements);
	}
	
	public boolean isValidInput(String input)
	{
		for(char c : input.toLowerCase().toCharArray())
		{
			if(!isValidChar(c))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public String evaluateFromDrawing(String result)
	{
		String expressionResult = "";
		boolean is3Circles = PanelGraphics.isDraw3Circles();
		ArrayList<Token> operatorSet = new ArrayList<Token>();
		ArrayList<Token> universalExpression = new ArrayList<Token>();
		ArrayList<Token> expression = new ArrayList<Token>();
		Set<String> elementsA = new LinkedHashSet<String>();
		Set<String> elementsB = new LinkedHashSet<String>();
		Set<String> elementsC = new LinkedHashSet<String>();
		Set<String> elementsU = new LinkedHashSet<String>();
		Token setA;
		Token setB;
		Token setC = null;
		Token setU;
		Token openParen = new Token("(", true);
		Token closeParen = new Token(")", true);
		Token complement = new Token("'", true);
		Token union = new Token("∪", true);
		Token intersection = new Token("∩", true);
		Token symetricalDiff = new Token("∆", true);
		Token difference = new Token("\\", true);
		
		
		operatorSet.add(union);
		operatorSet.add(intersection);
		operatorSet.add(symetricalDiff);
		operatorSet.add(difference);		
		operatorSet.add(complement);
		
		if(is3Circles)
		{
			elementsA.add("1");elementsA.add("2");elementsA.add("3");elementsA.add("4");
			setA = new Token(elementsA, "A");
			
			elementsB.add("2");elementsB.add("3");elementsB.add("6");elementsB.add("7");
			setB = new Token(elementsB, "B");
			
			elementsC.add("3");elementsC.add("4");elementsC.add("5");elementsC.add("6");
			setC = new Token(elementsC, "C");
			
			elementsU.add("1");elementsU.add("2");elementsU.add("3");elementsU.add("4");
			elementsU.add("5");elementsU.add("6");elementsU.add("7");elementsU.add("8");
			setU = new Token(elementsU, "U");
			universalExpression.add(setU);
		}
		else
		{
			elementsA.add("1");elementsA.add("2");
			setA = new Token(elementsA, "A");
			
			elementsB.add("2");elementsB.add("3");
			setB = new Token(elementsB, "B");
			
			elementsU.add("1");elementsU.add("2");elementsU.add("3");elementsU.add("4");
			setU = new Token(elementsU, "U");
			universalExpression.add(setU);
		}
		
		for(Token operator : operatorSet)
		{
			//U
			expression.add(setU);
			expressionResult = getResult(universalExpression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
			//A'
			expression.add(setA);expression.add(complement);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
			//B'
			expression.add(setB);expression.add(complement);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
			//A?B
			expression.add(setA);expression.add(operator);expression.add(setB);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
			//B?A
			expression.add(setB);expression.add(operator);expression.add(setB);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
			//A'?B
			expression.add(setA);expression.add(complement);expression.add(operator);expression.add(setB);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
			//A?B'
			expression.add(setA);expression.add(operator);expression.add(setB);expression.add(complement);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
			//B'?A
			expression.add(setB);expression.add(complement);expression.add(operator);expression.add(setA);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
			//B?A'
			expression.add(setB);expression.add(operator);expression.add(setA);expression.add(complement);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
			//B?A'
			expression.add(setB);expression.add(operator);expression.add(setA);expression.add(complement);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
			//(A?B)'
			expression.add(openParen);
			expression.add(setA);expression.add(operator);expression.add(setB);
			expression.add(closeParen);expression.add(complement);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
		}		
		
		if(is3Circles)
		{
			for(Token operator : operatorSet)
			{
				//A?C
				expression.add(setA);expression.add(operator);expression.add(setC);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//C?A
				expression.add(setC);expression.add(operator);expression.add(setA);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//B?C
				expression.add(setB);expression.add(operator);expression.add(setC);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//C?B
				expression.add(setC);expression.add(operator);expression.add(setB);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//A'?C
				expression.add(setA);expression.add(complement);expression.add(operator);expression.add(setC);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//A?C'
				expression.add(setA);expression.add(operator);expression.add(setC);expression.add(complement);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//C'?A
				expression.add(setC);expression.add(complement);expression.add(operator);expression.add(setA);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//C?A'
				expression.add(setC);expression.add(operator);expression.add(setA);expression.add(complement);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//A'?C'
				expression.add(setA);expression.add(complement);expression.add(setC);expression.add(complement);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//C'?A'
				expression.add(setC);expression.add(complement);expression.add(setA);expression.add(complement);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//B'?C'
				expression.add(setB);expression.add(complement);expression.add(setC);expression.add(complement);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//C'?B'
				expression.add(setC);expression.add(complement);expression.add(setB);expression.add(complement);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//(A?C)'
				expression.add(openParen);
				expression.add(setA);expression.add(operator);expression.add(setC);
				expression.add(closeParen);expression.add(complement);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
				//(B?C)'
				expression.add(openParen);
				expression.add(setB);expression.add(operator);expression.add(setC);
				expression.add(closeParen);expression.add(complement);
				expressionResult = getResult(expression, universalExpression);
				if(expressionResult.equals(result))
				{
					return getStringExpression(expression);
				}
				expression.clear();
			}
			
			for(Token operator1 : operatorSet)
			{
				for(Token operator2 : operatorSet)
				{
					//A?(B?C)
					expression.add(setA);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(operator2);expression.add(setC);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A?(C?B)
					expression.add(setA);expression.add(operator1);
					expression.add(openParen);
					expression.add(setC);expression.add(operator2);expression.add(setB);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//B?(A?C)
					expression.add(setB);expression.add(operator1);
					expression.add(openParen);
					expression.add(setA);expression.add(operator2);expression.add(setC);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//B?(C?A)
					expression.add(setB);expression.add(operator1);
					expression.add(openParen);
					expression.add(setC);expression.add(operator2);expression.add(setA);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//C?(A?B)
					expression.add(setC);expression.add(operator1);
					expression.add(openParen);
					expression.add(setA);expression.add(operator2);expression.add(setB);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//C?(B?A)
					expression.add(setC);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(operator2);expression.add(setA);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(C?B)?A
					expression.add(openParen);
					expression.add(setC);expression.add(operator1);expression.add(setB);
					expression.add(closeParen);
					expression.add(operator2);expression.add(setA);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(B?C)?A
					expression.add(openParen);
					expression.add(setB);expression.add(operator1);expression.add(setC);
					expression.add(closeParen);
					expression.add(operator2);expression.add(setA);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(C?A)?B
					expression.add(openParen);
					expression.add(setC);expression.add(operator1);expression.add(setA);
					expression.add(closeParen);
					expression.add(operator2);expression.add(setB);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(A?C)?B
					expression.add(openParen);
					expression.add(setA);expression.add(operator1);expression.add(setC);
					expression.add(closeParen);
					expression.add(operator2);expression.add(setB);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(B?A)?C
					expression.add(openParen);
					expression.add(setB);expression.add(operator1);expression.add(setA);
					expression.add(closeParen);
					expression.add(operator2);expression.add(setC);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(A?B)?C
					expression.add(openParen);
					expression.add(setA);expression.add(operator1);expression.add(setB);
					expression.add(closeParen);
					expression.add(operator2);expression.add(setC);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A'?(B?C)
					expression.add(setA);expression.add(complement);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(operator2);expression.add(setC);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A?(B'?C)
					expression.add(setA);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(complement);expression.add(operator2);expression.add(setC);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					expression.clear();
					//A?(B?C')
					expression.add(setA);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(operator2);expression.add(setC);expression.add(complement);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A?(B?C)'
					expression.add(setA);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(operator2);expression.add(setC);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A'?(B'?C)
					expression.add(setA);expression.add(complement);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(complement);expression.add(operator2);expression.add(setC);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A'?(B?C')
					expression.add(setA);expression.add(complement);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(operator2);expression.add(setC);expression.add(complement);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A'?(B?C)'
					expression.add(setA);expression.add(complement);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(operator2);expression.add(setC);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A?(B'?C')
					expression.add(setA);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(complement);expression.add(operator2);
					expression.add(setC);expression.add(complement);
					expression.add(closeParen);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A?(B'?C)'
					expression.add(setA);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(complement);expression.add(operator2);expression.add(setC);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A?(B?C')'
					expression.add(setA);expression.add(operator1);
					expression.add(openParen);
					expression.add(setB);expression.add(operator2);expression.add(setC);expression.add(complement);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
				}
			}//End of for loop
					
			/*
			 * 
			 * Less desired expression result, do LAST
			 * 
			 */
			for(Token operator1 : operatorSet)
			{
				for(Token operator2 : operatorSet)
				{
					//A?B?C
					expression.add(setA);expression.add(operator1);expression.add(setB);
					expression.add(operator2);expression.add(setC);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A?C?B
					expression.add(setA);expression.add(operator1);expression.add(setC);
					expression.add(operator2);expression.add(setB);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//B?A?C
					expression.add(setB);expression.add(operator1);expression.add(setA);
					expression.add(operator2);expression.add(setC);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//B?C?A
					expression.add(setB);expression.add(operator1);expression.add(setC);
					expression.add(operator2);expression.add(setA);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//C?A?B
					expression.add(setC);expression.add(operator1);expression.add(setA);
					expression.add(operator2);expression.add(setB);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//C?B?A
					expression.add(setC);expression.add(operator1);expression.add(setB);
					expression.add(operator2);expression.add(setA);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//C?B?A
					expression.add(setC);expression.add(operator1);expression.add(setB);
					expression.add(operator2);expression.add(setA);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A'?B?C
					expression.add(setA);expression.add(complement);expression.add(operator1);
					expression.add(setB);expression.add(operator2);expression.add(setC);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A?B'?C
					expression.add(setA);expression.add(operator1);expression.add(setB);
					expression.add(complement);expression.add(operator2);expression.add(setC);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//A?B?C'
					expression.add(setA);expression.add(operator1);expression.add(setB);
					expression.add(operator2);expression.add(setC);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(A?B?C)'
					expression.add(openParen);
					expression.add(setA);expression.add(operator1);expression.add(setB);
					expression.add(operator2);expression.add(setC);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(A'?B?C)'
					expression.add(openParen);
					expression.add(setA);expression.add(complement);expression.add(operator1);
					expression.add(setB);expression.add(operator2);expression.add(setC);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(A?B'?C)'
					expression.add(openParen);
					expression.add(setA);expression.add(operator1);expression.add(setB);
					expression.add(complement);expression.add(operator2);expression.add(setC);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(A?B?C')'
					expression.add(openParen);
					expression.add(setA);expression.add(operator1);expression.add(setB);
					expression.add(operator2);expression.add(setC);expression.add(complement);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(A'?B'?C)'
					expression.add(openParen);
					expression.add(setA);expression.add(complement);expression.add(operator1);
					expression.add(setB);expression.add(complement);expression.add(operator2);
					expression.add(setC);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(A'?B?C')'
					expression.add(openParen);
					expression.add(setA);expression.add(complement);expression.add(operator1);
					expression.add(setB);expression.add(operator2);expression.add(setC);
					expression.add(complement);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(A?B'?C')'
					expression.add(openParen);
					expression.add(setA);expression.add(operator1);expression.add(setB);
					expression.add(complement);expression.add(operator2);expression.add(setC);
					expression.add(complement);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
					//(A'?B'?C')'
					expression.add(openParen);
					expression.add(setA);expression.add(complement);expression.add(operator1);
					expression.add(setB);expression.add(complement);expression.add(operator2);
					expression.add(setC);expression.add(complement);
					expression.add(closeParen);expression.add(complement);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
				}
			}//End of For Loop
			
			//Custom expression finder
			expression.add(openParen);
			expression.add(setA);expression.add(intersection);expression.add(setB);
			expression.add(closeParen);expression.add(union);
			expression.add(openParen);
			expression.add(setA);expression.add(intersection);expression.add(setC);
			expression.add(closeParen);expression.add(union);
			expression.add(openParen);
			expression.add(setB);expression.add(intersection);expression.add(setC);
			expression.add(closeParen);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();
		}//End if
		
		return "Couldn't find result for "+result;
	}
	
	private String getStringExpression(ArrayList<Token> expression)
	{
		StringBuilder parsedExpression = new StringBuilder();
		
		for(Token token : expression)
		{
			if(token.isOperator)
			{
				parsedExpression.append(token.operator);		
			}
			else
			{
				parsedExpression.append(token.setName);
			}
		}
		
		return parsedExpression.toString();
	}
	
	private boolean drawingRequired(String input)
	{	
		for(char c : input.toLowerCase().toCharArray())
		{
			if(c == '{')
			{
				return false;
			}
		}
		
		return true;
	}
	
	private boolean isValidChar(char c)
	{
		String validChars = "abcdefghijklmnopqrstuvwxyz0123456789(){};,∈=⊆⊂'\\∩∪∆ ";
		
		for(char validChar : validChars.toCharArray())
		{
			if(c == validChar)
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean parseExpressionToDraw(
			String input, 
			ArrayList<Token> expression, 
			ArrayList<Token> universalElements
			)
	{
		boolean draw3Circles = false;
		boolean isSetC = false;
		Set<Character> uniqueSetName = new LinkedHashSet<Character>();
		Set<String> elementsA = new LinkedHashSet<String>();
		Set<String> elementsB = new LinkedHashSet<String>();
		Set<String> elementsC = new LinkedHashSet<String>();
		Set<String> elementsU = new LinkedHashSet<String>();
		
		//Count number of unique set
		for(char c : input.toLowerCase().toCharArray())
		{
			if(c == 'a')
			{
				uniqueSetName.add(c);
				
			}
			else if(c == 'b')
			{
				uniqueSetName.add(c);
			}
			else if(c == 'c')
			{
				isSetC = true;
				uniqueSetName.add(c);
			}
		}
		
		if(uniqueSetName.size() == 3 || isSetC)
		{
			elementsA.add("1");elementsA.add("2");elementsA.add("3");elementsA.add("4");
			
			elementsB.add("2");elementsB.add("3");elementsB.add("6");elementsB.add("7");
			
			elementsC.add("3");elementsC.add("4");elementsC.add("5");elementsC.add("6");
			
			elementsU.add("1");elementsU.add("2");elementsU.add("3");elementsU.add("4");
			elementsU.add("5");elementsU.add("6");elementsU.add("7");elementsU.add("8");
			universalElements.add(new Token(elementsU));
			
			draw3Circles = true;
		}
		else
		{
			elementsA.add("1");elementsA.add("2");
			
			elementsB.add("2");elementsB.add("3");
			
			elementsU.add("1");elementsU.add("2");elementsU.add("3");elementsU.add("4");
			universalElements.add(new Token(elementsU));
		}
		
		//Parse expression after we know how many set we have
		for(char c : input.toLowerCase().toCharArray())
		{
			if(c == 'a')
			{
				expression.add(new Token(elementsA));
				
			}
			else if(c == 'b')
			{
				expression.add(new Token(elementsB));
			}
			else if(c == 'c')
			{
				expression.add(new Token(elementsC));
			}
			else if(isOperator(c))
			{
				expression.add(new Token(Character.toString(c), true));
			}
		}
		
		return draw3Circles;
	}
	
	private void toArrayList(String input, ArrayList<Token> expression)
	{
		String buffer = "";
		boolean isASet = false;
		Set<String> elements = new LinkedHashSet<String>();
		
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) == '{')
			{
				if(i != 0)
				{
					if(input.charAt(i-1) == '{' || input.charAt(i-1) == ';' || input.charAt(i-1) == ',')
					{
						isASet = true;
					}
				}
			}
			else if(input.charAt(i) == '}')
			{
				isASet = false;
			}
			else if(isOperator(input.charAt(i)))
			{
				if(buffer.length() != 0)
				{
					elements.add(buffer);
					expression.add(new Token(elements));
					buffer = "";
					elements.clear();
					expression.add(new Token(Character.toString(input.charAt(i)), true));	
				}
				else
				{
					expression.add(new Token(Character.toString(input.charAt(i)), true));	
				}
			}
			else if( (input.charAt(i) == ';' || input.charAt(i) == ',')  && !isASet)
			{
				elements.add(buffer);
				buffer = "";
			}
			else if(isElement(input.charAt(i)))
			{
				if(isASet)
				{
					buffer = buffer.concat(Character.toString(input.charAt(i)));
					buffer = buffer.concat(";");
				}
				else
				{
					buffer = buffer.concat(Character.toString(input.charAt(i)));
				}
			}
		}
		
		
		if(buffer.length() != 0)
		{
			elements.add(buffer);
			expression.add(new Token(elements));
		}
	}
	
	private boolean isOperator(char c)
	{
		String operators = "()∈=⊆⊂'∩∪\\∆";
		
		for(char operator : operators.toCharArray())
		{
			if(c == operator)
			{
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean isElement(char c)
	{
		String validElements = "abcdefghijklmnopqrstuvwxyz0123456789";
		
		for(char operator : validElements.toCharArray())
		{
			if(c == operator)
			{
				return true;
			}
		}
		
		return false;
	}
	
	private String getResult(ArrayList<Token> expression, ArrayList<Token> universalElements)
	{
		Stack<Token> elements = new Stack<Token>();
		Stack<Token> operators = new Stack<Token>();
		
		for(Token token : expression) 
		{
			
			if(token.isOperator)
			{
				if(token.operator.contentEquals("("))
				{
					operators.push(token);
				}
				else if(token.operator.contentEquals(")"))
				{
					while(!operators.peek().operator.contentEquals("("))
					{
						try
						{
							elements.push(applyOperator(operators.pop(), elements.pop(), elements.pop()));
						}
						catch(EmptyStackException e)
						{
							return "Math Error! Invalid input...";
						}
					}
					
					operators.pop();	
				}
				else if(token.operator.contentEquals("'"))
				{
					if(universalElements.get(0) == null)
					{
						return "Error! No universal set found.";
					}
					else
					{
						elements.push(applyComplement(universalElements.get(0), elements.pop()));	
					}
				}
				else
				{
					while(!operators.empty() && hasPrecedence(operators.peek()))	
					{
						try
						{
							elements.push(applyOperator(operators.pop(), elements.pop(), elements.pop()));
						}
						catch(EmptyStackException e)
						{
							return "Math Error! Invalid input...";
						}
					}	
					
					operators.push(token);
				}
			}
			else
			{
				elements.push(token);
			}
		}
			
		while(!operators.empty())
		{
			try
			{
				elements.push(applyOperator(operators.pop(), elements.pop(), elements.pop()));
			}
			catch(EmptyStackException e)
			{
				return "Math Error! Invalid input...";
			}
		}
				
		return elements.pop().elementsToString();
	}
	
	private boolean hasPrecedence(Token operator)
	{
		if(operator.operator.contentEquals("(")|| operator.operator.contentEquals(")"))
		{
			return false;
		}
		
		return true;	
	}
	
	private Token applyComplement(Token universalElements, Token elements)
	{
		Set<String> buffer = new LinkedHashSet<String>();
		
		for(String element : universalElements.elements)
		{
			if(elements.elements.contains(element))
			{
				continue;
			}
			else
			{
				buffer.add(element);
			}
		}
		
		return new Token(buffer);
	}
	
	private Token applyOperator(Token operator, Token elements2, Token elements1)
	{	
		Token booleanToken = new Token();
		String buffer = "";
		
		switch(operator.operator)
		{
		case "∈":

			booleanToken.isBoolean = true;
			booleanToken.booleanString = "false";
			
			if(elements1.elements.size() > 1)
			{
				for(String element : elements1.elements)
				{
					buffer = buffer.concat(element);
					buffer = buffer.concat(";");
				}
				
				if(elements2.elements.contains(buffer))
				{
					booleanToken.isBoolean = true;
					booleanToken.booleanString = "true";
				}
			}
			else
			{
				for(String element : elements1.elements)
				{
					if(elements2.elements.contains(element))
					{
						booleanToken.isBoolean = true;
						booleanToken.booleanString = "true";
					}
				}
			}
			
			return booleanToken;
			
		case "=":
			
			if(elements1.elements.containsAll(elements2.elements) &&
			   elements2.elements.containsAll(elements1.elements))
			{
				booleanToken.isBoolean = true;
				booleanToken.booleanString = "true";
			}
			else
			{
				booleanToken.isBoolean = true;
				booleanToken.booleanString = "false";
			}
			
			return booleanToken;
			
		case "⊆":
			
			if(elements2.elements.containsAll(elements1.elements))
			{
				booleanToken.isBoolean = true;
				booleanToken.booleanString = "true";
			}
			else
			{
				booleanToken.isBoolean = true;
				booleanToken.booleanString = "false";
			}
			
			return booleanToken;
			
		case "⊂":
			
			if(elements2.elements.containsAll(elements1.elements) &&
		       elements2.elements.size() > elements1.elements.size())
			{
				booleanToken.isBoolean = true;
				booleanToken.booleanString = "true";
			}
			else
			{
				booleanToken.isBoolean = true;
				booleanToken.booleanString = "false";
			}
			
			return booleanToken;
			
		case "'":
			//TODO
			break;
		case "∩":
			
			for(String element1 : elements1.elements)
			{
				if(elements2.elements.contains(element1))
				{
					buffer = buffer.concat(element1);
				}
			}
			
			return new Token(buffer);
			
		case "∪":
			
			for(String element1 : elements1.elements)
			{
				buffer = buffer.concat(element1);
			}	
			
			for(String element2 : elements2.elements)
			{
				buffer = buffer.concat(element2);
			}
			
			return new Token(buffer);

		case "\\":
			
			for(String element1 : elements1.elements)
			{
				if(elements2.elements.contains(element1))
				{
					continue;
				}
				else
				{
					buffer = buffer.concat(element1);
				}
			}
			
			return new Token(buffer);
			
		case "∆":
			
			for(String element1 : elements1.elements)
			{
				if(elements2.elements.contains(element1))
				{
					continue;
				}
				else
				{
					buffer = buffer.concat(element1);
				}
			}	
			
			for(String element2 : elements2.elements)
			{
				if(elements1.elements.contains(element2))
				{
					continue;
				}
				else
				{
					buffer = buffer.concat(element2);
				}
			}
			
			return new Token(buffer);
		}
		
		return null;
	}	
}
