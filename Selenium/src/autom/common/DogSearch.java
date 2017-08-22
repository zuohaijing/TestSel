package autom.common;
public class DogSearch {

 public static void main(String[] args) {
    //This code will not compile correctly until you fix it.
    //
    //We are trying to count the number of times the word
    //dog appears in some input text, but we can't seem to
    //remember the appropriate method call in the String class,
    //and are not sure our approach will work.
    //task: Use the JDK documentation to help us finish the code,
    //then discuss the ambiguity in the problem description, and
    //reword it to remove the ambiguity in the space provided below.

    /*
     * The String method split can be used here to count the number of keyword appears
     * The do-while loop is very bad, should be removed. See below.
     * */

    String input = new String("The Dogman was no ordinary dog, nor man, but rather a peculiar dog-like man who barked like a dog, and panted like a dog, he even ate like a dog.  He owned a dog named Doglips, and interestingly enough, his favorite food was hotdogs.");
    System.out.println(input);
//    int index = -1;
    int count = 0;
    /*
     * Declare a variable to store the possible change of the searching keyword. Best practice.
     */
    String sKeyWord="dog";
    
    System.out.print("Counting word '" + sKeyWord + "':");
    
    /* The following do-while loop is very danger, may exhaust the system memory because
     *  condition Line 42 is always true. There is no way to jump out of the loop.
     *  The code will never be ended.
     */    
//    do {
//      index = input.input.findWord("dog");
//      System.out.print("Counting -----: " + index );
//      index=-1;
//      if(index != -1) {
//        count++;
//        System.out.print(count+" ");
//      }
//    } while (index != -1);
    
    /*
     * Use String split to count the number of the keyword appearance.
     */
    
    if(input.contains(sKeyWord)){
        count = input.split(sKeyWord).length-1;    	
    } else {
    	count=0;
    }
    System.out.println("\nThe word '"+ sKeyWord + "' appears " + count + " times.\n");
  } 
}

