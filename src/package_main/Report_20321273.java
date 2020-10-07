package package_main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Report_20321273 extends Client_20321273 {

	public Report_20321273() {
	}

	public void generate_report(ArrayList<String> clientName, ArrayList<String> propInfo, ArrayList<Float> rentInfo,ArrayList<Float> expenseInfo) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 			// FETCHING CURRENT DATE
		LocalDateTime now = LocalDateTime.now();
		float[] _rent = fetchRent(rentInfo);										//FETCHING RENT FOR EACH PROPERTY FROM THE fetchRent FUNCTION
		String[] _prop = null;
		int x = 0;

		for (String n : clientName) {

			System.out.println(
					"\n\n==============================================================================================");
			System.out.println("PORTFOLIO REPORT\nClient: " + n + "\nReport Generated: " + dtf.format(now) + "\n\n");
			System.out.println(
					"----------------------------------------------------------------------------------------------\n");
			System.out.println("|\t\tProperty|\t\tRent|\t\tExpenses|\t\tFees|\t\tNet|");

			for (String s : propInfo) {
				// System.out.println("|"+_prop[1]+"|\t"+rentInfo.get(x)+"|\t\t"+expenseInfo.get(x)+"|\t"+_prop[6]+"|\t\n");
				x++;
			}

			System.out.println(
					"\n----------------------------------------------------------------------------------------------\n");
			System.out.println(
					"==============================================================================================\n\n");
		}

	}

	// FETCHING ALL THE INDIVIDUAL SUM OF RENT FOR A EACH PROPERTY
	private float[] fetchRent(ArrayList<Float> rent_info) {
		ArrayList<Float> unique = (ArrayList<Float>) rent_info.stream().distinct().collect(Collectors.toList());
		int x = 0;
		float[] rent = null;
		for (Float t : unique) {
			int count = (int) Collections.frequency(rent_info, t);
			rent[x] = (float) (count * t);
		}
		return rent;
	}

	// CALCULATING THE EXPENSE
	private float calc_net_amount(ArrayList<String> expense, ArrayList<String> rentinfo) {
		float net_amount = 0;
		System.out.println("==================================expenseinfo starts");
		for (String s : expense) {
			System.out.println(s);
		}
		System.out.println("==================================rentinfo starts");
		for (String s : rentinfo) {
			System.out.println(s);
		}
		return net_amount;
	}

}
