package autom.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class Test2 {
	
	    public static void main(String[] args)
	        // Just for the ease of a throwaway test. Don't
	        // do this normally!
	        throws Exception
	    {
	    	
	    	
	    	  ArrayList<String> al=new ArrayList<String>();  
	    	  al.add("Ravi");  
	    	  al.add("Vijay");  
	    	  al.add("Ajay");  
	    	  ArrayList<String> al2=new ArrayList<String>();  
	    	  al2.add("Ravi");  
	    	  al2.add("Hanumat");  
	    	  al.retainAll(al2);  
	    	  System.out.println("iterating the elements after retaining the elements of al2...");  
	    	  Iterator itr=al.iterator();  
	    	  while(itr.hasNext()){  
	    	   System.out.println(itr.next());  
	    	  }  
	    	
			int i = 0;
			boolean to = true;
			boolean f = false, b;
			b = ( ((i++) == 0));
			b = (f || ((i+=2) > 0));
			System.out.println(14 ^ 23);
	    	
			boolean x = true;
		int a;
			if(x) a = x ? 1: 2;
			else a = x ? 3: 4;
			System.out.println(a);
			
			
			int xw;
			xw = -3 >> 1;
			xw = xw >>> 2;
			xw = xw << 1;
		 	System.out.println(xw);
			
//		 	int iy=Array{1,2,3};
//		 	int []iy[]=Array{1,2,3,4,5};
//			int iw = -1;
//			iw = iw >> 1;	 
//	 		System.out.println(iw);
		 	
		 	LinkedList ll = new LinkedList();
		 	ll.add(3);
		 	ll.add(2);
		 	ll.add(1);
		 	ll.add(4);
		 	ll.add(5);
		 	ll.add(6);
		 	ll.add(6);

		 	Iterator iter2 = ll.iterator();
		 	while(iter2.hasNext()){
		 	System.out.println(iter2.next());
		 	}
		 	
	        Other t = new Other();
	        t.setStr("hi");
	        Field field = Other.class.getDeclaredField("str");
	        field.setAccessible(true);
	        Object value = field.get(t);
	        System.out.println(value);
	        
//	        Field field2 = getField(Class.forName("Other"),"str");
//	        System.out.println(field2.toString());
	        
	        
	        
	        
	        Counter c1=new Counter();  
	        Counter c2=new Counter();  
	        Counter c3=new Counter();  

	    }
	    
	    
	    
	    @SuppressWarnings("unused")
		private static Field getField(Class<?> cls, String fieldName) {
	        for (Class<?> c = cls; c != null; c = c.getSuperclass()) {
	            try {
	                final Field field = c.getDeclaredField(fieldName);
	                field.setAccessible(true);
	                return field;
	            } catch (final NoSuchFieldException e) {
	                // Try parent
	            } catch (Exception e) {
	                throw new IllegalArgumentException(
	                        "Cannot access field " + cls.getName() + "." + fieldName, e);
	            }
	        }
	        throw new IllegalArgumentException(
	                "Cannot find field " + cls.getName() + "." + fieldName);
	    }
	}

class Counter{  
	static int count=0;//will get memory when instance is created  
  
	Counter(){ 
			count++;  
			System.out.println(count);  
			}
	}
