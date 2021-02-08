package utilities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import GUI.EBaseSelection;

public class LogicalExpressionCalculator 
{
	public static class Token
	{
		String name = "";
		boolean isNegated = false;
		boolean isVariable = false;
		ArrayList<String> values = new ArrayList<String>();
		
		Token(){}								//Default constructor
		Token(String name)						//Overloaded constructor
		{
			this.name = name;
		}
		Token(Token token)						//Default Copy constructor 
		{
			this.name = token.name;
			this.isNegated = token.isNegated;
			
			for(String value : token.values)
			{
				this.values.add(value);
			}
			
		}
		Token(Token token, boolean isVariable)	//Copy constructor for variables
		{
			this.name = token.name;
			this.isNegated = token.isNegated;
			this.isVariable = isVariable;
			
			for(String value : token.values)
			{
				this.values.add(value);
			}
			
		}
		
		void setValuesAndName(
				Token operator, 
				Token variable2, 
				Token variable1)
		{		
			for(int i = 0; i < variable1.values.size(); i++)
			{
				switch(operator.name)
				{
				case "∧":	
					if(variable1.values.get(i).contentEquals("1") && variable2.values.get(i).contentEquals("1"))
					{
						this.values.add("1");
					}
					else
					{
						this.values.add("0");
					}
					break;
				case "↔":
					if(variable1.values.get(i).contentEquals("0") && variable2.values.get(i).contentEquals("0") ||
					   variable1.values.get(i).contentEquals("1") && variable2.values.get(i).contentEquals("1"))
					{
						this.values.add("1");
					}
					else
					{
						this.values.add("0");
					}
					break;
				case "∨":
					if(variable1.values.get(i).contentEquals("0") && variable2.values.get(i).contentEquals("0"))
					{
						this.values.add("0");
					}
					else
					{
						this.values.add("1");
					}
					break;
				case "⊕":
					if(variable1.values.get(i).contentEquals("0") && variable2.values.get(i).contentEquals("0") ||
					   variable1.values.get(i).contentEquals("1") && variable2.values.get(i).contentEquals("1"))
					{
						this.values.add("0");
					}
					else
					{
						this.values.add("1");
					}
					break;
				case "→":
					if(variable1.values.get(i).contentEquals("1") && variable2.values.get(i).contentEquals("0"))
					{
						this.values.add("0");
					}
					else
					{
						this.values.add("1");
					}
					break;
				}
			}		
			String newName = "(";
			
			newName = newName.concat(variable1.name);
			newName = newName.concat(operator.name);
			newName = newName.concat(variable2.name);
			newName = newName.concat(")");
			
			operator.name = newName;
		}
		
		void negateValues()
		{
			for(int i = 0; i < values.size(); i++)
			{
				if(values.get(i).contentEquals("0"))
				{
					values.set(i, "1");
				}
				else
				{
					values.set(i, "0");
				}
			}
		}
		
		boolean isTotologie()
		{
			for(String value : values)
			{
				if(value.contentEquals("0"))
				{
					return false;
				}
			}
			
			return true;
		}
		
		boolean isContradiction()
		{
			for(String value : values)
			{
				if(value.contentEquals("1"))
				{
					return false;
				}
			}
			
			return true;
		}
		
		boolean areResultsEquivalent(ArrayList<Token> results)
		{
			if(results.size() == 0)
			{
				return false;
			}
			else if(results.size() == 1)
			{
				return true;
			}
			
			Token previousResult = null;
			Token currentResult = null;
			
			for(int i = 0; i < results.size(); i++)
			{
				currentResult = results.get(i);
				
				for(int j = 0; j < currentResult.values.size(); j++)
				{	
					if(previousResult == null)
					{
						break;
					}
					else if(previousResult.values.size() != currentResult.values.size())
					{
						return false;
					}
					
					if(previousResult.values.get(j) != currentResult.values.get(j))
					{
						return false;
					}
				}
				previousResult = results.get(i);
			}
			
			return true;
		}
	}	
	
	private static BinaryConverter binaryConverter = new BinaryConverter();
	
