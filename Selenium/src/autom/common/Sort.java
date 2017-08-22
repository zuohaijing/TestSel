package autom.common;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
//import java.util.Random;

public class Sort {

  public static void main(String[] args) {
    //This code will sort the numbers in the array.
    //task: Modify the algorithm to reverse the sort, then comment on its
    //efficiency in the space provided below:
    /*
		The following sorting algorithm Big O Notation is O(n^2) which n=100;
    */

    //fill the array with random numbers
    int[] unsorted = new int[100];
    for(int i = 0; i < 100; i++) {
      unsorted[i] = (int) (Math.random() * 100);
    }
    int[] unsortedCopy =unsorted.clone();
    System.out.println("Here are the unsorted numbers:");
    for(int i = 0; i < 100; i++) {
      System.out.print(unsorted[i] + " ");
    }
    System.out.println();
    int[] sorted = new int[100];
    
    
    /*
     * Not efficient nested for loop Big O Notation O(n^2) which n=100 here
     */
    for(int i = 0; i < 100; i++) {
      int hi = -1;
      int hiIndex = -1;
      for(int j = 0; j < 100; j++) {
        if(unsorted[j] > hi) { 
          hi = unsorted[j];
          hiIndex = j;
        }
      }
      sorted[i] = hi;
      unsorted[hiIndex] = -1;
    }
    System.out.println("Here are the sorted numbers: ");
    for(int i = 0; i < 100; i++) {
      System.out.print(sorted[i] + " ");
    }
    
    /*
     * Suggest to use ArrayUtils to improve the algorithm efficiency as following
     * Big O Notation O(1)
     */
    
    System.out.println("\n----------------" + 
    					"Suggest method running below ----------------");
    System.out.println("unsorted numbers:");
    System.out.println((Arrays.toString(unsortedCopy).replaceAll(", ", " ")).
    		replace("[", "").replace("]", ""));
    Arrays.sort(unsortedCopy);
    System.out.println("sorted numbers: ");
    System.out.println((Arrays.toString(unsortedCopy).replaceAll(", ", " ")).
    		replace("[", "").replace("]", ""));
    System.out.println("reeversed sorting numbers: ");
    ArrayUtils.reverse(unsortedCopy);
    System.out.println((Arrays.toString(unsortedCopy).replaceAll(", ", " ")).
    		replace("[", "").replace("]", ""));
    
  }
}

