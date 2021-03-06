package GUI;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class PanelContainer 
{
	private static JFrame frame;
	
	private static JPanel panelContainer;
	private static PanelConversionBinary panelConversionBinary;
	private static PanelOperationBinary panelOperationBinary;
	private static PanelComplement panelComplement;
	private static PanelTruthTables panelTruthTables;
	private static PanelCalculator panelCalculator;
	private static PanelLogicalProperties panelLogicalProperties;
	
	public void setupPanels(Core core)
	{
		frame = core.getFrame();
		panelContainer = new JPanel();
		frame.getContentPane().add(panelContainer, "CONTAINER");
		panelContainer.setLayout(new CardLayout(0, 0));

		panelCalculator = new PanelCalculator();
		panelCalculator.setupPanel(core);
		
		panelConversionBinary = new PanelConversionBinary();
		panelConversionBinary.setupPanel(core);
		
		panelOperationBinary = new PanelOperationBinary();
		panelOperationBinary.setupPanel(core);
		
		panelComplement = new PanelComplement();
		panelComplement.setupPanel(core);
		
		panelTruthTables = new PanelTruthTables();
		panelTruthTables.setupPanel(core);
		
		panelLogicalProperties = new PanelLogicalProperties();
		panelLogicalProperties.setupPanel(core);
		
	}
	
	public static void swapPanel(EPanelName panelToSwapTo)
	{
		Component[] components;
		String newPanelName;
		
		newPanelName = panelToSwapTo.getName();
		components = panelContainer.getComponents();
		
		for(Component component: components)
		{
			if(component instanceof JPanel)
			{
				JPanel panel = (JPanel)component;
				
				if(panel.getName() == newPanelName)
				{
					panel.setVisible(true);
				}
				else
				{
					panel.setVisible(false);
				}
			}
			else if(component instanceof JLayeredPane)
			{
				JLayeredPane layeredPanel = (JLayeredPane)component;
				if(layeredPanel.getName() == newPanelName)
				{
					layeredPanel.setVisible(true);
				}
				else
				{
					layeredPanel.setVisible(false);
				}
			}
		}
		
		setFrameSize(panelToSwapTo);
	}
	
	public static JPanel getPanel(EPanelName panelName)
	{		
		switch(panelName)
		{
		case CONTAINER:
			return panelContainer;
		case CONVERTER_BINARY:
			return panelConversionBinary.getPanel();
		case LOGICAL_PROPERTIES:
			return panelLogicalProperties.getPanel();
		case OPERATION_BINARY:
			return panelOperationBinary.getPanel();
		case TRUTH_TABLES:
			return panelTruthTables.getPanel();
		default:
			return null;
		}
	}
	
	public static JLayeredPane getLayeredPane(EPanelName panelName)
	{
		switch(panelName)
		{
		case CALCULATOR:
			return panelCalculator.getPanel();
		default:
			return null;
		}
	}
	
	private static void setFrameSize(EPanelName panelName)
	{
		switch(panelName)
		{
		case CALCULATOR:
			frame.setSize(665, 660);
			break;
		case CONVERTER_BINARY:
			frame.setSize(665, 660);
			break;
		case LOGICAL_PROPERTIES:
			frame.setSize(631,860);
			break;
		case OPERATION_BINARY:
			frame.setSize(665, 660);
			break;
		case TRUTH_TABLES:
			frame.setSize(815, 620);
			break;
		default:
			frame.setSize(665, 660);
			break;
		}
	}
}
