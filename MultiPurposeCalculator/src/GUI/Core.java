package GUI;

import java.awt.EventQueue;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class Core 
{
	private JFrame frame;
	private Menu menu;
	private PanelContainer panelContainer;
	private static FileWriter myWriter;
	private static String version = "5.4";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		try
    	{
			myWriter = new FileWriter("version.txt");
			myWriter.write(version);
			myWriter.close();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					Core window = new Core();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Core() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setTitle("Multi Purpose Calculator - (Calculator)");
		frame.setBounds(100, 100, 665, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new Menu();
		menu.setupMenu(this);
		
		panelContainer = new PanelContainer();
		panelContainer.setupPanels(this);
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public PanelContainer getPanelContainer()
	{
		return panelContainer;
	}
}
