import java.util.Arrays;
import java.util.Comparator;

public class Controller {
	static Sensor[] sensorList;		//placeholder
	
	
	/*	Function - createList
	 * 	In: 	number of sensors, radius
	 *  Out: 	Sorted list of sensors
	 *  Other: 	creates a list of sensors length numSensors with given radius and a random position
	 *  		in the area. The sensors are sorted by xpos	from lowest to highest
	 */
	public Sensor[] createList(int numSensors, float rad){
		//Create the list 
		sensorList = new Sensor[numSensors];
		for (int i=0; i<numSensors; i++) {
			float randPos = (float) Math.random();
			sensorList[i]  = new Sensor(randPos, rad);
		}
		//Sort it
		Arrays.sort(sensorList, new Comparator<Sensor>(){  
			public int compare(Sensor s1, Sensor s2){  
		         if (s1.getPos() < s2.getPos()) return -1;
		         if (s1.getPos() > s2.getPos()) return 1;
		         return 0;
		    }  
		});
		return sensorList;
	}
	
	
	/*	Function - rigidCoverage
	 * 	In: 	List of sensors
	 *  Out: 	Total movement of sensors
	 *  Other: 	rigidCoverage finds the minimum positions to completely cover the area
	 *  		and then moves the closest unlocked sensor to each of those positions
	 */
	public float rigidCoverage (Sensor[] s){
		float movement 	= 0; 				//sum total movement
		float radius 	= s[0].getRad();	//radius of sensors
		float newPos	= 0;				//next sensor destination
		int toMove		= 0;				//sensor that will move
				
		for(int i=0; i<s.length; i++) {
			newPos = (radius * (2*(float)i+1));
			if (newPos > 1){
				break;											//break when end of area is reached
			}
			toMove = getClosestUnlocked(s, newPos);				//select sensor
			movement+= Math.abs(s[toMove].getPos() - newPos);	//track movement
			s[toMove].moveTo(newPos);							//move sensor
			s[toMove].lock();									//lock it in place
		}
		return movement;										//return total movement
	}
		
	
	/*	Function - simpleCoverage
	 * 	In: 	List of sensors
	 *  Out: 	Total movement of sensors
	 *  Other: 	simpleCoverage scans from left to right until it finds an area uncovered by sensors
	 *  		when it finds a gap, it moves the closest available sensor to the optimal position
	 */
	public float simpleCoverage (Sensor[] s){
		float movement 	= 0; 				//sum total movement
		float radius 	= s[0].getRad();	//radius of sensors
		float newPos	= 0;				//next sensor destination
		int current 	= 0; 				//current sensor id
		int next		= 0; 				//next sensor id
			
		//select first sensor
		current = getClosestUnlocked(s, radius);
		s[current].moveTo(radius);
		s[current].lock();
		
		//scan to the right until we reach the end
		while (s[current].getPos()<(1-radius)){
			next = getRightinRange(s, s[current].getPos(), s[current].getPos()+(2*radius));
			if (next > -1){			//either lock the next best
				s[next].lock();			
			} else {				//or find the ideal spot for next
				newPos = s[current].getPos()+(2*radius);
				if (newPos>1){ 		//if newPos is too far, set to 1
					newPos = 1-radius;
				}
				next = getClosestUnlocked(s, newPos);
				if (next == -1){ 	//are we out of sensors?
						break;
					}
					
				movement+= Math.abs(s[next].getPos() - newPos);	//track movement
				s[next].moveTo(newPos);							//move sensor
				s[next].lock();									//lock it in place
			}
			current = next;
		}
		return movement;
	}

	
	/*	Function - trials
	 *  In:		number of sensors, radius, number of trials, coverage algorithm to use
	 *  Out:	Average movement of sensors over given number of trials
	 *  Other:	Runs the chosen algorithm n times and sums the total movement
	 *  		return total movement divided by n. 
	 */
	public float trials (int numSensors, float rad, int numTrials, boolean rigid){
		float totalMovement =0;
		Sensor[] sList;
		
		for (int i=0; i<numTrials; i++){
			sList = createList(numSensors, rad);
			if (rigid){
				totalMovement += rigidCoverage(sList);
			} else {
				totalMovement += simpleCoverage(sList);
			}
		}		
		return (totalMovement/numTrials);
	}
	
	
	/*	Function - getRightinRange
	 * 	In:		sensor list, left bound, right bound
	 * 	Out:	id of rightmost sensor in bounds
	 *  Other:	helper function for simpleCoverage
	 */
	public int getRightinRange(Sensor[] s, float x1, float x2) {
		int result 		= -1;
		float pos		=  0;
		
		for (int i=0; i<s.length; i++){
			pos = s[i].getPos();
			if((pos > x1) && (pos<=x2)){
				result = i;
			}
		}
		
		return result;
	}
	
	
	/*	Function - getClosestUnlocked
	 * 	In:		sensor list, x-position
	 * 	Out:	id of closest unlocked sensor to x 
	 *  Other:	helper function for rigidCoverage & simpleCoverage
	 */
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
	
	
};
