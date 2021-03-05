package utilities;

import java.util.ArrayList;
import java.util.Stack;

import GUI.EBaseSelection;

public class ExpressionCalculator 
{
	private BinaryConverter binaryConverter = new BinaryConverter();
	/*
	 * public static void main(String[] args) { String input; ArrayList<String>
	 * expression = new ArrayList<String>();
	 * 
	 * input = readInput();
	 * 
	 * if(!isValidInput(input)) { System.out.println("Syntax error!"); return; }
	 * 
	 * toArrayList(input, expression); insertMultiplication(expression);
	 * changeRepeatingSigns(expression); //printExpression(expression);
	 * System.out.println(getResult(expression)); }
	 */
	
	public String evaluate(String input, EBaseSelection baseSelection)
	{
		ArrayList<String> expression = new ArrayList<String>();	
		
		toArrayList(input, expression, baseSelection);
		insertMultiplication(expression);
		changeRepeatingSigns(expression);
		
		 switch(baseSelection)
		 {
		 case DECIMAL:
			 	break;
		 default:
			 operandToDecimal(expression, baseSelection);
			 break;
		 }
		 
		return Double.toString(getResult(expression));
	}
	
	
	public boolean isValidInput(String input, EBaseSelection baseSelection)
	{
		char[] chars;
		int openParenthesis = 0;
		int closingParenthesis = 0;
		
		chars = input.toLowerCase().toCharArray();
		
		for(int i = 0; i < chars.length; i++)
		{
			if(!isValidChar(chars[i], baseSelection))
			{
				return false;
			}
			
			//Opening parenthesis ')'
			if(chars[i] == '(')
			{
				if(i == chars.length -1)	 //Can't finish with (
				{
					return false;
				}
				if(chars[i+1] == '/' || 	//Dont't want division after (
				   chars[i+1] == '*') 		//Don't want multiplication after (
				{
					return false;
				}
				openParenthesis++;
			}
			
			//Closing parenthesis ')'
			else if(chars[i] == ')')
			{
				if(i == 0)            		//Can't start with )
				{
					return false;
				}
				if(chars[i-1] == '/' || 	//Dont't want division before )
				   chars[i-1] == '*') 		//Don't want multiplication before )
				{
					return false;
				}
				closingParenthesis++;
			}
			
			//Multiplication or division '*' or '/'
			else if(chars[i] == '*' || chars[i] == '/')
			{
				if(i == chars.length - 1) 	//Can't end expression with * or /
				{
					return false;
				}
				if(chars[i-1] == '*' ||   	//Don't want repeating|following * or /
				   chars[i-1] == '/' ||
				   chars[i+1] == '*' ||
				   chars[i+1] == '/')
				{
					return false;
				}
			}
			
			//Addition or division
			else if(chars[i] == '+' || chars[i] == '-')
			{
				if(i != 0)										//Making sure we're not out of bound array
				{
					if(chars[i+1] == '*' || chars[i+1] == '/')	//Can't have * or / following a x or /
					{
						return false;
					}
				}
				if(i == chars.length -1)						//Can't have the expression ending with + or -
				{
					return false;
				}
			}
				
		}
		
		if(openParenthesis != closingParenthesis) //Make sure the number of opening and closing parentheses matches
		{
			return false;
		}
		
		return true;
	}
	
	private boolean isValidChar(char c, EBaseSelection baseSelection)
	{
		final String DECIMAL_VALID_CHARS = "0123456789()^*/+-., ";
		final String BINARY_VALID_CHARS = "01()^*/+-., ";
		final String OCTAL_VALID_CHARS = "01234567()^*/+-., ";
		final String HEXA_VALID_CHARS = "0123456789abcdef()^*/+-., ";
		char[] validChars;
		
		switch(baseSelection)
		{
		case DECIMAL:
			validChars = DECIMAL_VALID_CHARS.toCharArray();
			break;
		case BINARY:
			validChars = BINARY_VALID_CHARS.toCharArray();
			break;
		case OCTAL:
			validChars = OCTAL_VALID_CHARS.toCharArray();
			break;
		case HEXA:
			validChars = HEXA_VALID_CHARS.toCharArray();
			break;
		default:
			validChars = new char['\0'];
			break;
		}
		
		for(char C : validChars)
		{
			if(c == C)
			{
				return true;
			}
		}
		
		return false;
	}
	
