package hadjiantoni_andreas_3;

public class PhoneCall extends Communication {
	
	private int seconds;
	
	public PhoneCall(String firstNumber, String secondNumber, int day, int month, int year, int seconds) {
		super(firstNumber, secondNumber, day, month, year);
		this.seconds = seconds;
	}
	
	//methods
	public void printInfo() {
		
		System.out.println("This phone call has the following info");
		super.printInfo();
		System.out.println("Duration: " + seconds);
	}
	
	public int getDurationOfCall () {
		return seconds;
	}
}