	public String evaluate(String input)
	{
		ArrayList<String> expression = new ArrayList<String>();
		ArrayList<Token> infixVariables = new ArrayList<Token>();
		ArrayList<Token> infixExpression = new ArrayList<Token>();
		ArrayList<Token> expressionResult = new ArrayList<Token>();
		
		if(input.length() == 0)
		{
			return "No expression to evaluate";
		}
		else if(!isValidInput(input))
		{
			return "Invalid input!";
		}
		
		if(isThereEquivalenceSymbol(input))
		{
			return evaluateAndCompare(input);
		}
		
		toArrayList(input, expression);
		instantiateUniqueVariable(expression, infixVariables);
		setVariableEntries(infixVariables);
		parseExpression(expression, infixVariables, infixExpression);
		expressionResult = getExpressionResult(infixVariables, infixExpression);
		
		return getPrintableTruthTable(expressionResult);
	}
	
	public boolean isValidInput(String input)
	{
		final String VALID_CHARS = "abcdefghijklmnopqrstuvwxyz()∧∨↔→⊕¬≡";
		char[] inputChars;
		char[] validChars;
		boolean found = false;
		
		inputChars = input.toLowerCase().toCharArray();
		validChars = VALID_CHARS.toCharArray();
		
		for(int i = 0; i < inputChars.length; i++)
		{
			for(int j = 0; j < validChars.length; j++)
			{
				if(inputChars[i] == validChars[j])
				{
					found = true;
					break;
				}
			}
			
			if(!found)
			{
				return false;
			}
			else if(i == (inputChars.length -1) && inputChars[i] == '!') //Can't have a negation operator at the end of expression
			{
				return false;
			}

			found = false;
		}
		
		return true;	
	}
	
	private boolean isThereEquivalenceSymbol(String input)
	{
		char[] chars;
		
		chars = input.toCharArray();
		
		for(char c : chars)
		{
			if(c == '≡')
			{
				return true;
			}
		}
		
		return false;
	}
	
	private String evaluateAndCompare(String input)
	{
		Token token = new Token();
		ArrayList<String> expressions = new ArrayList<String>();
		ArrayList<Token> results = new ArrayList<Token>();
		String printableTruthTable = "";
		
		separateExpressions(input, expressions);
		
		for(String expression : expressions)
		{
			ArrayList<String> expressionX = new ArrayList<String>();
			ArrayList<Token> infixVariables = new ArrayList<Token>();
			ArrayList<Token> infixExpression = new ArrayList<Token>();
			ArrayList<Token> expressionResult = new ArrayList<Token>();
			
			toArrayList(expression, expressionX);
			instantiateUniqueVariable(expressionX, infixVariables);
			setVariableEntries(infixVariables);
			parseExpression(expressionX, infixVariables, infixExpression);
			expressionResult = getExpressionResult(infixVariables, infixExpression);
			results.add(expressionResult.get(expressionResult.size()-1));
			printableTruthTable = printableTruthTable.concat(getPrintableTruthTable(expressionResult));
		}
		
		if(token.areResultsEquivalent(results))
		{
			printableTruthTable = printableTruthTable.concat("≡ is true");
		}
		else
		{
			printableTruthTable = printableTruthTable.concat("≡ is false");
		}
		
		return printableTruthTable;
	}
	
	private void separateExpressions(String input, ArrayList<String> expressions)
	{
		char[] chars;
		String buffer = "";
		
		chars = input.toCharArray();
		
		for(char c : chars)
		{
			if(c == '≡')
			{
				expressions.add(buffer);
				buffer = "";
			}
			else
			{
				buffer = buffer.concat(Character.toString(c));
			}
		}
		
		expressions.add(buffer);
	}

	private void toArrayList(String input, ArrayList<String> expression)
	{
		char[] chars;
		
		chars = input.toLowerCase().toCharArray();

		for(int i = 0; i<chars.length; i++)
		{
			if(chars[i] == ' ')					//If the token is a space
			{	
				continue;						//Do nothing. Don't want to add it to the array
			}
			else if(chars[i] == '¬')		//Else if we have a Negation sign
			{
				if(chars[i+1] == '(')			//If the following char is a (
				{
					chars[i+1] = '[';        	//Set next char to [ - means that we need to negate the entire parenthesis
				}
				else							//Else, add the negate operator to list
				{
					expression.add(Character.toString(chars[i])); 
				}
			}
			else								//Else, simply add the character to the expression list
			{
				expression.add(Character.toString(chars[i]));
			}
		}
	}
	
