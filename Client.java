import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/*
 * This class is the Client class for Problems 1 and 2 of the SE 320 Final Assignment
 * It opens a connection with port 8000, to the Server class, reads values for weight
 * and height from the user, and sends those values to the Server class, which then computes the 
 * BMI given those values, and displays the final BMI value.
 * 
 * For Problem 1, run Server.java first, then run Client.java
 * Running Client as is, will execute its functionality without any extra threads.
 * 
 * For Problem 2, run Server2.java first, then run Client.java
 * Server2 is set to use the dummy constructor to create multiple threads, for multiple clients.
 * It then uses uses the .start method of Thread, which will execute the run() method shown below, 
 * which then executes a Client normally. This allows multiple Clients to run at the same time. 
 *
 */
public class Client extends Thread{
	
	public static void main (String[] args) {
		
		new Client();
	}
	
	public Client (boolean isThreads) {
		
	}
	
	public Client() {
		
		try {
			
			Socket socket = new Socket("localhost", 8000);												//Socket is created to port 8000
						
			ObjectOutputStream outputToServer = new ObjectOutputStream(socket.getOutputStream());			//Output stream to Server
			
			while(true) {
				Scanner scanner = new Scanner(System.in);
				
				BMI bmiToSend = new BMI();
				
				System.out.println("Please enter weight (in kilograms): ");
				
				while (!scanner.hasNextDouble()) {
					System.out.println("Weight entered is an invalid value (must be a double)");		//Error checking to make sure a valid weight value is inputted
					System.out.println("Please try again: ");
					scanner.nextLine();
				}
				
				bmiToSend.setWeight(scanner.nextDouble());
				System.out.println("Weight is " + bmiToSend.getWeight() + " kilograms");
				
				System.out.println("Please enter height (in meters): ");
				
				while (!scanner.hasNextDouble()) {
					System.out.println("Height entered is an invalid value (must be a double)");		//Error checking to make sure a valid height value is inputted
					System.out.println("Please try again: ");
					scanner.nextLine();
				}
				
				bmiToSend.setHeight(scanner.nextDouble());
				System.out.println("Height is " + bmiToSend.getHeight() + " meters");							//Height value is sent to Server
				
				outputToServer.writeObject(bmiToSend);
				
				socket.close();																			//Socket is closed so that the Server.java and Client.java can be successfully run multiple times
				System.out.println("Client Socket Closed");
				break;
			}
		}
		catch(IOException ex) {
			System.err.println(ex.toString() + "\n");
		}
	}
	public void run() {
		new Client();
	}
}