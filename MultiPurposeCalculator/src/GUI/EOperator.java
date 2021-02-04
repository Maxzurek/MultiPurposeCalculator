package GUI;

public enum EOperator 
{
	NONE(""), 
	PLUS("+"), 
	MINUS("-"), 
	MULTIPLY("*"), 
	DIVIDE("/"); 
	
	private final String operator;
	
	EOperator(String s)
	{
		this.operator = s;
	}
	
	public String getOperator()
	{
		return operator;
	}
}
