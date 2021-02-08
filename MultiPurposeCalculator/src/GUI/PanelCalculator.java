package GUI;

import utilities.BinaryConverter;
import utilities.ExpressionCalculator;
import utilities.LogicalExpressionCalculator;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class PanelCalculator 
{
	private JLayeredPane panelCalculator;
	private JPanel logicButtonsPanel;
	private JPanel container;
	private JTextArea inputTextField;
	private JTextArea resultTextPane;
	private JButton equalButton;
	@SuppressWarnings("rawtypes")
	private JComboBox selectionComboBox;
	
	private BinaryConverter binaryConverter = new BinaryConverter();
	private ExpressionCalculator expressionCalculator = new ExpressionCalculator();
	private LogicalExpressionCalculator logicalExpressionCalculator = new LogicalExpressionCalculator();
	
	private int previousSelectedIndex = 0;
	private EBaseSelection baseSelection = EBaseSelection.DECIMAL;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupPanel(Core core)
	{
		
		container = PanelContainer.getPanel(EPanelName.CONTAINER);	
		panelCalculator = new JLayeredPane();
		container.add(panelCalculator);
		panelCalculator.setName("CALCULATOR");
		
		logicButtonsPanel = new JPanel();
		logicButtonsPanel.setVisible(false);
		logicButtonsPanel.setBounds(22, 314, 605, 152);
		panelCalculator.add(logicButtonsPanel);
		GridBagLayout gbl_logicButtonsPanel = new GridBagLayout();
		gbl_logicButtonsPanel.columnWidths = new int[]{0, 0, 0, 19, 0, 0, 0, 29, 0, 0, 0, 0};
		gbl_logicButtonsPanel.rowHeights = new int[] {30, 30, 30, 30, 30};
		gbl_logicButtonsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_logicButtonsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		logicButtonsPanel.setLayout(gbl_logicButtonsPanel);
		
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
		
		Object[] comboBoxSelection = {"(Select base)", "Decimal", "Binary", "Octal", "Hexadecimal", "Logical"};
		selectionComboBox = new JComboBox<Object>(comboBoxSelection);
		selectionComboBox.setSelectedIndex(1);
		selectionComboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		selectionComboBox.setBounds(74, 554, 318, 35);
		selectionComboBox.addItemListener(getSelectionComboBoxItemListener());
		panelCalculator.add(selectionComboBox);
		GridBagConstraints gbc_AndButton = new GridBagConstraints();
		
		JButton negationButton = new JButton("¬");
		negationButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_negationButton_1 = new GridBagConstraints();
		gbc_negationButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_negationButton_1.gridx = 0;
		gbc_negationButton_1.gridy = 3;
		negationButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u00AC", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		
		JButton pButton = new JButton("P");
		pButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_pButton = new GridBagConstraints();
		gbc_pButton.insets = new Insets(0, 0, 5, 5);
		gbc_pButton.gridx = 0;
		gbc_pButton.gridy = 2;
		logicButtonsPanel.add(pButton, gbc_pButton);
		pButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("P", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		
		JButton qButton = new JButton("Q");
		qButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_qButton = new GridBagConstraints();
		gbc_qButton.insets = new Insets(0, 0, 5, 5);
		gbc_qButton.gridx = 1;
		gbc_qButton.gridy = 2;
		logicButtonsPanel.add(qButton, gbc_qButton);
		qButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("Q", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		
		JButton rButton = new JButton("R");
		rButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_rButton = new GridBagConstraints();
		gbc_rButton.insets = new Insets(0, 0, 5, 5);
		gbc_rButton.gridx = 2;
		gbc_rButton.gridy = 2;
		logicButtonsPanel.add(rButton, gbc_rButton);
		rButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("R", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		
		JButton andButton = new JButton("∧");
		andButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		gbc_AndButton = new GridBagConstraints();
		gbc_AndButton.insets = new Insets(0, 0, 5, 5);
		gbc_AndButton.gridx = 4;
		gbc_AndButton.gridy = 2;
		logicButtonsPanel.add(andButton, gbc_AndButton);
		andButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2227", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		
		
		JButton andOrButton = new JButton("⊕");
		andOrButton.setPreferredSize(new Dimension(50, 30));
		andOrButton.setFont(new Font("DejaVu Sans Condensed", Font.PLAIN, 20));
		GridBagConstraints gbc_andOrButton_1 = new GridBagConstraints();
		gbc_andOrButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_andOrButton_1.gridx = 5;
		gbc_andOrButton_1.gridy = 2;
		logicButtonsPanel.add(andOrButton, gbc_andOrButton_1);
		andOrButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				inputTextField.insert("\u2295", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		
		JButton ifOnlyIfButton = new JButton("↔");
		ifOnlyIfButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_ifOnlyIfButton_1 = new GridBagConstraints();
		gbc_ifOnlyIfButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_ifOnlyIfButton_1.gridx = 6;
		gbc_ifOnlyIfButton_1.gridy = 2;
		logicButtonsPanel.add(ifOnlyIfButton, gbc_ifOnlyIfButton_1);
		ifOnlyIfButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2194", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(negationButton, gbc_negationButton_1);
		
		JButton openButton = new JButton("(");
		openButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		openButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("(", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		GridBagConstraints gbc_openButton = new GridBagConstraints();
		gbc_openButton.insets = new Insets(0, 0, 0, 5);
		gbc_openButton.gridx = 1;
		gbc_openButton.gridy = 3;
		logicButtonsPanel.add(openButton, gbc_openButton);
		
		JButton closeButton = new JButton(")");
		closeButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		closeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert(")", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.insets = new Insets(0, 0, 0, 5);
		gbc_closeButton.gridx = 2;
		gbc_closeButton.gridy = 3;
		logicButtonsPanel.add(closeButton, gbc_closeButton);
		
		JButton orButton = new JButton("∨");
		orButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		orButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2228", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		GridBagConstraints gbc_orButton_1 = new GridBagConstraints();
		gbc_orButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_orButton_1.gridx = 4;
		gbc_orButton_1.gridy = 3;
		logicButtonsPanel.add(orButton, gbc_orButton_1);
		
		JButton ifThenButton = new JButton("→");
		ifThenButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_ifThenButton = new GridBagConstraints();
		gbc_ifThenButton.insets = new Insets(0, 0, 0, 5);
		gbc_ifThenButton.gridx = 6;
		gbc_ifThenButton.gridy = 3;
		ifThenButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2192", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		
		JButton equivalenceButton = new JButton("≡");
		equivalenceButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_equivalenceButton = new GridBagConstraints();
		gbc_equivalenceButton.insets = new Insets(0, 0, 0, 5);
		gbc_equivalenceButton.gridx = 5;
		gbc_equivalenceButton.gridy = 3;
		equivalenceButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2261", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		
				logicButtonsPanel.add(equivalenceButton, gbc_equivalenceButton);
		logicButtonsPanel.add(ifThenButton, gbc_ifThenButton);
		
		
		ImageIcon refreshIcon = new ImageIcon(getClass().getResource("/refresh_icon.png"));
		Image image = refreshIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(42, 35,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		JButton refreshButton = new JButton("");
		refreshButton.setBounds(22, 554, 42, 35);
		refreshIcon = new ImageIcon(newimg); // transform it back
		refreshButton.setIcon(refreshIcon);
		refreshButton.addActionListener(getRefreshActionListener());
		panelCalculator.add(refreshButton);
		
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
		
		JPanel mathButtonsPanel = new JPanel();
		mathButtonsPanel.setBounds(22, 314, 605, 152);
		panelCalculator.add(mathButtonsPanel);
		mathButtonsPanel.setLayout(null);
		
		JButton sevenButton = new JButton("7");
		sevenButton.setMargin(new Insets(0, 0, 0, 0));
		sevenButton.setBounds(0, 19, 43, 31);
		sevenButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		sevenButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("7", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(sevenButton);
		
		JButton eightButton = new JButton("8");
		eightButton.setMargin(new Insets(0, 0, 0, 0));
		eightButton.setBounds(45, 19, 43, 31);
		eightButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		eightButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("8", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(eightButton);
		
		JButton nineButton = new JButton("9");
		nineButton.setMargin(new Insets(0, 0, 0, 0));
		nineButton.setBounds(90, 19, 43, 31);
		nineButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		nineButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("9", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(nineButton);
		
		JButton plusButton = new JButton("+");
		plusButton.setMargin(new Insets(0, 0, 0, 0));
		plusButton.setBounds(160, 87, 50, 31);
		plusButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		plusButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("+", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(plusButton);
		
		JButton fourButton = new JButton("4");
		fourButton.setMargin(new Insets(0, 0, 0, 0));
		fourButton.setBounds(0, 53, 43, 31);
		fourButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 16));
		fourButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("4", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(fourButton);
		
		JButton fiveButton = new JButton("5");
		fiveButton.setMargin(new Insets(0, 0, 0, 0));
		fiveButton.setBounds(45, 53, 43, 31);
		fiveButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		fiveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("5", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(fiveButton);
		
		JButton sixButton = new JButton("6");
		sixButton.setMargin(new Insets(0, 0, 0, 0));
		sixButton.setBounds(90, 53, 43, 31);
		sixButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		sixButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("6", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(sixButton);
		
		JButton multiplyButton = new JButton("x");
		multiplyButton.setMargin(new Insets(0, 0, 0, 0));
		multiplyButton.setBounds(213, 87, 50, 31);
		multiplyButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		multiplyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("*", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(multiplyButton);
		
		JButton oneButton = new JButton("1");
		oneButton.setMargin(new Insets(0, 0, 0, 0));
		oneButton.setBounds(0, 87, 43, 31);
		oneButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		oneButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("1", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(oneButton);
		
		JButton twoButton = new JButton("2");
		twoButton.setMargin(new Insets(0, 0, 0, 0));
		twoButton.setBounds(45, 87, 43, 31);
		twoButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		twoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("2", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(twoButton);
		
		JButton threeButton = new JButton("3");
		threeButton.setMargin(new Insets(0, 0, 0, 0));
		threeButton.setBounds(90, 87, 43, 31);
		threeButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		threeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("3", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(threeButton);
		
		JButton minusButton = new JButton("-");
		minusButton.setMargin(new Insets(0, 0, 0, 0));
		minusButton.setBounds(160, 121, 50, 31);
		minusButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		minusButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("-", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(minusButton);
		
		JButton divideButton = new JButton("÷");
		divideButton.setMargin(new Insets(0, 0, 0, 0));
		divideButton.setBounds(213, 121, 50, 31);
		divideButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		divideButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("/", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(divideButton);
		
		JButton openParenButton = new JButton("(");
		openParenButton.setMargin(new Insets(0, 0, 0, 0));
		openParenButton.setBounds(0, 121, 43, 31);
		openParenButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		openParenButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("(", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(openParenButton);
		
		JButton zeroButton = new JButton("0");
		zeroButton.setMargin(new Insets(0, 0, 0, 0));
		zeroButton.setBounds(45, 121, 43, 31);
		zeroButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		zeroButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("0", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(zeroButton);
		
		JButton closeParenButton = new JButton(")");
		closeParenButton.setMargin(new Insets(0, 0, 0, 0));
		closeParenButton.setBounds(90, 121, 43, 31);
		closeParenButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		closeParenButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert(")", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(closeParenButton);
		
		JButton aButton = new JButton("A");
		aButton.setMargin(new Insets(0, 0, 0, 0));
		aButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		aButton.setBounds(143, 19, 43, 31);
		aButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("A", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(aButton);
		
		JButton dButton = new JButton("D");
		dButton.setMargin(new Insets(0, 0, 0, 0));
		dButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		dButton.setBounds(143, 53, 43, 31);
		dButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("D", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(dButton);
		
		JButton cButton = new JButton("C");
		cButton.setMargin(new Insets(0, 0, 0, 0));
		cButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		cButton.setBounds(235, 19, 43, 31);
		cButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("C", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(cButton);
		
		JButton bButton = new JButton("B");
		bButton.setMargin(new Insets(0, 0, 0, 0));
		bButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		bButton.setBounds(189, 19, 43, 31);
		bButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("B", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(bButton);
		
		JButton eButton = new JButton("E");
		eButton.setMargin(new Insets(0, 0, 0, 0));
		eButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		eButton.setBounds(189, 53, 43, 31);
		eButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("E", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(eButton);
		
		JButton fButton = new JButton("F");
		fButton.setMargin(new Insets(0, 0, 0, 0));
		fButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		fButton.setBounds(235, 53, 43, 31);
		fButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("F", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(fButton);
			
		JToggleButton logicalButton = new JToggleButton("Logic");
		logicalButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		logicalButton.setBounds(22, 470, 88, 35);
		logicalButton.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ev)
			{		
				if(ev.getStateChange()==ItemEvent.SELECTED)
				{
					inputTextField.selectAll();
					inputTextField.requestFocus();
					previousSelectedIndex =selectionComboBox.getSelectedIndex();
					selectionComboBox.setSelectedIndex(5);
					mathButtonsPanel.setVisible(false);
					logicButtonsPanel.setVisible(true);
				} 
				else if(ev.getStateChange()==ItemEvent.DESELECTED)
				{
					inputTextField.setText("");
					inputTextField.requestFocus();
					selectionComboBox.setSelectedIndex(previousSelectedIndex);
					mathButtonsPanel.setVisible(true);
					logicButtonsPanel.setVisible(false);
				}
			}
		});
		panelCalculator.add(logicalButton);
	}
	
	public JLayeredPane getPanel() {return panelCalculator;}
	
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
			resultTextPane.setText(logicalExpressionCalculator.evaluate(input));
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
}
