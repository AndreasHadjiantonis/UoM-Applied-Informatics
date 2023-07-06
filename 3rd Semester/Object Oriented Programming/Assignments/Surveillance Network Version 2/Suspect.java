package hadjiantoni_andreas_3;
import java.util.*;

public class Suspect {

	private String name;
	private String codedName;
	private String countryOfOrigin;
	private String activeCity;
	
	private ArrayList<String> numbers = new ArrayList<String>();
	private ArrayList<Suspect> possiblePartners = new ArrayList<Suspect>();
	private ArrayList<Suspect> commonPartners = new ArrayList<Suspect>();
	private HashSet<Suspect> suggestedPartners = new HashSet<Suspect>();
	
	public Suspect(String name, String codedName, String countryOfOrigin, String activeCity) {
		this.name = name;
		this.codedName = codedName;
		this.countryOfOrigin = countryOfOrigin;
		this.activeCity = activeCity;
	}

	//Methods
	public void addNumber (String number) {
		numbers.add(number);
	}
	
	public void addPossibleSuspect(Suspect aSuspect) {
		
		if(!possiblePartners.contains(aSuspect))
			possiblePartners.add(aSuspect);
	} 
	
	public boolean isConnectedTo(Suspect aSuspect) {
		return possiblePartners.contains(aSuspect);
	}
	
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect) {
			
		for(int i=0;i<possiblePartners.size();i++) {
			for(int j=0;j<aSuspect.possiblePartners.size();j++) {
				if(possiblePartners.get(i).equals(aSuspect.possiblePartners.get(j)) &&
						!commonPartners.contains(possiblePartners.get(i))) {
					commonPartners.add(possiblePartners.get(i));
					break;
					
				}
			}
		}
		return commonPartners;
	}
	
	public void printPossiblePartners () {
		
		for(int i=0;i<possiblePartners.size();i++)
			
			System.out.println(possiblePartners.get(i).name + " " + possiblePartners.get(i).codedName +
					((possiblePartners.get(i).countryOfOrigin.equals(this.countryOfOrigin)) ? "*" : "" ));
	}
	
	public HashSet<Suspect> getSuggestedPossiblePartners (Suspect aSuspect) {
		
		for(int i=0; i<possiblePartners.size();i++) {
			
			ArrayList<Suspect> partners = possiblePartners.get(i).getPossiblePartners();
			
				for(int j=0;j<partners.size();j++)
					if(!possiblePartners.contains(partners.get(j)) && !partners.get(j).equals(aSuspect))
					suggestedPartners.add(partners.get(j));
		}
		
		return suggestedPartners;
	}
	
	//getters Methods
	public String getName() {
		return name;
	}

	public String getCodeName() {
		return codedName;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public ArrayList<String> getNumbers() {
		return numbers;
	}
	public String sendNumbers(int i) {
		return numbers.get(i);
	}
	public int getNumberOfPossiblePartners() {
		return possiblePartners.size();
	}
	
	public ArrayList<Suspect> getPossiblePartners (){
		return possiblePartners;
	}
	public String toString() {
		return name;
		
	}
	
	//override equals for objects comparison
	public boolean equals(Object obj) {
		
		Suspect i = (Suspect)obj;
		
		if(this.name.equals(i.name) && 
				this.codedName.equals(i.codedName) && 
				this.countryOfOrigin.equals(i.countryOfOrigin) && 
				this.activeCity.equals(i.activeCity))
			return true;
		return false;
	}
}
