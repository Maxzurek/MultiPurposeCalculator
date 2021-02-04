package GUI;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import utilities.BinaryConverter;

public class PanelConversionBinary 
{
	private static EBaseSelection baseSelection = EBaseSelection.NONE; 
	private static BinaryConverter binaryConverter = new BinaryConverter();
	
	private JPanel container;
	private static JPanel panelConversionBinary;
	private JTextField inputValue;
	private JLabel resultLabel1;
	private JTextPane resultTextPane1;
	private JLabel resultLabel2;
	private JTextPane resultTextPane2;
	private JLabel resultLabel3;
	private JTextPane resultTextPane3;
	private JButton convertButton;
	private JComboBox<Object> comboBox ;

	public void setupPanel(Core core)
	{	
		panelConversionBinary = new JPanel();
		container = PanelContainer.getPanel(EPanelName.CONTAINER);
		container.add(panelConversionBinary);
		
		//Setup the panel
		panelConversionBinary.setName("CONVERTER_BINARY");
		GridBagLayout gbl_converterBinaryPanel = new GridBagLayout();
		gbl_converterBinaryPanel.columnWidths = new int[]{163, 159, 0};
		gbl_converterBinaryPanel.rowHeights = new int[]{43, 26, 62, 62, 64, 0};
		gbl_converterBinaryPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_converterBinaryPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelConversionBinary.setLayout(gbl_converterBinaryPanel);
		
		//Add valueTextField
		inputValue = new JTextField();
		inputValue.setFont(new Font("Tahoma", Font.BOLD, 12));
		inputValue.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_valueTextField = new GridBagConstraints();
		gbc_valueTextField.insets = new Insets(0, 0, 5, 0);
		gbc_valueTextField.fill = GridBagConstraints.BOTH;
		gbc_valueTextField.gridx = 1;
		gbc_valueTextField.gridy = 0;
		inputValue.setColumns(5);
		inputValue.selectAll();
		panelConversionBinary.add(inputValue, gbc_valueTextField);
		
		//Add resultLabel1
		resultLabel1 = new JLabel();
		resultLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		resultLabel1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_resultLabel1 = new GridBagConstraints();
		gbc_resultLabel1.insets = new Insets(0, 0, 5, 5);
		gbc_resultLabel1.gridx = 0;
		gbc_resultLabel1.gridy = 2;
		panelConversionBinary.add(resultLabel1, gbc_resultLabel1);
		
		//Add resultTextPane1
		resultTextPane1 = new JTextPane();
		resultTextPane1.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_resultTextPane1 = new GridBagConstraints();
		gbc_resultTextPane1.insets = new Insets(0, 0, 5, 0);
		gbc_resultTextPane1.fill = GridBagConstraints.BOTH;
		gbc_resultTextPane1.gridx = 1;
		gbc_resultTextPane1.gridy = 2;
		panelConversionBinary.add(resultTextPane1, gbc_resultTextPane1);
		
		//Add resultLabel2
		resultLabel2 = new JLabel();
		resultLabel2.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_resultLabel2 = new GridBagConstraints();
		gbc_resultLabel2.insets = new Insets(0, 0, 5, 5);
		gbc_resultLabel2.gridx = 0;
		gbc_resultLabel2.gridy = 3;
		panelConversionBinary.add(resultLabel2, gbc_resultLabel2);
		
		//Add resultTextPane2
		resultTextPane2 = new JTextPane();
		resultTextPane2.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_resultTextPane2 = new GridBagConstraints();
		gbc_resultTextPane2.insets = new Insets(0, 0, 5, 0);
		gbc_resultTextPane2.fill = GridBagConstraints.BOTH;
		gbc_resultTextPane2.gridx = 1;
		gbc_resultTextPane2.gridy = 3;
		panelConversionBinary.add(resultTextPane2, gbc_resultTextPane2);
		
		//Add resultLabel3
		resultLabel3 = new JLabel();
		resultLabel3.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_resultLabel3 = new GridBagConstraints();
		gbc_resultLabel3.insets = new Insets(0, 0, 0, 5);
		gbc_resultLabel3.gridx = 0;
		gbc_resultLabel3.gridy = 4;
		panelConversionBinary.add(resultLabel3, gbc_resultLabel3);
		
		//add resultTextPane3
		resultTextPane3 = new JTextPane();
		resultTextPane3.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_resultTextPane3 = new GridBagConstraints();
		gbc_resultTextPane3.fill = GridBagConstraints.BOTH;
		gbc_resultTextPane3.gridx = 1;
		gbc_resultTextPane3.gridy = 4;
		panelConversionBinary.add(resultTextPane3, gbc_resultTextPane3);
		
		//add convertButton
		convertButton = new JButton("Convert");
		convertButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_convertButton = new GridBagConstraints();
		gbc_convertButton.insets = new Insets(0, 0, 5, 0);
		gbc_convertButton.gridx = 1;
		gbc_convertButton.gridy = 1;
		//Create Action Listener for the convertButton
		convertButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent actionEvent) 
		      { 
				convertButtonAction();
		      }
		});
		panelConversionBinary.add(convertButton, gbc_convertButton);

		//Create comboBox
		Object[] items = {"(Select base to convert)", "Decimal", "Binary", "Octal", "Hexadecimal"};
		comboBox = new JComboBox<Object>(items);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox.setEditable(false);
		comboBox.setMaximumRowCount(5);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panelConversionBinary.add(comboBox, gbc_comboBox);	
		//Create item listener for the comboBox
		comboBox.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent event) 
			{
				int selectedIndex = comboBox.getSelectedIndex();

				inputValue.setText("");
				inputValue.requestFocus();
				clearResultTextPanes();
						
				switch(selectedIndex)
				{
				case 0:
					baseSelection = EBaseSelection.NONE;
					break;
				case 1:
					baseSelection = EBaseSelection.DECIMAL;
			
					resultLabel1.setText("Binary");
					resultLabel2.setText("Octal");
					resultLabel3.setText("Hexadecimal");
					break;
				case 2:
					baseSelection = EBaseSelection.BINARY;

					resultLabel1.setText("Decimal");
					resultLabel2.setText("Octal");
					resultLabel3.setText("Hexadecimal");
					break;
				case 3:
					baseSelection = EBaseSelection.OCTAL;
			
					resultLabel1.setText("Decimal");
					resultLabel2.setText("Binary");
					resultLabel3.setText("Hexadecimal");
					break;
				case 4:
					baseSelection = EBaseSelection.HEXA;

					resultLabel1.setText("Decimal");
					resultLabel2.setText("Binary");
					resultLabel3.setText("Octal");
					break;
				}
			}
		});	
	}
	
	private static void convertButtonAction()
	{
		final int NUMBOFTEXTPANES = 3;
		int count =0;
		String inputValue;
		String decimalString;
		JTextField valueTextField = null;
		JTextPane[] textPanes = new JTextPane[NUMBOFTEXTPANES];	
		
		Component[] components = PanelContainer.getPanel(EPanelName.CONVERTER_BINARY).getComponents();
		
		for(Component component : components)
		{
			if(component instanceof JTextField)
			{
				valueTextField = (JTextField)component;
			}
			else if(component  instanceof JTextPane)
			{
				textPanes[count] = (JTextPane)component;
				count++;
			}
		}
		
		inputValue = valueTextField.getText();
		
		if(!binaryConverter.isValidInput(inputValue, baseSelection))
		{
			setResultTextPanes();
			return;
		}
		
        switch(baseSelection)
        {
        case DECIMAL:
        	textPanes[0].setText(binaryConverter.fromDecimal(inputValue, EBaseSelection.BINARY));
        	textPanes[1].setText(binaryConverter.fromDecimal(inputValue, EBaseSelection.OCTAL));
        	textPanes[2].setText(binaryConverter.fromDecimal(inputValue, EBaseSelection.HEXA));
        	break;
        case BINARY:
        	decimalString = binaryConverter.toDecimal(inputValue, baseSelection);
        	
        	textPanes[0].setText(decimalString);
        	textPanes[1].setText(binaryConverter.fromDecimal(decimalString, EBaseSelection.OCTAL));
        	textPanes[2].setText(binaryConverter.fromDecimal(decimalString, EBaseSelection.HEXA));
        	break;
        case OCTAL:
        	decimalString = binaryConverter.toDecimal(inputValue, baseSelection);
        	
        	textPanes[0].setText(decimalString);
        	textPanes[1].setText(binaryConverter.fromDecimal(decimalString, EBaseSelection.BINARY));
        	textPanes[2].setText(binaryConverter.fromDecimal(decimalString, EBaseSelection.HEXA));
        	break;
        case HEXA:
        	decimalString = binaryConverter.toDecimal(inputValue, baseSelection);
        	
        	textPanes[0].setText(decimalString);
        	textPanes[1].setText(binaryConverter.fromDecimal(decimalString, EBaseSelection.BINARY));
        	textPanes[2].setText(binaryConverter.fromDecimal(decimalString, EBaseSelection.OCTAL));
        	break;
        default:
        	for(JTextPane textPane : textPanes)
        	{
        		textPane.setText("Error! Select the base to convert from.");
        	}
        	break;	
        }
	}
	
	
	private void clearResultTextPanes()
	{
		Component[] components = panelConversionBinary.getComponents();
		JTextPane textPane;
		
		for(Component component : components)
		{
			if(component instanceof JTextPane)
			{
				textPane = (JTextPane)component;
				textPane.setText("");
			}
		}
	}
	
	
    //Set the text in all the resultTextPane1-3 to: Invalid Input
	private static void setResultTextPanes()
	{
		Component[] components = panelConversionBinary.getComponents();
		JTextPane textPane;
		
		for(Component component : components)
		{
			if(component instanceof JTextPane)
			{
				textPane = (JTextPane)component;
				textPane.setText("Invalid Input");
			}
		}
	}
	
	public JPanel getPanel()
	{
		return panelConversionBinary;
	}
}
