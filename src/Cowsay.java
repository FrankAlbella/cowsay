// School project for COP3502
// University of Florida

import java.util.HashMap;

public class Cowsay {

	public static void main(String[] args) {
		// Variables
		// params: key is flag, value is the argument passed with the flag
		// hasParam: used in the loop to determine when to add to params
		HashMap<String, String> params = new HashMap<String, String>();
		Boolean hasParam = false;

		// Loop through each argument 
		for(int i = 0; i < args.length; i++) {
			// '-' character indicates a flag
			// only check first argument
			if(i==0 && args[i].startsWith("-")) {
				// append the flag to params since
				// the loop will not run again
				if(args.length == 1)
					params.put(args[i], "");

				hasParam = true;
			}
			else if(!hasParam) {
				// Add "sentence" key so we can append to it
				// if this isn't then it will return null
				if(!params.containsKey("sentence"))
					params.put("sentence", "");
				
				// Add to put previous value + new value
				// in "sentence" key
				params.put("sentence", params.get("sentence") + args[i] + " ");
			}
			else {
				// If an argument was found on the previous loop
				// get the flag from the last loop as the key
				// and the current value of arg as the value
				params.put(args[i-1], args[i]);
				hasParam = false;
			}

		}

		// If there is a "-l" flag then 
		// only print out available cows
		if(params.containsKey("-l")) {
				listCows(HeiferGenerator.getCows());
				listCows(HeiferGenerator.getFileCows());
		}
		else if(params.containsKey("sentence")) {
			// Set default value for cowName incase none is given
			String cowName = "heifer";
			Cow cow;
	
			// if "-n" (cow name) flag was passed
			// then find the file cow with the given name
			if(params.containsKey("-n")) {
				cowName = params.get("-n").toString();
				cow = findCow(cowName, HeiferGenerator.getCows());
			}
			// if "-f" (file cow name) flag was passed
			// then find the file cow with the given name
			else if(params.containsKey("-f")) {
				cowName = params.get("-f").toString();
				cow = findCow(cowName, HeiferGenerator.getFileCows());
			}
			// if no cow flag was passed
			// then get the default "heifer" cow
			else 
				cow = findCow(cowName, HeiferGenerator.getCows());
			
			
			if(cow.getName() == "invalid") 
				System.out.println("Could not find " + cowName + " cow!");
			else {
				System.out.println(params.get("sentence"));
				System.out.println(cow.getImage());
			}

			if(cow instanceof Dragon) {
				if(((Dragon)cow).canBreatheFire())
					System.out.println("This  dragon  can  breathe  fire.");
				else
					System.out.println("This  dragon  cannot  breathe  fire.");
			}
		}


	}
	
	// Displays the available cows in the given array
	// Used for the "-l" flag
	// cows: array of Cow objects to display the names of
	private static void listCows(Cow[] cows) {
		if(cows[0] instanceof FileCow)
			System.out.print("File cows available: ");
		else
			System.out.print("Regular cows available: ");
		
		for(Cow cow : cows) {
			System.out.print(cow.getName() + " ");
		}
		
		System.out.println();
	}

	// Matches the name variable with a cow in the array with the same name
	// name: name to find
	// cows: array of Cow objects to search in
	// returns: Matching Cow object in the array if found
	// 	new cow object with the name of "invalid" if not
	private static Cow findCow(String name, Cow[] cows) {
		// Loop through the given array of cows
		// if cow.getName() matches the given name
		// return it
		for(Cow cow : cows) {
			if(cow.getName().equals(name)){
				return cow;
			}
		}
		
		// Create a new Cow instance of invalid
		// if no matches are found
		return new Cow("invalid");
	}
}
