package GUI;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelSetButtons 
{
	private static JFrame frame;
	private JPanel setButtonsPanel;
	private JTextArea inputTextField;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupPanel(Core core, JLayeredPane panelCalculator, JTextArea inputTextField)
	{
		frame = core.getFrame();
		this.inputTextField = inputTextField;
		
		setButtonsPanel = new JPanel();
		setButtonsPanel.setVisible(false);
		setButtonsPanel.setBounds(22, 314, 605, 152);
		setButtonsPanel.setLayout(null);
		panelCalculator.add(setButtonsPanel);	
		setupButtons();
	}
	
	public void setVisible(boolean visibility)
	{
		setButtonsPanel.setVisible(visibility);
	}
	
	private void setupButtons()
	{
		JButton elementButton = new JButton("∈");
		elementButton.setMargin(new Insets(0, 0, 0, 0));
		elementButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		elementButton.setBounds(145, 20, 45, 30);
		elementButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("∈", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(elementButton);
		
		JButton buttonEqual = new JButton("=");
		buttonEqual.setMargin(new Insets(0, 0, 0, 0));
		buttonEqual.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonEqual.setBounds(145, 52, 45, 30);
		buttonEqual.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("=", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonEqual);
		
		JButton buttonInclusion = new JButton("⊆");
		buttonInclusion.setMargin(new Insets(0, 0, 0, 0));
		buttonInclusion.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonInclusion.setBounds(190, 20, 45, 30);
		buttonInclusion.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("⊆", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonInclusion);
		
		JButton buttonStrictInclusion = new JButton("⊂");
		buttonStrictInclusion.setMargin(new Insets(0, 0, 0, 0));
		buttonStrictInclusion.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonStrictInclusion.setBounds(235, 20, 45, 30);
		buttonStrictInclusion.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("⊂", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonStrictInclusion);
		
		JButton buttonComplement = new JButton("'");
		buttonComplement.setMargin(new Insets(0, 0, 0, 0));
		buttonComplement.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonComplement.setBounds(145, 84, 45, 30);
		buttonComplement.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("'", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonComplement);
		
		JButton buttonIntersection = new JButton("∩");
		buttonIntersection.setMargin(new Insets(0, 0, 0, 0));
		buttonIntersection.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonIntersection.setBounds(235, 52, 45, 30);
		buttonIntersection.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("∩", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonIntersection);
		
		JButton buttonUnion = new JButton("∪");
		buttonUnion.setMargin(new Insets(0, 0, 0, 0));
		buttonUnion.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonUnion.setBounds(190, 52, 45, 30);
		buttonUnion.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("∪", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonUnion);
		
		JButton buttonDifference = new JButton("\\");
		buttonDifference.setMargin(new Insets(0, 0, 0, 0));
		buttonDifference.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonDifference.setBounds(190, 84, 45, 30);
		buttonDifference.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\\", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonDifference);
		
		JButton buttonSymetricalDiff = new JButton("∆");
		buttonSymetricalDiff.setMargin(new Insets(0, 0, 0, 0));
		buttonSymetricalDiff.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonSymetricalDiff.setBounds(235, 84, 45, 30);
		buttonSymetricalDiff.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("∆", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonSymetricalDiff);
		
		JButton buttonOpen = new JButton("(");
		buttonOpen.setMargin(new Insets(0, 0, 0, 0));
		buttonOpen.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonOpen.setBounds(0, 52, 45, 30);
		buttonOpen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("(", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonOpen);
		
		JButton buttonClose = new JButton(")");
		buttonClose.setMargin(new Insets(0, 0, 0, 0));
		buttonClose.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonClose.setBounds(90, 52, 45, 30);
		buttonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert(")", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonClose);
		
		JButton buttonA = new JButton("A");
		buttonA.setMargin(new Insets(0, 0, 0, 0));
		buttonA.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonA.setBounds(0, 20, 45, 30);
		buttonA.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("A", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonA);
		
		JButton buttonB = new JButton("B");
		buttonB.setMargin(new Insets(0, 0, 0, 0));
		buttonB.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonB.setBounds(45, 20, 45, 30);
		buttonB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("B", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonB);
		
		JButton buttonC = new JButton("C");
		buttonC.setMargin(new Insets(0, 0, 0, 0));
		buttonC.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonC.setBounds(90, 20, 45, 30);
		buttonC.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("C", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonC);
		
		JButton buttonU = new JButton("U");
		buttonU.setMargin(new Insets(0, 0, 0, 0));
		buttonU.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		buttonU.setBounds(45, 52, 45, 30);
		buttonU.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("U", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		setButtonsPanel.add(buttonU);
		
		JButton buttonHelp = new JButton("Help?");
		buttonHelp.setBounds(465, 22, 130, 30);
		buttonHelp.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				displaySetHelpFrame();
			}
		});
		setButtonsPanel.add(buttonHelp);
	}
	
	private void displaySetHelpFrame()
	{	
		JOptionPane.showMessageDialog(frame,
				getCenteredString("Egalite (=)", "_") +"\n"
			    + "Soit A et B. Les 2 emsemble doivent etre exactement egaux.\n"
			    + "Eg: {1;2;3} = {1;2;3}\n"
			    + "	      {1;2} ≠ {1;2;3}\n"
				+getCenteredString("Inclusion (⊆)", "_") +"\n"
				+ "Soit A et B. Tout les elements de A doivent etre dans B.\n"
			    + "Eg: {1;2;3} ⊆ {1;2;3}\n"
			    + "       {1;2;3} ⊈ {1;2;4}\n"
			    + getCenteredString("Inclusion Stricte (⊂)", "_") +"\n"
			    + "Soit A et B. Les 2 ensemble doivent etre egaux mais il doit y avoir au moin 1 element dans B qui ne soit pas dans A.\n"
				+ "Eg: {1;2} ⊂ {1;2;3}\n"
				+ "       {1;2;3} ⊄ {1;2;3}\n"
				+ getCenteredString("Complement (')", "_") +"\n"
				+ "Soit A et U(ensemble universel). La prime(') de A est tout les elements de U qui ne sont pas dans A\n"
				+ getCenteredString("Intersection (∩)", "_") +"\n"
				+ "Soit A et B. L'intersection est tout les elements dans A ET dans B\n"
				+ getCenteredString("Union (∪)", "_") +"\n"
				+ "Soit A et B. L'union est tout les elements dans A OU dans B\n"
				+ "Eg: Soient U={1;2;3;4;5;6;7;8;9;10}, A={1;2;3;4}, B={3;4;5;6}, C={1;3;5;7}\n"
				+ "Decrivez A' = {5;6;7;8;9;10}\n"
				+ "Decrivez A ∩ B = {3;4}\n"
				+ "Decrivez A ∪ B = {1;2;3;4;5;6}\n"
				+ getCenteredString("Difference de deux ensembles (\\)", "_") +"\n"
				+ "Soit A et B. La difference est tout les elements de A moin ceux de B\n"
				+ getCenteredString("Difference symetrique (∆)", "_") +"\n"
				+ "Soit A et B. La difference symetrique est tout les elements dans A ou dans B mais non les 2\n"
				+ "Eg: Soient U={1;2;3;4;5;6;7;8}, A={1;2;3;4}, B={3;4;5;6}\n"
				+ "Decrivez A \\ B = {1;2}\n"
				+ "Decrivez A ∆ B = {1;2;5;6}");
	}
	
	private String getCenteredString(String textToPrint, String spacerStyle)
	{
		final int FRAME_MAX_WIDTH = 90;
		int repeatCount;
		int leftSide;
		int rightSide;
		
		repeatCount = FRAME_MAX_WIDTH - textToPrint.length();
		leftSide = repeatCount/2;
		rightSide = repeatCount/2 + repeatCount%2;
		
		return spacerStyle.repeat(leftSide) + textToPrint + spacerStyle.repeat(rightSide);
	}
}
