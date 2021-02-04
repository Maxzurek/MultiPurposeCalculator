package GUI;

public enum EPanelName 
{
	CONTAINER("CONTAINER"),
	CONVERTER_BINARY("CONVERTER_BINARY"),
	CONVERTER_COMPLEMENT("CONVERTER_COMPLEMENT"),
	OPERATION_BINARY("OPERATION_BINARY"),
	TRUTH_TABLES("TRUTH_TABLES"),
	WELCOME("WELCOME");
	
	private final String name;
	
	EPanelName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}
