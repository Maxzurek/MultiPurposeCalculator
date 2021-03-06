package GUI;

import utilities.BinaryConverter;
import utilities.ExpressionCalculator;
import utilities.LogicalExpressionCalculator;
import utilities.SetOperationCalculator;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;

public class PanelCalculator 
{
	private JFrame frame;
	private JLayeredPane panelCalculator;
	private JPanel container;
	private JTextArea inputTextField;
	private JTextArea resultTextPane;
	private JButton equalButton;
	private JComboBox<Object> selectionComboBox;
	private PanelSetElements panelSetElements = new PanelSetElements();
	private JToggleButton logicToggle;
	private JToggleButton SetsToggle;
	private JRadioButton circle3Rdbt;
	private JRadioButton circle2Rdbt;
	
	private PanelGraphics panelGraphics = new PanelGraphics();
	private PanelMathButtons panelMathButtons = new PanelMathButtons();
	private PanelLogicButtons panelLogicButtons = new PanelLogicButtons();
	private PanelSetButtons panelSetButtons = new PanelSetButtons();
	private BinaryConverter binaryConverter = new BinaryConverter();
	private ExpressionCalculator expressionCalculator = new ExpressionCalculator();
	private LogicalExpressionCalculator logicalExpressionCalculator = new LogicalExpressionCalculator();
	private SetOperationCalculator setOperationCalculator = new SetOperationCalculator();
	
	private int previousSelectedIndex = 0;
	private EBaseSelection baseSelection = EBaseSelection.DECIMAL;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupPanel(Core core)
	{
		frame = core.getFrame();
		container = PanelContainer.getPanel(EPanelName.CONTAINER);	
		panelCalculator = new JLayeredPane();
		container.add(panelCalculator);
		panelCalculator.setName("CALCULATOR");
		
		setupPanelItems();	
		panelMathButtons.setupPanel(core, panelCalculator, inputTextField);
		panelLogicButtons.setupPanel(core, panelCalculator, inputTextField);
		panelSetButtons.setupPanel(core, panelCalculator, inputTextField);	
		panelGraphics.setupPanel(core, panelCalculator);
		panelSetElements.setupPanel(core, panelCalculator);
	}
	
	public JLayeredPane getPanel() {return panelCalculator;}
	
