package package_main;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_20321273 {

	static ArrayList<String> clients = new ArrayList<String>();
	static ArrayList<String> expenses = new ArrayList<String>();
	static ArrayList<String> properties = new ArrayList<String>();
	static ArrayList<String> rents = new ArrayList<String>();
	

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		// READING CLIENT TEXT FILE
		clients = readFile("src/package_main/txtFiles/clients.txt");
		if (!clients.isEmpty() && clients.get(0).equals("no file found")) {
			System.out.println(
					"No File Found. Would You Want to Type The Name of the File You Would Want Search For Instead?\nIf So Then Please Type: ");
			String newFileName = sc.next();
			clients = readFile("src/package_main/txtFiles/" + newFileName + ".txt");
			if (clients.get(0).equals("no file found")) {
				System.out.println("Still can't find the file. Creating a file named clients.txt");
				File file = new File("src/package_main/txtFiles/clients.txt");
				file.createNewFile();
			}
		}

		// READING PROPERTY TEXT FILE
		properties = readFile("src/package_main/txtFiles/properties.txt");
		if (!properties.isEmpty() && properties.get(0).equals("no file found")) {
			System.out.println(
					"No File Found. Would You Want to Type The Name of the File You Would Want Search For Instead?\nIf So Then Please Type: ");
			String newFileName = sc.next();
			properties = readFile("src/package_main/txtFiles/" + newFileName + ".txt");
			if (properties.get(0).equals("no file found")) {
				System.out.println("Still can't find the file. Creating a file named properties.txt");
				File file = new File("src/package_main/txtFiles/properties.txt");
				file.createNewFile();
			}
		}
		/*
		 * // READING EXPENSE TEXT FILE ArrayList<String> expenses = new
		 * ArrayList<String>(); expenses =
		 * readFile("src/package_main/txtFiles/expenses.txt"); if (!expenses.isEmpty()
		 * && expenses.get(0).equals("no file found")) { System.out.println(
		 * "No File Found. Would You Want to Type The Name of the File You Would Want Search For Instead?\nIf So Then Please Type: "
		 * ); String newFileName = sc.next(); expenses =
		 * readFile("src/package_main/txtFiles/" + newFileName + ".txt"); if
		 * (expenses.get(0).equals("no file found")) { System.out.
		 * println("Still can't find the file. Creating a file named expenses.txt");
		 * File file = new File("src/package_main/txtFiles/clients.txt");
		 * file.createNewFile(); } }
		 * 
		 * // READING RENT TEXT FILE ArrayList<String> rent = new ArrayList<String>();
		 * rent = readFile("src/package_main/txtFiles/rents.txt"); if (!rent.isEmpty()
		 * && rent.get(0).equals("no file found")) { System.out.println(
		 * "No File Found. Would You Want to Type The Name of the File You Would Want Search For Instead?\nIf So Then Please Type: "
		 * ); String newFileName = sc.next(); rent =
		 * readFile("src/package_main/txtFiles/" + newFileName + ".txt"); if
		 * (rent.get(0).equals("no file found")) { System.out.
		 * println("Still can't find the file. Creating a file named rents.txt"); File
		 * file = new File("src/package_main/txtFiles/clients.txt");
		 * file.createNewFile(); } }
		 * 
		 * 
		 */
		while (true) {

			System.out.println("1. Record Rent Collection");
			System.out.println("2. Record Expense");
			System.out.println("3. Generate Portfolio Report");
			System.out.println("4. Save");
			System.out.println("5. Exit Program\n");
			System.out.println("Please Select an Option From the Menu...");
			int option = sc.nextInt();
			if(option<1 || option>5) {
				System.out.println("Please Select an Option That is Present in the Menu\n\n\n");
				continue;
			}
			
			
			switch (option) {

			case 1:
				ArrayList<String> result = new ArrayList<String>();
					System.out.println("Please Enter a Property's Address to Search: ");
					int count = 1;
					int prop_found = 0;
					String addr = sc.next().trim();
					for (String p : properties) {
						if (p.toLowerCase().contains(addr.toLowerCase())) {
							result.add(count + ") @" + p);
							count++;
							prop_found = 1;
						}
					}
					if(prop_found == 0) {
						System.out.println("No Such Property Exist in Records\n\n\n");
						break;
					}
				
				
				// DISPLAYING ALL THE SEARCHED PROPERTIES
				result_prop(result);
				
				System.out.println("Please Select an Address: ");

				while (true) {
					try {
						String prop_owner = "";
						String property = result.get(sc.nextInt() - 1);
						String[] client_id = property.split(",");
						for (String c : clients) {
							String[] client = c.split(",");
							if (client[0].equals(client_id[client_id.length - 1])) {
								prop_owner = client[1];
							}
						}

						Client_20321273 c = new Client_20321273(property, prop_owner);
						break;
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Please Select a Valid Address");
					}
				}
				
				int weeks = 0;
				while(true) {
					try {
						System.out.println("\n\nHow many weeks of rent was collected for the chosen property?");
						weeks = sc.nextInt();				
						break;
					}catch (Exception e) {
						System.out.println("Please Enter an Integer");
					}
				}

				
				break;

			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				return;
			}
		}

		// END OF MAIN FUNCTION
	}

	// READING FROM FILES FUNCTION
	public static ArrayList<String> readFile(String fileName) {
		ArrayList<String> al = new ArrayList<String>();
		try {
			File myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				al.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			if (e.toString().contains("No such file or directory")) {
				al.add("no file found");
				return al;
			}
		}
		return al;
	}

	// RECORD RENT COLLECTION
	public static void result_prop(ArrayList<String> result) {
		if (!result.isEmpty()) {
			for (String p : result) {
				String[] x = p.split("@");
				System.out.println(x[0] + " " + x[1]);
			}
		} else {
			System.out.println("No Records Found!");
		}
	}

	
}

