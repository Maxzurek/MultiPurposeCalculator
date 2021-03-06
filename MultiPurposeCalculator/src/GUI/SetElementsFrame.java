package GUI;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SetElementsFrame 
{
	private static JFrame mainFrame;
	private static JFrame setElementsframe;
	private JPanel contentPane;
	private static JComboBox<Object> selectionComboBox;
	private static Rectangle mainFrameBounds;
	private static int setElementsXPos;
	private int currentComboBoxIndex = 0;

	private static ArrayList<JTextField> nameTextFields = new ArrayList<JTextField>();
	private static ArrayList<JTextField> elementTextFields = new ArrayList<JTextField>();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupFrame(Core core)
	{
		mainFrame = core.getFrame();
		mainFrameBounds = mainFrame.getBounds();
		setElementsXPos = mainFrameBounds.x + mainFrameBounds.width;
		
		setElementsframe = new JFrame();
		setElementsframe.setTitle("Set Elements");
		setElementsframe.setBounds(setElementsXPos, mainFrameBounds.x, 610, 100);
		setElementsframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setElementsframe.setVisible(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, 0.0};
		
		contentPane = (JPanel) setElementsframe.getContentPane();
		contentPane.setLayout(gridBagLayout);
		
		Object[] comboBoxSelection = {"(Select number of set)", "1", "2", "3", "4"};
		selectionComboBox = new JComboBox<Object>(comboBoxSelection);
		selectionComboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		//selectionComboBox.setBounds(74, 554, 318, 35);
		selectionComboBox.addItemListener(getSelectionComboBoxItemListener());
		GridBagConstraints gbc_selectionComboBox = new GridBagConstraints();
		gbc_selectionComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_selectionComboBox.gridx = 1;
		gbc_selectionComboBox.gridy = 0;
		contentPane.add(selectionComboBox, gbc_selectionComboBox);
		
	}
	
	public static void displayFrame()
	{
		setElementsframe.setLocation(mainFrame.getLocationOnScreen());
		setElementsframe.setVisible(true);
	}
	
	public static int getComboBoxIndex()
	{
		return selectionComboBox.getSelectedIndex();
	}
	
	public static String getSetElements(String setName)
	{
		for(Map.Entry<String, String> entry : getSets().entrySet())
		{
			if(entry.getKey().toLowerCase().contentEquals(setName))
			{
				return entry.getValue();
			}
		}
		
		return null;
	}
	
	public static Map<String, String> getSets()
	{
		Map<String,String> sets = new TreeMap<String,String>();
		
		for(int i = 0; i < nameTextFields.size(); i++)
		{
			sets.put(nameTextFields.get(i).getText(), elementTextFields.get(i).getText());
		}
		
		return sets;
	}
	
	private ItemListener getSelectionComboBoxItemListener()
	{
		return new ItemListener() 
		{
			public void itemStateChanged(ItemEvent event) 
			{
				int selectedIndex = selectionComboBox.getSelectedIndex();
				String setNames = "UABC";
				
				if(currentComboBoxIndex != selectedIndex)
				{
					Component[] components = contentPane.getComponents();
					
					for(Component component : components)
					{
						if(component instanceof JTextField)
						{
							nameTextFields.clear();
							elementTextFields.clear();
							contentPane.remove(component);
						}
					}	
					
					for(int i = 0; i < selectedIndex; i++)
					{
						nameTextFields.add(new JTextField(3));
						nameTextFields.get(i).setName("name" + i);
						nameTextFields.get(i).setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 12));
						nameTextFields.get(i).setText(Character.toString(setNames.charAt(i)));
						GridBagConstraints gbc_textField1 = new GridBagConstraints();
						gbc_textField1.fill = GridBagConstraints.HORIZONTAL;
						gbc_textField1.gridx = 0;
						gbc_textField1.gridy = i+1;
						contentPane.add(nameTextFields.get(i),gbc_textField1);
						
						elementTextFields.add(new JTextField(50));
						elementTextFields.get(i).setName("elements" + i);
						elementTextFields.get(i).setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 12));
						elementTextFields.get(i).setText("{}");
						GridBagConstraints gbc_textField2 = new GridBagConstraints();
						gbc_textField2.fill = GridBagConstraints.HORIZONTAL;
						gbc_textField2.gridx = 1;
						gbc_textField2.gridy = i+1;
						contentPane.add(elementTextFields.get(i),gbc_textField2);
					}		
				}

				currentComboBoxIndex = selectedIndex;
				setElementsframe.pack();
				
				if(elementTextFields.size() > 0)
				{
					elementTextFields.get(0).requestFocus();
					elementTextFields.get(0).setCaretPosition(1);	
				}
			}
		};
	}
}