	private void toArrayList(String input, ArrayList<String> expression, EBaseSelection baseSelection)
	{
		StringBuilder buffer = new StringBuilder();
		char[] chars;
		
		chars = input.toLowerCase().toCharArray();

		for(int i = 0; i<chars.length; i++)
		{
			if(chars[i] == ' ')						//If the token is a space
			{	
				continue;							//Do nothing. Don't want to add it to the array
			}
			else if(isOperator(chars[i]))			//If the token is an operator
			{
				if(buffer.length() != 0) 			//If buffer not empty, add it to the array before the operator
				{
					expression.add(buffer.toString());
					buffer = new StringBuilder();
					expression.add(Character.toString(chars[i]));
				}
				else								//Buffer is empty
				{
					expression.add(Character.toString(chars[i]));
				}
			}
			else if(isValidChar(chars[i], baseSelection))	//If the token is valid,it is an operand
			{
				if(chars[i] == ',')
				{
					buffer = buffer.append('.');
				}
				else
				{
					buffer = buffer.append(chars[i]);			//Append operand to the buffer			
				}
			}
		}
		
		if(buffer.length() != 0)					//At this point we are done, if we still have an operand in the buffer,
		{										
			expression.add(buffer.toString());		//Add it to the expression array
		}
	}
	
	private void insertMultiplication(ArrayList<String> expression)
	{	
		//Iterate through the input to see if there is a need to insert a * sign in front of a '(' of after a ')'
		for(int i = 0; i < expression.size(); i++)
		{	
			if(expression.get(i).contentEquals("(") && i != 0) 	//If token is ( and is not at the beginning
			{
				if(!isOperator(expression.get(i-1).charAt(0))) 	//if the token before the ( is not an operator, insert *
				{
					expression.add(i, "*");
					i++;
				}
				else if(expression.get(i-1).contentEquals(")")) //If the token before ( is a ) insert a *
				{
					expression.add(i, "*");
					i++;
				}
			}
			else if(expression.get(i).contentEquals(")")  && i != (expression.size() -1)) //If token is ) and not second last
			{
				if(!isOperator(expression.get(i+1).charAt(0))) 	//If the token after ) is not an operator, insert a *
				{
					expression.add(i+1, "*");
				}
			}
		}
	}
	
	private void changeRepeatingSigns(ArrayList<String> expression)
	{
		Stack<String> repeatingSign = new Stack<String>();
		int indexModifier = 0;
		double temp;
		
		//Iterate through the input to see if there is a need to insert a * sign in front of a '(' of after a ')'
		for(int i = 0; i < expression.size(); i++) 
		{	
			if(expression.get(i).contentEquals("+") || 							//If we find a + or -
			   expression.get(i).contentEquals("-"))
			{
				while(expression.get(i +indexModifier).contentEquals("+")||   	//Keep looping to see if we have + or -
						expression.get(i + indexModifier).contentEquals("-"))
				{
					repeatingSign.push(expression.get(i+indexModifier));						
					indexModifier++;									//Count the number of repeating sign
					
					if(repeatingSign.size() >= 2)						//If we indeed have a repeating sign
					{
						String sign1;
						String sign2;
						
						sign2 = repeatingSign.pop();
						sign1 = repeatingSign.pop();
						
						if(sign2.contentEquals("-") && sign1.contentEquals("-")) //If we have --, push + to sign stack
						{								
							repeatingSign.push("+");	

						}
						else if((sign2.contentEquals("+") && sign1.contentEquals("-"))||		//If we have +-, push - to sign stack
								(sign2.contentEquals("-") && sign1.contentEquals("+")))
						{
							repeatingSign.push("-");
						}
						else											//Else, we have ++. Push + to the sign stack
						{
							repeatingSign.push("+");
						}
					}
				}
				
				/*
				 *                   CLEANING AND ADDING THE SIGN TO THE EQUASION
				 */
				if(indexModifier > 1)							//If more than 2 repeating signs
				{												//  sign stack size will be 1
					while(indexModifier !=0)
					{
						expression.remove(i+(indexModifier-1));	//Remove all the repeating signs from expression
						indexModifier--;									
					}
					expression.add(i,repeatingSign.pop());		//finally, add the new sign to the expression
				}
				
				while(repeatingSign.size() != 0)				//Empty the repeatingSign stack
				{
					repeatingSign.pop();
				}
				indexModifier = 0;								//Reset the index modifier
				
				
				/*
				 *                           	IF WE FOUND A + OR -   
				 *                    			replace the operand following it IF needed
				 */
				if(i == 0 && 
				   (!expression.get(i+1).contentEquals("(") || 			//If sign is at the beginning of expression 
					!expression.get(i+1).contentEquals(")")))			//   and it not followed by parentheses
				{
					if(expression.get(i).contentEquals("-"))			//If we have a - and it is not a subtraction
					{			
						temp = Double.valueOf(expression.get(i+1)) *-1;	//Get the value of the next token(operand)
						expression.set(i+1, String.valueOf(temp));		// and multiply it by *-1 so it becomes negative
						expression.remove(i);
					}
					else												//It must be a + sign, simply remove it
					{
						expression.remove(i);
					}
					i--;													//Decrement i since we removed a token
				}
				else if(i != 0 && expression.get(i-1).contentEquals("("))	//Else if it ANYWHERE else but a the beginning 
				{															//  and preceded by a (
					if(expression.get(i).contentEquals("-"))				//If we have a - and it is not a subtraction
					{
						temp = Double.valueOf(expression.get(i+1)) *-1;		//Get the value of the next token(operand)	
						expression.set(i+1, String.valueOf(temp));			// and multiply it by *-1 so it becomes negative
						expression.remove(i);
					}
					else													//It must be a + sign, simply remove it
					{
						expression.remove(i);
					}
					i--;													//Decrement i since we removed a token
				}
				else if(expression.get(i+1).contentEquals("("))				//Else if - or + is followed by a (
				{
					if(expression.get(i).contentEquals("-"))				//If token is -
					{
						expression.set(i+1, "-(");							//Change next token ( for -(
						expression.remove(i);								// remove -
					}
					else													//Else, it must be a +, simply remove it
					{
						expression.remove(i);								
					}
					i--;													//Decrement i since we removed a token
				}
			}
		}
	}
	
