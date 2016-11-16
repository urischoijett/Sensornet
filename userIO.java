import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

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



public class userIO extends JFrame  {
	
	
	private Controller ctrl;
	private JPanel contentPane;
	Scanner inputs =  new Scanner(System.in);
	
	
	//constructor
	public userIO(Controller control){
		ctrl = control;
		setResizable(false);
		setSize(new Dimension(800, 600));	
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//gridbag start
		contentPane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setContentPane(contentPane);
		
		//Numbertext (0,0)
		JLabel numText = new JLabel("Number of Sensors: ");
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		contentPane.add(numText, c);
		
		//numberfield(1,0)
		JTextField numField = new JTextField(10);
		c.gridx = 1;
		c.gridy = 0;
		contentPane.add(numField, c);
		
		//radiustext(2,0)
		JLabel radText = new JLabel("Radius of Sensors: ");
		c.gridx = 2;
		c.gridy = 0;
		contentPane.add(radText, c);
		
		//radiusfield(3,0)
		JTextField radField = new JTextField(10);
		c.gridx = 3;
		c.gridy = 0;
		contentPane.add(radField, c);
		
		
		//Radio buttons (5, 0) & (5, 1)
		JRadioButton rigidRadioButton = new JRadioButton("Rigid Coverage");
		rigidRadioButton.setSelected(true);
		JRadioButton simpleRadioButton = new JRadioButton("Simple Coverage");
		ButtonGroup group = new ButtonGroup();
		group.add(rigidRadioButton);
		group.add(simpleRadioButton);

		c.gridx = 5;
		c.gridy = 0;
		c.anchor  = GridBagConstraints.WEST;
		contentPane.add(rigidRadioButton, c);
		
		c.gridx = 5;
		c.gridy = 1;
		contentPane.add(simpleRadioButton, c);

		//bar1text(1,2)
		JLabel beforeText = new JLabel("Before:");
		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(beforeText, c);
		
		
		//demobar1(0,3)
		SensorPanel beforePanel = new SensorPanel();
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 5;
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
		SensorPanel afterPanel = new SensorPanel();
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 5;
		c.gridheight = 1;
		c.weighty = 1;
		c.fill = 1;
		contentPane.add(afterPanel, c);
		
		
		//Go Button (4, 0)
		JButton goButton = new JButton("Go!");
		goButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				goButtonHandler(numField.getText().trim(), radField.getText().trim(), rigidRadioButton.isSelected(), beforePanel, afterPanel);
			}	
		});
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 0;
		c.fill = 0;
		c.gridx = 4;
		c.gridy = 0;
		contentPane.add(goButton, c);
		setVisible(true);	
	}
	
	 public void goButtonHandler(String numSensorsString, String radiusString, boolean rigidCoverage, SensorPanel bPanel, SensorPanel aPanel){
		  int numSensors; 
		  float radius;
		  Sensor[] sList;
			 
		  bPanel.clearSensors();
		  aPanel.clearSensors();
		  
		  //error handling for user input 
		  try {
			  numSensors = Integer.parseInt(numSensorsString);
			  radius = Float.parseFloat(radiusString);
		  }
		  catch(NumberFormatException e) {
			  String errorMessage = "Please enter a valid number for both the radius and the number of sensors";
			  JOptionPane.showMessageDialog(null, errorMessage, "Number format error", JOptionPane.INFORMATION_MESSAGE);
			  return;
		  }

		  //create sensors
		  sList = ctrl.createList(numSensors, radius);
		  
		  //display initial positions
		  bPanel.displaySensors(sList);
		  
		  //display final positions
		  if (rigidCoverage) {
			  ctrl.rigidCoverage(sList);		  
		  } else { // simple coverage
			  ctrl.simpleCoverage(sList);
		  }
		  aPanel.displaySensors(sList);
	 }
	
	
	
	
	
	public int getNum(){
		int n 	= -1;
		//Scanner inputs = new Scanner(System.in);
		
		System.out.println("How many sensors?(1-999)");
		n = inputs.nextInt();
		
		while (n<1 || n > 999){
			System.out.println("try again, idiot");
			n = inputs.nextInt();
		}
		//inputs.close();
		return n;
	}
	
	public float getRad(){
		float r 	= -1;
		//Scanner inputs = new Scanner(System.in);
		
		System.out.println("How much radius?(0<r<1)");
		r = inputs.nextFloat();
		
		while (r <= 0 || r >= 1){
			System.out.println("try again, idiot");
			r = inputs.nextFloat();
		}
		//inputs.close();
		return r;
	}

	public void displayNet(Sensor[] s){

	}

	public void printSensorPositions(Sensor[]s){
		for(int i=0; i<s.length; i++) {
			System.out.println("pos "+i+": " + s[i].getPos());
		}
	}
	
	
}