	private void instantiateUniqueVariable(ArrayList<String> expression, ArrayList<Token> infixVariables)
	{	
		Set<String> uniqueVariables = new LinkedHashSet<String>();
		
		for(String token : expression)			//Find variables, add them to a Set of string. This makes sure 
		{										//   every variables are unique
			if(!isOperator(token) && !token.contentEquals("¬"))
			{
				uniqueVariables.add(token);
			}
		}	
		
		for(String variable : uniqueVariables)
		{
			infixVariables.add(new Token(variable));
		}	
	}
	
	private void setVariableEntries(ArrayList<Token> infixVariables)
	{
		double possibleEntries;
		int maxBinaryPosition;
		StringBuilder binaryInput = new StringBuilder();
		int count = 0;
		
		possibleEntries = Math.pow(2, infixVariables.size());
		maxBinaryPosition = infixVariables.size();
		
		while(count != possibleEntries)
		{
			binaryInput = binaryInput.append(
					binaryConverter.fromDecimal(
							Integer.toString(count), EBaseSelection.BINARY));
			
			while(binaryInput.length() != maxBinaryPosition)
			{
				binaryInput = binaryInput.insert(0, "0");
			}
			
			for(int i = 0; i < infixVariables.size(); i++)
			{
				infixVariables.get(i).values.add(Character.toString(binaryInput.charAt(i)));
			}
			binaryInput = new StringBuilder();
			count++;
		}
		
	}
	
	private void parseExpression(ArrayList<String> expression, 
										ArrayList<Token> infixVariables, 
										ArrayList<Token> infixExpression)
	{
		for(int i = 0; i < expression.size(); i++)
		{
			if(isOperator(expression.get(i)))
			{
				infixExpression.add(new Token(expression.get(i)));	
			}
			else if(expression.get(i).contentEquals("¬"))
			{
				Token nextToken = new Token(getToken(expression.get(i+1), infixVariables));
				nextToken.isNegated = true;
				infixExpression.add(nextToken);
				i++;
			}
			else
			{
				infixExpression.add(getToken(expression.get(i), infixVariables));
			}
		}
	}
	
	private Token getToken(String name, ArrayList<Token> infixVariables)
	{
		for(int i = 0; i < infixVariables.size(); i++)
		{
			if(infixVariables.get(i).name.contentEquals(name))
			{
				return infixVariables.get(i);
			}
		}
		
		return null;
	}
	
