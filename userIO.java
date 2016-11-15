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
		setResizable(true);
		setSize(new Dimension(800, 720));	
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
		c.gridx = 3;
		c.gridy = 0;
		contentPane.add(radText, c);
		
		//radiusfield(3,0)
		JTextField radField = new JTextField(10);
		c.gridx = 5;
		c.gridy = 0;
		contentPane.add(radField, c);
		
		
		//Go Button (4, 0)
		
		JButton goButton = new JButton("Go!");
		goButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				goButtonHandler();
			}	
		});

		c.gridx = 7;
		c.gridy = 0;
		contentPane.add(goButton, c);
		
		//Radio buttons (5, 0) & (5, 1)
		JRadioButton rigidRadioButton = new JRadioButton("Rigid Coverage");
		rigidRadioButton.setSelected(true);
		JRadioButton simpleRadioButton = new JRadioButton("Simple Coverage");
		ButtonGroup group = new ButtonGroup();
		group.add(rigidRadioButton);
		group.add(simpleRadioButton);

		c.gridx = 8;
		c.gridy = 0;
		contentPane.add(rigidRadioButton, c);
		
		c.gridx = 8;
		c.gridy = 1;
		contentPane.add(simpleRadioButton, c);

		//bar1text(1,2)
		JLabel beforeText = new JLabel("Before:");
		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(beforeText, c);
		
		//demobar1(0,3)
		//bar2text(1,4)
		JLabel afterText = new JLabel("After:");
		c.gridx = 0;
		c.gridy = 4;
		contentPane.add(afterText, c);
		
		//demobar2(0,5)
		Box afterBox = Box.createHorizontalBox();
		afterBox.setBorder(BorderFactory.createLineBorder(Color.black));
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 5;
		contentPane.add(afterBox, c);
	
		setVisible(true);	
	}
	
	public void goButtonHandler(){
		//get radio selection
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
	
	public double getRad(){
		double r 	= -1;
		//Scanner inputs = new Scanner(System.in);
		
		System.out.println("How much radius?(0<r<1)");
		r = inputs.nextDouble();
		
		while (r <= 0 || r >= 1){
			System.out.println("try again, idiot");
			r = inputs.nextDouble();
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
