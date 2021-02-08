package GUI;

public enum EBaseSelection 
{
	NONE(0),
	DECIMAL(10),
	BINARY(2),
	OCTAL(8),
	HEXA(16),
	LOGICAL(1);
		
	private final int baseValue;
		
	EBaseSelection()
	{
		this.baseValue = 0;
	}
	EBaseSelection(int baseValue)
	{
		this.baseValue = baseValue;
	}
		
	public int getBaseValue()
	{
		return this.baseValue;
	}
}