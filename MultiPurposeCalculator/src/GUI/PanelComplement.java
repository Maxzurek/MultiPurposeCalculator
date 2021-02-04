package GUI;

import utilities.EComplementSelection;
import utilities.SignModuleComplement;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class PanelComplement 
{
	private JLayeredPane panelComplement;
	private JPanel container;
	private JTextField inputDecimal;
	private JTextField inputBite1;
	private JComboBox<Object> conversionComboBox;
	private JTextField inputBite2;
	private JTextField inputBite3;
	private JTextField inputBite4;
	private JTextField inputBite5;
	private JTextField inputBite6;
	private JTextField inputBite7;
	private JTextField inputBite8;
	private JButton convertButton;
	private JTextPane resultDecimal;
	private JTextField resultSignModule;
	private JTextField result2sComplement;
	private JLabel signModuleLabel;
	private JLabel Complement2sLabel;
	private JLabel decimalLabel;
	private JButton refreshButton;
	BufferedImage refreshButtonImage = null;
	private ImageIcon refreshIcon = new ImageIcon(getClass().getResource("/refresh_icon.jpg"));

	DocumentListener documentListener;
	private ArrayList<Component> order;
	private ArrayList<JTextField> inputBiteTextFields;
	
	private EComplementSelection complementSelection = EComplementSelection.NONE;
	
	private SignModuleComplement signModuleComplement;
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupPanel(Core core) 
	{
		signModuleComplement = new SignModuleComplement();
		container = PanelContainer.getPanel(EPanelName.CONTAINER);	
		panelComplement = new JLayeredPane();
		container.add(panelComplement);
		panelComplement.setName("CONVERTER_COMPLEMENT");
			
		Object[] conversionSelection = {"(Selection conversion)", "From decimal", "From Sign Module", "From 2's Complement"};
		conversionComboBox = new JComboBox<Object>(conversionSelection);
		conversionComboBox.setBounds(26, 43, 244, 32);
		panelComplement.add(conversionComboBox);	
		
		inputDecimal = new JTextField();
		inputDecimal.setFont(new Font("Tahoma", Font.BOLD, 14));
		inputDecimal.setBounds(290,42,292,32);
		inputDecimal.setColumns(30);
		inputDecimal.setVisible(false);
		panelComplement.add(inputDecimal);
		
		createDocumentListenerForBites();
		
		inputBite1 = new JTextField();
		inputBite1.getDocument().addDocumentListener(documentListener);
		inputBite1.getDocument().putProperty("BITE", inputBite1);
		inputBite1.setName("BITE1");
		inputBite1.setHorizontalAlignment(SwingConstants.CENTER);
		inputBite1.setFont(new Font("Tahoma", Font.BOLD, 14));
		inputBite1.setBackground(Color.GRAY);
		inputBite1.setOpaque(false);
		inputBite1.setBounds(290,43,33,32);
		inputBite1.setVisible(false);
		panelComplement.add(inputBite1);
		
		inputBite2 = new JTextField();
		inputBite2.getDocument().addDocumentListener(documentListener);
		inputBite2.getDocument().putProperty("BITE", inputBite2);
		inputBite2.setName("BITE2");
		inputBite2.setHorizontalAlignment(SwingConstants.CENTER);
		inputBite2.setFont(new Font("Tahoma", Font.BOLD, 14));
		inputBite2.setBounds(327, 43, 33, 32);
		inputBite2.setVisible(false);
		panelComplement.add(inputBite2);
		
		inputBite3 = new JTextField();
		inputBite3.getDocument().addDocumentListener(documentListener);
		inputBite3.getDocument().putProperty("BITE", inputBite3);
		inputBite3.setName("BITE3");
		inputBite3.setHorizontalAlignment(SwingConstants.CENTER);
		inputBite3.setFont(new Font("Tahoma", Font.BOLD, 14));
		inputBite3.setBounds(364, 43, 33, 32);
		inputBite3.setVisible(false);
		panelComplement.add(inputBite3);
		
		inputBite4 = new JTextField();
		inputBite4.getDocument().addDocumentListener(documentListener);
		inputBite4.getDocument().putProperty("BITE", inputBite4);
		inputBite4.setName("BITE4");
		inputBite4.setHorizontalAlignment(SwingConstants.CENTER);
		inputBite4.setFont(new Font("Tahoma", Font.BOLD, 14));
		inputBite4.setBounds(401, 43, 33, 32);
		inputBite4.setVisible(false);
		panelComplement.add(inputBite4);
		
		inputBite5 = new JTextField();
		inputBite5.getDocument().addDocumentListener(documentListener);
		inputBite5.getDocument().putProperty("BITE", inputBite5);
		inputBite5.setName("BITE5");
		inputBite5.setHorizontalAlignment(SwingConstants.CENTER);
		inputBite5.setFont(new Font("Tahoma", Font.BOLD, 14));
		inputBite5.setBounds(438, 43, 33, 32);
		inputBite5.setVisible(false);
		panelComplement.add(inputBite5);
		
		inputBite6 = new JTextField();
		inputBite6.getDocument().addDocumentListener(documentListener);
		inputBite6.getDocument().putProperty("BITE", inputBite6);
		inputBite6.setName("BITE6");
		inputBite6.setHorizontalAlignment(SwingConstants.CENTER);
		inputBite6.setFont(new Font("Tahoma", Font.BOLD, 14));
		inputBite6.setBounds(475, 43, 33, 32);
		inputBite6.setVisible(false);
		panelComplement.add(inputBite6);
		
		inputBite7 = new JTextField();
		inputBite7.getDocument().addDocumentListener(documentListener);
		inputBite7.getDocument().putProperty("BITE", inputBite7);
		inputBite7.setName("BITE7");
		inputBite7.setHorizontalAlignment(SwingConstants.CENTER);
		inputBite7.setFont(new Font("Tahoma", Font.BOLD, 14));
		inputBite7.setBounds(512, 43, 33, 32);
		inputBite7.setVisible(false);
		panelComplement.add(inputBite7);
		
		inputBite8 = new JTextField();
		inputBite8.getDocument().addDocumentListener(documentListener);
		inputBite8.getDocument().putProperty("BITE", inputBite8);
		inputBite8.setName("BITE8");
		inputBite8.setHorizontalAlignment(SwingConstants.CENTER);
		inputBite8.setFont(new Font("Tahoma", Font.BOLD, 14));
		inputBite8.setBounds(549, 43, 33, 32);
		inputBite8.setVisible(false);
		panelComplement.add(inputBite8);
		
		//Add all the JTextField inputBite1-8 to an array
		addInputBitesToArray();
		
		convertButton = new JButton("Convert");
		convertButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		convertButton.setBounds(359, 96, 158, 42);
		convertButton.addActionListener(getConvertButtonActionListener());
		panelComplement.add(convertButton);
		
		resultDecimal = new JTextPane();
		resultDecimal.setFont(new Font("Tahoma", Font.BOLD, 14));
		resultDecimal.setBounds(290, 358, 292, 128);
		panelComplement.add(resultDecimal);
		
		resultSignModule = new JTextField();
		resultSignModule.setFont(new Font("Tahoma", Font.BOLD, 14));
		resultSignModule.setBounds(290, 194, 292, 32);
		panelComplement.add(resultSignModule);
		resultSignModule.setColumns(10);
		
		result2sComplement = new JTextField();
		result2sComplement.setFont(new Font("Tahoma", Font.BOLD, 14));
		result2sComplement.setBounds(290, 278, 292, 32);
		panelComplement.add(result2sComplement);
		result2sComplement.setColumns(10);
		
		signModuleLabel = new JLabel("Sign Module");
		signModuleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signModuleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		signModuleLabel.setBounds(74, 197, 168, 24);
		panelComplement.add(signModuleLabel);
		
		Complement2sLabel = new JLabel("2's Complement");
		Complement2sLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Complement2sLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		Complement2sLabel.setBounds(74, 281, 168, 24);
		panelComplement.add(Complement2sLabel);
		
		decimalLabel = new JLabel("Decimal");
		decimalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		decimalLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		decimalLabel.setBounds(74, 403, 168, 24);
		panelComplement.add(decimalLabel);
		
		Image image = refreshIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(30, 23,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		refreshButton = new JButton();
		refreshButton.setBounds(593, 48, 30, 25);
		refreshIcon = new ImageIcon(newimg); // transform it back
		refreshButton.setIcon(refreshIcon);
		refreshButton.addActionListener(getRefreshActionListener());
		panelComplement.add(refreshButton);
		
		conversionComboBox.addItemListener(getConvesionComboBoxItemListener());
		
		setBiteFocusTraversalPolicy();
	}
	
	
	/*
	 * 				ACTION LISTENER FOR CONVERT BUTTON
	 * 
	 */
	private ActionListener getConvertButtonActionListener()
	{
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				String input;
				String decimalResult;
				String complementTwoResult;
				String signModuleResult;
				
				switch(complementSelection)
				{
				case NONE:
					resultDecimal.setText("Error! Select the conversion type");
					resultSignModule.setText("Error! Select the conversion type");
					result2sComplement.setText("Error! Select the conversion type");
					break;
				case DECIMAL:
					input = inputDecimal.getText();
					
					if(signModuleComplement.isValidInput(input, EBaseSelection.DECIMAL))
					{
						signModuleResult = signModuleComplement.fromDecimal(input, EComplementSelection.SIGN_MODULE, EBaseSelection.BINARY);
						resultSignModule.setText(signModuleResult);
						complementTwoResult = signModuleComplement.fromDecimal(input, EComplementSelection.COMPLEMENT_TWO, EBaseSelection.BINARY);
						result2sComplement.setText(complementTwoResult);
						resultDecimal.setText("");
					}
					else
					{
						displayInvalidInput();
					}
					break;
				case SIGN_MODULE:
					input = getBitesInput();
					
					if(signModuleComplement.isValidInput(input, EBaseSelection.BINARY))
					{
						decimalResult = signModuleComplement.toDecimal(input, complementSelection, EBaseSelection.BINARY);
						resultDecimal.setText(decimalResult);
						complementTwoResult = signModuleComplement.fromDecimal(decimalResult, EComplementSelection.COMPLEMENT_TWO, EBaseSelection.BINARY);
						result2sComplement.setText(complementTwoResult);
						resultSignModule.setText("");
					}
					else
					{
						displayInvalidInput();
					}
					break;
				case COMPLEMENT_TWO:
					input = getBitesInput();
					
					if(signModuleComplement.isValidInput(input, EBaseSelection.BINARY))
					{
						decimalResult = signModuleComplement.toDecimal(input, complementSelection, EBaseSelection.BINARY);
						resultDecimal.setText(decimalResult);
						signModuleResult = signModuleComplement.fromDecimal(decimalResult, EComplementSelection.SIGN_MODULE, EBaseSelection.BINARY);
						resultSignModule.setText(signModuleResult);
						result2sComplement.setText("");
					}
					else
					{
						displayInvalidInput();
					}
					break;
				default:
					break;
				}
			}
		};
	}
	
	
	/*
	 * 				ITEM LISTENER FOR CONVERSION COMBO BOX
	 * 
	 */
	private ItemListener getConvesionComboBoxItemListener()
	{
		return new ItemListener() 
		{
			public void itemStateChanged(ItemEvent event) 
			{
				int selectedIndex = conversionComboBox.getSelectedIndex();
				
				switch(selectedIndex)
				{
				case 0:
					complementSelection = EComplementSelection.NONE;
					break;
				case 1:
					complementSelection = EComplementSelection.DECIMAL;
					inputDecimal.setVisible(true);
					inputDecimal.requestFocus();
					hideBiteTextFields();
					break;
				case 2:
					complementSelection = EComplementSelection.SIGN_MODULE;
					inputDecimal.setVisible(false);
					setRequestFocus();
					displayBiteTextFields();
					break;
				case 3:
					complementSelection = EComplementSelection.COMPLEMENT_TWO;
					inputDecimal.setVisible(false);
					setRequestFocus();
					displayBiteTextFields();
					break;
				}
			}
		};
	}
	
	
	/*
	 * 				DOCUMENT LISTENER FOR INPUT BITE TEXTFIELD
	 * 
	 */
	private void createDocumentListenerForBites()
	{
		documentListener = new DocumentListener() 
		{
		    @Override
		    public void insertUpdate(DocumentEvent e)
		    {
		    	int lengthInput;
				int index;
				Object owner;
				JTextField textField;
		    	
				lengthInput = e.getDocument().getLength();
		    	owner = e.getDocument().getProperty("BITE");
		  	
		    	if(lengthInput == 1)
		    	{	
		    		if(owner instanceof JTextField)
		    		{
		    			textField = (JTextField) owner;
		    			index = order.indexOf(textField);
		    			
		    			order.get(index+1).requestFocus();				
		    		}	
		    	}
		    }

		    @Override public void removeUpdate(DocumentEvent e) 
		    { //VOID COULD USE LATER
		    	
		    }	
		    
			@Override public void changedUpdate(DocumentEvent e) 
			{ //VOID COULD USE LATER
				
			}
		};
	}
	
	/*
	 * 				ACTION LISTENER FOR REFRESH BUTTON
	 * 
	 */
	private ActionListener getRefreshActionListener()
	{
		 return new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				clearAllTextField();
				
				switch(complementSelection)
				{
				case DECIMAL:
					inputDecimal.requestFocus();
					break;
				case SIGN_MODULE:
					inputBite1.requestFocus();
					break;
				case COMPLEMENT_TWO:
					inputBite1.requestFocus();
					break;
				default:
					break;
				}
			}
		};
	}
	
	private void displayInvalidInput()
	{
		resultDecimal.setText("Invalid Input! ");
		resultSignModule.setText("Invalid Input! ");
		result2sComplement.setText("Invalid Input! ");
	}
	
	/*
	 * Stores all the BITE TextFields from this pannel
	 * 
	 * Easier to work with all of em since they are all linked
	 */
	private void addInputBitesToArray()
	{
		inputBiteTextFields = new ArrayList<JTextField>();
		
		inputBiteTextFields.add(inputBite1);
		inputBiteTextFields.add(inputBite2);
		inputBiteTextFields.add(inputBite3);
		inputBiteTextFields.add(inputBite4);
		inputBiteTextFields.add(inputBite5);
		inputBiteTextFields.add(inputBite6);
		inputBiteTextFields.add(inputBite7);
		inputBiteTextFields.add(inputBite8);
	}
	
	//Starts from left to right of input BITE
	//set focus the the first textfield that is empty
	private void setRequestFocus()
	{	
		for(JTextField textField : inputBiteTextFields)
		{
			if(textField.getText().length() == 0)
			{
				textField.requestFocus();
				return;
			}
		}
	}
	
	//Set focus from left to right when a character is entered in of the the input BITE textfield
	private void setBiteFocusTraversalPolicy()
	{	
		order = new ArrayList<Component>();
		
		for(JTextField textField : inputBiteTextFields)
		{
			order.add(textField);
		}
		
		order.add(convertButton);
	}
	
	//Get all the text from every input BITE textfield
	//concat each strings to 1 string and return it
	private String getBitesInput()
	{
		String formatedInput = "";
		
		for(JTextField textField : inputBiteTextFields)
		{
			formatedInput = formatedInput.concat(textField.getText());
		}
		
		return formatedInput;
	}
	
	//Set the text from all textfields empty
	private void clearAllTextField()
	{
		for(JTextField textField : inputBiteTextFields)
		{
			textField.setText("");
		}

		inputDecimal.setText("");
		resultDecimal.setText("");
		result2sComplement.setText("");
		resultSignModule.setText("");
	}
	
	//Toggle visibility of all the input BITE textfields
	private void displayBiteTextFields()
	{
		for(JTextField textField : inputBiteTextFields)
		{
			textField.setVisible(true);
		}
	}
	
	private void hideBiteTextFields()
	{
		Component[] components;
		
		components = panelComplement.getComponents();
		
		for(Component component : components)
		{
			if(component instanceof JTextField)
			{
				JTextField textField = (JTextField) component;
				
				if(textField.getName() == "BITE")
				{
					textField.setVisible(false);
				}
			}
		}
	}
	
	public JLayeredPane getPanel()
	{
		return panelComplement;
	}
}
