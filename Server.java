import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/*
 * This class is the Server class for Problem 1 of the SE 320 Final Assignment
 * It opens a connection with port 8000, to the Client class, 
 * and receives values for weight and height from the Client class. 
 * It then computes the BMI given those values, and displays the final BMI value.
 * 
 * To have these classes work properly, a user must run Server.java first, then, while Server.java
 * is running, run Client.java
 */
public class Server{
	
	public static void main(String[] args) {
		
		new Server();
	}
	
	public Server() {
		
		try {
			
			ServerSocket serverSocket = new ServerSocket(8000);											//Server socket created for port 8000
			System.out.println("Server started at " + new Date() + "\n");
			
			Socket socket = serverSocket.accept();
			
			ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());				//Object input stream from the Client
			
			while(true) {
				
				try {
					BMI bmiReceived = (BMI) inputFromClient.readObject();									//reads a BMI object, which contains the variables needed, from Client

					double bmi = bmiReceived.getWeight() / (bmiReceived.getHeight() * bmiReceived.getHeight());						//BMI calculated
					
					System.out.println("Weight received from client: " + bmiReceived.getWeight() + " kilograms\n");
					System.out.println("Height received from client: " + bmiReceived.getHeight() + " meters\n");
					
					System.out.println("BMI found: " + bmi + " kg/m^2\n");				
					
					socket.close();																			//Socket closed so that Server.java and Client.java can be run multiple times
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