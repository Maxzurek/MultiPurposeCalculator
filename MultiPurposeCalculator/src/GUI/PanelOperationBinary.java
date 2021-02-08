package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import utilities.BinaryConverter;

import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.JTextField;

public class PanelOperationBinary
{	
	private JPanel container;
	private JPanel panelOperationBinary;
	private JComboBox<Object> operationComboBox;
	private JComboBox<Object> baseComboBox1;
	private JComboBox<Object> baseComboBox2;
	private JButton equalButton;
	private JTextPane resultTextPane;
	private JTextField inputTextField1;
	private JTextField inputTextField2;
	
	private static EBaseSelection baseSelection1 = EBaseSelection.NONE; 
	private static EBaseSelection baseSelection2 = EBaseSelection.NONE; 
	private static EOperator operator = EOperator.NONE;
	
	private static BinaryConverter binaryConverter = new BinaryConverter();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupPanel(Core core) 
	{
		panelOperationBinary = new JPanel();
		panelOperationBinary.setVisible(false);
		container = PanelContainer.getPanel(EPanelName.CONTAINER);	
		panelOperationBinary.setName("OPERATION_BINARY");
		
		container.add(panelOperationBinary);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 50, 200};
		gridBagLayout.rowHeights = new int[]{54, 64, 80, 65, 33};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		panelOperationBinary.setLayout(gridBagLayout);
		
		inputTextField1 = new JTextField();
		inputTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		inputTextField1.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.SOUTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panelOperationBinary.add(inputTextField1, gbc_textField);
		inputTextField1.setColumns(30);
		
