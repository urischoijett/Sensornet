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
		
		return sensorList;
	}
	
	public void rigidCoverage (Sensor[] s){
		float movement 	= 0; 			//total movement
		float radius 	= s[0].getRad();
		float newPos	= 0;		
		
		//sort from lowest to highest xpos
		Arrays.sort(s, new Comparator<Sensor>(){  
			public int compare(Sensor s1, Sensor s2){  
		         if (s1.getPos() < s2.getPos()) return -1;
		         if (s1.getPos() > s2.getPos()) return 1;
		         return 0;
		    }  
		});
		
		
		// move sensors

		for(int i=0; i<s.length; i++) {
			
			newPos = (radius * (2*(float)i+1));
			movement+= Math.abs(s[i].getPos() - newPos);
			
			System.out.println(s[i].getPos() + ", ");
			s[i].moveTo(newPos);
			System.out.println(s[i].getPos() + ", ");
			
			if (newPos > 1){
				//set all remaining to 1?
				//do something with extras? Like move them all to the right
				System.out.println(", Movement: "+ movement);
				break;
			}
		}
	
	
	}
	
	
	public void simpleCoverage (Sensor[] s){
		//finds and moves the closest sensors possible needed to achieve total coverage ignoring overlap.
		float movement 		= 0; 			//total movement
		float radius 		= s[0].getRad();
		float newPos		= 0;		
		int lockedCount		= 0;
		//sort from lowest to highest xpos
		Arrays.sort(s, new Comparator<Sensor>(){  
			public int compare(Sensor s1, Sensor s2){  
		         if (s1.getPos() < s2.getPos()) return -1;
		         if (s1.getPos() > s2.getPos()) return 1;
		         return 0;
		    }  
		});
		
		
		//locate first sensor
		int current = 0; //current sensor
		int next	= 0; //next sensor
		
		//select first sensor
		current = getClosestUnlocked(s, radius);
		s[current].moveTo(radius);
		s[current].lock();
		
		//print pre-sort array
		for(int i=0; i<s.length; i++) {
			System.out.println("pos "+i+": " + s[i].getPos());
		}
		
		while (s[current].getPos()<=(1-radius)){
						
			//find farthest right within 2R 
			next = getRightinRange(s, s[current].getPos(), s[current].getPos()+(2*radius));
			
			System.out.println("s[current] pos:" + s[current].getPos());
			
			if (next > -1){
				s[next].lock();
				
				
			} else {	//if none, getClosestUnlocked to current.xpos+2r
				newPos = s[current].getPos()+(2*radius);
				if (newPos>1){ newPos = 1-radius; }
				next = getClosestUnlocked(s, newPos);
					if (next == -1){
						break;
					}
				movement+= Math.abs(s[next].getPos() - newPos);
				s[next].moveTo(newPos);
				s[next].lock();	
			}
			
			System.out.println("current: " + s[current].getPos());
			System.out.println("  next: " + s[next].getPos()+ "\n");
			current = next;
		}
		System.out.println("movement: "+movement);
		
	
	
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
				minD = d;
				winner = i;
			}
		}
		
		return winner;
	}
	
	
}
