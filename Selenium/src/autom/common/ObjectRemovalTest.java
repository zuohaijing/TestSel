package autom.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;




/**

  * Java program to demonstrate how to remove object form List and differnece

  * between Iterator's remove() method and Colection's remove() method in Java

  *

  * @author http://javarevisited.blogspot.com

 */

public class ObjectRemovalTest {

    public static void main(String args[]) {
       List <Object> markets = new ArrayList<Object>();
       StockExchange TSE = new StockExchange(){
            @Override
            public boolean isClosed() {
                return true;
            }
       };

      

       StockExchange HKSE = new StockExchange(){
            @Override
            public boolean isClosed() {
                return true;
            }
       };
       StockExchange NYSE = new StockExchange(){
            @Override
            public boolean isClosed() {
                return false;
            }
       }; 
       markets.add(TSE);
       markets.add(HKSE);
       markets.add(NYSE);
//       Iterator<Object> itr = markets.iterator();
//       while(itr.hasNext()){
//           StockExchange exchange = (StockExchange) itr.next();
//           if(exchange.isClosed()){
//               try {
//               markets.remove(exchange); //Use itr.remove() method
//
//				TimeUnit.SECONDS.sleep(4);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//           }
//       }
       
       
//       
//       ListIterator<Object> itr2 = markets.listIterator();
//       while(itr2.hasNext()){
//           StockExchange exchange = (StockExchange) itr2.next();
//           if(exchange.isClosed()){
//               try {
//               markets.remove(exchange); //Use itr.remove() method
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//           }
//       }
//       
       
       int limit = markets.size();
       for(int i=0; i<limit; i++){
    	   System.out.println(i);
    	   
    	   markets.remove(limit-i-1);
       }

            

    }     

   

}




interface StockExchange{

    public boolean isClosed();
    }


