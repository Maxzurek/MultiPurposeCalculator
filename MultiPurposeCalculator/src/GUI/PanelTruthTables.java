package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelTruthTables
{
	private JPanel container;
	private JPanel panelTruthTables;
	private BufferedImage backgroundImage = null;
	
	public void setupPanel(Core core)
	{
		//Try to get the image from the source package folder
		try 
		{
			backgroundImage = ImageIO.read(getClass().getResource("/TruthTables.png"));
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
		
		//Panel Truth Tables
		panelTruthTables = new JPanel()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
            }
        };
		panelTruthTables.setName("TRUTH_TABLES");
		panelTruthTables.setVisible(false);
		container = PanelContainer.getPanel(EPanelName.CONTAINER);
		container.add(panelTruthTables, "TRUTH_TABLES");
	}
	
	public JPanel getPanel()
	{
		return panelTruthTables;
	}
}
