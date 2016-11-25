import java.util.Arrays;
import java.util.Comparator;

public class Controller {
	static Sensor[] sensorList;
	
	public Sensor[] createList(int numSensors, Float rad){

		//create list of sensors and randomize positions
		sensorList = new Sensor[numSensors];
		for (int i=0; i<numSensors; i++) {
			float randPos = (float) Math.random();
			sensorList[i]  = new Sensor(randPos, rad);
		}
		//sort from lowest to highest xpos
		Arrays.sort(sensorList, new Comparator<Sensor>(){  
			public int compare(Sensor s1, Sensor s2){  
		         if (s1.getPos() < s2.getPos()) return -1;
		         if (s1.getPos() > s2.getPos()) return 1;
		         return 0;
		    }  
		});
		
		return sensorList;
	}
	
	public float rigidCoverage (Sensor[] s){
		float movement 	= 0; 			//total movement
		float radius 	= s[0].getRad();
		float newPos	= 0;
		int toMove		= 0;
				
		for(int i=0; i<s.length; i++) {
			
			newPos = (radius * (2*(float)i+1));
			if (newPos > 1){
				//set all remaining to 1?
				//do something with extras? Like move them all to the right
				break;
			}
			toMove = getClosestUnlocked(s, newPos);
			movement+= Math.abs(s[toMove].getPos() - newPos);
			s[toMove].moveTo(newPos);
			s[toMove].lock();
		}
		System.out.println("Total Movement: "+ movement);
		return movement;
	}
	
	
	public float simpleCoverage (Sensor[] s){
		//finds and moves the closest sensors possible needed to achieve total coverage ignoring overlap.
		float movement 	= 0; 			//total movement (return)
		float radius 	= s[0].getRad();
		float newPos	= 0;		
		int current 	= 0; 			
		int next		= 0; 			
			
		//select first sensor
		current = getClosestUnlocked(s, radius);
		s[current].moveTo(radius);
		s[current].lock();
		
		while (s[current].getPos()<(1-radius)){
			//select next sensor to move/lock			
			//find farthest right within 2R, if none (next == -1)
			next = getRightinRange(s, s[current].getPos(), s[current].getPos()+(2*radius));
			
			if (next > -1){				//either lock the next best
				s[next].lock();				
			} else {					//or find the ideal spot for next
				newPos = s[current].getPos()+(2*radius);
				if (newPos>1){ 			//if newPos is too far, set to 1
					newPos = 1-radius;
				}
				next = getClosestUnlocked(s, newPos);
				
					if (next == -1){ 	//are we out of sensors?
						break;
					}
					
				movement+= Math.abs(s[next].getPos() - newPos);
				s[next].moveTo(newPos);
				s[next].lock();	
			}
			current = next;
		}
		System.out.println("Total Movement: "+ movement);
		return movement;
	
	
	}
	
	
	//helper 1
	public int getRightinRange(Sensor[] s, float x, float y) {
		int result 		= -1;
		float pos		=  0;
		
		for (int i=0; i<s.length; i++){
			pos = s[i].getPos();
			if((pos > x) && (pos<=y)){
				result = i;
			}
		}
		
		return result;
	}
	
	//helper 2
	public int getClosestUnlocked(Sensor[] s, float x) {
		int winner 	= -1;
		float minD 	=  1;
		float d		=  1;
		
		for (int i=0; i<s.length; i++){
			d = Math.abs(s[i].getPos() - x);
			if (d < minD && !s[i].isLocked()){ //is closer and unlocked
				minD 	= d;
				winner 	= i;
			}
		}
		
		return winner;
	}
	
	
}
