package autom.common;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PracticeTest {

	public static void main(String args[]) {
		byte a = 127; byte b = 127; 
//		b = a + b; // error : cannot convert from int to byte 
		b += a; // ok
		System.out.println("*** b= " + b);
//		System.out.println("*** b= " + b + " ====> it is type of " + b.getClass().getSimpleName()); /// getClass cannot get the primitive type
		Typetester t = new Typetester();
		t.printType( b );
//				+ startedTime + " === Test started now at " 
//				+ dateFormat.format(date) + "***");
	
		Pattern pattern = Pattern.compile(".*\\D.*");
		Pattern pattern2 = Pattern.compile(".*[^0-9].*");
		Pattern digitPattern = Pattern.compile("\\d\\d\\d\\d\\d\\d");
		Pattern digitPattern2 = Pattern.compile("\\d{6}");


//		Pattern pattern3 = Pattern.compile("^(\\d+.*)");
//		Pattern pattern3 = Pattern.compile(".*[^0-9-+].[0-9]+");
		Pattern pattern3 = Pattern.compile(".*[^(-?)(+?)(1-9)][0-9]+");
//		Pattern pattern3 = Pattern.compile(".*[^(0-9-+)].[0-9]+");
//		Pattern pattern3 = Pattern.compile(".*[^(-+).*[0-9]+].\\d+");
//		Pattern pattern3 = Pattern.compile(".*[^(-+).*[0-9]+].\\d+|.*[^[1-9].*[0-9]+].\\d+");
//		Pattern pattern3 = Pattern.compile(".*[^(-+).*[0-9]+].\\d+|.*[^[1-9].*[0-9]+]");
//		Pattern pattern3 = Pattern.compile(".*[^0-9-+].\\d+");
		Pattern pattern4 = Pattern.compile("[^(-?1-9)][0-9]+");
		Pattern pattern5 = Pattern.compile("^(-?)\\d+$");
//		Pattern pattern6 = Pattern.compile(".*[^\\d*[1-9]\\d*$]");
//		Pattern pattern6 = Pattern.compile("^-?[0-9]{1,12}(?:\\.[0-9]{1,4})?$");
		
		Pattern pattern6 = Pattern.compile("^[\\-\\+]\\s*\\d+\\s*$"); 
		
	     String [] inputs = {"123", "-123" , "123.12", "abcd123","+98","-0.8","-9","-9pl78","-9-9"};
	       
	        for(String input: inputs){
	            System.out.println( "does " + input + " is number : " + !pattern3.matcher(input).matches());
	            System.out.println( "p4 does " + input + " is number : " + !pattern4.matcher(input).matches());
	            System.out.println( "p5 does " + input + " is number : " + !pattern5.matcher(input).matches());
	            System.out.println( "p6 does " + input + " is number : " + !pattern6.matcher(input).matches());

	        }
	        if (3*0.1 == 0.3){
	        	System.out.println( "does  is number : true");
	        } else {
	        	System.out.println( "p6 doesnumber : false");
	    		Typetester t2 = new Typetester();
	    		t2.printType( 3.0*0.1 );
	    		Typetester t3 = new Typetester();
	    		t3.printType( 0.3 );
	        }

	        
	        
	        
	  	  ArrayList<String> obj = new ArrayList<String>();

		  /*This is how elements should be added to the array list*/
		  obj.add("Ajeet");
		  obj.add("Harry");
		  obj.add("Chaitanya");
		  obj.add("Steve");
		  obj.add("Anuj");
		  System.out.println("Print arraylist directly:"+ obj);
		  System.out.println("Print arraylist from the toString():" + obj.toString());
	}
}
