import java.io.Serializable;

/*
 * This class represents an Object, with variables that will eventually be accessed by the Server.
 * 
 * The object itself is created within a Client, the variables initialized with inputs from the user, then the object
 * is sent through a stream to the Server. The server uses the variables to calculate the BMI.
 * 
 */
public class BMI implements Serializable{	
	
	private double weightInKilograms;
	
	private double heightInMeters;
	
	public void setWeight(double weight) {
		
		this.weightInKilograms = weight;
	}
	
	public void setHeight(double height) {
		
		this.heightInMeters = height;
	}
	
	public double getWeight() {
		
		return weightInKilograms;
	}
	
	public double getHeight() {
		
		return heightInMeters;
	}
}