package GUI;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;

public class Menu 
{
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnUtilities;
	private JMenu operationMenu;
	private JRadioButtonMenuItem rdbtOperationBinary;
	private JMenu converterMenu;
	private JRadioButtonMenuItem rdbtConverterBinary;
	private JLabel currentUtilityLabel;
	private JRadioButtonMenuItem rdbtComplement;
	private JMenuItem mntmTruthTables;
	
	
	public void setupMenu(Core core)
	{
		frame = core.getFrame();
		
		
		//Main menu bar
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		
		/*
		 * UTILITIES
		 * SUB MENU OF MAIN MENU
		 */
		mnUtilities = new JMenu("Utilities");
		menuBar.add(mnUtilities);	
		
		
		/*
		 * OPERATION 
		 * SUB MENU OF UTILITIES
		 */
		operationMenu = new JMenu("Operation");
		mnUtilities.add(operationMenu);
		
		//Radio Button Binary
		rdbtOperationBinary = new JRadioButtonMenuItem("Binary");
		rdbtOperationBinary.setName("Binary");
		operationMenu.add(rdbtOperationBinary);		
		rdbtOperationBinary.addActionListener(getOperationActionListener());
			
	
		/*
		 * CONVERSION
		 * SUB MENU OF UTILITIES
		 */
		converterMenu = new JMenu("Conversions");
		mnUtilities.add(converterMenu);	
		
		//Radio Button Binary
		rdbtConverterBinary = new JRadioButtonMenuItem("Binary");
		rdbtConverterBinary.setName("Binary");
		converterMenu.add(rdbtConverterBinary);		
		rdbtConverterBinary.addActionListener(getConversionActionListener());
		
		//Radio Button Complement
		rdbtComplement = new JRadioButtonMenuItem("Complement");
		rdbtComplement.setName("Complement");
		converterMenu.add(rdbtComplement);
		rdbtComplement.addActionListener(getConversionActionListener());
		
		
		/*
		 * RADIO BUTTON GROUP
		 * All the radio buttons in this panel are linked together
		 */
		ButtonGroup rdbtGroup= new ButtonGroup();	
		rdbtGroup.add(rdbtConverterBinary);
		rdbtGroup.add(rdbtOperationBinary);
		rdbtGroup.add(rdbtComplement);
		
		
		/*
		 * TRUTH TABLES
		 * SUB MENU OF UTILITIES
		 */
		mntmTruthTables = new JMenuItem("Truth Tables");
		mnUtilities.add(mntmTruthTables);
		mntmTruthTables.addActionListener(getTruthTableActionListener());
			
		
		/*
		 * UTILITY LABEL
		 * Used to display the current panel shown
		 */
		currentUtilityLabel = new JLabel();
		currentUtilityLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		menuBar.add(currentUtilityLabel);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
	}	
	
	
	/*
	 * GETTER FOR OPERATION ACTION LISTENER
	 * Calls the PanelContainer swapPanel() method with the name of the panel to swap to as argument
	 */
	private ActionListener getOperationActionListener()
	{
		return  new ActionListener() 
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{
				AbstractButton radioButton = (AbstractButton) actionEvent.getSource();
				
				switch(radioButton.getName())
				{
				case "Binary":
					PanelContainer.swapPanel(EPanelName.OPERATION_BINARY);
					currentUtilityLabel.setText("(Binary - Operations)");
					break;
				}
			}
		};
	}
	
	
	/*
	 * GETTER FOR CONVERSION ACTION LISTENER
	 * Calls the PanelContainer swapPanel() method with the name of the panel to swap to as argument
	 */
	private ActionListener getConversionActionListener()
	{
		return new ActionListener() 
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{
				AbstractButton radioButton = (AbstractButton) actionEvent.getSource();
				
				switch(radioButton.getName())
				{
				case "Binary":
					PanelContainer.swapPanel(EPanelName.CONVERTER_BINARY);
					currentUtilityLabel.setText("(Binary - Conversion)");
					break;
				case "Complement":
					PanelContainer.swapPanel(EPanelName.CONVERTER_COMPLEMENT);
					currentUtilityLabel.setText("(Conversion - Complement)");
					break;
				}
			}
		};
	}
	
	
	/*
	 * GETTER FOR TRUTH TABLE ACTION LISTENER
	 * Calls the PanelContainer swapPanel() method with the name of the panel to swap to as argument
	 */
	private ActionListener getTruthTableActionListener()
	{
		return  new ActionListener() 
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{		
				PanelContainer.swapPanel(EPanelName.TRUTH_TABLES);
				currentUtilityLabel.setText("(Truth Tables)");
			}
		};
	}
}
	
	