	private boolean isOperator(String s)
	{
		String[] operators = {"∧", "↔", "∨", "⊕", "→", "(", ")", "["};
		
		for(String operator : operators)
		{
			if(operator.contentEquals(s))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private ArrayList<Token> getExpressionResult(ArrayList<Token> infixVariables, ArrayList<Token> infixExpression)
	{
		Stack<Token> variables = new Stack<Token>();
		Stack<Token> operators = new Stack<Token>();
		ArrayList<Token> truthTable = new ArrayList<Token>();
		
		for(Token infixVariable : infixVariables)
		{
			Token variable = new Token(infixVariable, true);
			truthTable.add(variable);
		}
		
		for(int i = 0; i < infixExpression.size(); i++) 
		{
			if(infixExpression.get(i).name.contentEquals("(") || infixExpression.get(i).name.contentEquals("["))
			{
				operators.push(infixExpression.get(i));
			}
			else if(infixExpression.get(i).name.contentEquals(")"))
			{			
				while(!operators.peek().name.contentEquals("("))
				{
					if(operators.peek().name.contentEquals("[")) break;
					
					doOperation(variables, operators, truthTable);
				}
				
				if(operators.peek().name.contentEquals("["))
				{
					Token negatedToken = new Token(truthTable.get(truthTable.size()-1));
					String negation = "¬";
					
					negation = negation.concat(negatedToken.name);
					negatedToken.name = negation;
					negatedToken.negateValues();
					truthTable.add(negatedToken);
					variables.pop();
					variables.add(negatedToken);
					operators.pop();
				}
				else
				{
					operators.pop();					
				}
			}
			else if(isOperator(infixExpression.get(i).name)) //Else if we got an operator
			{
				while(!operators.empty() && hasPrecedence(operators.peek()))	
				{
					doOperation(variables, operators, truthTable);
				}
				operators.push(infixExpression.get(i));	
			}
			else 											//Else we got a variable!
			{
				if(infixExpression.get(i).isNegated == true)
				{
					variables.push(getNegatedToken(infixExpression.get(i), truthTable));
				}
				else
				{
					variables.push(infixExpression.get(i));		
				}
			}
		}
		
		while(!operators.empty())
		{
			doOperation(variables, operators, truthTable);
		}
		
		return getSortedTruthTable(truthTable, infixVariables);
	}
	
	private boolean hasPrecedence(Token operator)
	{
		if(operator.name.contentEquals("(") || operator.name.contentEquals(")") || operator.name.contentEquals("["))
		{
			return false;
		}
		
		return true;
	}
	
	private void doOperation(Stack<Token> variables, Stack<Token> operators, ArrayList<Token> truthTable)
	{	
		Token operation = new Token(operators.pop());
		Token variable2 = variables.pop();
		Token variable1 = variables.pop();
		
		operation.setValuesAndName(operation, variable2, variable1);
		truthTable.add(operation);
		variables.add(operation);
	}
	
	private Token getNegatedToken(Token tokenToNegate, ArrayList<Token> truthTable)
	{
		String negation = "¬";
		Token negatedToken = new Token(tokenToNegate);
		
		negation = negation.concat(negatedToken.name);
		negatedToken.name = negation;
		negatedToken.negateValues();
		truthTable.add(negatedToken);
		
		return negatedToken;
	}
	
	private ArrayList<Token> getSortedTruthTable(ArrayList<Token> truthTable, ArrayList<Token> infixVariables)
	{
		ArrayList<Token> finalTruthTable = new ArrayList<Token>();
		
		for(Token variable : infixVariables)
		{
			finalTruthTable.add(variable);
		}
		
		for(int i = 0; i < truthTable.size(); i++)
		{
			if(truthTable.get(i).isNegated == true)
			{
				finalTruthTable.add(new Token(truthTable.get(i)));
			}
		}
		
		for(int i = 0; i < truthTable.size(); i++)
		{
			if(truthTable.get(i).isNegated == false && truthTable.get(i).isVariable == false)
			{
				finalTruthTable.add(new Token(truthTable.get(i)));
			}
		}
		
		return finalTruthTable;
	}
	
	private String getPrintableTruthTable(ArrayList<Token> truthTable)
	{
		ArrayList<Integer> columWidth = new ArrayList<Integer>();
		String printableTruthTable = "";
		
		for(Token token: truthTable)
		{
			columWidth.add(token.name.length());
			printableTruthTable = printableTruthTable.concat(token.name + " | ");
		}
		
		printableTruthTable = printableTruthTable.concat("\n");
		
		for(int i = 0; i < truthTable.get(0).values.size(); i++)    //Columns
		{
			for(int j = 0; j < truthTable.size(); j++)				//Rows
			{
				int numOfSpace;
				
				numOfSpace = columWidth.get(j);
				if(j == 0)
				{
					printableTruthTable = printableTruthTable.concat(truthTable.get(j).values.get(i));
					printableTruthTable = printableTruthTable.concat("  ");
				}
				else
				{
					printableTruthTable = printableTruthTable.concat("  ");		
					printableTruthTable = printableTruthTable.concat(truthTable.get(j).values.get(i));
					for(int k = 0; k < numOfSpace; k++)
					{
						printableTruthTable = printableTruthTable.concat("  ");
					}
				}
			}
			
			if(i == 1)
			{
				if(truthTable.get(truthTable.size()-1).isTotologie())
				{
					printableTruthTable = printableTruthTable.concat("  Tautologie");
				}
				else if(truthTable.get(truthTable.size()-1).isContradiction())
				{
					printableTruthTable = printableTruthTable.concat("  Contradiction");
				}
			}
			
			printableTruthTable = printableTruthTable.concat("\n");	
		}
		
		printableTruthTable = printableTruthTable.concat("\n");
		
		return printableTruthTable;
	}
}
