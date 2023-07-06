import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindSuspectFrame extends JFrame{
	
	//Step 1
	private JPanel panel = new JPanel ();
	
	//Step 2
	private JTextField suspectNameField = new JTextField("Please enter a suspect's name");
	private JButton findSuspectButton = new JButton ("Find");
	private JButton visualizeNetworkButton = new JButton ("VisualizeNetwork");
	
	private Registry registry;
	
	public FindSuspectFrame (Registry registry) {
		
		this.registry = registry;
		
		//Step 3
		panel.add(suspectNameField);
		panel.add(findSuspectButton);
		panel.add(visualizeNetworkButton);
		
		//Step 4
		this.setContentPane(panel);
		
		//Step 3 Create Object Listener
		SearchButtonListener SearchButtonListener = new SearchButtonListener();
		VisualizeNetworkButtonListener VisualizeNetworkButtonListener = new VisualizeNetworkButtonListener();
		
		//Step 4 Connect Listener with...
		findSuspectButton.addActionListener(SearchButtonListener);
		visualizeNetworkButton.addActionListener(VisualizeNetworkButtonListener);
		
		//Frame
		this.setSize(300,120);
		this.setVisible(true);
		this.setTitle("Find Suspect");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Step 1 Create Listener Class
	class SearchButtonListener implements ActionListener {
			
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
	
	class VisualizeNetworkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			FindSuspectFrame.this.dispose();
			new VisualizeNetworkFrame(registry);
		}
	}
}
