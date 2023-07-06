public abstract class Communication {
	
	protected String firstNumber;
	protected String secondNumber;
	protected int day;
	protected int month;
	protected int year;
	
	//constructor
	public Communication(String number1, String number2, int day, int month, int year) {
		this.firstNumber = number1;
		this.secondNumber = number2;
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	//methods
	public void printInfo() {
		System.out.println("Between " + firstNumber +"-----" + secondNumber);
		System.out.println("on " + year +"/" + month +"/" + day);
	}
}
