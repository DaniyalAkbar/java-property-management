package package_main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Rents_20321273 {

	
	Rents_20321273(){	}
	
	
	public ArrayList<String> rents;
	
	
	
	public ArrayList<String> getRents() {
		return rents;
	}
	public void setRents(ArrayList<String> rents) {
		this.rents = rents;
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
		
		
		public ArrayList<String> fetchRents() throws IOException{
			Scanner sc = new Scanner(System.in);

			// READING RENTS TEXT FILE
			ArrayList<String> clients = new ArrayList<String>();
			clients = readFile("src/package_main/txtFiles/rents.txt");
			if (!clients.isEmpty() && clients.get(0).equals("no file found")) {
				System.out.println("No Rensts File Found. Would You Want to Type The Name of the File You Would Want Search For Instead?\nIf So Then Please Type: ");
				String newFileName = sc.next();
				clients = readFile("src/package_main/txtFiles/" + newFileName + ".txt");
				if (clients.get(0).equals("no file found")) {
					System.out.println("Still can't find the file. Creating a file named rents.txt");
					File file = new File("src/package_main/txtFiles/rents.txt");
					file.createNewFile();
					clients.clear();
				}
			}
			this.setRents(clients);
			return clients;
		}
		
		
		
		
		
		
		public ArrayList<String> getRent_info(ArrayList<String> propInfo){
			ArrayList<String> rent_info = new ArrayList<String>();
			if(!propInfo.isEmpty() ) {				
				for(String _rent : this.getRents()) {							//OUTTER LOOP TRAVERSING INDIVIDUAL RENT
					String[] r = _rent.split(",");
					for(String _prop : propInfo) {								//TRAVERSING INDIVIDUAL RENT
						String[] p = _prop.split(",");
						if(r[0].equals(p[0])) {									//MATCHING THE PROPERTY ID FROM INDIVIDUAL RENT TO THE PARAMETER ID VALUE
							rent_info.add(r[1]);
						}
					}
				}
				return rent_info;
			}
			return null;														//IF NO VALUE WAS MATCHED AND FOUND THEN RETURN A BLANK ARRAYLIST
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
}