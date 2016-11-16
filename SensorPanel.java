import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class SensorPanel extends JPanel{
	
	
	public SensorPanel(){
		//this.setBackground(Color.GRAY);
		setVisible(true);
		
	}
	
	//draw horizontal line
	public void paint(Graphics g){
		super.paint(g);
		//draw base line
		Graphics2D g2 = (Graphics2D) g;
		Line2D lineOne = new Line2D.Float(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2); 
		g2.draw(lineOne);
		System.out.println("width: " + this.getWidth()+ " height: "+ this.getHeight());
		
		
	}
	
	//
	public void displaySensors(Sensor[] s){
		Graphics g = this.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		
		float x1, x2;
		float y1, y2;
		y1 = y2 =(float) (this.getHeight()/2*1.0 - 15);
		
		for (int i =0; i<s.length; i++) {
			//draw circle
			Shape circle = new Ellipse2D.Float(this.getWidth()*s[i].getPos(), this.getHeight()/2 - 20, 6, 10);
			if(s[i].wasMoved()){
				g2.setColor(Color.RED);
			} else {
				g2.setColor(Color.BLACK);
			}
			g2.fill(circle);
			//draw line
			
			x1 = this.getWidth()*s[i].getPos() - this.getWidth()*s[i].getRad();
			x2 = this.getWidth()*s[i].getPos() + this.getWidth()*s[i].getRad();
			Line2D lineOne = new Line2D.Float(x1, y1, x2, y2);
			g2.draw(lineOne);
			
			if (s[i].isLocked()){
				Shape q = new Ellipse2D.Float(this.getWidth()*s[i].getPos()-2, this.getHeight()/2 - 22, 10, 14);
				g2.setColor(Color.GRAY);
				g2.draw(q);
			}
		}
		
		
	}
	public void clearSensors(){
		Graphics g = this.getGraphics();
		g.clearRect(0, 0, this.getWidth(), this.getHeight()/2-2);
	}

}
