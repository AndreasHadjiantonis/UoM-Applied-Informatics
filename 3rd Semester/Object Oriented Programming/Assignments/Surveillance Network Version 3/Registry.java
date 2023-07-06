
import java.util.ArrayList;

public class Registry {

	private ArrayList<Suspect> suspects = new ArrayList<>();
	private ArrayList<Communication> communications = new ArrayList<>();
	
	public void addSuspect (Suspect aSuspect) {
		
		suspects.add(aSuspect);
	}
	
	public void addCommunication(Communication aCommunication) {
		
		communications.add(aCommunication);
		
		for(int i=0;i<suspects.size();i++) {
			
			if(suspects.get(i).getNumbers().contains(aCommunication.firstNumber)) {
				
				for(int j=0;j<suspects.size();j++) 
					
					if(suspects.get(j).getNumbers().contains(aCommunication.secondNumber)) {
						
						suspects.get(i).addPossibleSuspect(suspects.get(j));
						suspects.get(j).addPossibleSuspect(suspects.get(i));
					}	
			}
		}
	}
	
	public Suspect getSuspectWithMostPartners () {
		
		int max = 0,index = -1;
		
		for(int i=0;i<suspects.size();i++) {
			
			if(max < suspects.get(i).getNumberOfPossiblePartners())
				max = suspects.get(i).getNumberOfPossiblePartners();
				index = i;
		}
		
		return suspects.get(index);
	}
	
	public PhoneCall getLongestPhoneCallBetween (String number1, String number2){
		
		int maxDuration = 0;
		PhoneCall index = null;
		
		for(Communication i : communications){
			
			if(i instanceof PhoneCall){
				
				PhoneCall phoneCall = (PhoneCall) i;
				
				if(phoneCall.firstNumber.equals(number1) && phoneCall.secondNumber.equals(number2)) {
					
					if(phoneCall.getDurationOfCall() > maxDuration){
						
						maxDuration = phoneCall.getDurationOfCall();
						index = phoneCall;
					}
				}
			}
		}
		
		return index;
	}
	
	public ArrayList<SMS> getMessagesBetween(String number1, String number2) {
		
		ArrayList<SMS> dangerousSMS = new ArrayList<SMS>();
		
		for(Communication i : communications) {
			if(i instanceof SMS) {
				SMS sms = (SMS) i;
				if(sms.firstNumber.equals(number1) && sms.secondNumber.equals(number2))
					if(sms.getMsg().contains("Bomb") || sms.getMsg().contains("Attack") || sms.getMsg().contains("Explosives")
							 || sms.getMsg().contains("Gun")) {
	
						dangerousSMS.add(sms);
					}
			}
		}
		return dangerousSMS;
	}
	
	public void printSuspectsFromCountry(String country) {
		
		System.out.println("Suspects coming from " + country + ":");
		
		for(int i=0;i<suspects.size();i++) {
			
			if(suspects.get(i).getCountryOfOrigin().equals(country)) {
				
				System.out.println(suspects.get(i).getName() + "(" + suspects.get(i).getCodeName() + ")");
			}
		}
	}
	
	public ArrayList<Suspect> getSuspects (){
		return suspects;
		
	}
}