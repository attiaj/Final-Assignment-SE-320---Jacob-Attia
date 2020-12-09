
/*
 * This class implements the generic linearSearch method outlined to be completed
 * in Problem 3 of the SE 320 Final Assignment.
 * 
 * Test cases are included for both a list of integers and a list of strings.
 */
public class Question3{
	
	public static void main(String[] args) {
		
		Integer[] list = {3, 4, 5, 1, -3, -5, -1};
		String[] list2 = {"one", "two", "three", "four", "five"};
		
		System.out.println(linearSearch(list, 2));
		System.out.println(linearSearch(list, 5));
																			//Test cases included for a list of integers and a list of strings
		System.out.println(linearSearch(list2, "two"));
		System.out.println(linearSearch(list2, "four"));
		System.out.println(linearSearch(list2, "none"));
	}
	
	public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
		
		for (int firstIndexFound = 0; firstIndexFound < list.length; firstIndexFound++) {		//Iterate across the list, and return the first index found
			
			if (list[firstIndexFound].equals(key)) {						//.equals used here instead of ==, as there is a possibility of comparing strings
				return firstIndexFound;
			}
		}
		return -1;															//Returns -1 if the list did not contain the key
	}
}