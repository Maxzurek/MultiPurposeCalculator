package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;

public class PanelSetElements 
{
	private static JLayeredPane panelCalculator;
	private static JPanel panelSetElements;
	private static JCheckBox defineSetCheckbox;
	private static int currentNumOfSet = 0;
	private static boolean definedSets = false;
	public static boolean areSetsDefined() {return definedSets;}

	private static ArrayList<JLabel> nameLabels = new ArrayList<JLabel>();
	private static ArrayList<JTextField> elementTextFields = new ArrayList<JTextField>();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupPanel(Core core, JLayeredPane panelCalculator)
	{		
		PanelSetElements.panelCalculator = panelCalculator;
		
		panelSetElements = new JPanel();
		panelSetElements.setBounds(660, 480, 560, 100);
		panelSetElements.setVisible(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40,393};
		gridBagLayout.rowHeights = new int[]{};
		gridBagLayout.columnWeights = new double[]{0.0,1.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		panelSetElements.setLayout(gridBagLayout);
		panelCalculator.add(panelSetElements);
		
		defineSetCheckbox = new JCheckBox("Defined sets");
		defineSetCheckbox.setFont(new Font("Tahoma", Font.BOLD, 11));
		defineSetCheckbox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				if(defineSetCheckbox.isSelected())
				{
					definedSets = true;
					elementTextFields.get(0).requestFocus();
					elementTextFields.get(0).setCaretPosition(1);
				}
				else
				{
					definedSets = false;
				}
			}
		});
		GridBagConstraints gbc_CheckBox = new GridBagConstraints();
		gbc_CheckBox.insets = new Insets(0, 0, 1, 5);
		gbc_CheckBox.gridx = 0;
		gbc_CheckBox.gridy = 0;
		panelSetElements.add(defineSetCheckbox, gbc_CheckBox);	
		
		JButton resetSetButton = new JButton("Reset Sets");
		resetSetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				for(JTextField textField: elementTextFields)
				{
					textField.setText("{}");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		panelSetElements.add(resetSetButton, gbc_btnNewButton);
		
		setPanelComponent();
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
		
		for(int i = 0; i < nameLabels.size(); i++)
		{
			String elements = "{";
			elements = elements.concat(elementTextFields.get(i).getText());
			elements = elements.concat("}");
			
			sets.put(nameLabels.get(i).getName(), elements);
		}
		
		return sets;
	}
	
	public static void setPanelComponent()
	{
		final char[] SET_NAMES = {'U', 'A', 'B', 'C'};
		int numOfSet;
				
		if(PanelGraphics.isDraw3Circles())
		{
			numOfSet = 3;
		}
		else
		{
			numOfSet = 2;
		}

		if(currentNumOfSet != numOfSet)
		{
			Component[] components = panelSetElements.getComponents();
			
			for(Component component : components)
			{
				if(component instanceof JLabel)
				{
					panelSetElements.remove(component);
				}
				else if(component instanceof JTextField)
				{
					panelSetElements.remove(component);
				}
			}	
				
			nameLabels.clear();
			elementTextFields.clear();
			
			for(int i = 0; i <= numOfSet; i++)
			{
				JLabel nameLabel = new JLabel(Character.toString(SET_NAMES[i]));
				nameLabel.setName(Character.toString(SET_NAMES[i]));
				nameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
				GridBagConstraints gbc_newLabel = new GridBagConstraints();
				gbc_newLabel.fill = GridBagConstraints.HORIZONTAL;
				gbc_newLabel.gridx = 0;
				gbc_newLabel.gridy = i+1;
				nameLabels.add(nameLabel);
				panelSetElements.add(nameLabels.get(i),gbc_newLabel);
				
				/*
				JLabel curlyBracesLabel1 = new JLabel("{");
				curlyBracesLabel1.setFont(new Font("Tahoma", Font.BOLD, 15));
				curlyBracesLabels.add(curlyBracesLabel1);
				GridBagConstraints gbc_curlyLabel1 = new GridBagConstraints();
				gbc_curlyLabel1.fill = GridBagConstraints.HORIZONTAL;
				//gbc_curlyLabel1.anchor = GridBagConstraints.EAST;
				//gbc_curlyLabel1.insets = new Insets(0, 0, 0, 5);
				gbc_curlyLabel1.gridx = 1;
				gbc_curlyLabel1.gridy = i+1;
				panelSetElements.add(curlyBracesLabel1, gbc_curlyLabel1);
				*/
				
				JTextField textField = new JTextField(50);
				textField.setText("{}");
				textField.setName("elements" + i);
				textField.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 13));
				textField.setColumns(10);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.insets = new Insets(0, 0, 0, 5);
				gbc_textField.gridx = 1;
				gbc_textField.gridy = i+1;
				elementTextFields.add(textField);
				panelSetElements.add(elementTextFields.get(i),gbc_textField);
				
				/*
				JLabel curlyBracesLabel2 = new JLabel("}");
				curlyBracesLabel2.setFont(new Font("Tahoma", Font.BOLD, 15));
				curlyBracesLabels.add(curlyBracesLabel2);
				GridBagConstraints gbc_curlyLabel2 = new GridBagConstraints();
				gbc_curlyLabel2.fill = GridBagConstraints.HORIZONTAL;
				//gbc_curlyLabel2.anchor = GridBagConstraints.EAST;
				//gbc_curlyLabel2.insets = new Insets(0, 0, 0, 5);
				gbc_curlyLabel2.gridx = 3;
				gbc_curlyLabel2.gridy = i+1;
				panelSetElements.add(curlyBracesLabel2, gbc_curlyLabel2);
				*/
			}		
		}

		currentNumOfSet = numOfSet;
		panelCalculator.setVisible(false);
		panelCalculator.setVisible(true);
				
		if(definedSets)
		{
			elementTextFields.get(0).requestFocus();
			elementTextFields.get(0).setCaretPosition(1);
		}
		
	}
}
