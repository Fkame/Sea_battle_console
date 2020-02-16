import java.util.ArrayList;

public class DotComBust {
	
	// ������⢮ �������
	int numOfGuesses = 0;
	
	// ��� ���짮��⥫�᪮�� ����� � ������ �ᯮ������� ��ࠡ�����
	GameHelper helper = new GameHelper();
	
	// �������� ��ࠡ��� ��� ��⮯�����
	ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
	
	
	// ����᪠� �㭪��, ��뢠���� ��� ᮧ����� ��ꥪ� ⥪�饣� �����
	public static void main (String[] args) {
		
		// ����஢����
		/*
		if (DotComBust.setUpGameTest() == true) System.out.println("Method setUpGame is OK");
		else System.out.println("Method setUpGame is WRONG");
		System.out.println("");
		
		if (DotComBust.checkUserGuessTest() == true) System.out.println("Method checkUserGuess is OK");
		else System.out.println("Method checkUserGuess is WRONG");
		System.out.println("");
		
		if (DotComBust.checkUserGuessTestBySetCells() == true) System.out.println("Method checkUserGuessBySetCells is OK");
		else System.out.println("Method checkUserGuessBySetCells is WRONG");
		System.out.println("");
		*/
		
		System.out.println("\n-------------------------\nLet The Game Begin\n------------------------");
		DotComBust game = new DotComBust();
		game.setUpGame();
		//game.showCells();
		game.startPlaying();
		
	}
	
	
	// ������� 3 ��ꥪ� DotCom � �����砥� �� �����. �뢮��� ��������
	private void setUpGame() {
		// �������� "ᠩ⮢"
		DotCom one = new DotCom();
		one.setName("Pets.com");
		DotCom two = new DotCom();
		two.setName("eToys.com");
		DotCom three = new DotCom();
		three.setName("Go2.com");
		dotComList.add(one);
		dotComList.add(two);
		dotComList.add(three);
		
		System.out.println("��� 楫� - ��⮯��� �� \"ᠩ�\".");
		System.out.println("Pets.com, eToys.com, Go2.com");
		System.out.println("����⠩��� ᤥ���� �� �� �������쭮� ������⢮ 室��");
		
		for (DotCom dotComToSet : dotComList) {
			ArrayList<String> newLocation = helper.placeDotCom(3);
			dotComToSet.setLocationCells(newLocation);
		}	
	}
	
	
	// ��� � �஢�ઠ ����� ���짮��⥫� ���� �� ��ࠡ�� �� ���� ��⮯����
	private void startPlaying() {
		while (!dotComList.isEmpty()) {
			String userGuess = helper.getUserInput("������� 室");
			checkUserGuess(userGuess);
		}
		finishGame();
	}
	
	
	// �஢�ઠ ���न��� �� ���������
	private boolean checkUserGuess(String userGuess) {
		numOfGuesses++;
		
		String result = "����";
		
		for (DotCom dotComToTest : dotComList) {
			result = dotComToTest.checkYourself(userGuess);
			if (result.equals("�����")) {
				break;
			}
			if (result.equals("��⮯��")) {
				dotComList.remove(dotComToTest);
				break;
			}
		}
		System.out.println(result);
		
		//for testing
		if (result.equals("�����") || result.equals("��⮯��")) return true;
		else return false;
	}
	
	
	// ����饭�� � ���� ���� � �뢮� ����⨪�
	private void finishGame() {
		System.out.println("�� \"ᠩ��\" �諨 �� ���! ��� ��樨 ⥯��� ��祣� �� ����!");
		if (numOfGuesses <= 18) {
			System.out.println("�� ���﫮 � ��� �ᥣ� " + numOfGuesses + " ����⪮�.");
			System.out.println("�� �ᯥ�� ������� �� ⮣�, ��� ��� �������� �⮭㫨.");
		} else {
			System.out.println("�� ���﫮 � ��� �����쭮 ����� �६���. " + numOfGuesses + "����⪮�.");
			System.out.println("��� ����� �஢��� ����� ���� ��������.");
		}
	}
	
	
	// �����
	public ArrayList<DotCom> getDots() {
		return this.dotComList;
	}
	
	
	// ���� 1
	private static boolean setUpGameTest() {
		System.out.println("Test #1");
		DotComBust dotComBust = new DotComBust();
		dotComBust.setUpGame();
		
		ArrayList<DotCom> dotComListTest = dotComBust.getDots();
		
		if (dotComListTest.size() != 3) return false;
		return true;
	}
	
	
	// ���� 2
	private static boolean checkUserGuessTest() {
		System.out.println("Test #2");
		
		// �������� ���� � ������� ��ࠡ���
		DotComBust dotComBust = new DotComBust();
		dotComBust.setUpGame();
		
		// ����祭�� ᯨ᪠ ��ࠡ��� �⮩ ����
		ArrayList<DotCom> dotComListTest = dotComBust.getDots();
		
		char[] field1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		char[] field2 = {'1', '2', '3', '4', '5', '6', '7'};	
		
		for (char value1 : field1) {
			for (char value2 : field2) {
				// ��ନ�㥬 �⮣���� �������
				String guess = String.valueOf(value1 + value2);
				
				// ����砥� �⢥� �� �㭪樨
				boolean testMethodAnswer = dotComBust.checkUserGuess(guess);
				
				// �஢�ઠ ࠡ��� �㭪樨 ��⥬ ���᪠ ⠪�� ���न��� � ��ꥪ�� DotCom
				boolean answerFromTest = false;
				
				// �஢�ઠ ������� ��ࠡ��
				for (DotCom dot : dotComListTest) {
					ArrayList<String> dotsLocation = dot.getLocationCells();
					
					// �஢�ઠ ������ ���⪨ ��ࠡ��
					for (String cell : dotsLocation) {
						if (cell == guess) answerFromTest = true;
						break;
					}
					
					if (answerFromTest == true) break;
				}
				
				// �᫨ ��� ���� ���⪠ �� ᮢ������ - �㭪�� ࠡ�⠥� ���ࠢ��쭮
				if (testMethodAnswer != answerFromTest) return false;
			}
		}
		
		return true;
	}


