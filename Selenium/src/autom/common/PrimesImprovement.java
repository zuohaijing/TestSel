package autom.common;
import java.util.ArrayList;

public class PrimesImprovement {

	public static void main(String[] args) {

		//This code should print out the prime numbers.
		//task: Fix and improve the algorithm

		System.out.println("Printing primes from 1 to 100");
		Integer i;
		ArrayList<Integer> arrlist = new ArrayList<Integer>();

//		    int j;
//		    boolean prime;
//		    for(i = 1; i <= 100; i++) {
//		      prime = true;
//		      for(j = 1; j < i; j++) {
//		        if(i % j == 0) prime = false;
//		      } 
//		      if(prime) {
//		        System.out.print(i + " ");
//		      }
//		    }
//		    System.out.println();

		
//		
//		Store the known prime number to an arrayList, and use known prime number to
//		check the outer loop number and see if it is a prime number
//		
//		Use a separate method "isPrime" instead of inner for loop, looks better logic structure.
//		
		for(i = 1; i <= 100; i++) {
			if(isPrime(i, arrlist)) {
				System.out.print(i + " ");
				arrlist.add(i);
			}
		}
		System.out.println();
	}

	private static boolean isPrime(Integer n, ArrayList<Integer> arrlist) {
		if(n < 2)
			return false;
		for (Integer num : arrlist) {   
			if (n % num == 0)
				return false;
//			
//			We don't have to iterate all the arrlist members, after we iterate to the member "num"
//			and if statement "num * num > n" is true, and then number n is a prime number.
//			
			if (num * num > n)
				break;
		}
		return true;
	}
}