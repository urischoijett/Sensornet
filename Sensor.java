
public class Sensor {
	
	private float 	xpos;				//current position within area
	private float 	radius;				//radius of sensors
	private boolean locked = false;		//can the sensor be moved
	private boolean moved = false;		//has the sensor been moved
	
	Sensor(float x, float r){
		xpos 	= x;
		radius 	= r;
	}
	
	public void lock () {
		locked = true;
	}
	public void moveTo(float x){
		xpos = x;
		moved = true;
	}
	public float getPos(){
		return xpos;
	}
	public float getRad(){
		return radius;
	}
	public boolean wasMoved(){
		return moved;
	}
	public boolean isLocked(){
		return locked;
	}
	
	
};
