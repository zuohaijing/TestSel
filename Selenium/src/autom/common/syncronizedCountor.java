package autom.common;

public class syncronizedCountor {
	String count1;//will get memory only once and retain its value  
    int iii=0;
    String kkk;
	syncronizedCountor(){  
//			count++;
			synchronized (kkk) {
				iii=iii+1;
			}
			
			System.out.println(iii);  
		}
  
	
//	syncronizedCountor111(){  
////		count++;
//		synchronized (kkk) {
//			iii=iii+1;
//		}
//		
//		System.out.println(iii);  
//	}
		syncronizedCountor(String abc, int bbb){
			this.iii=bbb;
			this.kkk=abc;
//			syncronizedCountor();
		}
		
		public static void main(String args[]){  
		
			   String s1="Sa";  
			     String s2="Sa";  
			     String s3="Ra";  
			     System.out.println(s1.compareTo(s2));//0  
			     System.out.println(s1.compareTo(s3));//1(because s1>s3)  

			
			syncronizedCountor c1=new syncronizedCountor();  
			syncronizedCountor c2=new syncronizedCountor();  
			syncronizedCountor c3=new syncronizedCountor(); 
		}  
	}  
