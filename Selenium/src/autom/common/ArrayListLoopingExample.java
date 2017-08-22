package autom.common;

//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.internal.collections.Ints;

public class ArrayListLoopingExample {
	
	int ig[] = {0};
	
	  public static Class<Boolean> typeof(final boolean expr) {
		    return Boolean.TYPE;
		  }
		 
		  public static Class<Character> typeof(final char expr) {
		    return Character.TYPE;
		  }
		 
		  public static Class<Byte> typeof(final byte expr) {
		    return Byte.TYPE;
		  }
		 
		  public static Class<Short> typeof(final short expr) {
		    return Short.TYPE;
		  }
		 
		  public static Class<Integer> typeof(final int expr) {
		    return Integer.TYPE;
		  }
		 
		  public static Class<Long> typeof(final long expr) {
		    return Long.TYPE;
		  }
		 
//		  public static Class<String> typeof(final String expr) {
//			    return String.
//			  }
		  public static Class<Float> typeof(final float expr) {
		    return Float.TYPE;
		  }
		 
		  public static Class<Double> typeof(final double expr) {
		    return Double.TYPE;
		  }
		 
		  public static Class<?> typeof(final Object expr) {
		    return expr == null ? null : expr.getClass();
		  }
	
	public static void main(String[] args) {

		List <String> hhh=new ArrayList<String>();
		List <Integer> hshh=new ArrayList<Integer>();
		Map<Integer, String> myTestMap = new HashMap<Integer, String>();
		myTestMap.put(1, "Jan");
		myTestMap.put(2, "Feb");
		myTestMap.put(3, "Mar");
		myTestMap.put(4, "Mar");
		
		
		
		
		
		String sd = "12345"; 
		int n = 0;
		Integer result = Integer.valueOf(sd);
		
		for (int i = 0; i < sd.length(); i++) 
			n = n * 10 + (sd.charAt(i) - '0');
		int itt=0;
	    while(itt < sd.length()) {
	        n *= 10;
	        System.out.println("   Character value chr '"+ sd.charAt(itt) + "'=" + (sd.charAt(itt)) + "   type of: " + typeof(sd.charAt(itt)));
	        n += sd.charAt(itt) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
	        System.out.println("i=" + itt + " and n=" + n + "   Character value chr '"+ sd.charAt(itt) + "'=" + (sd.charAt(itt)));
	        itt++;
	    }
		char ch='H';
	    if (ch >= 'A' && ch <= 'F')
	        System.out.println("i=" + itt + " and n=" + n);
	    if (ch >= 'A')
	        System.out.println("i=" + itt + " and n=" + n);
		
		Set <String> noDup=new HashSet<String>(hhh);
		
		if(noDup.size()<hhh.size()){
			System.out.println("There is duplicate member.");
		}

		//loop a Map
		for (Map.Entry<Integer, String> generalMapEntry : myTestMap.entrySet()) {
			System.out.println("Key : " + generalMapEntry.getKey() + " Value : " + generalMapEntry.getValue());
		}

		//Java 8 only, forEach and Lambda
		myTestMap.forEach((k,v)->System.out.println("Key : " + k + " Value : " + v));
		
		myTestMap.forEach((k,v)->hhh.add(v));
		myTestMap.forEach((k,v)->hshh.add(k));
		myTestMap.forEach((KW,Value)->System.out.println("KeyWord=" + KW + ", Value=" + Value));
		myTestMap.values();
		List<String> list = new ArrayList<String>();
		list.add("Text 1");
		list.add("Text 2");
		list.add("Text 3");

		
		for(int ll:myTestMap.keySet())
		{
			System.out.println("KeyWord=" + ll + ", Value=" + myTestMap.get(ll));
		}
		
		
		System.out.println("#1 normal for loop");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		System.out.println("#2 advance for loop");
		for (String temp : list) {
			System.out.println(temp);
		}

		System.out.println("#3 while loop");
		int j = 0;
		while (list.size() > j) {
			System.out.println(list.get(j));
			j++;
		}

		System.out.println("#4 iterator");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		
		//////invertion ~18
	    int eighteen = 18;
	    String eighteenBinary = Integer.toBinaryString(eighteen);
	    int inverted =~eighteen;
	    String invertedBinary = Integer.toBinaryString(inverted);
	  
	    System.out.println("18 in binary: " + eighteenBinary);
	    System.out.println("~18 in binary: " + invertedBinary);
	    
	    //////>>>>> or >>
//	    The shift operators include left shift <<, signed right shift >>, and unsigned right shift >>>.
//
//	    The value of n>>s is n right-shifted s bit positions with sign-extension.
//
//	    The value of n>>>s is n right-shifted s bit positions with zero-extension.
	    System.out.println( "'-1'=" + Integer.toBinaryString(-1));
	    // prints "11111111111111111111111111111111"
	    System.out.println("-1 >> 16=" + Integer.toBinaryString(-1 >> 16));
	    // prints "11111111111111111111111111111111"
	    System.out.println("-1 >>> 16=" + Integer.toBinaryString(-1 >>> 16));
	    // prints "1111111111111111"


	System.out.println(Integer.toBinaryString(121));
	// prints "1111001"
	System.out.println(Integer.toBinaryString(121 >> 1));
	// prints "111100"
	System.out.println(Integer.toBinaryString(121 >>> 1));
	// prints "111100"
	    
	    
	int foo[] = {1,2,3,4,5,6,7,8,9,0};
	Iterable<Integer> fooBar = Ints.asList(foo);
	for(Integer i : fooBar) {
	    System.out.println(i);
	}
	
	
	for (int oo=0;oo<foo.length;oo++){
		System.out.println(foo[oo]);
	}
	
	
	String foo2[] = {"sd","aa","eew","	qw","wqr"};
	List<String> lList = Arrays.asList(foo2);
	
	for(String ih : lList) {
	    System.out.println(ih);
	}
	
	
	System.out.println("#1 iterator");
	Iterator<String> iterator2 = lList.iterator();
	while (iterator2.hasNext()) {
		System.out.println(iterator2.next());
	}
	
	
	
	// for loop
	System.out.println("#2 for");
	for (int i = 0; i < lList.size(); i++) {
		System.out.println(lList.get(i));
	}

	// for loop advance
	System.out.println("#3 for advance");
	for (String temp : lList) {
		System.out.println(temp);
	}

	// while loop
	System.out.println("#4 while");
	int jk = 0;
	while (jk < lList.size()) {
		System.out.println(lList.get(jk));
		jk++;
	}
	
	


	{
	      int ig[] = {1};
	      change_i(ig);
	      System.out.println(ig[0]);
	}


    
	}
	public static void change_i(int i[]) 
	{
	      int j[] = {2};
	      i = j;
	      System.out.println(i[0]);
	}
}
