package fr.models;

public class Customer extends MyDbUtils
{
	public enum eCom
	{
		EMAIL,
		ADDRESS,
		PHONE
	}
	
	private int		id;
	private String	firstName;
	private String	lastName;
	private String	address;
	private String	email;
	private String	date;
	private boolean	gender;
	private String	phone;
	private eCom	communicationType;
	
	public int		getId()			{ return id; }
	public String	getFirstName()	{ return firstName; }
	public String	getLastName()	{ return lastName; }
	public String	getAddress()	{ return address; }
	public String	getEmail() 		{ return email; }
	public String	getDate()		{ return date; }
	public boolean	isGender()		{ return gender; }
	public String	getPhone()		{ return phone; }
	public eCom		getComType()	{ return communicationType; }
	
	public void setId(int id)					{ this.id = id; }
	public void setFirstName(String firstName)	{ this.firstName = firstName; }
	public void setLastName(String lastName)	{ this.lastName = lastName; }
	public void setAddress(String address)		{ this.address = address; }
	public void setEmail(String email) 			{ this.email = email; }
	public void setDate(String date)			{ this.date = date; }
	public void setGender(boolean gender)		{ this.gender = gender; }
	public void setPhone(String phone)			{ this.phone = phone; }
	public void setComType(eCom com)	{ this.communicationType = com; }
}
