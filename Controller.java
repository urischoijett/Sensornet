import java.util.Arrays;
import java.util.Comparator;

public class Controller {
	static Sensor[] sensorList;

	public static boolean start(userIO bob){
		boolean again = true; //start again when done 
		//start UI
		//on GO, get info
		//info.befirelist
		//run algo
		//info.afterlist
		//update(info)

		//requirements elicitation
		int numSensors 	= bob.getNum();
		double rad 		= bob.getRad();
						
		//create list of sensors and randomize positions
		sensorList = new Sensor[numSensors];
		for (int i=0; i<numSensors; i++) {
			double randPos = Math.random();
			sensorList[i]  = new Sensor(randPos, rad);
		}
		
		//before
		bob.printSensorPositions(sensorList);
		
		
		simpleCoverage(sensorList);
		//rigidCoverage(sensorList);
		
		//after
		bob.printSensorPositions(sensorList);
		return again;
	}
	
	public static void rigidCoverage (Sensor[] s){
		double movement 	= 0; 			//total movement
		double radius 		= s[0].getRad();
		double newPos		= 0;		
		
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
			
			newPos = (radius * (2*(double)i+1));
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
	
	
	
	public static void simpleCoverage (Sensor[] s){
		//finds and moves the closest sensors possible needed to achieve total coverage ignoring overlap.
		double movement 	= 0; 			//total movement
		double radius 		= s[0].getRad();
		double newPos		= 0;		
		
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
		
		while (s[current].getPos()<(1-radius)){
			if(s[s.length-1].isLocked()){
				System.out.println("I'M LOCKED");
				break;
			}
			
			//find farthest right within 2R 
			next = getRightinRange(s, s[current].getPos(), s[current].getPos()+(2*radius));
			
			System.out.println("s[current] pos:" + s[current].getPos());
			
			if (next > -1){
				s[next].lock();
				
			} else {	//if none, getClosestUnlocked to current.xpos+2r
				next = getClosestUnlocked(s, s[current].getPos()+(2*radius));
				s[next].moveTo(s[current].getPos()+(2*radius));
				s[next].lock();
				movement+= Math.abs(s[next].getPos() - s[current].getPos());
			}
			System.out.println("current: " + s[current].getPos());
			System.out.println("  next: " + s[next].getPos()+ "\n");
			current = next;
		}
		System.out.println("movement: "+movement);
		
	
	
	}
	
	
	//helper 1
	public static int getRightinRange(Sensor[] s, double x, double y) {
		int result 		= -1;
		double pos		=  0;
		
		for (int i=0; i<s.length; i++){
			pos = s[i].getPos();
			if((pos > x) && (pos<=y)){
				result = i;
			}
		}
		
		return result;
	}
	
	//helper 2
	public static int getClosestUnlocked(Sensor[] s, double x) {
		int winner 	= -1;
		double minD =  1;
		double d	=  1;
		
		for (int i=0; i<s.length; i++){
			d = Math.abs(s[i].getPos() - x);
			if (d < minD){
				minD = d;
				winner = i;
			}
		}
		
		return winner;
	}
	
	
	

}
