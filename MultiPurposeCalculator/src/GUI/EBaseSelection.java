package GUI;

public enum EBaseSelection 
{
	NONE(0),
	DECIMAL(10),
	BINARY(2),
	OCTAL(8),
	HEXA(16);
		
	private final int baseValue;
		
	private EBaseSelection(int baseValue)
	{
		this.baseValue = baseValue;
	}
		
	public int getBaseValue()
	{
		return this.baseValue;
	}
}