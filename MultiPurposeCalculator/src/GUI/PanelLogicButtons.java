package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
		GridBagLayout gbl_logicButtonsPanel = new GridBagLayout();
		gbl_logicButtonsPanel.columnWidths = new int[]{0, 0, 0, 19, 0, 0, 0, 29, 0, 0, 0, 0};
		gbl_logicButtonsPanel.rowHeights = new int[] {30, 9, 30, 30, 30};
		gbl_logicButtonsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_logicButtonsPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		logicButtonsPanel.setLayout(gbl_logicButtonsPanel);
		panelCalculator.add(logicButtonsPanel);
		
		setupButtons();
	}
	
	public void setVisible(boolean visibility)
	{
		logicButtonsPanel.setVisible(visibility);
	}
	
	private void setupButtons()
	{
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
		logicButtonsPanel.add(negationButton, gbc_negationButton_1);
		
		JButton pButton = new JButton("p");
		pButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_pButton = new GridBagConstraints();
		gbc_pButton.insets = new Insets(0, 0, 5, 5);
		gbc_pButton.gridx = 0;
		gbc_pButton.gridy = 2;
		pButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("p", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(pButton, gbc_pButton);
		
		JButton qButton = new JButton("q");
		qButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_qButton = new GridBagConstraints();
		gbc_qButton.insets = new Insets(0, 0, 5, 5);
		gbc_qButton.gridx = 1;
		gbc_qButton.gridy = 2;
		qButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("q", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(qButton, gbc_qButton);
		
		JButton rButton = new JButton("r");
		rButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_rButton = new GridBagConstraints();
		gbc_rButton.insets = new Insets(0, 0, 5, 5);
		gbc_rButton.gridx = 2;
		gbc_rButton.gridy = 2;
		rButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("r", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(rButton, gbc_rButton);
		
		JButton andButton = new JButton("∧");
		andButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_AndButton = new GridBagConstraints();	
		gbc_AndButton = new GridBagConstraints();
		gbc_AndButton.insets = new Insets(0, 0, 5, 5);
		gbc_AndButton.gridx = 4;
		gbc_AndButton.gridy = 2;
		andButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2227", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(andButton, gbc_AndButton);
		
		
		JButton andOrButton = new JButton("⊕");
		andOrButton.setPreferredSize(new Dimension(50, 30));
		andOrButton.setFont(new Font("DejaVu Sans Condensed", Font.PLAIN, 20));
		GridBagConstraints gbc_andOrButton_1 = new GridBagConstraints();
		gbc_andOrButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_andOrButton_1.gridx = 5;
		gbc_andOrButton_1.gridy = 2;
		andOrButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				inputTextField.insert("\u2295", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(andOrButton, gbc_andOrButton_1);
		
		JButton ifOnlyIfButton = new JButton("↔");
		ifOnlyIfButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 18));
		GridBagConstraints gbc_ifOnlyIfButton_1 = new GridBagConstraints();
		gbc_ifOnlyIfButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_ifOnlyIfButton_1.gridx = 6;
		gbc_ifOnlyIfButton_1.gridy = 2;
		ifOnlyIfButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				inputTextField.insert("\u2194", inputTextField.getCaretPosition());
				inputTextField.requestFocusInWindow();
			}
		});
		logicButtonsPanel.add(ifOnlyIfButton, gbc_ifOnlyIfButton_1);
		
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
		logicButtonsPanel.add(ifThenButton, gbc_ifThenButton);
				
		JButton helpButton = new JButton("Help?");
		GridBagConstraints gbc_helpButton = new GridBagConstraints();
		gbc_helpButton.gridx = 10;
		gbc_helpButton.gridy = 3;
		helpButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent) 
			{ 
				displayLogicHelpFrame();
			}
		});
		logicButtonsPanel.add(helpButton, gbc_helpButton);
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
