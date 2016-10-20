import java.util.Scanner;

public class userIO {
	Scanner inputs =  new Scanner(System.in);
	
	public int getNum(){
		int n 	= -1;
		//Scanner inputs = new Scanner(System.in);
		
		System.out.println("How many sensors?(1-999)");
		n = inputs.nextInt();
		
		while (n<1 || n > 999){
			System.out.println("try again, idiot");
			n = inputs.nextInt();
		}
		//inputs.close();
		return n;
	}
	
	
	public double getRad(){
		double r 	= -1;
		//Scanner inputs = new Scanner(System.in);
		
		System.out.println("How much radius?(0<r<1)");
		r = inputs.nextDouble();
		
		while (r <= 0 || r >= 1){
			System.out.println("try again, idiot");
			r = inputs.nextDouble();
		}
		//inputs.close();
		return r;
	}


	
	
	/*
	public int getVal(String Q, int min, int max){
		int n = 0;
		Scanner inputs = new Scanner(System.in);
		System.out.println(Q);
		n = inputs.nextInt();
		
		while (n<min || n> max) {
			System.out.println("try again, idiot (you done goofed)");
			n = inputs.nextInt();
		}
		
		return n;
	} */
}
