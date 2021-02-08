package GUI;

public enum EPanelName 
{
	CALCULATOR("CALCULATOR"),
	CONTAINER("CONTAINER"),
	CONVERTER_BINARY("CONVERTER_BINARY"),
	LOGICAL_PROPERTIES("LOGICAL_PROPERTIES"),
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