		Object[] operationSelection = {"(Select Operation)", "+", "-", "X", "/"};
		operationComboBox = new JComboBox<Object>(operationSelection);
		((JLabel)operationComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		operationComboBox.setMinimumSize(new Dimension(120, 40));
		operationComboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_operationComboBox = new GridBagConstraints();
		gbc_operationComboBox.anchor = GridBagConstraints.SOUTH;
		gbc_operationComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_operationComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_operationComboBox.gridx = 1;
		gbc_operationComboBox.gridy = 1;
		operationComboBox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent event) 
			{
				int selectedIndex = operationComboBox.getSelectedIndex();
				
				switch(selectedIndex)
				{
				case 0:
					operator = EOperator.NONE;
					break;
				case 1:
					operator = EOperator.PLUS;
					break;
				case 2:
					operator = EOperator.MINUS;
					break;
				case 3:
					operator = EOperator.MULTIPLY;
					break;
				case 4:
					operator = EOperator.DIVIDE;
					break;
				}
				inputTextField2.requestFocus();
			}
		});
		panelOperationBinary.add(operationComboBox, gbc_operationComboBox);
		
		Object[] baseSelections = {"(Select Base)", "Decimal", "Binary", "Octal", "Hexadecimal"};
		baseComboBox1 = new JComboBox<Object>(baseSelections);
		baseComboBox1.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_baseComboBox1 = new GridBagConstraints();
		gbc_baseComboBox1.fill = GridBagConstraints.HORIZONTAL;
		gbc_baseComboBox1.anchor = GridBagConstraints.NORTH;
		gbc_baseComboBox1.insets = new Insets(0, 0, 5, 5);
		gbc_baseComboBox1.gridx = 0;
		gbc_baseComboBox1.gridy = 2;
		baseComboBox1.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent event) 
			{
				int selectedIndex = baseComboBox1.getSelectedIndex();
				baseSelection1 = getBaseSelection(selectedIndex);
				inputTextField1.requestFocus();
			}
		});
		
		inputTextField2 = new JTextField();
		inputTextField2.setHorizontalAlignment(SwingConstants.CENTER);
		inputTextField2.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.SOUTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		panelOperationBinary.add(inputTextField2, gbc_textField_1);
		inputTextField2.setColumns(30);
		panelOperationBinary.add(baseComboBox1, gbc_baseComboBox1);
		
		baseComboBox2 = new JComboBox<Object>(baseSelections);
		((JLabel)baseComboBox2.getRenderer()).setHorizontalAlignment(SwingConstants.RIGHT);
		baseComboBox2.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_baseComboBox2 = new GridBagConstraints();
		gbc_baseComboBox2.anchor = GridBagConstraints.NORTH;
		gbc_baseComboBox2.insets = new Insets(0, 0, 5, 0);
		gbc_baseComboBox2.fill = GridBagConstraints.HORIZONTAL;
		gbc_baseComboBox2.gridx = 2;
		gbc_baseComboBox2.gridy = 2;
		baseComboBox2.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent event) 
			{
				int selectedIndex = baseComboBox2.getSelectedIndex();
				baseSelection2 = getBaseSelection(selectedIndex);
				inputTextField2.requestFocus();
			}
		});
		panelOperationBinary.add(baseComboBox2, gbc_baseComboBox2);
		
		equalButton = new JButton("=");
		equalButton.setFont(new Font("Tahoma", Font.BOLD, 28));
		GridBagConstraints gbc_equalButton = new GridBagConstraints();
		gbc_equalButton.fill = GridBagConstraints.BOTH;
		gbc_equalButton.insets = new Insets(0, 0, 5, 5);
		gbc_equalButton.gridx = 1;
		gbc_equalButton.gridy = 3;
		equalButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				equalButtonAction();
			}
		});
		panelOperationBinary.add(equalButton, gbc_equalButton);
		
		resultTextPane = new JTextPane();
		resultTextPane.setFont(new Font("Tahoma", Font.BOLD, 16));
		resultTextPane.setText("Result");
		GridBagConstraints gbc_resultTextPane = new GridBagConstraints();
		gbc_resultTextPane.gridwidth = 3;
		gbc_resultTextPane.fill = GridBagConstraints.BOTH;
		gbc_resultTextPane.gridx = 0;
		gbc_resultTextPane.gridy = 5;
		panelOperationBinary.add(resultTextPane, gbc_resultTextPane);
	}
	
	private EBaseSelection getBaseSelection(int selectedIndex)
	{
		switch(selectedIndex)
		{
		case 0:
			return EBaseSelection.NONE;
		case 1:
			return EBaseSelection.DECIMAL;
		case 2:
			return EBaseSelection.BINARY;
		case 3:
			return EBaseSelection.OCTAL;
		case 4:
			return EBaseSelection.HEXA;
		default:
			return EBaseSelection.NONE;
		}
	}
	
	private void equalButtonAction()
	{
		String inputValue1;
		String inputValue2;
		boolean isValid1 = false;
		boolean isValid2 = false;
		String stringValue1;
		String stringValue2;
		
		inputValue1 = inputTextField1.getText();
		inputValue2 = inputTextField2.getText();
		isValid1 = binaryConverter.isValidInput(inputValue1, baseSelection1);
		isValid2 = binaryConverter.isValidInput(inputValue2, baseSelection2);
		
		if(baseSelection1 == EBaseSelection.NONE || baseSelection2 == EBaseSelection.NONE)
		{
			resultTextPane.setText("Error! Select the base to operate from.");
		}
		else if(!isValid1 || !isValid2)
		{
			resultTextPane.setText("Invalid Input!");
		}
		else if(baseSelection1 == EBaseSelection.DECIMAL || baseSelection2 == EBaseSelection.DECIMAL)
		{
			doOperation(inputValue1, inputValue2);
		}
		else
		{
			stringValue1 = binaryConverter.toDecimal(inputValue1, baseSelection1);
			stringValue2 = binaryConverter.toDecimal(inputValue2, baseSelection2);
			
			doOperation(stringValue1, stringValue2);
		}	
	}
	
	private void doOperation(String stringValue1,String stringValue2)
	{
		double doubleValue1;
		double doubleValue2;
		double result = 0;
		String stringDecimalResult = "";
		String stringBinaryResult = "";
		String stringOctalResult = "";
		String stringHexaResult = "";
		
		doubleValue1 = Double.valueOf(stringValue1);
		doubleValue2 = Double.valueOf(stringValue2);
		
		switch(operator)
		{
		case NONE:
			resultTextPane.setText("Error! Select the operator.");
			operationComboBox.requestFocus();
			return;
		case PLUS:
			result = doubleValue1 + doubleValue2;
			resultTextPane.requestFocus();
			break;
		case MINUS:
			result = doubleValue1 - doubleValue2;
			resultTextPane.requestFocus();
			break;
		case MULTIPLY:
			result = doubleValue1 * doubleValue2;
			resultTextPane.requestFocus();
			break;
		case DIVIDE:
			result = doubleValue1 / doubleValue2;
			resultTextPane.requestFocus();
			break;
		}
		
		stringDecimalResult = String.valueOf(result);
		stringBinaryResult = binaryConverter.fromDecimal(stringDecimalResult, EBaseSelection.BINARY);
		stringOctalResult = binaryConverter.fromDecimal(stringDecimalResult, EBaseSelection.OCTAL);
		stringHexaResult = binaryConverter.fromDecimal(stringDecimalResult, EBaseSelection.HEXA);
		
		resultTextPane.setText("Decimal: " + stringDecimalResult 
				+ "\n" + "Binary: " + stringBinaryResult 
				+ "\n" + "Octal: " + stringOctalResult
				+ "\n" + "Hexadecimal: " + stringHexaResult);
	}
	
	public JPanel getPanel()
	{
		return panelOperationBinary;
	}
}
