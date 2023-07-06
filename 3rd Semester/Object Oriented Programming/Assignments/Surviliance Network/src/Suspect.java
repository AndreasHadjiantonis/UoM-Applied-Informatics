package hadjiantoni_andreas_2;
import java.util.ArrayList;

public class Suspect {

	private String name;
	private String codedName;
	private String countryOfOrigin;
	private String activeCity;
	
	private ArrayList<String> numbers = new ArrayList<String>();
	 ArrayList<Suspect> possiblePartners = new ArrayList<Suspect>();
	private ArrayList<Suspect> commonPartners = new ArrayList<Suspect>();
	
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
		
		System.out.println("\n\n");
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
	public int getNumberOfPossiblePartners() {
		return possiblePartners.size();
	}
	
	public ArrayList<Suspect> getPossiblePartners (){
		return possiblePartners;
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