	private void setupPanelItems()
	{
		Object[] comboBoxSelection = {"(Select base)", "Decimal", "Binary", "Octal", "Hexadecimal", "Logical", "Set"};
		selectionComboBox = new JComboBox<Object>(comboBoxSelection);
		selectionComboBox.setSelectedIndex(1);
		selectionComboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		selectionComboBox.setBounds(74, 554, 318, 35);
		selectionComboBox.addItemListener(getSelectionComboBoxItemListener());
		panelCalculator.add(selectionComboBox);
		
		ImageIcon refreshIcon = new ImageIcon(getClass().getResource("/refresh_icon.png"));
		Image image = refreshIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(42, 35,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		JButton refreshButton = new JButton("");
		refreshButton.setBounds(22, 554, 42, 35);
		refreshIcon = new ImageIcon(newimg); // transform it back
		refreshButton.setIcon(refreshIcon);
		refreshButton.addActionListener(getRefreshActionListener());
		panelCalculator.add(refreshButton);
		
		equalButton = new JButton("=");
		equalButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		equalButton.setBounds(517, 508, 110, 37);
		equalButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				equalButtonAction();
			}
		});
		panelCalculator.add(equalButton);
		
		resultTextPane = new JTextArea();
		resultTextPane.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 17));
		resultTextPane.setBounds(1, 1, 603, 287);
		panelCalculator.add(resultTextPane);
		
		JScrollPane scrollPane = new JScrollPane(resultTextPane, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(22, 21, 605, 288);
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setViewportView(resultTextPane);
		panelCalculator.add(scrollPane);
		
		inputTextField = new JTextArea();
		inputTextField.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 16));
		inputTextField.setBounds(22, 508, 483, 35);
		panelCalculator.add(inputTextField);
		Action sendInput = new AbstractAction()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e)
			{
				equalButtonAction();
			}
		};
		inputTextField.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "sendInput");
		inputTextField.getActionMap().put("sendInput", sendInput);
		inputTextField.requestFocus();
			
		logicToggle = new JToggleButton("Logic");
		logicToggle.setFont(new Font("Tahoma", Font.BOLD, 11));
		logicToggle.setBounds(417, 470, 88, 35);
		logicToggle.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ev)
			{		
				if(ev.getStateChange()==ItemEvent.SELECTED)
				{
					if(SetsToggle.isSelected())
					{
						SetsToggle.doClick();		
					}
					inputTextField.selectAll();
					inputTextField.requestFocus();
					previousSelectedIndex =selectionComboBox.getSelectedIndex();
					selectionComboBox.setSelectedIndex(5);
					panelMathButtons.setVisible(false);
					panelLogicButtons.setVisible(true);
					panelSetButtons.setVisible(false);
				} 
				else if(ev.getStateChange()==ItemEvent.DESELECTED)
				{
					inputTextField.selectAll();
					inputTextField.requestFocus();
					selectionComboBox.setSelectedIndex(previousSelectedIndex);
					panelMathButtons.setVisible(true);
					panelLogicButtons.setVisible(false);
					panelSetButtons.setVisible(false);
				}
			}
		});
		panelCalculator.add(logicToggle);
		
		SetsToggle = new JToggleButton("Set");
		SetsToggle.setFont(new Font("Tahoma", Font.BOLD, 11));
		SetsToggle.setBounds(325, 470, 88, 35);
		SetsToggle.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ev)
			{		
				Rectangle bounds = frame.getBounds();
				
				if(ev.getStateChange()==ItemEvent.SELECTED)
				{					
					if(logicToggle.isSelected())
					{
						logicToggle.doClick();		
					}
					
					frame.setBounds(bounds.x, bounds.y, 1230, 660);
					panelGraphics.getPanel().setVisible(true);
					inputTextField.selectAll();
					inputTextField.requestFocus();
					previousSelectedIndex =selectionComboBox.getSelectedIndex();
					selectionComboBox.setSelectedIndex(6);
					panelMathButtons.setVisible(false);
					panelLogicButtons.setVisible(false);
					panelSetButtons.setVisible(true);
				} 
				else if(ev.getStateChange()==ItemEvent.DESELECTED)
				{
					frame.setBounds(bounds.x, bounds.y, 665, 660);
					panelGraphics.getPanel().setVisible(false);
					inputTextField.selectAll();
					inputTextField.requestFocus();
					selectionComboBox.setSelectedIndex(previousSelectedIndex);
					panelMathButtons.setVisible(true);
					panelLogicButtons.setVisible(false);
					panelSetButtons.setVisible(false);
				}
			}
		});
		panelCalculator.add(SetsToggle);
		
		JButton backButton = new JButton("Back ⌫");
		backButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int caretPosition;
				
				caretPosition = inputTextField.getCaretPosition();
				
				if(caretPosition != 0)
				{
					inputTextField.setText(""
							+ inputTextField.getText().substring(0, inputTextField.getCaretPosition()-1)
							+ inputTextField.getText().substring(inputTextField.getCaretPosition()));
					inputTextField.setCaretPosition(caretPosition-1);		
				}
				inputTextField.requestFocus();
			}
		});
		backButton.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
		backButton.setBounds(22, 470, 89, 35);
		panelCalculator.add(backButton);
		
		circle2Rdbt = new JRadioButton("2 Circles");
		circle2Rdbt.setBounds(750, 450, 90, 25);
		circle2Rdbt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				PanelGraphics.setDraw3Circles(false);
				PanelSetElements.setPanelComponent();
				PanelGraphics.clearPaint();
			}
		});
		panelCalculator.add(circle2Rdbt);
		
		circle3Rdbt = new JRadioButton("3 Circles");
		circle3Rdbt.setBounds(660, 450, 90, 25);
		circle3Rdbt.setSelected(true);
		circle3Rdbt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				PanelGraphics.setDraw3Circles(true);
				PanelSetElements.setPanelComponent();
				PanelGraphics.clearPaint();
			}
		});
		panelCalculator.add(circle3Rdbt);
		
		ButtonGroup rdbtGroup= new ButtonGroup();
		rdbtGroup.add(circle2Rdbt);
		rdbtGroup.add(circle3Rdbt);		
	}
			
	private ItemListener getSelectionComboBoxItemListener()
	{
		return new ItemListener() 
		{
			public void itemStateChanged(ItemEvent event) 
			{
				int selectedIndex = selectionComboBox.getSelectedIndex();
				
				inputTextField.selectAll();
				inputTextField.requestFocus();
				
				switch(selectedIndex)
				{
				case 0:
					baseSelection = EBaseSelection.NONE;
					break;
				case 1:
					baseSelection = EBaseSelection.DECIMAL;
					
					break;
				case 2:
					baseSelection = EBaseSelection.BINARY;
					break;
				case 3:
					baseSelection = EBaseSelection.OCTAL;
					break;
				case 4:
					baseSelection = EBaseSelection.HEXA;
					break;
				case 5:
					baseSelection = EBaseSelection.LOGICAL;
					break;
				case 6:
					baseSelection = EBaseSelection.SET;
					break;
					
				}
			}
		};
	}
	
	private ActionListener getRefreshActionListener()
	{
		 return new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				if(PanelGraphics.isDraw3Circles())
				{
					circle3Rdbt.setSelected(true);
				}
				else
				{
					circle2Rdbt.setSelected(true);
				}
				PanelGraphics.clearPaint();
				inputTextField.setText("");
				resultTextPane.setText("");
				inputTextField.requestFocus();
			}
		};
	}
	
	private void equalButtonAction()
	{
		String input;
		String decimalResult;
		String binaryResult;
		String octalResult;
		String hexaResult;
		
		inputTextField.selectAll();
		inputTextField.requestFocus();
		
		switch(baseSelection)
		{
		case NONE:
			resultTextPane.setText("Error! Select the conversion type");
			break;
		case LOGICAL:
			input = inputTextField.getText();
			
			if(input.length() == 0)
			{
				resultTextPane.setText("No expression to evaluate");
			}
			else if(!logicalExpressionCalculator.isValidInput(input))
			{
				resultTextPane.setText("Invalid Input!");
			}
			else
			{
				resultTextPane.setText(logicalExpressionCalculator.evaluate(input));	
			}
			break;
		case SET:
			
			String parsedExpression;
			String result;
			
			input = inputTextField.getText();
			
			if(PanelGraphics.isResultFromDrawing())
			{
				result = setOperationCalculator.evaluateFromDrawing(PanelGraphics.parseSelectedAreaResult());
				resultTextPane.setText(result);
				PanelGraphics.setResultFromDrawing(false);
			}
			else if(PanelSetElements.areSetsDefined())
			{
				parsedExpression = getParsedSetExpression(input);
				
				if(!setOperationCalculator.isValidInput(parsedExpression))
				{
					resultTextPane.setText("Invalid Input!");
				}
				else
				{
					String regionToDraw = "";
					result = setOperationCalculator.evaluate(parsedExpression, PanelSetElements.getSetElements("u"));
					resultTextPane.setText(result);
					regionToDraw = setOperationCalculator.evaluate(input, null);
					panelGraphics.drawResult(regionToDraw);
				}						
			}
			else if(input.length() != 0)
			{
				if(!setOperationCalculator.isValidInput(input))
				{
					resultTextPane.setText("Invalid Input!");
				}
				else
				{
					result = setOperationCalculator.evaluate(input, PanelSetElements.getSetElements("u"));
					
					resultTextPane.setText(result);
					panelGraphics.drawResult(result);
				}			
			}
			else
			{
				resultTextPane.setText("No expression to evaluate");
			}
			
			if(PanelGraphics.isDraw3Circles())
			{
				circle3Rdbt.setSelected(true);
			}
			else
			{
				circle2Rdbt.setSelected(true);
			}
			
			break;
			
		default:
			input = inputTextField.getText();
			
			if(input.length() == 0)
			{
				resultTextPane.setText("No expression to evaluate");
			}
			else if(!expressionCalculator.isValidInput(input, baseSelection))
			{
				resultTextPane.setText("Invalid Input!");
			}
			else
			{
				decimalResult = expressionCalculator.evaluate(input, baseSelection);
				binaryResult = binaryConverter.fromDecimal(decimalResult, EBaseSelection.BINARY);
				octalResult = binaryConverter.fromDecimal(decimalResult, EBaseSelection.OCTAL);
				hexaResult = binaryConverter.fromDecimal(decimalResult, EBaseSelection.HEXA);
				resultTextPane.setText("Decimal: " + decimalResult + "\n\n");
				resultTextPane.setText(resultTextPane.getText() + "Binary: " + binaryResult + "\n\n");
				resultTextPane.setText(resultTextPane.getText() + "Octal: " + octalResult + "\n\n");
				resultTextPane.setText(resultTextPane.getText() + "Hexa: " + hexaResult + "\n\n");
			}
			break;
		}
	}
	
	private String getParsedSetExpression(String input)
	{
		String validSetNames = "UABC";
		boolean setNameFound = false;
		String parsedExpression = "";
		
		for(char c : input.toLowerCase().toCharArray())
		{
			for(char validName : validSetNames.toCharArray())
			{
				if(c == validName)
				{
					parsedExpression = parsedExpression.concat(PanelSetElements.getSetElements(Character.toString(c)));
						
					setNameFound = true;	
				}
			}
			
			if(!setNameFound)
			{
				parsedExpression = parsedExpression.concat(Character.toString(c));
			}
			
			setNameFound = false;
		}
		
		return parsedExpression;
	}
}
