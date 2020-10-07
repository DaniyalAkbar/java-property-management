package package_main;

import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
import java.util.*;

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
		
		
		//FETCHING AND RECORDING ALL THE TEXT FILES DATA INTO ARRYALISTS
		
		Client_20321273 obj_client = new Client_20321273();
		clients = obj_client.fetchClients();
		
		Properties_20321273 obj_prop = new Properties_20321273();
		properties = obj_prop.fetchProperties();
		
		Expenses_20321273 obj_expense = new Expenses_20321273();
		expenses = obj_expense.fetchExpenses();
		
		Rents_20321273 obj_rents = new Rents_20321273();
		rents = obj_rents.fetchRents();
		
		Report_20321273 obj_report = new Report_20321273();

		
		
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
				
				ArrayList<String> result_1 = new ArrayList<String>();
				summary.clear();
				count = 1;
				prop_found = 0;
				Client_20321273 class_client = null;
				System.out.println("\nPlease Enter a Property's Address to Search: ");
				String addr = sc.next().trim();
				for (String p : properties) {				// FETHCING ALL THE LIST OF PREPOERTY MATCHING THE ADDRESS FROM USER INPUT
					if (p.toLowerCase().contains(addr.toLowerCase())) {
						result_1.add(count + ") @" + p);
						count++;
						prop_found = 1;
					}
				}
				if(prop_found == 0) {
					System.out.println("No Such Property Exist in Records\n\n\n");
					break;
				}
				
				
				// DISPLAYING ALL THE SEARCHED PROPERTIES
				result_prop(result_1);
				
				
				System.out.println("\nPlease Select an Address: ");
				float week_rent_amnt = 0;
				
				while (true) {
					try {
						String prop_owner = "";
						String property = result_1.get(sc.nextInt() - 1);	//FETCHING SELECTED ADDRESS THROUGH USER INPUT AND "-1" FOR GETTING THE INDEX OF THE ARRAY
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
				
				ArrayList<String> result_2 = new ArrayList<String>();
				summary.clear();
				System.out.println("\nPlease Enter a Property's Address to Search: ");
				count = 1;
				prop_found = 0;
				addr = sc.next().trim();
				for (String p : properties) {				// FETHCING ALL THE LIST OF PREPOERTY MATCHING THE ADDRESS FROM USER INPUT
					if (p.toLowerCase().contains(addr.toLowerCase())) {
						result_2.add(count + ") @" + p);
						count++;
						prop_found = 1;
					}
				}
				if(prop_found == 0) {
					System.out.println("No Such Property Exist in Records\n\n\n");
					break;
				}
				
				
				// DISPLAYING ALL THE SEARCHED PROPERTIES
				result_prop(result_2);
				
				
				System.out.println("\nPlease Select an Address: ");
				float week_rent_amount = 0;
				
				while (true) {
					try {
						String prop_owner = "";
						String property = result_2.get(sc.nextInt() - 1);	//FETCHING SELECTED ADDRESS THROUGH USER INPUT AND "-1" FOR GETTING THE INDEX OF THE ARRAY
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
				
				ArrayList<String> result_3 = new ArrayList<String>();
				System.out.println("Do You Want to View Reports for:\n1)All Clients\t\t2)Specific Client\t3)All Properties in a Specific Postal Code");
				ch = null;
				ch = sc.next();
				
				
				if(ch.equals("1")) {

				}
				
				else if(ch.equals("2")) {
					prop_found = 0;
					ArrayList<String> match_clients = new ArrayList<>();
					ArrayList<String> clients_ID = new ArrayList<>();
					ArrayList<String> clients_name = new ArrayList<>();
					System.out.println("Please Enter a Clients Name to Search");
					String nm = sc.next();
					for (String c : clients) {				// FETHCING ALL THE LIST OF CLIENTS MATCHING THE WORDS FROM USER INPUT
						if (c.toLowerCase().contains(nm.toLowerCase())) {
							match_clients.add(c);
							
							//FETCHING ITS CLIENT ID
							String[] temp = c.split(",");
							clients_ID.add(temp[0]);
							clients_name.add(temp[1]);
							prop_found = 1;
						}
					}
					if(prop_found == 0) {
						System.out.println("No Such Client Exist in Records\n\n\n");
						break;
					}
					
					System.out.println("\n\nThese are the Clients Name Found:");
					for(String n : clients_name) {
						System.out.println(n);
					}
					
					//PASSING CLIENT ID AND NAME TO FETCH OWNED PROPERTIES INFO
					ArrayList<String> prop_info = obj_prop.getProperty_info(clients_ID, clients_name);
					for(String d : prop_info) {
						System.out.println(d);
					}
					
					//PASSING RETRIEVED PROPERTY INFO TO FETCH THE RENT AMOUNT FROM RENTS.TXT
					ArrayList<Double> rent_info = obj_rents.getRent_info(prop_info);
					if(rent_info.isEmpty() || rent_info.size()==0) {
						System.out.println("\nNo Rent Info Found for the Client");
						rent_info.add((double) 0);
					}
					
					//PASSING RETRIEVED PROPERTY INFO TO FETCH THE EXPENSE AMOUNT FROM EXPENSES.TXT
					ArrayList<Double> expense_info = obj_expense.getExpense_info(prop_info);
					if(expense_info.isEmpty() || expense_info.size()==0) {
						System.out.println("\nNo Expense Info Found for the Client");
						expense_info.add((double) 0);
					}
					for(double d : expense_info) {
						System.out.println(d);
					}
					
										
					//GENERATING AND PRINTING REPORT
					obj_report.generate_report(clients_name, prop_info, (ArrayList<Double>) rent_info, expense_info);

				}
				
				else if(ch.equals("3")) {
					prop_found = 0;
					System.out.println("Please Enter a Postal Code to Search");
					String pc = sc.next();
					for (String c : properties) {				// FETHCING ALL THE LIST OF CLIENTS MATCHING THE WORDS FROM USER INPUT
						if (c.toLowerCase().contains(pc.toLowerCase())) {
							result_3.add(count + ") @" + c);
							count++;
							prop_found = 1;
						}
					}
					if(prop_found == 0) {
						System.out.println("No Such Property Exist in Records\n\n\n");
						break;
					}
					
					
					// DISPLAYING ALL THE SEARCHED PROPERTIES
					result_prop(result_3);
				}
				else {
					System.out.println("You Entered an Invalid Choice");
				}
				
				
				
				break;
			case 4:
				break;
			case 5:
				return;
			}
		}

		// END OF MAIN FUNCTION
	}

	
	
	
	
	
	
	// DISPLAYING THE ADDRESS FROM THE CLIENTS TEXT FILE TO USER
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












