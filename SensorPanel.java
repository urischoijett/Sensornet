import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SensorPanel extends JPanel{
	
	
	public SensorPanel(){
		//this.setBackground(Color.DARK_GRAY);
		setVisible(true);
		this.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.BLACK));
		
		
	}
	
	//draw horizontal line
	public void paint(Graphics g){
		super.paint(g);
		//draw base line
		
		Graphics2D g2 = (Graphics2D) g;
		Line2D mainLine = new Line2D.Float(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2); 
		g2.draw(mainLine);
		
		Line2D notch1 = new Line2D.Float(this.getWidth()/2, this.getHeight()/2-3, this.getWidth()/2, this.getHeight()/2+3); 
		g2.draw(notch1);
		
		Line2D notch2 = new Line2D.Float(this.getWidth()/4, this.getHeight()/2-3, this.getWidth()/4, this.getHeight()/2+3); 
		g2.draw(notch2);
		
		Line2D notch3 = new Line2D.Float(this.getWidth()*3/4, this.getHeight()/2-3, this.getWidth()*3/4, this.getHeight()/2+3); 
		g2.draw(notch3);
		
		//add some line labels
		g2.drawString("0", 		3, 						this.getHeight()/2+15);
		g2.drawString("0.5", 	this.getWidth()/2-8, 	this.getHeight()/2+15);
		g2.drawString("1", 		this.getWidth()-10, 	this.getHeight()/2+15);
	}
	
	//takes a sensor list, puts them all on the line, locked and moved are displayed differently
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
		g.clearRect(2, 2, this.getWidth()-4, this.getHeight()/2-4);
	}

}
