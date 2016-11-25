
public class Sensor {
	
	private float 	xpos;
	private float 	radius;
	private boolean locked = false;
	private boolean moved = false;
	
	Sensor(float x, float r){
		xpos 	= x;
		radius 	= r;
	}
	
	public void lock () {
		locked = true;
	}
	public void unlock(){
		locked = false;
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
	
	
}
