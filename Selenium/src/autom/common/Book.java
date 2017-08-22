package autom.common;
import java.util.*;  

class Book {  
int id;  
String name,author,publisher;  
int quantity;  
public Book(int id, String name, String author, String publisher, int quantity) {  
    this.id = id;  
    this.name = name;  
    this.author = author;  
    this.publisher = publisher;  
    this.quantity = quantity;  
}
public Book() { //Nothing
}

public int RecursiveExp(int x, int n) { 
	//First base case 
	if (n == 0) { return 1; } 
	//Second base case 
	if (n == 1) { return x; } 
	//Even values of (n) 
	if (n % 2 == 0) { 
		int y = RecursiveExp(x, n / 2); 
		return y * y; 
	} 
	//Odd values of (n) 
	else { 
		int y = RecursiveExp(x, n - 1); 
		return x * y; 
	} 
	 //Main class class Program { //Main static void Main(string[] args) { 
}
public static void main(String[] args) {  
    HashSet<Book> set=new HashSet<Book>();  
    //Creating Books  
    Book b1=new Book(101,"Let us C","Yashwant Kanetkar","BPB",8);  
    Book b2=new Book(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);  
    Book b3=new Book(103,"Operating System","Galvin","Wiley",6);  
    //Adding Books to HashSet  
    set.add(b1);  
    set.add(b2);  
    set.add(b3);  
    //Traversing HashSet  
    for(Book b:set){  
    System.out.println(b.id+" "+b.name+" "+b.author+" "+b.publisher+" "+b.quantity);  
    }
  //Create a test object 
    Book tst = new Book(); 
    //Examples 
//    System.out.println(tst.RecursiveExp(2, 0)); 
//    System.out.println(tst.RecursiveExp(2, 1)); 
//    System.out.println(tst.RecursiveExp(2, 3)); 
    System.out.println(tst.RecursiveExp(3, 5));
  //Constructor public Test() { //Nothing } public int RecursiveExp(int x, int n) { //First base case if (n == 0) { return 1; } //Second base case if (n == 1) { return x; } //Even values of (n) if (n % 2 == 0) { int y = RecursiveExp(x, n / 2); return y * y; } //Odd values of (n) else { int y = RecursiveExp(x, n - 1); return x * y; } } } //Main class class Program { //Main static void Main(string[] args) { //Create a test object Test tst = new Test(); //Examples Console.Out.WriteLine(tst.RecursiveExp(2, 0)); Console.Out.WriteLine(tst.RecursiveExp(2, 1)); Console.Out.WriteLine(tst.RecursiveExp(2, 3)); Console.Out.WriteLine(tst.RecursiveExp(2, 4)); 
    
    
}  
}  