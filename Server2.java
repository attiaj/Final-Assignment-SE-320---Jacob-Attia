import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/*
 * This class is the Server class for Problem 2 of the SE 320 Final Assignment
 * It opens a connection with port 8000, creates multiple threads of the Client class, 
 * and receives BMI Objects, that contain values for weight and height, from those threads. 
 * It then computes the BMI given those values, and displays the final BMI values.
 * 
 * The Server2 class creates the Client threads within itself, so a user only needs to run
 * the Server2 class, instead of running both, like in Problem 1. 
 * 
 * If a user runs into a BindException error when trying to run this class multiple times, then quick fix
 * is to manually terminate all processes to port 8000. One can do this by downloading TCPView to view port connections.
 * 
 * Please note that the multi-threading functionality of this class does not work perfectly.
 * What generally happens, is that the threads are created and started successfully, values for
 * weight and height are then inputted successfully, but the BMI value, for some reason, only gets calculated
 * and displayed on the console for one of the threads, not all of them.
 * 
 * I spent a pretty long time trying to find a solution to this problem, watching many YouTube tutorials, reading
 * the bonus chapters of the SE 320 Java textbook, and reading through the class Threading slides multiple times,
 * but unfortunately, I did not end up fixing the problem before the deadline for this assignment. I would hope that
 * I get partial credit for this problem, since the threading functionality still works for most of the requirements.
 */
public class Server2{
	
	public static void main(String[] args) {
		
		new Server2();
	}
	
	public Server2() {

		try {
			System.out.println("Server started at " + new Date() + "\n");
			
			Thread thread1 = new Client(true);
			Thread thread2 = new Client(true);									//Two threads created to run two different clients at the same time
			
			thread1.start();													
			if (thread1.isAlive()) {
				System.out.println("Thread 1 is currently running");
			}
			thread2.start();
			if(thread2.isAlive()) {
				System.out.println("Thread 2 is currently running\n");
			}
			ServerSocket serverSocket = new ServerSocket(8000);											//Server socket created for port 8000
			
			Socket socket = serverSocket.accept();
			
			ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());				//Input stream from the Client
			
			while(true) {
				
				try {
					
					BMI bmiReceived = (BMI) inputFromClient.readObject();

					double bmi = bmiReceived.getWeight() / (bmiReceived.getHeight() * bmiReceived.getHeight());						//BMI calculated
					
					System.out.println("\nWeight received from client: " + bmiReceived.getWeight() + " kilograms\n");
					System.out.println("Height received from client: " + bmiReceived.getHeight() + " meters\n");
					
					System.out.println("BMI found: " + bmi + " kg/m^2\n");				
					
					//socket.close();																			//Socket closed so that Server.java and Client.java can be run multiple times
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		catch(IOException ex) {
			System.err.println(ex);
		}
		
	}
}