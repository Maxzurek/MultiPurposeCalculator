package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelLogicalProperties 
{
	private JPanel container;
	private JPanel panelLogicalProperties;
	private BufferedImage backgroundImage = null;
	
	public void setupPanel(Core core)
	{
		//Try to get the image from the source package folder
		try 
		{
			backgroundImage = ImageIO.read(getClass().getResource("/proprietees_operateurs_logiques.png"));
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
		
		//Panel Truth Tables
		panelLogicalProperties = new JPanel(){
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
        panelLogicalProperties.setName("LOGICAL_PROPERTIES");
        panelLogicalProperties.setVisible(false);
		container = PanelContainer.getPanel(EPanelName.CONTAINER);
		container.add(panelLogicalProperties, "LOGICAL_PROPERTIES");
	}
	
	public JPanel getPanel()
	{
		return panelLogicalProperties;
	}

}
