package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;

public class PanelWelcome 
{
	private JPanel container;
	private JPanel panelWelcome;
	private BufferedImage backgroundImage = null;
	private JTextArea txtrchooseUtility;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupPanel(Core core)
	{
		//Try to get the image from the source package folder
		try 
		{
			backgroundImage = ImageIO.read(getClass().getResource("/calculator_background.png"));
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
		
		//Panel Welcome 
		panelWelcome = new JPanel()
		{
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 55, 20, null);
            }
        };
		panelWelcome.setName("WELCOME");
		container = PanelContainer.getPanel(EPanelName.CONTAINER);
		container.add(panelWelcome, "WELCOME");
		GridBagLayout gbl_panelWelcome = new GridBagLayout();
		gbl_panelWelcome.columnWidths = new int[]{96, 0};
		gbl_panelWelcome.rowHeights = new int[]{21, 0};
		gbl_panelWelcome.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelWelcome.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelWelcome.setLayout(gbl_panelWelcome);
		
		txtrchooseUtility = new JTextArea();
		txtrchooseUtility.setBackground(SystemColor.control);
		txtrchooseUtility.setEditable(false);
		txtrchooseUtility.setFont(new Font("Myanmar Text", Font.BOLD, 18));
		txtrchooseUtility.setText("    ^ -- Choose Utility");
		GridBagConstraints gbc_txtrchooseUtility = new GridBagConstraints();
		gbc_txtrchooseUtility.fill = GridBagConstraints.BOTH;
		gbc_txtrchooseUtility.gridx = 0;
		gbc_txtrchooseUtility.gridy = 0;
		panelWelcome.add(txtrchooseUtility, gbc_txtrchooseUtility);
	}
	
	public JPanel getPanel()
	{
		return panelWelcome;
	}
}
