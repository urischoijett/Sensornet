import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class userIO extends JFrame  {
	
	private Controller ctrl;
	private JPanel contentPane;

	
	//constructor, sets up main window
	public userIO(Controller control){
		ctrl = control;
		setResizable(false);
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//gridbag start
		contentPane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setContentPane(contentPane);
		
		//Numbertext (0,0)
		JLabel numText = new JLabel("Number of Sensors: ");
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 1;
		c.anchor  = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		contentPane.add(numText, c);
		
		//numberfield(1,0)
		final JTextField numField = new JTextField(10);
		c.gridx = 1;
		c.gridy = 0;
		c.fill = 2;
		contentPane.add(numField, c);
		
		//radiustext(2,0)
		JLabel radText = new JLabel("Radius of Sensors: ");
		c.gridx = 2;
		c.gridy = 0;
		contentPane.add(radText, c);
		
		//radiusfield(3,0)
		final JTextField radField = new JTextField(10);
		c.gridx = 3;
		c.gridy = 0;
		contentPane.add(radField, c);
		
		//trials text (0, 1)
		JLabel trialText = new JLabel("Number of Trials: ");
		c.gridx = 0;
		c.gridy = 1;
		
		contentPane.add(trialText, c);
		
		//trialfield (1,1)
		final JTextField trialField = new JTextField(10);
		c.gridx = 1;
		c.gridy = 1;
		contentPane.add(trialField, c);
		
		//Algo Radio buttons (6, 0)
		final JRadioButton rigidRadioButton = new JRadioButton("Rigid Coverage");
		rigidRadioButton.setSelected(true);
		JRadioButton simpleRadioButton = new JRadioButton("Simple Coverage");
		ButtonGroup group2 = new ButtonGroup();
		group2.add(rigidRadioButton);
		group2.add(simpleRadioButton);
		
		Box algoRadio = Box.createVerticalBox();
		algoRadio.add(rigidRadioButton);
		algoRadio.add(simpleRadioButton);
		algoRadio.setBorder(BorderFactory.createEtchedBorder());
		
		c.gridx = 6;
		c.gridy = 0;
		c.gridheight = 2;
		
		contentPane.add(algoRadio, c);
		
		//Single/trial Radio buttons (6, 2)
		final JRadioButton singleRadioButton = new JRadioButton("Single Trial");
		singleRadioButton.setSelected(true);
		JRadioButton trialRadioButton = new JRadioButton("Multi Trial");
		ButtonGroup group1 = new ButtonGroup();
		group1.add(singleRadioButton);
		group1.add(trialRadioButton);
		
		Box trialRadio = Box.createVerticalBox();
		trialRadio.add(singleRadioButton);
		trialRadio.add(trialRadioButton);
		trialRadio.setBorder(BorderFactory.createEtchedBorder());
		
		c.gridx = 6;
		c.gridy = 2;
		c.gridheight = 2;
		c.anchor = GridBagConstraints.NORTH;
		contentPane.add(trialRadio, c);
		
		
		//bar1text(1,2)
		JLabel beforeText = new JLabel("Before:");
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.anchor  = GridBagConstraints.WEST;
		contentPane.add(beforeText, c);
		
		
		//demobar1(0,3)
		final SensorPanel beforePanel = new SensorPanel();
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 6;
		c.gridheight = 1;
		c.weighty = 1;
		c.fill = 1;
		contentPane.add(beforePanel, c);
		
		//bar2text(1,4)
		JLabel afterText = new JLabel("After:");
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 0;
		contentPane.add(afterText, c);
		
		//demobar2(0,5)
		final SensorPanel afterPanel = new SensorPanel();
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 6;
		c.gridheight = 1;
		c.weighty = 1;
		c.fill = 1;
		contentPane.add(afterPanel, c);
		
		//movement label (1,6)
		final JLabel movementText = new JLabel("");
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 0.2;
		movementText.setVisible(false);
		contentPane.add(movementText, c);
		
		//movementtotal label (2,6)
		final JLabel movementTotal = new JLabel("");
		c.gridx = 2;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 0.2;
		contentPane.add(movementTotal, c);
		
		
		//Go Button (6, 6)
		JButton goButton = new JButton("Go!");
		goButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				beforePanel.clearSensors();
				afterPanel.clearSensors();
				if(singleRadioButton.isSelected()){
					movementText.setText("Total Movement:");
					singleModeHandler(numField.getText().trim(), radField.getText().trim(), rigidRadioButton.isSelected(), beforePanel, afterPanel, movementTotal);
				} else {
					movementText.setText("Average Movement:");
					multiModeHandler(numField.getText().trim(), radField.getText().trim(),trialField.getText(), rigidRadioButton.isSelected(), movementTotal);
				}
				movementText.setVisible(true);
			}	
		});
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 0;
		c.fill = 2;
		c.gridx = 6;
		c.gridy = 6;
		contentPane.add(goButton, c);
		
		//Reset Button (6, 5)
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent c){
				numField.setText("");
				radField.setText("");
				trialField.setText("");
				movementText.setVisible(false);
				movementTotal.setText("");
				beforePanel.clearSensors();
				afterPanel.clearSensors();
				
			}	
		});
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 0;
		c.fill = 2;
		c.gridx = 6;
		c.gridy = 5;
		c.anchor  = GridBagConstraints.SOUTH;
		contentPane.add(resetButton, c);

		
		
		setVisible(true);	
	}
	
	/* Function - singleModeHandler
	 * In: 		current UI state
	 * Out: 	void
	 * Other: 	called when go button pressed with [single trial] selected, runs the trial with selected parameters and 
	 * 			displays the before/after results on the top/bottom SensorPanels and total movement at the bottom
	 */
	private void singleModeHandler(String numSensorsString, String radiusString, boolean rigidCoverage, SensorPanel bPanel, SensorPanel aPanel, JLabel move){
		  int 		numSensors; 
		  float 	radius;
		  Sensor[] 	sList;
		  float 	movement;
		  String 	errorMessage = "Number of sensors must be an int > 0, radius must be a decimal between 0 and 1";
		  
		  //error handling for user input 
		  try {
			  numSensors = Integer.parseInt(numSensorsString);
			  radius = Float.parseFloat(radiusString);
		  }
		  catch(NumberFormatException e) {
			  JOptionPane.showMessageDialog(null, errorMessage, "Number format error", JOptionPane.INFORMATION_MESSAGE);
			  return;
		  }
		  if (numSensors <= 0 || (radius <= 0 || radius > 1)){
				JOptionPane.showMessageDialog(null, errorMessage, "Number format error", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		  
		  sList = ctrl.createList(numSensors, radius);	//create sensors
		  //printSensorPositions(sList);
		  bPanel.displaySensors(sList); 				//display initial positions
		  if (rigidCoverage) {							//calculate new positions
			  movement = ctrl.rigidCoverage(sList);		  
		  } else { 
			  movement = ctrl.simpleCoverage(sList);
		  }
		  //printSensorPositions(sList);
		  aPanel.displaySensors(sList);					//display final positions
		  move.setText(""+movement);
	 }
	
	/* Function - multiModeHandler
	 * In: 		current UI state
	 * Out: 	void
	 * Other: 	called when go button pressed with [multi trial] selected, runs the selected trial the
	 * 			specified number of times then displays average movement at the bottom
	 */
	private void multiModeHandler(String numSensorsString, String radiusString, String trialString, boolean rigidCoverage, JLabel moveText){
		int 	numSensors; 
		float 	radius;
		int 	numTrials;
		float	movement;
		String  errorMessage = "Number of sensors/trials must be an int > 0, radius must be a decimal > 0";
		
		try {
			  numSensors 	= Integer.parseInt(numSensorsString);
			  radius 		= Float.parseFloat(radiusString);
			  numTrials		= Integer.parseInt(trialString);
		}
		catch(NumberFormatException e) {
			  JOptionPane.showMessageDialog(null, errorMessage, "Number format error", JOptionPane.INFORMATION_MESSAGE);
			  return;
		}
		if (numSensors <= 0 || radius <= 0 || numTrials <= 0){
			JOptionPane.showMessageDialog(null, errorMessage, "Number format error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		movement = ctrl.trials(numSensors, radius, numTrials, rigidCoverage);
		moveText.setText(""+movement);

	}

	
};
