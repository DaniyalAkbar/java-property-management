package package_main;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main_20321273 {

	static ArrayList<String> clients = new ArrayList<String>();
	static ArrayList<String> expenses = new ArrayList<String>();
	static ArrayList<String> properties = new ArrayList<String>();
	static ArrayList<String> rents = new ArrayList<String>();
	static ArrayList<String> summary = new ArrayList<String>();
	static int count = 1;
	static int prop_found = 0;
	static String addr = "";

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
			
			//PRINTING MAIN MENU
			
			System.out.println("\n");
			System.out.println("1. Record Rent Collection");
			System.out.println("2. Record Expense");
			System.out.println("3. Generate Portfolio Report");
			System.out.println("4. Save");
			System.out.println("5. Exit Program\n");
			System.out.println("Please Select an Option From the Menu...");
			int option = sc.nextInt();
			if(option<1 || option>5) {
				System.out.println("Please Select an Option That is Present in the Menu\n\n");
				continue;
			}
			
			
			switch (option) {

			case 1:
				
				ArrayList<String> result = new ArrayList<String>();
				summary.clear();
				Client_20321273 class_client = null;
				System.out.println("\nPlease Enter a Property's Address to Search: ");
				count = 1;
				prop_found = 0;
				String addr = sc.next().trim();
				for (String p : properties) {				// FETHCING ALL THE LIST OF PREPOERTY MATCHING THE ADDRESS FROM USER INPUT
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
				
				
				System.out.println("\nPlease Select an Address: ");
				float week_rent_amnt = 0;
				
				while (true) {
					try {
						String prop_owner = "";
						String property = result.get(sc.nextInt() - 1);	//FETCHING SELECTED ADDRESS THROUGH USER INPUT AND "-1" FOR GETTING THE INDEX OF THE ARRAY
						String[] prop_array = property.split(",");
						week_rent_amnt = Float.parseFloat(prop_array[prop_array.length - 3]);	//FETCHING THE WEEKLY RENT AMOUNT OF THE SELECTED PROPERTY
						for (String c : clients) {						//FETCHING THE PROPERTY OWNERS FULL NAME FROM THE ARRAYLIST
							String[] client = c.split(",");
							if (client[0].equals(prop_array[prop_array.length - 1])) {
								prop_owner = client[1];
							}
						}

						
						class_client = new Client_20321273(property, prop_owner);	//DISPLAYING PROPERTY ADDRESS, WEEKLY RENT CHARGED AND OWNERS FULL NAME
						summary.add("PROPERTY ADDRESS: "+class_client.getAdd());	//ADDING PROPERTY ADDRESS TO THE SUMMARY ARRAYLIST
						summary.add("OWNER'S NAME: "+class_client.getFullName());	//ADDING PROPERTY OWNER'S FULL NAME TO THE SUMMARY ARRAYLIST
						summary.add("MONETARY AMOUNT: "+class_client.getWeekly_rent());	//ADDING PROPERTY WEEKLY RENT AMOUNT TO THE SUMMARY ARRAYLIST
						
						
						break;
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Please Select a Valid Address");
						continue;
					}catch(InputMismatchException i) {
						System.out.println("Can Only Accept Integers!");
						continue;
					}
				}
				
				
				//FETCHING THE NUMBER OF WEEKS OF RENT COLLECTED THROUGH USER INPUT
				while(true) {
					try {
						System.out.println("\nHow many weeks of rent was collected for the chosen property?");
						int weeks = sc.nextInt();
						if(weeks<1) { 
							System.out.println("Please Enter a Valid Week Number...");
							continue;
						}
						
						float total_rent = new Client_20321273().calculate_week_rent(week_rent_amnt, weeks);			//FETCHING THE AMOUNT OF RENT COLLECTED FOR THE GIVEN NUMBER OF WEEKS
						//System.out.println("\nThe Total Weeks Rent for the Selected Property is: "+total_rent+"\n\n");
						
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
						LocalDateTime now = LocalDateTime.now();  
						summary.add("NUMBER OF WEEKS RENT COLLECTED: "+weeks);	//ADDING NUMBER OF WEEKS TO THE SUMMARY ARRAYLIST
						summary.add("TOTAL RENT COLLECTED: "+total_rent);	//ADDING TOTAL RENT COLLECTED TO THE SUMMARY ARRAYLIST
						summary.add("LAST RENT COLLECTION: "+dtf.format(now));	//ADDING TODAYS DATE TO THE SUMMARY ARRAYLIST
						break;
					}catch (Exception e) {
						System.out.println("Please Enter an Integer");
					}
				}
				

				//PRINTING SUMMARY
				System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
				System.out.println("\t\tSUMMARY");
				for (String s : summary) {				// DISPLAYING EACH ITEM IN SUMMARY ARRAYLIST
					System.out.println(s);
				}
				System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
				break;

				
				
				
				
				
				
				
				
				
				
				
			case 2:
				
				ArrayList<String> output = new ArrayList<String>();
				summary.clear();
				System.out.println("\nPlease Enter a Property's Address to Search: ");
				count = 1;
				prop_found = 0;
				addr = sc.next().trim();
				for (String p : properties) {				// FETHCING ALL THE LIST OF PREPOERTY MATCHING THE ADDRESS FROM USER INPUT
					if (p.toLowerCase().contains(addr.toLowerCase())) {
						output.add(count + ") @" + p);
						count++;
						prop_found = 1;
					}
				}
				if(prop_found == 0) {
					System.out.println("No Such Property Exist in Records\n\n\n");
					break;
				}
				
				
				// DISPLAYING ALL THE SEARCHED PROPERTIES
				result_prop(output);
				
				
				System.out.println("\nPlease Select an Address: ");
				float week_rent_amount = 0;
				
				while (true) {
					try {
						String prop_owner = "";
						String property = output.get(sc.nextInt() - 1);	//FETCHING SELECTED ADDRESS THROUGH USER INPUT AND "-1" FOR GETTING THE INDEX OF THE ARRAY
						String[] prop_array = property.split(",");
						week_rent_amount = Float.parseFloat(prop_array[prop_array.length - 3]);	//FETCHING THE WEEKLY RENT AMOUNT OF THE SELECTED PROPERTY
						for (String c : clients) {						//FETCHING THE PROPERTY OWNERS FULL NAME FROM THE ARRAYLIST
							String[] client = c.split(",");
							if (client[0].equals(prop_array[prop_array.length - 1])) {
								prop_owner = client[1];
							}
						}

						
						class_client = new Client_20321273(property, prop_owner);	//DISPLAYING PROPERTY ADDRESS, WEEKLY RENT CHARGED AND OWNERS FULL NAME
						summary.add("PROPERTY ADDRESS: "+class_client.getAdd());	//ADDING PROPERTY ADDRESS TO THE SUMMARY ARRAYLIST
						summary.add("OWNER'S NAME: "+class_client.getFullName());	//ADDING PROPERTY OWNER'S FULL NAME TO THE SUMMARY ARRAYLIST
						
						break;
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Please Select a Valid Address");
						continue;
					}catch(InputMismatchException i) {
						System.out.println("Can Only Accept Integers!");
						continue;
					}
				}
				
				
				System.out.println("\nWould You like to Record an Expense? Press 'Y' for yes and 'N' for no: ");
				String ch = sc.next();
				if(!ch.equals("Y") && !ch.equals("y")) { break; } 		//ANYTHING BESIDE 'Y' or 'y' IF CHOSEN, WOULD BE CONSIDERED A NO
				
				while(true) {
					try {
						System.out.println("\nPlease Enter an Amount");
						float amt = sc.nextFloat();
						if(amt<0) {									//VALIDATING IF THE AMOUNT IS NOT LESS THAN 0
							System.out.println("The Amount Should be Greater Than 0. Please Enter Again");
							continue;
						}
						String desc = desc();						//FETCHING DESCRIPTION FROM A FUNCTION + VALIDATION
						
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
						LocalDateTime now = LocalDateTime.now();  
						
						summary.add("EXPENSE AMOUNT: "+amt);	//ADDING EXPENSE AMOUNT TO THE SUMMARY ARRAYLIST
						summary.add("EXPENSE DESCRIPTION: "+desc);	//ADDING EXPENSE DESCRIPTION TO THE SUMMARY ARRAYLIST
						summary.add("LAST EXPENSE ADDED: "+dtf.format(now));	//ADDING TODAYS DATE TO THE SUMMARY ARRAYLIST
						
						break;
					}catch(InputMismatchException i) {
						System.out.println("Can Only Accept Integers!");
						continue;
					}catch (Exception e) {
						System.out.println("Please Enter an Amount in Numbers Only");
						continue;
					}
				}

				
				
				//PRINTING SUMMARY
				System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
				System.out.println("\t\tSUMMARY");
				for (String s : summary) {				// DISPLAYING EACH ITEM IN SUMMARY ARRAYLIST
					System.out.println(s);
				}
				System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
				
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

	
	
	
	
	
	
	// FETCHING THE ADDRESS FROM THE CLIENTS TEXT FILE TO DISPLAY TO USER
	public static void result_prop(ArrayList<String> result) {
		if (!result.isEmpty()) {
			System.out.println("\n=============================================================================");
			for (String p : result) {
				String[] x = p.split("@");
				System.out.println(x[0] + " " + x[1]);
			}
			System.out.println("=============================================================================\n");
		} else {
			System.out.println("No Records Found!");
		}
	}
	
	
	
	//FETCHING EXPENSE DESCRIPTION + VALIDATION
	public static String desc() {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("\nPlease Enter a Description of the Expense");
			String desc = sc.next();
			if(desc.isEmpty() || desc.equals("") || desc.equals(null) || desc.length() < 1 ) {
				System.out.println("Description is not Appropriate");
				continue;
			}
			return desc;
		}
	}

}












