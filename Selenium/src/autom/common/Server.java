package autom.common;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;


//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.server.Session;
import java.net.ServerSocket;




public class Server {
  @SuppressWarnings({ "deprecation" })
public static void main(String args[]) {
	ServerSocket echoServer = null;
    String line;
    DataInputStream is;
    PrintStream os;
    Socket clientSocket = null;

    /*
     * Open a server socket on port 2222. Note that we can't choose a port less
     * than 1023 if we are not privileged users (root).
     */
    try {
      echoServer = new ServerSocket(9090);
    } catch (IOException e) {
      System.out.println(e);
    }

    /*
     * Create a socket object from the ServerSocket to listen to and accept
     * connections. Open input and output streams.
     */
    System.out.println("The server started. To stop it press <CTRL><C>.");
    try {
      clientSocket = echoServer.accept();
      is = new DataInputStream(clientSocket.getInputStream());
      os = new PrintStream(clientSocket.getOutputStream());

      /* As long as we receive data, echo that data back to the client. */
      while (true) {
        line = is.readLine();
//        os.println("From server: " + line + "\n");
//        if(line.contains("test")){
//        	os.println("trigger test - " + line + "\n");
//        } else {
//        	os.println("From Server - " + line + "\n");
//        }
        
		switch (line)	{
	     case "test":
	    	 os.println("check notification");
	        break;
	     case "name":
	    	 os.println("From Server - " + line);
		    break;	        
	     case "quit":
	    	 os.println("stop");
	        break;
	     case "test passed":
	    	 os.println("From Server - acknowledged: test passed." + line );
	    	 TestActions.robotWait(1);
	    	 os.println("stop");
	        break;
	     case "css":
	    	 os.println("From Server - " + line);
	        break;
	     case "tagname":
	    	 os.println("From Server - " + line);
	        break;
	     case "class":
	    	 os.println("From Server - " + line );
	    	 break;
	     case "partiallinktext":
	    	 os.println("From Server - " + line );
	    	 break;
	     case "javascript":
	    	 os.println("From Server - " + line );
	    	 break;
	     case "jsid":
	    	 os.println("From Server - " + line );
	    	 break;
	     case "jsdom":
	    	 os.println("From Server - " + line );
	    	 break;
	     default: 
	    	 System.out.println("unknown method from client:\\n" + line);
		}       
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
