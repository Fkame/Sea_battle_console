import java.util.*;

public class DotCom {
	
	private ArrayList<String> locationCells;
	private String name;
	
	
	// �஢�ઠ ���न���
	public String checkYourself(String userInput) {
		String result = "����";
		int index = locationCells.indexOf(userInput);
		
		if (index >= 0) {
			locationCells.remove(index);
			
			if (locationCells.isEmpty()) {
				result = "��⮯��";
				System.out.println("��, �� ��⮯��� " + name + " :(");
			} else {
				result = "�����";
			}
		}
		return result;
	}
	
	
	// �����
	public ArrayList<String> getLocationCells () {
		return this.locationCells;
	}
	
	
	// ����� 
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