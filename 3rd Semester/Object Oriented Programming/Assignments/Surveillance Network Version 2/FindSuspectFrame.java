package hadjiantoni_andreas_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class FindSuspectFrame extends JFrame{
	
	//Step 1
	private JPanel panel = new JPanel ();
	//------
	
	//Step 2
	private JTextField suspectNameField = new JTextField("Please enter a suspect's name");
	private JButton findSuspectButton = new JButton ("Find");
	//------
	
	private Registry registry;
	
	public FindSuspectFrame (Registry registry) {
		
		this.registry = registry;
		
		
		//Step 3
		panel.add(suspectNameField);
		panel.add(findSuspectButton);
		//------	
		
		//Step 4
		this.setContentPane(panel);
		//------
		
		//Step 3 Create Object Listener
		ButtonListener listener = new ButtonListener();
		
		//Step 4 Connect Listener with...
		findSuspectButton.addActionListener(listener);
		
		//Frame
		this.setSize(400,100);
		this.setVisible(true);
		this.setTitle("Find Suspect");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//------
	}
	
	//Step 1 Create Listener Class
	class ButtonListener implements ActionListener {
			
		//Step 2 Write Code (This Class is Called By Java)
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			String name = suspectNameField.getText();
			boolean found = false;
			
			for(int i=0; i<registry.getSuspects().size();i++) 
				if(registry.getSuspects().get(i).getName().equals(name)) {
					
					found = true;
					FindSuspectFrame.this.dispose();
					new SuspectPageFrame(i,name,registry);
				}
			
			if(!found)
				JOptionPane.showMessageDialog(null,"Suspect " + name + " Not Found."); 
				
		}
	}

}
