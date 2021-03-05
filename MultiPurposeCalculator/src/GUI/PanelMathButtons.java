package GUI;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelMathButtons 
{
	private JPanel mathButtonsPanel;
	private JTextArea inputTextField;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupPanel(Core core, JLayeredPane panelCalculator, JTextArea inputTextField)
	{
		this.inputTextField = inputTextField;
		
		mathButtonsPanel = new JPanel();
		mathButtonsPanel.setBounds(22, 314, 605, 152);
		mathButtonsPanel.setLayout(null);
		panelCalculator.add(mathButtonsPanel);	
		
		setupButtons();
	}
	
	public void setVisible(boolean visibility)
	{
		mathButtonsPanel.setVisible(visibility);
	}
	
	private void setupButtons()
	{
		JButton sevenButton = new JButton("7");
		sevenButton.setMargin(new Insets(0, 0, 0, 0));
		sevenButton.setBounds(0, 20, 45, 30);
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
		eightButton.setBounds(45, 20, 45, 30);
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
		nineButton.setBounds(90, 20, 45, 30);
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
		plusButton.setBounds(160, 84, 50, 30);
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
		fourButton.setBounds(0, 52, 45, 30);
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
		fiveButton.setBounds(45, 52, 45, 30);
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
		sixButton.setBounds(90, 52, 45, 30);
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
		multiplyButton.setBounds(213, 84, 50, 30);
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
		oneButton.setBounds(0, 84, 45, 30);
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
		twoButton.setBounds(45, 84, 45, 30);
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
		threeButton.setBounds(90, 84, 45, 30);
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
		minusButton.setBounds(160, 114, 50, 30);
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
		divideButton.setBounds(213, 114, 50, 30);
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
		openParenButton.setBounds(0, 114, 45, 30);
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
		zeroButton.setBounds(45, 114, 45, 30);
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
		closeParenButton.setBounds(90, 114, 45, 30);
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
		aButton.setBounds(145, 20, 45, 30);
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
		dButton.setBounds(145, 52, 45, 30);
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
		cButton.setBounds(235, 20, 45, 30);
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
		bButton.setBounds(190, 20, 45, 30);
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
		eButton.setBounds(190, 52, 45, 30);
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
		fButton.setBounds(235, 52, 45, 30);
		fButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("F", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		mathButtonsPanel.add(fButton);
	}

}
