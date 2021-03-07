package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelLogicButtons 
{
	private static JFrame frame;
	private JPanel logicButtonsPanel;
	private JTextArea inputTextField;
	private JButton negationButton;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupPanel(Core core, JLayeredPane panelCalculator, JTextArea inputTextField)
	{
		frame = core.getFrame();
		this.inputTextField = inputTextField;
		
		logicButtonsPanel = new JPanel();
		logicButtonsPanel.setVisible(false);
		logicButtonsPanel.setBounds(22, 314, 605, 152);
		logicButtonsPanel.setLayout(null);
		panelCalculator.add(logicButtonsPanel);
		
		setupButtons();
	}
	
	public void setVisible(boolean visibility)
	{
		logicButtonsPanel.setVisible(visibility);
	}
	
	private void setupButtons()
	{
		negationButton = new JButton("¬");
		negationButton.setBounds(0, 50, 50, 30);
		negationButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		negationButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u00AC", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(negationButton);
		
		JButton pButton = new JButton("p");
		pButton.setBounds(0, 20, 50, 30);
		pButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		pButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("p", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(pButton);
		
		JButton qButton = new JButton("q");
		qButton.setBounds(50, 20, 50, 30);
		qButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		qButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("q", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(qButton);
		
		JButton rButton = new JButton("r");
		rButton.setBounds(100, 20, 50, 30);
		rButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		rButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("r", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(rButton);
		
		JButton andButton = new JButton("∧");
		andButton.setBounds(160, 20, 50, 30);
		andButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		andButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2227", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(andButton);
		
		
		JButton andOrButton = new JButton("⊕");
		andOrButton.setBounds(210, 20, 50, 30);
		andOrButton.setPreferredSize(new Dimension(50, 30));
		andOrButton.setFont(new Font("DejaVu Sans Condensed", Font.PLAIN, 20));
		andOrButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				inputTextField.insert("\u2295", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(andOrButton);
		
		JButton ifOnlyIfButton = new JButton("↔");
		ifOnlyIfButton.setBounds(260, 20, 50, 30);
		ifOnlyIfButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		ifOnlyIfButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2194", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(ifOnlyIfButton);
		
		JButton openButton = new JButton("(");
		openButton.setBounds(50, 50, 50, 30);
		openButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		openButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("(", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(openButton);
		
		JButton closeButton = new JButton(")");
		closeButton.setBounds(100, 50, 50, 30);
		closeButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		closeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert(")", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(closeButton);
		
		JButton orButton = new JButton("∨");
		orButton.setBounds(160, 50, 50, 30);
		orButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		orButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2228", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(orButton);
				
		JButton equivalenceButton = new JButton("≡");
		equivalenceButton.setBounds(210, 50, 50, 30);
		equivalenceButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		equivalenceButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2261", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(equivalenceButton);
				
		JButton ifThenButton = new JButton("→");
		ifThenButton.setBounds(260, 50, 50, 30);
		ifThenButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		ifThenButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2192", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(ifThenButton);
				
		JButton helpButton = new JButton("Help?");
		helpButton.setBounds(475, 22, 130, 30);
		helpButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				displayLogicHelpFrame();
			}
		});
		logicButtonsPanel.add(helpButton);
	}
	
	private static void displayLogicHelpFrame()
	{
		JOptionPane.showMessageDialog(frame,
				  "___________________________________Equivalence logique (≡)____________________________________________\n"
			    + "1.Determiner si la biconditionnelle [p ∨ (q ∧ r)] ↔ [(p ∨ q) ∧ (p ∨ r)] est une equivalence logique\n"
				+ "Solution: Ecrire (p∨(q∧r))≡((p∨q)∧(p∨r)) dans la calculatrice.\n"
				+ "Resultat: 2 tables de verite. Pour etre une equivalence logique, les valeurs de verite\n"
				+ "          des 2 tables doivent etre identiques.\n"
				+ "Reponse:  Les valeurs de verite des deux expressions etant identiques, on a une equivalence logique.\n"
				+ "\n"
				+ "2.Déterminer si l'énoncé p ↔ ( q → r) et [(p ⊕ q) ∧ ¬r] ∨ ( p ∧ r) est logiquement équivalent. Justifier\n"
				+ "Solution: Ecrire p↔(q→r)↔((p⊕q)∧¬r)∨(p∧r) dans la calculatrice.\n"
				+ "Resultat: 1 table de verite. Pour etre une equivalence logique, les valeurs de verite doivent\n"
				+ "			 tous etre vrais (tautologie).\n"
				+ "Reponse:  Puisque p ↔ (q → r) ↔ [(p ⊕ q) ∧ ¬r] ∨ (p ∧ r) est une tautologie,\n"
				+ "			 on a que p ↔ (q → r) ≡ [(p ⊕ q) ∧ ¬r] ∨ (p ∧ r)"
				+ "\n"
				+ "\n"
				+ "___________________________________Implications logiques (⇒)___________________________________________\n"
				+ "1.Determiner si la conditionnelle [p ∧ (p → q)] → q est une implication logique.\n"
				+ "Solution: Ecrire (p∧(p→q))→q dans la calculatrice.\n"
				+ "Resultat: 1 table de verite. Pour etre une equivalence logique, les valeurs de verite doivent\n"
				+ "			 tous etre vrais (tautologie).\n"
				+ "Reponse:  Puisque [p ∧ (p → q)] → q est une tautologie, on a que [p ∧ (p → q)] ⇒ q .");
	}
}
