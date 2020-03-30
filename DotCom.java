import java.util.*;

public class DotCom {
	
	private ArrayList<String> locationCells;
	private String name;
	
	
	// Проверка координат
	public String checkYourself(String userInput) {
		String result = "Мимо";
		int index = locationCells.indexOf(userInput);
		
		if (index >= 0) {
			locationCells.remove(index);
			
			if (locationCells.isEmpty()) {
				result = "Потопил";
				System.out.println("Ой, вы потопили " + name + " :(");
			} else {
				result = "Попал";
			}
		}
		return result;
	}
	
	
	// Геттер
	public ArrayList<String> getLocationCells () {
		return this.locationCells;
	}
	
	
	// Сеттер 
	public void setLocationCells(ArrayList<String> newLocationCells) {
		locationCells = newLocationCells;
	}
	
	
	public void setName (String n) {
		name = n;
	}
	
	
	public String getName() {
		return this.name;
	}
	
}		