import java.util.Scanner;
/** 
 * Static functions used to check the console input for validity
 * @author Shannon Foss 2017
 */
public class CheckInput {
	
	/**
	 * Checks that the inputted value is an integer.
	 * @return the valid input.
	 */
	public static int checkInt() {
		Scanner in = new Scanner( System.in );
		int input = 0;
		boolean valid = false;
		while( !valid ) {
			if( in.hasNextInt() ) {
				input = in.nextInt();
				valid = true;
			} else {
				in.next(); //clear invalid string
				System.out.println( "Invalid Input." );
			}
		}
		return input;
	}
	
	/**
	 * Checks that the inputted value is an integer and 
	 * within the specified range.
	 * @param low lower bound of the range.
	 * @param high upper bound of the range.
	 * @return the valid input.
	 */
	public static int checkIntRange( int low, int high ) {
		Scanner in = new Scanner( System.in );
		int input = 0;
		boolean valid = false;
		while( !valid ) {
			if( in.hasNextInt() ) {
				input = in.nextInt();
				if( input <= high && input >=low ) {
					valid = true;
				} else {
					System.out.println( "Invalid Range." );
				}
			} else {
				in.next(); //clear invalid string
				System.out.println( "Invalid Input." );
			}
		}
		return input;
	}
	
	/**
	 * Checks that the inputted value is a double.
	 * @return the valid input.
	 */
	public static double checkDouble() {
		Scanner in = new Scanner( System.in );
		double input = 0;
		boolean valid = false;
		while( !valid ) {
			if( in.hasNextDouble() ) {
				input = in.nextDouble();
				valid = true;
			} else {
				in.next(); //clear invalid string
				System.out.println( "Invalid Input." );
			}
		}
		return input;
	}
	
	/**
	 * Takes in a string from the user.
	 * @return the inputted String.
	 */
	public static String getString() {
		Scanner in = new Scanner( System.in );
		String input = in.nextLine();
		return input;
	}

	/**
	 * Takes in a yes/no from the user.
	 * @return 1 if yes, 0 if no.
	 */
	public static int getYesNo(){
		boolean valid = false;
		while( !valid ) {
			String s = getString();
			if( s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y") ) {
				return 1;
			} else if( s.equalsIgnoreCase("no") || s.equalsIgnoreCase("n") ) {
				return 0;
			} else {
				System.out.println( "Invalid Input" );
			}
		}
		return 0;
	}	
}