	// ���� 2.5
	private static boolean checkUserGuessTestBySetCells() {
		System.out.println("Test #2.5");
		// �������� ���� � ������� ��ࠡ���
		DotComBust dotComBust = new DotComBust();
		dotComBust.setUpGame();
		
		// ����祭�� ᯨ᪠ ��ࠡ��� �⮩ ����
		ArrayList<DotCom> dotComListTest = dotComBust.getDots();
		
		// ���᮪ ����� ���न��� ��ࠡ��
		ArrayList<String> newLocations1 = new ArrayList<String>();
		newLocations1.add("A1");
		newLocations1.add("A2");
		newLocations1.add("A3");
		String[] input1 = {"A1", "A2", "A3"};
		
		ArrayList<String> newLocations2 = new ArrayList<String>();
		newLocations2.add("B1");
		newLocations2.add("B2");
		newLocations2.add("B3");
		String[] input2 = {"B1", "B2", "B3"};
		
		ArrayList<String> newLocations3 = new ArrayList<String>();
		newLocations3.add("C1");
		newLocations3.add("C2");
		newLocations3.add("C3");
		String[] input3 = {"C1", "C2", "C3"};
		
		// ��⠭���� ����� ���न���
		dotComListTest.get(0).setLocationCells(newLocations1);
		dotComListTest.get(1).setLocationCells(newLocations2);
		dotComListTest.get(2).setLocationCells(newLocations3);
		
		// �஢�ઠ �������饩 �㭪樨
		for (String guess : input1) {
			if (dotComBust.checkUserGuess(guess) == false) return false;
		}
		
		for (String guess : input2) {
			if (dotComBust.checkUserGuess(guess) == false) return false;
		}
		
		for (String guess : input3) {
			if (dotComBust.checkUserGuess(guess) == false) return false;
		}
		
		return true;
	}
	
	private void showCells() {
		System.out.println("Out of all cells of all dots");
		for (DotCom dot : dotComList) {
			String name = dot.getName();
			System.out.print("\t" + name + ": ");
			for (String cell : dot.getLocationCells()) {
				System.out.print(cell + "  ");
			}
			System.out.print("\n");
		}
	}
	
}