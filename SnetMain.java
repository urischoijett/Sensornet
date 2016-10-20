import java.util.Arrays;
import java.util.Comparator;

public class SnetMain {

	public static void main(String[] args) {
		
		userIO viewClass = new userIO();	
		boolean again = true;
		
		while (again){
			start(viewClass);
			
		}

	}

/*	public Sensor[] makeSensors(userIO bob){
		int numSensors 	= bob.getNum();
		double rad 		= bob.getRad();
		
		Sensor[] sensorList = new Sensor[numSensors];
		for (int i=0; i<numSensors; i++) {
			double randPos =Math.random();
			sensorList[i] = new Sensor(randPos, rad);
		}
		return sensorList;
	} */

	public static void start(userIO bob){
		int numSensors 	= bob.getNum();
		double rad 		= bob.getRad();
						
		Sensor[] sensorList = new Sensor[numSensors];
		
		for (int i=0; i<numSensors; i++) {
			double randPos = Math.random();
			sensorList[i]  = new Sensor(randPos, rad);
		}
		
		rigidCoverage(sensorList);
		
	}
	
	public static void rigidCoverage (Sensor[] s){
		double movement 	= 0; 			//total movement
		double radius 		= s[0].getRad();
		double newPos		= 0;		
		
		//sort from lowest to highest xpos
		Arrays.sort(s, new Comparator<Sensor>(){  
			@Override
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
			s[i].setPos(newPos);
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
		double movement 	= 0; 			//total movement
		double radius 		= s[0].getRad();
		double newPos		= 0;		
		
		//sort from lowest to highest xpos
		Arrays.sort(s, new Comparator<Sensor>(){  
			@Override
		    public int compare(Sensor s1, Sensor s2){  
		         if (s1.getPos() < s2.getPos()) return -1;
		         if (s1.getPos() > s2.getPos()) return 1;
		         return 0;
		    }  
		});
	
	
	
	}
	
	
		
	
	
	
}
