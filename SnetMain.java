import java.util.Arrays;
import java.util.Comparator;

public class SnetMain {

	public static void main(String[] args) {
		
		Controller ctrl = new Controller();
		userIO viewClass = new userIO(ctrl);	
		boolean again = true;
		
		while (again){
			again = ctrl.start(viewClass);
			
		}

	}
}
