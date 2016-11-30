import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SensorPanel extends JPanel{
	
	//constructor, enable and create border
	public SensorPanel(){
		setVisible(true);
		this.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.BLACK));		
	}
	
	/* Function - Paint
	 * In:		Graphics objects
	 * Out:		void
	 * Other:	sets up display window, lines and labels
	 * 			@see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		super.paint(g);
		//draw base line
		
		Graphics2D g2 = (Graphics2D) g;
		Line2D mainLine = new Line2D.Float(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2); 
		g2.draw(mainLine);
		
		//notches
		Line2D notch1 = new Line2D.Float(this.getWidth()/4, this.getHeight()/2-3, this.getWidth()/4, this.getHeight()/2+3); 
		g2.draw(notch1);
		Line2D notch2 = new Line2D.Float(this.getWidth()/2, this.getHeight()/2-3, this.getWidth()/2, this.getHeight()/2+3); 
		g2.draw(notch2);
		Line2D notch3 = new Line2D.Float(this.getWidth()*3/4, this.getHeight()/2-3, this.getWidth()*3/4, this.getHeight()/2+3); 
		g2.draw(notch3);
		
		//line labels
		g2.drawString("0", 		3, 						this.getHeight()/2+15);
		g2.drawString("0.5", 	this.getWidth()/2-8, 	this.getHeight()/2+15);
		g2.drawString("1", 		this.getWidth()-10, 	this.getHeight()/2+15);
	}
	
	/* Function - displaySensors
	 * In: 		Sensor list
	 * Out: 	void
	 * Other:	Iterates over the given sensor list, drawing them all on the graph with a horizontal line for their radius.
	 * 			Sensors that have been moved are in red and locked sensors are circled
	 */
	public void displaySensors(Sensor[] s){
		Graphics g = this.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		
		float x1, x2;
		float y1, y2;
		y1 = y2 = (float) (this.getHeight()/2*1.0 - 15);
		
		for (int i =0; i<s.length; i++) {
			
			//select color
			if(s[i].wasMoved())	{g2.setColor(Color.RED);}
			else {				g2.setColor(Color.BLACK);}
			
			//draw sensor
			Shape circle = new Ellipse2D.Float(this.getWidth()*s[i].getPos(), this.getHeight()/2 - 20, 6, 10);
			g2.fill(circle);

			//draw radius
			x1 = this.getWidth()*s[i].getPos() - this.getWidth()*s[i].getRad();
			x2 = this.getWidth()*s[i].getPos() + this.getWidth()*s[i].getRad();
			Line2D lineOne = new Line2D.Float(x1, y1, x2, y2);
			g2.draw(lineOne);
			
			//circle locked sensor
			if (s[i].isLocked()){
				Shape q = new Ellipse2D.Float(this.getWidth()*s[i].getPos()-2, this.getHeight()/2 - 22, 10, 14);
				g2.setColor(Color.GRAY);
				g2.draw(q);
			}
		}
	}
	
	/* Function - clearSensor
	 * In:		none
	 * out:		void
	 * Other:	resets panel for next trial
	 */
	public void clearSensors(){
		Graphics g = this.getGraphics();
		g.clearRect(2, 2, this.getWidth()-4, this.getHeight()/2-4);
	}
};
