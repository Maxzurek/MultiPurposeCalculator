package GUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

public class Menu 
{
	private static JFrame frame;
	private JMenuBar menuBar;
	private JMenu miscMenu;
	private JRadioButtonMenuItem rdbtnCalculator;
	private JRadioButtonMenuItem rdbtComplement;
	private JRadioButtonMenuItem rdbtTruthTable;
	private JRadioButtonMenuItem rdbtnLogicProperties;
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupMenu(Core core)
	{
		frame = core.getFrame();
		
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		//Main menu bar
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		
		/*
		 * RADIO BUTTON GROUP
		 * All the radio buttons in this panel are linked together
		 */
		ButtonGroup rdbtGroup= new ButtonGroup();
		
		rdbtnCalculator = new JRadioButtonMenuItem("Calculator");
		rdbtnCalculator.setName("(Calculator)");
		rdbtnCalculator.setSelected(true);
		rdbtnCalculator.addActionListener(getActionListener());
		menuBar.add(rdbtnCalculator);
		
		miscMenu = new JMenu("Misc.");
		menuBar.add(miscMenu);
		
		rdbtComplement = new JRadioButtonMenuItem("Conversion Complement");
		rdbtComplement.setName("(Conversion Complement)");
		rdbtComplement.addActionListener(getActionListener());
		miscMenu.add(rdbtComplement);
		
		rdbtTruthTable = new JRadioButtonMenuItem("Truth Tables");
		rdbtTruthTable.setName("(Truth Tables)");
		rdbtTruthTable.addActionListener(getActionListener());
		miscMenu.add(rdbtTruthTable);
		
		rdbtnLogicProperties = new JRadioButtonMenuItem("Properties of Logical Operators");
		rdbtnLogicProperties.setName("(Properties of Logical Operators)");
		rdbtnLogicProperties.addActionListener(getActionListener());
		miscMenu.add(rdbtnLogicProperties);
		
		rdbtGroup.add(rdbtnCalculator);
		rdbtGroup.add(rdbtComplement);
		rdbtGroup.add(rdbtTruthTable);
		rdbtGroup.add(rdbtnLogicProperties);
	}	
	
	
	/*
	 * GETTER FOR OPERATION ACTION LISTENER
	 * Calls the PanelContainer swapPanel() method with the name of the panel to swap to as argument
	 */
	private ActionListener getActionListener()
	{
		return  new ActionListener() 
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{
				AbstractButton radioButton = (AbstractButton) actionEvent.getSource();
				String buttonName = radioButton.getName();
				
				switch(buttonName)
				{
				case "(Calculator)":
					PanelContainer.swapPanel(EPanelName.CALCULATOR);
					break;
				case "(Conversion Complement)":
					PanelContainer.swapPanel(EPanelName.CONVERTER_COMPLEMENT);
					break;
				case "(Truth Tables)":
					PanelContainer.swapPanel(EPanelName.TRUTH_TABLES);
					break;	
				case "(Properties of Logical Operators)":
					PanelContainer.swapPanel(EPanelName.LOGICAL_PROPERTIES);
					break;
				}
				
				frame.setTitle("Multi Purpose Calculator - " + buttonName);
			}
		};
	}
	
}
	
	
