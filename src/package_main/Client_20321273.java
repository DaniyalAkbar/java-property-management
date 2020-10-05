package package_main;

public class Client_20321273 {
	
	public int getProp_id() {
		return prop_id;
	}
	public void setProp_id(int prop_id) {
		this.prop_id = prop_id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getWeekly_rent() {
		return weekly_rent;
	}
	public void setWeekly_rent(String weekly_rent) {
		this.weekly_rent = weekly_rent;
	}
	public String getManag_fee() {
		return manag_fee;
	}
	public void setManag_fee(String manag_fee) {
		this.manag_fee = manag_fee;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	private int prop_id;
	private String client_id;
	private String add;
	private String suburb;
	private String state;
	private String postal;
	private String weekly_rent;
	private String manag_fee;
	private String fullName;
	
	
	
	Client_20321273() {	}
	
	
	Client_20321273(String property, String fName) {
		 String[] x = property.split("@");
		 String[] _prop = x[1].split(",");
		 
		 this.setClient_id(_prop[0]);
		 this.setAdd(_prop[1]);
		 this.setSuburb(_prop[2]);
		 this.setState(_prop[3]);
		 this.setPostal(_prop[4]);
		 this.setWeekly_rent(_prop[5]);
		 this.setManag_fee(_prop[6]);
		 this.setClient_id(_prop[7]);
		 this.setFullName(fName);

		 data();
	}
	
	
	private void data() {
		System.out.println("\n\n============================================================================================================\n");
		System.out.println("Property Address: "+this.getAdd()+"\t Weekly Rent: "+this.getWeekly_rent()+"\t Owner's Full Name: "+ this.getFullName());
		System.out.println("\n============================================================================================================\n\n");
	}
	
	public float calculate_week_rent(float week_rent_amount, int week_num) {
		return (float) week_rent_amount * week_num;
	}
	
	
	
	
	
	
	
	
	
	
}