	private void operandToDecimal(ArrayList<String> expression, EBaseSelection baseSelection)
	{
		for(int i=0; i<expression.size(); i++)
		{
			if(isOperator(expression.get(i).charAt(0))) //If token is operator
			{
				continue; 								//Do nothing
			}
			else 										//If Token is operand
			{
				expression.set(i, binaryConverter.toDecimal(expression.get(i), baseSelection));
			}
		}
	}
	
	private boolean isOperator(char c)
	{
		char[] operators = {'(' ,')', '^', '*', '/', '+', '-'};
		
		for(char operator : operators)
		{
			if(operator == c)
			{
				return true;
			}
		}
		
		return false;
	}
		
	private double getResult(ArrayList<String> expression)
	{
		Stack<Double> values = new Stack<Double>();
		Stack<String> operators = new Stack<String>();
		
		for(String token : expression) 
		{
			if(token.equals("(") || token.equals("-("))
			{
				operators.push(token);
			}
			else if(token.equals(")"))
			{
				while(!operators.peek().equals("("))
				{
					if(operators.peek().equals("-(")) break;
					values.push(applyOperator(operators.pop(),
							Double.valueOf(values.pop()),
							Double.valueOf(values.pop())));
				}
				
				if(operators.peek().equals("-("))
				{
					values.push((values.pop() * -1));
					operators.pop();
				}
				else
				{
					operators.pop();					
				}
			}
			else if(token.equals("*") || 
					token.equals("/") || 
					token.equals("+") || 
					token.equals("-"))
			{
				
				while(!operators.empty() && hasPrecedence(token, operators.peek()) )	
				{
					values.push(applyOperator(
											operators.pop(),
											values.pop(),
											values.pop()));
				}	
					operators.push(token);	
			}
			else //Else we got a number!
			{
				values.push(Double.valueOf(token));	
			}
		}
		
		while(!operators.empty())
		{
			values.push(applyOperator(
					operators.pop(),
					values.pop(),
					values.pop()));
		}
		
		return values.pop();
	}
	
	private double applyOperator(String operator, double value2, double value1) 
	{
		double result = 0;
		
		try
		{
			switch(operator)
			{
			case "+":
				result = value1 + value2;
				break;
			case "-":
				result = value1 - value2;
				break;
			case "*":
				result = value1 * value2;
				break;
			case "/":
				result = value1 / value2;
				break;
			}
		}
		catch(ArithmeticException e)
		{
			System.out.println("Math error!");
		}

		return result;	
	}
	
	private boolean hasPrecedence(String operator1, String operator2)
	{
		if(operator2.equals("(") || operator2.equals(")") || operator2.equals("-("))
		{
			return false;
		}
		if( (operator1.equals("*") || operator1.equals("/")) && 
			(operator2.equals("+") || operator2.equals("-")) )
		{
			return false;
		}
		
		return true;	
	}
}
