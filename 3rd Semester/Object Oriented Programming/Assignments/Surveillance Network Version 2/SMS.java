package hadjiantoni_andreas_3;

public class SMS extends Communication{
	
	private String msg;
	
	public SMS(String firstNumber, String secondNumber, int day, int month, int year, String msg) {
		super(firstNumber, secondNumber, day, month, year);
		this.msg = msg;
	}
	
	//methods
	public void printInfo() {
		
		System.out.println("This SMS has the following info");
		super.printInfo();
		System.out.println("Text: " + msg);
	}

	public String getMsg() {
		return msg;
	}
}
