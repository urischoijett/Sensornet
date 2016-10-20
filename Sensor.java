
public class Sensor {
	
	private double 	xpos;
	private double 	radius;
	private boolean locked = false;
	
	Sensor(double x, double r){
		xpos 	= x;
		radius 	= r;
	}
	
	public void lock () {
		locked = true;
	}
	public void unlock(){
		locked = false;
	}
	public void moveTo(int x){
		xpos = x;
	}
	public double getPos(){
		return xpos;
	}
	public double getRad(){
		return radius;
	}
	public void setPos(double newPos){
		xpos = newPos;
	}
	
	public boolean isLocked(){
		return locked;
	}
	
	
}
