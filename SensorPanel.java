import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class SensorPanel extends JPanel{
	Sensor[] list;
	int w = this.getWidth();
	int h = this.getHeight();
	
	public SensorPanel(){
		//this.setLayout(new GridLayout());
		//this.setBackground(Color.GRAY);
		setVisible(true);
	}
	
	//draw horizontal line
	public void paint(Graphics g){
		super.paint(g);
		//draw base line
		Graphics2D g2 = (Graphics2D) g;
		Line2D lineOne = new Line2D.Float(0, w, h/2, h/2); 
		g2.draw(lineOne);
		
		//for(int i = 0; i<list.length; i++){
			//draw dot
			//draw line
		//}
		
	}

}
