package hadjiantoni_andreas_3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class SuspectPageFrame extends JFrame {
	
	private Border lineBorderColor = new LineBorder(Color.gray, 1); //Border's COLOR and THIKNESS
	
	private JPanel mainPanel,suspectInfoPanel,partnersPanel, suggestedPartnersPanel, smsPanel,countryPanel;
	
	private JTextField suspectNameField, suspectCodedNameField, smsNumberField;
	private JButton findSMSButton, backButton;
	private JTextArea partnersField , suggestedPartnersField, countryField , smsField , phoneField;
	private JLabel partnersLabel;
	private JLabel suggestedPartnersLabel;
	
	private Registry registry;
	private int i;
	
	public SuspectPageFrame (int i, String name, Registry registry) {
		
		this.registry = registry;
		this.i = i;
		
		//Suspect's Panel-----------------------------------------
		suspectInfoPanel = new JPanel();
		
		suspectNameField = new JTextField(name);
		suspectNameField.setEditable(false);
		suspectCodedNameField = new JTextField(registry.getSuspects().get(i).getCodeName());
		suspectCodedNameField.setEditable(false);
		
		phoneField = new JTextArea();
		phoneField.setEditable(false);
		
		for(int j=0; j<registry.getSuspects().get(i).getNumbers().size(); j++) {
			
			phoneField.append(registry.getSuspects().get(i).sendNumbers(j));
			
			if(j<registry.getSuspects().get(i).getNumbers().size() -1) //To Correct the lines
				phoneField.append("\n");
		}
		suspectInfoPanel.add(suspectNameField);
		suspectInfoPanel.add(suspectCodedNameField);
		suspectInfoPanel.add(phoneField);
		suspectInfoPanel.setBorder(lineBorderColor);
		//------------------------------------------------------
		
		//SMSpanel------------------------------------
		smsPanel = new JPanel();
		
		smsNumberField = new JTextField(12);
		smsField = new JTextArea();
		smsField.setPreferredSize(new Dimension(200,100));
		smsField.setEditable(false);
		findSMSButton = new JButton("Find SMS");
		
		smsPanel.add(smsNumberField);
		smsPanel.add(smsField);
		smsPanel.add(findSMSButton);
		smsPanel.setBorder(lineBorderColor);
		
		FindSMSButtonListener findSMSButtonListener = new FindSMSButtonListener();
		findSMSButton.addActionListener(findSMSButtonListener);
		//------------------------------------
		
		//Partners Panel
		partnersPanel = new JPanel();
		partnersLabel = new JLabel("Partners");
		partnersField = new JTextArea();
		partnersField.setPreferredSize(new Dimension(200,200));
		partnersField.setEditable(false);
		
		ArrayList<Suspect> possiblePartners = registry.getSuspects().get(i).getPossiblePartners();
		
		for(int j=0; j< possiblePartners.size();j++)
			partnersField.append(possiblePartners.get(j).getName() + ", " + possiblePartners.get(j).getCodeName() + "\n");
		
		partnersPanel.add(partnersLabel);
		partnersPanel.add(partnersField);
		partnersPanel.setBorder(lineBorderColor);
		//------------------------------------------
		
		//suggested Partners Panel
		suggestedPartnersPanel = new JPanel ();
		
		suggestedPartnersField = new JTextArea();
		suggestedPartnersField.setEditable(false);
		suggestedPartnersField.setPreferredSize(new Dimension(200,100));
		suggestedPartnersLabel = new JLabel("Suggested Partners ------>");
		
		HashSet<Suspect> partners = registry.getSuspects().get(i).getSuggestedPossiblePartners(registry.getSuspects().get(i));
		
		for (Suspect aSuspect : partners)
			suggestedPartnersField.append(aSuspect.toString() + "\n");
		
		suggestedPartnersPanel.add(suggestedPartnersLabel);
		suggestedPartnersPanel.add(suggestedPartnersField);
		suggestedPartnersPanel.setBorder(lineBorderColor);
		//------------------------------------------
		
		//Country Panel
		countryPanel = new JPanel();
		
		countryField = new JTextArea();
		countryField.setEditable(false);
		countryField.setPreferredSize(new Dimension(300,100));
		
		countryField.append("Suspects coming from " + registry.getSuspects().get(i).getCountryOfOrigin() + "\n");
		for(int j = 0; j<registry.getSuspects().size();j++)
			if(registry.getSuspects().get(j).getCountryOfOrigin().equals(registry.getSuspects().get(i).getCountryOfOrigin()))
				countryField.append(registry.getSuspects().get(j).getName() + "\n");
		
		
		countryPanel.add(countryField);
		countryPanel.setBorder(lineBorderColor);
		//------------------------------------------
		
		//Back Button
		backButton = new JButton("Return to Search Screen");
		
		BackButtonListener backButtonListener = new BackButtonListener();
		backButton.addActionListener(backButtonListener);
		//----------------------------------------------
		
		//Main Panel *add other panels to the main one
		mainPanel = new JPanel();
		mainPanel.add(suspectInfoPanel);
		mainPanel.add(smsPanel);
		mainPanel.add(partnersPanel);
		mainPanel.add(suggestedPartnersPanel);
		mainPanel.add(countryPanel);
		mainPanel.add(backButton);
		
		this.setContentPane(mainPanel);
		
		this.setSize(500,750);
		this.setVisible(true);
		this.setTitle("Suspect Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class FindSMSButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			String number = smsNumberField.getText();
			ArrayList<SMS> messages = new ArrayList<SMS>(); 

			for(int j=0; j<registry.getSuspects().get(i).getNumbers().size(); j++)
				messages=registry.getMessagesBetween(registry.getSuspects().get(i).getNumbers().get(j).toString(), number);
			
			for(int j=0; j<messages.size(); j++)
				smsField.append(messages.get(j).getMsg()+"\n");
		}
	}
	
	class BackButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			new FindSuspectFrame(registry);
			SuspectPageFrame.this.dispose();
		}
	}
}