import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class userIO extends JFrame  {
	
	
	private Controller ctrl;
	private JPanel contentPane;
	Scanner inputs =  new Scanner(System.in);
	
	
	//constructor
	public userIO(Controller control){
		ctrl = control;
		setResizable(false);
		setSize(new Dimension(1280,720));		
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setContentPane(contentPane);


		
		
		//Go Button
		//set c
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		JButton goButton = new JButton("Go!");
		goButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				buttonHandler();
			}
			
		});
		contentPane.add(goButton, c);
		
		
		
		setVisible(true);	
	}
	
	public void buttonHandler(){
		
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
