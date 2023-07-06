import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class VisualizeNetworkFrame extends JFrame{
	
	private Border diameterBoarderColor = new LineBorder(Color.gray, 1); //Border's COLOR and THIKNESS
	private Registry registry;
	private JTextField diameterField = new JTextField();
			
	public VisualizeNetworkFrame(Registry registry) {
		
		this.registry = registry;
		
		Graph suspectsGraph = new SparseGraph();
		
		for(Suspect suspect : registry.getSuspects())
			suspectsGraph.addVertex(suspect.getCodeName());
		
		int edgeCounter = 0;
		for(Suspect firstSuspectVertex : registry.getSuspects()) 
			for(Suspect secondsSuspectVertex : firstSuspectVertex.getPossiblePartners()){	
				suspectsGraph.addEdge(edgeCounter,firstSuspectVertex.getCodeName(), secondsSuspectVertex.getCodeName());
				edgeCounter++;
			}

		VisualizationViewer suspectsVisualizedFrame = new VisualizationViewer(new CircleLayout(suspectsGraph), new Dimension(300,200));
		suspectsVisualizedFrame.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		
		double diameter = DistanceStatistics.diameter(suspectsGraph);
		diameterField.setText("Diameter = " + diameter);
		diameterField.setEditable(false);
		diameterField.setBorder(diameterBoarderColor);
		
		//set the diameter field in the end of the page
		this.setLayout(new BorderLayout ());
		this.add(diameterField, BorderLayout.PAGE_END);
		
		this.setTitle("Suspects Network");
		this.getContentPane().add(suspectsVisualizedFrame);
		this.setSize(350,350);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}