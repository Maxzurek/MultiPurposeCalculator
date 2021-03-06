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
		String booleanString = "";
		boolean isBoolean = false;
		boolean isOperator = false;
		boolean isComplement = false;
		Set<String> elements = new TreeSet<String>();
		
		Token(){}								//Default constructor
		
		Token(String elements)						//Overloaded constructor 1 argument
		{
			String buffer = "";
			
			for(char c : elements.toLowerCase().toCharArray())
			{
				
				if(c == ';')
				{
					this.elements.add(buffer);
					buffer = "";
				}
				else
				{
					buffer = buffer.concat(Character.toString(c));
				}
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
		boolean is3Circles = PanelGraphics.isDraw3Circles();
		String stringExpression;
		String expressionResult = "";
		ArrayList<String> operators = new ArrayList<String>();
		ArrayList<String> sets = new ArrayList<String>();
		ArrayList<String> complexSets = new ArrayList<String>();
		ArrayList<String> tempSets = new ArrayList<String>();
		ArrayList<Token> expression = new ArrayList<Token>();
		Set<String> elementsU = new LinkedHashSet<String>();
		ArrayList<Token> universalExpression = new ArrayList<Token>();
		Token setU;	
		
		if(is3Circles)
		{
			elementsU.add("1");elementsU.add("2");elementsU.add("3");elementsU.add("4");
			elementsU.add("5");elementsU.add("6");elementsU.add("7");elementsU.add("8");
			setU = new Token(elementsU, "U");
			universalExpression.add(setU);
		}
		else
		{			
			elementsU.add("1");elementsU.add("2");elementsU.add("3");elementsU.add("4");
			setU = new Token(elementsU, "U");
			universalExpression.add(setU);
		}
		
		operators.add("∪");
		operators.add("∩");
		operators.add("∆");
		operators.add("\\");
		operators.add("'");
		
		sets.add("A");
		sets.add("B");
		sets.add("A'");
		sets.add("B'");
		
		if(is3Circles)
		{
			sets.add("C");
			sets.add("C'");			
		}
		
		tempSets.addAll(sets);
		
		//A'
		stringExpression = "A'";
		parseExpressionToDraw(stringExpression, expression, universalExpression);
		expressionResult = getResult(expression, universalExpression);
		if(expressionResult.equals(result))
		{
			return getStringExpression(expression);
		}
		expression.clear();
		//B'
		stringExpression = "B'";
		parseExpressionToDraw(stringExpression, expression, universalExpression);
		expressionResult = getResult(expression, universalExpression);
		if(expressionResult.equals(result))
		{
			return getStringExpression(expression);
		}
		expression.clear();
		
		if(is3Circles)
		{
			//C'
			stringExpression = "C'";
			parseExpressionToDraw(stringExpression, expression, universalExpression);
			expressionResult = getResult(expression, universalExpression);
			if(expressionResult.equals(result))
			{
				return getStringExpression(expression);
			}
			expression.clear();		
		}
		
		
		//X?Y
		for(String operator: operators)
		{			
			for(String set1 : sets)
			{
				for(String set2 : sets)
				{
					stringExpression = "(" + set1 + operator + set2 + ")";
					tempSets.add(stringExpression);
					complexSets.add(stringExpression);
					parseExpressionToDraw(stringExpression, expression, universalExpression);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
				}
			}
		}
		
		sets.clear();
		sets.addAll(tempSets);
		tempSets.clear();
		
		for(String operator : operators)
		{
			for(String set1 : sets)
			{
				for(String set2 : sets)
				{
					stringExpression = set1+operator+set2;
					tempSets.add(stringExpression);
					parseExpressionToDraw(stringExpression, expression, universalExpression);
					expressionResult = getResult(expression, universalExpression);
					if(expressionResult.equals(result))
					{
						return getStringExpression(expression);
					}
					expression.clear();
				}
			}
		}
		
		sets.clear();
		sets.addAll(tempSets);
		tempSets.clear();
		
		for(String operator1 : operators)
		{
			for(String operator2 : operators)
			{
				for(String set1 : complexSets)
				{
					for(String set2 : complexSets)
					{
						for(String set3 : complexSets)
						{
							stringExpression = set1+operator1+set2+operator2+set3;
							tempSets.add(stringExpression);
							parseExpressionToDraw(stringExpression, expression, universalExpression);
							expressionResult = getResult(expression, universalExpression);
							if(expressionResult.equals(result) && expression.size() <= 17)
							{
								return getStringExpression(expression);
							}
							expression.clear();
						}					
					}
				}				
			}
		}
		
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
		
		if(uniqueSetName.size() == 3 || isSetC || PanelGraphics.isDraw3Circles())
		{
			elementsA.add("1");elementsA.add("2");elementsA.add("4");elementsA.add("7");
			
			elementsB.add("2");elementsB.add("3");elementsB.add("7");elementsB.add("6");
			
			elementsC.add("4");elementsC.add("5");elementsC.add("6");elementsC.add("7");
			
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
			universalElements.add(new Token(elementsU, "U"));
		}
		
		//Parse expression after we know how many set we have
		for(char c : input.toLowerCase().toCharArray())
		{
			if(c == 'a')
			{
				expression.add(new Token(elementsA, "A"));
				
			}
			else if(c == 'b')
			{
				expression.add(new Token(elementsB, "B"));
			}
			else if(c == 'c')
			{
				expression.add(new Token(elementsC, "C"));
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
				else if(token.operator.contentEquals("'") || token.isComplement)
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
					buffer = buffer.concat(";");
				}
			}
			
			return new Token(buffer);
			
		case "∪":
			
			for(String element1 : elements1.elements)
			{
				buffer = buffer.concat(element1);
				buffer = buffer.concat(";");
			}	
			
			for(String element2 : elements2.elements)
			{
				buffer = buffer.concat(element2);
				buffer = buffer.concat(";");
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
					buffer = buffer.concat(";");
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
					buffer = buffer.concat(";");
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
					buffer = buffer.concat(";");
				}
			}
			
			return new Token(buffer);
		}
		
		return null;
	}	
}
